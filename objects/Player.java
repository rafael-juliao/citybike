package pucrs.cg1.citybike.objects;

import pucrs.cg1.citybike.engine.GameObject;

import com.jogamp.opengl.GL2;

public class Player extends GameObject{
	
	
	private int speed = 0;
		
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
		z += speed;
	}

	public void moveLeft(){
		x += 3;
	}

	public void moveRight(){
		x -= 3;
	}
	
	public void speedUp(){
		if( speed != MOTO_MAX_SPEED)
		speed+= 1;
	}

	public void speedDown(){
		if( speed != 0)
		speed-= 1;
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
		gl.glVertex3f( x + MOTO_SIZE_X , y, z);
		
	}
	
	public void vertC(){
		gl.glVertex3f( x , y, z + MOTO_SIZE_Z);
	}
	
	public void vertD(){
		gl.glVertex3f( x + MOTO_SIZE_X, y, z + MOTO_SIZE_Z);		
	}
	
	public void vertE(){
		gl.glVertex3f( x , y + MOTO_SIZE_Y , z);
	}
	
	public void vertF(){
		gl.glVertex3f( x + MOTO_SIZE_X , y + MOTO_SIZE_Y, z);
		
	}
	
	public void vertG(){
		gl.glVertex3f( x , y + MOTO_SIZE_Y, z + MOTO_SIZE_Z);
	}
	
	public void vertH(){
		gl.glVertex3f( x + MOTO_SIZE_X, y + MOTO_SIZE_Y , z + MOTO_SIZE_Z);		
	}


	
	@Override
	public float[][] get2DSquare() {
		float[][] square = new float[4][2];
		
		square[DL][X] = x;
		square[DL][Z] = z;
		
		square[DR][X] = x + MOTO_SIZE_X;
		square[DR][Z] = z;
		
		square[UL][X] = x;
		square[UL][Z] = z + MOTO_SIZE_Z;
		
		square[UR][X] = x + MOTO_SIZE_X;
		square[UR][Z] = z + MOTO_SIZE_Z;
		
		return square;
	}
	
	
	
	
}
