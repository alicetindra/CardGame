import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2003.Kortspill.PlayingCard;
import org.junit.jupiter.api.Test;

public class PlayingCardTest {

  @Test
  public void testCreatePlayingCard() {
    PlayingCard card1 = new PlayingCard('H', 1);  // Ace of Hearts
    PlayingCard card2 = new PlayingCard('S', 13); // King of spades
    PlayingCard card3 = new PlayingCard('D', 7);  // 7 of diamonds

    assertEquals("H1", card1.getAsString());
    assertEquals("S13", card2.getAsString());
    assertEquals("D7", card3.getAsString());
  }

  @Test
  public void testGetSuit() {
    PlayingCard card1 = new PlayingCard('H', 1);

    assertEquals('H', card1.suit());
  }

  @Test
  public void testGetFace() {
    PlayingCard card1 = new PlayingCard('H', 1);

    assertEquals(1, card1.face());
  }

}
