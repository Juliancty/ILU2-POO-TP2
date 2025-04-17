package controleur;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	public boolean verifierIdentite(String nomAcheteur) {
		return village.trouverHabitant(nomAcheteur) != null;
	}
	
	public String[] rechercherVendeursProduit(String produit) {
		Gaulois[] vendeurs = village.rechercherVendeursProduit(produit);
		String[] stringVendeurs = null;
		if(vendeurs != null) {
			stringVendeurs = new String[vendeurs.length];
			for(int i = 0; i < vendeurs.length; i++) {
				stringVendeurs[i] = vendeurs[i].getNom();
			}
		}
		return stringVendeurs;
	}
	
	public int acheterProduit(String nomVendeur, int quantite) {
		if(controlVerifierIdentite.verifierIdentite(nomVendeur)) {
			Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
			return etal.acheterProduit(quantite);
		}
		return 0;
	}
}
