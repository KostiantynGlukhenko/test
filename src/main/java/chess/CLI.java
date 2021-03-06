package chess;

import chess.pieces.Piece;

import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 * This class provides the basic CLI interface to the Chess game.
 */
public class CLI {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final BufferedReader inReader;
    private final PrintStream outStream;

    private GameState gameState = null;

    public CLI(InputStream inputStream, PrintStream outStream) {
        this.inReader = new BufferedReader(new InputStreamReader(inputStream));
        this.outStream = outStream;
        writeOutput("Welcome to Chess!");
    }

    /**
     * Write the string to the output
     *
     * @param str The string to write
     */
    private void writeOutput(String str) {
        this.outStream.println(str);
    }

    /**
     * Retrieve a string from the console, returning after the user hits the 'Return' key.
     *
     * @return The input from the user, or an empty-length string if they did not type anything.
     */
    private String getInput() {
        try {
            this.outStream.print("> ");
            return inReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from input: ", e);
        }
    }

    void startEventLoop() {
        writeOutput("Type 'help' for a list of commands.");
        doNewGame();

        while (true) {
            try {
                showBoard();
                writeOutput(gameState.getCurrentPieceColor() + "'s Move");

                String input = getInput();
                if (input == null) {
                    break; // No more input possible; this is the only way to exit the event loop
                } else if (input.length() > 0) {
                    if (input.equals("help")) {
                        showCommands();
                    } else if (input.equals("new")) {
                        doNewGame();
                    } else if (input.equals("quit")) {
                        writeOutput("Goodbye!");
                        System.exit(0);
                    } else if (input.equals("board")) {
                        writeOutput("Current Game:");
                    } else if (input.equals("list")) {
                        writeList();
                    } else if (input.startsWith("move")) {
                        doMove(input);
                    } else {
                        writeOutput("I didn't understand that.  Type 'help' for a list of commands.");
                    }
                }
            } catch (InvalidGameStatusException e) {
                writeOutput(e.getLocalizedMessage());
            }
        }
    }

    private void doMove(String input) {
        String[] words = input.split("\\s+");
        if (words.length != 3) {
            writeOutput("I didn't understand that.  Type 'help' for a list of commands.");
        } else {
            try {
                Position from = new Position(words[1]);
                Position to = new Position(words[2]);
                gameState.move(from, to);
                if (gameState.isLose()) {
                    writeOutput("The game is over.  Congrats to " + gameState.getCurrentPieceColor());
                } else {
                    gameState.endMove();
                }
            } catch (PositionValidationException e) {
                writeOutput(e.getLocalizedMessage());
            } catch (InvalidPositionException e) {
                writeOutput(e.getLocalizedMessage());
            }
        }

    }

    private void writeList() {
        Map<Position, Set<Position>> list = gameState.listOfAvailableMoves();
        writeOutput(gameState.getCurrentPieceColor() + "'s Possible Moves:");
        for (Map.Entry<Position, Set<Position>> listEntry : list.entrySet()) {
            Position position = listEntry.getKey();
            for (Position destPosition : listEntry.getValue()) {
                writeOutput("   " + position + " " + destPosition);
            }
        }
        writeOutput("   ");

    }

    private void doNewGame() {
        gameState = new GameState();
        gameState.reset();
    }

    private void showBoard() {
        writeOutput(getBoardAsString());
    }

    private void showCommands() {
        writeOutput("Possible commands: ");
        writeOutput("    'help'                       Show this menu");
        writeOutput("    'quit'                       Quit Chess");
        writeOutput("    'new'                        Create a new game");
        writeOutput("    'board'                      Show the chess board");
        writeOutput("    'list'                       List all possible moves");
        writeOutput("    'move <colrow> <colrow>'     Make a move");
    }

    /**
     * Display the board for the user(s)
     */
    String getBoardAsString() {
        StringBuilder builder = new StringBuilder();
        builder.append(NEWLINE);

        printColumnLabels(builder);
        for (int i = Position.MAX_ROW; i >= Position.MIN_ROW; i--) {
            printSeparator(builder);
            printSquares(i, builder);
        }

        printSeparator(builder);
        printColumnLabels(builder);

        return builder.toString();
    }


    private void printSquares(int rowLabel, StringBuilder builder) {
        builder.append(rowLabel);

        for (char c = Position.MIN_COLUMN; c <= Position.MAX_COLUMN; c++) {
            Piece piece = gameState.getPieceAt(String.valueOf(c) + rowLabel);
            char pieceChar = piece == null ? ' ' : piece.getIdentifier();
            builder.append(" | ").append(pieceChar);
        }
        builder.append(" | ").append(rowLabel).append(NEWLINE);
    }

    private void printSeparator(StringBuilder builder) {
        builder.append("  +---+---+---+---+---+---+---+---+").append(NEWLINE);
    }

    private void printColumnLabels(StringBuilder builder) {
        builder.append("   ");
        for (char c = Position.MIN_COLUMN; c <= Position.MAX_COLUMN; c++) {
            builder.append(" ").append(c).append("  ");
        }

        builder.append(NEWLINE);
    }

    public static void main(String[] args) {
        CLI cli = new CLI(System.in, System.out);
        cli.startEventLoop();
    }
}
