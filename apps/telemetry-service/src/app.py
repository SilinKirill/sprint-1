import json
import os

from http.server import BaseHTTPRequestHandler, HTTPServer

from controller import TelemetryController
from repository import TelemetryRepository
from service import TelemetryService


repository = TelemetryRepository()
service = TelemetryService(repository)
controller = TelemetryController(service)


class Handler(BaseHTTPRequestHandler):

    def do_GET(self):
        if self.path == "/health":
            self.send_json(200, {"status": "UP"})
            return

        if self.path.startswith("/telemetry/latest"):
            status, body = controller.get_latest(self.path)
            self.send_json(status, body)
            return

        if self.path.startswith("/telemetry"):
            status, body = controller.get_telemetry(self.path)
            self.send_json(status, body)
            return

        self.send_json(404, {"error": "Not found"})

    def do_POST(self):
        if self.path != "/telemetry":
            self.send_json(404, {"error": "Not found"})
            return

        length = int(self.headers.get("Content-Length", "0"))
        body = self.rfile.read(length).decode("utf-8")
        payload = json.loads(body or "{}")

        status, response = controller.create_telemetry(payload)

        self.send_json(status, response)

    def send_json(self, status: int, body):
        response = json.dumps(body, ensure_ascii=False).encode("utf-8")

        self.send_response(status)
        self.send_header("Content-Type", "application/json; charset=utf-8")
        self.send_header("Content-Length", str(len(response)))
        self.end_headers()

        self.wfile.write(response)

    def log_message(self, format, *args):
        return


if __name__ == "__main__":
    port = int(os.getenv("TELEMETRY_SERVICE_PORT", "8083"))

    server = HTTPServer(("0.0.0.0", port), Handler)

    print(f"Telemetry service started on port {port}")

    server.serve_forever()