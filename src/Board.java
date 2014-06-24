import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements Commons {

    Image ii;
    Timer timer;
    String message = "Game Over";
    Ball ball;
    Paddle paddle;
    ArrayList<Brick> bricks = null;
    String path;
    JLabel time = new JLabel("Time: ");
    JLabel score = new JLabel("Score: 0");
    Font font = null;
    boolean pause = true;
    
    
    boolean ingame = true;
    int timerId;
    int score1 = 0;
	int time_ml = 0;//милисек
	int level1 = 0;

	
    public Board(KeyListener keyl) {
    	level1++;
    	
    	font =null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Fclock.ttf"));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	font = font.deriveFont(Font.BOLD,22);

    	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	ge.registerFont(font);
        setDoubleBuffered(true);
        timer = new Timer(10, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ScheduleTask();
			}
		});
        
        gameInit();
        timer.start();
    }
    	
    	//оповещение панели о нажатии клавиши
        public void addNotify() {
            super.addNotify();
            
        }
        public void setBrics(File lvl){
        bricks = new ArrayList<>();
    	BufferedReader in =null;
    	StringBuffer sb=null;
    	 try {
    		 int posx =2;
    		 in= new BufferedReader(new FileReader( lvl.getAbsoluteFile()));
            //В цикле построчно считываем файл
            String s;
           // int k=0;
         
            while ((s = in.readLine()) != null) {
            	for(int posy = 0;posy<6;posy++){
 	                sb=new StringBuffer(s);
            		if(sb.charAt(posy)=='*'){
            			bricks.add( new Brick(posy*50, posx*20 ,50, 20, 5, ""));//размеры и положение
            		}
            	}             
            	posx++;
            }
        } catch (Exception e ) {
			// TODO Auto-generated catch block
		
			level1 = -1;
		} 
    	finally {
            //Также не забываем закрыть файл
            try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    public void gameInit() {

        ball = new Ball(0,0,10,10,10,"");
        ball.setSpeed(1);
        paddle = new Paddle(0,0,50,20,5,"");
             try{
        File lv = new File("map/"+level1+"lvl.MyMap");
   
        setBrics(lv);
        }catch(Exception e){
        	
        	
        }
    	
       
    }

    //отрисовка объектов 
    public void ScheduleTask(){
    	if(level1 <0){
    		ingame = false;
    		message = "Victory";
    	}
    
    	if(pause == false ){
    		if(ingame ==true){
    			time_ml++;
    			settimer(time_ml);
    			score.setText("Score : "+score1);
    		}
    		
	        ball.move();
	        paddle.move();
	        checkCollision();
	        repaint();
    	}
    	 
    }
	public void pauseGame() {
	    pause = true;
	}public void startGame() {
	    ingame = true;
	    pause = false;
	}
	//Таймер
	public void settimer(int num){
		
		if(num%100==0){
			
			if(num/100<10)
				time.setText("Time : 00"+num/100);
			else{
				if(num/100>10 && num/10<100)
					time.setText("Time : 0"+num/100);
				else	time.setText("Time : "+num/100);
				
			}
		}
		
	}
	

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2=(Graphics2D) g;
        if(path!=null){
    
        
          	ImageIcon im = new ImageIcon(path);
          	g.drawImage(im.getImage(),0,0,null);
          
      	}
        if (ingame == true) {
        	g.setFont(font);
        
        	g.drawString(score.getText(), Commons.WIDTH/2, 30);
        	g.drawString(time.getText(), 20, 30);
        	//рисование Ball
        	g.setColor(Color.RED);
        	GradientPaint redwhite = new GradientPaint(0, 0, Color.RED,0,0, Color.WHITE);
        	g2.setPaint(redwhite);
        	g2.fill(new Ellipse2D.Double(ball.getX(), ball.getY(), ball.getRadious(), ball.getRadious()));
        	  
            //рисование Paddle
        	g.setColor(Color.BLUE);
        	g2.setPaint(new GradientPaint(0, 0, Color.BLUE,0,0, Color.BLUE));
        	g2.fill(new RoundRectangle2D.Double(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight(), paddle.getRadious(), paddle.getRadious()));
            
          //отрисовка кирпичей,если они не уничтожены
        	try{
           
            for (int i = 0; i < bricks.size(); i++) {
                if (bricks.get(i).isDestroyed()!= true){
                	   g.setColor(Color.GREEN);
                	   g.drawRoundRect(bricks.get(i).getX(), bricks.get(i).getY(), bricks.get(i).getWidth(), bricks.get(i).getHeight(), bricks.get(i).getRadious(), bricks.get(i).getRadious());
                	   g2.setPaint(new GradientPaint(0, 0, Color.YELLOW,0,0, Color.YELLOW));
                	   g2.fill(new RoundRectangle2D.Double(bricks.get(i).getX()+2, bricks.get(i).getY()+2, bricks.get(i).getWidth()-2, bricks.get(i).getHeight()-2, bricks.get(i).getRadious(), bricks.get(i).getRadious()));
                       
                }
            }
         	}catch(Exception e){
         		e.printStackTrace();
        	}
        } else {

            Font font = new Font("Verdana", Font.BOLD, 18);
            FontMetrics metr = this.getFontMetrics(font);

            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString(message,
                         (Commons.WIDTH - metr.stringWidth(message)) / 2,
                         Commons.WIDTH / 2);
        }


        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }  
   
    //проверка на пересечение с объектом
    public void checkCollision() {
    
        if (ball.getRect().getMaxY() > Commons.BOTTOM) {
           ingame = false;
           message="Game Over";
           level1=1;
        }//если шар ударяется пола,то игра остановлена
        
        for (int i = 0, j = 0; i < bricks.size(); i++) {
        	//проверка уничтожения всех кирпичей
            if (bricks.get(i).isDestroyed()) {
                j++;
               
            }
            //если все кирпичи уничтожены,то победа
            if (j == bricks.size()) {
              level1++;
              gameInit();
            }
        }
      
        
        //если шарик ударяется об доску то он отлетает
        if ((ball.getRect()).intersects(paddle.getRect())) {

            int paddleLPos = (int)paddle.getRect().getMinX();//позиция левого угла доски
            int ballLPos = (int)ball.getRect().getMinX();//позиция левого мяча

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;
            
            
            if (ballLPos < first) {
                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {
                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {
                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {
                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) {
                ball.setXDir(1);
                ball.setYDir(-1);
            }


        }

        
        //пересечение с кубиками
        for (int i = 0; i < bricks.size(); i++) {
            if ((ball.getRect()).intersects(bricks.get(i).getRect())) {
            	//положение шарика
                int ballLeft = (int)ball.getRect().getMinX();
                int ballHeight = (int)ball.getRect().getHeight();
                int ballWidth = (int)ball.getRect().getWidth();
                int ballTop = (int)ball.getRect().getMinY();
                
                //точки соударения шарика с кирпичами
                Point pointRight =
                    new Point(ballLeft + ballWidth + 1, ballTop);//
                Point pointLeft = new Point(ballLeft - 1, ballTop);//
                Point pointTop = new Point(ballLeft, ballTop - 1);//
                Point pointBottom =
                    new Point(ballLeft, ballTop + ballHeight + 1);//
              
                if (!bricks.get(i).isDestroyed()) {
                	  //ударение справа
                    if (bricks.get(i).getRect().contains(pointRight)) {
                        ball.setXDir(-1);
                    }
                    //ударение слева
                    else if( bricks.get(i).getRect().contains(pointLeft)) {
                        ball.setXDir(1);
                    }
                    //ударение сверху
                    if (bricks.get(i).getRect().contains(pointTop)) {
                        ball.setYDir(1);
                    }
                    //ударение снизу
                    else if (bricks.get(i).getRect().contains(pointBottom)) {
                        ball.setYDir(-1);
                    }
                    score1++;
                    bricks.get(i).setDestroyed(true);//удаление кирпича
                }
            }
        }
    }


}