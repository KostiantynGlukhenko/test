package chess;


import chess.pieces.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

    /**
     * The current player
     */
    private PieceColor currentPieceColor = PieceColor.White;

    private Player whitePlayer;
    private Player blackPlayer;

    private boolean gameEnded;

    /**
     * A map of board positions to pieces at that position
     */
    private Map<Position, Piece> positionToPieceMap;

    /**
     * Create the game state.
     */
    public GameState() {
        positionToPieceMap = new HashMap<Position, Piece>();
    }

    public PieceColor getCurrentPieceColor() {
        return currentPieceColor;
    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces

        Map<Position, Piece> whitePieces = new HashMap<Position, Piece>(16);
        placePiece(whitePieces, new Rook(PieceColor.White, new Position("a1")));
        placePiece(whitePieces, new Knight(PieceColor.White, new Position("b1")));
        placePiece(whitePieces, new Bishop(PieceColor.White, new Position("c1")));
        placePiece(whitePieces, new Queen(PieceColor.White, new Position("d1")));
        MainPiece whiteKing = new King(PieceColor.White, new Position("e1"));
        placePiece(whitePieces, whiteKing);
        placePiece(whitePieces, new Bishop(PieceColor.White, new Position("f1")));
        placePiece(whitePieces, new Knight(PieceColor.White, new Position("g1")));
        placePiece(whitePieces, new Rook(PieceColor.White, new Position("h1")));
        placePiece(whitePieces, new Pawn(PieceColor.White, new Position("a2")));
        placePiece(whitePieces, new Pawn(PieceColor.White, new Position("b2")));
        placePiece(whitePieces, new Pawn(PieceColor.White, new Position("c2")));
        placePiece(whitePieces, new Pawn(PieceColor.White, new Position("d2")));
        placePiece(whitePieces, new Pawn(PieceColor.White, new Position("e2")));
        placePiece(whitePieces, new Pawn(PieceColor.White, new Position("f2")));
        placePiece(whitePieces, new Pawn(PieceColor.White, new Position("g2")));
        placePiece(whitePieces, new Pawn(PieceColor.White, new Position("h2")));
        whitePlayer = new Player(PieceColor.White, whitePieces, whiteKing);


        // Black Pieces
        Map<Position, Piece> blackPieces = new HashMap<Position, Piece>(16);
        placePiece(blackPieces, new Rook(PieceColor.Black, new Position("a8")));
        placePiece(blackPieces, new Knight(PieceColor.Black, new Position("b8")));
        placePiece(blackPieces, new Bishop(PieceColor.Black, new Position("c8")));
        placePiece(blackPieces, new Queen(PieceColor.Black, new Position("d8")));
        MainPiece blackKing = new King(PieceColor.Black, new Position("e8"));
        placePiece(blackPieces, blackKing);
        placePiece(blackPieces, new Bishop(PieceColor.Black, new Position("f8")));
        placePiece(blackPieces, new Knight(PieceColor.Black, new Position("g8")));
        placePiece(blackPieces, new Rook(PieceColor.Black, new Position("h8")));
        placePiece(blackPieces, new Pawn(PieceColor.Black, new Position("a7")));
        placePiece(blackPieces, new Pawn(PieceColor.Black, new Position("b7")));
        placePiece(blackPieces, new Pawn(PieceColor.Black, new Position("c7")));
        placePiece(blackPieces, new Pawn(PieceColor.Black, new Position("d7")));
        placePiece(blackPieces, new Pawn(PieceColor.Black, new Position("e7")));
        placePiece(blackPieces, new Pawn(PieceColor.Black, new Position("f7")));
        placePiece(blackPieces, new Pawn(PieceColor.Black, new Position("g7")));
        placePiece(blackPieces, new Pawn(PieceColor.Black, new Position("h7")));
        blackPlayer = new Player(PieceColor.Black, blackPieces, blackKing);
    }

    /**
     * Get the piece at the position specified by the String
     *
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     *
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
        if (whitePlayer == null) {
            return null;
        }
        Piece whitePiece = whitePlayer.getPieceAt(position);
        if (whitePiece != null) {
            return whitePiece;
        } else {
            return blackPlayer.getPieceAt(position);
        }
    }

    /**
     * Method to place a piece at a given position
     *
     * @param piece The piece to place
     */
    private void placePiece(Map<Position, Piece> map, Piece piece) {
        map.put(piece.getPosition(), piece);
    }


    //======================================
    public Map<Position, Set<Position>> listOfAvailableMoves() {
        if (gameEnded) {
            throw new InvalidGameStatusException("Game already finished");
        }
        if (currentPieceColor == PieceColor.White) {
            Map<Position, Set<Position>> blackMoves = blackPlayer.listOfAvailableMoves(whitePlayer.getPiecePositions());
            return whitePlayer.safeList(blackPlayer.getPiecePositions(), blackMoves);
        } else {
            Map<Position, Set<Position>> whiteMovies = whitePlayer.listOfAvailableMoves(blackPlayer.getPiecePositions());
            return blackPlayer.safeList(whitePlayer.getPiecePositions(), whiteMovies);
        }
    }

    public void move(Position from, Position to) {
        if (gameEnded) {
            throw new InvalidGameStatusException("Game already finished");
        }
        if (currentPieceColor == PieceColor.White) {
            Map<Position, Set<Position>> blackMoves = blackPlayer.listOfAvailableMoves(whitePlayer.getPiecePositions());
            whitePlayer.move(from, to, blackPlayer.getPiecePositions(), blackMoves);
            blackPlayer.capture(to);
        } else {
            Map<Position, Set<Position>> whiteMovies = whitePlayer.listOfAvailableMoves(blackPlayer.getPiecePositions());
            blackPlayer.move(from, to, whitePlayer.getPiecePositions(), whiteMovies);
            whitePlayer.capture(to);
        }
    }

    public boolean isLose() {
        if (currentPieceColor == PieceColor.Black) {
            gameEnded = whitePlayer.isLose(blackPlayer.listOfAvailableMoves(whitePlayer.getPiecePositions()));
            return gameEnded;
        } else {
            gameEnded = blackPlayer.isLose(whitePlayer.listOfAvailableMoves(blackPlayer.getPiecePositions()));
            return gameEnded;
        }
    }

    public void endMove() {
        if (currentPieceColor == PieceColor.White) {
            currentPieceColor = PieceColor.Black;
        } else {
            currentPieceColor = PieceColor.White;
        }
    }
}
