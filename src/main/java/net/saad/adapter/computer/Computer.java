package net.saad.adapter.computer;

/**
 * The client: an office computer that only knows VGA. Whatever is plugged in
 * through {@code setVga(...)} must speak VGA — adapters make that possible
 * for HDMI-only devices.
 */
public class Computer {

    private VGA vga;

    public void setVga(VGA vga) {
        this.vga = vga;
    }

    public void print(String message) {
        System.out.println("======= Computer =======");
        vga.print(message);
        System.out.println("=========================");
    }
}
