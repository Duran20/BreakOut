import javax.swing.ImageIcon;


public class Ball extends Sprite implements Commons {

   private int xdir;//смещение по х
   private int ydir;//смещение по у
   private int speed =1;

 //  protected String ball = "E:/Лабы 4 семестр/project/images/ball.png";

   public Ball(int x,int y,int width,int heigth,int radious,String path) {
   		super(x,y,width,heigth,radious, path);
   		xdir = 1;
   		ydir = -1;

    // ImageIcon ii = new ImageIcon(this.getClass().getResource(ball));
    // image = ii.getImage();

  

     resetState();
    }
   
   //speed
   public void setSpeed(int speed){
	   this.speed=speed;
   }
   	//двигает объект
    public void move()
    {
      x += xdir;
      y += ydir;
      
      //ударение в левую стенку
      if (x == 0) {
    	  
        setXDir(speed);
      }
    //ударение в правую стенку
     if (x == BALL_RIGHT) {
        setXDir(-speed);
      }
   //ударение об потолок
      if (y == 0) {
        setYDir(speed);
      }
    }

    //возвращение в первоначальное положение
    public void resetState() 
    {
      x = 230;
      y = 355;
    }

    public void setXDir(int x)
    {
      xdir = x;
    }

    public void setYDir(int y)
    {
      ydir = y;
    }

    public int getYDir()
    {
      return ydir;
    }
}