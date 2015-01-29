
public class Test {
	public static void main(String[] args){
		CrystalModel c = new CrystalModel(50); 
		while (c.crystallizeOneIon()) {
			System.out.println(c.toString());
			System.out.println("hej");
			try{
				Thread.sleep(10); // take a break
			} 
			// måste fångas, kan kastas av Thread.sleep men bör ej hända
			catch (InterruptedException ex) {System.out.println("InterruptedException");
			}
		}
	}

}
