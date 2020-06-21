package CardGameSap;

public class Round {

    /*
    round class responsible for each round
     */

    public Round(){

    }

    //method to print required variables to console for each round
    protected void playRound(String playerName, int remainingCards, int currentHand){

        System.out.println(playerName + " (" + "cards " + remainingCards + ") " + ": " + currentHand);

    }

    //compares the players' respective hands to see if any player has won the round or if it is a draw
    protected int compareCards(int p1, int p2){

        if(p1 > p2){
            return 1;
        } else if(p2 > p1){
            return -1;
        } else{
            return 0;
        }

    }


    //prints the round winner to the console
    protected void roundWinner(int winner){
        if(winner == 1){
            System.out.println("Player one wins the round!" + "\n");;
        } else if(winner == -1){
            System.out.println("Player two wins the round!" + "\n");;
        } else{
            System.out.println("Draw! Better luck next round!" + "\n");;
        }

    }

    //checks to see if any player has won the game by checking if the opposing player has 0 cards in either card pile
    protected int checkGameWinner(int p1Hand, int p1Discarded, int p2Hand, int p2Discarded){

        if(p1Hand == 0 && p1Discarded == 0){
            System.out.println("Player 2 won the game!");
            return -1;
        } else if(p2Hand == 0 && p2Discarded == 0){
            System.out.println("Player 1 won the game!");
            return 1;
        } else {
            return 0;
        }

    }
}
