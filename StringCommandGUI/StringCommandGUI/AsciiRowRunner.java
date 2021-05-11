package StringCommandGUI;

public class AsciiRowRunner implements Runnable {

    public String result = "";
    private int row;
    private int max;
    private int a;

    public String generateCell() {
        StringBuilder out = new StringBuilder();
        for (int b = 0; b < row; b++) {
            out.append(AsciiTabelle.produceCell(a + b * (max / row), max, ColorScheme.standardSchema)).append(b == row - 1 ? "\n" : "\t\t");
        }
        this.result = out.toString();
        return out.toString();
    }

    @Override
    public void run() {
        generateCell();
    }

    public AsciiRowRunner(int r, int m, int a) {
        row = r;
        max = m;
        this.a = a;
    }
}
