package fa.nfa;

import fa.State;

public class NFA implements NFAInterface {
    // Nick
    @java.lang.Override
    public boolean addState(String name) {
        return false;
    }

    @java.lang.Override
    public boolean setFinal(String name) {
        return false;
    }

    @java.lang.Override
    public boolean setStart(String name) {
        return false;
    }

    @java.lang.Override
    public void addSigma(char symbol) {

    }

    @java.lang.Override
    public boolean accepts(String s) {
        return false;
    }

    @java.lang.Override
    public Set<Character> getSigma() {
        return null;
    }

    @java.lang.Override
    public State getState(String name) {
        return null;
    }

    // Flynn
    @java.lang.Override
    public boolean isFinal(String name) {
        return false;
    }

    @java.lang.Override
    public boolean isStart(String name) {
        return false;
    }

    @java.lang.Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        return null;
    }

    @java.lang.Override
    public Set<NFAState> eClosure(NFAState s) {
        return null;
    }

    @java.lang.Override
    public int maxCopies(String s) {
        return 0;
    }

    @java.lang.Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        return false;
    }

    @java.lang.Override
    public boolean isDFA() {
        return false;
    }
}
