package chess;

import chess.pieces.MainPiece;
import chess.pieces.Piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 01.02.2015.
 */
public class Player {
    private PieceColor color;

    private Map<Position, Piece> pieces;

    private MainPiece mainPiece;

    public Player(PieceColor color, Map<Position, Piece> pieces, MainPiece mainPiece) {
        this.color = color;
        this.pieces = pieces;
        this.mainPiece = mainPiece;
    }

    public PieceColor getColor() {
        return color;
    }


    public void setColor(PieceColor color) {
        this.color = color;
    }


    public Map<Position, Set<Position>> listOfAvailableMoves(Set<Position> enemyPieces) {
        Set<Position> positions = getPiecePositions();
        Map<Position, Set<Position>> list = new HashMap<Position, Set<Position>>();
        for (Map.Entry<Position, Piece> pieceEntry : pieces.entrySet()) {
            Set<Position> pieceList = pieceEntry.getValue().listOfAvailableMoves(positions, enemyPieces);
            if (!pieceList.isEmpty()) {
                list.put(pieceEntry.getKey(), pieceList);
            }
        }
        return list;
    }

    public Map<Position, Set<Position>> safeList(Set<Position> enemyPieces, Map<Position, Set<Position>> enemyList) {
        Map<Position, Set<Position>> list = listOfAvailableMoves(enemyPieces);

        Set<Position> mainPieceList = mainPiece.safeList(getPiecePositions(), enemyList);
        if (mainPieceList.isEmpty()) {
            list.remove(mainPiece.getPosition());
        } else {
            list.put(mainPiece.getPosition(), mainPieceList);
        }

        return list;
    }

    public void move(Position from, Position to, Set<Position> enemyPieces, Map<Position, Set<Position>> enemyList) {
        Piece piece = pieces.get(from);
        if (piece == null) {
            throw new InvalidPositionException("Invalid position: " + from);
        }
        if (piece == mainPiece) {
            if (!mainPiece.safeList(getPiecePositions(), enemyList).contains(to)) {
                throw new InvalidPositionException("Invalid position: " + to);
            }
        } else {
            if (!piece.listOfAvailableMoves(getPiecePositions(), enemyPieces).contains(to)) {
                throw new InvalidPositionException("Invalid position: " + to);
            }
        }
        pieces.remove(from);
        piece.setPosition(to);
        pieces.put(to, piece);
    }

    public boolean isLose(Map<Position, Set<Position>> enemyList) {
        return mainPiece.isLose(getPiecePositions(), enemyList);
    }


    /**
     * Get the piece at a given position on the board
     *
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
        return pieces.get(position);
    }

    public Set<Position> getPiecePositions() {
        return pieces.keySet();
    }

    public void capture(Position to) {
        pieces.remove(to);
    }
}
