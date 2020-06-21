package CardGameSap;

import java.util.*;

public class CardShuffler {

    /*
    Card Shuffler class contains the main card methods.
     */

    //original pre-shuffled deck
    int[] deck;

    public CardShuffler(int[] deck){
        this.deck = deck;
    }


    /*
    populates int array deck with integers 1 to 10 (x4)
    Outer loop runs 4x and inner loop 10x, providing 4 sets of integers 1 - 10.
    Count variable is used to access the array's index each loop from 0 - 39
     */

    protected void populateDeck(int[] arr){
        int count = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 1; j <=10; j++){
                arr[count] = j;
                count++;
            }

        }
    }


    /*
    Fisher-Yates shuffle algorithm - that I borrowed from the internet.
    The shuffle takes the array's length n and creates a new instance of the Random Java class to
    provide a pseudorandom integer.
    Sets randomValue to the random generated int which is set so that it cannot go beyond the array's
    length as the bound parameter is set to n - i.
    Uses the random generated randomValue to access the array at a random index
    which is stored in randomElement (temp).
    Adds the element at index position i to the array at index position randomValue.
    Adds the element that was at randomValue position to the array at position i
    Works like a bubble sort but with the random integer used to access one of the arrays at a random point.
    */

    protected void shuffleCard(int[] array){

        int n = array.length;
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {

            int randomValue = i + random.nextInt(n - i);
            int randomElement = array[randomValue];
            array[randomValue] = array[i];
            array[i] = randomElement;
        }



    }


    //returns the size of the deck (40 elements).

    protected int deckSize(){
        return deck.length;
    }


    /*
    deals 20 cards to each player's stack using a for loop that increments by 2 each loop.
    The input array is accessed at the index of i and i + 1 and uses the stack's push() method
    to add one int element of the input array per iteration to each player's stack
     */

    protected void dealCard(int[] arr, Stack<Integer> player1, Stack<Integer> player2){

        for(int i = 0; i < arr.length -1; i +=2){
            player1.push(arr[i]);
            player2.push(arr[i+1]);
        }

    }


    //After every round, cards from that hand are allocated to the winner's discardedCards pile

    protected void allocateCardsAfterRound(int p1Card, int p2Card, LinkedList<Integer> discardedCards){
        discardedCards.add(p1Card);
        discardedCards.add(p2Card);
    }


    /*
    In the case of a draw, the cards are added to the next winning player's discardedPile.
    The draw pile is then cleared.
    An iterator and a while loop are used to iterate over ever element in the list and
    add them to the player's stack. At the end of the process, the draw pile is cleared of cards.
     */
    protected void drawPileWin(LinkedList<Integer> playerDiscarded, LinkedList<Integer> drawPile){
        ListIterator<Integer> listIterator = drawPile.listIterator();
        while(listIterator.hasNext()) {
            playerDiscarded.push(listIterator.next());

        }
        drawPile.clear();
    }


    /*
    The player's card pile is refilled using this method. The discarded card pile is shuffled using Collections.shuffle() rather
    than the Fisher Yates algorithm. A while loop and iterator are used and the player's discarded card pile is then cleared when
    the loop has finished.
     */

    protected void refillPlayerStack(Stack<Integer> playerDeck, LinkedList<Integer> playerDiscarded){
            Collections.shuffle(playerDiscarded);
            ListIterator<Integer> listIterator = playerDiscarded.listIterator();
            while(listIterator.hasNext()) {
                playerDeck.push(listIterator.next());

            }
            playerDiscarded.clear();


    }


    /*
    checks to see if player stack is empty and if there are cards still in the player's discarded pile,
    it then calls the refillPlayerStack() method to refill the stack.
     */

    protected void checkEmptyStack(int playerRemainingCards, int playerDiscarded,
                                   Stack<Integer> playerCards, LinkedList<Integer> playerDiscardedCards){
        if (playerRemainingCards == 0 && playerDiscarded != 0){
            refillPlayerStack(playerCards, playerDiscardedCards);
        }

    }


}
