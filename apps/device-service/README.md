# Device Service

MVP-сервис устройств для задания 6 проекта «Тёплый дом».

## API

```http
GET  /devices
GET  /devices/{deviceId}
POST /devices

GET  /devices/{deviceId}/commands
POST /devices/{deviceId}/commands

GET  /actuator/health