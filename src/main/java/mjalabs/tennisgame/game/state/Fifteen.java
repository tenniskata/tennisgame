package mjalabs.tennisgame.game.state;

public class Fifteen extends ScoreState {

  @Override
  public ScoreState pointWon() {
    return new Thirty();
  }


  @Override
  public Score getScore() {
    return Score.FIFTEEN;
  }
}
