package ru.warmhouse.deviceservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.warmhouse.deviceservice.model.DeviceStatus;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DeviceResponse {

    private UUID id;
    private UUID houseId;
    private UUID typeId;
    private String name;
    private String serialNumber;
    private DeviceStatus status;
}
