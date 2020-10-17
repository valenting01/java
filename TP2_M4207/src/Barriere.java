// Valentin GAUTREAU 2AFA

class Barriere extends Equipement {
	// Déclaration des données membres
	private double tailleBarriere;
	private Boolean etatBarriere;
	
	// Définition des constructeurs
	
	public Barriere(String adrIP, String adrMAC, boolean mis_baie, double longueur, double largeur, double hauteur, double tailleBarriere, Boolean etatBarriere) {
		super(adrIP, adrMAC, mis_baie, longueur, largeur, hauteur);
		this.tailleBarriere = tailleBarriere;
		this.etatBarriere = etatBarriere;
	}
	public String toString() {
		   return " \nType : Barrière" + " \nTaille de la barrière : " + tailleBarriere + " \nÉtat de la barrière : " + etatBarriere + super.toString();
		}
}