package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game.
 * We define an empty cell as BoardCell.EMPTY. An empty row is defined as one
 * where every cell corresponds to BoardCell.EMPTY.
 * 
 * @author Department of Computer Science, UMCP
 */

public class ClearCellGame extends Game {

	/**
	 * Defines a board with empty cells. It relies on the super class constructor to
	 * define the board. The random parameter is used for the generation of random
	 * cells. The strategy parameter defines which clearing cell strategy to use
	 * (for this project it will be 1). For fun, you can add your own strategy by
	 * using a value different that one.
	 * 
	 * @param maxRows
	 * @param maxCols
	 * @param random
	 * @param strategy
	 */

	private int maxCols;
	private Random randomValue;
	private int maxRows;
	private int collapseRow;
	private BoardCell[] emptyArray;
	private ArrayList<Integer> emptyRowNum = new ArrayList<Integer>();
	private int score;
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.maxCols = maxCols;
		this.randomValue = random;
		this.maxRows = maxRows;
		score = 0;
		// COME BACK TO THIS!!!
	}

	/**
	 * The game is over when the last board row (row with index board.length -1) is
	 * different from empty row.
	 */
	public boolean isGameOver() {
		int emptyCount = 0;
		for (int i = 0; i < maxCols; i++) {
			if (board[board.length - 1][i] == BoardCell.EMPTY) {
				emptyCount++;

			}
		}
		if (emptyCount != this.maxCols) {
			return true;

		}
		return false;
	}

	public int getScore() {
		return this.score;
	}

	/**
	 * This method will attempt to insert a row of random BoardCell objects if the
	 * last board row (row with index board.length -1) corresponds to the empty row;
	 * otherwise no operation will take place.
	 */
	public void nextAnimationStep() {
//			int emptyCount = 0;
//			for(int i=0; i<maxCols;i++)
//				if(board[board.length-1][i] == BoardCell.EMPTY) {
//					emptyCount++;
//				}
//			 if(emptyCount == this.maxCols) {
//				 
//			 }
//		    }
		if (!isGameOver()) {
//			if(checkEmpty() && emptyRowNum.contains(board.length - 1)){
				for (int row = this.board.length - 1; row > 0; row--) {
					for (int col = 0; col < this.board[row].length; col++) {
						this.board[row][col] = this.board[row - 1][col];
					}
				}
				for (int i = 0; i < maxCols; i++) {
					this.board[0][i] = BoardCell.getNonEmptyRandomBoardCell(randomValue);

				}
			}
		}
