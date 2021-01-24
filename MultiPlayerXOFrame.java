import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MultiPlayerXOFrame extends JFrame {

	private JPanel contentPane;
	private JTextField turnField;
	private JTextArea scoreArea;
	private char turn = 'X';
	private char firstTurn = 'X';
	private int scoreX = 0;
	private int scoreO = 0;
	private HashMap<Integer, String> gridMap = new HashMap<Integer, String>();   
	
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
	
	public void fillGrid(JLabel grid, int counter, String playerX, String playerO) {
		grid.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(gridMap.get(counter).equals("unchecked")) {
				if(Character.compare(getTurn(), 'X') == 0) {
					grid.setIcon(new ImageIcon("src\\images\\imageX.jpg"));
					setTurn('O');
					turnField.setText(playerO);
					gridMap.replace(counter, "checked X");
				}
				else
				if(Character.compare(getTurn(), 'O') == 0) {
					grid.setIcon(new ImageIcon("src\\images\\imageO.jpg"));
					setTurn('X');
					turnField.setText(playerX);
					gridMap.replace(counter, "checked O");
				}
				stopGame(playerX, playerO); 
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
	
	public void stopGame(String playerX, String playerO) {
		
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
	
	public void updateScore(String playerX, String playerO) {
		
		if(Character.compare(checkWinner(), 'X') == 0)  { scoreX++; }
		if(Character.compare(checkWinner(), 'O') == 0)  { scoreO++; }
		scoreArea.setText(playerX + " : " + scoreX + "\n" + playerO + " : " + scoreO);
	}
	
	public void resetGame() {
		for(int i=1; i<=9; i++) {
			gridMap.replace(i, "unchecked");
		}
	}
	
	public MultiPlayerXOFrame(String playerX, String playerO) {
		setTitle("Tic Tac Toe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 384);
		
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
		
		JLabel grid_1 = new JLabel("");
		grid_1.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_1.setBounds(0, 0, 100, 100);
		panel.add(grid_1);
		fillGrid(grid_1, 1, playerX, playerO);
		
		JLabel grid_2 = new JLabel("");
		grid_2.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_2.setBounds(103, 0, 100, 100);
		panel.add(grid_2);
		fillGrid(grid_2, 2, playerX, playerO);
		
		JLabel grid_3 = new JLabel("");
		grid_3.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_3.setBounds(206, 0, 100, 100);
		panel.add(grid_3);
		fillGrid(grid_3, 3, playerX, playerO);
		
		JLabel grid_4 = new JLabel("");
		grid_4.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_4.setBounds(0, 103, 100, 100);
		panel.add(grid_4);
		fillGrid(grid_4, 4, playerX, playerO);
		
		JLabel grid_5 = new JLabel("");
		grid_5.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_5.setBounds(103, 103, 100, 100);
		panel.add(grid_5);
		fillGrid(grid_5, 5, playerX, playerO);
		
		JLabel grid_6 = new JLabel("");
		grid_6.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_6.setBounds(206, 103, 100, 100);
		panel.add(grid_6);
		fillGrid(grid_6, 6, playerX, playerO);
		
		JLabel grid_7 = new JLabel("");
		grid_7.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_7.setBounds(0, 206, 100, 100);
		panel.add(grid_7);
		fillGrid(grid_7, 7, playerX, playerO);
		
		JLabel grid_8 = new JLabel("");
		grid_8.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_8.setBounds(103, 206, 100, 100);
		panel.add(grid_8);
		fillGrid(grid_8, 8, playerX, playerO);
		
		JLabel grid_9 = new JLabel("");
		grid_9.setIcon(new ImageIcon("src\\images\\imageEmpty.jpg"));
		grid_9.setBounds(206, 206, 100, 100);
		panel.add(grid_9);
		fillGrid(grid_9, 9, playerX, playerO);
		
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
				updateScore(playerX, playerO);
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
}
