package tetriminos;
import logic.*;
import GUI.TetrisGUI;
import javax.swing.ImageIcon;

public class Tetrimino_O extends Tetrimino {

	public Tetrimino_O(Grid myGrid) {
		super(myGrid);
		cellImage = new ImageIcon(TetrisGUI.class.getResource("/img/cells/yellowCell.png"));
	}
	
	public boolean initializeTetrimino() {		
		
		boolean valid = checkPositions(0, 4, 0, 5, 1, 4, 1, 5);
		assignNewCellsToTetrimino(0, 4, 0, 5, 1, 4, 1, 5);
		setTetriminoCellsAsTaken();
		
		return valid;
	}

	@Override
	public void moveToLeft() {
		
		refreshPositions();
		boolean valid = (myGrid.checkMoveHorizontal(xx, xy - 1) &&
				myGrid.checkMoveHorizontal(xx + 1, xy - 1));
		
		if (valid) {
			
			setTetriminoCellsAsFree();
			assignNewCellsToTetrimino(xx, xy - 1, yx, yy - 1, zx, zy - 1, wx, wy - 1);
			setTetriminoCellsAsTaken();
		}
	
	}

	@Override
	public void moveToRight() {

		refreshPositions();
		boolean valid = (myGrid.checkMoveHorizontal(yx, yy + 1) &&
						myGrid.checkMoveHorizontal(yx + 1, yy + 1));

		if (valid) {
			
			setTetriminoCellsAsFree();
			assignNewCellsToTetrimino(xx, xy + 1, yx, yy + 1, zx, zy + 1, wx, wy + 1);
			setTetriminoCellsAsTaken();
		}
	}

	@Override
	public void rotate() {
		// No rota
		
	}

	@Override
	public boolean moveDown() {
		
		refreshPositions();
		boolean valid = (myGrid.checkMoveVertical(zx + 1, zy) &&
						myGrid.checkMoveVertical(zx + 1, zy + 1));
		
		if(valid) {
			
			setTetriminoCellsAsFree();
			assignNewCellsToTetrimino(xx + 1, xy, yx + 1, yy, zx + 1, zy, wx + 1, wy);
			setTetriminoCellsAsTaken();
			
		}
		
		return valid;
	}
	
	public void rotateLeft() {
		//No rota
	}

	
}
