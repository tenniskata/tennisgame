package mjalabs.tennisgame.game.state;

public class Advantage extends ScoreState {

  @Override
  public ScoreState toDeuce() {
    return new Deuce();
  }

  @Override
  public ScoreState pointWon() {
    return new Won();
  }

  @Override
  public ScoreState winGame() {
    return new Won();
  }

  @Override
  public Score getScore() {
    return Score.ADVANTAGE;
  }
}
