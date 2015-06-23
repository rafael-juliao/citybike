package pucrs.cg1.citybike;

import com.jogamp.opengl.GL2;

public class GameObject {

	float x, y, z;
	
	GL2 gl;
	
	public void create(){}
	public void colorize(){}
	
	
	public void draw(GL2 gl) {
		this.gl = gl;
		create();
	}
}
