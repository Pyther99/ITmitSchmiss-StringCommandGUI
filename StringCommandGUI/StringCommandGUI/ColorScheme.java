package StringCommandGUI;

public class ColorScheme {

    public static final ColorScheme standardSchema = new ColorScheme(new String[]{
            "",
            CC.ANSI_BLUE,
            CC.ANSI_RED,
            CC.ANSI_PURPLE,
            ""
    });

    private final String[] colorList;

    public String get(int i) {
        if (i < colorList.length)
            return colorList[i];
        return "";
    }

    public ColorScheme() {
        colorList = new String[0];
    }

    public ColorScheme(String[] arr) {
        colorList = arr;
    }
}
