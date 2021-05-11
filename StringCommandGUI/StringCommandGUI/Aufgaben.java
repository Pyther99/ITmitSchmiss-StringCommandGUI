package StringCommandGUI;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Aufgaben {

    public static String OrdinalzahlenBasic(String in) {
        String out = "";
        for (int i = 0; i < in.length(); i++) {
            out += (int) in.charAt(i) + " ";
        }
        return out;
    }

    public static String Ordinalzahlen(String in) {
        StringBuilder out = new StringBuilder();
        in.chars().forEach(x -> out.append(x).append(" "));
        return out.toString();
    }

    public static String upperCase(String in) {
        String out = "";
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            if (c >= 'a' && c <= 'z') {
                out += (char) (c - 32);
            } else if (c == 'ä') {
                out += 'Ä';
            } else if (c == 'ö') {
                out += 'Ö';
            } else if (c == 'ü') {
                out += 'Ü';
            } else if (c == 'ß') {
                out += "SS";
            } else {
                out += c;
            }
        }
        return out;
    }

    public static boolean istZiffer(char c) {
        return c >= '0' && c <= '9';
    }

    public static String arabischeZiffernErsetzenTrivial(String in) {
        String out = "";

        String[] ziffernArray = {"null", "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun"};

        for (int i = 0; i <in.length(); i++) {
            if (istZiffer(in.charAt(i))) {
                out += ziffernArray[Integer.parseInt(String.valueOf(in.charAt(i)))];
            } else {
                out += in.charAt(i);
            }
        }

        return out;
    }

    public static String arabischeZiffernErsetzen(String in) {
        String[] ziffernArray = {"null", "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun"};
        StringBuilder out = new StringBuilder();
        in.chars()
                .mapToObj(x -> (char) x)
                .forEach(x -> out.append(istZiffer(x)?ziffernArray[Integer.parseInt(Character.toString(x))]:x));

        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(arabischeZiffernErsetzenTrivial("123Hello"));
    }
}
