package citybike.objects;
import com.jogamp.opengl.glu.GLU;

import citybike.engine.GameObject;

public class Camera extends GameObject {
    
    private Player player;

    public Camera(Player player) {
        this.player = player;
    }

    public void updateCamera(GLU glu) {
        float x_aux = (player.x) / 7;

        double cameraPosX = player.x +(MOTO_SIZE_X/2) + x_aux;
        double cameraPosY = player.y + 35;
        double cameraPosZ = player.z - 50;

        double lookAtX = player.x+(MOTO_SIZE_X/2) + x_aux;
        double lookAtY = player.y + 35;
        double lookAtZ = player.z;

        glu.gluLookAt(
            cameraPosX, cameraPosY, cameraPosZ,     //WHERE
            lookAtX, lookAtY, lookAtZ,    //Look At 
            0, 1, 0     //Point the Sky
        );
    }
}
