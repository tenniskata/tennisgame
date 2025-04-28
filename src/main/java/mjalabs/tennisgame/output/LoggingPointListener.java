package mjalabs.tennisgame.output;

import mjalabs.tennisgame.game.Game;
import mjalabs.tennisgame.game.PointListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingPointListener implements PointListener {

  private static final Logger logger = LoggerFactory.getLogger(LoggingPointListener.class);

  @Override
  public void onPoint(Game game) {
    if (game.isGameOver()) {
      game.getWinner().ifPresent(winner -> logger.info("Winner: {}", winner));
    } else if (game.arePlayersDeuce()) {
      logger.info("Deuce");
    } else if (game.getAdvantaged().isPresent()) {
      game.getAdvantaged().ifPresent(advantaged -> logger.info("Advantage: {}", advantaged));
    } else {
      logger.info("{} : {} / {} : {}", game.getPlayerA(), game.getPlayerA().getScore().value(), game.getPlayerB(),
          game.getPlayerB().getScore().value());
    }
  }
}
