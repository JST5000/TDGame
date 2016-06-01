package grid;

import org.lwjgl.util.vector.Vector2f;
//A way point is a path to be taken in a straight line from point a to point b.
public class Waypoint {
	
	//This gives the absolute (pixel) location of the start point on the page.
	private Vector2f locationP1;
	
	//This is the location of the start point in the Array of the Grid.
	private Vector2f locationA1;
	
	//This gives the absolute (pixel) location of the end point
	private Vector2f locationP2;
	
	//This is the location of the end-point with values given in row/column
	private Vector2f locationA2;
	
	private Grid g;
	//Makes a way point.
	Waypoint(Vector2f loc1, Vector2f loc2, Grid gr) {
		
		//Establishes an internal connection to the grid
		g = gr;
		
		//Establishes start point values
		locationP1 = loc1;
		locationA1 = new Vector2f((int)(loc1.getX()/(g.getSize().getX()/g.getGrid().length)),
					(int)(loc1.getY()/(g.getSize().getY())));
		
		//Establishes end point values
		locationP2 = loc2;
		locationA2 = new Vector2f((int)(loc2.getX()/(g.getSize().getX()/g.getGrid().length)),
					(int)(loc2.getY()/(g.getSize().getY())));
	}
	
	//Returns the location of start relative to page corner in pixels
	public Vector2f getAbsLoc1() {
		return locationP1;
	}
	
	//Returns the location of end relative to page corner in pixels
	public Vector2f getAbsLoc2() {
		return locationP2;
	}
	
	//Returns the location of the start relative to the grid (Row, Column)
	public Vector2f getGridLoc1() {
		return locationA1;
	}
	
	//Returns the location of the end relative to the grid (Row, Column)

	public Vector2f getGridLoc2() {
		return locationA2;
	}
	
	public Vector2f getDisp() {
		return new Vector2f(locationA1.getX()-locationA2.getX(), locationA1.getY()-locationA2.getY());
	}
	
}
