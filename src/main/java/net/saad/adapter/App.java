package net.saad.adapter;

import net.saad.adapter.adapters.HdmiVgaAdapter;
import net.saad.adapter.adapters.HdmiVgaInheritanceAdapter;
import net.saad.adapter.computer.Computer;
import net.saad.adapter.computer.Screen;
import net.saad.adapter.computer.SuperVideoProjector;
import net.saad.adapter.computer.TV;
import net.saad.adapter.computer.VideoProjector;

/**
 * Client entry point for the Adapter pattern demo: a computer that speaks
 * only VGA, plus adapters that let HDMI devices (a TV, a hybrid projector)
 * be plugged into it — shown in both composition and inheritance flavors.
 */
public class App {

    public static void main(String[] args) {
        Computer computer = new Computer();

        computer.setVga(new Screen());
        computer.print("Hello");

        computer.setVga(new VideoProjector());
        computer.print("Hello");

        HdmiVgaAdapter adapter = new HdmiVgaAdapter();
        adapter.setHdmi(new TV());
        computer.setVga(adapter);
        computer.print("Good evening TV");

        computer.setVga(new SuperVideoProjector());   // implements VGA natively
        computer.print("Hello");

        HdmiVgaAdapter adapter2 = new HdmiVgaAdapter();
        adapter2.setHdmi(new SuperVideoProjector());
        computer.setVga(adapter2);
        computer.print("Good evening via HDMI");

        computer.setVga(new HdmiVgaInheritanceAdapter());
        computer.print("Inheritance test");
    }
}
