package citybike;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import citybike.engine.GameConfiguration;
import citybike.engine.GameEngine;
import citybike.engine.GameEngine.CameraCallback;
import citybike.engine.GameEngine.GameLoopCallback;
import citybike.objects.Camera;
import citybike.objects.Cenario;
import citybike.objects.Display;
import citybike.objects.Player;
import citybike.objects.Road;
import citybike.objects.VehicleList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;


public class CityBike implements GameConfiguration {
    
    GameEngine engine = new GameEngine();
    Object gameLock = new Object();
    
    //Objects
    Player player = new Player();
    Road road = new Road(player);
    Display display = new Display(player);
    VehicleList vehicles = new VehicleList(player);
    Cenario cenario = new Cenario();
    Camera camera = new Camera(player);
    
    //Game Lifecycle Methos
    public CityBike() {
        engine.setGameLoopCallback(gameLoopCallback);
        engine.setControls(gameControl);
        engine.setCameraCallback(cameraCallback);
    }

    private void gameLoop(GL2 gl) {

        road.draw(gl);
        cenario.draw(gl);

        player.move();
        player.draw(gl);
        
        vehicles.update(gl);
        
        if (road.checkColision() || vehicles.checkColision())
            playerLose();
        
        if (player.z > ROAD_SIZE)
            playerWin();
        
        display.displayGameInformation();
    }

    
    private void playerLose() {
        engine.stop();
        display.displayPlayerLose();
    }

    private void playerWin() {
        engine.stop();
        display.displayPlayerWin();
    }

    public void startGame() {
        engine.start();
        vehicles.startGenerateVehicles();
    }

    private void restartGame() {
        synchronized (gameLock) {
            vehicles.clear();
            player.reset();
            engine.restart();
        }
    }

    // Game Loop Callback
    GameLoopCallback gameLoopCallback = new GameLoopCallback() {
        @Override public void onUpdate(GL2 gl, GLU glu) { synchronized (gameLock) { gameLoop(gl); } }
    };
    
    // Camera Callback
    CameraCallback cameraCallback = new CameraCallback() {        
        @Override public void updateCamera(GLU glu) { camera.updateCamera(glu); }
    };
    
    //Game Control
    KeyListener gameControl = new KeyListener() {
        
        @Override public void keyTyped(KeyEvent e) { }

        @Override public void keyPressed(KeyEvent e) {

            player.keyPressed(e.getKeyCode());

            switch(e.getKeyCode()) {
                case CTRL_LEAVE:
                    //leave game
                    System.exit(0);
                    break;
                    
                case CTRL_RESTART:
                    restartGame();
                    break;
            }

        }

        @Override public void keyReleased(KeyEvent e) { 
            player.keyReleased(e.getKeyCode());
        }
    };
    
}
