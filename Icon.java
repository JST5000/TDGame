package playerObjects;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.opengl.Texture;

public class Icon {
	private Texture t1;
	
	//This is the image shown on the icon
	private Image design;
	
	//This is the size of the image in pixels
	private Vector2f sizeP;
	
	//establishes the fields to values. (Image size should be in Pixels)
	public Icon(Image i, Vector2f s) {
		Texture t1 = i.getTexture();
		design = i;
		sizeP = s;
	}
	
	public Icon(Vector2f s) {
		sizeP = s;
	}
	
	//This returns the image
	public Image getDesign() {
		return design;
	}
	
	//Returns the size of the image in pixels
	public Vector2f getSize() {
		return sizeP;
	}
}
