public class PatternBlock extends Pattern {
    private final int sizeX;
    private final int sizeY;
    private boolean[][] pattern;

    public PatternBlock() {
        this.sizeX = 2;
        this.sizeY = 2;

        this.pattern = new boolean[][]{
                {true, true},
                {true, true}
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
