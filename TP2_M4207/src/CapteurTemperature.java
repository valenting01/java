// Valentin GAUTREAU 2AFA

class CapteurTemperature extends Equipement {
	// Déclaration des données membres
	private double temperatureMin;
	private double temperatureMax;
	private double temperatureAct;
	
	// Définition des constructeurs
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
		   return " \nType : Capteur de température" + " \nTempérature minimale : " + temperatureMin + " \nTempérature maximale : " + temperatureMax + " \nTempérature actuelle : " + temperatureAct + super.toString();
		}
}