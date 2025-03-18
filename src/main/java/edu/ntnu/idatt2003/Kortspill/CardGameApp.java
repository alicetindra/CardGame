package edu.ntnu.idatt2003.Kortspill;

import java.util.List;

public class CardGameApp {
  public void start() {


    DeckOfCards deck = new DeckOfCards();
    System.out.println(deck.getCards().size());

    Hand hand = deck.dealHand(5);
    System.out.println(hand);
  }

}
