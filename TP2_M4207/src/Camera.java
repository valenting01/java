// Valentin GAUTREAU 2AFA

class Camera extends Equipement {
	// Déclaration des données membres
	private String nbImageSeconde;
	private String typeCamera;
	private double resolutionCamera;
	
	// Définition des constructeurs
	
	public Camera(String adrIP, String adrMAC, boolean mis_baie, double longueur, double largeur, double hauteur, String nbImageSeconde, String typeCamera, double resolutionCamera) {
		super(adrIP, adrMAC, mis_baie, longueur, largeur, hauteur);
		this.nbImageSeconde = nbImageSeconde;
		this.typeCamera = typeCamera;
		this.resolutionCamera = resolutionCamera;
	}
	public String toString() {
		   return " \nType : Caméra" + " \nNombre d'images par secondes : " + nbImageSeconde + " \nType de caméra : " + typeCamera + " \nRésolution de la caméra : " + resolutionCamera + super.toString();
		}
}