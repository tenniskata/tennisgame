package mjalabs.tennisgame.game.state;

import mjalabs.tennisgame.exception.TransitionException;

public abstract class ScoreState {

  public ScoreState pointWon() {
    throw new TransitionException("Cannot win point from current state : " + getScore());
  }

  public ScoreState winGame() {
    throw new TransitionException("Cannot win game from current state : " + getScore());
  }

  public ScoreState toDeuce() {
    throw new TransitionException("Cannot go to deuce from current state : " + getScore());
  }

  public ScoreState advantage() {
    throw new TransitionException("Cannot go to advantage from current state : " + getScore());
  }

  public abstract Score getScore();

}
