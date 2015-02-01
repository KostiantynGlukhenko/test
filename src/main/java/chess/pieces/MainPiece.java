package chess.pieces;

import chess.PieceColor;
import chess.Position;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 01.02.2015.
 */
public abstract class MainPiece extends Piece {
    public MainPiece(PieceColor color, Position position) {
        super(color, position);
    }

    public abstract Set<Position> safeList(Set<Position> pieces, Map<Position, Set<Position>> enemyList);

    public abstract boolean isLose(Set<Position> pieces, Map<Position, Set<Position>> enemyList);
}
