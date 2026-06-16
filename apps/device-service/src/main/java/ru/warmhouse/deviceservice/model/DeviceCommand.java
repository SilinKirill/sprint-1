package ru.warmhouse.deviceservice.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceCommand {
    private UUID id;
    private UUID deviceId;
    private CommandType commandType;
    private CommandStatus commandStatus;
    private String parameters;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
