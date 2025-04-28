package mjalabs.tennisgame.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import mjalabs.tennisgame.exception.TransitionException;
import mjalabs.tennisgame.game.state.Deuce;
import mjalabs.tennisgame.game.state.Forty;
import mjalabs.tennisgame.game.state.Score;
import mjalabs.tennisgame.game.state.ScoreState;
import mjalabs.tennisgame.game.state.Thirty;
import org.junit.jupiter.api.Test;


class ThirtyTest {

  /**
   * just like AdvantageTest, for Thirty
   */

  @Test
  void pointWon_whenStateIsThirty_shouldGoToForty() {
    ScoreState state = new Thirty();

    ScoreState result = state.pointWon();

    assertThat(result).isInstanceOf(Forty.class);
  }

  @Test
  void toDeuce_whenStateIsThirty_shouldGoToDeuce() {
    ScoreState state = new Thirty();

    ScoreState result = state.toDeuce();

    assertThat(result).isInstanceOf(Deuce.class);
  }

  @Test
  void winGame_whenStateIsThirty_shouldThrowException() {
    ScoreState state = new Thirty();

    assertThatThrownBy(state::winGame)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot win game from current state : THIRTY");
  }

  @Test
  void advantage_whenStateIsThirty_shouldThrowException() {
    ScoreState state = new Thirty();

    assertThatThrownBy(state::advantage)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot go to advantage from current state : THIRTY");
  }

  @Test
  void getScore_whenStateIsThirty_shouldReturnThirty() {
    ScoreState state = new Thirty();

    Score score = state.getScore();

    assertEquals(Score.THIRTY, score);
  }


}