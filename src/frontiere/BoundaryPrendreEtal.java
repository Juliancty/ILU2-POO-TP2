package frontiere;

import controleur.ControlPrendreEtal;

public class BoundaryPrendreEtal {
	private ControlPrendreEtal controlPrendreEtal;

	public BoundaryPrendreEtal(ControlPrendreEtal controlChercherEtal) {
		this.controlPrendreEtal = controlChercherEtal;
	}

	public void prendreEtal(String nomVendeur) {
		boolean nomVendeurConnu = controlPrendreEtal.verifierIdentite(nomVendeur);
		if(!nomVendeurConnu) {
			System.out.println("Je suis desol�e" + nomVendeur + "mais il faut �tre un habitant de notre village pour commencer ici.\n");
		} else {
			System.out.println("Bonjour " + nomVendeurConnu + "je vais regarder si je peux vous trouver un �tal.\n");
			boolean etalDisponible = controlPrendreEtal.resteEtals();
			if(!etalDisponible) {
				System.out.println("D�sol�e " + nomVendeur + " je n'ai plus d'�tal qui ne soit pas d�j� occup�.\n");
			} else {
				installerVendeur(nomVendeur);
			}
		}
	}

	private void installerVendeur(String nomVendeur) {
		System.out.println("C'est parfait, il me reste un �tal pour vous !\n");
		System.out.println("Il me faudrait quelques renseignements :\n");
		String produit = Clavier.entrerChaine("Quel produit souhaitez-vous vendre ?\n");
		int nbProduit = Clavier.entrerEntier("Combien souhaitez-vous en vendre ?\n");
		int numeroEtal = controlPrendreEtal.prendreEtal(nomVendeur, produit, nbProduit);
		if(numeroEtal != -1) {
			System.out.println("Le vendeur " + nomVendeur + " s'est install� � l'�tal n�" + numeroEtal + 1);
		}
	}
}
