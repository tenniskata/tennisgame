package mjalabs.tennisgame.game.state;

public class Thirty extends ScoreState {

  @Override
  public ScoreState pointWon() {
    return new Forty();
  }

  @Override
  public ScoreState toDeuce() {
    return new Deuce();
  }

  @Override
  public Score getScore() {
    return Score.THIRTY;
  }
}
