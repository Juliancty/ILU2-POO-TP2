package frontiere;

import controleur.ControlAcheterProduit;
import personnages.Gaulois;
import villagegaulois.Etal;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		boolean nomAcheteurConnu = controlAcheterProduit.verifierIdentite(nomAcheteur);
		if(!nomAcheteurConnu) {
			System.out.println("Je suis d�sol�e " + nomAcheteur + " mais il faut �tre un habitant de notre village pour commercer ici\n");
		} else {
			String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?\n");
			Gaulois[] vendeurs = controlAcheterProduit.rechercherVendeursProduit(produit);
			if(vendeurs.length == 0) {
				System.out.println("D�sol�, personne ne vend ce produit au march�\n");
			} else {
				System.out.println("Chez quel commer�ant voulez-vous acheter des " + produit + "\n");
				for(int i = 0; i < vendeurs.length; i++) {
					System.out.println(i+1 + " - " + vendeurs[i].getNom() + "\n");
				}
				int choixVendeur = Clavier.entrerEntier("");
				Gaulois vendeur = vendeurs[choixVendeur-1];
				System.out.println(nomAcheteur + " se d�place jusqu'� l'�tal du vendeur " + vendeur.getNom() + "\n");
				System.out.println("Bonjour " + nomAcheteur + "\n");
				int quantite = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?\n");
				int quantiteAcheter = controlAcheterProduit.acheterProduit(vendeur.getNom(),quantite);
				if(quantiteAcheter == 0) {
					System.out.println(nomAcheteur + " veut acheter " + quantite + " " + produit + ", malheureusement il n'y en a plus !\n");
				} else if(quantiteAcheter == quantite) {
					System.out.println(nomAcheteur + " ach�te " + quantiteAcheter + " " + produit + " � " + vendeur.getNom() + "\n");
				} else {
					System.out.println(nomAcheteur + " veut acheter " + quantite + " " + produit + ", malheureusement " + vendeur.getNom() + " n'en a plus que " + quantiteAcheter + ". " + nomAcheteur + " ach�te tout le stock de Bonemine.\ns");
				}
			}
		}
	}
}
