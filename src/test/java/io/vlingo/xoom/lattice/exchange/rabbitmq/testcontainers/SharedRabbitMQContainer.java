package io.vlingo.xoom.lattice.exchange.rabbitmq.testcontainers;

import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.utility.DockerImageName;

public class SharedRabbitMQContainer extends RabbitMQContainer {
  private static final String IMAGE_NAME = "rabbitmq:alpine";
  private static SharedRabbitMQContainer instance;

  private SharedRabbitMQContainer() {
    super(DockerImageName.parse(IMAGE_NAME));
  }

  @SuppressWarnings("resource")
  public static SharedRabbitMQContainer instance() {
    if (instance == null) {
      instance = (SharedRabbitMQContainer) new SharedRabbitMQContainer()
              .withExposedPorts(4369, 5672, 25672, 15672);
      instance.start();
    }
    return instance;
  }

  @Override
  public void stop() {
    // do nothing, the JVM handles shut down
  }
}
