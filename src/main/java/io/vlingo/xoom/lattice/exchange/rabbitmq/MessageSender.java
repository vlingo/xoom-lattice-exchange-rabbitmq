// Copyright © 2012-2022 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.lattice.exchange.rabbitmq;

import io.vlingo.xoom.lattice.exchange.ExchangeSender;

/**
 * A sender of Exchange/Queue text messages.
 */
public class MessageSender implements ExchangeSender<Message> {
  private final MessageProducer messageProducer;

  /*
   * @see io.vlingo.xoom.lattice.exchange.ExchangeSender#send(java.lang.Object)
   */
  @Override
  public void send(final Message message) {
    messageProducer.send(message.payload, message.messageParameters);
  }

  /**
   * Constructs my default state.
   * @param brokerConnection the BrokerConnection for which my messageProducer is created
   */
  public MessageSender(final BrokerConnection brokerConnection) {
    this.messageProducer = MessageProducer.instance(brokerConnection);
  }
}
