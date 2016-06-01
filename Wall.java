package grid;

import org.lwjgl.util.vector.Vector2f;

//This object creates a wall to be avoided by the path finding functionality.

public class Wall {
	Vector2f location;
	boolean isGhost;
	
	//This makes a wall and determines if it s a ghost wall or not (if so it was derived and has no actual object there)
	Wall(Vector2f l, boolean g) {
		location = l;
		isGhost = g;
	}
	
	//Tests if the wall contains an object or if it was derived from being a tile never walked on
	public boolean isGhost() {
		return isGhost;
	}
	
	//This makes the grid object reflect the fact that a wall has been made based off a raw location value
	public void addToGrid(Grid g) {
		int i = 1;
		if(isGhost)
			i = 2;
		g.change((int)(location.getX()/(g.getSize().getX()/g.getGrid().length)),
				(int)(location.getY()/(g.getSize().getY())), i);
	}
}
