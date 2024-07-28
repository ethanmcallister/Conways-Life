import java.util.Arrays;

public class LifeSimulator {
    private final int sizeX;
    private final int sizeY;
    private boolean[][] grid;

    // constructor
    public LifeSimulator(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        // internal 2d array created
        this.grid = new boolean[sizeX][sizeY];

        // fill the array with all false values
        for (boolean[] column : grid) {
            Arrays.fill(column, false);
        }
    }

    // getters
    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public boolean getCell(int x, int y) {
        return grid[x][y];
    }

    // other methods ('How' of the Life Simulator)
    public void insertPattern(Pattern pattern, int startX, int startY) {
        for (int col = startX, patternCol = 0; patternCol < pattern.getSizeX(); col++, patternCol++) {
            for (int row = startY, patternRow = 0; patternRow < pattern.getSizeY(); row++, patternRow++) {
                grid[col][row] = pattern.getCell(patternCol, patternRow);
            }
        }
    }

    public void update() {
        // make new grid
        boolean[][] updatedGrid = new boolean[sizeX][sizeY];

        // fill the array with all false values
        for (boolean[] column : updatedGrid) {
            Arrays.fill(column, false);
        }

        // traverse grid
        for (int col = 0; col < this.sizeX; col++) {
            for (int row = 0; row < this.sizeY; row++) {

                // boolean value of cell we are looking at from original grid
                boolean currentCell = getCell(col, row);

                // get count of alive neighbors
                int aliveNeighborsCount = countAliveNeighbors(col, row);

                // update the updated grid with updated
                updatedGrid[col][row] = willBeAlive(aliveNeighborsCount, currentCell);

            }
        }
        // set reference of grid to the updated grid
        grid = updatedGrid;
    }

    private int countAliveNeighbors(int col, int row) {
        int count = 0;

        // above left neighbor
        if (getNeighbor(grid, col - 1, row - 1)) {
            count++;
        }
        // above neighbor
        if (getNeighbor(grid, col, row - 1)) {
            count++;
        }
        // above right neighbor
        if (getNeighbor(grid, col + 1, row - 1)) {
            count++;
        }
        // left neighbor
        if (getNeighbor(grid, col - 1, row)) {
            count++;
        }
        // right neighbor
        if (getNeighbor(grid, col + 1, row)) {
            count++;
        }
        // bottom left neighbor
        if (getNeighbor(grid, col - 1, row + 1)) {
            count++;
        }
        // bottom neighbor
        if (getNeighbor(grid, col, row + 1)) {
            count++;
        }
        // bottom right neighbor
        if (getNeighbor(grid, col + 1, row + 1)) {
            count++;
        }

        return count;
    }

    private boolean getNeighbor(boolean[][] grid, int col, int row) {
        // if out of bounds, return false
        if (col < 0 || col >= this.sizeX || row < 0 || row >= this.sizeY) {
            return false; // Out of bounds neighbors are considered false
        }
        // the rest of them return their current boolean value
        return grid[col][row];
    }

    private static boolean willBeAlive(int aliveNeighborCount, boolean cell) {
        // if the original cell is alive
        if (cell) {
            // live cell will live if it has 2 or 3 alive neighbors
            return aliveNeighborCount == 2 || aliveNeighborCount == 3;
        } else {
            // dead cell will be alive if it has exactly 3 alive neighbors
            return aliveNeighborCount == 3;
        }
    }
}
