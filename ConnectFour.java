/*
 * CMPSCI 2261 
 * Project 2
 * Author: Illia Arzamastsev
 * 
 * Connect Four is a two-player board game in which the players alternately drop colored disks into a seven-column, six-row vertically suspended grid. The object of the game is to connect four same-colored disks in a row, column, or diagonal before your opponent can do so. Please see http://www.ludoteka.com/connect-4.html (Links to an external site.)
 * The program should prompt two players to drop a red or yellow disk alternately in a column of their choosing (i.e. 0-6). Whenever a disk is dropped, the program should display the board on the console and determine the status of the game (win, draw, or continue). Here is a sample run:
 *  0   1   2   3   4   5   6

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

-------------------------

Please choose a column (0-6) to drop the RED disk:  0 // where the user entered 0

  0   1   2   3   4   5   6

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

| R  |    |    |    |     |    |    |

-----------------------------

Please choose a column (0-6) to drop the YELLOW disk:  0 // where the user 2 also entered 0

  0   1   2   3   4   5   6

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|  Y  |    |    |    |     |    |    |

| R  |    |    |    |     |    |    |

-----------------------------

Please choose a column (0-6) to drop the RED disk:  1 // where the user 1 entered 1

  0   1   2   3   4   5   6

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

| Y  |    |    |    |     |    |    |

| R  | R  |    |    |     |    |    |

-----------------------------

 Please choose a column (0-6) to drop the YELLOW disk: ....

... And so on until a user (either Red or Yellow) connects 4 of their characters.

The program must contain:
- User input.
- A multidimensional array as the game board.
- Winning conditions: 4 Horizontal pieces of the same kind ,4 vertical pieces of the same kind , or 4 diagonal pieces of the same kind.
- Ensure your program does not terminate if there is bad input and can recover gracefully.
 * */

import java.util.Scanner;

public class ConnectFour {
	//Method to create empty board
	public static String[][] createBoard() {
		//Size7 rows and 15 columns
		String[][] board = new String[7][15];
		
		for (int i =0;i<board.length;i++) {
			for (int j =0;j<board[i].length;j++) {
				//even column-border, odd column between is empty or have number
				if (j% 2 == 0) board[i][j] ="|";
				else board[i][j] = " ";
				//bottom row
				if (i==6) board[i][j]= "-";
			}
		}
		return board;
	}
	//Method to print out board
	public static void printBoard(String[][] board) {
		for (int i =0;i<board.length;i++) {
			for (int j=0;j<board[i].length;j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	//Method that makes lowest empty row-columnn drop Red
	public static void dropRed(String[][] board) {
		System.out.println("Drop a red disk at column (0-6): ");
		Scanner scan = new Scanner (System.in);
		//convert 1-2-3-4-5-6 to 1-3-5-7-9-11-13
		int c = 2*scan.nextInt()+1;
		//loop to find first empty space, then drop R, break afterwards
		for (int i =5;i>=0;i--) {
			if (board[i][c] == " ") {
				board[i][c] = "R";
				break;
			}
		}
	}
	//Method that makes lowest empty row-column drop Yellow
	public static void dropYellow(String[][] board) {
		System.out.println("Drop a yellow disk at column (0-6): ");
	    Scanner scan = new Scanner (System.in);
	    
	    int c = 2*scan.nextInt()+1;
	    //loop to find first empty space, then drop Y, break after
	    for (int i =5;i>=0;i--) {
	    	if (board[i][c] == " ") {
	    		board[i][c] = "Y";
	            break;
	    	}
	    }
	}
	//Method to check winner
	public static String checkWinner(String[][] board) {
		//horizontal line 
		for (int i =0;i<6;i++) {
			 for (int j=0;j<7;j+=2) {
				 if ((board[i][j+1] != " ")
			     && (board[i][j+3] != " ")
			     && (board[i][j+5] != " ")
			     && (board[i][j+7] != " ")
			     && ((board[i][j+1] == board[i][j+3])
			     && (board[i][j+3] == board[i][j+5])
			     && (board[i][j+5] == board[i][j+7])))
					 //return who won
					 return board[i][j+1];
			 }
		}
		//vertical line
		for (int i=1;i<15;i+=2) {
			for (int j =0;j<3;j++) {
				if((board[j][i] != " ")
			    && (board[j+1][i] != " ")
			    && (board[j+2][i] != " ")
			    && (board[j+3][i] != " ")
			    && ((board[j][i] == board[j+1][i])
			    && (board[j+1][i] == board[j+2][i])
			    && (board[j+2][i] == board[j+3][i])))
					return board[j][i];
			}
		}
		//diagonal line, left-up to right-down
		for (int i=0;i<3;i++) {
			for (int j=1;j<9;j+=2) {
				if((board[i][j] != " ")
			    && (board[i+1][j+2] != " ")
			    && (board[i+2][j+4] != " ")
			    && (board[i+3][j+6] != " ")
			    && ((board[i][j] == board[i+1][j+2])
			    && (board[i+1][j+2] == board[i+2][j+4])
			    && (board[i+2][j+4] == board[i+3][j+6])))
					return board[i][j]; 
			}
		}
		//same diagonal
		for (int i=0;i<3;i++) {
			for (int j=7;j<15;j+=2) {
				if((board[i][j] != " ")
			    && (board[i+1][j-2] != " ")
			    && (board[i+2][j-4] != " ")
			    && (board[i+3][j-6] != " ")
			    && ((board[i][j] == board[i+1][j-2])
			    && (board[i+1][j-2] == board[i+2][j-4])
			    && (board[i+2][j-4] == board[i+3][j-6])))
					return board[i][j];
			}
		}
		return null;
	}
	//Main method
	public static void main (String[] args) {
		//create board
		String[][] board = createBoard();
		//set flag for loop to true
		boolean flag = true;
		//counter for whose turn is
		int count = 0;
		//print out game
		printBoard(board);
		//game loop
		while (flag) {
			//red get first, then yellow
			if (count % 2 == 0) dropRed(board);
			else dropYellow(board);
			count++;
			printBoard(board);
			
			if (checkWinner(board) != null) {
				if (checkWinner(board) == "R")
					System.out.println("The red player won.");
				if (checkWinner(board) == "Y")
					System.out.println("The yellow player won.");
				flag = false;
			}
		}
	}
}
