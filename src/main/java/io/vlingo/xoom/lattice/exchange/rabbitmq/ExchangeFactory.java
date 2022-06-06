// Copyright © 2012-2022 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.lattice.exchange.rabbitmq;

import io.vlingo.xoom.lattice.exchange.ConnectionSettings;

/**
 * A factory that produces RabbitMQ {@code Exchange} instances.
 */
public class ExchangeFactory {

  /**
   * Answers a new instance of a direct Exchange with the name {@code name} and
   * that listens on the default self-listing queue. The underlying
   * exchange has the isDurable quality, and is not auto-deleted.
   * @param connectionSettings the ConnectionSettings
   * @param name the String name of the exchange
   * @param isDurable the boolean indicating whether or not I am durable
   * @return BrokerExchange
   */
  public static BrokerExchange directInstance(
          final ConnectionSettings connectionSettings,
          final String name,
          final boolean isDurable) {

    return new BrokerExchange(connectionSettings, name, "direct", isDurable);
  }

  /**
   * Answers a new instance of a direct Exchange with the name {@code name} and
   * that listens on the queue named {@code queueListenerName}. The underlying
   * exchange has the isDurable quality, and is not auto-deleted.
   * @param connectionSettings the ConnectionSettings
   * @param name the String name of the exchange
   * @param queueListenerName the String name of the queue that listens to this Exchange
   * @param isDurable the boolean indicating whether or not I am durable
   * @return BrokerExchange
   */
  public static BrokerExchange directInstance(
          final ConnectionSettings connectionSettings,
          final String name,
          final String queueListenerName,
          final boolean isDurable) {

    return new BrokerExchange(connectionSettings, name, "direct", isDurable);
  }

  /**
   * Answers a new instance of a fan-out Exchange with the name {@code name} and
   * that listens on the default self-listing queue. The underlying exchange
   * has the isDurable quality, and is not auto-deleted.
   * @param connectionSettings the ConnectionSettings
   * @param name the String name of the exchange
   * @param isDurable the boolean indicating whether or not I am durable
   * @return BrokerExchange
   */
  public static BrokerExchange fanOutInstance(
          final ConnectionSettings connectionSettings,
          final String name,
          final boolean isDurable) {
    return new BrokerExchange(connectionSettings, name, "fanout", isDurable);
  }

  /**
   * Answers a new instance of a fan-out Exchange with the name {@code name} and
   * that listens on the queue named {@code queueListenerName}. The underlying
   * exchange has the isDurable quality, and is not auto-deleted.
   * @param connectionSettings the ConnectionSettings
   * @param name the String name of the exchange
   * @param queueListenerName the String name of the queue that listens to this Exchange
   * @param isDurable the boolean indicating whether or not I am durable
   * @return BrokerExchange
   */
  public static BrokerExchange fanOutInstance(
          final ConnectionSettings connectionSettings,
          final String name,
          final String queueListenerName,
          final boolean isDurable) {
    return new BrokerExchange(connectionSettings, name, queueListenerName, "fanout", isDurable);
  }

  /**
   * Answers a new instance of a fan-out Exchange with the name {@code name} and
   * that listens on the default self-listing queue. The underlying exchange
   * has the isDurable quality, and is not auto-deleted. In the case of failure
   * the exception is swallowed and an inactive Exchange is answered.
   * @param connectionSettings the ConnectionSettings
   * @param name the String name of the exchange
   * @param isDurable the boolean indicating whether or not I am durable
   * @return BrokerExchange on success, returns an active BrokerExchange.
   * Otherwise, an inactive BrokerExchange without propagating the
   * connection failure exception.
   */
  public static BrokerExchange fanOutInstanceQuietly(
          final ConnectionSettings connectionSettings,
          final String name,
          final boolean isDurable) {
    try {
      return fanOutInstance(connectionSettings, name, isDurable);
    } catch (final IllegalArgumentException illegalArgumentException) {
      return BrokerExchange.inactiveInstanceWithName(name);
    }
  }

  /**
   * Answers a new instance of a fan-out Exchange with the name {@code name} and
   * that listens on the queue named {@code queueListenerName}. The underlying
   * exchange has the isDurable quality, and is not auto-deleted. In the case
   * of failure the exception is swallowed and an inactive Exchange is answered.
   * @param connectionSettings the ConnectionSettings
   * @param name the String name of the exchange
   * @param queueListenerName the String name of the queue that listens to this Exchange
   * @param isDurable the boolean indicating whether or not I am durable
   * @return an active BrokerExchange on success; otherwise, an inactive
   * BrokerExchange without propagating the connection failure exception.
   */
  public static BrokerExchange fanOutInstanceQuietly(
          final ConnectionSettings connectionSettings,
          final String name,
          final String queueListenerName,
          final boolean isDurable) {
    try {
      return fanOutInstance(connectionSettings, name, queueListenerName, isDurable);
    } catch (final IllegalArgumentException illegalArgumentException) {
      return BrokerExchange.inactiveInstanceWithName(name);
    }
  }

  /**
   * Answers a new instance of a headers Exchange with the name {@code name} and
   * that listens on the default self-listing queue. The underlying exchange
   * has the isDurable quality, and is not auto-deleted.
   * @param connectionSettings the ConnectionSettings
   * @param name the String name of the exchange
   * @param isDurable the boolean indicating whether or not I am durable
   * @return BrokerExchange
   */
  public static BrokerExchange headersInstance(
          final ConnectionSettings connectionSettings,
          final String name,
          final boolean isDurable) {

    return new BrokerExchange(connectionSettings, name, "headers", isDurable);
  }

  /**
   * Answers a new instance of a headers Exchange with the name {@code name} and
   * that listens on the queue named {@code queueListenerName}. The underlying
   * exchange has the isDurable quality, and is not auto-deleted.
   * @param connectionSettings the ConnectionSettings
   * @param name the String name of the exchange
   * @param queueListenerName the String name of the queue that listens to this Exchange
   * @param isDurable the boolean indicating whether or not I am durable
   * @return BrokerExchange
   */
  public static BrokerExchange headersInstance(
          final ConnectionSettings connectionSettings,
          final String name,
          final String queueListenerName,
          final boolean isDurable) {

    return new BrokerExchange(connectionSettings, name, queueListenerName, "headers", isDurable);
  }

  /**
   * Answers a new instance of a topic Exchange with the name {@code name} and
   * that listens on the default self-listing queue. The underlying exchange
   * has the isDurable quality, and is not auto-deleted.
   * @param connectionSettings the ConnectionSettings
   * @param name the String name of the exchange
   * @param isDurable the boolean indicating whether or not I am durable
   * @return BrokerExchange
   */
  public static BrokerExchange topicInstance(
          final ConnectionSettings connectionSettings,
          final String name,
          final boolean isDurable) {

    return new BrokerExchange(connectionSettings, name, "topic", isDurable);
  }

  /**
   * Answers a new instance of a topic Exchange with the name {@code name} and
   * that listens on the queue named {@code queueListenerName}. The underlying
   * exchange has the isDurable quality, and is not auto-deleted.
   * @param connectionSettings the ConnectionSettings
   * @param name the String name of the exchange
   * @param isDurable the boolean indicating whether or not I am durable
   * @return BrokerExchange
   */
  public static BrokerExchange topicInstance(
          final ConnectionSettings connectionSettings,
          final String name,
          final String queueListenerName,
          final boolean isDurable) {

    return new BrokerExchange(connectionSettings, name, queueListenerName, "topic", isDurable);
  }
}
