package pucrs.cg1.citybike;

import static com.jogamp.opengl.GL.GL_LINE_LOOP;

import com.jogamp.opengl.GL2;

/**
 * in role x, y, z, represents the center;
 * */
public class Role extends GameObject {
	
	float radius;
	
	public void draw(GL2 gl) {
		this.gl = gl;
		
		gl.glBegin(GL_LINE_LOOP);
		for (int i = 0; i < 360; i++) {
			float theta = 2.0f * 3.1415926f * i / 360;
			float x = (float) (radius * Math.cos(theta));
			float y = (float) (radius * Math.sin(theta));
			gl.glVertex3f(x + this.x, 0, z + this.z );
		}
		
		gl.glEnd();
		/***/
	}
	
	
	@Override
	public float[][] get2DSquare() {
		// TODO Auto-generated method stub
		return super.get2DSquare();
	}
}
