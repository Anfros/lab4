//Anders Fredriksson och Henrik Petersson, labgrupp 131
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Steg3 {
	public static void main(String[] args){
		JFrame frame= new JFrame("Crystal Experiment");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CrystalControl control;
		try{
			control=new CrystalControl(Integer.parseInt(args[0]));
		}catch(IllegalArgumentException e){
			JOptionPane.showMessageDialog(frame, e.getMessage()+", setting size to default");
			control=new CrystalControl();
		}catch(ArrayIndexOutOfBoundsException e){
			JOptionPane.showMessageDialog(frame, "Should imput bath size on start");
			control=new CrystalControl();
		}
		frame.add(control);
		frame.pack();
		frame.setVisible(true);

	}
}
