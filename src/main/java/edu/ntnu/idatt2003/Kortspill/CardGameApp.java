package edu.ntnu.idatt2003.Kortspill;

public class CardGameApp {
  public void start() {
    // Skapa några PlayingCard-objekt
    PlayingCard card1 = new PlayingCard('H', 1);  // Hjärtess
    PlayingCard card2 = new PlayingCard('S', 13); // Spaderkung
    PlayingCard card3 = new PlayingCard('D', 7);  // Ruter 7

    // Skriv ut korten
    System.out.println("Här är dina kort:");
    System.out.println(card1.getAsString());
    System.out.println(card2.getAsString());
    System.out.println(card3.getAsString());
  }

}
