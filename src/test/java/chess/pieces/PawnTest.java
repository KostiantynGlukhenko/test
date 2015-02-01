package chess.pieces;

import chess.PieceColor;
import chess.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.*;

/**
 * Created by Administrator on 01.02.2015.
 */
public class PawnTest {

    private Pawn a2pawn;
    private Pawn b2pawn;
    private Pawn h2pawn;

    @Before
    public void setup(){
        a2pawn = new Pawn(PieceColor.White, new Position("a2"));
        b2pawn = new Pawn(PieceColor.White, new Position("b2"));
        h2pawn = new Pawn(PieceColor.White, new Position("h2"));
    }

    @Test
    public void listWithoutCapturing(){
        Set<Position> position = a2pawn.listOfAvailableMoves(Collections.EMPTY_SET, Collections.EMPTY_SET);

        assertNotNull(position);
        assertEquals(2, position.size());
        assertTrue(position.contains(new Position("a3")));
        assertTrue(position.contains(new Position("a4")));

    }

    @Test
    public void listWith1Capturing(){
        Set<Position> enemyPieces = new HashSet<Position>();
        enemyPieces.add(new Position("b3"));
        Set<Position> position = a2pawn.listOfAvailableMoves(Collections.EMPTY_SET, enemyPieces);

        assertNotNull(position);
        assertEquals(3, position.size());
        assertTrue(position.contains(new Position("a3")));
        assertTrue(position.contains(new Position("a4")));
        assertTrue(position.contains(new Position("b3")));
    }

    @Test
    public void listWith2Capturing(){
        Set<Position> enemyPieces = new HashSet<Position>();
        enemyPieces.add(new Position("a3"));
        enemyPieces.add(new Position("c3"));
        Set<Position> position = b2pawn.listOfAvailableMoves(Collections.EMPTY_SET, enemyPieces);

        assertNotNull(position);
        assertEquals(4, position.size());
        assertTrue(position.contains(new Position("b3")));
        assertTrue(position.contains(new Position("b4")));
        assertTrue(position.contains(new Position("a3")));
        assertTrue(position.contains(new Position("c3")));
    }

    @Test
    public void listWith1CapturingAndGridCheck(){
        Set<Position> enemyPieces = new HashSet<Position>();
        enemyPieces.add(new Position("g3"));
        enemyPieces.add(new Position("i3"));
        Set<Position> position = h2pawn.listOfAvailableMoves(Collections.EMPTY_SET, enemyPieces);

        assertNotNull(position);
        assertEquals(3, position.size());
        assertTrue(position.contains(new Position("h3")));
        assertTrue(position.contains(new Position("h4")));
        assertTrue(position.contains(new Position("g3")));
    }

    @Test
    public void setPositionTest(){
        a2pawn.setPosition(new Position("a1"));
        assertEquals(new Position("a1"), a2pawn.getPosition());
        assertFalse(a2pawn.isFirstMove());
    }
}
