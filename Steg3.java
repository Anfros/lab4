//Anders Fredriksson och Henrik Petersson, labgrupp 131
import javax.swing.JFrame;

public class Steg3 {
	public static void main(String[] args){
		JFrame frame= new JFrame("Crystal Experiment");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CrystalControl control;
		try{
			control=new CrystalControl(Integer.parseInt(args[0]));
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			control=new CrystalControl();
		}catch(ArrayIndexOutOfBoundsException e){
			control=new CrystalControl();
		}
		frame.add(control);
		frame.pack();
		frame.setVisible(true);

	}
}
