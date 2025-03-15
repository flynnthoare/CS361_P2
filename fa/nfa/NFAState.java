package fa.nfa;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fa.State;

public class NFAState extends State {
    private boolean isStart;
    private boolean isFinal;

    private final Map<Character, Set<NFAState>> transitions;

    public NFAState(String name) {
        super(name);
        isStart = false;
        isFinal = false;
        transitions = new HashMap<>();
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean value) {
        isStart = value;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void makeFinal() {
        isFinal = true;
    }

    /**
     * Adds a transition from this state to another state on a given input symbol.
     * If a transition on this symbol already exists, the new state is added to the set.
     *
     * @param onSymb   The input symbol that triggers the transition.
     * @param toState  The destination state for this transition.
     */
    public void addTransition(char onSymb, NFAState toState) {
        transitions.putIfAbsent(onSymb, new HashSet<>());  // Ensure key exists
        transitions.get(onSymb).add(toState);
    }

    /**
     * Retrieves the set of states that can be reached from this state on a given input symbol.
     * If no transitions exist for the given symbol, returns an empty set.
     *
     * @param onSymb  The input symbol for which transitions should be retrieved.
     * @return A set of NFAState objects representing the possible next states.
     */
    public Set<NFAState> toStates(char onSymb) {
        return transitions.getOrDefault(onSymb, Collections.emptySet());
    }


}
