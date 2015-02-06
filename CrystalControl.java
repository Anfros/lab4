//Anders Fredriksson och Henrik Petersson, labgrupp 131
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * A class to control the simulation and drawing of a crystallization experiment.
 * @author Anders Fredriksson och Henrik Petersson
 *
 */
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
	 * Constructor
	 * @param size size of bath to be simulated
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
	 * standard constructor
	 * sets size to 200.
	 */
	public CrystalControl(){
		this(200);
	}

	/**
	 * Listener for buttons and the timer
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		switch (Integer.parseInt(e.getActionCommand())){
		case 1:
			if(!timer.isRunning())
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
			else
				timer.stop();
			view.repaint();
			break;
		}
	}

	private void setSpeed(){
		boolean done;
		JOptionPane option =new JOptionPane();
				Object ans;
		option.createDialog("Set Speed");
		JOptionPane.showInternalInputDialog(option,"Set Speed");
		while(option.getValue()==JOptionPane.UNINITIALIZED_VALUE)
		;
		
		ans=option.getValue();

		do{
			if (ans==null){
				done=true;
			}else{
				try{
					speed=Integer.parseInt((String)ans);
					if (speed<0)
						throw new IllegalArgumentException("Speed needs to be positive");
					done=true;
				}catch(IllegalArgumentException e){
					done=false;
				}
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
