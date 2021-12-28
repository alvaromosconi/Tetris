package GUI;
import logic.Logic;
import launcher.Launcher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class TetrisGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private final static int rows = 21;
	private final static int columns = 10;
	private Logic myLogic;
	
	private ImageIcon grassIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TetrisGUI.class.getResource("/img/cells/emptyCell.png")));
	private JPanel graphicPanel;
	private JPanel panel = new JPanel(); 
	private JLabel[][] graphicCells;
	private JLabel lblGameOver;
	private JLabel lblLines;
	private JLabel lblLinesText;
	private JLabel lblNextTetrimino;
	private JLabel lblScore;
	private JLabel lblScoreText;
	private JLabel lblTime = new JLabel("0:0");
	private JLabel lblSpeed;
	private JLabel lblfinalResults;
	private JButton btnClose;
	private JButton btnRestart;
	
	public TetrisGUI() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(TetrisGUI.class.getResource("/img/gameAssets/tetris.png")));
	
		setUpWindow();
		setUpGrid();
		myLogic = new Logic(this);
		setUpLabels();
		setUpButtons();
	
		refreshNextTetriminoLabel(myLogic.getNameOfNextTetrimino());
		
		super.setVisible(true);
		super.pack();
        setLocationRelativeTo(null);
        
        startListener();

        this.setFocusable(true);
	}

	private void setUpWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setForeground(Color.BLACK);
        setMinimumSize(new Dimension(600, 800)); 
		setUndecorated(true);
		setResizable(false);

	}
	
	private void setUpGrid() {
		
		graphicPanel = new JPanel(new GridLayout(rows, columns, 0,0));
		graphicPanel.setBounds(30,36, 300,630);
		graphicCells = new JLabel[rows][columns];
		
		for (int i = 0; i < rows; i++) 
			for (int j = 0; j < columns; j++) {
				graphicCells[i][j] = new JLabel();
				graphicCells[i][j].setIcon(grassIcon);
				graphicPanel.add(graphicCells[i][j]);
			}
		
	
		setContentPane(panel);
        this.getContentPane().setBackground(Color.BLACK);
		panel.setLayout(null);
		
		lblGameOver = new JLabel("");
		lblGameOver.setVerticalAlignment(SwingConstants.TOP);
		lblGameOver.setIcon(new ImageIcon(TetrisGUI.class.getResource("/img/gameAssets/gameOverSign.png")));
		lblGameOver.setBounds(10, 307, 565, 130);
		lblGameOver.setVisible(false);
		
		
		lblfinalResults = new JLabel("", SwingConstants.CENTER);
		lblfinalResults.setForeground(Color.RED);
		lblfinalResults.setFont(new Font("Agency FB", Font.PLAIN, 40));
		lblfinalResults.setBackground(Color.BLACK);
		lblfinalResults.setBounds(30, 459, 545, 100);
		panel.add(lblfinalResults);
		panel.add(lblGameOver);
		panel.add(graphicPanel);
  
	}
	
	private void setUpLabels() {
	
		lblLines = new JLabel("0");
		lblLines.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblLines.setForeground(Color.WHITE);
		lblLines.setBounds(431, 439, 46, 33);
		
	    lblLinesText = new JLabel("Lines");
	    lblLinesText.setForeground(Color.WHITE);
	    lblLinesText.setFont(new Font("Agency FB", Font.PLAIN, 27));
	    lblLinesText.setBounds(373, 380, 125, 57);
	    
		lblNextTetrimino = new JLabel("");
	    lblNextTetrimino.setBounds(398, 163, 100, 100);
	    
	    JLabel lblNextTetriminoText = new JLabel("Next");
	    lblNextTetriminoText.setForeground(Color.WHITE);
	    lblNextTetriminoText.setFont(new Font("Agency FB", Font.PLAIN, 27));
	    lblNextTetriminoText.setBounds(373, 95, 125, 57);
	    	
		lblScore = new JLabel("0");
		lblScore.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblScore.setForeground(Color.WHITE);
		lblScore.setBounds(431, 342, 46, 33);
		
		lblScoreText = new JLabel("Score");
	    lblScoreText.setFont(new Font("Agency FB", Font.PLAIN, 27));
	    lblScoreText.setForeground(Color.WHITE);
	    lblScoreText.setBounds(373, 274, 125, 57);
	    
	    lblTime.setForeground(Color.WHITE);
	    lblTime.setFont(new Font("Agency FB", Font.PLAIN, 24));
	    lblTime.setBounds(415, 551, 46, 33);
	    	                
	    JLabel lblTimeText = new JLabel("Time");
	    lblTimeText.setForeground(Color.WHITE);
	    lblTimeText.setFont(new Font("Agency FB", Font.PLAIN, 27));
	    lblTimeText.setBounds(373, 483, 125, 57);
	 	panel.add(lblLines);
	    panel.add(lblLinesText);
	    panel.add(lblNextTetrimino);
		panel.add(lblNextTetriminoText);
	 	panel.add(lblScore);
	 	panel.add(lblScoreText);
	 	panel.add(lblTime);
	 	panel.add(lblTimeText);
	 	
	 	JLabel lblLArrow = new JLabel("");
	 	lblLArrow.setIcon(new ImageIcon(TetrisGUI.class.getResource("/img/gameAssets/leftArrow.png")));
	 	lblLArrow.setBounds(31, 689, 36, 34);
	 	panel.add(lblLArrow);
	 	
	 	JLabel lblRArrow = new JLabel("");
	 	lblRArrow.setIcon(new ImageIcon(TetrisGUI.class.getResource("/img/gameAssets/rightArrow.png")));
	 	lblRArrow.setBounds(97, 689, 46, 34);
	 	panel.add(lblRArrow);
	 	
	 	JLabel lblZKey = new JLabel("");
	 	lblZKey.setIcon(new ImageIcon(TetrisGUI.class.getResource("/img/gameAssets/zKey.png")));
	 	lblZKey.setBounds(203, 689, 46, 34);
	 	panel.add(lblZKey);
	 	
	 	JLabel lblXKey = new JLabel("");
	 	lblXKey.setIcon(new ImageIcon(TetrisGUI.class.getResource("/img/gameAssets/xKey.png")));
	 	lblXKey.setBounds(237, 689, 46, 34);
	 	panel.add(lblXKey);
	 	
	 	JLabel lblLArrow_1 = new JLabel("");
	 	lblLArrow_1.setIcon(new ImageIcon(TetrisGUI.class.getResource("/img/gameAssets/downArrow.png")));
	 	lblLArrow_1.setBounds(65, 689, 54, 34);
	 	panel.add(lblLArrow_1);
	 	
	 	JLabel lblMove = new JLabel("Move");
	 	lblMove.setForeground(Color.WHITE);
	 	lblMove.setFont(new Font("Agency FB", Font.PLAIN, 24));
	 	lblMove.setBounds(141, 690, 46, 33);
	 	panel.add(lblMove);
	 	
	 	JLabel lblRotate = new JLabel("Rotate");
	 	lblRotate.setForeground(Color.WHITE);
	 	lblRotate.setFont(new Font("Agency FB", Font.PLAIN, 24));
	 	lblRotate.setBounds(276, 689, 54, 33);
	 	panel.add(lblRotate);
	}
	
	private void setUpButtons() {
		
		btnClose = new JButton("");
	 	btnClose.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent e) {
	 			
	 			dispose();
	    		System.exit(EXIT_ON_CLOSE);
	 		}
	 	});
	 	btnClose.setIcon(new ImageIcon(TetrisGUI.class.getResource("/img/gameAssets/close.png")));
	 	btnClose.setBackground(Color.BLACK);
	 	btnClose.setBorder(null);
	 	btnClose.setBounds(550, 21, 40, 42);
	 	panel.add(btnClose);
		
		btnRestart = new JButton("");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restartGame();
				panel.requestDefaultFocus();
            }

            private void restartGame() {
				lblScore.setText("0");
				lblLines.setText("0");
				lblSpeed.setText("0");
				lblGameOver.setVisible(false);
				lblfinalResults.setVisible(false);
				myLogic.restartLogic();
				refreshNextTetriminoLabel(myLogic.getNameOfNextTetrimino());
				btnRestart.setVisible(false);
				panel.requestFocus();
				
			}
		});
		btnRestart.setIcon(new ImageIcon(TetrisGUI.class.getResource("/img/gameAssets/retry.png")));
		btnRestart.setBorder(null);
		btnRestart.setBackground(Color.BLACK);
		btnRestart.setBounds(499, 21, 40, 42);
		btnRestart.setVisible(false);
		panel.add(btnRestart);
		
		JLabel lblSpeedText = new JLabel("Speed");
		lblSpeedText.setForeground(Color.WHITE);
		lblSpeedText.setFont(new Font("Agency FB", Font.PLAIN, 27));
		lblSpeedText.setBounds(373, 595, 125, 57);
		panel.add(lblSpeedText);
		
		lblSpeed = new JLabel("0");
		lblSpeed.setForeground(Color.WHITE);
		lblSpeed.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblSpeed.setBounds(431, 663, 46, 33);
		panel.add(lblSpeed);

	}

	public void refreshNextTetriminoLabel(String nextTetriminoName) {
	 
		lblNextTetrimino.setIcon(new ImageIcon(TetrisGUI.class.getResource("/img/tetriminos/"+nextTetriminoName.charAt(nextTetriminoName.length()-1)+".png")));
	}	
	
	private void startListener() {
		
		this.addKeyListener(new KeyListener(){
			@Override
		
			public void keyPressed(KeyEvent e) {                 
				
				switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT: { myLogic.moveToLeft(); break;}
                case KeyEvent.VK_RIGHT: { myLogic.moveToRight(); break;}
                case KeyEvent.VK_Z: { myLogic.rotateLeft(); break; }
                case KeyEvent.VK_X: { myLogic.rotate(); break; }
                case KeyEvent.VK_DOWN: { myLogic.moveToDown(); break; }
				}
              }

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}


	 	});
	}
	
	public void draw(int y, int x, ImageIcon image) {
		
		if (image != null) 
			graphicCells[x][y].setIcon(image);
		else 
			graphicCells[x][y].setIcon(grassIcon);
	}

	public void refreshDataGUI(int s, int l) {
		
		lblScore.setText(String.valueOf(s));
		lblLines.setText(String.valueOf(l));
	}
	
	public void refreshTime(String time) {
		
		this.lblTime.setText(time);
	}
	
	public void refreshSpeed(int sp) {
		
		this.lblSpeed.setText(String.valueOf(sp));
	}

	public void showGameOver() {
		
		lblGameOver.setVisible(true);
		btnRestart.setVisible(true);
	}
	
	public void showFinalResults(int s, int l, String time) {
		
		lblfinalResults.setOpaque(true);
		lblfinalResults.setText("Score: " + String.valueOf(s) + "    Lines: " + String.valueOf(l) + "    Time: " + time );
		lblfinalResults.setVisible(true);
	}
	
	public int getRows() {
		
		return rows;	
	}
	
	public int getColumns() {
		
		return columns;
	}
}
