
	//�������� ������� ������.
	public class Brick extends Sprite {

	    boolean destroyed;//�������� �� ����������� ������� �� ������


	    public Brick(int x,int y,int width,int heigth,int radious, String path) {
	    	super(x,y,width,heigth,radious, path);
	    	destroyed = false;//
	    }

	    public boolean isDestroyed()
	    {
	      return destroyed;
	    }

	    public void setDestroyed(boolean destroyed)
	    {
	      this.destroyed = destroyed;
	    }
}
