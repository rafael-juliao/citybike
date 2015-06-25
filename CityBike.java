package pucrs.cg1.citybike;

import java.awt.Dimension;
import java.awt.Font;
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
import pucrs.cg1.citybike.objects.Cenario;
import pucrs.cg1.citybike.objects.Player;
import pucrs.cg1.citybike.objects.Road;
import pucrs.cg1.citybike.objects.Role;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;

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
	
	TextRenderer renderer;
		
	//Objects
	Player player = new Player();
	Road road = new Road();	
	ArrayList<Car> cars = new ArrayList<>();
	ArrayList<Role> roles = new ArrayList<>();
	Cenario cenario = new Cenario();
	
	ActionListener actionAddCar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(player.speed > 10)
				cars.add(new Car(player.z));
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
		public void onUpdate(GL2 gl, GLU glu) {
			synchronized (carsLock) {
				timerAddCar.setDelay(TIME_TO_ADD_CAR - 5 * player.speed);
				road.draw(gl);
				cenario.draw(gl);
				player.move();
				player.draw(gl);
				
				for( Car car: cars ){
					for( Car car_aux: cars ){
						if( !car_aux.equals(car) && ColisionDetector.isColiding(car, car_aux)){
							//Se os carros baterem o de traz freia
							if( car.z > car_aux.z ){
								car_aux.speed--;
								if( car.speed == car_aux.speed)
									car.speed++;
							}else{
								car.speed--;
								if( car_aux.speed == car.speed)
									car_aux.speed++;	
							}
						}
					}
					car.move();
					car.draw(gl);
					
					if( ColisionDetector.isColiding(player, car)){
						playerLose();
						return;
					}
					
					if( car.z < player.z && !car.isBehind){
						car.isBehind = true;
						player.score+= player.speed;
						player.total_cars_behind++;
					}
				}
				
				
				for( Role role: roles ){
					if( ColisionDetector.isColiding(player, role)){
						player.tilt(role.radius);
					}
					role.draw2D(gl, glu);
				}
				
				if( player.z > ROAD_SIZE)
					playerWin();
				
				if( player.x > -MOTO_SIZE_X + ROAD_WIDTH_SIZE/2 || player.x < (-ROAD_WIDTH_SIZE)/2){
					playerLose();
				}
				
				DisplayInformation();

			}			
			
		}

		private void DisplayInformation() {
			renderer = new TextRenderer(
					new Font("SansSerif", Font.BOLD, 25));
			
			// Display Score
			renderer.beginRendering(850, 850);
			renderer.setColor(1.0f, 1.0f, 1.0f, 0.8f);
			renderer.draw("Score: "+player.score, 20, 775);
			renderer.endRendering();
			
			// Display total Cars Behind
			renderer.beginRendering(850, 850);
			renderer.setColor(1.0f, 1.0f, 1.0f, 0.8f);
			renderer.draw("Cars: "+player.total_cars_behind, 20, 750);
			renderer.endRendering();	
			
			
			// Display Distance Left
			renderer.beginRendering(850, 850);
			renderer.setColor(1.0f, 1.0f, 1.0f, 0.8f);
			renderer.draw("Left: "+((ROAD_SIZE - player.z)/10000)+ " km", 20, 800);
			renderer.endRendering();	
			
			//Display Velocity
			renderer.beginRendering(850, 850);
			renderer.setColor(1.0f, 1.0f, 1.0f, 0.8f);
			renderer.draw("Speed: "+((int)(4.8745 * player.speed))+ " km/h", 20, 700);
			renderer.endRendering();	
			
			
		}

		private void playerLose() {
			engine.stop();
			renderer = new TextRenderer(
					new Font("SansSerif", Font.BOLD, 60));
			renderer.beginRendering(850, 850);
			renderer.setColor(1.0f, 0.0f, 0.0f, 0.8f);
			renderer.draw("Game Over!", 280, 700);
			renderer.endRendering();
		
		}

		private void playerWin() {
			engine.stop();
			renderer = new TextRenderer(
					new Font("SansSerif", Font.BOLD, 60));
			renderer.beginRendering(850, 850);
			renderer.setColor(0.0f, 1.0f, 0.0f, 0.8f);
			renderer.draw("You Win!", 280, 700);
			renderer.endRendering();
		}
		
	};
	

	private void restartGame() {
		synchronized (carsLock) {
			cars.clear();
			player = new Player();
			engine.restart();
		}
	}
	
	//Camera Callback
	CameraCallback cameraCallback = new CameraCallback() {		
		@Override
		public void updateCamera(GLU glu) {
			float x_aux = (player.x) / 7;
	        glu.gluLookAt(
	        		player.x +(MOTO_SIZE_X/2) + x_aux , player.y +35, player.z - 50, 	//WHERE
	        		player.x+(MOTO_SIZE_X/2) + x_aux, player.y+35, player.z,	//AT 
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
					
				case SPACE:
					restartGame();
					break;
				case PRINT_LOG:
					
					break;
			}
		}
	};
	
}
