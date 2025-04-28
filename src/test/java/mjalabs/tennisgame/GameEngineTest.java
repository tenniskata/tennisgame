package mjalabs.tennisgame;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import mjalabs.tennisgame.exception.InputException;
import mjalabs.tennisgame.game.Player;
import mjalabs.tennisgame.game.PointListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GameEngineTest {

  @Mock
  PointListener pointListener;
  @Captor
  ArgumentCaptor<Player> playerCaptor;
  GameEngine gameEngine;

  @BeforeEach
  void setUp() {
    gameEngine = new GameEngine(pointListener);
  }

  @Test
  void shouldThrowExceptionWhenSequenceIsNull() {
    String sequence = null;

    assertThatThrownBy(() -> gameEngine.play(sequence))
        .isInstanceOf(InputException.class)
        .hasMessage("Sequence cannot be null or empty");
  }

  @Test
  void shouldThrowExceptionWhenSequenceIsEmpty() {
    String sequence = "";

    assertThatThrownBy(() -> gameEngine.play(sequence))
        .isInstanceOf(InputException.class)
        .hasMessage("Sequence cannot be null or empty");
  }

  @Test
  void shouldThrowExceptionWhenSequenceHasInvalidCharacters() {
    String sequence = "ABZ";

    assertThatThrownBy(() -> gameEngine.play(sequence))
        .isInstanceOf(InputException.class)
        .hasMessage("Invalid sequence: ABZ");
  }
}