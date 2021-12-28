package logic;

import javax.swing.ImageIcon;

public class Cell {

	private int x;
	private int y;
	private boolean state;
	private ImageIcon image;
	private Grid myGrid;
	
	public Cell (int x, int y, ImageIcon image, Grid myGrid) {
		
		this.x = x;
		this.y = y;
		this.image = image; 
		state = false;
		this.myGrid = myGrid;
	}
	
	public Cell (int x, int y, ImageIcon image) {
		
		this.x = x;
		this.y = y;
		state = false;
	}
	
	public void setX(int x) {
		
		this.x = x;
	}
	
	public void setY(int y) {
		
		this.y = y;
	}
	
	public void setStateAsTaken() {
		
		state = true;
		myGrid.occupyCell(this);
	}
	
	public void setStateAsFree() {
		
		state = false;
		myGrid.releaseCell(this);
	}
	
	public void setImage(ImageIcon image) {
		
		this.image = image;
	}
	
	
	public int getXPosition() {
		
		return x;
	}
	
	public int getYPosition() {
		
		return y;
	}
	
	public boolean getCurrentState() {
		
		return state;
	}
	
	public ImageIcon getImage() {
		
		return image;
	}

}
