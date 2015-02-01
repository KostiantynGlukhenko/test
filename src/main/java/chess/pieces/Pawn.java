package chess.pieces;

import chess.PieceColor;
import chess.Position;

import java.util.HashSet;
import java.util.Set;

import static chess.pieces.PieceUtil.checkCaptureAndAdd;
import static chess.pieces.PieceUtil.checkMoveAndAdd;

/**
 * The Pawn
 */
public class Pawn extends Piece {
    private boolean firstMove = true;

    public Pawn(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }

    @Override
    public Set<Position> listOfAvailableMoves(Set<Position> pieces, Set<Position> enemyPieces) {
        Set<Position> positions = new HashSet<Position>();
        int sign = 1;
        if (getColor().equals(PieceColor.Black)) {
            sign = -1;
        }
        checkMoveAndAdd(positions, position.getPosition(0, sign), pieces, enemyPieces);
        if (firstMove) {
            checkMoveAndAdd(positions, position.getPosition(0, sign * 2), pieces, enemyPieces);
        }
        checkCaptureAndAdd(positions, position.getPosition(-1, sign), enemyPieces);
        checkCaptureAndAdd(positions, position.getPosition(1, sign), enemyPieces);
        return positions;
    }


    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
        firstMove = false;
    }

    public boolean isFirstMove() {
        return firstMove;
    }
}
