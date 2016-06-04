package grid;

import org.newdawn.slick.geom.Vector2f;
//A way point is a path to be taken in a straight line from point a to point b.
public class Waypoint {
	
	//This gives the array location of the start point on the page.
	private Vector2f locationA1;
		
	private int value;
	
	private Waypoint parent;
	//Makes a way point.
	Waypoint(Vector2f loc1) {
		
		//Establishes start point values
		locationA1 = loc1;
	
		parent = null;
		
		value = 0;
	}
	
	//Returns the location of start relative to page corner in pixels
	public Vector2f getLoc() {
		return locationA1;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int x) {
		value = x;
	}
	
	public Waypoint getParent() {
		return parent;
	}
	
	public void setParent(Waypoint p) {
		parent = p;
	}
	
}
