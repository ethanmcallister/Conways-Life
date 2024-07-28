public class PatternGlider extends Pattern {
    private final int sizeX;
    private final int sizeY;
    private boolean[][] pattern;

    public PatternGlider() {
        this.sizeX = 3;
        this.sizeY = 3;

        this.pattern = new boolean[][] {
                {false, false, true},
                {true, false, true},
                {false, true, true}
        };
    }

    @Override
    public int getSizeX() {
        return sizeX;
    }

    @Override
    public int getSizeY() {
        return sizeY;
    }

    @Override
    public boolean getCell(int x, int y) {
        return pattern[x][y];
    }
}
