package pucrs.cg1.citybike.objects;

import java.util.ArrayList;
import java.util.Random;

import com.jogamp.opengl.GL2;

import pucrs.cg1.citybike.engine.GameObject;

public class Cenario extends GameObject {

	private static final int TOTAL_PIRAMIDES = 150;

	private ArrayList<Piramide> piramides = new ArrayList<>();
	public Cenario() {
		Random random = new Random();
		for( int i = 0; i < TOTAL_PIRAMIDES; i++){
			Piramide piramide = new Piramide();
			piramide.z = random.nextInt(ROAD_SIZE);
			piramide.x = random.nextInt(2) % 2 == 0? 285 + random.nextInt(100): -850 + random.nextInt(100);
			piramide.y = -10;
			piramide.size = 50 +random.nextInt(500);
			piramides.add(piramide);
		}
		y = -6;
	}
	@Override
	public void draw(GL2 gl) {
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(0.4f, 0.4f, 0.0f);
		
		gl.glVertex3f( x - (ROAD_WIDTH_SIZE*2), y , z + 2 * ROAD_SIZE);
		gl.glVertex3f( x - (ROAD_WIDTH_SIZE*2), y , z);
		gl.glVertex3f( x + (ROAD_WIDTH_SIZE*2), y , z);
		gl.glVertex3f( x + (ROAD_WIDTH_SIZE*2), y , z + 2 * ROAD_SIZE);
		
		gl.glEnd();
		
		for(Piramide p: piramides){
			p.draw(gl);
		}
	}
	


	
}
