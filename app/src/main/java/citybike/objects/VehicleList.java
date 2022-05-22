package citybike.objects;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.jogamp.opengl.GL2;

import citybike.engine.ColisionDetector;
import citybike.engine.GameObject;

public class VehicleList extends GameObject {

    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private Player player;

    public VehicleList(Player player) {
        this.player = player;
    }
    
    ActionListener actionAddVehicle = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(player.speed > 10)
            vehicles.add(new Vehicle(player.z));
        }
    };

    Timer timerAddVehicle = new Timer(TIME_TO_ADD_CAR, actionAddVehicle);

    public void startGenerateVehicles(){
        timerAddVehicle.start();
    }


    public void clear() {
        vehicles.clear();
    }

    public void update(GL2 gl) {
        timerAddVehicle.setDelay((int)(TIME_TO_ADD_CAR - 5 * player.speed));

        for (Vehicle vehicle: vehicles) {
            vehicle.move();
            vehicle.draw(gl);
            avoidColisionWithOtherVehicles(vehicle);
            checkIfPlayerHasOvertakeVehicle(vehicle);
        }

    }

    private void checkIfPlayerHasOvertakeVehicle(Vehicle vehicle) {
        
        if (vehicle.z < player.z && !vehicle.isBehind) {
            vehicle.isBehind = true;
            player.score+= player.speed;
            player.total_vehicles_behind++;
        }

    }

    private void avoidColisionWithOtherVehicles(Vehicle vehicle){ 
        
        for( Vehicle otherVehicle: vehicles ) {
    
            if (otherVehicle.equals(vehicle) || !ColisionDetector.isColiding(vehicle, otherVehicle)) 
                continue;

            //Se os vehicleros baterem o de traz freia
            if (vehicle.z > otherVehicle.z) {
                otherVehicle.speed--;
                if (vehicle.speed == otherVehicle.speed)
                    vehicle.speed++;
            } else {
                vehicle.speed--;
                if (otherVehicle.speed == vehicle.speed)
                    otherVehicle.speed++;
            }
            
        }
    }

    public boolean checkColision() {

        for (Vehicle vehicle: vehicles)
            if (ColisionDetector.isColiding(player, vehicle))
                return true;
                    
        return false;
    }


}