//	}

	/**
	 * This method will turn to BoardCell.EMPTY the cell selected and any adjacent
	 * surrounding cells in the vertical, horizontal, and diagonal directions that
	 * have the same color. The clearing of adjacent cells will continue as long as
	 * cells have a color that corresponds to the selected cell. Notice that the
	 * clearing process does not clear every single cell that surrounds a cell
	 * selected (only those found in the vertical, horizontal or diagonal
	 * directions).
	 * 
	 * IMPORTANT: Clearing a cell adds one point to the game's score.<br />
	 * <br />
	 * 
	 * If after processing cells, any rows in the board are empty,those rows will
	 * collapse, moving non-empty rows upward. For example, if we have the following
	 * board (an * represents an empty cell):<br />
	 * <br />
	 * RRR<br />
	 * GGG<br />
	 * YYY<br />
	 * * * *<br/>
	 * <br />
	 * then processing each cell of the second row will generate the following
	 * board<br />
	 * <br />
	 * RRR<br />
	 * YYY<br />
	 * * * *<br/>
	 * * * *<br/>
	 * <br />
	 * IMPORTANT: If the game has ended no action will take place.
	 * 
	 * 
	 */
	public void processCell(int rowIndex, int colIndex) {
//		for (int diag = rowIndex; diag < maxRows; diag++) {
//			for (int diag2 = colIndex; diag2 < maxCols; diag2++) {
//				if (rowIndex == maxRows || colIndex == maxCols) {
//					break;
//				}
//				board[rowIndex][colIndex] = BoardCell.EMPTY;
//			}
//		}

//		System.out.println(board[rowIndex][colIndex+1].equals(board[rowIndex][colIndex]));
		if (!isGameOver()) {
		for (int vert = rowIndex + 1; vert < maxCols; vert++) {
			if (board[vert][colIndex] == (board[rowIndex][colIndex])) {
				board[vert][colIndex] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}
		}
		for (int vert2 = rowIndex - 1; vert2 < maxCols; vert2--) {
			if (board[vert2][colIndex] == (board[rowIndex][colIndex])) {
				board[vert2][colIndex] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}
		}
		for (int horiz = colIndex + 1; horiz < maxCols; horiz++) {
			if (board[rowIndex][horiz] == (board[rowIndex][colIndex])) {
				board[rowIndex][horiz] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}
		}

		for (int horiz2 = colIndex - 1; horiz2 >= 0; horiz2--) {
			if (board[rowIndex][horiz2] == board[rowIndex][colIndex]) {
				board[rowIndex][horiz2] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}

		}
		for (int diag3 = rowIndex - 1, diag4 = colIndex - 1; diag3 >= 0 && diag4 >= 0; diag3--, diag4--) {
			if (board[diag3][diag4] == (board[rowIndex][colIndex])) {
				board[diag3][diag4] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}
		}
		for (int diag3 = rowIndex + 1, diag4 = colIndex + 1; diag3 < maxRows  && diag4 < maxCols; diag3++, diag4++) {
			if (board[diag3][diag4] == board[rowIndex][colIndex]) {
				board[diag3][diag4] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}
		}
		for (int diag3 = rowIndex - 1, diag4 = colIndex + 1; diag3 >= 0 && diag4 < maxCols; diag3--, diag4++) {
			if (board[diag3][diag4] == board[rowIndex][colIndex]) {
				board[diag3][diag4] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}
		}
		for (int diag3 = rowIndex + 1, diag4 = colIndex - 1; diag3 < maxRows && diag4 >= 0; diag3++, diag4--) {
			if (board[diag3][diag4] == board[rowIndex][colIndex]) {
				board[diag3][diag4] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}
		}
		board[rowIndex][colIndex] = BoardCell.EMPTY;

		BoardCell[][] emptyBoard = new BoardCell[maxRows][maxCols];
		int rowNumber = 0;
		if (checkEmpty() == true) {
			for (int i = 0; i < board.length; i++) {
				// Check if soure row is empty
				if ((emptyRowNum.contains(rowNumber))) {
					rowNumber++;
				}

				// if cannot copy from source, then create empty row
				if (rowNumber >= maxRows) {
					//Try ==
					// Add blank row
					for (int j = 0; j < board.length; j++) {
						emptyBoard[i][j] = BoardCell.EMPTY;
					}
					continue;
				} 
				
				// copy row
				emptyBoard[i] = Arrays.copyOf(board[rowNumber], board[i].length);
				rowNumber++;
//				
//				for (int j = 0; j < board.length; j++) {
//					System.out.println(emptyBoard[i][j].getName());
//
//				}

			}
			board = emptyBoard;
		}

	}
	}
	// System.out.println(emptyBoard);

	// collapseCell();

//	// Make a new empty board 
//		private boolean checkEmpty() {
//			int emptyCount = 0;
//			for (collapseRow = 0; collapseRow < maxRows - 1; collapseRow++) {
//				for (int collapseCollumn = 0; collapseCollumn < board[collapseRow].length; collapseCollumn++) {
//		//			for (int collapseCollumn = 0; collapseCollumn < maxCols; collapseCollumn++) {
//
//					if (board[collapseRow][collapseCollumn] == BoardCell.EMPTY) {
//						emptyCount++;
//					}
//
////					else {
//////						System.out.println(collapseRow);
////						collapseRow++;
////		//				collapseCollumn = 0;
////						emptyCount = 0;
////					}
//				}
//			}
//			if (emptyCount == maxCols-1) {
//					System.out.println(collapseRow);
//				return true;
//			}
//			return false;
//		}
//	}
	// Make a new empty board
	private boolean checkEmpty() {
		emptyRowNum.clear();
		int emptyCount = 0;
		int collapseRow;
		for (collapseRow = 0; collapseRow < maxRows - 1; collapseRow++) {
			// for (int collapseCollumn = 0; collapseCollumn < board[collapseRow].length;
			// collapseCollumn++) {
			emptyCount = 0;
			for (int collapseCollumn = 0; collapseCollumn < maxCols; collapseCollumn++) {

				if (board[collapseRow][collapseCollumn] == BoardCell.EMPTY) {
					emptyCount++;
				}

				if (emptyCount == maxCols) {
					emptyRowNum.add(collapseRow);
					// System.out.println(collapseRow);
					break;
				}
			}
		}
		if (emptyRowNum.size() > 0) {
			System.out.println(emptyRowNum);
//				System.out.println(emptyRowNum);

			return true;
		}
		return false;
	}
}

