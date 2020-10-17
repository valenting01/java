// Valentin GAUTREAU 2AFA

class Barriere extends Equipement {
	// D�claration des donn�es membres
	private double tailleBarriere;
	private Boolean etatBarriere;
	
	// D�finition des constructeurs
	
	public Barriere(String adrIP, String adrMAC, boolean mis_baie, double longueur, double largeur, double hauteur, double tailleBarriere, Boolean etatBarriere) {
		super(adrIP, adrMAC, mis_baie, longueur, largeur, hauteur);
		this.tailleBarriere = tailleBarriere;
		this.etatBarriere = etatBarriere;
	}
	public String toString() {
		   return " \nType : Barri�re" + " \nTaille de la barri�re : " + tailleBarriere + " \n�tat de la barri�re : " + etatBarriere + super.toString();
		}
}