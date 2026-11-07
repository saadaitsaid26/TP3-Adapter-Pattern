package net.saad.adapter.computer;

/**
 * A standard VGA device: a monitor plugged in directly.
 */
public class Screen implements VGA {

    @Override
    public void print(String message) {
        System.out.println("Screen : " + message);
    }
}
