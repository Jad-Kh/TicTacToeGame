import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewGame extends JFrame {

	private JPanel contentPane;


	public NewGame() {
		setTitle("New Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 450, 345, 105);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton singleButton = new JButton("Single Player");
		singleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				NewSinglePlayerGame frame = new NewSinglePlayerGame();
				frame.setVisible(true);
			}
		});
		singleButton.setBounds(10, 11, 110, 23);
		contentPane.add(singleButton);
		
		JButton multiButton = new JButton("Multiplayer");
		multiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				NewMultiplayerGame frame = new NewMultiplayerGame();
				frame.setVisible(true);
			}
		});
		multiButton.setBounds(209, 11, 110, 23);
		contentPane.add(multiButton);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGame frame = new NewGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
