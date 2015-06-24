package pucrs.cg1.citybike;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

public class Road extends GameObject{
	
	ArrayList<Car> cars;
	ArrayList<Role> roles;
	
	
	private static final int
	DISTANCE_SIZE = 50 * 60 * 5,
	WIDTH_SIZE = 300,
	WIDTH_ROAD_DETAIL = 10,
	TOTAL_DETAILS = 50,
	DETAIL_SIZE = 50;

	public Road(){
		y-=5;
	}

	@Override
	public void draw(GL2 gl) {
		this.gl = gl;
		gl.glBegin(GL2.GL_QUADS);

		gl.glColor3f(0.5f, 0.5f, 0.5f);
		
		gl.glVertex3f( x - (WIDTH_SIZE/2), y , z + DISTANCE_SIZE);
		gl.glVertex3f( x - (WIDTH_SIZE/2), y , z);
		gl.glVertex3f( x + (WIDTH_SIZE/2), y , z);
		gl.glVertex3f( x + (WIDTH_SIZE/2), y , z + DISTANCE_SIZE);

		gl.glColor3f(1f, 1f, 1f);
		for( int i = 0; i < DISTANCE_SIZE / DETAIL_SIZE; i+=2){			
			
			gl.glVertex3f(-50+ x - (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE + DETAIL_SIZE);
			gl.glVertex3f(-50+ x - (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE );
			gl.glVertex3f(-50+ x + (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE );
			gl.glVertex3f(-50+ x + (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE + DETAIL_SIZE);
			
			gl.glVertex3f(50+ x - (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE + DETAIL_SIZE);
			gl.glVertex3f(50+ x - (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE );
			gl.glVertex3f(50+ x + (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE );
			gl.glVertex3f(50+ x + (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE + DETAIL_SIZE);
			
		}
		gl.glEnd(); 

		
	}


	
}
