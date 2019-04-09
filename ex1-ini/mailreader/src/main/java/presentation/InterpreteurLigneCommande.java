package presentation;

import service.MailService;

public class InterpreteurLigneCommande {
	public InterpreteurLigneCommande() {
		
	}
	public void nouveauMail(String[] args) {
		boolean production = Boolean.parseBoolean(args[0]); //True: le mail doit être envoyé, False: nous sommes dans l'environnement de recette
		String sujetMail = args[1];
		
		MailService service = new MailService();
		
		service.envoyerMail(sujetMail, production);
	}
}