// Make a temporary new board 
//public void collapseCell(){
//	int emptyCount = 0;
//	for (int collapseRow = 0; collapseRow < maxRows; collapseRow++) {
//		for (int collapseCollumn = 0; collapseCollumn < maxCols; collapseCollumn++) {
//			if(board[collapseRow][collapseCollumn].equals(BoardCell.EMPTY)) {
//				emptyCount++;
//				if(emptyCount==maxCols) {
////					System.out.println("Row is Empty at Row:"+collapseRow);
//					BoardCell[] selectedRow = board[collapseRow+1];
//					board[collapseRow] = selectedRow;
//					
//				}
//			}
//			else {
//				collapseRow++;
//				collapseCollumn = 0;
//				emptyCount = 0;
//				
//			}
//		}
//	}
//			
//}
//	private BoardCell[][] collapseCell() {
//		BoardCell[][]emptyBoard = new BoardCell[maxRows][maxCols];
//		int rowNumber = 0;
//				if (checkEmpty() == true) {
//								for(int i=0,j=0;i<board.length && j<board.length;i++,j++) {
//									if(i==1) {
//										j++;
//									}
//								
//									else {
//										if(rowNumber < 6) {
//								emptyBoard[i] = Arrays.copyOf(board[j], board[j].length);
//								rowNumber++;
//										}
//									}
//						}
//								ArrayList<BoardCell>emptyArrayList = new ArrayList<BoardCell>();
//								for(int i=0; i<maxCols;i++) {
//									emptyArrayList.add(null);
//								}
//							    emptyArray = new BoardCell[emptyArrayList.size()];
//								emptyArray = emptyArrayList.toArray(emptyArray);
////							for()
//								return emptyBoard;
//
//				}
//						
//					//		System.out.println(emptyBoard);
//							return null;
//				
//				}

//		ArrayList<BoardCell>emptyArrayList = new ArrayList<BoardCell>();
//		for(int i=0; i<maxCols;i++) {
//			emptyArrayList.add(BoardCell.EMPTY);
//		}
//		emptyArray = emptyArrayList.toArray();
//	    emptyArray = new BoardCell[emptyArrayList.size()];
//		emptyArray = emptyArrayList.toArray(emptyArray);
//		
//		System.out.println(board[row][col] == BoardCell.EMPTY);
//		for(collapseRow = 0;collapseRow < maxRows; collapseRow++) {
//			if (board[collapseRow] == (emptyArray)) {
//			return true;
//
//		}
//			else {
//		System.out.println(collapseRow);
//		collapseRow++;
//	}
//		for(collapseRow = 0; )
//}
// false;

//}
//make a nested for loop that checks if the board has any empty rows. If there is an empty row copy the orginal board into the empty one 
//to make it seem like it moved up.

//	if (!isGameOver()) {
//		for (int row = this.board.length - 1; row > 0; row--) {
//			for (int col = 0; col < this.board[row].length; col++) {
//				this.board[row][col] = this.board[row - 1][col];
//			}
//		}
//		for (int i = 0; i < maxCols; i++) {
//			this.board[0][i] = BoardCell.getNonEmptyRandomBoardCell(randomValue);
//
//		}
//	}
