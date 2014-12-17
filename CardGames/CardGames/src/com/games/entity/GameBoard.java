package com.games.entity;

import java.util.ArrayList;

public class GameBoard {

	int numberOfPlayers;
	int numberofDeck;
	
	static ArrayList<CardInGame> cardOnTable;
	static String tableName;
	static ArrayList<String> playerInGame;
	
	public void setUpGameBoard(int numberOfPlayers, int numberOfDeck, int cardPerPlayer) {
		
		
		
	}
	
	public int determineNumberOfDeck (int numberOfPlayers , int cardPerPlayer ) {
		
		 int numberOfDeck = (cardPerPlayer * numberOfPlayers ) % 56;
	
		 return 1;
	}
	
}
