package pucrs.cg1.citybike;

import com.jogamp.opengl.GL2;

public class Car extends GameObject {
	
	private static final int SIZE_X = 30, SIZE_Y = 30, SIZE_Z = 45;
	
	public Car(float posX, float posY, float posZ) {
		x = posX;
		y = posY;
		z = posZ;
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
	
	@Override
	public float[][] get2DSquare() {
		float[][] square = new float[4][2];
		
		square[DL][X] = x;
		square[DL][Z] = z;
		
		square[DR][X] = x + SIZE_X;
		square[DR][Z] = z;
		
		square[UL][X] = x;
		square[UL][Z] = z + SIZE_Z;
		
		square[UR][X] = x + SIZE_X;
		square[UR][Z] = z + SIZE_Z;
		
		return square;
	}
	
}
