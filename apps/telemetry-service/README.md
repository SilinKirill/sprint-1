# Telemetry Service

MVP-сервис телеметрии для задания 6 проекта «Тёплый дом».

## API

```http
POST /telemetry
GET  /telemetry
GET  /telemetry?deviceId={deviceId}
GET  /telemetry/latest?deviceId={deviceId}
GET  /health