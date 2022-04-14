// Copyright © 2012-2022 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.lattice.exchange.rabbitmq;

public class InactiveBrokerExchangeException extends RuntimeException {
  private static final long serialVersionUID = -5018416201374350407L;

  private static final String INACTIVITY_MESSAGE =
          "\n==========================================================================\n" +
          "                                                                         \n" +
          " WARNING: %s exchange is inactive because the Broker Connection failed.  \n" +
          "                                                                         \n" +
          "==========================================================================\n";

  public InactiveBrokerExchangeException(final String exchangeName) {
    super(String.format(INACTIVITY_MESSAGE, exchangeName));
  }
}
