package io.vlingo.lattice.exchange.rabbitmq;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

import io.vlingo.actors.testkit.AccessSafely;
import org.junit.Test;

import io.vlingo.lattice.exchange.ConnectionSettings;
import io.vlingo.lattice.exchange.Covey;
import io.vlingo.lattice.exchange.Exchange;

public class FanOutExchangeTest {

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
    return ConnectionSettings.instance("localhost", ConnectionSettings.UndefinedPort, "/", "guest", "guest");
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
