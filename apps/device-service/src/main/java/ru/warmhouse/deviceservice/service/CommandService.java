package ru.warmhouse.deviceservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.warmhouse.deviceservice.dto.request.CommandCreateRequest;
import ru.warmhouse.deviceservice.dto.response.CommandResponse;
import ru.warmhouse.deviceservice.model.CommandStatus;
import ru.warmhouse.deviceservice.model.DeviceCommand;
import ru.warmhouse.deviceservice.publisher.CommandPublisher;
import ru.warmhouse.deviceservice.repository.CommandRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommandService {

    private final DeviceService deviceService;
    private final CommandRepository commandRepository;
    private final CommandPublisher commandPublisher;

    public CommandResponse createCommand(UUID deviceId, CommandCreateRequest request) {
        deviceService.findDevice(deviceId);

        LocalDateTime now = LocalDateTime.now();
        DeviceCommand command = new DeviceCommand(
                UUID.randomUUID(),
                deviceId,
                request.getCommandType(),
                CommandStatus.CREATED,
                request.getParameters(),
                now,
                now
        );

        DeviceCommand savedCommand = commandRepository.save(command);
        commandPublisher.publish(savedCommand);

        return toResponse(savedCommand);
    }

    public List<CommandResponse> getCommands(UUID deviceId) {
        deviceService.findDevice(deviceId);

        return commandRepository.findByDeviceId(deviceId).stream()
                .map(this::toResponse)
                .toList();
    }

    private CommandResponse toResponse(DeviceCommand command) {
        return new CommandResponse(
                command.getId(),
                command.getDeviceId(),
                command.getCommandType(),
                command.getCommandStatus(),
                command.getParameters(),
                command.getCreatedAt(),
                command.getUpdatedAt()
        );
    }
}