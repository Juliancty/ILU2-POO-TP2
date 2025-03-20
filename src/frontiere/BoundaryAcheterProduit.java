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
			System.out.println("Je suis désolée " + nomAcheteur + " mais il faut être un habitant de notre village pour commercer ici\n");
		} else {
			String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?\n");
			Gaulois[] vendeurs = controlAcheterProduit.rechercherVendeursProduit(produit);
			if(vendeurs.length == 0) {
				System.out.println("Désolé, personne ne vend ce produit au marché\n");
			} else {
				System.out.println("Chez quel commerçant voulez-vous acheter des " + produit + "\n");
				for(int i = 0; i < vendeurs.length; i++) {
					System.out.println(i+1 + " - " + vendeurs[i].getNom() + "\n");
				}
				int choixVendeur = Clavier.entrerEntier("");
				Gaulois vendeur = vendeurs[choixVendeur-1];
				System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + vendeur.getNom() + "\n");
				System.out.println("Bonjour " + nomAcheteur + "\n");
				int quantite = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?\n");
				int quantiteAcheter = controlAcheterProduit.acheterProduit(vendeur.getNom(),quantite);
				if(quantiteAcheter == 0) {
					System.out.println(nomAcheteur + " veut acheter " + quantite + " " + produit + ", malheureusement il n'y en a plus !\n");
				} else if(quantiteAcheter == quantite) {
					System.out.println(nomAcheteur + " achète " + quantiteAcheter + " " + produit + " à " + vendeur.getNom() + "\n");
				} else {
					System.out.println(nomAcheteur + " veut acheter " + quantite + " " + produit + ", malheureusement " + vendeur.getNom() + " n'en a plus que " + quantiteAcheter + ". " + nomAcheteur + " achète tout le stock de Bonemine.\ns");
				}
			}
		}
	}
}
