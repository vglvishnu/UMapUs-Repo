package com.games.entity;

import java.util.ArrayList;

public class CardDeck {

	ArrayList<CardInGame> cardDeck;
	String[] suitsList = { "hearts", "diamonds", "clovers", "spades" };
	String[] rankList = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
			"11", "12", "13" };

	String deckNumber;

	public String getDeckNumber() {
		return deckNumber;
	}

	public void setDeckNumber(String deckNumber) {
		this.deckNumber = deckNumber;
	}

	public CardDeck(int numberOfJokers, String deckNumber) {

		cardDeck = createDeck(numberOfJokers, deckNumber);

	}

	private ArrayList<CardInGame> createDeck(int numberOfJokers,
			String deckNumber) {

		ArrayList<CardInGame> cardDeck = new ArrayList<CardInGame>();
		this.deckNumber = deckNumber;

		for (String suit : suitsList) {

			for (String rank : rankList) {

				cardDeck.add(new CardInGame(suit, rank,
						(rank.equals("11") ? "jack"
								: (rank.equals("12") ? "queen" : (rank
										.equals("13") ? "king" : (rank
										.equals("1") ? "ace" : rank)))),
						deckNumber));

			}

		}
		for (int i = 0; i < numberOfJokers; i++) {

			cardDeck.add(new CardInGame("joker", "joker" + i, "joker",
					deckNumber));
		}

		return cardDeck;
	}

	public ArrayList<CardInGame> getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(ArrayList<CardInGame> cardDeck) {
		this.cardDeck = cardDeck;
	}

}
