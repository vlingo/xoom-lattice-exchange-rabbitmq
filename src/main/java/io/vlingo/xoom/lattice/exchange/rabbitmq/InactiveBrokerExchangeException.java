// Copyright © 2012-2021 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package io.vlingo.xoom.lattice.exchange.rabbitmq;

public class InactiveBrokerExchangeException extends RuntimeException {

  public InactiveBrokerExchangeException(final String exchangeName) {
    super("Exchange " + exchangeName + " is inactive. Unable to perform the requested operation.");
  }
}
