package chess.pieces;

import chess.PieceColor;
import chess.Position;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The King class
 */
public class King extends MainPiece {
    public King(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public Set<Position> safeList(Set<Position> pieces, Map<Position, Set<Position>> enemyList) {
        Set<Position> positions = new HashSet<Position>();

        checkAndAdd(positions, position.getPosition(-1, -1), pieces, enemyList);
        checkAndAdd(positions, position.getPosition(-1, 0), pieces, enemyList);
        checkAndAdd(positions, position.getPosition(-1, 1), pieces, enemyList);
        checkAndAdd(positions, position.getPosition(0, -1), pieces, enemyList);
        checkAndAdd(positions, position.getPosition(0, 1), pieces, enemyList);
        checkAndAdd(positions, position.getPosition(1, -1), pieces, enemyList);
        checkAndAdd(positions, position.getPosition(1, 0), pieces, enemyList);
        checkAndAdd(positions, position.getPosition(1, 1), pieces, enemyList);

        return positions;
    }

    private void checkAndAdd(Set<Position> positions, Position position, Set<Position> pieces, Map<Position, Set<Position>> enemyList) {
        if (position != null && !pieces.contains(position) && !contains(position, enemyList)) {
            positions.add(position);
        }
    }

    private boolean contains(Position position, Map<Position, Set<Position>> enemyList) {
        for (Set<Position> positionSet : enemyList.values()) {
            for (Position enemyMove : positionSet) {
                if (enemyMove.equals(position)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isLose(Set<Position> pieces, Map<Position, Set<Position>> enemyList) {
        return contains(position, enemyList) && safeList(pieces, enemyList).isEmpty();
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }

    @Override
    public Set<Position> listOfAvailableMoves(Set<Position> pieces, Set<Position> enemyPieces) {
        Set<Position> positions = new HashSet<Position>();

        add(positions, position.getPosition(-1, -1), pieces);
        add(positions, position.getPosition(-1, 0), pieces);
        add(positions, position.getPosition(-1, 1), pieces);
        add(positions, position.getPosition(0, -1), pieces);
        add(positions, position.getPosition(0, 1), pieces);
        add(positions, position.getPosition(1, -1), pieces);
        add(positions, position.getPosition(1, 0), pieces);
        add(positions, position.getPosition(1, 1), pieces);


        return positions;
    }

    private void add(Set<Position> positions, Position position, Set<Position> pieces) {
        if (position != null && !pieces.contains(position)) {
            positions.add(position);
        }
    }
}
