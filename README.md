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
As a whole, the project went very smoothly. I think the thing that caused
the most trouble was figuring out what structure to use in order to contain
the transition table for each DFA. The first thing we tried was using Hashmap
of type <String, String> and using a comma as a delimiter in order to separate
the start state and the symbol we were transitioning on. Thinking this could
cause problems if states were created using names with the delimeter we were
using, we ended up settling on a nested Hashmap instead. Besides that, we didn't
run into any problems that weren't quickly resolved.

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


## Sources used