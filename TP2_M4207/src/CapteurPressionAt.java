// Valentin GAUTREAU 2AFA

class CapteurPressionAt extends Equipement {
	// D�claration des donn�es membres
	private double pressionMin;
	private double pressionMax;
	private double pressionAct;
	
	// D�finition des constructeurs
	
	public CapteurPressionAt(String adrIP, String adrMAC, boolean mis_baie, double longueur, double largeur, double hauteur, double pressionMin, double pressionMax, double pressionAct) {
		super(adrIP, adrMAC, mis_baie, longueur, largeur, hauteur);
		this.pressionMin = pressionMin;
		this.pressionMax = pressionMax;
		this.pressionAct = pressionAct;
	}
	
	public String toString() {
		   return " \nType : Capteur de pression atmosph�rique" + " \nPression minimale : " + pressionMin + " \nPression maximale : " + pressionMax + " \nPression actuelle : " + pressionAct + super.toString();
		}
}