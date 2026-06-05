import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Random;

public class TemperatureApi {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        server.createContext("/temperature", TemperatureApi::handleTemperature);
        server.start();

        System.out.println("Temperature API started on port 8081");
    }

    private static void handleTemperature(HttpExchange exchange) throws IOException {
        if (!"GET".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        URI uri = exchange.getRequestURI();

        String sensorId = getSensorId(uri);
        String location = getLocation(uri);

        if (location.isBlank()) {
            location = switch (sensorId) {
                case "1" -> "Living Room";
                case "2" -> "Bedroom";
                case "3" -> "Kitchen";
                default -> "Unknown";
            };
        }

        if (sensorId.isBlank()) {
            sensorId = switch (location) {
                case "Living Room" -> "1";
                case "Bedroom" -> "2";
                case "Kitchen" -> "3";
                default -> "0";
            };
        }

        double value = Math.round((18 + RANDOM.nextDouble(10)) * 10.0) / 10.0;

        String response = """
                {
                  "value": %.1f,
                  "unit": "°C",
                  "timestamp": "%s",
                  "location": "%s",
                  "status": "active",
                  "sensor_id": "%s",
                  "sensor_type": "temperature",
                  "description": "Temperature in %s"
                }
                """.formatted(value, Instant.now(), location, sensorId, location);

        byte[] body = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(200, body.length);
        exchange.getResponseBody().write(body);
        exchange.close();
    }

    private static String getSensorId(URI uri) {
        String path = uri.getPath();

        if (path.startsWith("/temperature/")) {
            return path.substring("/temperature/".length());
        }

        return "";
    }

    private static String getLocation(URI uri) {
        String query = uri.getRawQuery();

        if (query != null && query.startsWith("location=")) {
            return URLDecoder.decode(query.substring("location=".length()), StandardCharsets.UTF_8);
        }

        return "";
    }
}