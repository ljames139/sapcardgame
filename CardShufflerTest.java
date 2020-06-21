package CardGameSap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class CardShufflerTest {

    /*
    Checks to see if the deck array initialised with 40 elements returns the
    value of 40 when the deckSize() method is called upon it
    and compared to the expected result testInt of 40.
     */

    int[] deck = new int[40];
    int testInt = 40;

    CardShuffler testCS = new CardShuffler(deck);

    @org.junit.jupiter.api.Test
    void deckSize() {

        assertEquals(testInt, testCS.deckSize());
    }

    /*
    This test uses two arrays containing the same integers and then compares them both
    after the CardShuffler class' shuffleCard() method has been called on the
    deckTest.

    Arrays.equals() should return false as deckTest and controlDeck should
    be different, which passes the assertFalse() test.
     */

    @Test
    void schuffleCard() {

        int[] deckTest = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] controlDeck = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        testCS.shuffleCard(deckTest);

        assertFalse((Arrays.equals(deckTest, controlDeck)));

    }

    /*
    Checks that the contents of the discarded list are
    copied to the empty stack and that after the list
    is copied that the list is cleared returning
    a size() of 0 and that the stack returns 2.
     */

    @Test
    void refillPlayerStack() {
        LinkedList<Integer> discarded = new LinkedList<>();
        discarded.add(1);
        discarded.add(2);

        Stack<Integer> playerHand = new Stack<>();

        testCS.refillPlayerStack(playerHand, discarded);
        assertEquals(0, discarded.size());
        assertEquals(2, playerHand.size());
    }
}