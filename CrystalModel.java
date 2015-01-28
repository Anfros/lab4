import static java.lang.Math.*;
import java.util.*;

public class CrystalModel {

	private final int size;
	private final int dropRadius;
	private final int escapeRadius;

	private boolean[][] representation;
	private int[] newIon = new int[2];
	Random rand=new Random();

	public CrystalModel(int size){
		this.size=size;
		this.escapeRadius=size/2-1;
		this.dropRadius=this.escapeRadius*8/10;
		reset();
	}

	/*släpper en ny jon (med hjälp av dropNewIon() ) och flyttar sedan jonen ett steg åt gången 
	tills den kristalliseras. Kommer den utanför flyktcirkeln så släpps en ny jon. Se beskrivningen 
	ovan för mer detaljer. Metoden returnerar false när kristallen är klar (dvs när sista jonen 
			kristalliseras på startcirkeln) och true om vi kan kristallisera fler joner.
	 */
	boolean crystallizeOneIon(){
		dropNewIon();

		while(!anyNeighbours(getX(),getX())){
			if (rand.nextBoolean())
				newIon[rand.nextInt(1)]++;
			else
				newIon[rand.nextInt(1)]--;
			
			if(outsideCircle(escapeRadius,getX(),getY()))
					dropNewIon();
		}
		
		representation[xBathToModelRep(newIon[0])][yBathToModelRep(newIon[1])]=true;
		if (outsideCircle(dropRadius,getX(),getY()))
			return false;
		else
			return true;



	}

	/*- returnerar "true" om det finns en kristalliserad jon på position x,y.
	(Vi behöver nog inte denna i steg 1 för där använder vi toString som 
	ju kan läsa direkt i matrisen (eller vad man nu använder) men när vi skall rita 
	så kommer vi inte enkelt åt matrisen)
	 */
	boolean getModelValue(int x, int y){
		return(this.representation[xBathToModelRep(x)][yBathToModelRep(y)]);


	}		

	//- kollar om position x,y är utanför (eller på) cirkeln med radie r. (använd pythagoras)
	boolean outsideCircle(int r, int x, int y){
		return (sqrt(pow(x,2)+pow(y,2))>=r);
	}		

	//- kollar om jonen på position x,y har några grannar som kristalliserats.
	boolean anyNeighbours(int x, int y){
		return(this.representation[xBathToModelRep(x+1)][yBathToModelRep(y)]||
				this.representation[xBathToModelRep(x)][yBathToModelRep(y+1)]||
				this.representation[xBathToModelRep(x-1)][yBathToModelRep(y)]||
				this.representation[xBathToModelRep(x)][yBathToModelRep(y-1)]
				);

	}		

	//- släpper en jon på startcirkeln (dvs slumpar fram en ny punkt x, y på startcirkeln).
	void dropNewIon(){
		double rand =random();
		this.newIon[0]=(int) Math.round(dropRadius*cos(rand*2*Math.PI));
		this.newIon[1]=(int) Math.round(dropRadius*sin(rand*2*Math.PI));
	}		

	//- initierar modellen (dvs matrisen) och lägger en första kristalliserad jon mitt i "badet". 
	void reset(){
		representation=new boolean[size+2][size+2];
		representation[xBathToModelRep(0)][yBathToModelRep(0)]=true;
	}

	/*och en yBathToModelRep (x Bath to ModelRepresentation) omvandlar en "bad"-kordinat 
		till ett matris värde (om man använt en matris som representation). (Observera att dom inte ser likadana ut...) 
	 */
	int xBathToModelRep(int x){
		if(abs(x)>escapeRadius+2)
			throw new IllegalArgumentException("Coordinate outside bath");
		return x+escapeRadius+2;


	}

	int yBathToModelRep(int y){
		if(abs(y)>escapeRadius+2)
			throw new IllegalArgumentException("Coordinate outside bath");
		return -y+escapeRadius+2;
	}


	public int getX(){
		return newIon[0];
	}

	public int getY(){
		return newIon[1];
	}

	public int getRadius(){
		return escapeRadius;
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
