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

  public int getSum(){
    int sum = 0;
    for(PlayingCard card : this.cards){
      sum += card.face();
    }
    return sum;
  }

  public boolean checkQueenSpades(){
    boolean QueenSpades = false;
    for(PlayingCard card : this.cards){
      if(card.face() == 12 && card.suit() == 'S'){
        QueenSpades = true;
        break;
      }
    }
    return QueenSpades;
  }

  public String cardHearts(){
    StringBuilder hearts = new StringBuilder();
    boolean isHearts = false;

    for(PlayingCard card : this.cards){
      if(card.suit() == 'H'){
        hearts.append(card.getAsString()).append(", ");
        isHearts = true;
      }
    }
    if(!isHearts){
      return "No hearts found";
    }
    return hearts.toString();
  }

  public boolean flush(){
    int hearts = 0, spades = 0, clubs = 0, diamonds = 0;

    for(PlayingCard card : this.cards){
      switch(card.suit()){
        case 'H' -> hearts++;
        case 'S' -> spades++;
        case 'C' -> clubs++;
        case 'D' -> diamonds++;
      }
    }
    return hearts >=5 || spades >=5 || clubs >=5 || diamonds >=5;
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
