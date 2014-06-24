
		
	import java.awt.Image;
	import java.awt.Rectangle;
	
	//хранит положение,радиус,размер и картинку объекта
	public class Sprite {
		

	    protected int x;
	    protected int y;
	    protected int width;
	    protected int heigth;
	    protected int radious;
	    protected Image image;
	    
	    public Sprite(int x,int y,int width,int heigth,int radious, String path){
	    	this.x=x;
	    	this.y=y;
	    	this.width=width;
	    	this.heigth=heigth;
	    	this.radious=radious;
	    }
	    
	    
	    
	    public void setX(int x) {
	        this.x = x;
	    }

	    public int getX() {
	        return x;
	    }

	    public void setY(int y) {
	        this.y = y;
	    }

	    public int getY() {
	        return y;
	    }

	    public int getWidth() {
	        return width;
	    }

	    public int getHeight() {
	        return heigth;
	    }
	    
	    public int getRadious(){
	    	return radious;
	    }
	    
	    Image getImage()
	    {
	      return image;
	    }

	    Rectangle getRect()
	    {
	      return new Rectangle(x, y,width, heigth);
	    }
	}
