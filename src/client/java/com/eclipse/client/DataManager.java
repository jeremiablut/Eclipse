package com.eclipse.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.file.*;

public class DataManager {
    private static final Path PATH = Paths.get("eclipse/config/config.json");

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static final Data DATA = load();

    public static class Data {
        public int fpscolor = 0x00FFF;
    }

    private static Data load() {
        try {
            if (Files.exists(PATH)) {
                return GSON.fromJson(Files.readString(PATH), Data.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Data();
    }

    public static void save() {
        try {
            Files.createDirectories(PATH.getParent());
            Files.writeString(PATH, GSON.toJson(DATA));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
