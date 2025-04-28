package mjalabs.tennisgame.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import mjalabs.tennisgame.exception.TransitionException;
import mjalabs.tennisgame.game.state.Fifteen;
import mjalabs.tennisgame.game.state.Score;
import mjalabs.tennisgame.game.state.ScoreState;
import mjalabs.tennisgame.game.state.Thirty;
import org.junit.jupiter.api.Test;

class FifteenTest {

  @Test
  void pointWon_whenStateIsFifteen_shouldGoToThirty() {
    ScoreState state = new Fifteen();

    ScoreState result = state.pointWon();

    assertThat(result).isInstanceOf(Thirty.class);
  }

  @Test
  void toDeuce_whenStateIsFifteen_shouldThrowException() {
    ScoreState state = new Fifteen();

    assertThatThrownBy(state::toDeuce)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot go to deuce from current state : FIFTEEN");
  }

  @Test
  void winGame_whenStateIsFifteen_shouldThrowException() {
    ScoreState state = new Fifteen();

    assertThatThrownBy(state::winGame)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot win game from current state : FIFTEEN");
  }

  @Test
  void advantage_whenStateIsFifteen_shouldThrowException() {
    ScoreState state = new Fifteen();

    assertThatThrownBy(state::advantage)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot go to advantage from current state : FIFTEEN");
  }

  @Test
  void getScore_whenStateIsFifteen_shouldReturnFifteen() {
    ScoreState state = new Fifteen();

    Score score = state.getScore();

    assertThat(score).isEqualTo(Score.FIFTEEN);
  }

}