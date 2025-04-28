package mjalabs.tennisgame;

import static org.assertj.core.api.Assertions.assertThat;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import java.util.List;
import java.util.stream.Stream;
import mjalabs.tennisgame.game.PointListener;
import mjalabs.tennisgame.output.LoggingPointListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.LoggerFactory;

class GameEngineIT {

  private GameEngine gameEngine;
  private ListAppender<ILoggingEvent> listAppender;

  @BeforeEach
  void setUp() {
    PointListener pointListener = new LoggingPointListener();
    gameEngine = new GameEngine(pointListener);
    listAppender = new ListAppender<>();
    listAppender.start();
    Logger logger = (Logger) LoggerFactory.getLogger(LoggingPointListener.class);
    logger.addAppender(listAppender);
  }

  @ParameterizedTest
  @MethodSource("provideAllCases")
  void ultimateTest(String input, String loggedWinner) {
    gameEngine.play(input);

    var logs = getLoggedMessages();
    String lastLog = logs.get(logs.size() - 1);
    assertThat(lastLog).isEqualTo(loggedWinner);
  }

  public static Stream<Arguments> provideAllCases() {
    return Stream.of(
        Arguments.of("AAAA", "Winner: Player A"),
        Arguments.of("AAABA", "Winner: Player A"),
        Arguments.of("AABAA", "Winner: Player A"),
        Arguments.of("AABBAA", "Winner: Player A"),
        Arguments.of("AABBAAB", "Winner: Player A"), // game finishes before the last point which is not considered
        Arguments.of("AABBABABAA", "Winner: Player A"), // deuce then advantage then deuce etc..
        Arguments.of("AABBABAA", "Winner: Player A"),
        Arguments.of("AABBABBB", "Winner: Player B")
    );
  }


  private List<String> getLoggedMessages() {
    return listAppender.list.stream().map(ILoggingEvent::getFormattedMessage).toList();
  }
}