package dz.hanouti.Tools;

import java.util.regex.Pattern;

public class Patterns {
    public static String userNRegex = "^@[\\w\\d]{3,}_[\\w\\d]{3,}";
    public static String userNameRegex = "([a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)";
    public static String userTelRegex = "^(00213|\\+213|0)(5|6|7)[0-9]{8}$";

}
