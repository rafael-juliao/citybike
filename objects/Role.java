package pucrs.cg1.citybike.objects;


import java.util.Random;

import pucrs.cg1.citybike.engine.GameObject;

import com.jogamp.opengl.GL2;

/**
 * in role x, y, z, represents the center;
 * */
public class Role extends GameObject {
	
	float radius;
	
	public Role(){
		Random rand = new Random();
		radius = 2 + ( rand.nextFloat() * 5 );
		x = 20 + ( rand.nextFloat() * (WIDTH -40) );
		z = rand.nextFloat() * DISTANCE_SIZE;
		y = 1;
	}
	
	public void draw(GL2 gl) {
		this.gl = gl;
		
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0f, 0f, 0f);
		for (int i = 0; i < 360; i++) {
			float theta = 2.0f * 3.1415926f * i / 360;
			float x = (float) (radius * Math.cos(theta));
			float y = (float) (radius * Math.sin(theta));
			gl.glVertex3f(x + this.x, 0, y + this.z );
		}
		
		gl.glEnd();
		/***/
	}
	
	@Override
	public float[][] get2DSquare() {
		float[][] square = new float[4][2];
		
		square[DL][X] = x - (radius/2);
		square[DL][Z] = z - (radius/2);
		
		square[DR][X] = x + (radius/2);
		square[DR][Z] = z - (radius/2);
		
		square[UL][X] = x - (radius/2);
		square[UL][Z] = z + (radius/2);
		
		square[UR][X] = x + (radius/2);
		square[UR][Z] = z + (radius/2);
		
		return square;
	}
}
