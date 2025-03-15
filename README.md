# CS361_P2
# Project 2: Non-deterministic Finite Automata

* Author: Flynn Hoare & Nick Bortz
* Class: CS361 Section 2
* Semester: Spring 2025

## Overview

This program is a Non-deterministic Finite Automata (NFA). The NFA
contains a set of states that can transition between each other over
an alphabet. The purpose is to determine if a string is accepted by
the language that the NFA defines.

## Reflection
### Flynn
This project went smoothly much like the last. There were a lot of things I was
able to bring over from P1, but there were some major differences as well. This
time around I think we structured our classes a bit better, adding more to the NFAState
class than we had in the DFAState class in the previous project. Overall, I didn't run
into any problems and we were able to get it passing the tests fairly easily. I think
for the future it would be much smarter to start the project much earlier, as getting
caught up in midterms caused me to procrastinate this project way too much. 

### Nick
The project went very smoothly this time around. The only thing that took some
time to figure out for me was the BFS for the accept method. I had
to write out what I expected to happen as I debugged the method to
make sure it was working correctly. The rest of the project went very well
and took very little time to get running. Flynn and I, again, split up the
work halfway between the two of us through meetings and kept in touch
to make sure we were on time. If I could go back in time, I would 
start the project a little earlier, so I wouldn't be so overwhelmed with 
everything else going on this time of year. This was an interesting
way of learning to convert a different program to something that has
similar uses to work differently and, sometimes, more simply.

## Compiling and Using

To compile, execute the following command in the main project directory:
```
javac -cp .:/usr/share/java/junit.jar ./test/nfa/NFATest.java
```
Run the compiled class with the command:
```
java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar
org.junit.runner.JUnitCore test.nfa.NFATest
```
The program requires no user input.

## Sources used

https://docs.oracle.com/javase/8/docs/api/java/util/Set.html
https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html