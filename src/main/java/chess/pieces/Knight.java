package chess.pieces;

import chess.PieceColor;
import chess.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * The Knight class
 */
public class Knight extends Piece {
    public Knight(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }

    @Override
    public Set<Position> listOfAvailableMoves(Set<Position> pieces, Set<Position> enemyPieces) {
        Set<Position> positions = new HashSet<Position>();

        PieceUtil.checkAndAdd(positions, position.getPosition(-2, -1), pieces, enemyPieces);
        PieceUtil.checkAndAdd(positions, position.getPosition(-2, 1), pieces, enemyPieces);
        PieceUtil.checkAndAdd(positions, position.getPosition(2, -1), pieces, enemyPieces);
        PieceUtil.checkAndAdd(positions, position.getPosition(2, 1), pieces, enemyPieces);
        PieceUtil.checkAndAdd(positions, position.getPosition(-1, -2), pieces, enemyPieces);
        PieceUtil.checkAndAdd(positions, position.getPosition(-1, 2), pieces, enemyPieces);
        PieceUtil.checkAndAdd(positions, position.getPosition(1, -2), pieces, enemyPieces);
        PieceUtil.checkAndAdd(positions, position.getPosition(1, 2), pieces, enemyPieces);

        return positions;
    }
}
