package domaine;

import java.time.Instant;
import java.util.Comparator;

import domaine.Mail.Statut;

/**
 * Comparateur de mails
 * 
 * Comme on désire afficher les mails les plus importants en premier, l'element le plus grand retourne une valeur négative
 *
 */
public class MailComparator implements Comparator<Mail> {

	//Classement des mails
	private static final int SECOND_MAIL_AVANT = 1;
	private static final int PREMIER_MAIL_AVANT = -1;
	private static final int MAILS_EGAUX = 0;

	
	/**
	 * Compare deux mails
	 * 
	 * @param premierMail : le premier mail
	 * @param secondMail : le second mail
	 * @return 0 si les mails sont égaux, -1 si le premier mail vient avant le second, 1 dans l'autre cas
	 */
	public int compare(Mail premierMail, Mail secondMail) {
		
		int resultatComparaison;
		
		if (premierMail == null || secondMail == null) {
			resultatComparaison = MAILS_EGAUX;
			return resultatComparaison;
		}
		boolean importancePremierMail = premierMail.isImportant();
		boolean importanceSecondMail = secondMail.isImportant();
		
		if (importancePremierMail != importanceSecondMail) {
			if (importancePremierMail && !importanceSecondMail) {
				resultatComparaison = PREMIER_MAIL_AVANT;
				return resultatComparaison;
			} else {
				resultatComparaison = SECOND_MAIL_AVANT;
				return resultatComparaison;
			}
		}
		
		Statut statutPremierMail = premierMail.getStatut();
		Statut statutSecondMail = secondMail.getStatut();
		
		if (statutPremierMail != statutSecondMail) {
			
			int ordinalStatutPremierMail = statutPremierMail.ordinal();
			int ordinalStatutSecondMail = statutSecondMail.ordinal();
			
			resultatComparaison = ordinalStatutPremierMail
					- ordinalStatutSecondMail;
			return resultatComparaison > MAILS_EGAUX ? PREMIER_MAIL_AVANT : SECOND_MAIL_AVANT;
		}
		
		String sujetPremierMail = premierMail.getSujet();
		String sujetSecondMail = secondMail.getSujet();
		
		if (sujetPremierMail != sujetSecondMail) {
			int comparaisonSujetsSecondMailContrePremierMail = sujetSecondMail.compareTo(sujetPremierMail);
			return comparaisonSujetsSecondMailContrePremierMail;
		}
		
		Instant dateSecondMail = secondMail.getDate();
		Instant datePremierMail = premierMail.getDate();
		
		int comparaisonDatesSecondMailContrePremierMail = dateSecondMail.compareTo(datePremierMail);
		return comparaisonDatesSecondMailContrePremierMail;
	}
	

}
