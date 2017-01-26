
import static java.lang.Math.abs;
import java.util.Random;

/**
 * Description	:AI Batting Game
 * Copyright	:Copyright (c) 2016
 * Created on	:2016
 * @version 	:1.0
 */
public class Bat extends GameEntity {

	private int delta;
	
	public Bat(int gameWidth, int gameHeight, int width, int height, int startX, int startY, int speed){
		super(gameWidth, gameHeight, width, height, startX, startY, speed);
	}
	
        private boolean batMove=false;
        private boolean hitted=false;
        private int xMovement=0;    // X Side Bat movement speed
        private int endX=0;         // final X coordinate to hit the bat
        private int interval=200;   // distance interval from bat
        
        public void ballHitted(int x){
            System.out.println("MOVING X: "+(this.x+width/2)+ " this.x:"+this.x+" endX:"+endX);
            hitted=true;
           // endX=0;
            System.out.println("HITTED : "+x);
            
        }

        // AI Part of the GAME
        
        public void moveBat(int x, int y, int dx, int dy , int speed){
            // gathering data before a intreval distance from bat
            // this.y = Y coordinate of the bat
            // this.x = X coordinate of the bat
            // y = Y coordinate of the ball
            // x = X coordinate of the bat
            // dx,dy = X,Y variations of the Ball
            // speed = speed of the ball
            
             Random randomNo = new Random();
            if(gameHeight-y==interval) 
            {
                if(batMove==false)
                {
                    // when ball is moving down, initially set variables
                    batMove=true;
                    hitted=false;
                    System.out.println("DOWN");
                    //dx - X variation of the Ball, so if dx is negative, then it move Right to Left
                    
                    // Type A
                    if(dx<0){
                        // Type A - Figure 1
                        if(this.y-y>x){
                            endX=abs(this.y-(y+x));
                        }
                        // Type A - Figure 2
                        else{
                            endX=x-(this.y-y);
                        }
                        // self correcting the value
                       
                        int randomVal = randomNo.nextInt(width/2)+width/2-10 ; // endX random adjustment
                        endX-=randomVal; 
                    }
                    // Type B
                    else
                    {
                        // Type B - Figure 1
                        if(this.y-y>gameWidth-x){
                            endX=gameWidth-(this.y-(y+(gameWidth-x)));
                        }
                        // Type B - Figure 2
                        else{
                            endX=x+(this.y-y);
                        }
                        int randomVal = randomNo.nextInt(width/2) ; // endX random adjustment
                        endX-=randomVal;
                    }
                    System.out.println("Cal : "+endX);
                    
                    // Calculation of the Ball Time to reach the bat
                    int horiTime=(int)(interval)/speed;
                    
                    //Type C
                    if(this.x>endX){
                        // Avg Speed of the Bat for the Movement
                       xMovement=(this.x-endX+width/2)/horiTime; 
                    }
                    //Type D
                    else{
                        // Avg Speed of the Bat for the Movement
                        xMovement=(endX-this.x-width/2)/horiTime; 
                    }
                    //Selft Correcting the Avg Speed of the Bat
                    xMovement+=2;
                    System.out.println("xMovement : "+xMovement);
                
                }
                else
                {
                    batMove=false;
                    System.out.println("UP\n");
                }
                
            }
            
            if(batMove==true && hitted==false){
                
                //Also Type C
                if(this.x>endX){
                    // Move Left Side, decrement of the Bat X Coordinate
                    this.x-=xMovement;
                }
                // Also Type D
                else{
                    // Move Right Side, increment of the Bat X Coordinate
                    this.x+=xMovement;
                }
            }
         
        }
        
	public void setLeft(){
		this.delta = -(this.speed);
	}
	
	public void setRight(){
		this.delta = (this.speed); 
	}
	
	public void update(){		
		this.x = this.x + this.delta;
		
		// Check boundary
		if ( this.x <= 0 ){
			this.x = 0;
		}
		
		if ( this.x >= this.gameWidth - this.width){
			this.x = this.gameWidth - this.width;
		}
		
		this.delta = 0;
	}	
}
