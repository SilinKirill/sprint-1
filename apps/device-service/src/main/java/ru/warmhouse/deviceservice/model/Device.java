package ru.warmhouse.deviceservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Device {

    private UUID id;
    private UUID houseId;
    private UUID typeId;
    private String name;
    private String serialNumber;
    private DeviceStatus status;
}
