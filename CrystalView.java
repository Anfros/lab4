import static java.lang.Math.abs;

import javax.swing.*;
import java.awt.*;
public class CrystalView extends JPanel{
	int size;
	int bathSize;
	CrystalModel model;

	public CrystalView(CrystalModel model){
		this.size=model.getSize();
		this.bathSize=model.getSize();
		this.setPreferredSize(new Dimension(size+50,size+100));
		setLayout(new BorderLayout());
		this.model=model;
		setBackground(Color.BLACK);
		setVisible(true);
		repaint();


	}
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




