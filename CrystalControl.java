import java.awt.*;
/**
 * 
 */
import java.awt.event.*;

import javax.swing.*;
public class CrystalControl extends JPanel implements ActionListener{

	private JButton speedButton=new JButton("ChangeSpeed");
	private JButton runButton=new JButton("Run");
	private JButton stopButton=new JButton("Stop");
	private Timer timer;
	private CrystalModel model;
	private CrystalView view;
	private int speed=5;
	private boolean crystalNotDone=true;

/**
 * 
 * @param size
 */
	public CrystalControl(int size){
		if (size<100||size>1000)
			throw new IllegalArgumentException("size should be 100...1000, using default");
		setLayout(new BorderLayout());
		model = new CrystalModel (size);
		view = new CrystalView (model);
		add(view, BorderLayout.NORTH);
		speedButton.setActionCommand("1");
		speedButton.addActionListener(this);
		add(speedButton,BorderLayout.WEST);
		runButton.setActionCommand("2");
		runButton.addActionListener(this);
		add(runButton,BorderLayout.CENTER);
		stopButton.setActionCommand("3");
		stopButton.addActionListener(this);
		add(stopButton,BorderLayout.EAST);
		timer=new Timer(10,this);
		timer.setActionCommand("4");

		this.setVisible(true);
	}

/**
 * 
 */
	public void actionPerformed(ActionEvent e) {
		switch (Integer.parseInt(e.getActionCommand())){
		case 1:
			if(timer.isRunning())
				break;
			setSpeed();
			break;
		case 2:
			model.reset();
			crystalNotDone=true;
			view.repaint();
			timer.start();
			break;
		case 3:
			timer.stop();

			break;
		case 4:
			if(crystalNotDone)
				crystalNotDone=runSomeSteps();
				view.repaint();
			break;
		}

	}

	private void setSpeed(){
		String ans;
		boolean done;
		do{
				ans = JOptionPane.showInputDialog("Set Speed");
			try{
				speed=Integer.parseInt(ans);
				done=true;
			}
			catch(IllegalArgumentException e){
				done=false;
			}
			catch(NullPointerException e){
				done=false;
			}
		}while(!done);
	}


	private boolean runSomeSteps(){
		int i=0;
		boolean crystalNotDone =true;
		do{
			crystalNotDone=model.crystallizeOneIon();
			i++;
		}while (i<speed&&crystalNotDone);
		return crystalNotDone;
	}





}
