package mjalabs.tennisgame.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import mjalabs.tennisgame.exception.TransitionException;
import mjalabs.tennisgame.game.state.Advantage;
import mjalabs.tennisgame.game.state.Deuce;
import mjalabs.tennisgame.game.state.Score;
import mjalabs.tennisgame.game.state.ScoreState;
import org.junit.jupiter.api.Test;

class DeuceTest {

  @Test
  void pointWon_whenStateIsDeuce_shouldGoToAdvantage() {
    ScoreState state = new Deuce();

    ScoreState result = state.pointWon();

    assertThat(result).isInstanceOf(Advantage.class);
  }

  @Test
  void toDeuce_whenStateIsDeuce_shouldThrowException() {
    ScoreState state = new Deuce();

    assertThatThrownBy(state::toDeuce)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot go to deuce from current state : DEUCE");
  }

  @Test
  void winGame_whenStateIsDeuce_shouldThrowException() {
    ScoreState state = new Deuce();

    assertThatThrownBy(state::winGame)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot win game from current state : DEUCE");
  }

  @Test
  void advantage_whenStateIsDeuce8_shouldGoToAdvantage() {
    ScoreState state = new Deuce();

    ScoreState result = state.advantage();

    assertThat(result).isInstanceOf(Advantage.class);
  }

  @Test
  void getScore_whenStateIsDeuce_shouldReturnDeuce() {
    ScoreState state = new Deuce();

    Score score = state.getScore();

    assertThat(score).isEqualTo(Score.DEUCE);
  }
}