import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Breakout extends JFrame implements Commons ,ActionListener,KeyListener {
	Board board=null;

	StartWindow stwindow = null;
	SettingWindow setwindow =null;
	JPanel mainpanel = new JPanel(new CardLayout());
	String background=null;
	KeyListener listener = this;
	
    public Breakout()
    {
        board = new Board(listener);
    	stwindow = new StartWindow(this);
    	setwindow = new SettingWindow(this);
    
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(this);
        add(stwindow);
        add(setwindow);
        add(board);
      
        setSize(Commons.WIDTH, 400);
        setVisible(true);
        setFocusable(true);
        visibility(1);
        
             
    }

    public static void main(String[] args) {
        new Breakout();
    }


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("Start")){
		
			visibility(3);
			if(board.ingame == false){
				board.gameInit();
			}
			if(board.ingame ==false && board.pause == true){
				board.level1 = 1;			
				board.gameInit();
			}
			
			
			board.startGame();
		}
		if(arg0.getActionCommand().equals("Setting")){
			setwindow.setVisible(true);
			stwindow.setVisible(false);
			
		}
		if(arg0.getActionCommand().equals("Level Constructor")){
			visibility(4);
			
		}
		if(arg0.getActionCommand().equals("Exit")) {
				// TODO Auto-generated method stub
			
				System.exit(0);
		}
		if(arg0.getActionCommand().equals("Back")){
			visibility(1);
		}
		if(arg0.getActionCommand().equals("Background")){
			JFileChooser file = new JFileChooser();
			int ret = file.showDialog(null, "Выбрать фон");
			if(ret == JFileChooser.APPROVE_OPTION){
				background = (file.getSelectedFile()).getPath();
				board.path = background;
			}
		}
		if(arg0.getActionCommand() == "Save"){
			 if(setwindow.levelconst.field.getText() != "*lvl"){
				File file = new File("map/"+setwindow.levelconst.field.getText()+".MyMap");
				try{
					FileWriter writer = new FileWriter(file);
					 for( int i =1; i <= setwindow.levelconst.map.size(); i++){
						 writer.append(setwindow.levelconst.map.get(i-1));
						 if(i%6 == 0 ) 	writer.append("\n");
					 }
				  writer.flush();							//finished write to the file
				  writer.close();
				 visibility(1);
				}catch(Exception e1){}
			 }
		 }
	}
	
    public void keyReleased(KeyEvent e) {
        
    	 if (  e.getKeyCode() == KeyEvent.VK_LEFT) {
             board.paddle.dx=0;
             
           }

           if (e.getKeyCode()  == KeyEvent.VK_RIGHT) {
           	board.paddle.dx=0;
           }
        }

    public void keyPressed(KeyEvent e) {
    	if(e.getKeyCode()==27 && board.ingame==false){
            	board.pauseGame();
            	board.setVisible(false);
            	board.requestFocus();
            	visibility(1);
            }
            if(e.getKeyCode()==32 && board.isVisible()==true){
            	board.pauseGame();
            	board.setVisible(false);
            	visibility(1);
            	
            }

            if (  e.getKeyCode() == KeyEvent.VK_LEFT) {
              board.paddle.dx=-2;
              
            }

            if (e.getKeyCode()  == KeyEvent.VK_RIGHT) {
            	board.paddle.dx=2;
            }
    }

    public void visibility(int value){
    	
    	switch (value) {
		case 1:
			setwindow.setVisible(false);
			board.setVisible(false);
	//		levelconst.setVisible(false);
			stwindow.setVisible(true);
			break;
		case 2:
			setwindow.setVisible(true);
			board.setVisible(false);
	//		levelconst.setVisible(false);
			stwindow.setVisible(false);
			break;
		case 3:
			setwindow.setVisible(false);
			board.setVisible(true);
	//		levelconst.setVisible(false);
		//	levelconst.setFocusable(false);
			stwindow.setVisible(false);
			break;
		case 4:
			setwindow.setVisible(false);
			board.setVisible(false);
	//		levelconst.setVisible(true);
			stwindow.setVisible(false);
			break;
    	}

    }
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}