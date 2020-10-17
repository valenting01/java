// Valentin GAUTREAU 2AFA

class Radar extends Equipement {
	// D�claration des donn�es membres
	private double frequence;
	private double longueurOnde;
	private double porteeMaximale;
	
	// D�finition des constructeurs
	
	public Radar(String adrIP, String adrMAC, boolean mis_baie, double longueur, double largeur, double hauteur, double frequence, double longueurOnde, double porteeMaximale) {
		super(adrIP, adrMAC, mis_baie, longueur, largeur, hauteur);
		this.frequence = frequence;
		this.longueurOnde = longueurOnde;
		this.porteeMaximale = porteeMaximale;
	}
	
	public String toString() {
		   return " \nType : Radar" + " \nFr�quence : " + frequence + " \nHz Longueur d'onde : " + longueurOnde + " nm \nPort�e maximale : " + porteeMaximale + super.toString();
		}
}