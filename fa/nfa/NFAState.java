package fa.nfa;

import fa.State;

public class NFAState extends State {
    private boolean isStart;
    private boolean isFinal;

    public NFAState(String name) {
        super(name);
        isStart = false;
        isFinal = false;
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
}
