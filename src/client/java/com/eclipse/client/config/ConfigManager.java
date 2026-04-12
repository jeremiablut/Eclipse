package com.eclipse.client.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ConfigManager {
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("mymod.json");

    private static ModConfig config = new ModConfig();

    private ConfigManager() {}

    public static ModConfig getConfig() {
        return config;
    }

    public static void load() {
        if (Files.exists(CONFIG_PATH)) {
            try {
                String json = Files.readString(CONFIG_PATH);
                ModConfig loaded = GSON.fromJson(json, ModConfig.class);

                if (loaded != null) {
                    config = loaded;
                } else {
                    config = new ModConfig();
                }
            } catch (Exception e) {
                System.err.println("[mymod] Failed to load config, using defaults.");
                e.printStackTrace();
                config = new ModConfig();
            }
        } else {
            config = new ModConfig();
            save(); // create the file with defaults
        }
    }

    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            Files.writeString(CONFIG_PATH, GSON.toJson(config));
        } catch (IOException e) {
            System.err.println("[mymod] Failed to save config.");
            e.printStackTrace();
        }
    }
}