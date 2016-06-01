package grid;

import java.util.ArrayList;

//This group is used to determine areas where path finding is unavailable
public class Group {
	
	//This holds the list of all walls contained by the group
	private ArrayList<Wall> g;
	
	//This is the unique code for the group so that they can be differentiated
	private int id;
	
	//Initializes the group and gives it an id number.
	Group(int i) {
		id = i;
		g = new ArrayList<Wall>();
	}
	
	//Returns the identifier for the group
	public int getId() {
		return id;
	}
	
	//Returns the list of walls
	public ArrayList<Wall> getWalls() {
		return g;
	}
	
	//Returns the index of a wall in the ArrayList
	public int indexOf(Wall w) {
		if(g.contains(w)) {
			return g.indexOf(w);
		} else return -1;
	}
	
	//Returns the wall at index i
	//PRE-CONDITION: i is less than the end of the array.
	public Wall get(int i) {
		return g.get(i);
	}
	
	//Tests if the given wall is inside the ArrayList.
	public boolean contains(Wall w) {
		return g.contains(w);
	}
}
