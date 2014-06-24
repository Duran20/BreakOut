import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Paddle extends Sprite implements Commons {

    String paddle = "../images/paddle.png";

    int dx;//смещение по х

    public Paddle (int x,int y,int width,int heigth,int radious, String path) {
   		super(x,y,width,heigth,radious, path);

      //  ImageIcon ii = new ImageIcon(this.getClass().getResource(paddle));
     //   image = ii.getImage();


        resetState();

    }

    //движение доски
    public void move() {
    	
        x += dx;
        if (x <= 2) //ограничение выхода за фрейм
          x = 2;
        if (x >= Commons.PADDLE_RIGHT)
          x = Commons.PADDLE_RIGHT;
    }
    

    //возращение в первоначальное положение
    public void resetState() {
        x = 200;
        y = 360;
    }
}