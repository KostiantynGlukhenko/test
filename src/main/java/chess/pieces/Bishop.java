package chess.pieces;

import chess.PieceColor;
import chess.Position;

import java.util.HashSet;
import java.util.Set;

import static chess.pieces.PieceUtil.checkAndAddIterable;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {

    public Bishop(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }

    @Override
    public Set<Position> listOfAvailableMoves(Set<Position> pieces, Set<Position> enemyPieces) {
        Set<Position> positions = new HashSet<Position>();

        checkAndAddIterable(positions, position, pieces, enemyPieces, -1, -1);
        checkAndAddIterable(positions, position, pieces, enemyPieces, -1, 1);
        checkAndAddIterable(positions, position, pieces, enemyPieces, 1, -1);
        checkAndAddIterable(positions, position, pieces, enemyPieces, 1, 1);

        return positions;
    }
}
