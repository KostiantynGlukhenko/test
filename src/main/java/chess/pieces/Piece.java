package chess.pieces;

import chess.PieceColor;
import chess.Position;

import java.util.Set;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final PieceColor color;
    protected Position position;

    protected Piece(PieceColor color, Position position) {
        this.color = color;
        this.position = position;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (color.equals(PieceColor.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public PieceColor getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    protected abstract char getIdentifyingCharacter();

    public abstract Set<Position> listOfAvailableMoves(Set<Position> pieces, Set<Position> enemyPieces);
}
