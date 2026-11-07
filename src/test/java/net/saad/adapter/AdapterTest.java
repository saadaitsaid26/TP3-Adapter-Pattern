package net.saad.adapter;

import net.saad.adapter.adapters.HdmiVgaAdapter;
import net.saad.adapter.adapters.HdmiVgaInheritanceAdapter;
import net.saad.adapter.computer.Computer;
import net.saad.adapter.computer.HDMI;
import net.saad.adapter.computer.Screen;
import net.saad.adapter.computer.SuperVideoProjector;
import net.saad.adapter.computer.VGA;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdapterTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void computerDelegatesToScreen() {
        Computer computer = new Computer();
        computer.setVga(new Screen());
        computer.print("Hello");
        assertTrue(out.toString().contains("Screen : Hello"));
    }

    @Test
    void compositionAdapterConvertsAndDelegates() {
        HdmiVgaAdapter adapter = new HdmiVgaAdapter();
        final byte[][] received = new byte[1][];
        adapter.setHdmi(data -> received[0] = data);

        adapter.print("Hello");

        assertArrayEquals("Hello".getBytes(StandardCharsets.UTF_8), received[0]);
        assertTrue(out.toString().contains("VGA -> HDMI adapter"));
    }

    @Test
    void inheritanceAdapterDelegatesToTv() {
        HdmiVgaInheritanceAdapter adapter = new HdmiVgaInheritanceAdapter();
        adapter.print("Test");
        assertTrue(out.toString().contains("TV : Test"));
        assertTrue(out.toString().contains("inheritance"));
    }

    @Test
    void superVideoProjectorImplementsBothInterfaces() {
        SuperVideoProjector superVideoProjector = new SuperVideoProjector();
        assertTrue(superVideoProjector instanceof VGA);
        assertTrue(superVideoProjector instanceof HDMI);
    }
}
