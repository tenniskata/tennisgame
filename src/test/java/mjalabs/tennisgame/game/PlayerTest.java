package mjalabs.tennisgame.game;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import mjalabs.tennisgame.game.state.Score;
import mjalabs.tennisgame.game.state.ScoreState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PlayerTest {

  static final String PLAYER_ID = "player1";

  Player player;
  @Mock
  ScoreState currentState;
  @Mock
  ScoreState nextState;

  @BeforeEach
  void setUp() {
    player = new Player(PLAYER_ID, currentState);
  }

  @Test
  void create_shouldReturnPlayerInLove() {
    Player otherPlayer = Player.create("other");

    assertThat(otherPlayer.getScoreState()).isInstanceOf(ScoreState.class);
    assertThat(otherPlayer).hasToString("Player other");
    assertThat(otherPlayer.getScoreState().getScore()).isEqualTo(Score.LOVE);
  }

  @Test
  void pointWon_shouldAddPointToState() {
    when(currentState.pointWon()).thenReturn(nextState);

    player.winPoint();

    verify(currentState).pointWon();
    assertThat(player.getScoreState()).isEqualTo(nextState);
  }

  @Test
  void advantage_shouldUpgradeStateToAdvantage() {
    when(currentState.advantage()).thenReturn(nextState);

    player.advantage();

    verify(currentState).advantage();
    assertThat(player.getScoreState()).isEqualTo(nextState);
  }

  @Test
  void toDeuce_shouldDowngradeStateToDeuce() {
    when(currentState.toDeuce()).thenReturn(nextState);

    player.toDeuce();

    verify(currentState).toDeuce();
    assertThat(player.getScoreState()).isEqualTo(nextState);
  }

  @Test
  void winGame_shouldUpgradeStateToWon() {
    when(currentState.winGame()).thenReturn(nextState);

    player.winGame();

    verify(currentState).winGame();
    assertThat(player.getScoreState()).isEqualTo(nextState);
  }

  @Test
  void getScore_shouldReturnScoreFromState() {
    when(currentState.getScore()).thenReturn(Score.LOVE);

    Score score = player.getScore();

    verify(currentState).getScore();
    assertThat(score).isEqualTo(Score.LOVE);
  }

  @ParameterizedTest
  @EnumSource(value = Score.class, names = {"LOVE", "FIFTEEN", "THIRTY"})
  void isUnderForty_shouldReturnTrueIfScoreIsUnderForty(Score score) {
    when(currentState.getScore()).thenReturn(score);

    boolean result = player.isUnderForty();

    assertThat(result).isTrue();
  }

  @ParameterizedTest
  @EnumSource(value = Score.class, names = {"FORTY", "ADVANTAGE", "DEUCE", "WON"})
  void isUnderForty_scoreIsNotUnderForty_shouldReturnFalse(Score score) {
    when(currentState.getScore()).thenReturn(score);

    boolean result = player.isUnderForty();

    assertThat(result).isFalse();
  }

  @Test
  void isForty_scoreIsForty_shouldReturnTrue() {
    when(currentState.getScore()).thenReturn(Score.FORTY);

    boolean result = player.isForty();

    assertThat(result).isTrue();
  }

  @Test
  void isForty_scoreIsNotForty_shouldReturnFalse() {
    when(currentState.getScore()).thenReturn(Score.FIFTEEN);
    when(currentState.getScore()).thenReturn(Score.LOVE);

    boolean result = player.isForty();

    assertThat(result).isFalse();
  }

  @Test
  void hasAdvantage_scoreIsAdvantage_shouldReturnTrue() {
    when(currentState.getScore()).thenReturn(Score.ADVANTAGE);

    boolean result = player.hasAdvantage();

    assertThat(result).isTrue();
  }

  @Test
  void hasAdvantage_scoreIsNotAdvantage_shouldReturnFalse() {
    when(currentState.getScore()).thenReturn(Score.FORTY);
    when(currentState.getScore()).thenReturn(Score.LOVE);

    boolean result = player.hasAdvantage();

    assertThat(result).isFalse();
  }

  @Test
  void isDeuce_scoreIsDeuce_shouldReturnTrue() {
    when(currentState.getScore()).thenReturn(Score.DEUCE);

    boolean result = player.isDeuce();

    assertThat(result).isTrue();
  }

  @Test
  void isDeuce_scoreIsNotDeuce_shouldReturnFalse() {
    when(currentState.getScore()).thenReturn(Score.FORTY);
    when(currentState.getScore()).thenReturn(Score.LOVE);

    boolean result = player.isDeuce();

    assertThat(result).isFalse();
  }

  @Test
  void equals_sameId_shouldReturnTrue() {
    player = new Player(PLAYER_ID, currentState);
    Player player2 = new Player(PLAYER_ID, currentState);

    boolean result = player.equals(player2);

    assertThat(result).isTrue();
  }

  @Test
  void equals_differentId_shouldReturnFalse() {
    player = new Player(PLAYER_ID, currentState);
    Player player2 = new Player("player2", currentState);

    boolean result = player.equals(player2);

    assertThat(result).isFalse();
  }

  @Test
  void equals_differentObject_shouldReturnFalse() {
    player = new Player(PLAYER_ID, currentState);
    String differentObject = "not a player";

    boolean result = player.equals(differentObject);

    assertThat(result).isFalse();
  }

  @Test
  void hashCode_sameId_shouldReturnSameHashCode() {
    player = new Player(PLAYER_ID, currentState);
    Player player2 = new Player(PLAYER_ID, currentState);

    int hashCode1 = player.hashCode();
    int hashCode2 = player2.hashCode();

    assertThat(hashCode1).isEqualTo(hashCode2);
  }

  @Test
  void toString_shouldReturnPlayerId() {
    String result = player.toString();

    assertThat(result).isEqualTo("Player player1");
  }
}