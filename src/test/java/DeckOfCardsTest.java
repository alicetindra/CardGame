import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2003.Kortspill.DeckOfCards;
import edu.ntnu.idatt2003.Kortspill.Hand;
import org.junit.jupiter.api.Test;

public class DeckOfCardsTest {
  private DeckOfCards deck;

  @Test
  public void testCreateDeckOfCards() {
    deck = new DeckOfCards();

    assertEquals(52, deck.getCards().size());
  }

  @Test
  public void testDealHand() {
    deck = new DeckOfCards();
    Hand hand = deck.dealHand(5);

    assertEquals(5, hand.getCards().size());
  }

}
