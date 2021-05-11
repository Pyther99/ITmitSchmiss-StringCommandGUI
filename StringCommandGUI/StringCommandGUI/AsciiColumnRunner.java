package StringCommandGUI;

public class AsciiColumnRunner implements Runnable {

    public String result = "";
    private int column;
    private int max;
    private int a;

    public String generateCell() {
        StringBuilder out = new StringBuilder();
        for (int b = 0; b < column; b++) {
            out.append(AsciiTabelle.produceCell(a + b * (max / column), max, ColorScheme.standardSchema)).append(b == column - 1 ? "\n" : "\t\t");
        }
        this.result = out.toString();
        return out.toString();
    }

    @Override
    public void run() {
        generateCell();
    }

    public AsciiColumnRunner(int r, int m, int a) {
        column = r;
        max = m;
        this.a = a;
    }
}
