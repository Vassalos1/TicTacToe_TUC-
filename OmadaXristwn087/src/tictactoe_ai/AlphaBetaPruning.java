package tictactoe_ai;

import domain_model.Board;


public class AlphaBetaPruning {
	

	    private static final int MAX_DEPTH = 10;

	    private AlphaBetaPruning() {
	    }

	    public static int miniMax(String p,Board board, int depth, int alpha, int beta,
	            boolean Max) {
	        int value = evaluateBoard(board,depth);

	        // Terminating node (win/lose board configuration) or max depth reached.
	        if (Math.abs(value) > 0 || depth == 0 || board.checkNullBoxes()==0) {
	            return value;
	        }
	    	String opp;
			if(p=="X") {
				opp="O";
			}else {
				opp="X";
			}
	        // Maximising player, find the maximum attainable value.
	        if (Max) {
	            int highestVal = Integer.MIN_VALUE;
	            for (int row = 0; row < 3; row++) {
	                for (int col = 0; col < 3; col++) {
	                    if (board.getBoard()[row][col]==null) {
	                        board.setCell(row, col,p);
	                        highestVal = Math.max(highestVal, miniMax(p,board,
	                                depth - 1, alpha, beta, false));
	                        board.setCell(row, col, null);
	                        alpha = Math.max(alpha, highestVal);
	                        if (alpha >= beta) {
	                            return highestVal;
	                        }
	                    }
	                }
	            }
	            
	            return highestVal;
	            // Minimising player, find the minimum attainable value;
	        } else {
	            int lowestVal = Integer.MAX_VALUE;
	            for (int row = 0; row <3; row++) {
	                for (int col = 0; col < 3; col++) {
	                    if (board.getBoard()[row][col]==null) {
	                        board.setCell(row, col,opp);
	                        lowestVal = Math.min(lowestVal, miniMax(opp,board,
	                                depth - 1, alpha, beta, true));
	                        board.setCell(row, col, null);
	                        beta = Math.min(beta, lowestVal);
	                        if (beta <= alpha) {
	                            return lowestVal;
	                        }
	                    }
	                }
	            }
	            return lowestVal;
	        }
	    }

	    public static int[] getBestMove(String p,Board board) {
	        int[] bestMove = new int[]{-1, -1};
	        int bestValue = Integer.MIN_VALUE;

	        for (int row = 0; row < 3; row++) {
	            for (int col = 0; col <3; col++) {
	                if (board.getBoard()[row][col]==null) {
	                    board.setCell(row, col, p);
	                    int moveValue = miniMax(p,board, MAX_DEPTH, Integer.MIN_VALUE,
	                            Integer.MAX_VALUE, false);
	                    board.setCell(row, col, null);
	                    if (moveValue > bestValue) {
	                        bestMove[0] = row;
	                        bestMove[1] = col;
	                        bestValue = moveValue;
	                    }
	                }
	            }
	        }
	        return bestMove;
	    }

	    private static int evaluateBoard (Board board,int depth) {
	    	if(board.Win("X")) {
	    		return 10 + depth;
	    		
	    	}
	    	if(board.Win("O")) {
	    		return -10-depth;
	    	}
	    	
	    	return 0;
	    }
}
