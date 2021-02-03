import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SinglePlayerXOFrame extends JFrame {

	private JPanel contentPane;
	private JTextField turnField;
	private JTextArea scoreArea;
	private char turn = 'X';
	private char firstTurn = 'X';
	private int scoreX = 0;
	private int scoreO = 0;
	private HashMap<Integer, String> gridMap = new HashMap<Integer, String>(); 
	private char[][] board = new char[3][3]; 
	private String playerO = "AI";
	private int depth = 0;
	
	JLabel grid_1 = new JLabel("");
	JLabel grid_2 = new JLabel("");
	JLabel grid_3 = new JLabel("");
	JLabel grid_4 = new JLabel("");
	JLabel grid_5 = new JLabel("");
	JLabel grid_6 = new JLabel("");
	JLabel grid_7 = new JLabel("");
	JLabel grid_8 = new JLabel("");
	JLabel grid_9 = new JLabel("");
	
	public char getTurn() {
		return turn;
	}
	
	public void setTurn(char turn) {
		this.turn = turn;
	}

	public void fillGridMap() {
		for(int i=1; i<=9; i++) {
			gridMap.put(i, "unchecked");
		}
	}
	
	public void fillAIBoard() {
		int counter = 1;
		for(int i=0; i<3; i++) 	
			for(int j=0; j<3; j++)
			{
				if(gridMap.get(counter).equals("unchecked")) board[i][j] = '_';
				if(gridMap.get(counter).equals("checked X")) board[i][j] = 'X';
				if(gridMap.get(counter).equals("checked O")) board[i][j] = 'O';
				counter++;
			}
	}
	
	public void fillGrid(JLabel grid, int counter, String playerX) {
		grid.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(gridMap.get(counter).equals("unchecked")) {
				if(Character.compare(getTurn(), 'X') == 0) {
					grid.setIcon(new ImageIcon("src\\images\\imageX.jpg"));
					setTurn('O');
					turnField.setText(playerO);
					gridMap.replace(counter, "checked X");
				}			
				stopGame(playerX); 
				fillAIBoard();
				calculateMove();
			}}
		});
	}
	
	public char checkWinner() {
		
		for(int i=1; i<=7; i+=3) 
		{
			if(gridMap.get(i).equals(gridMap.get(i+1)) && gridMap.get(i).equals(gridMap.get(i+2)) && gridMap.get(i+1).equals(gridMap.get(i+2)))
			{
				if(!gridMap.get(i).equals("unchecked"))
				{
					String[] splitter = gridMap.get(i).split(" ");
					if(splitter[1].equals("X")) { return 'X'; }
					if(splitter[1].equals("O")) { return 'O'; }
				}
			}
		}
		
		for(int i=1; i<=3; i+=1) 
		{
			if(gridMap.get(i).equals(gridMap.get(i+3)) && gridMap.get(i).equals(gridMap.get(i+6)) && gridMap.get(i+3).equals(gridMap.get(i+6)))
			{
				if(!gridMap.get(i).equals("unchecked"))
				{
					String[] splitter = gridMap.get(i).split(" ");
					if(splitter[1].equals("X")) { return 'X'; }
					if(splitter[1].equals("O")) { return 'O'; }
				}
			}
		}
		
		if(gridMap.get(1).equals(gridMap.get(5)) && gridMap.get(1).equals(gridMap.get(9)) && gridMap.get(5).equals(gridMap.get(9)))
		{
			if(!gridMap.get(1).equals("unchecked"))
			{
				String[] splitter = gridMap.get(1).split(" ");
				if(splitter[1].equals("X")) { return 'X'; }
				if(splitter[1].equals("O")) { return 'O'; }
			}
		}	
		
		if(gridMap.get(3).equals(gridMap.get(5)) && gridMap.get(3).equals(gridMap.get(7)) && gridMap.get(5).equals(gridMap.get(7)))
		{
			if(!gridMap.get(3).equals("unchecked"))
			{
				String[] splitter = gridMap.get(3).split(" ");
				if(splitter[1].equals("X")) { return 'X'; }
				if(splitter[1].equals("O")) { return 'O'; }
			}
		}	
		
		if(  !gridMap.get(1).equals("unchecked")  &&
			 !gridMap.get(2).equals("unchecked")  &&
			 !gridMap.get(3).equals("unchecked")  &&
			 !gridMap.get(4).equals("unchecked")  &&
			 !gridMap.get(5).equals("unchecked")  &&
			 !gridMap.get(6).equals("unchecked")  &&
			 !gridMap.get(7).equals("unchecked")  &&
			 !gridMap.get(8).equals("unchecked")  &&
			 !gridMap.get(9).equals("unchecked")  )
			
		{    turnField.setText("Tie");            };
		
		return 'N';
	}
	
	public void stopGame(String playerX) {
		
		if(Character.compare(checkWinner(), 'N') != 0)
		{
			for(int i=1; i<=9; i++)
			{
				gridMap.replace(i, "checked " + checkWinner());
			}
			
			if(Character.compare(checkWinner(), 'X') == 0) { turnField.setText("Winner is " + playerX); }
			if(Character.compare(checkWinner(), 'O') == 0) { turnField.setText("Winner is " + playerO); }
		}
	}
	
	public void updateScore(String playerX) {
		
		if(Character.compare(checkWinner(), 'X') == 0)  { scoreX++; }
		if(Character.compare(checkWinner(), 'O') == 0)  { scoreO++; }
		scoreArea.setText(playerX + " : " + scoreX + "\n" + playerO + " : " + scoreO);
	}
	
	public void resetGame() {
		for(int i=1; i<=9; i++) {
			gridMap.replace(i, "unchecked");
		}
	}
	
	public SinglePlayerXOFrame(String playerX) {
		setTitle("Tic Tac Toe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 517, 384);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuItem = new JMenu("Application");
		menuBar.add(menuItem);
		
		JMenuItem newGameItem = new JMenuItem("New  Game");
		menuItem.add(newGameItem);
		
		JMenuItem newPartyItem = new JMenuItem("New  Party");
		newPartyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				NewGame frame = new NewGame();
				frame.setVisible(true);
			}
		});
		menuItem.add(newPartyItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		menuItem.add(exitItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(187, 11, 306, 306);
		contentPane.add(panel);
		panel.setLayout(null);
		
		grid_1.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_1.setBounds(0, 0, 100, 100);
		panel.add(grid_1);
		fillGrid(grid_1, 1, playerX);
		
		grid_2.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_2.setBounds(103, 0, 100, 100);
		panel.add(grid_2);
		fillGrid(grid_2, 2, playerX);
		
		grid_3.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_3.setBounds(206, 0, 100, 100);
		panel.add(grid_3);
		fillGrid(grid_3, 3, playerX);
		
		grid_4.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_4.setBounds(0, 103, 100, 100);
		panel.add(grid_4);
		fillGrid(grid_4, 4, playerX);
		
		grid_5.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_5.setBounds(103, 103, 100, 100);
		panel.add(grid_5);
		fillGrid(grid_5, 5, playerX);
		
		grid_6.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_6.setBounds(206, 103, 100, 100);
		panel.add(grid_6);
		fillGrid(grid_6, 6, playerX);
		
		grid_7.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_7.setBounds(0, 206, 100, 100);
		panel.add(grid_7);
		fillGrid(grid_7, 7, playerX);
		
		grid_8.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_8.setBounds(103, 206, 100, 100);
		panel.add(grid_8);
		fillGrid(grid_8, 8, playerX);
		
		grid_9.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_9.setBounds(206, 206, 100, 100);
		panel.add(grid_9);
		fillGrid(grid_9, 9, playerX);
		
		newGameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grid_1.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
				grid_2.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
				grid_3.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
				grid_4.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
				grid_5.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
				grid_6.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
				grid_7.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
				grid_8.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
				grid_9.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
				
				if(Character.compare(firstTurn, 'X') == 0) { firstTurn = 'O'; }
				else
				if(Character.compare(firstTurn, 'O') == 0) { firstTurn = 'X'; }
				
				if(Character.compare(firstTurn, 'O') == 0) turnField.setText(playerO);
				if(Character.compare(firstTurn, 'X') == 0) turnField.setText(playerX);
				
				setTurn(firstTurn);
				updateScore(playerX);
				resetGame();
			}
		});
		
		scoreArea = new JTextArea();
		scoreArea.setBackground(Color.LIGHT_GRAY);
		scoreArea.setBounds(10, 111, 167, 206);
		scoreArea.setText(playerX + " : " + scoreX + "\n" + playerO + " : " + scoreO);
		contentPane.add(scoreArea);
		
		turnField = new JTextField();
		turnField.setBounds(10, 79, 167, 20);
		turnField.setEditable(false);
		if(Character.compare(getTurn(), 'X') == 0) turnField.setText(playerX);
		if(Character.compare(getTurn(), 'O') == 0) turnField.setText(playerO);
		contentPane.add(turnField);
		turnField.setColumns(10);
	}
	
	public void detectGrid(int position) {
		if(position == 1) { fillGridAI(grid_1, 1);  }
		if(position == 2) { fillGridAI(grid_2, 2);  }
		if(position == 3) { fillGridAI(grid_3, 3);  }
		if(position == 4) { fillGridAI(grid_4, 4);  }
		if(position == 5) { fillGridAI(grid_5, 5);  }
		if(position == 6) { fillGridAI(grid_6, 6);  }
		if(position == 7) { fillGridAI(grid_7, 7);  }
		if(position == 8) { fillGridAI(grid_8, 8);  }
		if(position == 9) { fillGridAI(grid_9, 9);  }
	}
	
	public int boardToPos(int row, int col) {
		if(row == 0 && col == 0) { return 1; } 
		if(row == 0 && col == 1) { return 2; }
		if(row == 0 && col == 2) { return 3; } 
		if(row == 1 && col == 0) { return 4; } 
		if(row == 1 && col == 1) { return 5; } 
		if(row == 1 && col == 2) { return 6; } 
		if(row == 2 && col == 0) { return 7; } 
		if(row == 2 && col == 1) { return 8; } 
		if(row == 2 && col == 2) { return 9; } 
		return 0;
	}
	
	public int evaluate()
	{
	    for (int row = 0; row < 3; row++)
	    {
	        if (board[row][0] == board[row][1] &&
	            board[row][1] == board[row][2])
	        {
	            if (board[row][0] == 'X')
	                return +10;
	            else if (board[row][0] == 'O')
	                return -10;
	        }
	    }
	 
	    for (int col = 0; col < 3; col++)
	    {
	        if (board[0][col] == board[1][col] &&
	            board[1][col] == board[2][col])
	        {
	            if (board[0][col] == 'X')
	                return +10;
	 
	            else if (board[0][col] == 'O')
	                return -10;
	        }
	    }
	 
	    if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
	    {
	        if (board[0][0] == 'X')
	            return +10;
	        else if (board[0][0] == 'O')
	            return -10;
	    }
	 
	    if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
	    {
	        if (board[0][2] == 'X')
	            return +10;
	        else if (board[0][2] == 'O')
	            return -10;
	    }

	    return 0;
	}
	
	public int calculateMove() {
		
		int bestVal = -1000;
	    int row = -1;
	    int col = -1;
		
	    if (Character.compare(board[1][1], '_') == 0) { detectGrid(boardToPos(1, 1)); return boardToPos(1, 1); }
		for (int i = 0; i < 3; i++)
	    {
	        for (int j = 0; j < 3; j++)
	        {
	            if (Character.compare(board[i][j], '_') == 0)
	            {
	                board[i][j] = 'X';
	                int moveVal = minimax(board, 0, false);
	                board[i][j] = '_';
	                if (moveVal > bestVal)
	                {
	                    row = i;
	                    col = j;
	                    bestVal = moveVal;
	                }
	            }
	        }
	    }
		
		detectGrid(boardToPos(row, col));
	    return boardToPos(row, col);
	}
	
	public Boolean isMovesLeft(char board[][])
	{
	    for (int i = 0; i < 3; i++)
	        for (int j = 0; j < 3; j++)
	            if (Character.compare(board[i][j], '_') == 0)
	                return true;
	    return false;
	}
	
	public int minimax(char board[][], int depth, Boolean isMax) {
		
		int winner = evaluate();
		 
	    if (winner == 10 || winner == -10)
	    	return winner;
	    
	    if (isMovesLeft(board) == false)
	        return 0;

	    if (isMax)
	    {
	        int best = -11;
	        for (int i = 0; i < 3; i++)
	        {
	            for (int j = 0; j < 3; j++)
	            {
	                if (Character.compare(board[i][j], '_') == 0)
	                {
	                    board[i][j] = 'X';
	                    best = Math.max(best, minimax(board, depth + 1, !isMax));
	                    board[i][j] = '_';
	                }
	            }
	        }
	        return best;
	    }
	    else
	    {
	        int best = 11;
	        for (int i = 0; i < 3; i++)
	        {
	            for (int j = 0; j < 3; j++)
	            {
	                if (Character.compare(board[i][j], '_') == 0)
	                {
	                    board[i][j] = 'O';
	                    best = Math.min(best, minimax(board, depth + 1, isMax));
	                    board[i][j] = '_';
	                }
	            }
	        }
	        return best;
	    }
	}

	public void fillGridAI(JLabel grid, int counter) {
		grid.setIcon(new ImageIcon("src\\images\\imageO.jpg"));
		setTurn('X');
		turnField.setText(playerO);
		gridMap.replace(counter, "checked O");
	}
}
