// Copyright © 2012-2022 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.lattice.exchange.rabbitmq;

import io.vlingo.xoom.lattice.exchange.ConnectionSettings;
import io.vlingo.xoom.lattice.exchange.Covey;
import io.vlingo.xoom.lattice.exchange.Exchange;
import io.vlingo.xoom.lattice.exchange.Forwarder;
import io.vlingo.xoom.lattice.exchange.rabbitmq.BrokerConnection.Type;

/**
 * An Exchange for RabbitMQ via a BrokerChannel.
 */
class BrokerExchange implements Exchange {
  /** My type, which is the type of exchange. */
  public final String type;

  /** My connection to the broker. */
  final BrokerConnection connection;

  /** My exchange listener, which forwards messages through my forwarder. */
  private final ExchangeListener listener;

  /** My forwarder. */
  private final Forwarder forwarder;

  /** Whether I am active or not */
  private final boolean active;

  /**
   * Answer my forwarder for internal use only.
   * @return Forwarder
   */
  Forwarder forwarder() {
    return forwarder;
  }

  //====================================
  // Exchange
  //====================================

  /*
   * @see io.vlingo.xoom.lattice.exchange.Exchange#close()
   */
  @Override
  public void close() {
    if(listener != null) {
      listener.close();
    }
    connection.close();
  }

  /*
   * @see io.vlingo.xoom.lattice.exchange.Exchange#channel()
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T> T channel() {
    checkStatus();
    return (T) connection.channel();
  }

  /*
   * @see io.vlingo.xoom.lattice.exchange.Exchange#connection()
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T> T connection() {
    checkStatus();
    return (T) connection;
  }

  /*
   * @see io.vlingo.xoom.lattice.exchange.Exchange#name()
   */
  @Override
  public String name() {
    checkStatus();
    return connection.name;
  }

  /*
   * @see io.vlingo.xoom.lattice.exchange.Exchange#register(io.vlingo.xoom.lattice.exchange.Covey)
   */
  @Override
  public <L, E, EX> Exchange register(final Covey<L, E, EX> covey) {
    checkStatus();
    forwarder.register(covey);
    return this;
  }

  /*
   * @see io.vlingo.xoom.lattice.exchange.Exchange#send(java.lang.Object)
   */
  @Override
  public <L> void send(final L local) {
    checkStatus();
    forwarder.forwardToSender(local);
  }

  /**
   * Constructs my default state.
   * @param connectionSettings the ConnectionSettings
   * @param name the String name of the exchange
   * @param type the String type of the exchange
   * @param isDurable the boolean indicating whether or not I am durable
   */
  BrokerExchange(
          final ConnectionSettings connectionSettings,
          final String name,
          final String type,
          final boolean isDurable) {
    this(connectionSettings, name, name + ".self-listening-queue", type, isDurable);
  }

  BrokerExchange(
          final ConnectionSettings connectionSettings,
          final String name,
          final String queueListenerName,
          final String type,
          final boolean isDurable) {

    this.connection = new BrokerConnection(connectionSettings, Type.Exchange, name, isDurable);
    this.forwarder = new Forwarder();
    this.type = type;
    this.active = true;
    try {
      this.connection.channel().exchangeDeclare(name, type, isDurable);
      this.listener = new ExchangeListener(this, queueListenerName);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to create/open the exchange because: " + e.getMessage(), e);
    }
  }

  static BrokerExchange inactiveInstanceWithName(final String name) {
    return new BrokerExchange(name);
  }

  /**
   * Constructs my inactive state.
   *
   * @param name the String name of the exchange
   */
  BrokerExchange(final String name) {
    this.connection = new BrokerConnection(null, null, name);
    this.forwarder = null;
    this.type = null;
    this.listener =null;
    this.active = false;
  }

  /*
   * @see io.vlingo.xoom.lattice.exchange.Exchange#isActive()
   */
  @Override
  public boolean isActive() {
    return active;
  }

  private void checkStatus() {
    if(!active) {
      throw new InactiveBrokerExchangeException(connection.name);
    }
  }

}
