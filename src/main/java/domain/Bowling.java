package domain;

import domain.frame.Frame;
import domain.frame.result.FrameResults;

/**
 * Created by hspark on 22/11/2018.
 */
public class Bowling {
	private Frame frame = Frame.first();

	public void bowl(Pin pin) {
		Frame lastFrame = frame.getLastFrame();
		lastFrame.pitch(pin);
	}

	public boolean hasNext() {
		return frame.getLastFrame().isLeftFrame();
	}

	public int getNextFrameNumber() {
		return frame.getLastFrame().getFrameNumber();
	}

	public BowlingScoreBoard getBowlingScoreBoard() {
		FrameResults frameResults = frame.getFrameResults();
		FrameScores frameScores = frame.getScores();
		return new BowlingScoreBoard(frameResults, frameScores);
	}
}
