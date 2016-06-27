package com.github.mdnu.overmodule;

/**
 * Enum containing Overwatch map name values.
 * @author m
 */

public enum Maps {
	anubis("anubis"),
	dorado("dorado"),
	gibraltar("gibraltar"),
	hanamura("hanamura"),
	hollywood("hollywood"),
	kingsrow("kingsrow"),
	lijiang("lijiang"),
	nepal("nepal"),
	numbani("numbani"),
	route66("route66"),
	volskaya("volskaya");
	
	private final String name;
	
	private Maps(String s) {
		name = s;
	}
	
	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}
	
	public String toString() {
		return this.name();
	}
}
