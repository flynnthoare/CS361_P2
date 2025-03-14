package fa.nfa;

import java.util.LinkedHashSet;
import java.util.Set;

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

    // Nick
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

    // Flynn
    @Override
    public boolean isFinal(String name) {
        return false;
    }

    @Override
    public boolean isStart(String name) {
        return false;
    }

    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        return null;
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        return null;
    }

    @Override
    public int maxCopies(String s) {
        return 0;
    }

    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        return false;
    }

    @Override
    public boolean isDFA() {
        return false;
    }
}
