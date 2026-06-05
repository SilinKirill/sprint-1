package ru.warmhouse.deviceservice.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.warmhouse.deviceservice.model.DeviceCommand;

@Component
@RequiredArgsConstructor
public class CommandPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${device.command.queue}")
    private String commandQueue;

    public void publish(DeviceCommand command) {
        rabbitTemplate.convertAndSend(commandQueue, command);
    }
}