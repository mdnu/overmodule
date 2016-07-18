package com.github.mdnu.overmodule;

public enum Characters {
	ana("Ana"),
	bastion("Bastion"),
	dva("D.Va"),
	genji("Genji"),
	hanzo("Hanzo"),
	junkrat("Junkrat"),
	lucio("Lucio"),
	mccree("McCree"),
	mei("Mei"),
	mercy("Mercy"),
	pharah("Pharah"),
	reinhardt("Reinhardt"),
	roadhog("Roadhog"),
	soldier76("Soldier 76"),
	symmetra("Symmetra"),
	torbjorn("Torbjorn"),
	tracer("Tracer"),
	widowmaker("Widowmaker"),
	winston("Winston"),
	zarya("Zarya"),
	zenyatta("Zenyatta");
	
	private final String name;
	
	private Characters(String s) {
		name = s;
	}
	
	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}
	
	public String toString() {
		return this.name();
	}
}
