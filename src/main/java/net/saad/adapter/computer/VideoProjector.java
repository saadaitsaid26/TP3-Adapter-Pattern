package net.saad.adapter.computer;

/**
 * A standard VGA device: a video projector plugged in directly.
 */
public class VideoProjector implements VGA {

    @Override
    public void print(String message) {
        System.out.println("Video projector : " + message);
    }
}
