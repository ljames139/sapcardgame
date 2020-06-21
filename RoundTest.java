package CardGameSap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {



    //instance of round class
    Round rnd = new Round();

    /*
    tests the compareCards() method to ensure that it is capable of registering a win for either player
    when p1 or p2 has the greater hand and also a draw.
     */

    @Test
    void compareCards() {
        int playerOneTest;
        int playerTwoTest;
        int resultDraw = 0;
        int resultPlayerOneWin = 1;
        int resultPlayerTwoWin = -1;

        //player one wins round
        playerOneTest=10;
        playerTwoTest=9;
        assertEquals(resultPlayerOneWin, rnd.compareCards(playerOneTest, playerTwoTest));

        //player two wins round
        playerOneTest=9;
        playerTwoTest=10;
        assertEquals(resultPlayerTwoWin, rnd.compareCards(playerOneTest, playerTwoTest));

        //draw
        playerOneTest=7;
        playerTwoTest=7;
        assertEquals(resultDraw, rnd.compareCards(playerOneTest, playerTwoTest));


    }

    /*
    test each outcome for the checkGameWinner() method by testing ints related to stack and discarded
    pile size to ensure that player one and player two can win (if the other player
    has 0 cards in either of their piles) and that it returns 0 if neither player has won.
     */


    @Test
    void checkGameWinner() {
        int p1TestHand;
        int p1TestDiscarded;
        int p2TestHand;
        int p2TestDiscarded;
        int roundResultTest;


        //check player one win
        p1TestHand = 1;
        p1TestDiscarded = 1;
        p2TestHand = 0;
        p2TestDiscarded = 0;
        roundResultTest = 1;
        assertEquals(roundResultTest, rnd.checkGameWinner(p1TestHand, p1TestDiscarded, p2TestHand, p2TestDiscarded));

        //check player two win
        p1TestHand = 0;
        p1TestDiscarded = 0;
        p2TestHand = 1;
        p2TestDiscarded = 1;
        roundResultTest = -1;
        assertEquals(roundResultTest, rnd.checkGameWinner(p1TestHand, p1TestDiscarded, p2TestHand, p2TestDiscarded));

        //check no winner
        p1TestHand = 1;
        p1TestDiscarded = 1;
        p2TestHand = 1;
        p2TestDiscarded = 1;
        roundResultTest = 0;
        assertEquals(roundResultTest, rnd.checkGameWinner(p1TestHand, p1TestDiscarded, p2TestHand, p2TestDiscarded));

    }
}