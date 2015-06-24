package pucrs.cg1.citybike.objects;

import java.util.Random;

import pucrs.cg1.citybike.engine.GameObject;

import com.jogamp.opengl.GL2;

public class Car extends GameObject {
	
	public int size_x, size_y, size_z;
	public int speed = 0;
	public int carType = 0;
	
	public Car(float posX, float posZ) {
		x = posX;
		z = posZ;
		Random random = new Random();
		
		int carType = random.nextInt(3);
		
		switch (carType) {
		case 1: //caminhao
			size_z = 170  + random.nextInt(250);
			size_y = 45 + random.nextInt(20);
			size_x = 40 + random.nextInt(30);
			speed = 12 + random.nextInt(2);
			break;
		case 2://Moto
			size_z = 50 + random.nextInt(20);
			size_y = 20 + random.nextInt(15);
			size_x = 10 + random.nextInt(10);
			speed = 10 + random.nextInt(10);	
			break;
		default: //carro
			size_z = 60 + random.nextInt(30);
			size_x = 30 + random.nextInt(20);
			size_y = 25 + random.nextInt(10);
			//Deixa os valores padrão
			speed = 12 + random.nextInt(6);
			break;
		}
	}
	
	@Override
	public void draw(GL2 gl) {
		this.gl = gl;
		gl.glBegin(GL2.GL_QUADS); 
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		
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
	
	public void vertA(){
		gl.glVertex3f( x , y , z);
	}
	
	public void vertB(){
		gl.glVertex3f( x + size_x , y, z);
	}
	
	public void vertC(){
		gl.glVertex3f( x , y, z + size_z);
	}
	
	public void vertD(){
		gl.glVertex3f( x + size_x, y, z + size_z);		
	}
	
	public void vertE(){
		gl.glVertex3f( x , y + size_y , z);
	}
	
	public void vertF(){
		gl.glVertex3f( x + size_x , y + size_y, z);
		
	}
	
	public void vertG(){
		gl.glVertex3f( x , y + size_y, z + size_z);
	}
	
	public void vertH(){
		gl.glVertex3f( x + size_x, y + size_y , z + size_z);		
	}
	
	public float[][] get2DSquare() {
		float[][] square = new float[4][2];
		
		square[DL][X] = x;
		square[DL][Z] = z;
		
		square[DR][X] = x + size_x;
		square[DR][Z] = z;
		
		square[UL][X] = x;
		square[UL][Z] = z + size_z;
		
		square[UR][X] = x + size_x;
		square[UR][Z] = z + size_z;
		
		return square;
	}
	
	public void move(){
		if(speed <= MINIMUM_SPEED ) speed = MINIMUM_SPEED;
			z += speed;
	}

	public void speedDown() {
		speed--;

	}
	
}
