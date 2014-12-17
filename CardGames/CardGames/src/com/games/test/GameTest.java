package com.games.test;

import java.util.ArrayList;
import java.util.List;

import com.games.entity.CardDeck;
import com.games.entity.CardInGame;

public class GameTest {

	public static void main(String[] args) {

		int nofDeck = 4;
		
		List<CardDeck> cardDecks = new ArrayList<CardDeck>(4);
		
		for (int i=1 ; i<= nofDeck ; i++) {
			 cardDecks.add(new CardDeck(4,Integer.toString(i)));
				
		 }
		
		
		for(CardDeck c:cardDecks) {
			
			System.out.println("--------Begin of Deck---------" + c.getDeckNumber());
			
			for (CardInGame card: c.getCardDeck())
			{
				System.out.println("Card values = " + card.getSuit() + "->"
						+ card.getRank() + "->" + card.getFaceValue() + "->" + card.getFromDeckNumber() );
			}
			
		}

		int numberOfDeck = ((8 * 13 ) / 56 ) + 1;
		 System.out.println(" Number oF Deck = " + numberOfDeck);
		numberOfDeck = (((8 * 13 ) % 56 ) < (8*13)) ? ++numberOfDeck : numberOfDeck;
        System.out.println(" Number oF Deck = " + numberOfDeck);
	}
	
	

}
