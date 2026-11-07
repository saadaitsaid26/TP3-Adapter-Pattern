# Adapter Pattern — Java Demo

A small Java console demo of the **Adapter** design pattern (GoF structural):
a computer that only speaks VGA gets HDMI devices plugged into it through an
**adapter**, exactly like a physical VGA↔HDMI dongle between an old tower and
a modern screen.

## How it works

| Pattern role            | Class                                        | Description                                       |
|-------------------------|----------------------------------------------|---------------------------------------------------|
| Target (known interface)| `net.saad.adapter.computer.VGA`              | `print(String)` — what the client expects         |
| Adaptee (foreign iface) | `net.saad.adapter.computer.HDMI`             | `view(byte[])` — what the device actually speaks  |
| Client                  | `net.saad.adapter.computer.Computer`         | Holds a `VGA`, delegates to it                    |
| Standard devices        | `computer.Screen`, `computer.VideoProjector` | VGA-native, plugged in directly                   |
| Incompatible device     | `computer.TV`                                | HDMI-only: unusable with the client as-is         |
| Hybrid device           | `computer.SuperVideoProjector`               | Implements both interfaces                        |
| Adapter (composition)   | `net.saad.adapter.adapters.HdmiVgaAdapter`   | Implements `VGA`, delegates to any `HDMI` device  |
| Adapter (inheritance)   | `adapters.HdmiVgaInheritanceAdapter`         | Extends `TV` and implements `VGA`                 |

The adapter is a two-sided plug:

```java
public class HdmiVgaAdapter implements VGA {          // client side: print(String)
    private HDMI hdmi;                                // device side: view(byte[])

    @Override
    public void print(String message) {
        hdmi.view(message.getBytes(StandardCharsets.UTF_8));  // converts and delegates
    }
}
```

## Composition vs inheritance

Both variants answer identically to the client — `Computer` only ever sees a
`VGA`:

- **Composition** (the privileged choice): the adapter holds an `HDMI`
  *interface*, so it can adapt *any* HDMI device — a TV today, a hybrid
  projector or any future device tomorrow (swap it with `setHdmi(...)`).
- **Inheritance**: the adapter extends `TV` directly. One fewer class to hold,
  but it is welded to a single concrete device.

The swap is invisible to the client: `computer.setVga(...)` accepts both
standard devices and adapters.

## Project layout

```
src/main/java/net/saad/adapter/
├── App.java                     # client: plugs devices into the computer
├── adapters/
│   ├── HdmiVgaAdapter.java              # by composition (idiomatic)
│   └── HdmiVgaInheritanceAdapter.java   # by inheritance
└── computer/
    ├── VGA.java                 # target interface (print)
    ├── HDMI.java                # adaptee interface (view)
    ├── Computer.java            # client
    ├── Screen.java              # standard device
    ├── VideoProjector.java      # standard device
    ├── TV.java                  # HDMI-only device
    └── SuperVideoProjector.java # speaks both languages

src/test/java/net/saad/adapter/
└── AdapterTest.java             # JUnit 5: conversion + both adapter flavors
```

## Requirements

- Java 21+
- Maven 3.9+

## Build & run

```bash
mvn clean compile
java -cp target/classes net.saad.adapter.App
```

Run the test suite with:

```bash
mvn test
```

## Sample output

```
======= Computer =======
Screen : Hello
=========================
======= Computer =======
Video projector : Hello
=========================
======= Computer =======
--- VGA -> HDMI adapter ---
TV : Good evening TV
=========================
======= Computer =======
Super video projector (VGA) : Hello
=========================
======= Computer =======
--- VGA -> HDMI adapter ---
Super video projector (HDMI) : Good evening via HDMI
=========================
======= Computer =======
--- VGA -> HDMI adapter (inheritance) ---
TV : Inheritance test
=========================
```

## Extending

Add any HDMI device (`OledDisplay.java` implementing `HDMI`) and wrap it with
`HdmiVgaAdapter` — or add any VGA device and plug it in directly. The client
never changes: it keeps calling `print(...)` on whatever it was handed, Open/
Closed style.
