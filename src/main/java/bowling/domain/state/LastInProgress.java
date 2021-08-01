package bowling.domain.state;

import bowling.domain.pin.DownedPins;

import java.util.List;

public class LastInProgress extends State {
    private static final int MAX_ROLL_COUNT = 3;

    private int tryCount;
    private final ComplexState complexState;

    public LastInProgress() {
        complexState = ComplexState.init();
    }

    public static LastInProgress init() {
        return new LastInProgress();
    }

    @Override
    protected State nextState(DownedPins downedPins) {
        ++tryCount;

        complexState.nextState(downedPins);

        return updateTotalState();
    }

    private State updateTotalState() {
        if (isEnd()) {
            return LastEnd.from(complexState);
        }

        complexState.giveExtraChange();
        return this;
    }

    @Override
    public boolean isEnd() {
        return tryCount == MAX_ROLL_COUNT || complexState.isEnd();
    }

    @Override
    public List<State> getState() {
        return complexState.getState();
    }

    @Override
    public List<Integer> getDownedPins() {
        return complexState.getDownedPins();
    }
}
