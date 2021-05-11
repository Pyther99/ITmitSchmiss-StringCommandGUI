package StringCommandGUI;

import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class AsciiTabelle {

    private static final String[] controlCharacters = {"NUL", "SOH", "STX", "ETX", "EOT", "ENQ", "ACK", "BEL", "BS", "TAB", "LF", "VT", "FF", "CR", "SO", "Si", "DLE", "SC1", "DC2", "DC3", "DC4", "NAK", "SYN", "ETB", "CAn", "EM", "SUB", "ESC", "FS", "GS", "RS", "US"};

    private static int nextpower(int x, int n) {
        return (int) Math.ceil(Math.log(x) / Math.log(n));
    }

    private static String linksauffuellen(String in, int n) {
        return "0".repeat(n - in.length()) + in;
    }

    private static String rechtsauffuellen(String in, int n) {
        return in + " ".repeat(n - in.length());
    }

    private static String bitwiseUpperCase(String in) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            out.append(c < ':'? c:(char) ((int) in.charAt(i) & 65503));
        }
        return out.toString();
    }

    private static boolean isPrintableChar(char c) {
        Character.UnicodeBlock block = Character.UnicodeBlock.of( c );
        return (!Character.isISOControl(c)) &&
                c != KeyEvent.CHAR_UNDEFINED &&
                block != null &&
                Arrays.stream(Blacklist.blacklistedBlocks).noneMatch((x) -> x == block) &&
                block != Character.UnicodeBlock.SPECIALS;
    }

    private static String makeCharPrintable(char in) {
        return isPrintableChar(in)? Character.toString(in): in < controlCharacters.length? controlCharacters[in] : "-";
    }

    public static String produceHeader(int row, int max) {
        return String.format("Dec%1$s│ Bin%2$s│ Hx%3$s│ Oct%4$s│ Char  \t\t".repeat(row) + "\n",
                " ".repeat(nextpower(max, 10)-2),
                " ".repeat(nextpower(max, 2)-2),
                " ".repeat(nextpower(max, 16)-1),
                " ".repeat(nextpower(max, 8)-2));
    }

    public static String colorLine(String colorCode, String in) {
        return colorCode + in + (colorCode.equals("") ? "":CC.ANSI_RESET);
    }

    public static String produceCell(int i, int max, ColorScheme scheme) {

        char c = (char) i;

        // \\u200e ist der Left-to-right Mark character und löst Ausrichtungsprobleme
        // mit manchen Buchstaben der arabischen Sprache
        String[] lineArr = {
                rechtsauffuellen(Integer.toString(i), nextpower(max, 10)),
                linksauffuellen(Integer.toBinaryString(i), nextpower(max, 2)),
                rechtsauffuellen(bitwiseUpperCase(Integer.toHexString(i)), nextpower(max, 16)),
                rechtsauffuellen(Integer.toOctalString(i), nextpower(max, 8)),
                "\u200e" + rechtsauffuellen(makeCharPrintable(c), 4) + "  "
        };

        StringBuilder out = new StringBuilder();

        for (int j = 0; j < lineArr.length; j++)
            out.append(colorLine(scheme.get(j), lineArr[j])).append(j != lineArr.length - 1 ? " │ " : "");

        return out.toString();
    }

    /**
     * Gibt eine formatierte ASCII-Tabelle zurück
     * @param row Anzahl der Reihen
     * @param max Der maximale Char der ausgegeben werden soll
     * @return Formatierte Tabelle
     */
    public static String table(int row, int max, ColorScheme scheme) {

        StringBuilder out = new StringBuilder();

        out.append(produceHeader(row, max));

        for (int a = 0; a < max / row; a++)
            for (int b = 0; b < row; b++)
                out.append(produceCell(a + b * (max / row), max, scheme)).append(b == row - 1 ? "\n" : "\t\t");

        return out.toString();
    }

    public static void measure() {
        // Für 5000, 50000: PT0.7108615S
        Instant start = Instant.now();
        String x = table(100000, 1000000, ColorScheme.standardSchema);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }

    public static void main(String[] args) {
        System.out.println(table(4, 127, ColorScheme.standardSchema));
        // measure();
        // System.out.println(table(5000, 50000));
    }

}
