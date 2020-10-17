import java.io.*;
import java.net.*;

// Valentin GAUTREAU 2AFA

class Equipement implements Serializable {
	// Déclaration des données membres
	private String adrIP;
	private String adrMAC;
	private String nom_const;
	private boolean mis_baie;
	private double[] baie = new double[3];
	private boolean etat;

	// Définition des constructeurs

	public Equipement(String adrIP, String adrMAC, boolean mis_baie, double longueur, double largeur, double hauteur) {
		this.adrIP = adrIP;
		this.adrMAC = adrMAC;
		this.mis_baie = mis_baie;
		this.baie[0] = longueur;
		this.baie[1] = largeur;
		this.baie[2] = hauteur;
		try {
			recup_nomconst(adrMAC);
		} catch (IOException e) {
			System.err.println("Erreur dans la MAC, récupération du constructeur échouée.");
		}
	}

	public void recup_nomconst(String MAC) throws IOException {
		URL u = new URL("http://api.macvendors.com/" + MAC);
		URLConnection c = u.openConnection();
		BufferedReader din = new BufferedReader(new InputStreamReader(c.getInputStream()));
		adrMAC = din.readLine();
		din.close();
	}
	
	public boolean compare_adrMAC(String compareMAC) {
		if (compareMAC == adrMAC) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean compare_adrIP(String compareIP) {
		if (compareIP == adrIP) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void demarrer_Equipement() {
		etat = true;
	}
	
	public void eteindre_Equipement() {
		etat = false;
	}
	
	public String getMAC() {
		return adrMAC;
	}
	
	public String getIP() {
		return adrIP;
	}
	
	public String toString() {
		return "\nAdresse IP : " + adrIP + " \nAdresse MAC : " + adrMAC + " \nNom : " + nom_const + " \nMis en baie : " + mis_baie
				+ " \nDimensions : \nLongueur : " + baie[0] + " \nLargeur : " + baie[1] + " \nLargeur : " + baie[2] + " \nÉtat : " + etat;
	}
}