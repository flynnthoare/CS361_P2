package fa.nfa;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Represents a non-deterministic finite automaton (NFA).
 * Supports state management, transitions (including epsilon),
 * input processing, epsilon closure computation, and DFA checks.
 *
 * @author Nick Bortz & Flynn Hoare
 * @since 2025-03-14
 */
public class NFA implements NFAInterface {
    // the states store isFinal, so contains F
    private final LinkedHashSet<NFAState> states;
    // sigma includes the total alphabet
    private final LinkedHashSet<Character> sigma;

    private NFAState start;

    /**
     * Constructor for a new NFA.
     */
    public NFA() {
        states = new LinkedHashSet<>();
        sigma = new LinkedHashSet<>();

        start = null;
    }

    /**
     * Adds a new state to the NFA.
     * @param name The name of the state to add.
     * @return true if the state was added successfully, false if it already exists.
     */
    @Override
    public boolean addState(String name) {
        // Cannot have the name of an existing state
        if (getState(name) != null)
            return false;

        // Create a new state with this name
        NFAState state = new NFAState(name);
        states.add(state);
        return true;
    }

    /**
     * Marks a state as a final (accepting) state.
     * @param name The name of the state to set as final.
     * @return true if the state was successfully set as final, false if the state does not exist.
     */
    @Override
    public boolean setFinal(String name) {
        NFAState state = getState(name);
        // Cannot set a nonexistent state to final
        if (state == null)
            return false;

        // Set the state to final
        state.makeFinal();
        return true;
    }

    /**
     * Sets the start state of the NFA.
     * @param name The name of the state to set as the start state.
     * @return true if successfully set, false if the state does not exist.
     */
    @Override
    public boolean setStart(String name) {
        NFAState state = getState(name);
        // Cannot set a nonexistent state to start
        if (state == null)
            return false;

        // Ensure no other states are marked as isStart
        for (NFAState other : states)
            other.setStart(false);

        // Set the start state to this state
        state.setStart(true);
        start = state;
        return true;
    }

    /**
     * Adds a character to the NFA's alphabet.
     * @param symbol The character to add to the alphabet.
     */
    @Override
    public void addSigma(char symbol) {
        // Add the symbol to sigma
        sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
        Set<NFAState> currentStates = new HashSet<>();
        currentStates.add(start);
        currentStates.addAll(eClosure(start));

        int charIdx = 0;

        // While s is not empty
        while (charIdx < s.length()) {
            char currentChar = s.charAt(charIdx);
            Set<NFAState> newStates = new HashSet<>();

            // Loop through all current states
            for (NFAState state : currentStates) {
                // Get all possible states to transition to and add them to the newStates set
                Set<NFAState> possibleStates = getToState(state, currentChar);
                newStates.addAll(possibleStates);

                // Loop through all of these states and add the corresponding epsilon transition states
                for (NFAState possibleState : possibleStates) {
                    newStates.addAll(eClosure(possibleState));
                }
            }

            // If newStates is empty, there is nowhere to go, so the string is not accepted
            if (newStates.isEmpty())
                return false;

            currentStates = newStates;
            charIdx++;
        }

        // If any current state is final, this string is accepted
        for (NFAState state : currentStates) {
            if (state.isFinal())
                return true;
        }

        // The current state is not a final state, so the string is not accepted
        return false;
    }

    /**
     * Retrieves the NFA's alphabet
     * @return A set containing all characters in the NFA's alphabet.
     */
    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    /**
     * Retrieves a state by its name.
     * @param name The name of the state to retrieve.
     * @return The NFAState object corresponding to the given name, or null if not found.
     */
    @Override
    public NFAState getState(String name) {
        for (NFAState state : states) {
            if (state.getName().equals(name))
                return state;
        }

        return null;
    }

    /**
	 * Determines if a state with a given name is final
	 * @param name the name of the state
	 * @return true if a state with that name exists and it is final
	 */
    @Override
    public boolean isFinal(String name) {
        NFAState state = getState(name);
        return state != null && state.isFinal();
    }

