package tetriminos;
import logic.*;
import GUI.TetrisGUI;


import javax.swing.ImageIcon;

public class Tetrimino_I extends Tetrimino{
	
	public Tetrimino_I(Grid myGrid) {
		
		super(myGrid);			
		cellImage = new ImageIcon(TetrisGUI.class.getResource("/img/cells/cyanCell.png"));
	}
	
	public boolean initializeTetrimino() {
		
		boolean valid = checkPositions(0, 3, 0, 4, 0, 5, 0, 6);
		assignNewCellsToTetrimino(0, 3, 0, 4, 0, 5, 0, 6);
		setTetriminoCellsAsTaken();	
		
		return valid;
	}
	
	@Override
	public void moveToLeft() {
		
		refreshPositions();
		boolean validMove = false;
		
		switch (currentAngle) {
					
			case 0:
			case 180:
				
				if (currentAngle == 0)
					validMove = myGrid.checkMoveHorizontal(xx, xy - 1);
				else 
					validMove = myGrid.checkMoveHorizontal(wx, wy - 1);
				
				if (validMove) {
					
					setTetriminoCellsAsFree();			
					assignNewCellsToTetrimino(xx, xy - 1, yx, yy - 1, zx, zy - 1, wx, wy - 1);
					setTetriminoCellsAsTaken();
				}
				
				break;
				
			case 90:
			case 270:
				
				validMove = myGrid.checkMoveHorizontal(xx, xy - 1) &&
							myGrid.checkMoveHorizontal(yx, yy - 1) &&
							myGrid.checkMoveHorizontal(zx, zy - 1) &&
							myGrid.checkMoveHorizontal(wx, wy - 1); 
					
				if (validMove) {
					
					setTetriminoCellsAsFree();			
					assignNewCellsToTetrimino(xx, xy - 1, yx, yy - 1, zx, zy - 1, wx, wy - 1);			
					setTetriminoCellsAsTaken();
				}
				
				break;		
		}
			
	}

	@Override
	public void moveToRight() {
		
		refreshPositions();
		
		boolean validMove = false;
	
		switch (currentAngle) {
			
			case 0:
			case 180:
				
				if (currentAngle == 0)
					validMove = myGrid.checkMoveHorizontal(wx, wy + 1);
				else
					validMove = myGrid.checkMoveHorizontal(xx, xy + 1);
				
				if (validMove) {
			
					setTetriminoCellsAsFree();		
					assignNewCellsToTetrimino(xx, xy + 1 , yx, yy + 1, zx, zy + 1, wx, wy + 1);
					setTetriminoCellsAsTaken();
				}
				
				break;
				
			case 90:
			case 270:
				
				validMove = myGrid.checkMoveHorizontal(xx, xy + 1) &&
							myGrid.checkMoveHorizontal(yx, yy + 1) &&
							myGrid.checkMoveHorizontal(zx, zy + 1) &&
							myGrid.checkMoveHorizontal(wx, wy + 1); 
					
				if (validMove) {
					
					setTetriminoCellsAsFree();		
					assignNewCellsToTetrimino(xx, xy + 1, yx, yy + 1, zx, zy + 1, wx, wy + 1);
					setTetriminoCellsAsTaken();
				}
				
				break;		
		}
		
	}

	@Override
	public void rotate() {
			
		refreshPositions();
		boolean validRotation = false;

		
			switch (currentAngle) {
				
				case 0: 


					validRotation = ((zx > 1 && zx + 1 < 21) && (wx > 1 && wx + 1 < 21)) &&
												    myGrid.checkMoveVertical(zx - 2, zy) &&
												    myGrid.checkMoveVertical(zx - 1, zy) &&
									 			    myGrid.checkMoveVertical(zx + 1, zy);

					if (validRotation) {						
						
						setTetriminoCellsAsFree();
						assignNewCellsToTetrimino(zx - 2, zy, zx - 1, zy, zx, zy, zx + 1, zy);	
						setTetriminoCellsAsTaken();		
						
						currentAngle = 90;
					}
						
					break;
						
				case 90:
					
					validRotation = myGrid.checkMoveHorizontal(zx, zy + 1) &&
									myGrid.checkMoveHorizontal(zx, zy - 1) && 
									myGrid.checkMoveHorizontal(zx, zy - 2);
					
					if (validRotation) {
						
						setTetriminoCellsAsFree();
						assignNewCellsToTetrimino(zx, zy + 1, zx, zy - 1, zx, zy, zx, zy - 2);	
						setTetriminoCellsAsTaken();
						
						currentAngle = 180;
					}
					
					break;
					
			case 180:

					validRotation = ((yx > 1 && yx + 1 < 21) && (wx > 1 && wx + 1 < 21)) &&
													myGrid.checkMoveVertical(yx - 2, yy) &&
													myGrid.checkMoveVertical(yx - 1, yy) && 
													myGrid.checkMoveVertical(yx + 1, yy);
					
					if (validRotation) {
						
						setTetriminoCellsAsFree();
						assignNewCellsToTetrimino(yx - 2, yy, yx, yy, yx - 1, yy, yx + 1, yy);						
						setTetriminoCellsAsTaken();
						
						currentAngle = 270;
					}
					
					break;
					
			case 270:
				
				validRotation = myGrid.checkMoveHorizontal(yx, yy - 1) &&
								myGrid.checkMoveHorizontal(yx, yy + 1) &&
								myGrid.checkMoveHorizontal(yx, yy + 2);
				
				if (validRotation) {
					
					setTetriminoCellsAsFree();
					assignNewCellsToTetrimino(yx, yy -1, yx, yy, yx, yy + 1, yx, yy + 2);	
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
		
			switch (currentAngle) {
			
				case 0:
				case 180:
				
				validMove = ((myGrid.checkMoveVertical(xx + 1, xy)) &&
							 (myGrid.checkMoveVertical(yx + 1, yy)) &&
							 (myGrid.checkMoveVertical(zx + 1, zy)) &&
							 (myGrid.checkMoveVertical(wx + 1, wy)));
			
				if (validMove) {		
					setTetriminoCellsAsFree();
					assignNewCellsToTetrimino(xx + 1, xy, yx + 1, yy, zx + 1, zy, wx + 1, wy);		
					setTetriminoCellsAsTaken();
				}
				
				break;
				
				case 90:
				case 270:
						
					if (currentAngle == 90) 							
							validMove = myGrid.checkMoveVertical(wx + 1, wy);			
					else 
							validMove = myGrid.checkMoveVertical(wx + 1, wy);
			
					
					if (validMove) {		
				
						setTetriminoCellsAsFree();
						assignNewCellsToTetrimino(xx + 1, xy, yx + 1, yy, zx + 1, zy, wx + 1, wy);	
						setTetriminoCellsAsTaken();
					}
					
				break;
			}
		
		return validMove;
	}
	
	

	@Override
	public void rotateLeft() {
		
		this.rotate();
	}

}
