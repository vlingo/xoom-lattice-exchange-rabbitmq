// Copyright © 2012-2023 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.lattice.exchange.rabbitmq;

import io.vlingo.xoom.lattice.exchange.ExchangeReceiver;

public class TextMessageReceiver implements ExchangeReceiver<String> {
  private final Object lock;
  private final FanOutExchangeTest.ConcurrentLinkedQueueResults results;

  public TextMessageReceiver(final FanOutExchangeTest.ConcurrentLinkedQueueResults results) {
    this.results = results;
    this.lock = new Object();
  }

  @Override
  public void receive(final String message) {
    synchronized (lock) {
      results.access.writeUsing("answers", message);
    }
  }
}
