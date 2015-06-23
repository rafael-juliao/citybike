package pucrs.cg1.citybike;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

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
public class CityBike implements GLEventListener {
	
	//Starter
	public static void main(String[] args){
		CityBike openGLControl = new CityBike(getDefaultCapabilites());
	}
	private static GLCapabilities getDefaultCapabilites() {
		GLCapabilities capabilities = new GLCapabilities(GLProfile.getDefault());
		capabilities.setRedBits(8);
		capabilities.setBlueBits(8);
		capabilities.setGreenBits(8);
		capabilities.setAlphaBits(8);
		return capabilities;
	}
	
	//Window
	private static final int WIDTH = 800, HEIGHT = 640, FPS = 60;
	
	//Control
	private static final int LEFT_CONTROL = 37, 
			RIGHT_CONTROL = 39, 
			ESC = 27, 
			PRINT_LOG = KeyEvent.VK_P;

	//Dependencies
    private GLU glu;
	private FPSAnimator fpsAnimator;
	private GLCanvas canvas;
	
	//Objects
	private JFrame window;
	private ArrayList<GameObject> gameObjects;
	private GL2 gl;
	private Player player;
	private Road road;
	

	public void start(GLCapabilities capabilities){
		player = new Player();
		road = new Road();
		gameObjects = new ArrayList<>();
		window = new JFrame();
        canvas = new GLCanvas(capabilities);
		glu = new GLU();
		fpsAnimator = new FPSAnimator(canvas, FPS);
		
		//Configure Canvas
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.addKeyListener(dodgeControl);
		canvas.addGLEventListener(this);
		
		//Configure Window
		window.setLocation(300, 50);
		window.getContentPane().add(canvas);
		window.setUndecorated(true); // Remove title bar
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(canvas);
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
		window.requestFocus();
        canvas.requestFocus();
		
	}
		
	
	//Game Lifecycle Methos
	public CityBike(GLCapabilities capabilities) { start(capabilities); }
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
    public void dispose(GLAutoDrawable drawable) { }
	
    public void init(GLAutoDrawable drawable) {
		gl = drawable.getGL().getGL2();
    	drawable.setGL(gl);
    	
        // Global settings.
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); 
		gl.glClearDepth(1.0f);		
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

        // Start animator (which should be a field).
        fpsAnimator.start();
        //setCamera(gl, glu, 100);
    }
    
    public void display(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // Clear color and depth buffers
		gl.glMatrixMode(GL2.GL_MODELVIEW);     // To operate on model-view matrix
		  
		for( GameObject gameObject : gameObjects){
			gameObject.draw(gl);
		}
		
		road.draw(gl);
		player.move();
		player.draw(gl);
        setCamera(gl);

    }
	
	private void setCamera(GL2 gl) {
		// Change to projection matrix.
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        float widthHeightRatio = (float) canvas.getWidth() / (float) canvas.getHeight();
        glu.gluPerspective(45, widthHeightRatio, 1, 1000);
        glu.gluLookAt(
        		player.x , player.y +35, player.z - 50, 	//WHERE
        		player.x, player.y+35, player.z,	//AT 
        		0, 1, 0 	//SKY
        		);

        // Change back to model view matrix.
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
		
	}

	
	//Game Control
	KeyListener dodgeControl = new KeyListener() {
		
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
					
				case PRINT_LOG:
					
					break;
				
			}
		}
	};
	
}
