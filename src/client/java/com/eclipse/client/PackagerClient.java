package com.eclipse.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class PackagerClient {

    private static final String BASE_URL = "https://gestational-ictic-charlee.ngrok-free.dev";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    // ✅ /get
    public static void getUUID(List<UUID> uuids) {
        try {
            JsonObject json = new JsonObject();

            // UUID Liste → Strings
            json.add("uuids", gson.toJsonTree(
                    uuids.stream().map(UUID::toString).toList()
            ));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/get"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("GET: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ /refresh
    public static void refresh(UUID uuid) {
        try {
            JsonObject json = new JsonObject();
            json.addProperty("uuid", uuid.toString());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/refresh"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("REFRESH: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
