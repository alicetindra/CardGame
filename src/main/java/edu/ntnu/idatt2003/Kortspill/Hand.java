package edu.ntnu.idatt2003.Kortspill;

import java.util.ArrayList;
import java.util.List;

public class Hand {
  private final List<PlayingCard> cards;

  public Hand() {
    this.cards = new ArrayList<>();
  }

  public void addCard(PlayingCard card) {
    this.cards.add(card);
  }

  public List<PlayingCard> getCards() {
    return new ArrayList<>(this.cards);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (PlayingCard card : cards) {
      sb.append(card.getAsString()).append(", ");
    }
    return sb.toString();
  }


}
