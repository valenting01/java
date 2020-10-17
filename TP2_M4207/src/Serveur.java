
// Valentin GAUTREAU 2AFA
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Serveur {
	// Déclaration des données membres
	private ArrayList<Porte> list_portes;
	private ArrayList<Equipement> list_equipements;
	private ServerSocket ss;
	private Socket client;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	// Définition des constructeurs
	public Serveur() {
		connexion();
	}

	public void connexion() {
		list_portes = new ArrayList<Porte>();
		list_equipements = new ArrayList<Equipement>();
		int port = 80;
		try {
			ss = new ServerSocket(port, 5);
			System.out.println("Le serveur reçoit sur le Port : " + ss.getLocalPort());
			System.out.println("Prêt");
			client = ss.accept();
			System.out.print(" Connexion reçue de : ");
			System.out.println(" " + client.getInetAddress());

			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());

			restaurer();
			traitement();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void traitement() {
		try {
			while (true) {
				String typeAction = in.readUTF();
				// System.out.println(typeAction);
				if (typeAction.equals("ajouterEquipementPeage")) {
					Equipement equipement = (Equipement) in.readObject();
					ajoutEquipement(equipement);
					// out.writeObject(afficherEquipementsInfoPeage());
					// out.flush();
					out.writeBoolean(true);
					out.flush();
				}
				if (typeAction.equals("ajouterPortePeage")) {
					Porte porte = (Porte) in.readObject();
					ajoutPorte(porte);
					// out.writeObject(afficherEquipementsInfoPeage());
					// out.flush();
					out.writeBoolean(true);
					out.flush();
				}
				if (typeAction.equals("rechercherEquipement")) {
					String a = in.readUTF();
					Boolean b = in.readBoolean();
					Boolean c = in.readBoolean();
					String d = rechercherEquipement(a, b, c);
					out.writeUTF(d);
					out.flush();
				}
				if (typeAction.equals("afficherPortesInfoPeage")) {
					String a = afficherPortesInfoPeage();
					out.writeUTF(a);
					out.flush();
				}
				if (typeAction.equals("afficherEquipementsInfoPeage")) {
					String a = afficherEquipementsInfoPeage();
					out.writeUTF(a);
					out.flush();
				}
				if (typeAction.equals("sauvegarder")) {
					Boolean a = sauvegarder();
					out.writeBoolean(a);
					out.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void ajoutPorte(Porte porte) {
		list_portes.add(porte); // Ajoute l'équipement dans l'ArrayList
	}

	public void ajoutEquipement(Equipement eq) {
		list_equipements.add(eq); // Ajoute l'équipement dans l'ArrayList
	}

	public void afficherPortes() {
		Iterator<Porte> iterator = list_portes.iterator();
		System.out.println("Liste des portes du péage : ");
		while (iterator.hasNext())
			System.out.println(iterator.next());
	}

	public String afficherPortesInfoPeage() {
		Iterator<Porte> iterator = list_portes.iterator();
		String liste = "";
		while (iterator.hasNext()) {
			liste = liste + "\n" + iterator.next().toString();
		}
		return liste;
	}

	public void afficherEquipements() {
		Iterator<Equipement> iterator = list_equipements.iterator();
		System.out.println("Liste des équipements du péage : ");
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

	public String rechercherEquipement(String adr, Boolean est_ip, Boolean all) {
		Iterator<Porte> iterator_porte = list_portes.iterator();
		Iterator<Equipement> iterator = list_equipements.iterator();

		if (all) {
			if (est_ip) {
				while (iterator_porte.hasNext()) {
					Porte porte = iterator_porte.next();
					if (porte.rechercherEquipement(adr, est_ip)) {
						return "Présent";
					}
				}
				while (iterator.hasNext()) {
					Equipement equipement = iterator.next();
					if (equipement.getIP().equals(adr)) {
						return "Présent";
					}
				}
			} else {
				while (iterator_porte.hasNext()) {
					Porte porte = iterator_porte.next();
					if (porte.rechercherEquipement(adr, est_ip)) {
						return "Présent";
					}
				}
				while (iterator.hasNext()) {
					Equipement equipement = iterator.next();
					if (equipement.getMAC().equals(adr)) {
						return "Présent";
					}
				}
			}
		} else {
			if (est_ip) {
				while (iterator.hasNext()) {
					Equipement equipement = iterator.next();
					if (equipement.getIP().equals(adr)) {
						return "Présent";
					}
				}
			} else {
				while (iterator.hasNext()) {
					Equipement equipement = iterator.next();
					if (equipement.getMAC().equals(adr)) {
						return "Présent";
					}
				}
			}
		}
		return "Non trouvé";
	}

	public Boolean sauvegarder() {
		try {
			FileOutputStream fos = new FileOutputStream("C:/Users/valen/Desktop/equipements.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list_equipements);
			oos.close();
			fos.close();
			
			fos = new FileOutputStream("C:/Users/valen/Desktop/portes.dat");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(list_portes);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return true;
	}
	
	public void restaurer() {
		try
        {
            FileInputStream fis = new FileInputStream("C:/Users/valen/Desktop/equipements.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
 
            list_equipements = (ArrayList) ois.readObject();
 
            ois.close();
            fis.close();
            
            fis = new FileInputStream("C:/Users/valen/Desktop/portes.dat");
            ois = new ObjectInputStream(fis);
 
            list_portes = (ArrayList) ois.readObject();
 
            ois.close();
            fis.close();
        }
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
            return;
        } 
        catch (ClassNotFoundException c) 
        {
            c.printStackTrace();
            return;
        }
	}

	public static void main(String[] args) {
		new Serveur();
	}
}
