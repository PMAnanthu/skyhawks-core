package com.skyhawks;/*
Copyright @ 2021 - covist
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/

import java.util.Random;

public class Utils {
    public static char[] randomChar(int length){
        char[] chars=new char[length];
        String string="AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789!@#$%^&*?-+=(){}|[];',./<>";
        Random random = new Random();
        for(int i=0;i<length;i++){
            chars[i]=string.charAt(random.nextInt(string.length()));
        }
        return chars;
    }
}
