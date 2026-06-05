package ru.warmhouse.deviceservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.warmhouse.deviceservice.dto.request.CommandCreateRequest;
import ru.warmhouse.deviceservice.dto.response.CommandResponse;
import ru.warmhouse.deviceservice.service.CommandService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/devices/{deviceId}/commands")
public class CommandController {

    private final CommandService commandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommandResponse createCommand(@PathVariable UUID deviceId, @RequestBody CommandCreateRequest request) {
        return commandService.createCommand(deviceId, request);
    }

    @GetMapping
    public List<CommandResponse> getCommands(@PathVariable UUID deviceId) {
        return commandService.getCommands(deviceId);
    }
}
