package mjalabs.tennisgame.game.state;

public class Forty extends ScoreState {

  @Override
  public ScoreState pointWon() {
    return new Advantage();
  }

  @Override
  public ScoreState toDeuce() {
    return new Deuce();
  }

  @Override
  public ScoreState winGame() {
    return new Won();
  }

  @Override
  public Score getScore() {
    return Score.FORTY;
  }
}
