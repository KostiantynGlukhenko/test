package chess.pieces;

import chess.Position;

import java.util.Set;

/**
 * Created by Administrator on 01.02.2015.
 */
public class PieceUtil {

    public static void checkMoveAndAdd(Set<Position> positions, Position position, Set<Position> pieces, Set<Position> enemyPieces) {
        if (position != null && !pieces.contains(position) && !enemyPieces.contains(position)) {
            positions.add(position);
        }
    }

    public static void checkCaptureAndAdd(Set<Position> positions, Position position, Set<Position> enemyPieces) {
        if (position != null && enemyPieces.contains(position)) {
            positions.add(position);
        }
    }

    public static boolean checkAndAdd(Set<Position> positions, Position position, Set<Position> pieces, Set<Position> enemyPieces) {
        if (position != null && !pieces.contains(position)) {
            positions.add(position);
            return !enemyPieces.contains(position);
        } else {
            return false;
        }
    }


    public static void checkAndAddIterable(Set<Position> positions, Position position, Set<Position> pieces, Set<Position> enemyPieces, int columnDirection, int rowDirection) {
        for (int i = 1; i <= 7; i++) {
            if (!checkAndAdd(positions, position.getPosition(-i * columnDirection, -i * rowDirection), pieces, enemyPieces)) {
                break;
            }
        }
    }
}
