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

public class NewSinglePlayerGame extends JFrame {

	private JPanel contentPane;
	private JTextField playerXField;

	public NewSinglePlayerGame() {
		setTitle("New Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 350, 175, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel playerXLabel = new JLabel("Player X :");
		playerXLabel.setBounds(52, 11, 89, 14);
		contentPane.add(playerXLabel);
		
		playerXField = new JTextField();
		playerXField.setBounds(10, 36, 140, 20);
		contentPane.add(playerXField);
		playerXField.setColumns(10);
		
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SinglePlayerXOFrame frame = new SinglePlayerXOFrame(playerXField.getText());
				frame.fillGridMap();
				frame.fillAIBoard();
				frame.setTurn('X');
				frame.setVisible(true);
			}
		});
		doneButton.setBounds(31, 87, 89, 23);
		contentPane.add(doneButton);
	}

}
