package mjalabs.tennisgame;

import java.util.Map;
import mjalabs.tennisgame.exception.InputException;
import mjalabs.tennisgame.game.Game;
import mjalabs.tennisgame.game.Player;
import mjalabs.tennisgame.game.PointListener;

public class GameEngine {

  private static final String FIRST_PLAYER_ID = "A";
  private static final String SECOND_PLAYER_ID = "B";
  private final PointListener pointListener;

  GameEngine(PointListener pointListener) {
    this.pointListener = pointListener;
  }

  void play(String sequence) {
    validate(sequence);
    Map<String, Player> players = Map.of(
        FIRST_PLAYER_ID, Player.create(FIRST_PLAYER_ID),
        SECOND_PLAYER_ID, Player.create(SECOND_PLAYER_ID)
    );
    Game game = new Game(players.get(FIRST_PLAYER_ID), players.get(SECOND_PLAYER_ID));

    int index = 0;
    while (index < sequence.length() && !game.isGameOver()) {
      Player player = players.get(String.valueOf(sequence.charAt(index)));
      game.pointTo(player);
      pointListener.onPoint(game);
      index++;
    }
  }

  private void validate(String sequence) {
    if (sequence == null || sequence.isEmpty()) {
      throw new InputException("Sequence cannot be null or empty");
    }
    if (!sequence.matches("[AB]+")) {
      throw new InputException("Invalid sequence: " + sequence);
    }
  }

}