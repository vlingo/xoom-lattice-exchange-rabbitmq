// Copyright © 2012-2022 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package nativebuild;

import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

import io.vlingo.xoom.actors.Configuration;
import io.vlingo.xoom.actors.World;
import io.vlingo.xoom.actors.plugin.logging.slf4j.Slf4jLoggerPlugin;
import io.vlingo.xoom.lattice.exchange.ConnectionSettings;
import io.vlingo.xoom.lattice.exchange.rabbitmq.ExchangeFactory;

public final class NativeBuildEntryPoint {
  @SuppressWarnings("unused")
  @CEntryPoint(name = "Java_io_vlingo_xoom_lattice_exchange_rabbitmqnative_Native_start")
  public static int start(@CEntryPoint.IsolateThreadContext long isolateId, CCharPointer name) {
    final String nameString = CTypeConversion.toJavaString(name);
    Configuration configuration =
        Configuration
            .define()
            .with(Slf4jLoggerPlugin
                .Slf4jLoggerPluginConfiguration
                .define()
                .defaultLogger()
                .name("xoom-actors"));
    World world = World.start(nameString, configuration).world();

    ExchangeFactory.fanOutInstance(ConnectionSettings.instance("localhost", ConnectionSettings.UndefinedPort,
        "/", "guest", "guest"), "fanout", true);
    return 0;
  }
}
