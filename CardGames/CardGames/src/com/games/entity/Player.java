package com.games.entity;

import java.util.ArrayList;

public class Player {

	ArrayList<CardInGame> inHand = new ArrayList<CardInGame>();
	String name;
	boolean inPlay;

	public ArrayList<CardInGame> getInHand() {
		return inHand;
	}

	public void setInHand(ArrayList<CardInGame> inHand) {
		this.inHand = inHand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInPlay() {
		return inPlay;
	}

	public void setInPlay(boolean inPlay) {
		this.inPlay = inPlay;
	}

}
