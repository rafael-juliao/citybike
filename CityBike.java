package pucrs.cg1.citybike;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import pucrs.cg1.citybike.engine.ColisionDetector;
import pucrs.cg1.citybike.engine.GameConfiguration;
import pucrs.cg1.citybike.engine.GameEngine;
import pucrs.cg1.citybike.engine.GameEngine.CameraCallback;
import pucrs.cg1.citybike.engine.GameEngine.OnUpdateCallback;
import pucrs.cg1.citybike.objects.Car;
import pucrs.cg1.citybike.objects.Player;
import pucrs.cg1.citybike.objects.Road;
import pucrs.cg1.citybike.objects.Role;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

/**
 * Um jogo onde o player é como um motorista de uma motocicleta.
 * O Objetivo do jogo é desviar de todos os carros e chegar até o final
 * Se o motorista bater em um carro ele perde, se bater na parede perde.
 * Controle para esquerda e direita nas setas < >
 * */

/**
 * 
1)   O jogo deve possuir objetivo, inicio e fim e controle de vitória e derrota. A professora deverá classificá-lo como um jogo.

2)  O jogador deve interagir com o jogo, ou seja simulação não é considerada jogo.

3)  Possuir pelo menos 4 componentes geométricos diferentes.
	1 - Player é uma caixa
	2 - Tem uma Piramide
	3 - Tem um Cubo
	
	A idéia é criar alguns carros ou caminhoes e talvez outras motos, 
	e fazer a intersecção como se fosse caixa.

4) Possuir animação de câmera(DONE) e de pelo menos 3 objetos diferentes.

5)  Possuir interação com mouse e/ou teclado. DONE

 * */
public class CityBike implements GameConfiguration {
	
	GameEngine engine = new GameEngine();
	Object carsLock = new Object();
		
	//Objects
	Player player = new Player();
	Road road = new Road();	
	ArrayList<Car> cars = new ArrayList<>();
	ArrayList<Role> roles = new ArrayList<>();
	
	ActionListener actionAddCar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Random random = new Random();
			int pos = random.nextInt(3);
			float x = 0.0f;
			if(pos == 0) // left
				x = 90.0f;
			else if(pos == 1) //center
				x = -10.0f;
			else
				x = -110.0f; //rigth
			cars.add(new Car(x, player.z + (float)( CAMERA_DISTANCE * 1.5)  ));
		}
	};
	Timer timerAddCar = new Timer(TIME_TO_ADD_CAR, actionAddCar);;
		
	
	//Game Lifecycle Methos
	public CityBike() { 
	
        engine.setUpdateCallback(updateCallback);
        engine.setControls(gameControl);
        engine.setCameraCallback(cameraCallback);
        engine.start();
        
		timerAddCar.start();
		addRoles();
	}
    
	private void addRoles() {
		for( int i = 0; i < TOTAL_ROLES; i++){
			roles.add( new Role( ) );
		}
		
	}

	OnUpdateCallback updateCallback = new OnUpdateCallback() {
		
		@Override
		public void onUpdate(GL2 gl) {
			synchronized (carsLock) {
				if( player.z > DISTANCE_SIZE)
					playerWin();
				for( Car car: cars ){
					if( ColisionDetector.isColiding(player, car)){
						playerLose();
						return;
					}
					
					for( Car car_aux: cars ){
						if( !car_aux.equals(car) && ColisionDetector.isColiding(car, car_aux)){
							//Se os carros baterem o de traz freia
							if( car.z > car_aux.z ){
								car_aux.speedDown();
							}else{
								car.speed-=1;
							}
						}
					}
					
					car.move();
					car.draw(gl);
				}
				
				for( Role role: roles ){
					if( ColisionDetector.isColiding(player, role)){
						playerLose();
					}

					role.draw(gl);
				}
				road.draw(gl);
				player.move();
				player.draw(gl);
			}			
			
		}

		private void playerLose() {
			restartGame();
		}

		private void playerWin() {
			
		}

		private void restartGame() {
			synchronized (carsLock) {
				cars.clear();
				player = new Player();
			}
		}
		
		
	};
	
	
	//Camera Callback
	CameraCallback cameraCallback = new CameraCallback() {		
		@Override
		public void updateCamera(GLU glu) {
	        glu.gluLookAt(
	        		player.x , player.y +35, player.z - 50, 	//WHERE
	        		player.x, player.y+35, player.z,	//AT 
	        		0, 1, 0 	//SKY
	        		);
		}
	};
	
		
	//Game Control
	KeyListener gameControl = new KeyListener() {
		
		@Override public void keyTyped(KeyEvent e) { }
		@Override public void keyReleased(KeyEvent e) { }
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()){
				case ESC:
					//leave game
					System.exit(0);
					break;
				
				case LEFT_CONTROL:
					player.moveLeft();
					break;
					
				case RIGHT_CONTROL:
					player.moveRight();					
					break;
					
					
				case UP_CONTROL:
					player.speedUp();
					break;
					
				case DOWN_CONTROL:
					player.speedDown();
					break;
					
					
				case PRINT_LOG:
					
					break;
				
			}
		}
	};
	
}
