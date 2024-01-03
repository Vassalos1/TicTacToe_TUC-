package test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

import domain_model.Board;

import tictactoe_ai.MiniMax;

public class MiniMaxTest {
	private Board b;
	
	@Test
	public void getBestMoveTest() {
		String[][] stringB3 ={{"X","X","O"},
							{null,"O",null},
							{null,null,null}};
		String x="X";
		b = new Board(stringB3);
		int[] bestMove = MiniMax.BestMove(x,b);
		System.out.println("The Best Move is: "+bestMove[0]+","+bestMove[1]);
		assertArrayEquals(new int[]{2, 0} , bestMove);
}
	}