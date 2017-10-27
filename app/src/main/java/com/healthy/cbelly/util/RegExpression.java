package com.healthy.cbelly.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chan1park on 2017. 10. 24..
 */

public class RegExpression {

    public static boolean regexEmail(String email) {
        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        boolean isEmail = matcher.matches();
        return isEmail;
    }

    public static boolean regexPassWord(String pw) {
        String regex = "^[A-Za-z0-9_@./#&+-]*.{6,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pw);
        boolean isPW = matcher.matches();
        return isPW;

    }

}
