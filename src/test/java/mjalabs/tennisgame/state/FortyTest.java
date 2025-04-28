package mjalabs.tennisgame.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import mjalabs.tennisgame.exception.TransitionException;
import mjalabs.tennisgame.game.state.Advantage;
import mjalabs.tennisgame.game.state.Deuce;
import mjalabs.tennisgame.game.state.Forty;
import mjalabs.tennisgame.game.state.Score;
import mjalabs.tennisgame.game.state.ScoreState;
import mjalabs.tennisgame.game.state.Won;
import org.junit.jupiter.api.Test;


class FortyTest {

  @Test
  void pointWon_whenStateIsForty_shouldGoToAdvantage() {
    ScoreState state = new Forty();

    ScoreState result = state.pointWon();

    assertThat(result).isInstanceOf(Advantage.class);
  }

  @Test
  void toDeuce_whenStateIsForty_shouldGoToDeuce() {
    ScoreState state = new Forty();

    ScoreState result = state.toDeuce();

    assertThat(result).isInstanceOf(Deuce.class);
  }

  @Test
  void winGame_whenStateIsForty_shouldWinGame() {
    ScoreState state = new Forty();

    ScoreState result = state.winGame();

    assertThat(result).isInstanceOf(Won.class);
  }

  @Test
  void advantage_whenStateIsForty_shouldThrowException() {
    ScoreState state = new Forty();

    assertThatThrownBy(state::advantage)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot go to advantage from current state : FORTY");
  }

  @Test
  void getScore_whenStateIsForty_shouldReturnForty() {
    ScoreState state = new Forty();

    Score score = state.getScore();

    assertThat(score).isEqualTo(Score.FORTY);
  }
}