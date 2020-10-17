// Valentin GAUTREAU 2AFA

class BornePaiement extends Equipement {
	// Déclaration des données membres
	private Boolean typeEcran;
	private String resolutionEcran;
	private double tempsDeReponse;
	
	// Définition des constructeurs
	
	public BornePaiement(String adrIP, String adrMAC, boolean mis_baie, double longueur, double largeur, double hauteur, Boolean typeEcran, String resolutionEcran, double tempsDeReponse) {
		super(adrIP, adrMAC, mis_baie, longueur, largeur, hauteur);
		this.typeEcran = typeEcran;
		this.resolutionEcran = resolutionEcran;
		this.tempsDeReponse = tempsDeReponse;
	}
	
	public String toString() {
		   return " \nType : Borne Paiement" + " \nTactile : " + typeEcran + " \nRésolution : " + resolutionEcran + " \nTemps de réponse : " + tempsDeReponse +" ms" + super.toString();
		}
}