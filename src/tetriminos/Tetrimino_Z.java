package tetriminos;
import logic.*;
import GUI.TetrisGUI;
import javax.swing.ImageIcon;

public class Tetrimino_Z extends Tetrimino {
	public Tetrimino_Z(Grid myGrid) {
		super(myGrid);		
		cellImage = new ImageIcon(TetrisGUI.class.getResource("/img/cells/redCell.png"));

	}

	public boolean initializeTetrimino() {
		
		boolean valid = checkPositions(0, 3, 0, 4, 1, 4, 1, 5);
		assignNewCellsToTetrimino(0, 3, 0, 4, 1, 4, 1, 5);
		setTetriminoCellsAsTaken();
		
		return valid;
	}
	
	@Override
	public void moveToLeft() {
		refreshPositions();
		boolean validMove = false;
		switch(currentAngle) {
			case 0: 				
				validMove = ( myGrid.checkMoveHorizontal(xx, xy-1) &&   myGrid.checkMoveHorizontal(zx, zy-1));
				break;
			case 90:
				validMove = ( myGrid.checkMoveHorizontal(xx, xy-1) &&   myGrid.checkMoveHorizontal(yx, yy-1) 
							&& myGrid.checkMoveHorizontal(wx, wy-1));
				break;
			case 180: 
				validMove = ( myGrid.checkMoveHorizontal(xx, xy-1) &&   myGrid.checkMoveHorizontal(yx, yy-1));
				break;
			case 270:
				validMove = ( myGrid.checkMoveHorizontal(xx, xy-1) &&   myGrid.checkMoveHorizontal(zx, zy-1) 
						&& myGrid.checkMoveHorizontal(wx, wy-1));
				break;
		}	
		if (validMove) {
			setTetriminoCellsAsFree();		
			assignNewCellsToTetrimino(xx,xy -1,yx, yy -1,zx, zy -1,wx, wy -1);		
			setTetriminoCellsAsTaken();
		}
	}

	@Override
	public void moveToRight() {
		refreshPositions();
		boolean validMove = false;
		switch(currentAngle) {
			case 0: 				
				validMove = ( myGrid.checkMoveHorizontal(yx, yy+1) &&   myGrid.checkMoveHorizontal(wx, wy+1));
				break;
			case 90:
				validMove = ( myGrid.checkMoveHorizontal(xx, xy+1) &&   myGrid.checkMoveHorizontal(zx, zy+1) 
							&& myGrid.checkMoveHorizontal(wx, wy+1));
				break;
			case 180: 
				validMove = ( myGrid.checkMoveHorizontal(wx, wy+1) &&   myGrid.checkMoveHorizontal(zx, zy+1));
				break;
			case 270:
				validMove = ( myGrid.checkMoveHorizontal(xx, xy+1) &&   myGrid.checkMoveHorizontal(yx, yy+1) 
						&& myGrid.checkMoveHorizontal(wx, wy+1));
				break;
		}	
		if (validMove) {
			setTetriminoCellsAsFree();	
			assignNewCellsToTetrimino(xx, xy +1,yx, yy +1,zx, zy +1,wx, wy +1);		
			setTetriminoCellsAsTaken();
		}
	}

	@Override
	public void rotate() {
		refreshPositions();
		boolean block1 = false;
		boolean block2 = false;
		boolean Flag = false;
		switch (currentAngle) {
			case 0:
				block1 = myGrid.checkMoveVertical(zx + 1, zy - 1) && myGrid.checkMoveHorizontal(zx + 1, zy - 1);
				block2 = myGrid.checkMoveVertical(zx, zy - 1) && myGrid.checkMoveHorizontal(zx, zy - 1);
				if(block1 && block2) {
					setTetriminoCellsAsFree();
					assignNewCellsToTetrimino(zx + 1, zy - 1,zx, zy - 1,zx,zy,zx - 1, zy);	
					setTetriminoCellsAsTaken();
					Flag = true;
				}
				break;
			case 90:
				block1 = myGrid.checkMoveVertical(zx - 1, zy - 1) && myGrid.checkMoveHorizontal(zx - 1, zy - 1);
				block2 = myGrid.checkMoveHorizontal(zx, zy + 1);
				if(block1 && block2) {
					setTetriminoCellsAsFree();
					assignNewCellsToTetrimino(zx - 1, zy - 1,zx, zy,zx - 1, zy,zx, zy + 1);
					setTetriminoCellsAsTaken();
					Flag = true;
				}
				break;
			case 180:
				block1 = myGrid.checkMoveVertical(yx, yy - 1) && myGrid.checkMoveHorizontal(yx, yy - 1);
				block2 = myGrid.checkMoveVertical(yx + 1, yy - 1) && myGrid.checkMoveHorizontal(yx, yy - 1);
				if(block1 && block2) {
					setTetriminoCellsAsFree();
					assignNewCellsToTetrimino(yx -1 , yy,yx,yy,yx, yy - 1,yx + 1, yy - 1);
					setTetriminoCellsAsTaken();
					Flag = true;
				}
				break;
			case 270:
				block1 = myGrid.checkMoveVertical(yx - 1, yy - 1) && myGrid.checkMoveHorizontal(yx - 1, yy - 1);
				block2 = myGrid.checkMoveHorizontal(yx, yy + 1);
				if(block1 && block2) {
					setTetriminoCellsAsFree();
					assignNewCellsToTetrimino(yx - 1, yy - 1,yx - 1 , yy,yx, yy,yx , yy + 1);
					setTetriminoCellsAsTaken();
					Flag = true;
				}
				break;
				
		}
		if (Flag) {
			if (currentAngle<=180) {
				currentAngle+=90;
			}else
				currentAngle = 0;
		}
	}

	@Override
	public boolean moveDown() {
		refreshPositions();
		boolean block1 = false;
		boolean block2 = false;
		boolean block3 = false;
		switch(currentAngle) {		
			case 0:
				block1 = myGrid.checkMoveVertical(xx + 1, xy);
				block2 = myGrid.checkMoveVertical(zx + 1, zy);
				block3 = myGrid.checkMoveVertical(wx + 1, wy);
				break;			
			case 90:
				block1 = myGrid.checkMoveVertical(xx + 1, xy);
				block2 = myGrid.checkMoveVertical(zx + 1, zy);
				block3 = true;
				break;				
			case 180:				
				block1 = myGrid.checkMoveVertical(xx + 1, xy);
				block2 = myGrid.checkMoveVertical(yx + 1, yy);
				block3 = myGrid.checkMoveVertical(wx + 1, wy);				
				break;				
			case 270:
				block1 = myGrid.checkMoveVertical(yx + 1, yy);
				block2 = myGrid.checkMoveVertical(wx + 1, wy);
				block3 = true;
				break;
				
		}
			if (block1 && block2 && block3) {						
				setTetriminoCellsAsFree();	
				assignNewCellsToTetrimino(xx + 1, xy,yx + 1, yy,zx + 1, zy,wx + 1, wy);
				setTetriminoCellsAsTaken();
			}		
			return block1 && block2 && block3;
		
	}
	
	public void rotateLeft() { 
		rotate();
	}


}
