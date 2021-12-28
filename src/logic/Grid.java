package logic;




import javax.swing.ImageIcon;

public class Grid {

	private int rows;
	private int columns;
	private Cell cellGrid [][];
	private Logic myLogic;
	
	public Grid(int rows, int columns, Logic myLogic) {
		
		this.rows = rows;
		this.columns = columns;
		this.myLogic = myLogic;
		cellGrid = new Cell [rows][columns];
	
		for (int i = 0; i < rows; i++) 
			for (int j = 0; j < columns; j++) 
				cellGrid[i][j] = new Cell(i,j, null, this);
	}
	
	
	public int removeLines() {
		
		boolean emptyCellFounded = false;
		int linesCleaned = 0;
		
		for (int i = rows - 1; i >= 0; i--) {
			
			emptyCellFounded = checkFullRow(i);
			
			if (emptyCellFounded == true) {
				clearRow(i);
				i++;
				linesCleaned += 1;
				moveAllDown(i-1);
			}
			
		}
		
		return linesCleaned;
		
	}

	private void moveAllDown(int x) {
	
			
		for (int i = x; i > 0; i--) 		
			
			for (int j = 0; j < columns; j++) {
				
				if (cellGrid[i][j].getCurrentState()) {
				
					cellGrid[i+1][j].setImage(cellGrid[i][j].getImage());
					cellGrid[i][j].setStateAsFree();
					cellGrid[i+1][j].setStateAsTaken();
				}
					
			}
	}
					
			
	private void clearRow(int row) {
		
		for (int j = 0; j < columns; j++) 
			cellGrid[row][j].setStateAsFree();
		
	}


	public void occupyCell(Cell cell) {

		myLogic.refreshGUI(cell.getXPosition(), cell.getYPosition(), cell.getImage());
	}
	
	public void releaseCell(Cell cell) {

		myLogic.refreshGUI(cell.getXPosition(), cell.getYPosition(), null);
	}
	
	public void restartGrid() {
		
		for (int i = 0; i < rows; i++) 
			for (int j = 0; j < columns; j++) {
				cellGrid[i][j] = new Cell(i,j, null, this);
				cellGrid[i][j].setStateAsFree();
			}		
	}
	
	public boolean checkFullRow(int y) {
	
			boolean llena = true;
			
			for (int j = 0; j < columns && llena; j++)	 
				llena = cellGrid[y][j].getCurrentState();				
			

			return llena;
			
	}
	
	public boolean checkMoveHorizontal(int x, int y) {
		
		return (y >= 0 && y < columns) && (cellGrid[x][y].getCurrentState() == false) ? true : false;		
	}
	
	public boolean checkMoveVertical(int x, int y) {
		
		return (x >= 0 && x < rows) && (cellGrid[x][y].getCurrentState() == false) ? true : false;
	}
	

	public Cell getCell(int x, int y, ImageIcon cellImage) {

		cellGrid[x][y].setImage(cellImage);
		return cellGrid[x][y];
	}
	
	public Cell getCell(int x, int y) {

		return cellGrid[x][y];
	}
	
	public void generateNewTetrimino() {
		
		myLogic.createNewTetrimino();
	}
	
		
}
