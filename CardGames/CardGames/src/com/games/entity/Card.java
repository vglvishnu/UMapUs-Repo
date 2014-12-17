package com.games.entity;

public class Card {
	
	 private String suit;
	 private String rank;
	 private String faceValue;
	 
	 
	public Card(String suit, String rank, String faceValue) {
		
		this.suit = suit;
		this.rank = rank;
		this.faceValue = faceValue;
		
		
	}
	 
	public String getSuit() {
		return suit;
	}
	public void setSuits(String suits) {
		this.suit = suit;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(String faceValue) {
		this.faceValue = faceValue;
	}

}
