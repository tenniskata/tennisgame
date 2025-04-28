package mjalabs.tennisgame.game.state;

public class Won extends ScoreState {

  @Override
  public Score getScore() {
    return Score.WON;
  }
}
