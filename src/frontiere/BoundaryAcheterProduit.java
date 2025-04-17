package frontiere;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		boolean nomAcheteurConnu = controlAcheterProduit.verifierIdentite(nomAcheteur);
		if (!nomAcheteurConnu) {
			System.out.println("Je suis désolée " + nomAcheteur
					+ " mais il faut être un habitant de notre village pour commercer ici\n");
		} else {
			choisirProduit(nomAcheteur);
		}
	}
	
	private void choisirProduit(String nomAcheteur) {
		String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?\n");
		String[] vendeurs = controlAcheterProduit.rechercherVendeursProduit(produit);
		if (vendeurs == null) {
			System.out.println("Désolé, personne ne vend ce produit au marché\n");
		} else {
			choisirCommercant(nomAcheteur, vendeurs, produit);
		}
	}
	
	private void choisirCommercant(String nomAcheteur, String[] vendeurs, String produit) {
		System.out.println("Chez quel commerçant voulez-vous acheter des " + produit + "\n");
		for (int i = 0; i < vendeurs.length; i++) {
			System.out.println(i + 1 + " - " + vendeurs[i] + "\n");
		}
		int choixVendeur = Clavier.entrerEntier("");
		String vendeur = vendeurs[choixVendeur - 1];
		System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + vendeur + "\n");
		System.out.println("Bonjour " + nomAcheteur + "\n");
		choisirQuantite(nomAcheteur, vendeur, produit);
	}
	
	private void choisirQuantite(String nomAcheteur, String vendeur, String produit) {
		int quantite = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?\n");
		int quantiteAcheter = controlAcheterProduit.acheterProduit(vendeur, quantite);
		if (quantiteAcheter == 0) {
			System.out.println(nomAcheteur + " veut acheter " + quantite + " " + produit
					+ ", malheureusement il n'y en a plus !\n");
		} else if (quantiteAcheter == quantite) {
			System.out.println(nomAcheteur + " achète " + quantiteAcheter + " " + produit + " à "
					+ vendeur + "\n");
		} else {
			System.out.println(nomAcheteur + " veut acheter " + quantite + " " + produit + ", malheureusement "
					+ vendeur + " n'en a plus que " + quantiteAcheter + ". " + nomAcheteur
					+ " achète tout le stock de Bonemine.\ns");
		}
	}
}
