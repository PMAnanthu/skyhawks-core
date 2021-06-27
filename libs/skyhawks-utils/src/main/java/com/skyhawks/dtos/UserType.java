package com.skyhawks.dtos;/*
Copyright @ 2021 - covist
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/

public enum UserType {
    STUDENT(1),
    TEACHING_STAFF(2),
    ACADEMIC_COORDINATOR(4),
    OFFICE_MANAGER(8),
    SCHOOL_MANAGER(16),
    SUPER_ADMIN(32),
    NONE(64);

    private final int value;

    UserType(int value) {
        this.value=value;
    }

    public static UserType getUserType(int value){
        for (UserType userType:UserType.values()){
            if(userType.getValue()==value)
                return userType;
        }
        return UserType.NONE;
    }

    public static UserType getUserType(String value) {
        for (UserType userType:UserType.values()){
            if(userType.toString().equals(value))
                return userType;
        }
        return UserType.NONE;
    }

    public int getValue() {
        return value;
    }
}
