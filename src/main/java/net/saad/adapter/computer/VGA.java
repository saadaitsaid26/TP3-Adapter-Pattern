package net.saad.adapter.computer;

/**
 * The interface the client knows — the <em>target</em> of the Adapter pattern.
 * Every standard output device implements it, and every adapter transforms a
 * foreign device into a {@code VGA}.
 */
public interface VGA {

    void print(String message);
}