    /**
	 * Determines if a state with name is final
	 * @param name the name of the state
	 * @return true if a state with that name exists and it is the start state
	 */
    @Override
    public boolean isStart(String name) {
        return start != null && start.getName().equals(name);
    }

    /**
	 * Return delta entries
	 * @param from - the source state
	 * @param onSymb - the label of the transition
	 * @return a set of sink states
	 */
    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        if (from == null) return Collections.emptySet();  // Handle null case
        return from.toStates(onSymb);  // Return states
    }

    /**
	 * Traverses all epsilon transitions and determine
	 * what states can be reached from s through e
	 * @param s
	 * @return set of states that can be reached from s on epsilon trans.
	 */
    @Override
    public Set<NFAState> eClosure(NFAState s) {
        Set<NFAState> closure = new HashSet<>();
        Stack<NFAState> stack = new Stack<>();

        // Start from s and add it to the closure
        stack.push(s);
        closure.add(s);

        while (!stack.isEmpty()) {
            NFAState current = stack.pop();

            // Get all states reachable by epsilon 
            for (NFAState nextState : current.toStates('e')) {
                if (!closure.contains(nextState)) {  // Avoid revisiting states
                    closure.add(nextState);
                    stack.push(nextState);
                }   
            }
        }

        return closure;
    }

    /**
	 * Determines the maximum number of NFA copies
	 * created when processing string s
	 * @param s - the input string
	 * @return - the maximum number of NFA copies created.
	 */
    @Override
    public int maxCopies(String s) {
        Set<NFAState> currentStates = new HashSet<>();
        currentStates.add(start);
        currentStates.addAll(eClosure(start));

        int charIdx = 0;
        int maxNumCopies = currentStates.size();

        // While s is not empty
        while (charIdx < s.length()) {
            char currentChar = s.charAt(charIdx);
            Set<NFAState> newStates = new HashSet<>();

            // Loop through all current states
            for (NFAState state : currentStates) {
                // Get all possible states to transition to and add them to the newStates set
                Set<NFAState> possibleStates = getToState(state, currentChar);
                newStates.addAll(possibleStates);

                // Loop through all of these states and add the corresponding epsilon transition states
                for (NFAState possibleState : possibleStates) {
                    newStates.addAll(eClosure(possibleState));
                }
            }

            // If newStates is empty, there is nowhere to go, so the string is not accepted
            if (newStates.isEmpty())
                break;

            // Update the max number of copies with the number of active states
            maxNumCopies = Math.max(maxNumCopies, newStates.size());

            currentStates = newStates;
            charIdx++;
        }

        // The current state is not a final state, so the string is not accepted
        return maxNumCopies;
    }

    /**
	 * Adds the transition to the NFA's delta data structure
	 * @param fromState is the label of the state where the transition starts
	 * @param toState is the set of labels of the states where the transition ends
	 * @param onSymb is the symbol from the NFA's alphabet.
	 * @return true if successful and false if one of the states don't exist or the symbol in not in the alphabet
	 */
    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        NFAState from = getState(fromState);
        if (from == null) return false;  // check if state exists
        if (!sigma.contains(onSymb) && onSymb != 'e') return false; // check if symbol is in sigma

        for (String toStateName : toStates) {
            NFAState to = getState(toStateName);
            if (to == null) return false;  // check if state exists

            from.addTransition(onSymb, to);  // Add transition in NFAState
        }

        return true;
    }

    /**
	 * Determines if NFA is an instance of a DFA
	 * @return - true if NFA's transition function has DFA's properties.
	 */
    @Override
    public boolean isDFA() {
        for (NFAState state : states) {
            // Check for epsilon transitions
            if (!state.toStates('e').isEmpty()) {
                return false;  
            }
    
            // Check that each symbol has at most ONE transition
            for (char symbol : sigma) {
                if (state.toStates(symbol).size() > 1) {
                    return false;  
                }
            }
        }
    
        return true;  // No epsilon transitions, and all symbols have one transition at most
    
    }
}
