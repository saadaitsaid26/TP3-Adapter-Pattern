package net.saad.adapter.computer;

/**
 * The foreign interface to adapt — the <em>adaptee</em>. Modern display
 * devices speak HDMI (`view(byte[])`), not VGA.
 */
public interface HDMI {

    void view(byte[] data);
}
