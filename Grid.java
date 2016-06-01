package grid;

import org.lwjgl.util.vector.Vector2f;

//This makes a grid for laying out other objects. It is used for in game management
//of space. It allows for the organization of turrets and enemy locations for range.

public class Grid {
	
	//This is the size of the grid in pixels.
	private Vector2f size;
	
	//This is the location in relation to the page spawned.
	private Vector2f location;
	
	//This is the array that represents locations that are occupied 0 == null, 1 == wall, 2 == ghostWall
	//Ghost walls can be placed upon, but cannot be moved through.
	private int[][] gr;
	
	//This acts as a unifying number so that groups don't get the same id within the grid.
	private int idCount;
	
	//makes a grid of boolean values with length r and height r along with a raw size of s at location l.
	Grid(Vector2f s, Vector2f l, int r) {
		size = s;
		location = l;
		gr = new int[r][r];
		idCount = 1;
	}
	
	//makes a grid of boolean values with length r and height c along with a raw size of s at location l.
	public Grid(Vector2f s, Vector2f l, int r, int c){
		size = s;
		location = l;
		gr = new int[r][c];
		idCount = 1;
	}
	
	//Changes the value of a piece of the array.
	public void change(int r, int c, int b) {
		gr[r][c] = b;
	}
	
	//Checks the value at r,c.
	public int get(int r, int c) {
		return gr[r][c];
	}
	//Returns the grid
	public int[][] getGrid() {
		return gr;
	}
	
	//Returns the size
	public Vector2f getSize() {
		return size;
	}
	
	//Returns the location
	public Vector2f getLoc() {
		return location;
	}
	
	public int getId() {
		return idCount;
	}
	
}
