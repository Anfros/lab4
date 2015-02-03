import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class CrystalControl extends JPanel implements ActionListener{
	
	private JButton SpeedButton=new JButton("Speed");
	private JButton StartButton=new JButton("Start");
	private JButton StopButton=new JButton("Stop");
	
	
	public CrystalControl(int size){
		setPreferredSize(new Dimension(size,20));
		setLayout(new BorderLayout());
		add(SpeedButton,BorderLayout.WEST);
		add(StartButton,BorderLayout.CENTER);
		add(StopButton,BorderLayout.EAST);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
	
	
	

}
