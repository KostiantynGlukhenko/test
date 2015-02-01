package chess;

import chess.pieces.King;
import chess.pieces.Piece;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Administrator on 01.02.2015.
 */
public class PlayerTest {
    @Test
    public void listOfAvailableMovesTest(){
        Map<Position, Piece> map = new HashMap<>();
        Player player = new Player(PieceColor.Black, map, new King(PieceColor.Black, new Position("d4")));
        Map<Position, Set<Position>> list = player.listOfAvailableMoves(new HashSet<Position>());
        assertNotNull(list);
        assertEquals(0, list.size());
    }
}
