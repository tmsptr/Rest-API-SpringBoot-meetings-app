package com.web.meetings.enums;

/**
 * Variable "value" to be a set of predefined constants
 * allowing for fixed values selection in "Type" field of meeting creation
 *
 */
public enum Type {
	LIVE("Live"), IN_PERSON("InPerson");
	
	private final String value;       

    private Type(String s) {
        value = s;
    }

    public String toString() {
       return this.value;
    }
}
