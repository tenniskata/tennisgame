package mjalabs.tennisgame.game.state;

public class Deuce extends ScoreState {

  @Override
  public ScoreState advantage() {
    return new Advantage();
  }

  @Override
  public ScoreState pointWon() {
    return new Advantage();
  }

  @Override
  public Score getScore() {
    return Score.DEUCE;
  }
}
