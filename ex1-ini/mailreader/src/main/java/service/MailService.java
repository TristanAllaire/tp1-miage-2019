package service;
import domaine.Mail;
import infrastructure.*;

public class MailService {
	public MailService() {}
	public void envoyerMail(String sujet, boolean production) {
		Mail unMail = new Mail();
		unMail.setSujet(sujet);
		
		MailSender sender;
		if(production) {
			sender = new MailSendProduction();
		} else {
			sender = new MailSendRecette();
		}
		sender.envoyerMail(unMail);
	}
}
