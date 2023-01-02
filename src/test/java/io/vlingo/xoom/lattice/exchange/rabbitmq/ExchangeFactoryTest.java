// Copyright © 2012-2023 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.lattice.exchange.rabbitmq;

import io.vlingo.xoom.lattice.exchange.ConnectionSettings;
import io.vlingo.xoom.lattice.exchange.Exchange;
import io.vlingo.xoom.lattice.exchange.rabbitmq.testcontainers.SharedRabbitMQContainer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExchangeFactoryTest {
  private final SharedRabbitMQContainer rabbitMQContainer = SharedRabbitMQContainer.instance();

  @Test
  public void testThatFanOutInstanceConnects() {
    final Exchange exchange = ExchangeFactory.fanOutInstance(settings(), "test-fanout", true);
    assertNotNull(exchange);
    assertEquals("test-fanout", exchange.name());
    exchange.close();
  }

  private ConnectionSettings settings() {
    return ConnectionSettings.instance(rabbitMQContainer.getHost(), rabbitMQContainer.getMappedPort(5672), "/", "guest", "guest");
  }
}
