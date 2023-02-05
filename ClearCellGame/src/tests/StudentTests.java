package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.BoardCell;
import model.ClearCellGame;
import model.Game;

/* The following directive executes tests in sorted order */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
	
	/* Remove the following test and add your tests */

		@Test
		public void pub03HorizontalCells() {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			int maxRows = 10, maxCols = 10, strategy = 1;
			Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

			ccGame.setBoardWithColor(BoardCell.BLUE);
			ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
			ccGame.setRowWithColor(1, BoardCell.YELLOW);
			ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);

			String answer = "Before processCell\n\n";
			answer += getBoardStr(ccGame);
			ccGame.processCell(3, 3);
			answer += "\nAfter processCell\n";
			answer += getBoardStr(ccGame);
System.out.println(answer);
System.out.println("After processCell\r\n"
		+ "Board(Rows: 8, Columns: 8)\r\n"
		+ "BBBBBBBB\r\n"
		+ "YYYYYYYR\r\n"
		+ "BBB...BB\r\n"
		+ "........\r\n"
		+ "BBB...BB\r\n"
		+ "BB.B.B.B\r\n"
		+ "B.BB.BB.\r\n"
		+ "........");
			
			}
		
		@Test 
		public void testingGameOver() {
			int maxRows = 8, maxCols = 8, strategy = 1;
			ClearCellGame game = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
			System.out.println(game.isGameOver());
		}
		@Test 
		public void testingGetScore(){
			int maxRows = 8, maxCols = 8, strategy = 1;
			ClearCellGame game = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
			System.out.println(game.getScore());

		}

	/* Support methods */
	private static String getBoardStr(Game game) {
		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();

		String answer = "Board(Rows: " + maxRows + ", Columns: " + maxCols + ")\n";
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++) {
				answer += game.getBoardCell(row, col).getName();
			}
			answer += "\n";
		}

		return answer;
	}
	}
