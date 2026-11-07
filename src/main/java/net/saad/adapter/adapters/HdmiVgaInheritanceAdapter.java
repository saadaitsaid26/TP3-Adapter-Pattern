package net.saad.adapter.adapters;

import net.saad.adapter.computer.TV;
import net.saad.adapter.computer.VGA;

import java.nio.charset.StandardCharsets;

/**
 * Adapter by inheritance: extends {@link TV} instead of holding an HDMI
 * reference. Works the same way for the client, but is tightly coupled to one
 * concrete device rather than adapting any HDMI implementation.
 */
public class HdmiVgaInheritanceAdapter extends TV implements VGA {

    @Override
    public void print(String message) {
        System.out.println("--- VGA -> HDMI adapter (inheritance) ---");
        super.view(message.getBytes(StandardCharsets.UTF_8));
    }
}
