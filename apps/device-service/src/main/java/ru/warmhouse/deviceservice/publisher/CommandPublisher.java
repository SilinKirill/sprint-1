package ru.warmhouse.deviceservice.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.warmhouse.deviceservice.model.DeviceCommand;

@Slf4j
@Component
public class CommandPublisher {

    public void publish(DeviceCommand command) {
        // TODO: publish command to MessageBroker for DeviceAdapterService
        log.info("Command sent: deviceId={}, commandType={}", command.getDeviceId(), command.getCommandType());
    }
}
