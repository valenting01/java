// Valentin GAUTREAU 2AFA

class CapteurTemperature extends Equipement {
	// D�claration des donn�es membres
	private double temperatureMin;
	private double temperatureMax;
	private double temperatureAct;
	
	// D�finition des constructeurs
	public CapteurTemperature() {
		super("192.168.16.16", "tttMAC", true, 12, 10, 10);
	}
	
	public CapteurTemperature(String adrIP, String adrMAC, boolean mis_baie, double longueur, double largeur, double hauteur, double temperatureMin, double temperatureMax, double temperatureAct) {
		super(adrIP, adrMAC, mis_baie, longueur, largeur, hauteur);
		this.temperatureMin = temperatureMin;
		this.temperatureMax = temperatureMax;
		this.temperatureAct = temperatureAct;
	}
	
	public String toString() {
		   return " \nType : Capteur de temp�rature" + " \nTemp�rature minimale : " + temperatureMin + " \nTemp�rature maximale : " + temperatureMax + " \nTemp�rature actuelle : " + temperatureAct + super.toString();
		}
}