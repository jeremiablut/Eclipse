package com.eclipse.client;

import net.minecraft.util.Util;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Wikijang {
    public static void openWikiPage(String argument) throws IOException, URISyntaxException {
        URI link = new URI("https://minecraft.wiki/w/" + argument);
        Util.getPlatform().openUri(link);
    }
}
