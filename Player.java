package pucrs.cg1.citybike;

import com.jogamp.opengl.GL2;

public class Player extends GameObject{
	
	private static final int SIZE_X = 5, SIZE_Y = 30, SIZE_Z = 10;
	private static final int SPEED = 5;
	
	private static final float COLOR_RED = 0.5f, COLOR_GREEN = 0.0f, COLOR_BLUE = 0.0f;

	
	@Override
	public void draw(GL2 gl) {
		this.gl = gl;
		colorize();
		
		gl.glBegin(GL2.GL_QUADS); 
		
	    // Define vertices in counter-clockwise (CCW) order with normal pointing out
			  // Top face
			vertE();
			vertF();
			vertH();
			vertG();
		 
		      // Bottom face
			vertA();
			vertB();
			vertD();
			vertC();
		 
		      // Front face  
			vertC();
			vertD();
			vertH();
			vertG();
		 
		      // Back face (z = -1.0f)
			vertA();
			vertB();
			vertF();
			vertE();
		 
		      // Left face (x = -1.0f)
			vertA();
			vertC();
			vertG();
			vertE();
		 
		      // Right face (x = 1.0f)
			vertB();
			vertD();
			vertH();
			vertF();
			
		gl.glEnd();  // End of drawing color-cube
		
		gl.glBegin(GL2.GL_LINES);
		gl.glColor3f(0f, 0f, 0f);
		gl.glLineWidth(5f);
		
		//First Square
		vertA();
		vertB();
		
		vertB();
		vertF();

		vertF();
		vertE();
		

		vertE();
		vertA();
		
		
		//Ligations
		vertA();
		vertC();

		vertB();
		vertD();
		
		vertE();
		vertG();		

		vertF();
		vertH();
		
		//Second Square
		vertC();
		vertD();

		vertD();
		vertH();

		vertH();
		vertG();

		vertG();
		vertC();	
		
		gl.glEnd();  	
	 
	}
	

	public void colorize(){
		gl.glColor3f(COLOR_RED, COLOR_GREEN, COLOR_BLUE);     // Green
	}
	
	public void move(){
		z += SPEED;
	}

	public void moveLeft(){
		x += 3;
	}

	public void moveRight(){
		x -= 3;
	}

	/**
	 * Eight Vertices
	 * 	G   H
	 * 	C   D
	 *E   F
	 *A   B
	 * */
	
	public void vertA(){
		gl.glVertex3f( x , y , z);
	}
	
	public void vertB(){
		gl.glVertex3f( x + SIZE_X , y, z);
		
	}
	
	public void vertC(){
		gl.glVertex3f( x , y, z + SIZE_Z);
	}
	
	public void vertD(){
		gl.glVertex3f( x + SIZE_X, y, z + SIZE_Z);		
	}
	
	public void vertE(){
		gl.glVertex3f( x , y + SIZE_Y , z);
	}
	
	public void vertF(){
		gl.glVertex3f( x + SIZE_X , y + SIZE_Y, z);
		
	}
	
	public void vertG(){
		gl.glVertex3f( x , y + SIZE_Y, z + SIZE_Z);
	}
	
	public void vertH(){
		gl.glVertex3f( x + SIZE_X, y + SIZE_Y , z + SIZE_Z);		
	}
	
	
}
