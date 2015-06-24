package pucrs.cg1.citybike.engine;

import java.awt.event.KeyEvent;

public interface GameConfiguration{
	

	//Window
	static final int WIDTH = 800, HEIGHT = 640, FPS = 60;

	//Game Settings
	static final int TIME_TO_ADD_CAR = 500;
	static final int MINIMUM_SPEED = 4;
	static final int TOTAL_ROLES = 50;
	
	//CAMERA DISTANCE
	static final int CAMERA_DISTANCE = 1500;
	
	//Player Settings
	static final int MOTO_MAX_SPEED = 50;
	static final int MOTO_SIZE_X = 10, MOTO_SIZE_Y = 20, MOTO_SIZE_Z = 30;
	static final float COLOR_RED = 0.5f, COLOR_GREEN = 0.0f, COLOR_BLUE = 0.0f;
	
	//Control
	static final int LEFT_CONTROL = 37, 
			UP_CONTROL = 38,
			RIGHT_CONTROL = 39,
			DOWN_CONTROL = 40,
			SPACE = KeyEvent.VK_SPACE,
			ESC = 27,
			PRINT_LOG = KeyEvent.VK_P;
	
	
	//Road
	static final int
	ROAD_SIZE =  5 * 60 * MOTO_MAX_SPEED,
	ROAD_WIDTH_SIZE = 300,
	WIDTH_ROAD_DETAIL = 10,
	TOTAL_DETAILS = 50,
	DETAIL_SIZE = 50;
	
	
}
