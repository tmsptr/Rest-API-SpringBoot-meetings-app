package com.web.meetings.enums;

public enum Category {
	CODE_MONKEY("CodeMonkey"), HUB("Hub"), SHORT("Short"), TEAM_BUILDING("TeamBuilding");
	
	 private final String value;       

    private Category(String s) {
        value = s;
    }

    public boolean equalsValue(String otherValue) {
        return value.equals(otherValue);
    }

    public String toString() {
       return this.value;
    }
}
