package net.saad.adapter.computer;

import java.nio.charset.StandardCharsets;

/**
 * The incompatible device: a TV that only speaks HDMI, so it cannot be used
 * with the client directly — that is where the adapter comes in.
 */
public class TV implements HDMI {

    @Override
    public void view(byte[] data) {
        String message = new String(data, StandardCharsets.UTF_8);
        System.out.println("TV : " + message);
    }
}
