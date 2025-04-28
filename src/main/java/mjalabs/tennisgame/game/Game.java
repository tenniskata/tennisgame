package mjalabs.tennisgame.game;

import java.util.Optional;
import java.util.stream.Stream;

public class Game {

  private final Player playerA;
  private final Player playerB;

  public Game(Player playerA, Player playerB) {
    this.playerA = playerA;
    this.playerB = playerB;
  }

  public Player getPlayerA() {
    return playerA;
  }

  public Player getPlayerB() {
    return playerB;
  }

  public void pointTo(Player player) {
    if (isGameOver()) {
      return;
    }

    Player opponent = opponent(player);
    if (player.isUnderForty()) {
      if (opponent.isForty() && player.isThirty()) {
        player.toDeuce();
        opponent.toDeuce();
      } else {
        player.winPoint();
      }
      return;
    }
    if (!player.isUnderForty() && opponent.isUnderForty()) {
      playerWinGame(player);
      return;
    }
    if (player.isDeuce() && opponent.isDeuce()) {
      player.advantage();
      return;
    }
    if (player.hasAdvantage()) {
      playerWinGame(player);
      return;
    }
    if (opponent.hasAdvantage()) {
      opponent.toDeuce();
    }
  }

  private void playerWinGame(Player player) {
    player.winGame();
  }

  private Player opponent(Player player) {
    return player.equals(playerA) ? playerB : playerA;
  }

  public boolean isGameOver() {
    return getWinner().isPresent();
  }

  public boolean arePlayersDeuce() {
    return playerA.isDeuce() && playerB.isDeuce();
  }

  public Optional<Player> getWinner() {
    return Stream.of(playerA, playerB)
        .filter(Player::isWinner)
        .findFirst();
  }

  public Optional<Player> getAdvantaged() {
    return Stream.of(playerA, playerB)
        .filter(Player::hasAdvantage)
        .findFirst();
  }
}