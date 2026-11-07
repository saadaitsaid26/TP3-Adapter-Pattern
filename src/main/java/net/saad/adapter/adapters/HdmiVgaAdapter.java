package net.saad.adapter.adapters;

import net.saad.adapter.computer.HDMI;
import net.saad.adapter.computer.VGA;

import java.nio.charset.StandardCharsets;

/**
 * Adapter by composition: implements {@link VGA} (what the client knows) and
 * delegates to {@link HDMI} (the foreign interface). Because it depends on an
 * interface, it can adapt any HDMI device — not just one concrete class.
 */
public class HdmiVgaAdapter implements VGA {

    private HDMI hdmi;

    public void setHdmi(HDMI hdmi) {
        this.hdmi = hdmi;
    }

    @Override
    public void print(String message) {
        System.out.println("--- VGA -> HDMI adapter ---");
        hdmi.view(message.getBytes(StandardCharsets.UTF_8));
    }
}
