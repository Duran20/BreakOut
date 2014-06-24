import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class StartWindow extends JPanel implements Commons {
	CardLayout cards = null;
	JPanel conteiner = null;
	
	public StartWindow( ActionListener act) {
		// TODO Auto-generated constructor stub
		super();
		setLayout(new GridLayout(4,1));
		setSize(Commons.WIDTH,Commons.HEIGTH);
	       
        //создаем кнопки
        JButton button = new JButton("Start");
        button.addActionListener(act);
        
       add(button); 
        
        button = new JButton("Level Constructor"); 
        button.addActionListener(act);
        
     	add(button); 
        
        button = new JButton("Setting");      
        button.addActionListener(act);
			
  	
        add(button); 
        
        button = new JButton("Exit");  
        button.addActionListener(act);
        add(button);
        setVisible(true);
	}

	
}