package chess.pieces;

import chess.PieceColor;
import chess.Position;

import java.util.HashSet;
import java.util.Set;

import static chess.pieces.PieceUtil.checkAndAddIterable;

/**
 * The Queen
 */
public class Queen extends Piece {
    public Queen(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }

    @Override
    public Set<Position> listOfAvailableMoves(Set<Position> pieces, Set<Position> enemyPieces) {
        Set<Position> positions = new HashSet<Position>();

        checkAndAddIterable(positions, position, pieces, enemyPieces, 0, -1);
        checkAndAddIterable(positions, position, pieces, enemyPieces, 0, 1);
        checkAndAddIterable(positions, position, pieces, enemyPieces, -1, 0);
        checkAndAddIterable(positions, position, pieces, enemyPieces, 1, 0);

        checkAndAddIterable(positions, position, pieces, enemyPieces, -1, -1);
        checkAndAddIterable(positions, position, pieces, enemyPieces, -1, 1);
        checkAndAddIterable(positions, position, pieces, enemyPieces, 1, -1);
        checkAndAddIterable(positions, position, pieces, enemyPieces, 1, 1);

        return positions;
    }
}
