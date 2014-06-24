import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Constructor extends JPanel implements ActionListener,Commons {

	public ArrayList<String> map=new  ArrayList<String>();			//map
	public ArrayList<Brick> bricks = new ArrayList<>();			//bricks
	public JTextField field = new JTextField("**lvl");
	
	public Constructor(ActionListener ai) {
		// TODO Auto-generated constructor stub
		super();
		setLayout(new BorderLayout());
		setSize(Commons.WIDTH, Commons.HEIGTH-100);
		JPanel panel = new JPanel();
		add(panel,BorderLayout.CENTER);
		//panel.setBounds(0,0,Commons.WIDTH,150);
		
		addBricks(panel);						//add bricks to the board
		
		JPanel panel2 = new  JPanel();			//add button save
		
		
	
	
		panel2.add(field);
	
		JButton save = new JButton("Save");
		
		save.addActionListener(ai);			//add listener to button for Map saving 
		panel2.add(save);	//*/
		//panel2.setVisible(true);
		add(panel2,BorderLayout.SOUTH);
	}
	
	class Brick extends JButton{
		public boolean flag = false;
		public Brick(String string) {
			// TODO Auto-generated constructor stub
		super(string);
		}
	}
	
	public void addBricks(JPanel  panel){
	
		panel.setLayout(new GridLayout(5,6));
		panel.setBounds(0, 0, Commons.WIDTH, Commons.HEIGTH);
		for(int i =0 ; i < 6*5;i++){
			Brick brick = new Brick(""+(i+1));
			map.add("-");
			brick.addActionListener(this);
			brick.setSize(20, 5);
			brick.setBackground(Color.GRAY);
			bricks.add(brick);
			panel.add(brick);
		}
	}
	
	public void actionPerformed(ActionEvent e){		

			  int index = Integer.parseInt(e.getActionCommand()); 
			 
			  Brick button = bricks.get(index-1);
					 if(button.flag == true){
						 button.setBackground(Color.gray);
						 button.flag = false;
						 map.get(index).concat("-");
					 }
					 else{
						 button.setBackground(Color.red);
						 button.flag =true;
						 map.set(index-1, "*");
					 }
	  }

}
