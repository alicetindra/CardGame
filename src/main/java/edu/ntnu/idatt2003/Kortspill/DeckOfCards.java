package edu.ntnu.idatt2003.Kortspill;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class DeckOfCards {
  private final List<PlayingCard> cards;
  private final char[] suit = { 'S', 'H', 'D', 'C'}; //Spades, Hearts, Diamonds, Clubs

  public DeckOfCards() {
    cards = new ArrayList<>();
    for(char CurrentSuit : suit){
      for(int face=1; face<=13; face++){
        cards.add(new PlayingCard(CurrentSuit, face));
      }
    }
  }

  public List<PlayingCard> getCards() {
    return cards;
  }

  public Hand dealHand(int n) {
    if(n<1 || n> cards.size()){
      throw new IllegalArgumentException("Number of cards must be between 1 and the size of the deck");
    }
    Hand hand = new Hand();
    Random random = new Random();

    for(int i=0; i<n; i++){
      int randIndex = random.nextInt(cards.size());
      hand.addCard(cards.remove(randIndex));
    }
    return hand;
  }

  public void resetDeck(){
    cards.clear();
    for(char CurrentSuit : suit){
      for(int face=1; face<=13; face++){
        cards.add(new PlayingCard(CurrentSuit, face));
      }
    }
  }

}
