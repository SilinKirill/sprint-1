package ru.warmhouse.deviceservice.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class DeviceCreateRequest {

    private UUID houseId;
    private UUID typeId;
    private String name;
    private String serialNumber;
}
