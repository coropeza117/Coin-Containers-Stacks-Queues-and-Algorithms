# Coin-Containers-Stacks-Queues-and-Algorithms

-Accomplishments

• Coin Containers

The project now includes three container classes: DefaultContainer, Stack, and Pipeline, each satisfying the Container interface (ADT). These classes are capable of managing coins, ensuring that each coin can only be in one container at a time. The containers use singly-linked lists to efficiently store coins, and special care has been taken to maintain data structure integrity.

• Stack and Queue Implementations

    Stack: The Stack class represents a stack of coins where larger coins cannot be placed on top of smaller ones. It ensures that coins are added and removed in a last-in-first-out (LIFO) manner.
    Pipeline: The Pipeline class represents a first-in-first-out (FIFO) queue of coins, providing functionality to add coins at the tail and remove coins from the head.

• Coin Movement Algorithm

The project includes an algorithm for moving stacks of coins between containers. This algorithm, based on the "Towers of Hanoi" concept, efficiently transfers stacks of coins while maintaining their order. It demonstrates the use of recursive techniques and ADT methods for coin manipulation.

• Testing and Validation

The project features an extensive suite of tests, including unit tests, functional tests, and efficiency tests. These tests ensure the correctness and performance of the implemented container classes and coin movement algorithm. The use of spies and decorators enables thorough testing of internal behaviors.

• Future Development

While the project has met its initial objectives, there is potential for further development and expansion. Future enhancements and areas for exploration include:

    Extending the functionality of coin containers to handle more complex scenarios.
    Implementing additional algorithms and operations related to coin manipulation and management.
    Enhancing the user interface for interacting with coin containers and performing coin movements.
