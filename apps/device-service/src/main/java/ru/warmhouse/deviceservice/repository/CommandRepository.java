package ru.warmhouse.deviceservice.repository;

import org.springframework.stereotype.Repository;
import ru.warmhouse.deviceservice.model.DeviceCommand;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CommandRepository {

    private final Map<UUID, DeviceCommand> commands = new ConcurrentHashMap<>();

    public DeviceCommand save(DeviceCommand command) {
        commands.put(command.getId(), command);
        return command;
    }

    public List<DeviceCommand> findByDeviceId(UUID deviceId) {
        return commands.values().stream()
                .filter(command -> command.getDeviceId().equals(deviceId))
                .toList();
    }
}
