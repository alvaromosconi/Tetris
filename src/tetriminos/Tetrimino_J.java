package tetriminos;
import logic.*;
import GUI.TetrisGUI;
import java.util.function.Supplier;

import javax.swing.ImageIcon;

public class Tetrimino_J extends Tetrimino implements Supplier<Tetrimino>{

	public Tetrimino_J(Grid myGrid) {
		
		super(myGrid);
		cellImage = new ImageIcon(TetrisGUI.class.getResource("/img/cells/blueCell.png"));
	}
	
	public boolean initializeTetrimino() {
		
		boolean valid = checkPositions(0, 5, 1, 5, 2, 5, 2, 4);
		assignNewCellsToTetrimino(0, 5, 1, 5, 2, 5, 2, 4);		
		setTetriminoCellsAsTaken();
		
		return valid;
	}

	@Override
	public void moveToLeft() {
		
		refreshPositions();
		boolean valid = false;
		
		switch (currentAngle) {
		
		case 0:
			valid = myGrid.checkMoveHorizontal(xx, xy - 1) &&
					myGrid.checkMoveHorizontal(yx, yy - 1) &&
					myGrid.checkMoveHorizontal(wx, wy - 1);
			break;
			
		case 90:
			valid = myGrid.checkMoveHorizontal(xx, xy - 1) &&
					myGrid.checkMoveHorizontal(yx, yy - 1);
			break;
		
		case 180:
			valid = myGrid.checkMoveHorizontal(xx, xy - 1) &&
					myGrid.checkMoveHorizontal(yx, yy - 1) &&
					myGrid.checkMoveHorizontal(zx, zy - 1);
			break;
			
		case 270:
			valid = myGrid.checkMoveHorizontal(xx, xy - 1) &&
					myGrid.checkMoveHorizontal(wx, wy - 1);
			break;
		}

		if (valid) {
	
			setTetriminoCellsAsFree();
			assignNewCellsToTetrimino(xx, xy - 1, yx, yy - 1, zx, zy - 1, wx, wy - 1);
			setTetriminoCellsAsTaken();
		}
	}

	@Override
	public void moveToRight() {
		
		refreshPositions();
		boolean valid = false;
		
		switch (currentAngle) {
		
		case 0:
			valid = myGrid.checkMoveHorizontal(xx, xy + 1) &&
					myGrid.checkMoveHorizontal(yx, yy + 1) &&
					myGrid.checkMoveHorizontal(zx, zy + 1);
			break;
			
		case 90:
			valid = myGrid.checkMoveHorizontal(xx, xy + 1) &&
					myGrid.checkMoveHorizontal(wx, wy + 1);
			break;
		
		case 180:
			valid = myGrid.checkMoveHorizontal(wx, wy + 1) &&
					myGrid.checkMoveHorizontal(yx, yy + 1) &&
					myGrid.checkMoveHorizontal(zx, zy + 1);
			break;
			
		case 270:
			valid = myGrid.checkMoveHorizontal(zx, zy + 1) &&
					myGrid.checkMoveHorizontal(wx, wy + 1);
			break;
		}
		if (valid) {
			
			setTetriminoCellsAsFree();
			assignNewCellsToTetrimino(xx, xy + 1, yx, yy + 1, zx, zy + 1, wx, wy + 1);
			setTetriminoCellsAsTaken();
		}
		
	}

	@Override
	public void rotate() {
		
		refreshPositions();
		boolean valid = false;

		switch (currentAngle) {
			
			case 0: 				
				valid = myGrid.checkMoveHorizontal(yx, yy - 1) &&
						myGrid.checkMoveHorizontal(wx - 1, wy + 2);
				
				if (valid) {
									
					setTetriminoCellsAsFree();
					
					assignNewCellsToTetrimino(xx, xy - 1, yx, yy - 1, zx - 1, zy, wx - 1, wy + 2);						
					setTetriminoCellsAsTaken();
					
					currentAngle = 90;
				}
					
				break;
					
			
			case 90:
				
				valid = myGrid.checkMoveVertical(zx + 1, zy) &&
						myGrid.checkMoveVertical(wx - 1, wy);
				
				if (valid) {
									
					setTetriminoCellsAsFree();
					assignNewCellsToTetrimino(xx, xy + 1, yx, yy + 1, zx + 1, zy, wx - 1, wy);					
					setTetriminoCellsAsTaken();
					
					currentAngle = 180;
				}
				
				break;
				
		case 180:
				
				valid = myGrid.checkMoveHorizontal(xx + 1, xy - 1) &&
						myGrid.checkMoveVertical(wx + 2, wy);
				
				if (valid) {
									
					setTetriminoCellsAsFree();
					assignNewCellsToTetrimino(xx + 1, xy - 1, yx, yy, zx - 1, zy + 1, wx + 2, wy);	
					setTetriminoCellsAsTaken();
					
					currentAngle = 270;
				}
				
				break;
				
		case 270:
			valid = myGrid.checkMoveHorizontal(zx + 1, zy - 1) &&
					myGrid.checkMoveHorizontal(wx, wy - 2);
			
			if (valid) {
				
				setTetriminoCellsAsFree();
				assignNewCellsToTetrimino(xx - 1, xy + 1, yx, yy, zx + 1, zy - 1, wx, wy - 2);				
				setTetriminoCellsAsTaken();
				
				currentAngle = 0;
			}
			
			break; 
				  
		}
		
	}

