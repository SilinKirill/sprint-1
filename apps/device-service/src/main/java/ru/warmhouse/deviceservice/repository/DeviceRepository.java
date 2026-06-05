package ru.warmhouse.deviceservice.repository;

import org.springframework.stereotype.Repository;
import ru.warmhouse.deviceservice.model.Device;
import ru.warmhouse.deviceservice.model.DeviceStatus;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DeviceRepository {

    private final Map<UUID, Device> devices = new ConcurrentHashMap<>();

    public DeviceRepository() {
        UUID deviceId = UUID.fromString("33333333-3333-3333-3333-333333333333");

        Device device = new Device(
                deviceId,
                UUID.fromString("22222222-2222-2222-2222-222222222222"),
                UUID.fromString("44444444-4444-4444-4444-444444444444"),
                "Living Room Heater",
                "WH-HEATER-001",
                DeviceStatus.ACTIVE
        );

        devices.put(deviceId, device);
    }

    public Collection<Device> findAll() {
        return devices.values();
    }

    public Optional<Device> findById(UUID id) {
        return Optional.ofNullable(devices.get(id));
    }

    public Device save(Device device) {
        devices.put(device.getId(), device);
        return device;
    }
}
