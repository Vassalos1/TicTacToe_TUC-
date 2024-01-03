package domain_model;

public class Board implements Cloneable{
	private String[][] board;
	
	public Board(String[][] b) {
		this.board = b;
	}	
	
	public Board() {
		this.board = new String[3][3];
		//initialiazing board with empty positions
		for(int row=0; row<3; row++) {
			for(int col=0; col<3; col++) {
				this.board[row][col] =null ;
			}
		}
	}
	
	//Getters
	public String[][] getBoard() {
		return board;
	}
	
	//Setters
	public void setBoard(String[][] board) {
		this.board = board;
	}
	
	public void setCell(int row,int col,String marker) {
		this.board[row][col]=marker;
	}
	//Methods
	
	public int checkXs() {
		int counterX=0;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(this.board[i][j]!=null && this.board[i][j].equals("X")) {
					counterX++;
				}
			}
		}
		return counterX;
	}
	
	public int checkOs() {
		int counterO=0;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(this.board[i][j]!=null && this.board[i][j].equals("O")) {
					counterO++;
				}
			}
		}
		return counterO;
	}
	
	public int checkNullBoxes() {
		int counterN=0;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(this.board[i][j]==null) {
					counterN++;
				}
			}
		}
		return counterN;
	}
	
	public int checkFullBoxes() {
		int counterF=0;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(this.board[i][j]!=null) {
					counterF++;
				}
			}
		}
		return counterF;
	}
	
	public boolean Win(String s) {
		//checking the rows first
		if(this.board[0][0]==s && this.board[0][1]==s && this.board[0][2]==s) {
			return true;
		}
		//check 2nd row
		if(this.board[1][0]==s && this.board[1][1]==s && this.board[1][2]==s) {
			return true;
		}
		//check 3rd row
		if(this.board[2][0]==s && this.board[2][1]==s && this.board[2][2]==s) {
			return true;
		}
		//check first col
		if(this.board[0][0]==s && this.board[1][0]==s && this.board[2][0]==s) {
			return true;
		}
		//check 2nd col
		if(this.board[0][1]==s && this.board[1][1]==s && this.board[2][1]==s) {
			return true;
		}
		//check 3rd col
		if(this.board[0][2]==s && this.board[1][2]==s && this.board[2][2]==s) {
			return true;
		}
		//check first diagonal
		if(this.board[0][0]==s && this.board[1][1]==s && this.board[2][2]==s) {
			return true;
		}
		//check 2nd diagonal
		if(this.board[0][2]==s && this.board[1][1]==s && this.board[2][0]==s) {
			return true;
		}
		return false;
	}
	
	//method to check if a board every next move is valid so the game can continue
	public boolean LegalBoard() {
		int numX = checkXs();
		int numO = checkOs();
		if(numO==numX || numX == numO+1) {
			
			if(Win("O")) {
				if(Win("X")) {
					return false;
				}
			}
			else {
				return (numX==numO+1);
			}
			
		}
		if(Win("X") && numX!=numO+1) {
			return true;
	}
		else if(Win("O")) {
			return true;
		}
			return false;
}
	
	//method to check if the board is final: if it has wins or draws 
	public boolean FinalBoard() {
		int finalBoxes = 9;
		if((Win("X")==true || Win("O")==true || checkFullBoxes() == finalBoxes)&& LegalBoard() ) {
			System.out.println("This is a final board.");
			return true;
		}
		else
			return false;
	}
}
