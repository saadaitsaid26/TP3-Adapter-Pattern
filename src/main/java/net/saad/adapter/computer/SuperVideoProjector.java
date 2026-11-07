package net.saad.adapter.computer;

import java.nio.charset.StandardCharsets;

/**
 * A hybrid device implementing both contracts, so it works with the client
 * directly (VGA) and can also serve as the HDMI side of an adapter.
 */
public class SuperVideoProjector implements VGA, HDMI {

    @Override
    public void print(String message) {
        System.out.println("Super video projector (VGA) : " + message);
    }

    @Override
    public void view(byte[] data) {
        System.out.println("Super video projector (HDMI) : " + new String(data, StandardCharsets.UTF_8));
    }
}
