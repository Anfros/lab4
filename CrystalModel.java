//Anders Fredriksson och Henrik Petersson, labgrupp 131
import static java.lang.Math.*;
import java.util.*;
/**
 * A class to model a crystal forming in a electrolyte bath.
 * 
 * @author Anders Fredriksson and Henrik Petersson
 *
 */
public class CrystalModel {

	private final int size;
	private final int dropRadius;
	private final int escapeRadius;
	private boolean[][] representation;
	private int[] newIon = new int[2];
	private int[] lastIon=new int[2];
	private Random rand=new Random();
	/**
	 * Constructor
	 * @param size size of bath
	 */
	public CrystalModel(int size){
		this.size=size;
		this.escapeRadius=size/2;
		this.dropRadius=(this.escapeRadius*8)/10;
		reset();
		System.out.println();
	}

	/**
	 * Standard constructor
	 * Sets size of bath to 200x200
	 */
	public CrystalModel(){
		this(200);
	}

	/**
	 * Simulates the dropping of a new ion into the bath.
	 * @return false if crystal is done, true otherwise.
	 */
	public boolean crystallizeOneIon(){
		dropNewIon();

		while(!anyNeighbours(getX(),getY())){
			if (rand.nextBoolean())
				newIon[rand.nextInt(2)]++;
			else
				newIon[rand.nextInt(2)]--;
			if(outsideCircle(escapeRadius,getX(),getY())){
				dropNewIon();
			}
		}

		representation[xBathToModelRep(getX())][yBathToModelRep(getY())]=true;
		lastIon[0]=newIon[0];
		lastIon[1]=newIon[1];
		if (outsideCircle(dropRadius,getX(),getY()))
			return false;
		else
			return true;
	}

	/**
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return true if position (x,y) contains an ion.
	 */
	public boolean getModelValue(int x, int y){
		return(this.representation[xBathToModelRep(x)][yBathToModelRep(y)]);
	}		

	private boolean outsideCircle(int r, int x, int y){
		return (pow(r,2)<=(pow(x,2)+pow(y,2)));
	}		

	private boolean anyNeighbours(int x, int y){
		return(getModelValue(x+1,y)||
				getModelValue(x-1,y)||
				getModelValue(x,y+1)||
				getModelValue(x,y-1));
	}		

	private void dropNewIon(){
		double rand =random();
		this.newIon[0]=(int) Math.round(dropRadius*cos(rand*2*Math.PI));
		this.newIon[1]=(int) Math.round(dropRadius*sin(rand*2*Math.PI));
	}		

	/**
	 * Initiates the model with a clear field and then puts a crystal with one ion in the middle.
	 * All data about previous models is lost.
	 */
	public void reset(){
		representation=new boolean[size+4][size+4];
		setRepPos(0,0,true);
		lastIon[0]=0;
		lastIon[1]=0;
	}

	private int xBathToModelRep(int x)throws IllegalArgumentException{
		if(abs(x)>escapeRadius+2)
			throw new IllegalArgumentException("Coordinate outside bath");
		return x+representation.length/2;
	}

	private int yBathToModelRep(int y)throws IllegalArgumentException{
		if(abs(y)>escapeRadius+2)
			throw new IllegalArgumentException("Coordinate outside bath");
		return -y+representation.length/2;
	}

	private void setRepPos(int x,int y, boolean state){
		representation[xBathToModelRep(x)][yBathToModelRep(y)]=state;
	}
	/**
	 * 
	 * @return x-coordinate of ion currently moving in the bath
	 */
	public int getX(){
		return newIon[0];
	}
	/**
	 * 
	 * @return y-coordinate of ion currently moving in the bath
	 */
	public int getY(){
		return newIon[1];
	}
	/**
	 * 
	 * @return x-coordinate of last ion to attach itself to the crystal.
	 */
	public int getLastX(){
		return lastIon[0];
	}
	/**
	 * 
	 * @return y-coordinate of last ion to attach itself to the crystal.
	 */
	public int getLastY(){
		return lastIon[1];
	}
	/**
	 * 
	 * @return the highest x or y coordinate an ion can have and still be within the bath. The bath is completely symmetrical.  
	 */
	public int getHighCoord(){
		return size/2;
	}

	private int getRadius(){
		return escapeRadius;
	}
	/**
	 * @return the diameter of the bath.
	 */
	public int getSize(){
		return size;
	}
	/**
	 * Returns the crystals state i.e. a string according to figure 3 i labPM. 
	 * x and y is the position of the ion in the bath
	 * @return A string that draws the crystal.
	 */
	public String toString() {
		int x = getX(); // the ions position in the bath
		int y = getY();
		int size = getRadius();
		StringBuffer s = new StringBuffer(1000);
		for(int i=-size-1; i<size+1; i++) {
			s.append("-");
		}
		s.append("\n");
		for(int i=-size; i<size; i++) {
			s.append("|");
			for(int j=-size; j<size; j++) {
				if (getModelValue(i, j)) {
					if (i==x && j==y) {
						s.append("#");
					} else {
						s.append("*");
					}
				} else {
					s.append(" ");
				}
			}
			s.append("|");
			s.append("\n");
		}
		for(int i = -size-1; i < size+1; i++) {
			s.append("-");
		}
		s.append("\n");
		return s.toString();
	}


}
