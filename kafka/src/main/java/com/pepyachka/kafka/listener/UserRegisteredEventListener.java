package com.pepyachka.kafka.listener;

import com.google.protobuf.InvalidProtocolBufferException;
import com.pepyachka.core.service.UserService;
import com.pepyachka.event.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRegisteredEventListener {

  private final UserService userService;

  @SneakyThrows(InvalidProtocolBufferException.class)
  private static UserRegisteredEvent parseUserRegisteredEvent(ConsumerRecord<String, byte[]> consumerRecord) {
    return UserRegisteredEvent.parseFrom(consumerRecord.value());
  }

  @KafkaListener(
      topics = "${app.kafka.topics.user-registered-event-topic-v1}",
      groupId = "${app.kafka.consumers.user-registered-event-topic-v1.group-id}",
      concurrency = "${app.kafka.consumers.user-registered-event-topic-v1.concurrency:1}"
  )
  public void onUserRegistered(ConsumerRecord<String, byte[]> consumerRecord) {
    try {
      log.info("Received user registered event with key: {}", consumerRecord.key());

      UserRegisteredEvent event = parseUserRegisteredEvent(consumerRecord);

      log.debug("Parsed event: {}", event);

      // Call service to process user registration
      // userService.processUserRegistration(event);

    } catch (Exception e) {
      log.error("Error processing user registered event with key: {}", consumerRecord.key(), e);
      throw e;
    }
  }
}
