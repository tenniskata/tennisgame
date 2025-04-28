package mjalabs.tennisgame.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import mjalabs.tennisgame.exception.TransitionException;
import mjalabs.tennisgame.game.state.Fifteen;
import mjalabs.tennisgame.game.state.Love;
import mjalabs.tennisgame.game.state.Score;
import mjalabs.tennisgame.game.state.ScoreState;
import org.junit.jupiter.api.Test;

class LoveTest {

  @Test
  void pointWon_whenStateIsLove_shouldGoToFifteen() {
    ScoreState state = new Love();

    ScoreState result = state.pointWon();

    assertThat(result).isInstanceOf(Fifteen.class);
  }

  @Test
  void toDeuce_whenStateIsLove_shouldThrowException() {
    ScoreState state = new Love();

    assertThatThrownBy(state::toDeuce)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot go to deuce from current state : LOVE");
  }

  @Test
  void winGame_whenStateIsLove_shouldThrowException() {
    ScoreState state = new Love();

    assertThatThrownBy(state::winGame)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot win game from current state : LOVE");
  }

  @Test
  void advantage_whenStateIsLove_shouldThrowException() {
    ScoreState state = new Love();

    assertThatThrownBy(state::advantage)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot go to advantage from current state : LOVE");
  }

  @Test
  void getScore_whenStateIsLove_shouldReturnLove() {
    ScoreState state = new Love();

    Score score = state.getScore();

    assertThat(score).isEqualTo(Score.LOVE);
  }
}