package citybike.objects;

import java.util.ArrayList;

import citybike.engine.ColisionDetector;
import citybike.engine.GameObject;

import com.jogamp.opengl.GL2;

public class Road extends GameObject{
        
    ArrayList<Hole> holes = new ArrayList<>();
    private Player player;

    public Road(Player player){
        this.player = player;
        y = 1;
        for (int i = 0; i < TOTAL_HOLES; i++)
            holes.add(new Hole());
    }

    @Override
    public void draw(GL2 gl) {
        this.gl = gl;
        gl.glBegin(GL2.GL_QUADS);

        gl.glColor3f(0.5f, 0.5f, 0.5f);
        
        gl.glVertex3f( x - (WIDTH_ROAD_SIZE/2), y , z + ROAD_SIZE);
        gl.glVertex3f( x - (WIDTH_ROAD_SIZE/2), y , z);
        gl.glVertex3f( x + (WIDTH_ROAD_SIZE/2), y , z);
        gl.glVertex3f( x + (WIDTH_ROAD_SIZE/2), y , z + ROAD_SIZE);

        gl.glColor3f(1f, 1f, 1f);
        for( int i = 0; i < ROAD_SIZE / DETAIL_SIZE; i+=2){            
            
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
        

        for (Hole hole: holes)
            hole.draw(gl);
    }

    public boolean checkColision() {

        for (Hole hole: holes)
            if (ColisionDetector.isColiding(player, hole))
                player.tilt(hole.radius);
        
        if (player.x > -MOTO_SIZE_X + WIDTH_ROAD_SIZE/2 || player.x < (-WIDTH_ROAD_SIZE)/2)
            return true;
        
        return false;

    }

    
}
