package pucrs.cg1.citybike.objects;

import pucrs.cg1.citybike.engine.GameObject;

import com.jogamp.opengl.GL2;

public class Piramide extends GameObject {

	private static final int SIZE = 10;
	
	@Override
	public void draw(GL2 gl) {

		gl.glBegin(GL2.GL_QUADS);
		vertA();
		vertB();
		vertC();
		vertD();
		
		gl.glBegin(GL2.GL_TRIANGLES);           // Begin drawing the pyramid with 4 triangles
		vertA();
		vertB();
		vertE();
	 
		vertB();
		vertC();
		vertE();
	 
		vertC();
		vertD();
		vertE();

		vertA();
		vertD();
		vertE();
		
		gl.glEnd();   // Done drawing the pyramid
		
	}
	
	
	
	void vertA(){
		gl.glVertex3f( x , y, z);		
	}
	void vertB(){
		gl.glVertex3f( x + SIZE, y, z);		
		
	}
	void vertC(){
		gl.glVertex3f( x + SIZE, y, z + SIZE);		
		
	}
	void vertD(){
		gl.glVertex3f( x, y, z + SIZE);				
	}
	
	//TOPO
	void vertE(){

		gl.glVertex3f( x+ (SIZE/2), y + SIZE, z + (SIZE/2));			
	}
	

}
