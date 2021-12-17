package bowling.domain.frame;

import bowling.domain.bowl.FinalBowl;
import bowling.domain.bowl.FirstBowl;
import bowling.domain.pin.Pin;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;

public class Frames {

    private static final int INDEX_UNIT = 1;

    public final List<Frame> frames;

    public Frames(Frame firstFrame) {
        this(new ArrayList<>(singletonList(firstFrame)));
    }

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        Frame firstFrame = Frame.firstOf(new FirstBowl());
        return new Frames(firstFrame);
    }

    /**
     * @return 더 투구할 수 있는지
     */
    public boolean pitch(Pin pin) {
        Frame currentFrame = currentFrame();
        if (currentFrame.pitch(pin)) {
            return true;
        }
        if (frames.size() == Frame.MAX_FRAME_NUMBER) {
            return false;
        }
        frames.add(createNextFrame(currentFrame));
        return true;
    }

    private Frame currentFrame() {
        return frames.get(frames.size() - INDEX_UNIT);
    }

    private Frame createNextFrame(Frame currentFrame) {
        if (frames.size() < Frame.MAX_FRAME_NUMBER - INDEX_UNIT) {
            return currentFrame.nextOf(new FirstBowl());
        }
        return currentFrame.nextOf(new FinalBowl());
    }

    public int numberOfFrame() {
        return frames.size();
    }

    public List<Frame> frames() {
        return unmodifiableList(frames);
    }
}
