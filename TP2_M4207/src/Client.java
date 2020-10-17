
// Valentin GAUTREAU 2AFA
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
	// D�claration des donn�es membres
	private Socket sc;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private InterfaceGraphique ui;

	// D�finition des constructeurs
	public Client() throws ClassNotFoundException {
		ui = new InterfaceGraphique(this);
		try {
			connexion();
		} catch (IOException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null,
					"Erreur de connexion ! V�rifiez que le serveur est d�marr� ou joignable. Puis red�marrez le client.",
					"Erreur de connexion au serveur", 1);
			System.exit(1);
		}
	}

	public void connexion() throws IOException, ClassNotFoundException {
		sc = new Socket("localhost", 80);
		out = new ObjectOutputStream(sc.getOutputStream());
		in = new ObjectInputStream(sc.getInputStream());
		ui.appendLogPeage(
				"\nConnexion r�ussie !\nVous pouvez maintenant ajouter des �quipements ou des portes via le menu.");
		ui.activeUI(true);
	}

	public boolean ajoutPorte(Porte porte) {
		try {
			out.writeUTF("ajouterPortePeage");
			out.flush();

			out.writeObject(porte);
			out.flush();

			// System.out.println(in.readObject());

			return in.readBoolean();
		} catch (IOException e) {
			e.printStackTrace();
		} /*
			 * catch (ClassNotFoundException e) { e.printStackTrace(); }
			 */
		return false;
	}

	public boolean ajoutEquipement(Equipement eq) {
		try {
			out.writeUTF("ajouterEquipementPeage");
			out.flush();

			out.writeObject(eq);
			out.flush();

			// System.out.println(in.readObject());

			return in.readBoolean();
		} catch (IOException e) {
			e.printStackTrace();
		} /*
			 * catch (ClassNotFoundException e) { e.printStackTrace(); }
			 */
		return false;
	}

	public String afficherPortesInfoPeage() {
		try {
			out.writeUTF("afficherPortesInfoPeage");
			out.flush();

			return in.readUTF();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "\nErreur de r�cup�ration";
	}

	public String afficherEquipementsInfoPeage() {
		try {
			out.writeUTF("afficherEquipementsInfoPeage");
			out.flush();

			return in.readUTF();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "\nErreur de r�cup�ration";
	}

	public String rechercherEquipement(String adr, Boolean est_ip, Boolean all) {
		try {
			out.writeUTF("rechercherEquipement");
			out.flush();

			out.writeUTF(adr);
			out.flush();

			out.writeBoolean(est_ip);
			out.flush();

			out.writeBoolean(all);
			out.flush();

			return in.readUTF();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "\nErreur de r�cup�ration";
	}

	public Boolean sauvegarder() {
		try {
			out.writeUTF("sauvegarder");
			out.flush();

			return in.readBoolean();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		new Client();
	}
}