import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2003.Kortspill.Hand;
import edu.ntnu.idatt2003.Kortspill.PlayingCard;
import org.junit.jupiter.api.Test;

public class HandTest {
  private Hand hand;

  @Test
  public void testAddCard() {
    hand = new Hand();
    PlayingCard card1 = new PlayingCard('H', 1);
    PlayingCard card2 = new PlayingCard('S', 7);

    hand.addCard(card1);
    hand.addCard(card2);

    assertEquals(2, hand.getCards().size());
    assertTrue(hand.getCards().contains(card1));
    assertTrue(hand.getCards().contains(card2));

  }

  @Test
  void testGetSum() {
    hand = new Hand();
    hand.addCard(new PlayingCard('H', 10));
    hand.addCard(new PlayingCard('S', 12));
    hand.addCard(new PlayingCard('D', 5));

    assertEquals(27, hand.getSum());
  }

  @Test
  void testCheckQueenSpades() {
    hand = new Hand();
    hand.addCard(new PlayingCard('H', 10));
    hand.addCard(new PlayingCard('S', 12));

    assertTrue(hand.checkQueenSpades());
  }

  @Test
  void testCardHearts() {
    hand = new Hand();
    hand.addCard(new PlayingCard('H', 10));
    hand.addCard(new PlayingCard('H', 4));
    hand.addCard(new PlayingCard('S', 12));

    assertEquals("H10, H4, ", hand.cardHearts());
  }

  @Test
  void testFlush() {
    hand = new Hand();
    hand.addCard(new PlayingCard('H', 2));
    hand.addCard(new PlayingCard('H', 3));
    hand.addCard(new PlayingCard('H', 4));
    hand.addCard(new PlayingCard('H', 5));
    hand.addCard(new PlayingCard('H', 6));

    assertTrue(hand.flush());
  }

  //-------------------NEGATIVE TESTS---------------------------
  @Test
  void testFlushNegative() {
    Hand hand = new Hand();
    hand.addCard(new PlayingCard('H', 2));
    hand.addCard(new PlayingCard('S', 3));
    hand.addCard(new PlayingCard('H', 4));
    hand.addCard(new PlayingCard('H', 5));
    hand.addCard(new PlayingCard('D', 6));

    assertFalse(hand.flush());
  }
}
