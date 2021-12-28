package tetriminos;
import logic.*;
import GUI.TetrisGUI;
import javax.swing.ImageIcon;

public class Tetrimino_T extends Tetrimino {


	public Tetrimino_T(Grid myGrid) {
		
		super(myGrid);			
		cellImage = new ImageIcon(TetrisGUI.class.getResource("/img/cells/violetCell.png"));
	}

	public boolean initializeTetrimino() {
		
		boolean valid = checkPositions(0, 4, 1, 3, 1, 4, 1, 5);
		assignNewCellsToTetrimino(0, 4, 1, 3, 1, 4, 1, 5);
		setTetriminoCellsAsTaken();
		
		return valid;
	}
	
	@Override
	public void moveToLeft() {
		refreshPositions();
		boolean validMove = false;
		switch(currentAngle) {
			case 0: 
				
				validMove = ( myGrid.checkMoveHorizontal(xx, xy-1) &&   myGrid.checkMoveHorizontal(yx, yy-1));
				break;
			case 90:
				validMove = ( myGrid.checkMoveHorizontal(zx, zy-1) &&   myGrid.checkMoveHorizontal(yx, yy-1) 
							&& myGrid.checkMoveHorizontal(wx, wy-1));
				break;
			case 180: 
				validMove = ( myGrid.checkMoveHorizontal(xx, xy-1) &&   myGrid.checkMoveHorizontal(wx, wy-1));
				break;
			case 270:
				validMove = ( myGrid.checkMoveHorizontal(xx, xy-1) &&   myGrid.checkMoveHorizontal(yx, yy-1) 
						&& myGrid.checkMoveHorizontal(wx, wy-1));
				break;
		}	
		if (validMove) {
			setTetriminoCellsAsFree();
			assignNewCellsToTetrimino(xx, xy - 1, yx, yy - 1, zx, zy - 1, wx, wy - 1);
			setTetriminoCellsAsTaken();
		}
		
	}

	@Override
	public void moveToRight() {
		refreshPositions();
		boolean validMove = false;
		switch(currentAngle) {
			case 0: 
				
				validMove = ( myGrid.checkMoveHorizontal(xx, xy+1) &&  myGrid.checkMoveHorizontal(wx, wy+1));
				break;
			case 90:
				validMove = ( myGrid.checkMoveHorizontal(xx, xy+1) && myGrid.checkMoveHorizontal(yx, yy+1) 
							&& myGrid.checkMoveHorizontal(wx, wy+1));
				break;
			case 180: 
				validMove = ( myGrid.checkMoveHorizontal(xx, xy+1) && myGrid.checkMoveHorizontal(yx, yy+1));
				break;
			case 270:
				validMove = ( myGrid.checkMoveHorizontal(zx, zy+1) && myGrid.checkMoveHorizontal(yx, yy+1) 
						&& myGrid.checkMoveHorizontal(wx, wy+1));
				break;
		}
		if (validMove) {
			setTetriminoCellsAsFree();
			assignNewCellsToTetrimino(xx, xy + 1, yx, yy + 1, zx, zy + 1, wx, wy + 1);
			setTetriminoCellsAsTaken();
		}
	}	
	

	@Override
	public void rotate() {
		refreshPositions();		
		boolean validRotation = false;
								
			switch (currentAngle) {
				case 0: 
					
					validRotation = myGrid.checkMoveVertical(zx + 1, zy); 
					if (validRotation) {
										
						setTetriminoCellsAsFree();
						assignNewCellsToTetrimino(wx, wy, xx, xy, zx, zy, zx + 1, zy);
						setTetriminoCellsAsTaken();
						currentAngle = 90;
					}
						
					break;
				
				case 90:
					
					validRotation = myGrid.checkMoveHorizontal(zx, zy-1);
					if (validRotation) {
						
						setTetriminoCellsAsFree();
						assignNewCellsToTetrimino(wx, wy, xx, xy, zx, zy, zx, zy-1);
						setTetriminoCellsAsTaken();
						currentAngle = 180;
					}
						
					break;
				
				case 180:
					
					validRotation = myGrid.checkMoveVertical(zx-1,zy);
					if (validRotation) {
										
						setTetriminoCellsAsFree();
						assignNewCellsToTetrimino(wx, wy, xx, xy, zx, zy, zx - 1, zy);
						setTetriminoCellsAsTaken();
						currentAngle = 270;
					}
						
					break;
				
				case 270:
					
					validRotation = myGrid.checkMoveHorizontal(zx, zy+1);
					if (validRotation) {
										
						setTetriminoCellsAsFree();
						assignNewCellsToTetrimino(wx, wy, xx, xy, zx, zy, zx , zy+1);
						setTetriminoCellsAsTaken();
						currentAngle = 0;
					}
						
					break;
				
			}
		}	

	@Override
	public boolean moveDown() {
		refreshPositions();
		boolean validMove = false;
		switch(currentAngle) {		

			case 0:
				validMove = ( (myGrid.checkMoveVertical(yx + 1, yy)) &&
						(myGrid.checkMoveVertical(zx + 1, zy)) &&
						(myGrid.checkMoveVertical(wx + 1, wy)) ); 
				break;
				
			case 90:
				validMove = (myGrid.checkMoveVertical(xx+1, xy) && myGrid.checkMoveVertical(wx+1, wy));
				break;
				
			case 180:
				
					validMove = (myGrid.checkMoveVertical(xx+1, xy) && myGrid.checkMoveVertical(wx+1, wy) 
							&& myGrid.checkMoveVertical(yx+1, yy));
				
				break;
				
			case 270:
				validMove = (myGrid.checkMoveVertical(xx+1, xy) && myGrid.checkMoveVertical(yx+1, yy));
		}
			if (validMove) {		
				
				setTetriminoCellsAsFree();			
				assignNewCellsToTetrimino(xx + 1,xy, yx + 1, yy, zx + 1, zy, wx + 1, wy);
				setTetriminoCellsAsTaken();
			}
			
			return validMove;
	}
	
	public void rotateLeft() { 
		
		refreshPositions();		
		boolean validRotation = false;
								
		switch (currentAngle) {
			case 0: 
				
				validRotation = myGrid.checkMoveVertical(zx + 1,zy);
				if (validRotation) {
									
					setTetriminoCellsAsFree();
					assignNewCellsToTetrimino(yx, yy, zx + 1, zy, zx, zy, xx, xy);
					setTetriminoCellsAsTaken();
					currentAngle = 270;
				}
					
				break;
				
			case 90:
				validRotation = myGrid.checkMoveHorizontal(zx, zy - 1);
				if (validRotation) {
									
					setTetriminoCellsAsFree();
					assignNewCellsToTetrimino(yx, yy, zx, zy - 1, zx, zy, xx, xy);
					setTetriminoCellsAsTaken();
					currentAngle = 0;
				}
					
				break;
				
				
			case 180:
				
				validRotation = myGrid.checkMoveVertical(zx - 1, zy); 
				if (validRotation) {
									
					setTetriminoCellsAsFree();
					assignNewCellsToTetrimino(yx, yy, zx - 1, zy, zx, zy, xx, xy);
					setTetriminoCellsAsTaken();
					currentAngle = 90;
				}
					
				break;
			
			case 270:
				
				validRotation = myGrid.checkMoveHorizontal(zx, zy + 1);
				if (validRotation) {
					
					setTetriminoCellsAsFree();
					assignNewCellsToTetrimino(yx, yy, zx, zy + 1, zx, zy, xx, xy);
					setTetriminoCellsAsTaken();
					currentAngle = 180;
				}
					
				break;
			
		}
		
	}
	
}
