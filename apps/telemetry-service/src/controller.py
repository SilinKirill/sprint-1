import json
from dataclasses import asdict
from datetime import datetime, timezone
from random import uniform
from urllib.parse import parse_qs, urlparse
from uuid import uuid4

from model import Telemetry
from service import TelemetryService


class TelemetryController:

    def __init__(self, service: TelemetryService):
        self.service = service

    def get_telemetry(self, path: str):
        parsed = urlparse(path)
        device_id = parse_qs(parsed.query).get("deviceId", [None])[0]

        if device_id:
            items = self.service.get_by_device_id(device_id)
        else:
            items = self.service.get_all()

        return 200, [asdict(item) for item in items]

    def get_latest(self, path: str):
        parsed = urlparse(path)
        device_id = parse_qs(parsed.query).get("deviceId", [None])[0]

        item = self.service.get_latest(device_id)

        if item is None:
            return 404, {"error": "Telemetry not found"}

        return 200, asdict(item)

    def create_telemetry(self, payload: dict):
        value = payload.get("value", round(uniform(20, 25), 1))

        telemetry = Telemetry(
            id=str(uuid4()),
            device_id=payload.get("deviceId", "33333333-3333-3333-3333-333333333333"),
            type=payload.get("type", "temperature"),
            value=str(value),
            unit=payload.get("unit", "°C"),
            measured_at=payload.get(
                "measuredAt",
                datetime.now(timezone.utc).isoformat()
            )
        )

        saved = self.service.save(telemetry)

        return 201, asdict(saved)