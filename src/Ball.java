/**
 * Description	:AI Batting Game
 * Copyright	:Copyright (c) 2016
 * Created on	:2016
 * @version 	:1.0
 */
public class Ball extends GameEntity {
	
	private int dx;		//>>
	private int dy;		//>>
	
        private Bat bat;
        
	public Ball(int gameWidth, int gameHeight, int size, int startX, int startY, int speed,Bat bat){
		super(gameWidth, gameHeight, size, size, startX, startY, speed);
		this.dx = this.speed;		//>>
		this.dy = this.speed;		//>>
                this.bat=bat;
	}
	
	/**
	 * Update ball location
	 */
	public void update(){		
		x = x + dx;
		y = y + dy;
		
		if ( x + this.width >= gameWidth  ) {
			dx = -this.speed;
		}
		
		if ( x <= 0 ){
			dx = +this.speed;
		}
		
		if ( y + this.height  >= gameHeight ){			
			dy = -this.speed;
		}
		
		if ( y <= 0 ){
			dy = +this.speed;
		}	
                bat.moveBat(this.x, this.y, dx, dy, this.speed);
	}
	
	public void reverse(){
		dy = -this.speed;
	}
	
	
}
