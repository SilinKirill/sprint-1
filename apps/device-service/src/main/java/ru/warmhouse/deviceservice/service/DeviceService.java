package ru.warmhouse.deviceservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.warmhouse.deviceservice.dto.request.DeviceCreateRequest;
import ru.warmhouse.deviceservice.dto.response.DeviceResponse;
import ru.warmhouse.deviceservice.model.Device;
import ru.warmhouse.deviceservice.model.DeviceStatus;
import ru.warmhouse.deviceservice.repository.DeviceRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public List<DeviceResponse> getDevices() {
        return deviceRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public DeviceResponse getDevice(UUID deviceId) {
        Device device = findDevice(deviceId);
        return toResponse(device);
    }

    public DeviceResponse createDevice(DeviceCreateRequest request) {
        Device device = new Device(
                UUID.randomUUID(),
                request.getHouseId(),
                request.getTypeId(),
                request.getName(),
                request.getSerialNumber(),
                DeviceStatus.ACTIVE
        );

        return toResponse(deviceRepository.save(device));
    }

    public Device findDevice(UUID deviceId) {
        return deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Device not found: " + deviceId));
    }

    private DeviceResponse toResponse(Device device) {
        return new DeviceResponse(
                device.getId(),
                device.getHouseId(),
                device.getTypeId(),
                device.getName(),
                device.getSerialNumber(),
                device.getStatus()
        );
    }
}
