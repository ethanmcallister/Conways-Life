public class PatternBlinker extends Pattern {
    private final int sizeX;
    private final int sizeY;
    private boolean[][] pattern;

    public PatternBlinker() {
        this.sizeX = 3;
        this.sizeY = 3;

        this.pattern = new boolean[][]{
                {false, true, false},
                {false, true, false},
                {false, true, false}
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
