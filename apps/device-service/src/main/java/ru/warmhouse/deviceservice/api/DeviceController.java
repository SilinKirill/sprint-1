package ru.warmhouse.deviceservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.warmhouse.deviceservice.dto.request.DeviceCreateRequest;
import ru.warmhouse.deviceservice.dto.response.DeviceResponse;
import ru.warmhouse.deviceservice.service.DeviceService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    public List<DeviceResponse> getDevices() {
        return deviceService.getDevices();
    }

    @GetMapping("/{deviceId}")
    public DeviceResponse getDevice(@PathVariable UUID deviceId) {
        return deviceService.getDevice(deviceId);
    }

    @PostMapping
    public DeviceResponse createDevice(@RequestBody DeviceCreateRequest request) {
        return deviceService.createDevice(request);
    }
}
