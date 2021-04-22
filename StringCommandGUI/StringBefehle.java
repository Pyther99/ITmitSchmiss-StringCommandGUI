import java.nio.charset.CharacterCodingException;
import java.util.stream.IntStream;

public class StringBefehle {
    /**
     * Die Methode gibt den String verdoppelt, also zweimal hintereinander zurueck
     */
    public static String duplicate(String in) {
        // Erzeuge einen leeren Ausgabestring
        String out = "";

        // Haenge zwei Mal den Eingabestring an
        out += in + " " + in;

        // Gib den Ausgabestring zurueck
        return out;
    }

    /**
     * Kopiert jeden char aus dem Eingabestring in einen neuen String
     */
    public static String copy(String in) {

        // Deklarierung und Initialisierung des Ausgabestrings
        String out = "";

        // For-Schleife von 0 bis in.length()
        for (int i = 0; i < in.length(); i++) {

            // Dem Ausgabestring out wird der char aus in an Position i angehangen
            out += in.charAt(i);
        }

        // out wird ausgegeben
        return out;
    }

    /**
     * Gibt den Eingabestring rückwärts aus
     */
    public static String rueckwaerts(String in) {
        String out = "";

        // For-Schleife diesmal von in.length()-1 bis 0
        // Wie bei einem Array geht String von 0 bis string.length()-1
        for (int i = in.length() - 1; i >= 0; i--) {
            out += in.charAt(i);
        }

        return out;
    }

    /**
     * StringBuilder Implementation der rueckwarts() Funktion, verwendet die StringBuilder-Methode reverse()
     */
    public static String rueckwaertsStringBuilder(String in) {
        return new StringBuilder(in).reverse().toString();
    }

    /**
     * Gibt den Eingabestring in Großbuchstaben zurück
     */
    public static String caseUpper(String in) {
        String out = "";
        char ch;

        for (int i = 0; i < in.length(); i++) {
            // toUpperCase() ist eine Methode der Klasse Character und nimmt als Argument einen char an
            // Kann auch als Einzeiler geschrieben werden: out += Character.toUpperCase(in.charAt(i))
            ch = in.charAt(i);
            out += Character.toUpperCase(ch);
        }

        return out;
    }

    /**
     * Versucht den Eingabestring als Integer n zu interpretieren und gibt die ersten n ASCII Symbole aus
     */
    public static String asciiLoop(String in) {
        String out = "";

        int range;
        try {
            range = Integer.parseInt(in);
        } catch (NumberFormatException e) {
            // Falls der Eingabestring keine valide Zahl ist, wird range auf 127 gesetzt
            range = 127;
        }

        // For Schleife beginnt bei 32, da die ASCII Zeichen 0-31 Kontrollzeichen sind
        for (int i = 32; i < range; i++) {
            // Konvertierung von i -> ASCII char
            out += (char) i;
        }
        return out;
    }

    /**
     * StringBuilder Implementation von asciiLoop()
     */
    public static String asciiStringBuilder(String in) {
        StringBuilder out = new StringBuilder();

        int range;
        try {
            range = Integer.parseInt(in);
        } catch (NumberFormatException e) {
            // Falls der Eingabestring keine valide Zahl ist, wird range auf 127 gesetzt
            range = 127;
        }

        for (int i = 32; i < range; i++) {
            out.append((char) i);
        }
        return out.toString();
    }

    /**
     * Stream Implementation von asciiLoop() (unsafe, kann NumberFormatException werfen)
     */
    public static String asciiStream(String in) {
        StringBuilder out = new StringBuilder();
        IntStream.range(33, Integer.parseInt(in)).forEach(x -> out.append((char) x));
        return out.toString();
    }

    /**
     * Erhöht jeden Character aus dem Eingabestring um 1 (Naive Implementation)
     */
    public static String zeichenErhoehen(String in) {
        String out = "";

        for (int i = 0; i < in.length(); i++) {
            // Erhöht den char bei Position i um 1 und konvertiert diesen wieder zu char
            out += (char) (in.charAt(i) + 1);
        }
        return out;
    }

    /**
     * StringBuilder Implementation von zeichenErhoehen()
     */
    public static String zeichenErhoehenStringBuilder(String in) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            out.append((char) (in.charAt(i) + 1));
        }
        return out.toString();
    }

    private static Character moduloCharErhoehung(Character a, Character b, Character c) {
        return (char) ((c + 1 - a) % (b - a) + a);
    }

    private static Character erhoehezeichen(Character c) throws CharacterCodingException {
        if (Character.isWhitespace(c) || c == '.' || c == ',') {
            // Filter für Satzzeichen
            return c;
        } else if (c >= 'a' && c <= 'z') {
            // Kleinbuchstaben
            return moduloCharErhoehung('a', 'z', c);
        } else if (c >= 'A' && c <= 'Z') {
            // Großbuchstaben
            return moduloCharErhoehung('A', 'Z', c);
        }
        // Falls der Buchstabe kein Satzzeichen und kein Buchstabe des Lateinischen Alphabetes ist, wird eine
        // Exception gweorfen
        throw new CharacterCodingException();
    }

    /**
     * Erhoeht jeden Character des Eingabestrings um 1, beachtet dabei die Alphabetsgrenzen: Z + 1 -> A
     */
    public static String zeichenErhoehenIntelligent(String in) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            try {
                out.append(erhoehezeichen(in.charAt(i)));
            } catch (CharacterCodingException e) {
                return "Diese Funktion akzeptiert nur Buchstaben aus dem römischen Alphabet und Leerzeichen!";
            }
        }
        return out.toString();
    }

}
