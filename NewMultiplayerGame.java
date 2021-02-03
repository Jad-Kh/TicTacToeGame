import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewMultiplayerGame extends JFrame {

	private JPanel contentPane;
	private JTextField playerXField;
	private JTextField playerOField;

	public NewMultiplayerGame() {
		setTitle("New Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 350, 345, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel playerXLabel = new JLabel("Player X :");
		playerXLabel.setBounds(10, 11, 140, 14);
		contentPane.add(playerXLabel);
		
		playerXField = new JTextField();
		playerXField.setBounds(10, 36, 140, 20);
		contentPane.add(playerXField);
		playerXField.setColumns(10);
		
		JLabel playerOLabel = new JLabel("Player O :");
		playerOLabel.setBounds(179, 11, 140, 14);
		contentPane.add(playerOLabel);
		
		playerOField = new JTextField();
		playerOField.setColumns(10);
		playerOField.setBounds(179, 36, 140, 20);
		contentPane.add(playerOField);
		
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MultiPlayerXOFrame frame = new MultiPlayerXOFrame(playerXField.getText(),playerOField.getText());
				frame.fillGridMap();
				frame.setTurn('X');
				frame.setVisible(true);
			}
		});
		doneButton.setBounds(118, 87, 89, 23);
		contentPane.add(doneButton);
	}
}
