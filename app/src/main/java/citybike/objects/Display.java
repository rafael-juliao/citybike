package citybike.objects;

import java.awt.Font;

import com.jogamp.opengl.util.awt.TextRenderer;

import citybike.engine.GameConfiguration;

public class Display implements GameConfiguration{
    TextRenderer renderer;
    private Player player;

    public Display(Player player){
        this.player = player;
    }

    public void displayGameInformation() {
        displayScore();
        displayVehiclesBehind();
        displayDistanceLeft();
        displayVelocity();
    }

    public void displayScore() {
        renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 25));
        
        // Display Score
        renderer.beginRendering(850, 850);
        renderer.setColor(1.0f, 1.0f, 1.0f, 0.8f);
        renderer.draw("Score: "+player.score, 20, 775);
        renderer.endRendering();
        
    }
    
    public void displayVehiclesBehind() {
        renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 25));

        // Display total Vehicles Behind
        renderer.beginRendering(850, 850);
        renderer.setColor(1.0f, 1.0f, 1.0f, 0.8f);
        renderer.draw("Vehicles: "+player.total_vehicles_behind, 20, 750);
        renderer.endRendering();    
        
    }

    public void displayDistanceLeft() {
        renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 25));
        
        // Display Distance Left
        renderer.beginRendering(850, 850);
        renderer.setColor(1.0f, 1.0f, 1.0f, 0.8f);
        renderer.draw("Left: "+((int)((ROAD_SIZE - player.z)/MOTO_MAX_SPEED))+ " m", 20, 800);
        renderer.endRendering();    
    }

    public void displayVelocity() {
        renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 25));        
        
        //Display Velocity
        renderer.beginRendering(850, 850);
        renderer.setColor(1.0f, 1.0f, 1.0f, 0.8f);
        renderer.draw("Speed: "+(int)(player.speed * 3)+ " km/h", 20, 700);
        renderer.endRendering();    
    }

    public void displayPlayerLose() {
        renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 60));
        renderer.beginRendering(850, 850);
        renderer.setColor(1.0f, 0.0f, 0.0f, 0.8f);
        renderer.draw("Game Over!", 280, 700);
        renderer.endRendering();
    
    }

    public void displayPlayerWin() {
        renderer = new TextRenderer(
                new Font("SansSerif", Font.BOLD, 60));
        renderer.beginRendering(850, 850);
        renderer.setColor(0.0f, 1.0f, 0.0f, 0.8f);
        renderer.draw("You Win!", 280, 700);
        renderer.endRendering();
    }
    
}
