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

    private Map<Character, Set<NFAState>> transitions;

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

    // Add a transition to another state on a given symbol
    public void addTransition(char onSymb, NFAState toState) {
        transitions.putIfAbsent(onSymb, new HashSet<>());  // Ensure key exists
        transitions.get(onSymb).add(toState);
    }

    // Retrieve all states reachable from this state on a given symbol
    public Set<NFAState> getTransitions(char onSymb) {
        return transitions.getOrDefault(onSymb, Collections.emptySet());
    }


}
