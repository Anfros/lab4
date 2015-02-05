//Anders Fredriksson och Henrik Petersson, labgrupp 131
import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;
/**
 * A class to draw the state of a CrystalModel.
 * @author Anders Fredriksson och Henrik Petersson
 *
 */
public class CrystalView extends JPanel{
	int size;
	int bathSize;
	CrystalModel model;
/**
 * Constructor
 * @param model the object, of type CrystalModel, which the CrystalView is to draw.
 */
	public CrystalView(CrystalModel model){
		this.size=model.getSize();
		this.bathSize=model.getSize();
		this.setPreferredSize(new Dimension(size,size));
		setLayout(new BorderLayout());
		this.model=model;
		setBackground(Color.BLACK);
		setVisible(true);
		repaint();


	}
	/**
	 * Draws the CrystalModel associated with this object. 
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		for(int i=-model.getHighCoord();i<=model.getHighCoord();i++)
			for(int j=-model.getHighCoord()+1;j<=model.getHighCoord();j++)
				if (model.getModelValue(i,j))
					g.fillRect(xBathToGraph(i),yBathToGraph(j),1,1);
		g.setColor(Color.GREEN);
		g.fillRect(xBathToGraph(model.getLastX()),yBathToGraph(model.getLastY()),1,1);
	}

	
	
	private int xBathToGraph(int x){
		if(abs(x)>model.getHighCoord())
			throw new IllegalArgumentException("Coordinate outside bath!!!");
		return x+model.getHighCoord();
	}

	private int yBathToGraph(int y){
		if(abs(y)>model.getHighCoord())
			throw new IllegalArgumentException("Coordinate outside bath!!!");
		return -y+model.getHighCoord();
	}
}
