package mjalabs.tennisgame.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import mjalabs.tennisgame.exception.TransitionException;
import mjalabs.tennisgame.game.state.Score;
import mjalabs.tennisgame.game.state.ScoreState;
import mjalabs.tennisgame.game.state.Won;
import org.junit.jupiter.api.Test;

class WonTest {

  /**
   * just like the other tests for Won state
   */

  @Test
  void getScore_whenStateIsWon_shouldReturnWon() {
    ScoreState state = new Won();

    Score score = state.getScore();

    assertThat(score).isEqualTo(Score.WON);
  }

  @Test
  void pointWon_whenStateIsWon_shouldThrowException() {
    ScoreState state = new Won();

    assertThatThrownBy(state::pointWon)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot win point from current state : WON");
  }

  @Test
  void toDeuce_whenStateIsWon_shouldThrowException() {
    ScoreState state = new Won();

    assertThatThrownBy(state::toDeuce)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot go to deuce from current state : WON");
  }

  @Test
  void winGame_whenStateIsWon_shouldThrowException() {
    ScoreState state = new Won();

    assertThatThrownBy(state::winGame)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot win game from current state : WON");
  }

  @Test
  void advantage_whenStateIsWon_shouldThrowException() {
    ScoreState state = new Won();

    assertThatThrownBy(state::advantage)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot go to advantage from current state : WON");
  }
}