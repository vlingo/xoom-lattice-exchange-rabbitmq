package io.vlingo.xoom.lattice.exchange.rabbitmq;

import io.vlingo.xoom.actors.testkit.AccessSafely;
import io.vlingo.xoom.lattice.exchange.ConnectionSettings;
import io.vlingo.xoom.lattice.exchange.Covey;
import io.vlingo.xoom.lattice.exchange.Exchange;
import io.vlingo.xoom.lattice.exchange.rabbitmq.testcontainers.SharedRabbitMQContainer;
import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class FanOutExchangeTest {
  private final SharedRabbitMQContainer rabbitMQContainer = SharedRabbitMQContainer.instance();

  @Test
  public void testThatFanOutExchangeHearsItself() {
    final Exchange exchange = ExchangeFactory.fanOutInstance(settings(), "test-fanout", true);

    final ConcurrentLinkedQueueResults results = new ConcurrentLinkedQueueResults(2);

    exchange
      .register(Covey.of(
              new MessageSender(exchange.connection()),
              new TextMessageReceiver(results),
              new TextExchangeAdapter(),
              String.class,
              String.class,
              Message.class));

    exchange.send("ABC");
    exchange.send("DEF");

    assertEquals(2, (int) results.access.readFrom("answersSize"));
    assertEquals("ABC", results.access.readFrom("answers"));
    assertEquals("DEF", results.access.readFrom("answers"));

    exchange.close();
  }

  private ConnectionSettings settings() {
    return ConnectionSettings.instance(rabbitMQContainer.getHost(), rabbitMQContainer.getMappedPort(5672), "/", "guest", "guest");
  }

  static class ConcurrentLinkedQueueResults {
    AccessSafely access;
    final ConcurrentLinkedQueue<Object> answers;

    ConcurrentLinkedQueueResults(final int totalAnswers) {
      this.answers = new ConcurrentLinkedQueue<>();
      this.access = afterCompleting(totalAnswers);
    }

    private AccessSafely afterCompleting(final int steps) {
      access = AccessSafely
              .afterCompleting(steps)
              .writingWith("answers", (Consumer<String>) answers::add)
              .readingWith("answers", answers::poll)
              .readingWith("answersSize", answers::size);
      return access;
    }
  }
}
