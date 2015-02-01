package chess.pieces;

import chess.PieceColor;
import chess.Position;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Administrator on 01.02.2015.
 */
public class QueenTest {
    @Test
    public void listOfAvailableMovesTest(){
        Piece queen = new Queen(PieceColor.Black, new Position("d4"));

        Set<Position> list = queen.listOfAvailableMoves(new HashSet<Position>(), new HashSet<Position>());
        assertNotNull(list);

        assertFalse(list.contains(queen.getPosition()));

        assertEquals(27, list.size());

    }

    @Test
    public void listOfAvailableMovesA1Test(){
        Piece queen = new Queen(PieceColor.Black, new Position("a1"));

        Set<Position> list = queen.listOfAvailableMoves(new HashSet<Position>(), new HashSet<Position>());
        assertNotNull(list);

        assertFalse(list.contains(queen.getPosition()));

        assertEquals(21, list.size());
    }

    @Test
    public void listOfAvailableMovesA1WithPiecesTest(){
        Piece queen = new Queen(PieceColor.Black, new Position("a1"));

        Set<Position> positions = new HashSet<>();
        positions.add(new Position("a2"));
        positions.add(new Position("b1"));
        positions.add(new Position("b2"));

        Set<Position> list = queen.listOfAvailableMoves(positions, new HashSet<Position>());
        assertNotNull(list);

        assertTrue(list.isEmpty());

        list = queen.listOfAvailableMoves(new HashSet<Position>(), positions);
        assertNotNull(list);

        assertEquals(3, list.size());
        assertTrue(list.contains(new Position("a2")));
        assertTrue(list.contains(new Position("b1")));
        assertTrue(list.contains(new Position("b2")));
    }
    
}
