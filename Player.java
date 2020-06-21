package CardGameSap;

import java.util.LinkedList;
import java.util.Stack;

public class Player {

    /*
    Player class and the relevant player methods
     */

    String playerName;
    Stack<Integer> playerCards;
    //int remainingCards;
    LinkedList<Integer> playerDiscardedPile;
    int playerDrawCard;


    protected Player(String playerName, Stack<Integer> playerCards,
                  LinkedList<Integer> playerDiscardedPile){

        this.playerName=playerName;
        this.playerCards=playerCards;
        //this.remainingCards=remainingCards;
        this.playerDiscardedPile=playerDiscardedPile;

    }

    //enables the player to draw a card
    protected int drawCard(){
        return playerDrawCard = playerCards.pop();
    }

    //returns the size of the player's stack
    protected int getPlayerCardsSize(){
        return playerCards.size();
    }

    //returns the player's name
    protected String getPlayerName(){
        return playerName;
    }

    //returns the size of the player's discarded cards
    protected int getDiscardedSize(){
        return playerDiscardedPile.size();
    }

}
