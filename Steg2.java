
public class Steg2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CrystalModel model=new CrystalModel(400);
		CrystalView view=new CrystalView(model);
		while(model.crystallizeOneIon())
			view.repaint();
	}

}
