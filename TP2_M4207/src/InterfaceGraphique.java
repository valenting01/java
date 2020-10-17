
// Valentin GAUTREAU 2AFA
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGraphique extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	// Déclaration des données membres
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem ajtEqt, ajtPrt, save, quit;
	private JTextArea logPeage, logPorte;
	private JLabel iPMac, recherche, resultat, lblajMac, lblajIP, lblajEq, lblajMeb, lblajEtat, lblajEtPrt,
			lblajSensPrt, lblajEqtPrt;
	private JRadioButton ip, mac, oui, non, allume, eteint, haut, bas, sens1, sens2;
	private JButton rechercher, supprimer, ajouter, ajouterEqt, ajouterPrt;
	private JTextField valeur, resultat_valeur, tfajMAC, tfajIP, ajTaBar, ajLon, ajLar, ajHau, ajResEcr, ajTpsEcr,
			ajTF1, ajTF2, ajTF3;
	private JCheckBox allPortes, EtPrt, isTouch;
	private Client client;
	private Porte porte;
	private JComboBox<String> ajEq;
	private JFrame nouvel_equipement, nouvelle_porte;
	private Boolean ajoutdans, rechercherdsPorte = false;

	private double tailleBarriere, longueur, largeur, hauteur;
	private Boolean etatBarriere;

	private Boolean typeEcran;
	private String resolutionEcran;
	private double tempsDeReponse;

	private String nbImageSeconde;
	private String typeCamera;
	private double resolutionCamera;

	private double pressionMin;
	private double pressionMax;
	private double pressionAct;

	private double temperatureMin;
	private double temperatureMax;
	private double temperatureAct;

	private double frequence;
	private double longueurOnde;
	private double porteeMaximale;

	// Définition du constructeur
	InterfaceGraphique(Client client) {
		super("Gestion du péage"); // Appel du contructeur parent
		this.client = client;
		fenetrePrincipale();
	}

	public void fenetrePrincipale() {
		// paramétrages principaux de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contenu = getContentPane();
		contenu.setLayout(new BoxLayout(contenu, BoxLayout.X_AXIS));

		// initialisation et ajout de la barre de menus
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		// initialisation et ajout du menu "Menu" à la barre de menus
		menu = new JMenu("Menu");
		menuBar.add(menu);

		// initialisation et ajout des items au menu "Menu"
		ajtEqt = new JMenuItem("Ajouter \u00E9quipement");
		menu.add(ajtEqt);
		ajtEqt.addActionListener(this);
		ajtEqt.setEnabled(false);

		ajtPrt = new JMenuItem("Ajouter porte");
		menu.add(ajtPrt);
		ajtPrt.addActionListener(this);
		ajtPrt.setEnabled(false);
		
		save = new JMenuItem("Sauvegarder");
		menu.add(save);
		save.addActionListener(this);
		save.setEnabled(false);

		quit = new JMenuItem("Quitter");
		menu.add(quit);
		quit.addActionListener(this);

		// Déclaration et initialisation des JPanels
		JPanel gauche = new JPanel(new BorderLayout());
		JPanel droit = new JPanel(new GridBagLayout());

		// Contraintes du GridBagLayout
		GridBagConstraints constr = new GridBagConstraints();
		constr.insets = new Insets(5, 5, 5, 5);
		constr.anchor = GridBagConstraints.WEST;

		// Déclaration des valeurs grids à 0
		constr.gridx = 0;
		constr.gridy = 0;

		// Instanciation des labels
		iPMac = new JLabel("Type :");
		recherche = new JLabel("Adresse :");
		resultat = new JLabel("Résultat :");

		// Instanciation des zones de textes
		valeur = new JTextField(20);
		resultat_valeur = new JTextField(20);
		resultat_valeur.setEditable(false);

		logPeage = new JTextArea("");
		logPeage.setEditable(false);
		JScrollPane spLogPeage = new JScrollPane(logPeage);
		logPeage.setText("");
		
		// Instanciation des boutons radios
		ButtonGroup groupe = new ButtonGroup();
		ip = new JRadioButton("IP");
		mac = new JRadioButton("MAC");
		groupe.add(ip);
		groupe.add(mac);

		// Instanciation de la case à cocher
		allPortes = new JCheckBox("Rechercher sur toutes les portes");

		// Instanciation des boutons
		rechercher = new JButton("Rechercher");
		supprimer = new JButton("Supprimer");
		rechercher.addActionListener(this); // Ajout d'un écouteur d'action sur les boutons
		supprimer.addActionListener(this);
		rechercher.setEnabled(false);

		// Ajout au JPanel gauche
		gauche.add(spLogPeage);

		// Ajout au JPanel droit
		droit.add(iPMac, constr);
		constr.gridx = 1;
		droit.add(ip, constr);
		constr.anchor = GridBagConstraints.CENTER;
		droit.add(mac, constr);
		constr.gridx = 0;
		constr.gridy = 1;
		constr.anchor = GridBagConstraints.WEST;
		droit.add(recherche, constr);
		constr.gridx = 1;
		droit.add(valeur, constr);
		constr.gridx = 0;
		constr.gridy = 2;

		droit.add(allPortes, constr);
		constr.gridx = 0;
		constr.gridy = 3;

		constr.gridwidth = 2;
		constr.anchor = GridBagConstraints.CENTER;
		droit.add(rechercher, constr);
		constr.gridx = 0;
		constr.gridy = 4;

		constr.gridwidth = 0;
		constr.anchor = GridBagConstraints.WEST;
		droit.add(resultat, constr);
		constr.gridx = 1;
		droit.add(resultat_valeur, constr);
		constr.gridx = 0;
		constr.gridy = 5;
		droit.add(supprimer, constr);

		// Ajout des panels à la fenêtre
		contenu.add(gauche);
		contenu.add(droit);

		setMinimumSize(new Dimension(800, 750));
		// setSize(800, 700);
		// setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public void fenetreAjouterEquipement(Boolean ajoutdans) {
		this.ajoutdans = ajoutdans;
		// paramétrages principaux de la fenêtre
		nouvel_equipement = new JFrame("Ajout d'un équipement");
		nouvel_equipement.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenu = nouvel_equipement.getContentPane();
		contenu.setLayout(new GridBagLayout());

		// Contraintes du GridBagLayout
		GridBagConstraints constr = new GridBagConstraints();
		constr.insets = new Insets(5, 5, 5, 5);
		constr.anchor = GridBagConstraints.WEST;

		// Déclaration des valeurs grids à 0
		constr.gridx = 0;
		constr.gridy = 0;

		// Instanciation des labels
		lblajMac = new JLabel("Saisir une adresse MAC :");
		lblajIP = new JLabel("Saisir une adresse IP :");
		lblajEq = new JLabel("Sélectionner le type d'équipement");
		lblajMeb = new JLabel("Mise en baie possible :");
		lblajEtat = new JLabel("État");

		// Instanciation des zones de textes
		tfajMAC = new JTextField(20);
		tfajIP = new JTextField(20);

		// Instanciation de la liste
		ajEq = new JComboBox<String>();
		ajEq.addItem("");
		ajEq.addItem("Barrière");
		ajEq.addItem("Borne de paiement");
		ajEq.addItem("Caméra");
		ajEq.addItem("Capteur de pression atmosphérique");
		ajEq.addItem("Capteur de température");
		ajEq.addItem("Radar");
		ajEq.addActionListener(this);

		// Instanciation des boutons radios
		ButtonGroup ajMeb = new ButtonGroup();
		oui = new JRadioButton("Oui");
		non = new JRadioButton("Non");
		ajMeb.add(oui);
		ajMeb.add(non);

		ButtonGroup ajEtat = new ButtonGroup();
		allume = new JRadioButton("Allumé");
		eteint = new JRadioButton("Éteint");
		ajEtat.add(allume);
		ajEtat.add(eteint);

		// Instanciation des boutons
		ajouter = new JButton("Ajouter");
		ajouter.addActionListener(this);

		// Ajout au JPanel
		contenu.add(lblajMac, constr);
		constr.gridx = 1;
		contenu.add(tfajMAC, constr);
		constr.gridx = 0;
		constr.gridy = 1;

		contenu.add(lblajIP, constr);
		constr.gridx = 1;
		contenu.add(tfajIP, constr);
		constr.gridx = 0;
		constr.gridy = 2;

		contenu.add(lblajEq, constr);
		constr.gridx = 1;
		contenu.add(ajEq, constr);
		constr.gridx = 0;
		constr.gridy = 3;

		contenu.add(lblajMeb, constr);
		constr.gridx = 1;
		contenu.add(oui, constr);
		constr.anchor = GridBagConstraints.CENTER;
		contenu.add(non, constr);
		constr.gridx = 0;
		constr.gridy = 4;

		constr.anchor = GridBagConstraints.WEST;

		contenu.add(lblajEtat, constr);
		constr.gridx = 1;
		contenu.add(allume, constr);
		constr.anchor = GridBagConstraints.CENTER;
		contenu.add(eteint, constr);
		constr.gridx = 0;
		constr.gridy = 5;

		constr.gridwidth = 2;
		constr.anchor = GridBagConstraints.CENTER;
		contenu.add(ajouter, constr);
		constr.gridx = 0;
		constr.gridy = 6;

		nouvel_equipement.setResizable(false);
		nouvel_equipement.setSize(600, 300);
		nouvel_equipement.setLocationRelativeTo(null);
		nouvel_equipement.setVisible(true);
	}

	public void fenetreAjouterPorte() {
		// paramétrages principaux de la fenêtre
		porte = null;
		porte = new Porte();
		nouvelle_porte = new JFrame("Ajout d'une porte");
		nouvelle_porte.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenu = nouvelle_porte.getContentPane();
		contenu.setLayout(new GridBagLayout());

		// Contraintes du GridBagLayout
		GridBagConstraints constr = new GridBagConstraints();
		constr.insets = new Insets(5, 5, 5, 5);
		constr.anchor = GridBagConstraints.WEST;

		// Déclaration des valeurs grids à 0
		constr.gridx = 0;
		constr.gridy = 0;

		// Instanciation des labels
		lblajEtPrt = new JLabel("État de la porte :");
		lblajSensPrt = new JLabel("Sens de la porte :");
		lblajEqtPrt = new JLabel("Liste des équipements ajoutés :");

		// Instanciation de la case à cocher
		EtPrt = new JCheckBox("Activée");

		// Instanciation des boutons radios
		ButtonGroup sens = new ButtonGroup();
		sens1 = new JRadioButton("Sens 1");
		sens2 = new JRadioButton("Sens 2");
		sens.add(sens1);
		sens.add(sens2);

		// Instanciation de la zone de texte
		logPorte = new JTextArea(10, 20);
		logPorte.setEditable(false);
		JScrollPane spLogPorte = new JScrollPane(logPorte);

		// Instanciation des boutons
		ajouterEqt = new JButton("Ajouter un équipement");
		ajouterEqt.addActionListener(this);
		ajouterPrt = new JButton("Ajouter la porte");
		ajouterPrt.addActionListener(this);

		// Ajout au JPanel
		constr.gridx = 1;
		contenu.add(lblajEtPrt, constr);
		constr.gridx = 2;
		contenu.add(EtPrt, constr);
		constr.gridx = 1;
		constr.gridy = 1;

		contenu.add(lblajSensPrt, constr);
		constr.gridx = 2;
		contenu.add(sens1, constr);
		constr.gridx = 3;
		constr.anchor = GridBagConstraints.EAST;
		contenu.add(sens2, constr);
		constr.gridx = 0;
		constr.gridy = 2;

		constr.anchor = GridBagConstraints.WEST;

		constr.gridwidth = 3;
		constr.anchor = GridBagConstraints.CENTER;
		contenu.add(ajouterEqt, constr);
		constr.gridx = 0;
		constr.gridy = 3;

		constr.anchor = GridBagConstraints.WEST;

		contenu.add(lblajEqtPrt, constr);
		constr.gridx = 3;

		constr.gridwidth = 2;
		constr.anchor = GridBagConstraints.CENTER;
		contenu.add(spLogPorte, constr);
		constr.gridx = 1;
		constr.gridy = 4;

		constr.anchor = GridBagConstraints.WEST;

		constr.gridwidth = 3;
		constr.anchor = GridBagConstraints.CENTER;
		contenu.add(ajouterPrt, constr);
		constr.gridx = 0;
		constr.gridy = 5;

		nouvelle_porte.setResizable(false);
		nouvelle_porte.setSize(600, 500);
		nouvelle_porte.setLocationRelativeTo(null);
		nouvelle_porte.setVisible(true);
		infoPorte();
	}

	// Méthode permettant de concaténer dans la JTextArea du péage.
	public void appendLogPeage(String infos) {
		logPeage.append(infos);
	}
	
	// Méthode permettant de mettre à jour la JTextArea affichant les équipements ajoutés à la porte en cours.
	public void infoPorte() {
		logPorte.setText("");
		logPorte.append(porte.afficherEquipementsInfoPeage());
	}

	// Méthode permettant de mettre à jour la JTextArea affichant les portes et équipements du péage.
	public void infoPeage() {
		logPeage.setText("");
		logPeage.append("Liste des informations du péage");
		logPeage.append("\n================================");
		logPeage.append("\nListe des équipements");
		logPeage.append(client.afficherEquipementsInfoPeage());
		logPeage.append("\n================================");
		logPeage.append("\nListe des portes");
		logPeage.append(client.afficherPortesInfoPeage());
	}
	
	// Méthode permettant l'activation ou la désactivation de l'interface graphique.
	public void activeUI (Boolean active) {
		ajtEqt.setEnabled(active);
		ajtPrt.setEnabled(active);
		save.setEnabled(active);
		rechercher.setEnabled(active);
		infoPeage();
	}

	public void actionPerformed(ActionEvent eve) // Méthode unique de l'interface ActionListener
	{
		boolean tf_macorip = true;
		boolean tf_allportes = true;

		if (eve.getSource() == rechercher) // Si le bouton "rechercher" est poussé
		{
			if (valeur.getText().isEmpty() == true || (!ip.isSelected() && !mac.isSelected())) { // Vérifie que les champs obligatoires soit remplis
				JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs obligatoires",
						"Données manquantes", 1);
			} else {
				if (ip.isSelected()) {
					tf_macorip = true;
				}
				if (mac.isSelected()) {
					tf_macorip = false;
				}
				if (allPortes.isSelected()) {
					tf_allportes = true;
				} else {
					tf_allportes = false;
				}
				String renvoi = client.rechercherEquipement(valeur.getText(), tf_macorip, tf_allportes);
				resultat_valeur.setText(renvoi); // Marque le résultat dans la JTextField resultat_valeur
			}
		}
		if (eve.getSource() == supprimer) // Si le bouton "annuler" est poussé
		{
			valeur.setText(""); // Enlève le texte de la valeur recherchée
			resultat_valeur.setText(""); // Enlève le texte du résultat
		}
		if (eve.getSource() == ajtEqt) // Si "Ajouter un équipement" est poussé dans le menu
		{
			fenetreAjouterEquipement(true); // Appel de la méthode pour ajouter un équipement dans le péage car valeur d'entrée à vrai.
		}
		if (eve.getSource() == ajtPrt) // Si "Ajouter une porte" est poussé dans le menu
		{
			fenetreAjouterPorte(); // Appel de la méthode pour ajouter une porte
		}
		if (eve.getSource() == save) // Si "Sauvegarder" est poussé dans le menu
		{
			client.sauvegarder(); // Appel de la méthode pour sauvegarder
		}
		if (eve.getSource() == quit) // Si "Quitter" est poussé dans le menu
		{
			System.exit(0); // Arrête le programme
		}
		if (eve.getSource() == ajouterEqt) // Si le bouton "Ajouter" est poussé dans la fenêtre d'ajout d'un équipement
		{
			fenetreAjouterEquipement(false); // Appel de la méthode pour ajouter un équipement dans la porte en cours car valeur d'entrée à faux.
		}
		if (eve.getSource() == ajouterPrt) // Si le bouton "Ajouter" est poussé dans la fenêtre d'ajout d'une porte
		{
			if ((!sens1.isSelected() && !sens2.isSelected())) { // 
				JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs obligatoires",
						"Données manquantes", 1);
			} else {
				if (sens1.isSelected()) {
					porte.setSens("Sens 1");
				} else {
					porte.setSens("Sens 2");
				}
				if (EtPrt.isSelected()) {
					porte.setEtat_porte(true);
				} else {
					porte.setEtat_porte(false);
				}
				Boolean srvajPrt = client.ajoutPorte(porte);
				
				if (srvajPrt) {
					JOptionPane.showMessageDialog(null, "Porte ajoutée avec succès !",
							"Succès", 1);
					infoPeage();
					nouvelle_porte.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de la porte !",
							"Erreur", 1);
				}			
			}
		}
		if (eve.getSource() == ajEq) {
			int selection = ajEq.getSelectedIndex();
			switch (selection) {
			case 0:
				break;
			case 1:
				ButtonGroup ajBar = new ButtonGroup();
				bas = new JRadioButton("Bas");
				haut = new JRadioButton("Haut");
				ajBar.add(bas);
				ajBar.add(haut);

				ajTaBar = new JTextField(20);

				int opt1 = JOptionPane.showOptionDialog(null,
						new Object[] { "Taille de la barrière :", ajTaBar, "État de la barrière :", haut, bas },
						"Ajout d'une barrière", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
						null);

				if (opt1 == -1 || opt1 == 2) {
					ajEq.setSelectedIndex(0);
				} else {
					if (ajTaBar.getText().isEmpty() == true || (!bas.isSelected() && !haut.isSelected())) {
						JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs obligatoires",
								"Données manquantes", 1);
						ajEq.setSelectedIndex(0);
					} else {
						if (bas.isSelected()) {
							etatBarriere = false;
						} else {
							etatBarriere = true;
						}
						tailleBarriere = Double.parseDouble(ajTaBar.getText());
					}
				}
				break;
			case 2:
				isTouch = new JCheckBox("Disponible");
				ajResEcr = new JTextField(20);
				ajTpsEcr = new JTextField(20);

				int opt2 = JOptionPane.showOptionDialog(null,
						new Object[] { "Tactile :", isTouch, "Résolution de l'écran :", ajResEcr, "Temps de réponse :",
								ajTpsEcr },
						"Ajout d'une borne de paiement", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, null, null);

				if (opt2 == -1 || opt2 == 2) {
					ajEq.setSelectedIndex(0);
				} else {
					if (ajResEcr.getText().isEmpty() == true || ajTpsEcr.getText().isEmpty() == true) {
						JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs obligatoires",
								"Données manquantes", 1);
						ajEq.setSelectedIndex(0);
					} else {
						if (isTouch.isSelected()) {
							typeEcran = false;
						} else {
							typeEcran = true;
						}
						resolutionEcran = ajResEcr.getText();
						tempsDeReponse = Double.parseDouble(ajTpsEcr.getText());
					}
				}
				break;
			case 3:
				ajTF1 = new JTextField(20);
				ajTF2 = new JTextField(20);
				ajTF3 = new JTextField(20);

				int opt3 = JOptionPane.showOptionDialog(null,
						new Object[] { "Nombre d'images par seconde :", ajTF1, "Type de caméra : ", ajTF2,
								"Résolution de la caméra :", ajTF3 },
						"Ajout d'une caméra", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
						null);

				if (opt3 == -1 || opt3 == 2) {
					ajEq.setSelectedIndex(0);
				} else {
					if (ajTF1.getText().isEmpty() == true || ajTF2.getText().isEmpty() == true
							|| ajTF3.getText().isEmpty() == true) {
						JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs obligatoires",
								"Données manquantes", 1);
						ajEq.setSelectedIndex(0);
					} else {
						nbImageSeconde = ajTF1.getText();
						typeCamera = ajTF2.getText();
						resolutionCamera = Double.parseDouble(ajTF3.getText());
					}
				}
				break;
			case 4:
				ajTF1 = new JTextField(20);
				ajTF2 = new JTextField(20);
				ajTF3 = new JTextField(20);

				int opt4 = JOptionPane.showOptionDialog(null,
						new Object[] { "Pression minimale :", ajTF1, "Pression maximale : ", ajTF2,
								"Pression actuelle :", ajTF3 },
						"Ajout d'un capteur de pression atmosphérique", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);

				if (opt4 == -1 || opt4 == 2) {
					ajEq.setSelectedIndex(0);
				} else {
					if (ajTF1.getText().isEmpty() == true || ajTF2.getText().isEmpty() == true
							|| ajTF3.getText().isEmpty() == true) {
						JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs obligatoires",
								"Données manquantes", 1);
						ajEq.setSelectedIndex(0);
					} else {
						pressionMin = Double.parseDouble(ajTF1.getText());
						pressionMax = Double.parseDouble(ajTF2.getText());
						pressionAct = Double.parseDouble(ajTF3.getText());
					}
				}
				break;
			case 5:
				ajTF1 = new JTextField(20);
				ajTF2 = new JTextField(20);
				ajTF3 = new JTextField(20);

				int opt5 = JOptionPane.showOptionDialog(null,
						new Object[] { "Température minimale :", ajTF1, "Température maximale : ", ajTF2,
								"Température actuelle :", ajTF3 },
						"Ajout d'un capteur de température", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, null, null);

				if (opt5 == -1 || opt5 == 2) {
					ajEq.setSelectedIndex(0);
				} else {
					if (ajTF1.getText().isEmpty() == true || ajTF2.getText().isEmpty() == true
							|| ajTF3.getText().isEmpty() == true) {
						JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs obligatoires",
								"Données manquantes", 1);
						ajEq.setSelectedIndex(0);
					} else {
						temperatureMin = Double.parseDouble(ajTF1.getText());
						temperatureMax = Double.parseDouble(ajTF2.getText());
						temperatureAct = Double.parseDouble(ajTF3.getText());
					}
				}
				break;
			case 6:
				ajTF1 = new JTextField(20);
				ajTF2 = new JTextField(20);
				ajTF3 = new JTextField(20);

				int opt6 = JOptionPane.showOptionDialog(null,
						new Object[] { "Fréquence :", ajTF1, "Longueur d'onde : ", ajTF2, "Portée maximale :", ajTF3 },
						"Ajout d'un radar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
						null);

				if (opt6 == -1 || opt6 == 2) {
					ajEq.setSelectedIndex(0);
				} else {
					if (ajTF1.getText().isEmpty() == true || ajTF2.getText().isEmpty() == true
							|| ajTF3.getText().isEmpty() == true) {
						JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs obligatoires",
								"Données manquantes", 1);
						ajEq.setSelectedIndex(0);
					} else {
						frequence = Double.parseDouble(ajTF1.getText());
						longueurOnde = Double.parseDouble(ajTF2.getText());
						porteeMaximale = Double.parseDouble(ajTF3.getText());
					}
				}
				break;
			}
		}
		if (eve.getSource() == ajouter) // Si quand le bouton "valider" est poussé
		{
			int selection = ajEq.getSelectedIndex();
			if (tfajIP.getText().isEmpty() == true || tfajMAC.getText().isEmpty() == true
					|| (!oui.isSelected() && !non.isSelected()) || (!allume.isSelected() && !eteint.isSelected())
					|| (selection == 0)) {
				JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs obligatoires",
						"Données manquantes", 1);
			} else {
				if (!ajoutdans) {
					if ((porte.rechercherEquipement(tfajIP.getText(), true))
							|| (porte.rechercherEquipement(tfajMAC.getText(), false))) {
						rechercherdsPorte = true;
					} else {
						rechercherdsPorte = false;
					}
				}
				if ((client.rechercherEquipement(tfajIP.getText(), true, true) == "Présent")
						|| (client.rechercherEquipement(tfajMAC.getText(), true, true) == "Présent")
						|| rechercherdsPorte) {
					JOptionPane.showMessageDialog(null, "Équipement avec même adresse IP ou MAC déjà présent !",
							"Données manquantes", 1);
				} else {

					if (oui.isSelected()) {
						ajLon = new JTextField(20);
						ajLar = new JTextField(20);
						ajHau = new JTextField(20);

						int opt = JOptionPane.showOptionDialog(null,
								new Object[] { "Longeur :", ajLon, "Largeur :", ajLar, "Hauteur :", ajHau },
								"Dimension équipement mis en baie", JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, null, null);

						if (opt == -1 || opt == 2) {
							ajEq.setSelectedIndex(0);
						} else {
							if (ajLon.getText().isEmpty() == true || ajLar.getText().isEmpty() == true
									|| ajHau.getText().isEmpty() == true) {
								JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs obligatoires",
										"Données manquantes", 1);
								ajEq.setSelectedIndex(0);
							} else {
								longueur = Double.parseDouble(ajLon.getText());
								largeur = Double.parseDouble(ajLar.getText());
								hauteur = Double.parseDouble(ajHau.getText());
							}
						}
					} else {
						longueur = 0;
						longueur = 0;
						longueur = 0;
					}
					Boolean srvajtEqt = false;
					switch (selection) {
					case 1:
						if (ajoutdans) {
							srvajtEqt = client.ajoutEquipement(new Barriere(tfajIP.getText(), tfajMAC.getText(), oui.isSelected(),
									longueur, largeur, hauteur, tailleBarriere, etatBarriere));
						} else {
							porte.ajoutEquipement(new Barriere(tfajIP.getText(), tfajMAC.getText(), oui.isSelected(),
									longueur, largeur, hauteur, tailleBarriere, etatBarriere));
						}
						break;
					case 2:
						if (ajoutdans) {
							srvajtEqt = client.ajoutEquipement(
									new BornePaiement(tfajIP.getText(), tfajMAC.getText(), oui.isSelected(), longueur,
											largeur, hauteur, typeEcran, resolutionEcran, tempsDeReponse));
						} else {
							porte.ajoutEquipement(
									new BornePaiement(tfajIP.getText(), tfajMAC.getText(), oui.isSelected(), longueur,
											largeur, hauteur, typeEcran, resolutionEcran, tempsDeReponse));
						}
						break;
					case 3:
						if (ajoutdans) {
							srvajtEqt = client.ajoutEquipement(new Camera(tfajIP.getText(), tfajMAC.getText(), oui.isSelected(),
									longueur, largeur, hauteur, nbImageSeconde, typeCamera, resolutionCamera));
						} else {
							porte.ajoutEquipement(new Camera(tfajIP.getText(), tfajMAC.getText(), oui.isSelected(),
									longueur, largeur, hauteur, nbImageSeconde, typeCamera, resolutionCamera));
						}
						break;
					case 4:
						if (ajoutdans) {
							srvajtEqt = client.ajoutEquipement(
									new CapteurPressionAt(tfajIP.getText(), tfajMAC.getText(), oui.isSelected(),
											longueur, largeur, hauteur, pressionMin, pressionMax, pressionAct));
						} else {
							porte.ajoutEquipement(
									new CapteurPressionAt(tfajIP.getText(), tfajMAC.getText(), oui.isSelected(),
											longueur, largeur, hauteur, pressionMin, pressionMax, pressionAct));
						}
						break;
					case 5:
						if (ajoutdans) {
							srvajtEqt = client.ajoutEquipement(new CapteurTemperature(tfajIP.getText(), tfajMAC.getText(),
									oui.isSelected(), longueur, largeur, hauteur, temperatureMin, temperatureMax,
									temperatureAct));
						} else {
							porte.ajoutEquipement(new CapteurTemperature(tfajIP.getText(), tfajMAC.getText(),
									oui.isSelected(), longueur, largeur, hauteur, temperatureMin, temperatureMax,
									temperatureAct));
						}
						break;
					case 6:
						if (ajoutdans) {
							srvajtEqt = client.ajoutEquipement(new Radar(tfajIP.getText(), tfajMAC.getText(), oui.isSelected(),
									longueur, largeur, hauteur, frequence, longueurOnde, porteeMaximale));
						} else {
							porte.ajoutEquipement(new Radar(tfajIP.getText(), tfajMAC.getText(), oui.isSelected(),
									longueur, largeur, hauteur, frequence, longueurOnde, porteeMaximale));
						}
						break;
					}
					if (!ajoutdans) {
						infoPorte();
						nouvel_equipement.dispose();
					}
					else {
						if (srvajtEqt) {
							JOptionPane.showMessageDialog(null, "Équipement ajouté avec succès !",
									"Succès", 1);
							infoPeage();
							nouvel_equipement.dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de l'équipement !",
									"Erreur", 1);
						}
					}
				}
			}
		}
	}
}