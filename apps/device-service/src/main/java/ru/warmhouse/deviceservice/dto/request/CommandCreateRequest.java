package ru.warmhouse.deviceservice.dto.request;

import lombok.Data;
import ru.warmhouse.deviceservice.model.CommandType;

@Data
public class CommandCreateRequest {

    private CommandType commandType;
    private String parameters;
}
