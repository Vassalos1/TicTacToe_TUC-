package tictactoe_ai;
import domain_model.*;
public class MiniMax {
	private static final int MAX_DEPTH = 9;
	
	public MiniMax(){
		
	}
	
	
	
	public static int miniMax(String p,Board board,int depth,boolean Max) {
		 int score = evaluateBoard(board,depth);
		 if (Math.abs(score) > 0 || depth == 0 || board.checkNullBoxes()==0) {
	            return score;
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
	                                depth - 1, false));
	                        board.setCell(row, col, null);
	                        
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
	                                depth - 1, true));
	                        board.setCell(row, col, null);
	                       
	                         
	                        }
	                    }
	                }
	            
	            return lowestVal;
	        }
	        }



	public static int[] BestMove(String p,Board board) {
		int[] bestMove=new int[] {-1,-1};
		
		int	maxValue=Integer.MIN_VALUE;
		for(int row=0;row<3;row++) {
			for(int col=0;col<3;col++) {
				if(board.getBoard()[row][col]==null) {
					
					board.setCell(row, col,p);
					int moveVal=miniMax(p,board,MAX_DEPTH,false);
					board.setCell(row, col,"-");
					if(moveVal>maxValue) {
						bestMove[0]=row;
						bestMove[1]=col;
						maxValue=moveVal;
						
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
