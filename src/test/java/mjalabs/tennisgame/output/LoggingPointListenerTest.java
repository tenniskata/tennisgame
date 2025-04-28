package mjalabs.tennisgame.output;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import java.util.Optional;
import mjalabs.tennisgame.game.Game;
import mjalabs.tennisgame.game.Player;
import mjalabs.tennisgame.game.state.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LoggingPointListenerTest {

  @Mock
  Player player1;
  @Mock
  Player player2;
  @Mock
  Game game;

  LoggingPointListener loggingPointListener = new LoggingPointListener();

  ListAppender<ILoggingEvent> listAppender;

  @BeforeEach
  void setUp() {
    listAppender = new ListAppender<>();
    listAppender.start();
    Logger logger = (Logger) LoggerFactory.getLogger(LoggingPointListener.class);
    logger.addAppender(listAppender);
    when(player1.getId()).thenReturn("A");
    when(player2.getId()).thenReturn("B");
    when(player1.toString()).thenReturn("Player A");
    when(player2.toString()).thenReturn("Player B");

    when(game.getPlayerA()).thenReturn(player1);
    when(game.getPlayerB()).thenReturn(player2);
  }

  @Test
  void shouldLogWinnerWhenGameIsOver() {
    when(game.isGameOver()).thenReturn(true);
    when(game.getWinner()).thenReturn(Optional.of(player1));

    loggingPointListener.onPoint(game);

    String log = getLog();
    assertThat(log).isEqualTo("Winner: Player A");
  }

  @Test
  void shouldLogDeuce() {
    when(game.isGameOver()).thenReturn(false);
    when(game.arePlayersDeuce()).thenReturn(true);

    loggingPointListener.onPoint(game);

    String log = getLog();
    assertThat(log).isEqualTo("Deuce");
  }

  @Test
  void shouldLogAdvantage() {
    when(game.isGameOver()).thenReturn(false);
    when(game.arePlayersDeuce()).thenReturn(false);
    when(game.getAdvantaged()).thenReturn(Optional.of(player1));

    loggingPointListener.onPoint(game);

    String log = getLog();
    assertThat(log).isEqualTo("Advantage: Player A");
  }

  @Test
  void shouldLogScore() {
    when(game.isGameOver()).thenReturn(false);
    when(game.arePlayersDeuce()).thenReturn(false);
    when(game.getAdvantaged()).thenReturn(Optional.empty());
    when(player1.getScore()).thenReturn(Score.FORTY);
    when(player2.getScore()).thenReturn(Score.THIRTY);

    loggingPointListener.onPoint(game);

    String log = getLog();
    assertThat(log).isEqualTo("Player A : 40 / Player B : 30");
  }

  private String getLog() {
    return listAppender.list.stream().findFirst().map(ILoggingEvent::getFormattedMessage).orElseThrow(() -> new RuntimeException("No log found"));
  }

}