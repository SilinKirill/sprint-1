package ru.warmhouse.deviceservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.warmhouse.deviceservice.model.CommandStatus;
import ru.warmhouse.deviceservice.model.CommandType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CommandResponse {

    private UUID id;
    private UUID deviceId;
    private CommandType commandType;
    private CommandStatus commandStatus;
    private String parameters;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
