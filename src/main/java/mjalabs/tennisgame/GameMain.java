package mjalabs.tennisgame;

import mjalabs.tennisgame.exception.BusinessException;
import mjalabs.tennisgame.game.PointListener;
import mjalabs.tennisgame.output.LoggingPointListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameMain {

  private static final Logger logger = LoggerFactory.getLogger(GameMain.class);

  public static void main(String[] args) {
    if (args.length == 0) {
      logger.error("Missing input sequence argument. Please provide a valid sequence of points.");
      return;
    }
    String sequence = args[0];
    PointListener pointListener = new LoggingPointListener();
    GameEngine engine = new GameEngine(pointListener);
    try {
      engine.play(sequence);
    } catch (BusinessException e) {
      logger.error(e.getMessage());
    }
  }
}