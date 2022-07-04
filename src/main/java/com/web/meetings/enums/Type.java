package com.web.meetings.enums;

public enum Type {
	LIVE("Live"), IN_PERSON("InPerson");
	
	 private final String value;       

    private Type(String s) {
        value = s;
    }

    public boolean equalsValue(String otherValue) {
        return value.equals(otherValue);
    }

    public String toString() {
       return this.value;
    }
}
