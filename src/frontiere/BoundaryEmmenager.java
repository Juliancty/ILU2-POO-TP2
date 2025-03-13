package frontiere;

import controleur.ControlEmmenager;

public class BoundaryEmmenager {
	private ControlEmmenager controlEmmenager;

	public BoundaryEmmenager(ControlEmmenager controlEmmenager) {
		this.controlEmmenager = controlEmmenager;
	}

	public void emmenager(String nomVisiteur) {
		if (controlEmmenager.isHabitant(nomVisiteur)) {
			System.out.println(
					"Mais vous êtes déjà un habitant du village !");
		} else {
			StringBuilder question = new StringBuilder();
			question.append("Êtes-vous :\n");
			question.append("1 - un druide.\n");
			question.append("2 - un gaulois.\n");
			int choixUtilisateur = -1;
			do {
				choixUtilisateur = Clavier.entrerEntier(question.toString());
				switch (choixUtilisateur) {
				case 1:
					emmenagerDruide(nomVisiteur);
					break;

				case 2:
					StringBuilder b = new StringBuilder();
					b.append("Bienvenue villageois " + nomVisiteur + "\n");
					b.append("Quelle est votre force ?\n");
					int forceVisiteur = Clavier.entrerEntier(b.toString());
					controlEmmenager.ajouterGaulois(nomVisiteur, forceVisiteur);
					break;

				default:
					System.out
							.println("Vous devez choisir le chiffre 1 ou 2 !");
					break;
				}
			} while (choixUtilisateur != 1 && choixUtilisateur != 2);
		}
	}

	private void emmenagerDruide(String nomVisiteur) {
		StringBuilder b = new StringBuilder();
		b.append("Bienvenue druide " + nomVisiteur + "\n");
		b.append("Quelle est votre force ?\n");
		int forceVisiteur = Clavier.entrerEntier(b.toString());
		int effetPotionMin,effetPotionMax;
		do {
			StringBuilder qpmin = new StringBuilder();
			qpmin.append("Quelle est la force de potion la plus faible que vous produisez ?\n");
			effetPotionMin = Clavier.entrerEntier(qpmin.toString());
			StringBuilder qpmax = new StringBuilder();
			qpmax.append("Quelle est la force de potion la plus forte que vous produisez ?\n");
			effetPotionMax = Clavier.entrerEntier(qpmax.toString());
			if(effetPotionMax < effetPotionMin) {
				System.out.println("Attention Druide, vous vous �tes tromp� entre le minimum et le maximum\n");
			}
		} while(effetPotionMax < effetPotionMin);
		controlEmmenager.ajouterDruide(nomVisiteur, forceVisiteur, effetPotionMin, effetPotionMax);
	}
}
