// Copyright © 2012-2023 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.lattice.exchange.rabbitmq;

import java.util.Date;
import java.util.Map;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.MessageProperties;

import io.vlingo.xoom.lattice.exchange.MessageParameters;

/**
 * A mapper from MessageParameters to RabbitMQ BasicProperties.
 */
public class MessageParametersMapper {
  private static BasicProperties defaultProperties = MessageProperties.PERSISTENT_BASIC;

  public static BasicProperties from(final MessageParameters messageParameters) {
    return new BasicProperties(
            contentType(messageParameters.contentType()),
            contentEncoding(messageParameters.contentEncoding()),
            headers(messageParameters.headers()),
            deliveryModeOf(messageParameters),
            priorityOf(messageParameters),
            correlationId(messageParameters.correlationId()),
            replyTo(messageParameters.replyTo()),
            timeToLive(messageParameters.timeToLive()),
            messageId(messageParameters.messageId()),
            timestamp(messageParameters.timestamp()),
            typeName(messageParameters.typeName()),
            userId(messageParameters.userId()),
            applicationId(messageParameters.applicationId()),
            null);      // clusterId (no longer used)
  }

  private static String applicationId(final String applicationId) {
    return applicationId == null ? defaultProperties.getAppId() : applicationId;
  }

  private static String correlationId(final String correlationId) {
    return correlationId == null ? defaultProperties.getCorrelationId() : correlationId;
  }

  private static String contentEncoding(final String contentEncoding) {
    return contentEncoding == null ? defaultProperties.getContentEncoding() : contentEncoding;
  }

  private static String contentType(final String contentType) {
    return contentType == null ? defaultProperties.getContentType() : contentType;
  }

  private static int deliveryModeOf(final MessageParameters messageParameters) {
    return messageParameters.isDurableDeliveryMode() ? 2: 1;
  }

  private static Map<String, Object> headers(final Map<String, Object> headers) {
    return headers == null ? defaultProperties.getHeaders() : headers;
  }

  private static String messageId(final String messageId) {
    return messageId == null ? defaultProperties.getMessageId() : messageId;
  }

  private static int priorityOf(final MessageParameters messageParameters) {
    if (messageParameters.priority() != null) {
      switch (messageParameters.priority()) {
      case Low:
      case P0:
        return 0;
      case P1:
        return 1;
      case P2:
        return 2;
      case P3:
        return 3;
      case Medium:
      case Normal:
      case P4:
        return 4;
      case P5:
        return 5;
      case P6:
        return 6;
      case P7:
        return 7;
      case P8:
        return 8;
      case High:
      case P9:
        return 9;
      }
    }

    return 4;
  }

  private static String replyTo(final String replyTo) {
    return replyTo == null ? defaultProperties.getReplyTo() : replyTo;
  }

  private static Date timestamp(final long timestamp) {
    return timestamp <= 0 ? defaultProperties.getTimestamp() : new Date(timestamp);
  }

  private static String timeToLive(final long timeToLive) {
    return timeToLive <= 0 ? defaultProperties.getExpiration() : Long.toString(timeToLive);
  }

  private static String typeName(final String typeName) {
    return typeName == null ? defaultProperties.getType() : typeName;
  }

  private static String userId(final String userId) {
    return userId == null ? defaultProperties.getUserId() : userId;
  }
}
