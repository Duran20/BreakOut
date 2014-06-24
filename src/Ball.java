import javax.swing.ImageIcon;


public class Ball extends Sprite implements Commons {

   private int xdir;//�������� �� �
   private int ydir;//�������� �� �
   private int speed =1;

 //  protected String ball = "E:/���� 4 �������/project/images/ball.png";

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
   	//������� ������
    public void move()
    {
      x += xdir;
      y += ydir;
      
      //�������� � ����� ������
      if (x == 0) {
    	  
        setXDir(speed);
      }
    //�������� � ������ ������
     if (x == BALL_RIGHT) {
        setXDir(-speed);
      }
   //�������� �� �������
      if (y == 0) {
        setYDir(speed);
      }
    }

    //����������� � �������������� ���������
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