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
public class BishopTest {

    @Test
    public void listOfAvailableMovesTest(){
        Piece bishop = new Bishop(PieceColor.Black, new Position("d4"));

        Set<Position> list = bishop.listOfAvailableMoves(new HashSet<Position>(), new HashSet<Position>());
        assertNotNull(list);

        assertFalse(list.contains(bishop.getPosition()));

        assertEquals(13, list.size());

    }

    @Test
    public void listOfAvailableMovesA1Test(){
        Piece bishop = new Bishop(PieceColor.Black, new Position("a1"));

        Set<Position> list = bishop.listOfAvailableMoves(new HashSet<Position>(), new HashSet<Position>());
        assertNotNull(list);

        assertFalse(list.contains(bishop.getPosition()));

        assertEquals(7, list.size());
    }

    @Test
    public void listOfAvailableMovesA1WithPiecesTest(){
        Piece bishop = new Bishop(PieceColor.Black, new Position("a1"));

        Set<Position> positions = new HashSet<>();
        positions.add(new Position("b2"));

        Set<Position> list = bishop.listOfAvailableMoves(positions, new HashSet<Position>());
        assertNotNull(list);

        assertTrue(list.isEmpty());

        list = bishop.listOfAvailableMoves(new HashSet<Position>(), positions);
        assertNotNull(list);

        assertEquals(1, list.size());
        assertTrue(list.contains(new Position("b2")));
    }
}
