
# MyShelfie


This is a digital adaptation of the physical game "MyShelfie"
It is developed by Filiberto Canino, Riccardo Zonno, Giuseppe Preziosi, and Giorgio Sidari, who are students of Politecnico di Milano in the "Software Engineering" course. Together, they form the development group "GC40."

## Features

- Implemented in Java, following the game rules of the physical board game "MyShelfie" and the game requirements specified by the course professors.
- Supports both RMI and socket connections, allowing players to choose their preferred mode of connection.
- Offers a choice between a text-based interface (fully functional) and a graphical interface (work in progress) for gameplay.

## Game Requirements

Our software adheres to the complete rules of the MyShelfie board game, including the following:

- Text-based user interface (TUI) for seamless gameplay.
- Remote Method Invocation (RMI) for networked play.
- Socket connection for networked play.
- Graphical User Interface (GUI) for an enhanced gaming experience.

## Advanced Features

In addition to the core game rules, our software includes two advanced features:

1. Multiple Games: The software supports the ability to run multiple games concurrently, allowing multiple groups of players to enjoy MyShelfie simultaneously.
2. Disconnection Resilience: The software is designed to handle disconnections gracefully, ensuring that the game state is maintained even if a player loses network connectivity temporarily.

## Getting Started

1. Open a terminal and navigate to the "softeng_gc40_jar" folder.
2. Execute the following command to start the game: `java -jar softeng_gc40.jar`.
3. The program will launch and prompt you to choose whether you want to run it as a client or a server.
4. If you choose to run as a client, enter the target IP address to connect to the server.
5. If you choose to run as a server, enter your own IP address.
6. To play a game, you need at least three instances of the program, with at least one instance acting as the server. These instances can be running on different computers connected over a network.

## Usage

- Upon launching the game, you will be prompted to select the desired mode of connection: RMI or socket.
- For gameplay, you can choose between the text-based interface (fully implemented) or the graphical interface (partially implemented).
- Follow the on-screen instructions and prompts to play the game.
- Enjoy MyShelfie with your friends, either locally or over a network!
