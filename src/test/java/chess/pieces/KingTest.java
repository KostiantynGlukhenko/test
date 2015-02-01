package chess.pieces;

import chess.PieceColor;
import chess.Position;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Administrator on 01.02.2015.
 */
public class KingTest {
    @Test
    public void listOfAvailableMovesTest(){
        Piece king = new King(PieceColor.Black, new Position("d4"));

        Set<Position> list = king.listOfAvailableMoves(new HashSet<Position>(), new HashSet<Position>());
        assertNotNull(list);

        assertFalse(list.contains(king.getPosition()));

        assertEquals(8, list.size());

    }

    @Test
    public void listOfAvailableMovesA1Test(){
        Piece king = new King(PieceColor.Black, new Position("a1"));

        Set<Position> list = king.listOfAvailableMoves(new HashSet<Position>(), new HashSet<Position>());
        assertNotNull(list);

        assertFalse(list.contains(king.getPosition()));

        assertEquals(3, list.size());
    }

    @Test
    public void listOfAvailableMovesA1WithPiecesTest(){
        Piece king = new King(PieceColor.Black, new Position("a1"));

        Set<Position> positions = new HashSet<>();
        positions.add(new Position("a2"));
        positions.add(new Position("b1"));
        positions.add(new Position("b2"));

        Set<Position> list = king.listOfAvailableMoves(positions, new HashSet<Position>());
        assertNotNull(list);
        assertTrue(list.isEmpty());

        list = king.listOfAvailableMoves(new HashSet<Position>(), positions);
        assertNotNull(list);

        assertEquals(3, list.size());
        assertTrue(list.contains(new Position("a2")));
        assertTrue(list.contains(new Position("b1")));
        assertTrue(list.contains(new Position("b2")));
    }

    @Test
    public void safeListOfAvailableMovesA1Test(){
        MainPiece king = new King(PieceColor.Black, new Position("a1"));

        Set<Position> positions = new HashSet<>();
        positions.add(new Position("a2"));
        positions.add(new Position("b1"));
        positions.add(new Position("b2"));

        Set<Position> list = king.safeList(positions, new HashMap<Position, Set<Position>>());
        assertNotNull(list);
        assertTrue(list.isEmpty());

        Map<Position, Set<Position>> map = new HashMap<Position, Set<Position>>();
        map.put(new Position("h4"), positions);

        list = king.safeList(new HashSet<Position>(), map);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }


}
