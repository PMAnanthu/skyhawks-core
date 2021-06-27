/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks;

import com.skyhawks.dtos.UserType;

import java.util.ArrayList;
import java.util.List;

public class UserTypeUtil {
    public static List<UserType> getUserType(int value){
        List<UserType> userTypes=new ArrayList<>();
        if(value<=127) {
            String binary = new StringBuffer(Integer.toBinaryString(value)).reverse().toString();
            for (int i = 0; i < binary.length(); i++) {
                if (binary.charAt(i) == '1') {
                    UserType userType = UserType.getUserType((int) Math.pow(2,i));
                    if(userType!=UserType.NONE) {
                        userTypes.add(userType);
                    }
                }
            }
        }
        return userTypes;
    }

    public static int getValue(List<UserType> userTypes){
        return userTypes.stream().mapToInt(UserType::getValue).sum();
    }

    public static int getValueFromString(List<String> userType) {
        List<UserType> userTypes=new ArrayList<>();
        userType.forEach(value->userTypes.add(UserType.getUserType(value)));
        return getValue(userTypes);
    }
}
