package mjalabs.tennisgame.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import mjalabs.tennisgame.exception.TransitionException;
import mjalabs.tennisgame.game.state.Advantage;
import mjalabs.tennisgame.game.state.Deuce;
import mjalabs.tennisgame.game.state.Score;
import mjalabs.tennisgame.game.state.ScoreState;
import mjalabs.tennisgame.game.state.Won;
import org.junit.jupiter.api.Test;

class AdvantageTest {

  @Test
  void pointWon_whenStateIsAdvantage_shouldWinGame() {
    ScoreState state = new Advantage();

    ScoreState result = state.pointWon();

    assertThat(result).isInstanceOf(Won.class);
  }


  @Test
  void toDeuce_whenStateIsAdvantage_shouldBackToDeuce() {
    ScoreState state = new Advantage();

    ScoreState result = state.toDeuce();

    assertThat(result).isInstanceOf(Deuce.class);
  }


  @Test
  void winGame_whenStateIsAdvantage_shouldWinGame() {
    ScoreState state = new Advantage();

    ScoreState result = state.winGame();

    assertThat(result).isInstanceOf(Won.class);
  }

  @Test
  void advantage_whenStateIsAdvantage_shouldThrowException() {
    ScoreState state = new Advantage();

    assertThatThrownBy(state::advantage)
        .isInstanceOf(TransitionException.class)
        .hasMessage("Cannot go to advantage from current state : ADVANTAGE");
  }

  @Test
  void getScore_whenStateIsAdvantage_shouldReturnAdvantage() {
    ScoreState state = new Advantage();

    Score score = state.getScore();

    assertThat(score).isEqualTo(Score.ADVANTAGE);
  }

}