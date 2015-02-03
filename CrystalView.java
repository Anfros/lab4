import javax.swing.*;
import java.awt.*;
public class CrystalView extends JFrame{
	int size;
	DrawArea drawArea;

	public CrystalView(CrystalModel model){
		this.size=model.getSize();
		this.setPreferredSize(new Dimension(size+200,size+400));
		setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawArea=new DrawArea(model.getSize(),model);
		add(drawArea,BorderLayout.NORTH);
		pack();
		setVisible(true);
		repaint();


	}
	public void repaint(){
		drawArea.repaint();
		super.repaint();
	}

	
	
	private int xGraphToBath(int x){
		if(x<0||x>size)
			throw new IllegalArgumentException("Coordinate outside bath");
		return x-size/2;
	}

	private int yGraphToBath(int y){
		if(y<0||y>size)
			throw new IllegalArgumentException("Coordinate outside bath");
		return y-size/2;
	}


	private class DrawArea extends JPanel{
		CrystalModel model;
		
		
		public DrawArea(int size, CrystalModel model){
			this.setSize(new Dimension(size,size));
			this.model=model;
			setVisible(true);
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			for(int i=0;i<size;i++)
				for(int j=0;j<size;j++)
					if (model.getModelValue(xGraphToBath(i),yGraphToBath(j)))
						g.fillRect(i,j,3,3);
			
			
			
		}
		

	}

}




