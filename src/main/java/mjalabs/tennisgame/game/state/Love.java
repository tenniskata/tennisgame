package mjalabs.tennisgame.game.state;

import static mjalabs.tennisgame.game.state.Score.LOVE;

public class Love extends ScoreState {

  @Override
  public ScoreState pointWon() {

    return new Fifteen();
  }

  @Override
  public Score getScore() {
    return LOVE;
  }
}
