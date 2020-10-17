// Valentin GAUTREAU 2AFA

class Camera extends Equipement {
	// D�claration des donn�es membres
	private String nbImageSeconde;
	private String typeCamera;
	private double resolutionCamera;
	
	// D�finition des constructeurs
	
	public Camera(String adrIP, String adrMAC, boolean mis_baie, double longueur, double largeur, double hauteur, String nbImageSeconde, String typeCamera, double resolutionCamera) {
		super(adrIP, adrMAC, mis_baie, longueur, largeur, hauteur);
		this.nbImageSeconde = nbImageSeconde;
		this.typeCamera = typeCamera;
		this.resolutionCamera = resolutionCamera;
	}
	public String toString() {
		   return " \nType : Cam�ra" + " \nNombre d'images par secondes : " + nbImageSeconde + " \nType de cam�ra : " + typeCamera + " \nR�solution de la cam�ra : " + resolutionCamera + super.toString();
		}
}