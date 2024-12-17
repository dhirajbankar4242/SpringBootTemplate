package com.prime.app.utils;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private StringUtils() {

    }

    private static final String REGEX = "([a-z])([A-Z])";

    public static final String EMPTY = "";
    private static final String REPLACEMENT = "$1_$2";

    private static final String[] tensNames = {"", " ten", " twenty", " thirty", " forty", " fifty", " sixty", " seventy", " eighty", " ninety"};

    private static final String[] numNames = {"", " one", " two", " three", " four", " five", " six", " seven", " eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen", " seventeen", " eighteen", " nineteen"};

    private static String convertLessThanOneThousand(int number) {
        String soFar;

        if (number % 100 < 20) {
            soFar = numNames[number % 100];
            number /= 100;
        } else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }
        if (number == 0) return soFar;
        return numNames[number] + " hundred" + soFar;
    }

    public static String numberToText(long number) {
        // 0 to 999 999 999 999
        if (number == 0) {
            return "zero";
        }
        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        String snumber = df.format(number);

        int billions = Integer.parseInt(snumber.substring(0, 3));
        int millions = Integer.parseInt(snumber.substring(3, 6));
        int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
        int thousands = Integer.parseInt(snumber.substring(9, 12));

        String result = "";
        if (billions != 0) {
            result = convertLessThanOneThousand(billions) + " billion ";
        }
        String tradMillions = "";
        if (millions != 0) {
            tradMillions = convertLessThanOneThousand(millions) + " million ";
        }
        result = result + tradMillions;

        String tradHundredThousands = switch (hundredThousands) {
            case 0 -> "";
            case 1 -> "one thousand ";
            default -> convertLessThanOneThousand(hundredThousands) + " thousand ";
        };
        result = result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        result = result + tradThousand;

        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }

    public static String checkString(String s) {
        if (s == null) {
            return "";
        }
        return s.trim();
    }

    public static String pad(String str, int size, char padChar) {
        if (str == null) str = "";
        StringBuilder padded = new StringBuilder(str.trim());
        while (padded.length() < size) {
            padded.append(padChar);
        }
        return padded.toString();
    }

    public static String lpad(String str, int size, char padChar) {
        if (str == null) str = "";
        StringBuilder padded = new StringBuilder(str.trim());
        while (padded.length() < size) {
            padded.insert(0, padChar);
        }
        return padded.toString();
    }

    public static boolean isValidEmail(String email) {

        Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+;");
        Matcher matcher = pattern.matcher(email);
        boolean matchFound = matcher.matches();
        if (matchFound) {
            pattern = Pattern.compile(".+@.+\\.[a-z]+");
            String[] splitStr = email.split(";");
            for (String str : splitStr) {
                matcher = pattern.matcher(str);
                if (!matcher.matches()) return false;
            }
        } else return false;

        return true;
    }

    public static boolean isUrlValidate(String url) {

        String urlPattern = "\\b(https?://|ftp://|www.)" + "[-A-Za-z0-9+&@#/%?=~_|!:,.;]" + "*[-A-Za-z0-9+&@#/%=~_|]";
        Pattern p = Pattern.compile(urlPattern);
        Matcher m = p.matcher(url);
        return m.matches();
    }

    public static String removeChar(String originalString, char c) {
        StringBuilder newString = new StringBuilder();
        originalString = checkString(originalString);
        if (originalString.indexOf(c) != -1) {
            for (int i = 0; i < originalString.length(); i++) {
                if (originalString.charAt(i) != c) newString.append(originalString.charAt(i));
            }
        } else {
            return originalString;
        }
        return newString.toString();
    }

    public static String replaceChar(String originalString, char oldChar, char newChar) {
        originalString = checkString(originalString);
        if (originalString.indexOf(oldChar) != -1) {
            originalString = originalString.replace(oldChar, newChar);
        }
        return originalString;
    }

    public static String[] parseForwardingAgent(String agentName) {
        String[] details = new String[2];
        int lastIndex = agentName.lastIndexOf("-");
        details[0] = agentName.substring(0, lastIndex).trim();
        details[1] = agentName.substring(lastIndex + 1).trim();

        return details;
    }

    public static String[] splitByCodeAndName(String value) {
        String[] details = new String[2];
        int firstIndex = value.indexOf("-");
        details[0] = value.substring(0, firstIndex).trim();
        details[1] = value.substring(firstIndex + 1).trim();

        return details;
    }

    public static boolean equals(String str1, String str2) {
        return ((str1 != null) && ((str2 == null) || str1.equals(str2)));
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return ((str1 != null) && ((str2 == null) || str1.equalsIgnoreCase(str2)));
    }


    public static BigDecimal roundingToUpperHalf(BigDecimal weight) {
        if (weight == null) return null;
        return BigDecimal.valueOf(Math.ceil(weight.doubleValue() * 2) / 2);
    }

    public static boolean isEmpty(@Nullable Object str) {
        return str == null || EMPTY.equals(str);
    }

    public static boolean isEmptyDeepCheck(@Nullable String str) {
        return isEmpty(str) || "undefined".equalsIgnoreCase(str) || "null".equalsIgnoreCase(str);
    }

    public static int length(final CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

    public static boolean isNotEmpty(@Nullable Object str) {
        return str != null && !"".equals(str);
    }

    public static String toSnakeCase(String value) {
        if (value == null) return EMPTY;
        return value.replaceAll(REGEX, REPLACEMENT).toLowerCase();
    }


    public static String generateOtp(int length) {
        SecureRandom rnd = new SecureRandom();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(rnd.nextInt(9));
        }
        return builder.toString();
    }


}
