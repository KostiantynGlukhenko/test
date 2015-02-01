package chess.pieces;

import chess.PieceColor;
import chess.Position;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.*;

/**
 * Created by Administrator on 01.02.2015.
 */
public class RookTest {
    @Test
    public void listOfAvailableMovesTest(){
        Piece rook = new Rook(PieceColor.Black, new Position("d4"));

        Set<Position> list = rook.listOfAvailableMoves(new HashSet<Position>(), new HashSet<Position>());
        assertNotNull(list);

        assertFalse(list.contains(rook.getPosition()));

        assertEquals(14, list.size());

    }

    @Test
    public void listOfAvailableMovesA1Test(){
        Piece rook = new Rook(PieceColor.Black, new Position("a1"));

        Set<Position> list = rook.listOfAvailableMoves(new HashSet<Position>(), new HashSet<Position>());
        assertNotNull(list);

        assertFalse(list.contains(rook.getPosition()));

        assertEquals(14, list.size());
    }

    @Test
    public void listOfAvailableMovesA1WithPiecesTest(){
        Piece rook = new Rook(PieceColor.Black, new Position("a1"));

        Set<Position> positions = new HashSet<>();
        positions.add(new Position("a2"));
        positions.add(new Position("b1"));

        Set<Position> list = rook.listOfAvailableMoves(positions, new HashSet<Position>());
        assertNotNull(list);

        assertTrue(list.isEmpty());

        list = rook.listOfAvailableMoves(new HashSet<Position>(), positions);
        assertNotNull(list);

        assertEquals(2, list.size());
        assertTrue(list.contains(new Position("a2")));
        assertTrue(list.contains(new Position("b1")));
    }
}