	@Override
	public boolean moveDown() {
		
		refreshPositions();
		boolean valid = false;
		
		switch (currentAngle) {
		
		case 0:
			valid = myGrid.checkMoveVertical(zx + 1, zy) &&
					myGrid.checkMoveVertical(wx + 1, wy);
			break;
			
		case 90:
			valid = myGrid.checkMoveVertical(zx + 1, zy) &&
					myGrid.checkMoveVertical(yx + 1, yy) &&
					myGrid.checkMoveVertical(wx + 1, wy);
			break;
		
		case 180:
			valid = myGrid.checkMoveVertical(wx + 1, wy) &&
					myGrid.checkMoveVertical(zx + 1, zy);
			break;
			
		case 270:
			valid = myGrid.checkMoveVertical(xx + 1, xy) &&
					myGrid.checkMoveVertical(yx + 1, yy) &&
					myGrid.checkMoveVertical(wx + 1, wy);
			break;
		}

		if (valid) {
	
			setTetriminoCellsAsFree();
			assignNewCellsToTetrimino(xx + 1, xy, yx + 1, yy, zx + 1, zy, wx + 1, wy);
			setTetriminoCellsAsTaken();
		}
		
		return valid;
		
	}

	@Override
	public void rotateLeft() { 
		
		refreshPositions();
		boolean valid = false;

		switch (currentAngle) {
			
			case 0: 				
				valid = myGrid.checkMoveVertical(xx + 1, xy - 1) &&
						myGrid.checkMoveHorizontal(zx - 1, zy + 1) &&
						myGrid.checkMoveHorizontal(wx, wy + 2);
				if (valid) {
									
					setTetriminoCellsAsFree();
					assignNewCellsToTetrimino(xx + 1, xy - 1, yx, yy, zx - 1, zy + 1, wx, wy + 2);						
					setTetriminoCellsAsTaken();
					
					currentAngle = 270;
				}
					
				break;
					
			
			case 90:
				
				valid = myGrid.checkMoveHorizontal(zx + 1, zy) &&
						myGrid.checkMoveVertical(wx + 1, wy - 2);
				if (valid) {
									
					setTetriminoCellsAsFree();
					assignNewCellsToTetrimino(xx, xy + 1, yx, yy + 1, zx + 1, zy, wx + 1, wy - 2);					
					setTetriminoCellsAsTaken();
					
					currentAngle = 0;
				}
				
				break;
				
		case 180:
				valid = myGrid.checkMoveHorizontal(yx, yy - 1) &&
						myGrid.checkMoveVertical(wx + 1, wy);
				if (valid) {
									
					setTetriminoCellsAsFree();
					assignNewCellsToTetrimino(xx, xy - 1, yx, yy - 1, zx - 1, zy, wx + 1, wy);	
					setTetriminoCellsAsTaken();
					
					currentAngle = 90;
				}
				
				break;
				
		case 270:
			valid = myGrid.checkMoveHorizontal(zx + 1, zy - 1) &&
					myGrid.checkMoveVertical(wx - 2, wy);
			if (valid) {
								
				setTetriminoCellsAsFree();
				assignNewCellsToTetrimino(xx - 1, xy + 1, yx, yy, zx + 1, zy - 1, wx - 2, wy);					
				setTetriminoCellsAsTaken();
				
				currentAngle = 180;
			}
			
			break; 
				  
		}
		
	}
}
