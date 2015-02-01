package chess.pieces;

import chess.PieceColor;
import chess.Position;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.*;

/**
 * Created by Administrator on 01.02.2015.
 */
public class KnightTest {
    @Test
    public void listOfAvailableMovesTest(){
        Piece knight = new Knight(PieceColor.Black, new Position("d4"));

        Set<Position> list = knight.listOfAvailableMoves(new HashSet<Position>(), new HashSet<Position>());
        assertNotNull(list);

        assertFalse(list.contains(knight.getPosition()));

        assertEquals(8, list.size());
        assertTrue(list.contains(new Position("b3")));
        assertTrue(list.contains(new Position("c2")));
        assertTrue(list.contains(new Position("b5")));
        assertTrue(list.contains(new Position("c6")));
        assertTrue(list.contains(new Position("f3")));
        assertTrue(list.contains(new Position("e2")));
        assertTrue(list.contains(new Position("f5")));
        assertTrue(list.contains(new Position("e6")));

    }

    @Test
    public void listOfAvailableMovesA1Test(){
        Piece knight = new Knight(PieceColor.Black, new Position("a1"));

        Set<Position> list = knight.listOfAvailableMoves(new HashSet<Position>(), new HashSet<Position>());
        assertNotNull(list);

        assertFalse(list.contains(knight.getPosition()));

        assertEquals(2, list.size());
    }

    @Test
    public void listOfAvailableMovesA1WithPiecesTest(){
        Piece knight = new Knight(PieceColor.Black, new Position("a1"));

        Set<Position> positions = new HashSet<>();
        positions.add(new Position("b3"));
        positions.add(new Position("c2"));

        Set<Position> list = knight.listOfAvailableMoves(positions, new HashSet<Position>());
        assertNotNull(list);

        assertTrue(list.isEmpty());

        list = knight.listOfAvailableMoves(new HashSet<Position>(), positions);
        assertNotNull(list);

        assertEquals(2, list.size());
        assertTrue(list.contains(new Position("b3")));
        assertTrue(list.contains(new Position("c2")));
    }

}
