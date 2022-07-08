package com.web.meetings.enums;

/**
 * Variable "value" to be a set of predefined constants
 * allowing for fixed values selection in "Category" field of meeting creation
 */
public enum Category {
	CODE_MONKEY("CodeMonkey"), HUB("Hub"), SHORT("Short"), TEAM_BUILDING("TeamBuilding");
	
	private final String value;       

    private Category(String s) {
        this.value = s;
    }

    public String toString() {
       return this.value;
    }
}
