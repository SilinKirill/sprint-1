from repository import TelemetryRepository
from model import Telemetry


class TelemetryService:

    def __init__(self, repository: TelemetryRepository):
        self.repository = repository

    def save(self, telemetry: Telemetry) -> Telemetry:
        return self.repository.save(telemetry)

    def get_all(self):
        return self.repository.find_all()

    def get_by_device_id(self, device_id: str):
        return self.repository.find_by_device_id(device_id)

    def get_latest(self, device_id: str | None):
        items = self.get_all()

        if device_id:
            items = self.get_by_device_id(device_id)

        return items[-1] if items else None