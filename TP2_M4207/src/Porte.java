// Valentin GAUTREAU 2AFA
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

class Porte implements Serializable {
	// Déclaration des données membres
	private boolean etat_porte;
	private String sens;
	private ArrayList<Equipement> list_equipements;
	
	// Définition des constructeurs
	public Porte() {
		etat_porte = false;
		sens = "Paris-Nantes";
		list_equipements = new ArrayList<Equipement>();
	}
	
	public Porte(boolean etat_porte, String sens) {
		this.etat_porte = etat_porte;
		this.sens = sens;
		this.list_equipements = new ArrayList<Equipement>();
	}
	
	public String toString() {
		   return "\nÉtat de la porte : " + etat_porte + " \nSens : " + sens + "\nListe des équipements :" + afficherEquipementsInfoPeage();
		}
	
    public void ajoutEquipement(Equipement e) {
    	list_equipements.add(e); // Ajoute l'équipement dans l'ArrayList
    	}
	
	public void afficherEquipements() {
		Iterator<Equipement> iterator = list_equipements.iterator();
		System.out.println("Liste des équipements de la porte : ");
		while (iterator.hasNext()) 
            System.out.println(iterator.next());
		}
	
	public String afficherEquipementsInfoPeage() {
		Iterator<Equipement> iterator = list_equipements.iterator();
		String liste = "";
		while (iterator.hasNext()) {
			liste = liste + "\n" + iterator.next().toString();
		}
		return liste;
	}
	
	public boolean isEtat_porte() {
		return etat_porte;
	}

	public void setEtat_porte(boolean etat_porte) {
		this.etat_porte = etat_porte;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
	}

	public boolean rechercherEquipement(String adr, Boolean est_ip) {
		Iterator<Equipement> iterator = list_equipements.iterator();
		if (est_ip) {
			while (iterator.hasNext()) {
				Equipement equipement = iterator.next();
				if (equipement.getIP().equals(adr)) {
					return true;
				}
			}
		}
		else {
			while (iterator.hasNext()) {
				Equipement equipement = iterator.next();
				if (equipement.getMAC().equals(adr)) {
					return true;
				}
			}
		}
		return false;
	}
}