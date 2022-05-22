package citybike.objects;

import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import java.util.Random;

import citybike.engine.GameObject;

import com.jogamp.opengl.GL2;

/**
 * in role x, y, z, represents the center;
 * */
public class Hole extends GameObject {
    
    public float radius;
    
    public Hole(){
        Random rand = new Random();
        radius = 2 + ( rand.nextFloat() * 10 );
        x = -(WIDTH/6) + ( rand.nextFloat() * (WIDTH/3) );
        z = rand.nextFloat() * ROAD_SIZE;
        y = 5;
    }

    @Override
    public void draw(GL2 gl){
        this.gl = gl;

        gl.glMatrixMode(GL_PROJECTION);
        gl.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        
        int i;
        int triangleAmount = 100; 
        float twicePi = (float)(2.0f * Math.PI);
        
        gl.glBegin(GL2.GL_LINES);
        gl.glLineWidth(5.0f);

        for(i = 0; i <= triangleAmount; i++) {
            gl.glVertex3f(x, y, z);
            double xx = x + (radius * Math.cos(i * twicePi / triangleAmount));
            double zz = z + (radius * Math.sin(i * twicePi / triangleAmount));
            gl.glVertex3d(xx, y, zz);
        }

        gl.glMatrixMode(GL_MODELVIEW);
        gl.glEnd();
    }
    
    @Override
    public float[][] get2DSquare() {
        float[][] square = new float[4][2];
        
        square[DL][X] = x - (radius);
        square[DL][Z] = z - (radius);
        
        square[DR][X] = x + (radius);
        square[DR][Z] = z - (radius);
        
        square[UL][X] = x - (radius);
        square[UL][Z] = z + (radius);
        
        square[UR][X] = x + (radius);
        square[UR][Z] = z + (radius);
        
        return square;
    }
}
