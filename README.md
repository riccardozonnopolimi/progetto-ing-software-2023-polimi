# MyShelfie

Digital, networked version of the board game **MyShelfie**, developed as the final team project of the **Software Engineering** course (Ingegneria del Software, 2023) at **Politecnico di Milano** by group **GC40**: Filiberto Canino, Riccardo Zonno, Giuseppe Preziosi and Giorgio Sidari.

> This repository is a public copy, published with the team's consent, of the original team repository [filibertocanino/ing-sw-2023-canino-zonno-sidari-preziosi](https://github.com/filibertocanino/ing-sw-2023-canino-zonno-sidari-preziosi). The full commit history and all branches are preserved, so every commit keeps its original author.

## Features

- Complete implementation of the MyShelfie game rules, following the requirements set by the course professors.
- **Client-server architecture** supporting both **RMI** and **socket** connections: each player chooses the connection type.
- **Text-based interface (TUI)**, fully functional, and a **graphical interface (GUI)** built with Swing, partially implemented.
- **Multiple games:** the server can run several games at the same time.
- **Disconnection resilience:** the game state is kept even if a player temporarily loses the connection.

## Tech stack

| Area | Technologies |
|---|---|
| Language and build | Java 19, Maven |
| Networking | Java RMI, TCP sockets, Java object serialization |
| Data | Game configuration (personal goal cards) loaded from JSON with Gson |
| User interfaces | Command line (TUI), Swing (GUI) |
| Design | MVC architecture, Observer pattern, UML |
| Testing | JUnit 4 (14 test classes) |

## Project structure

```
src/main/java/it/polimi/ingsw23/gc40/
├── server/      model (game logic, goal cards), controller, connection handling
├── client/      views (TUI and GUI) and client-side connection
└── netModel/    objects exchanged over the network and observer/observable utilities
src/test/java/   unit tests
deliverables/    UML diagrams, Javadoc, test coverage report, peer reviews, executable jar
```

## Getting started

1. Make sure Java 19 or later is installed.
2. Open a terminal in the `deliverables/softeng_gc40_jar` folder.
3. Start the program with `java -jar softeng-gc40.jar`.
4. Choose whether to run it as a **server** (enter your own IP address) or as a **client** (enter the server's IP address).
5. A game needs at least three instances of the program, one of them acting as the server; they can run on different computers on the same network.
6. Each client chooses the connection type (RMI or socket) and the interface (text or graphical), then follows the on-screen instructions.

## Documentation

- **UML diagrams** (high-level design, connection and match creation, sequence of moves): `deliverables/UML`
- **Javadoc**: `deliverables/javadoc/index.html`
- **Test coverage report**: `deliverables/test coverage.png`

## My contribution (Riccardo Zonno)

- Implementation and unit testing of the **common goal cards** and of parts of the game model, including scoring.
- **Networking layer:** RMI connection, socket communication and serialization of the data sent from server to clients.
- **Observer/observable** mechanism linking model, server and clients.
- Work on the **command-line interface**, the first version of the **GUI** and the end-of-game logic.
