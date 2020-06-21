package CardGameSap;

import java.util.LinkedList;
import java.util.Stack;

public class PlayGame {

    /*
    Class responsible for the game play itself
     */



    /*
    A stack was chosen as the player's card deck for its LIFO properties, which
    mimic a real card game where the deck is dealt from the top.
     */

    Stack<Integer> playerOneStack = new Stack<>();
    Stack<Integer> playerTwoStack = new Stack<>();

    /*
    Linkedlist used for the discarded piles and for the draw pile
    as there is no requirement to find an element by its index,
    where an arraylist would be superior, as we only use the lists
    for add and remove operations.
     */
    LinkedList<Integer> playerOneDiscarded = new LinkedList<>();
    LinkedList<Integer> playerTwoDiscarded = new LinkedList<>();
    LinkedList<Integer> drawPile = new LinkedList<>();


    //main method responsible for the game
    protected void playGame(){

        //initialise the initial unshuffled deck
        int[] deck = new int[40];

        /*
        create a new instance of the CardShuffler class, calls populateDeck() passing the deck
        to add 40 elements of integers 1 - 10 and then calls the shuffleCard() method on the deck
        which uses the Fisher-Yates algorithm to shuffle the deck.
         */

        CardShuffler cs = new CardShuffler(deck);
        cs.populateDeck(deck);
        cs.shuffleCard(deck);



        //Creates two instances of Player class for the two players, passing in the relevant attributes
        Player p1 = new Player("playerOne", playerOneStack, playerOneDiscarded);
        Player p2 = new Player("playerTwo", playerTwoStack, playerTwoDiscarded);

        //calls the dealCard() method to share the 40 cards evenly among the two players
        cs.dealCard(deck, playerOneStack, playerTwoStack);

        //System.out.println(playerOneStack.size());
        //System.out.println(playerTwoStack.size());

        //boolean to break loop when player wins
        boolean noWinner = true;

        //each player's current hand
        int p1Card = 0;
        int p2Card = 0;

        //creating instance of Round class
        Round round = new Round();

        //variables used to determine winners
        int whoWonRound = 0;
        int whoWonGame = 0;

        //main game while loop which runs until the noWinner condition is false
        while(noWinner){

            //cards are drawn using the drawCard() method
            p1Card = p1.drawCard();
            p2Card = p2.drawCard();

            //round is played
            round.playRound(p1.getPlayerName(),
                    p1.getPlayerCardsSize() + 1, p1Card);
            round.playRound(p2.getPlayerName(),
                    p2.getPlayerCardsSize() + 1, p2Card);

            //check to see who won the round
            whoWonRound = round.compareCards(p1Card, p2Card);

            //print out the round's winner to console
            round.roundWinner(whoWonRound);

            /*
            allocate the winning cards and draw pile if applicable to the winner after the round
            or add the cards to the draw pile
             */

            if(whoWonRound == 1){
                cs.allocateCardsAfterRound(p1Card, p2Card, playerOneDiscarded);
                if (!drawPile.isEmpty()){
                    cs.drawPileWin(playerOneDiscarded, drawPile);
                }

            } else if(whoWonRound == -1){
                cs.allocateCardsAfterRound(p1Card, p2Card, playerTwoDiscarded);
                if (!drawPile.isEmpty()){
                    cs.drawPileWin(playerTwoDiscarded, drawPile);
                }
            } else{
                drawPile.add(p1Card);
                drawPile.add(p2Card);
            }

            //get the size of each player's card stack and discarded pie
            int p1RemainingCards = p1.getPlayerCardsSize();
            int p1RemainingDiscarded = p1.getDiscardedSize();
            int p2RemainingCards = p2.getPlayerCardsSize();
            int p2RemainingDiscarded = p2.getDiscardedSize();

            //check to see if we have a winner
           whoWonGame = round.checkGameWinner(p1RemainingCards, p1RemainingDiscarded,
                                                p2RemainingCards, p2RemainingDiscarded);

           //if one of the two players won, condition of noWinner = false, loop is broken and game ends
           if(whoWonGame == 1 || whoWonGame == -1){
               noWinner=false;
           }


           //check to see if a player's card stack is empty and refills it
           cs.checkEmptyStack(p1RemainingCards, p1RemainingDiscarded,
                   playerOneStack, playerOneDiscarded);
           cs.checkEmptyStack(p2RemainingCards, p2RemainingDiscarded,
                   playerTwoStack, playerTwoDiscarded);


        }


    }


}

