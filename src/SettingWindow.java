import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class SettingWindow extends JPanel implements Commons{
	public boolean flag=false;
	public void setVisible (){
		setVisible(flag);
	}
	Constructor levelconst = null;
	public SettingWindow(ActionListener act) {
		setSize(Commons.WIDTH,Commons.HEIGTH);
		  JPanel up= new JPanel(); 
		  JPanel bot= new JPanel(); 
	
		  levelconst = new Constructor(act);
		  bot.add(levelconst);
		 
		  JButton button = new JButton("Background"); 
		  button.addActionListener(act);
		  up.add(button); 
		  button = new JButton("Back"); 
		  button.addActionListener(act);
		  up.add(button); 
		  up.setBounds(0, 0, Commons.WIDTH, 20);
		  bot.setBounds(0, 0, Commons.WIDTH,Commons.HEIGTH-20);
		  add(up);
		  add(bot);
		 // setLayout(new BorderLayout());
	}
}

