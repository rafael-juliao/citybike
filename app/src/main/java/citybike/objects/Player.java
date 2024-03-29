package citybike.objects;
import java.util.Random;
import citybike.engine.GameObject;
import com.jogamp.opengl.GL2;

public class Player extends GameObject{
    
    public float speed = 0;
    public float acceleration = 0;
    public int total_vehicles_behind = 0;
    public int score = 0;

    public boolean isTurningRight;
    public boolean isTurningLeft;
    public boolean isAccelerating;
    public boolean isBreaking;

    public void reset(){
        speed = 0;
        acceleration = 0;
        total_vehicles_behind = 0;
        score = 0;
        z = 0;
        x = 0;
    }
    
    public void tilt(float base){
        Random random = new Random();
        x+= ((-5) + random.nextInt(10)) * (base/2);
    }
    public void move(){
        z += speed;
        
        if(isTurningLeft)
            if(speed != 0)
                x +=  5 - speed/30;
        
        if(isTurningRight)
            if(speed != 0)
                x-= 5 - speed/30;
        
        if(isAccelerating){
            if(speed < 10)
                acceleration = 2;
            else
                acceleration = 20/speed;
            if( speed < MOTO_MAX_SPEED )
                speed+= acceleration;
        }
        if(isBreaking){
            if( speed <= 1 ) speed = 0;
            if( speed > 1) speed-=1;
        }
            
        
    }

    
    //DRAWING METHODS
    /**
     * Eight Vertices
     *     G   H
     *     C   D
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
    

    
    public void keyPressed(int keyCode) {

        switch(keyCode) {

            case CTRL_LEFT:
                isTurningLeft = true;
                break;
                
            case CTRL_RIGHT:
                isTurningRight = true;                    
                break;
                
            case CTRL_ACCELERATE:
                isAccelerating = true;
                break;
                
            case CTRL_BREAK:
                isBreaking = true;
                break;

        }

    }

    public void keyReleased(int keyCode) {

        switch(keyCode) {

            case CTRL_LEFT:
                isTurningLeft = false;
                break;
            
            case CTRL_RIGHT:
                isTurningRight = false;                    
                break;
                
            case CTRL_ACCELERATE:
                isAccelerating = false;
                break;
                
            case CTRL_BREAK:
                isBreaking = false;
                break;

        }
        
    }
    
}
