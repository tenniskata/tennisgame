package mjalabs.tennisgame.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GameTest {

  @Mock
  Player playerA;
  @Mock
  Player playerB;
  Game game;

  @BeforeEach
  void setUp() {
    when(playerB.isWinner()).thenReturn(false);
    when(playerA.isWinner()).thenReturn(false);
    game = new Game(playerA, playerB);
  }

  @Test
  void newGame_shouldNotBeOver() {
    boolean isOver = game.isGameOver();

    assertThat(isOver).isFalse();
  }

  @Test
  void pointToA_firstPointToPlayerAZeroToPlayerB_pointToPlayerA() {
    when(playerA.isUnderForty()).thenReturn(true);
    when(playerB.isForty()).thenReturn(false);

    game.pointTo(playerA);

    verify(playerA).winPoint();
    verifyNoMoreInteractions(ignoreStubs(playerA, playerB));
  }

  @Test
  void pointToA_playerIsThirtyAndPlayerBIsForty_Deuce() {
    when(playerA.isUnderForty()).thenReturn(true);
    when(playerA.isThirty()).thenReturn(true);
    when(playerB.isForty()).thenReturn(true);

    game.pointTo(playerA);

    verify(playerA).toDeuce();
    verify(playerB).toDeuce();
    verifyNoMoreInteractions(ignoreStubs(playerA, playerB));
  }

  @Test
  void pointToA_playerAIsNotUnderFortyAndPlayerBIsUnderForty_playerAWinsGame() {
    when(playerA.isUnderForty()).thenReturn(false);
    when(playerB.isUnderForty()).thenReturn(true);

    game.pointTo(playerA);

    verify(playerA).winGame();
    verifyNoMoreInteractions(ignoreStubs(playerA, playerB));
  }

  @Test
  void pointToA_deuce_playerAShouldHaveAdvantage() {
    prepareDeuce();

    game.pointTo(playerA);

    verify(playerA).advantage();
    verifyNoMoreInteractions(ignoreStubs(playerA, playerB));
  }

  @Test
  void pointToA_playerAHasAdvantage_playerAWinsGame() {
    prepareAHasAdvantage();

    game.pointTo(playerA);

    verify(playerA).winGame();
    verifyNoMoreInteractions(ignoreStubs(playerA, playerB));
  }

  @Test
  void pointToA_playerBHasAdvantage_playerBShouldGoBackToDeuce() {
    prepareBHasAdvantage();

    game.pointTo(playerA);

    verify(playerB).toDeuce();
    verifyNoMoreInteractions(ignoreStubs(playerA, playerB));
  }

  @Test
  void arePlayersDeuce_playersAreDeuce_shouldReturnTrue() {
    when(playerA.isDeuce()).thenReturn(true);
    when(playerB.isDeuce()).thenReturn(true);

    boolean arePlayersDeuce = game.arePlayersDeuce();

    assertThat(arePlayersDeuce).isTrue();
  }

  @ParameterizedTest
  @MethodSource("provideDeuceTestCases")
  @MockitoSettings(strictness = Strictness.LENIENT)
  void arePlayersDeuce_oneOrBothAreNot_returnFalse(boolean isPlayerADeuce, boolean isPlayerBDeuce) {
    when(playerA.isDeuce()).thenReturn(isPlayerADeuce);
    when(playerB.isDeuce()).thenReturn(isPlayerBDeuce);

    boolean arePlayersDeuce = game.arePlayersDeuce();

    assertThat(arePlayersDeuce).isFalse();
  }

  @Test
  void getAdvantaged_onlyPlayerAHasAdvantage_returnAdvantaged() {
    when(playerA.hasAdvantage()).thenReturn(true);

    Optional<Player> player = game.getAdvantaged();

    assertThat(player)
        .isPresent()
        .get()
        .isEqualTo(playerA);
  }

  @Test
  void getAdvantaged_onlyPlayerBHasAdvantage_returnAdvantaged() {
    when(playerA.hasAdvantage()).thenReturn(false);
    when(playerB.hasAdvantage()).thenReturn(true);

    Optional<Player> player = game.getAdvantaged();

    assertThat(player)
        .isPresent()
        .get()
        .isEqualTo(playerB);
  }

  @Test
  void getAdvantaged_noneOfThemHasAdvantage_returnEmpty() {
    when(playerA.hasAdvantage()).thenReturn(false);
    when(playerB.hasAdvantage()).thenReturn(false);

    Optional<Player> player = game.getAdvantaged();

    assertThat(player).isEmpty();
  }

  private void prepareDeuce() {
    when(playerA.isUnderForty()).thenReturn(false);
    when(playerB.isUnderForty()).thenReturn(false);
    when(playerA.isDeuce()).thenReturn(true);
    when(playerB.isDeuce()).thenReturn(true);
  }

  private void prepareAHasAdvantage() {
    when(playerA.hasAdvantage()).thenReturn(true);
    when(playerA.isDeuce()).thenReturn(false);
    when(playerA.isUnderForty()).thenReturn(false);
    when(playerB.isUnderForty()).thenReturn(false);
  }

  private void prepareBHasAdvantage() {
    when(playerB.hasAdvantage()).thenReturn(true);
    when(playerA.hasAdvantage()).thenReturn(false);
    when(playerA.isDeuce()).thenReturn(false);
    when(playerA.isUnderForty()).thenReturn(false);
    when(playerB.isUnderForty()).thenReturn(false);
  }

  public static Stream<Arguments> provideDeuceTestCases() {
    return Stream.of(
        Arguments.of(false, false),
        Arguments.of(true, false),
        Arguments.of(false, true));
  }
}