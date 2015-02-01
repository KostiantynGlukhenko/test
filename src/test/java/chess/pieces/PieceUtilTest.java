package chess.pieces;

import chess.Position;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.*;


/**
 * Created by Administrator on 01.02.2015.
 */
public class PieceUtilTest {

    @Test
    public void checkMoveAndAdd(){
        Set<Position> result = new HashSet<Position>();

        PieceUtil.checkMoveAndAdd(result, null, Collections.EMPTY_SET, Collections.EMPTY_SET);
        assertTrue(result.isEmpty());

        Set<Position> pieces = new HashSet<Position>();
        pieces.add(new Position("a1"));
        PieceUtil.checkMoveAndAdd(result, new Position("a1"), pieces, Collections.EMPTY_SET);
        assertTrue(result.isEmpty());

        PieceUtil.checkMoveAndAdd(result, new Position("a1"), Collections.EMPTY_SET, pieces);
        assertTrue(result.isEmpty());

        PieceUtil.checkMoveAndAdd(result, new Position("b2"), Collections.EMPTY_SET, Collections.EMPTY_SET);
        assertEquals(1, result.size());
        assertTrue(result.contains(new Position("b2")));

        PieceUtil.checkMoveAndAdd(result, new Position("c2"), pieces, Collections.EMPTY_SET);
        assertEquals(2, result.size());
        assertTrue(result.contains(new Position("b2")));
        assertTrue(result.contains(new Position("c2")));
    }

    @Test
    public void checkCaptureAndAdd(){
        Set<Position> result = new HashSet<Position>();

        PieceUtil.checkCaptureAndAdd(result, null, Collections.EMPTY_SET);
        assertTrue(result.isEmpty());

        PieceUtil.checkCaptureAndAdd(result, new Position("a2"), Collections.EMPTY_SET);
        assertTrue(result.isEmpty());

        Set<Position> pieces = new HashSet<Position>();
        pieces.add(new Position("a1"));
        PieceUtil.checkCaptureAndAdd(result, new Position("a1"), pieces);
        assertEquals(1, result.size());
        assertTrue(result.contains(new Position("a1")));
    }

    @Test
    public void checkAndAddTest(){
        Set<Position> result = new HashSet<Position>();

        assertFalse(PieceUtil.checkAndAdd(result, null, Collections.EMPTY_SET, Collections.EMPTY_SET));
        assertTrue(result.isEmpty());

        Set<Position> pieces = new HashSet<Position>();
        pieces.add(new Position("a1"));
        assertFalse(PieceUtil.checkAndAdd(result, new Position("a1"), pieces, Collections.EMPTY_SET));
        assertTrue(result.isEmpty());

        assertFalse(PieceUtil.checkAndAdd(result, new Position("a1"), Collections.EMPTY_SET, pieces));
        assertEquals(1, result.size());
        assertTrue(result.contains(new Position("a1")));
        result.clear();

        assertTrue(PieceUtil.checkAndAdd(result, new Position("b2"), Collections.EMPTY_SET, Collections.EMPTY_SET));
        assertEquals(1, result.size());
        assertTrue(result.contains(new Position("b2")));

        assertTrue(PieceUtil.checkAndAdd(result, new Position("c2"), pieces, Collections.EMPTY_SET));
        assertEquals(2, result.size());
        assertTrue(result.contains(new Position("b2")));
        assertTrue(result.contains(new Position("c2")));
    }



    @Test
    public void checkAndAddIterable(){
        Set<Position> result = new HashSet<Position>();

        Set<Position> pieces = new HashSet<Position>();
        pieces.add(new Position("a1"));
        PieceUtil.checkAndAddIterable(result, new Position("a1"), pieces, Collections.EMPTY_SET, 1, 1);
        assertTrue(result.isEmpty());

        PieceUtil.checkAndAddIterable(result, new Position("a1"), Collections.EMPTY_SET, pieces, -1, 0);
        assertEquals(7, result.size());
        result.clear();

        PieceUtil.checkAndAddIterable(result, new Position("b2"), Collections.EMPTY_SET, Collections.EMPTY_SET, 1, 1);
        assertEquals(1, result.size());

        PieceUtil.checkAndAddIterable(result, new Position("c1"), pieces, Collections.EMPTY_SET, 0, -1);
        assertEquals(8, result.size());
    }
}
