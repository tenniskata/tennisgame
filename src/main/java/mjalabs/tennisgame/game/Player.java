package mjalabs.tennisgame.game;

import static mjalabs.tennisgame.game.state.Score.ADVANTAGE;
import static mjalabs.tennisgame.game.state.Score.DEUCE;
import static mjalabs.tennisgame.game.state.Score.FIFTEEN;
import static mjalabs.tennisgame.game.state.Score.FORTY;
import static mjalabs.tennisgame.game.state.Score.LOVE;
import static mjalabs.tennisgame.game.state.Score.THIRTY;
import static mjalabs.tennisgame.game.state.Score.WON;

import java.util.Objects;
import java.util.Set;
import mjalabs.tennisgame.game.state.Love;
import mjalabs.tennisgame.game.state.Score;
import mjalabs.tennisgame.game.state.ScoreState;

public class Player {

  private static final Set<Score> UNDER_FORTY = Set.of(LOVE, FIFTEEN, THIRTY);

  private final String id;
  private ScoreState scoreState;

  public static Player create(String id) {
    return new Player(id, new Love());
  }

  Player(String id, ScoreState scoreState) {
    this.id = id;
    this.scoreState = scoreState;
  }

  public void winGame() {
    scoreState = scoreState.winGame();
  }

  public void winPoint() {
    scoreState = scoreState.pointWon();
  }

  public boolean isUnderForty() {
    return UNDER_FORTY.contains(getScore());
  }

  public boolean isThirty() {
    return THIRTY.equals(getScore());
  }

  public void advantage() {
    scoreState = scoreState.advantage();
  }

  public void toDeuce() {
    scoreState = scoreState.toDeuce();
  }

  public boolean isForty() {
    return FORTY == getScore();
  }

  public boolean hasAdvantage() {
    return getScore() == ADVANTAGE;
  }

  public boolean isWinner() {
    return getScore() == WON;
  }

  public boolean isDeuce() {
    return DEUCE == scoreState.getScore();
  }

  public Score getScore() {
    return scoreState.getScore();
  }

  public ScoreState getScoreState() {
    return scoreState;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Player player)) {
      return false;
    }
    return Objects.equals(id, player.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return String.format("Player %s", id);
  }
}