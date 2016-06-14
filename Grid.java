package grid;

import org.newdawn.slick.geom.Vector2f;

import playerObjects.Enemy;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;

import org.newdawn.slick.Color;

//This makes a grid for laying out other objects. It is used for in game management
//of space. It allows for the organization of turrets and enemy locations for range.

public class Grid {
	public final Vector2f GRID_UNIT;
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
	public Grid(Vector2f l, Vector2f s, int r) {
		size = s;
		location = l;
		gr = new int[r][r];
		idCount = 1;
		GRID_UNIT = new Vector2f(size.x/r, size.y/r);
	}
	
	//makes a grid of boolean values with length r and height c along with a raw size of s at location l.
	public Grid(Vector2f l, Vector2f s, int r, int c){
		size = s;
		location = l;
		gr = new int[r][c];
		idCount = 1;
		GRID_UNIT = new Vector2f(size.x/r, size.y/c);
	}
	
	//Changes the value of a piece of the array.
	public void change(int r, int c, int b) {
		gr[r][c] = b;
	}
	
	//Checks the value at r,c.
	public int get(int r, int c) {
		return gr[r][c];
	}
	
	//Gives grid position based off a vector.
	public int get(Vector2f r) {
		return gr[(int)r.x][(int)r.y];
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
	
	public void render(Graphics g) {
		g.setColor(new Color(0f, 0f, 0f));
		for(int i = 0; i<gr.length+1; i++) {
			g.drawLine((int)(location.x+size.x/gr.length*i), (int)location.y, (int)(location.x+size.x/gr.length*i), (int)(location.y+size.y));
		}
		
		for(int i = 0; i<gr[0].length + 1; i++) {
			g.drawLine((int)(location.x), (int)(location.y+size.y/gr[0].length*i), (int) (location.x + size.x), (int)(location.y+size.y/gr[0].length*i)); 
		}
	}
	
	//This is the A* Value system. Distance between start and point vs end and point.
	private static int value(Vector2f start, Vector2f goal, Vector2f loc, Waypoint w) {
		
		int inheritance = 0;
		
		//This determines how much is inherited via the path.
		if(w.hasParent()) {
			inheritance = w.getParent().getValue();
		}
		
		//This starts with value of point from start (g) and ends with heuristic of proximity to end (h)
		return (int)(start.x+goal.x-2*loc.x)+(int)(start.y+goal.y-2*loc.y)+inheritance;
		
	}
	private Vector2f[] getAdjacent(Vector2f loc) {
		//An array of the locations that an object can move to assuming 4 way motion only
		//This gets the adjacent locations if possible and leaves null if they do not exist
		Vector2f[] adjacents = new Vector2f[4];
		//Left side
		if(loc.x!=0 && (gr[(int)loc.x-1][(int)loc.y] == 0 || gr[(int)loc.x-1][(int)loc.y] == 100)) {
			adjacents[0] = new Vector2f(loc.x-1, loc.y);
		}else {
			adjacents[0] = new Vector2f(-1, -1);
		}
		//Top side
		if(loc.y!=0 && (gr[(int)loc.x][(int)loc.y-1] == 0|| gr[(int)loc.x][(int)loc.y-1] == 100)) {
			adjacents[1] = new Vector2f(loc.x, loc.y-1);
		}else {
			adjacents[1] = new Vector2f(-1, -1);
		}
		//Right side
		if(loc.x!=gr.length-1 && (gr[(int)loc.x+1][(int)loc.y] == 0 || gr[(int)loc.x+1][(int)loc.y] == 100)) {
			adjacents[2] = new Vector2f(loc.x+1, loc.y);
		}else {
			adjacents[2] = new Vector2f(-1, -1);
		}
		//Bottom side
		if(loc.y!=gr[0].length-1 && (gr[(int)loc.x][(int)loc.y+1] == 0 || gr[(int)loc.x][(int)loc.y+1] == 100)) {
			adjacents[3] = new Vector2f(loc.x, loc.y+1);
		}else {
			adjacents[3] = new Vector2f(-1, -1);
		}
		
		return adjacents;
	}
	
	
	public Waypoint getPath(Vector2f loc, Vector2f goal) {
		ArrayList<Waypoint> open = new ArrayList<Waypoint>();
		ArrayList<Waypoint> closed = new ArrayList<Waypoint>();
		Waypoint parent = new Waypoint(loc);
		open.add(parent);
		parent.setValue(0);
		
		while(!open.isEmpty()) {
			Waypoint mostPromising = open.get(0);
			int q = 0;
			for(int i = 0; i<open.size(); i++) {
				open.get(i).setParent(parent);
				if(open.get(i).equals(goal)) {
					//This gives a waypoint who's parents can be followed up the chain to find the path
					return open.get(i);
				} 
				
				//This finds the most promising waypoint to search from ^ and below
				
				int v = value(loc, goal, open.get(i).getLoc(), open.get(i));
				open.get(i).setValue(v);
				if(v < mostPromising.getValue()) {
					q = i;
					mostPromising = open.get(i);
				}
			}
			
			//This adds new things to the open list
			Vector2f[] adjacent = getAdjacent(open.get(q).getLoc());
			for(int j = 0; j<adjacent.length; j++) {
				if(!adjacent[j].equals(new Vector2f(-1, -1))) {
					Waypoint initiate = new Waypoint(adjacent[j]);
					initiate.setParent(mostPromising);
					initiate.setValue(value(loc, goal, initiate.getLoc(), initiate));//+mostPromising.getValue());
				
				//If there are better options in closed or open this code ignores the node
					if(closed.contains(initiate) && closed.get(closed.indexOf(initiate)).getValue() < initiate.getValue()) {
						continue;
					}
					if(open.contains(initiate) && open.get(open.indexOf(initiate)).getValue() < initiate.getValue()) {
						System.out.println("Hey, this just jumped over something");
						
						continue;
						
					}
					
					open.add(initiate);
				}
			}
			
			closed.add(open.remove(q));
			
			//Make paths for all, setting first one as first for all. (Clone?)
		}
		return null;
	}
	
}
