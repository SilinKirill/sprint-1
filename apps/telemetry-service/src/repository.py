from model import Telemetry


class TelemetryRepository:

    def __init__(self):
        self.telemetry = []

    def save(self, telemetry: Telemetry) -> Telemetry:
        self.telemetry.append(telemetry)
        return telemetry

    def find_all(self):
        return self.telemetry

    def find_by_device_id(self, device_id: str):
        return [
            item for item in self.telemetry
            if item.device_id == device_id
        ]