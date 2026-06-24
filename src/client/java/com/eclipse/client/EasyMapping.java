package com.eclipse.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;

public class EasyMapping {
    private KeyMapping self;

    public EasyMapping(String called, int key) {
        self = KeyMappingHelper.registerKeyMapping(
                new KeyMapping(
                        called,
                        InputConstants.Type.KEYSYM,
                        key,
                        EclipseClient.CATEGORY
                )
        );
    }

    public KeyMapping getSelf() {return self;}

    public boolean consumeClick() {return self.consumeClick();}

    public boolean isDown() {return self.isDown();}
}
