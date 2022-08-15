# xoom-lattice-exchange-rabbitmq

[![Javadocs](http://javadoc.io/badge/io.vlingo.xoom/xoom-lattice-exchange-rabbitmq.svg?color=brightgreen)](http://javadoc.io/doc/io.vlingo.xoom/xoom-lattice-exchange-rabbitmq) [![Build](https://github.com/vlingo/xoom-lattice-exchange-rabbitmq/workflows/Build/badge.svg)](https://github.com/vlingo/xoom-lattice-exchange-rabbitmq/actions?query=workflow%3ABuild) [![Download](https://img.shields.io/maven-central/v/io.vlingo.xoom/xoom-lattice-exchange-rabbitmq?label=maven)](https://search.maven.org/artifact/io.vlingo.xoom/xoom-lattice-exchange-rabbitmq) [![Gitter chat](https://badges.gitter.im/gitterHQ/gitter.png)](https://gitter.im/vlingo-platform-java/lattice)

The VLINGO XOOM platform SDK implementation of XOOM LATTICE Exchange for RabbitMQ.

Docs: https://docs.vlingo.io/xoom-lattice/exchange


See the primary protocol and related ones:
- [io.vlingo.xoom.lattice.exchange.Exchange](https://github.com/vlingo/xoom-lattice/blob/master/src/main/java/io/vlingo/xoom/lattice/exchange/Exchange.java)

See the following tests for examples:
- [io.vlingo.xoom.lattice.exchange.rabbitmq.ExchangeFactoryTest](https://github.com/vlingo/xoom-lattice-exchange-rabbitmq/blob/master/src/test/java/io/vlingo/xoom/lattice/exchange/rabbitmq/ExchangeFactoryTest.java)
- [io.vlingo.xoom.lattice.exchange.rabbitmq.FanOutExchangeTest](https://github.com/vlingo/xoom-lattice-exchange-rabbitmq/blob/master/src/test/java/io/vlingo/xoom/lattice/exchange/rabbitmq/FanOutExchangeTest.java)

### Installation

```xml
  <dependencies>
    <dependency>
      <groupId>io.vlingo.xoom</groupId>
      <artifactId>xoom-lattice-exchange-rabbitmq</artifactId>
      <version>1.11.0</version>
      <scope>compile</scope>
    </dependency>
  </dependencies>
```

```gradle
dependencies {
    compile 'io.vlingo.xoom:xoom-lattice-exchange-rabbitmq:1.11.0'
}
```

License (See LICENSE file for full license)
-------------------------------------------
Copyright © 2012-2022 VLINGO LABS. All rights reserved.

This Source Code Form is subject to the terms of the
Mozilla Public License, v. 2.0. If a copy of the MPL
was not distributed with this file, You can obtain
one at https://mozilla.org/MPL/2.0/.
