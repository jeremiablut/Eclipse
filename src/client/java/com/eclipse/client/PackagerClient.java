package com.eclipse.client;

import com.google.gson.*;
import net.minecraft.client.Minecraft;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

import static com.eclipse.client.EclipseClient.eclipsePlayerPrefixes;

public class PackagerClient {

    private static final String BASE_URL = "http://157.180.94.191";

    public static java.util.Set<UUID> onlineEclipsePlayers = new java.util.HashSet<>();

    public static JsonObject lastResponse = new JsonObject();

    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    private static final Gson gson = new Gson();

    public static void getUUID(List<UUID> uuids) {
        if (uuids.isEmpty()) return;

        JsonObject json = new JsonObject();
        json.add("uuids", gson.toJsonTree(uuids.stream().map(UUID::toString).toList()));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/get"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(body -> {
                    lastResponse = JsonParser.parseString(body).getAsJsonObject();

                    onlineEclipsePlayers.clear();

                    Minecraft mc = Minecraft.getInstance();
                    if (mc.player != null) {
                        UUID selfUuid = mc.player.getUUID();
                        onlineEclipsePlayers.add(selfUuid);
                        eclipsePlayerPrefixes.putIfAbsent(selfUuid, "§4[§fEP§4] ");
                    }

                    if (lastResponse.has("clients") && lastResponse.get("clients").isJsonArray()) {
                        JsonArray clientsArray = lastResponse.getAsJsonArray("clients");

                        for (JsonElement element : clientsArray) {
                            JsonObject clientObj = element.getAsJsonObject();
                            if (clientObj.has("uuid") && clientObj.get("online").getAsBoolean()) {
                                UUID onlineUuid = UUID.fromString(clientObj.get("uuid").getAsString());
                                onlineEclipsePlayers.add(onlineUuid);

                                if (clientObj.has("prefix")) {
                                    eclipsePlayerPrefixes.put(onlineUuid, clientObj.get("prefix").getAsString());
                                }
                            }
                        }
                    }
                })
                .exceptionally(ex -> {
                    System.err.println("API error: " + ex.getMessage());
                    return null;
                });
    }

    public static void refresh(UUID uuid) {
        JsonObject json = new JsonObject();
        json.addProperty("uuid", uuid.toString());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/refresh"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(body -> {
                    JsonObject obj = JsonParser.parseString(body).getAsJsonObject();

                    String prefix = obj.has("prefix") ? obj.get("prefix").getAsString() : "";

                    Minecraft.getInstance().execute(() -> {
                        onlineEclipsePlayers.add(uuid);

                        if (!prefix.isEmpty()) {
                            eclipsePlayerPrefixes.put(uuid, prefix);
                        }
                    });
                })
                .exceptionally(ex -> {
                    System.err.println("Refresh Error: " + ex.getMessage());
                    return null;
                });
    }
}
