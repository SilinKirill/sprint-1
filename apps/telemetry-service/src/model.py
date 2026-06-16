from dataclasses import dataclass


@dataclass
class Telemetry:
    id: str
    device_id: str
    type: str
    value: str
    unit: str
    measured_at: str