// Reference for Lanterna 3: https://github.com/mabe02/lanterna/blob/master/docs/contents.md
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.swing.*;

public class ConwaysLife {
    static final int COLUMNS = 80;
    static final int ROWS = 25;

    public static void main(String[] args) {
        // Print Java version information
        System.out.println("Java compiler version (javac): " + System.getProperty("java.version"));
        System.out.println("Java runtime version: " + Runtime.version());
        System.out.println("Java vendor: " + System.getProperty("java.vendor"));
        System.out.println("Java vendor URL: " + System.getProperty("java.vendor.url"));
        System.out.println("Java home: " + System.getProperty("java.home"));

        try {
            // Create a SwingTerminalFrame with configuration
            TerminalSize size = new TerminalSize(COLUMNS, ROWS);
            SwingTerminalFrame terminalFrame = new SwingTerminalFrame(
                    "Lanterna Swing Example",
                    size,
                    TerminalEmulatorDeviceConfiguration.getDefault(),
                    SwingTerminalFontConfiguration.getDefault(),
                    TerminalEmulatorColorConfiguration.getDefault(),
                    TerminalEmulatorAutoCloseTrigger.CloseOnExitPrivateMode);

            // Ensure the frame is properly initialized and has a valid size
            terminalFrame.setVisible(true);
            terminalFrame.pack();

            // Initialize the screen
            Screen screen = new TerminalScreen(terminalFrame);
            TextGraphics graphics = screen.newTextGraphics();

            // create simulation
            LifeSimulator simulation = new LifeSimulator(size.getColumns(), size.getRows());

            // insert block pattern
            PatternBlock blockPattern = new PatternBlock();
            simulation.insertPattern(blockPattern, 50, 4);
            // insert blinker pattern
            PatternBlinker blinkerPattern = new PatternBlinker();
            simulation.insertPattern(blinkerPattern, 10, 4);
            // insert glider pattern
            PatternGlider gliderPattern = new PatternGlider();
            simulation.insertPattern(gliderPattern, 10, 8);
            // insert acorn pattern
            PatternAcorn acornPattern = new PatternAcorn();
            simulation.insertPattern(acornPattern, 40, 12);

            screen.startScreen();
            screen.setCursorPosition(null);

            for (int i = 0; i < size.getColumns(); i++) {
                render(simulation, screen, graphics);
                simulation.update();
                Thread.yield(); // Let the JVM have some time to update other things
                Thread.sleep(100); // Sleep for a bit to make for a nicer paced animation
            }

            screen.stopScreen();
        } catch (Exception ex) {
            System.out.println("Something bad happened: " + ex.getMessage());
        }
    }

    // render the screen row by row
    public static void render(LifeSimulator simulation, Screen screen, TextGraphics graphics) {
        screen.clear();

        // nested for loop that goes through rows and cols of Life Simulator, prints only alive cells
        for (int col = 0; col < simulation.getSizeX(); col++) {

            for (int row = 0; row < simulation.getSizeY(); row++) {

                // check if cell is alive. If so, set to 'X' character
                if (simulation.getCell(col, row)) {
                    graphics.setCharacter(col, row, 'X');
                }
            }
        }

        // This is what causes the console to render the new state
        try {
            screen.refresh();
        } catch (Exception ex) {
            System.out.println("Something bad happened: " + ex.getMessage());
        }
    }
}
