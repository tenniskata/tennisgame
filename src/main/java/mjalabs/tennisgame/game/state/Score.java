package mjalabs.tennisgame.game.state;

public enum Score {
  LOVE("0"),
  FIFTEEN("15"),
  THIRTY("30"),
  FORTY("40"),
  DEUCE("DEUCE"),
  ADVANTAGE("ADVANTAGE"),
  WON("WON");

  private final String value;

  Score(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
}
