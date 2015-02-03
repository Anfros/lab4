import java.awt.*;
import javax.swing.*;
public class Steg2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CrystalModel model=new CrystalModel(Integer.parseInt(args[0]));
		JFrame frame=new JFrame("asdasd");
		frame.setSize(new Dimension(model.getSize(),model.getSize()));
		CrystalView view=new CrystalView(model);
		frame.add(view);
		frame.setVisible(true);
		while(model.crystallizeOneIon())
			view.repaint();
	}

}
