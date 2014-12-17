package com.games.entity;

public class CardInGame  extends Card{

	
	 public CardInGame(String suit, String rank, String faceValue) {
		super(suit, rank, faceValue);
		// TODO Auto-generated constructor stub
	}

	String fromDeckNumber;
   
	public String getFromDeckNumber() {
		return fromDeckNumber;
	}

	public void setFromDeckNumber(String fromDeckNumber) {
		this.fromDeckNumber = fromDeckNumber;
	}

	public CardInGame(String suit,String rank, String faceValue, String fromDeckNumber) {
		
		super(suit, rank, faceValue);
		this.fromDeckNumber = fromDeckNumber;
	}
	
	

	
	
}
