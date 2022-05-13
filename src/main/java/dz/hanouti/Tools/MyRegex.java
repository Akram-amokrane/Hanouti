package dz.hanouti.Tools;

import java.util.regex.Pattern;

public  class MyRegex {

    public static boolean match(String str, String pattern){
        return Pattern.compile(pattern,Pattern.CASE_INSENSITIVE).matcher(str).matches();
    }

}
