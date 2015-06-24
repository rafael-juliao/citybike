package pucrs.cg1.citybike;

import java.util.Random;

import com.jogamp.opengl.GL2;

public class Car extends GameObject {
	
	private int SIZE_X = 30, SIZE_Y = 25, SIZE_Z = 60;
	private int SPEED = 3;
	
	public Car(float posX, float posY, float posZ) {
		x = posX;
		y = posY;
		z = posZ;
		Random random = new Random();
		int carType = random.nextInt(3);
		if (carType == 1) { //caminhão
			SIZE_Z = 170;
			SIZE_Y = 45;
			SIZE_X = 40;
			SPEED = random.nextInt(5);
		}
	}
	
	@Override
	public void draw(GL2 gl) {
		this.gl = gl;
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
	}
	
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
	
	public void move(){
		z += SPEED;
	}
	
}
