# vlingo-lattice-exchange-rabbitmq

[![Javadocs](http://javadoc.io/badge/io.vlingo/vlingo-lattice-exchange-rabbitmq.svg?color=brightgreen)](http://javadoc.io/doc/io.vlingo/vlingo-lattice-exchange-rabbitmq) [![Build](https://github.com/vlingo/vlingo-lattice-exchange-rabbitmq/workflows/Build/badge.svg)](https://github.com/vlingo/vlingo-lattice-exchange-rabbitmq/actions?query=workflow%3ABuild) [ ![Download](https://api.bintray.com/packages/vlingo/vlingo-platform-java/vlingo-lattice-exchange-rabbitmq/images/download.svg) ](https://bintray.com/vlingo/vlingo-platform-java/vlingo-lattice-exchange-rabbitmq/_latestVersion) [![Gitter chat](https://badges.gitter.im/gitterHQ/gitter.png)](https://gitter.im/vlingo-platform-java/lattice)

The VLINGO XOOM platform SDK implementation of XOOM LATTICE Exchange for RabbitMQ.

Docs: https://docs.vlingo.io/vlingo-lattice/exchange


See the primary protocol and related ones:
- [io.vlingo.lattice.exchange.Exchange](https://github.com/vlingo/vlingo-lattice/blob/master/src/main/java/io/vlingo/lattice/exchange/Exchange.java)

See the following tests for examples:
- [io.vlingo.lattice.exchange.rabbitmq.ExchangeFactoryTest](https://github.com/vlingo/vlingo-lattice-exchange-rabbitmq/blob/master/src/test/java/io/vlingo/lattice/exchange/rabbitmq/ExchangeFactoryTest.java)
- [io.vlingo.lattice.exchange.rabbitmq.FanOutExchangeTest](https://github.com/vlingo/vlingo-lattice-exchange-rabbitmq/blob/master/src/test/java/io/vlingo/lattice/exchange/rabbitmq/FanOutExchangeTest.java)

### Important
If using snapshot builds [follow these instructions](https://github.com/vlingo/vlingo-platform#snapshots-repository) or you will experience failures.

### Bintray

```xml
  <repositories>
    <repository>
      <id>jcenter</id>
      <url>https://jcenter.bintray.com/</url>
    </repository>
  </repositories>
  <dependencies>
    <dependency>
      <groupId>io.vlingo</groupId>
      <artifactId>vlingo-lattice-exchange-rabbitmq</artifactId>
      <version>1.5.0</version>
      <scope>compile</scope>
    </dependency>
  </dependencies>
```

```gradle
dependencies {
    compile 'io.vlingo:vlingo-lattice-exchange-rabbitmq:1.5.0'
}

repositories {
    jcenter()
}
```

## Docker and Bouncing the Server Volume
RabbitMQ must be running for tests. See the `rmqbounce.sh`. This shell script can be used to bounce the RabbitMQ volume named in `docker-compose.yml`:

  `vlingo-lattice-exchange-rabbitmq`

`$ ./rmqbounce.sh`


License (See LICENSE file for full license)
-------------------------------------------
Copyright © 2012-2020 VLINGO LABS. All rights reserved.

This Source Code Form is subject to the terms of the
Mozilla Public License, v. 2.0. If a copy of the MPL
was not distributed with this file, You can obtain
one at https://mozilla.org/MPL/2.0/.
