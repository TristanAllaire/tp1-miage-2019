package infrastructure;
import domaine.Mail;
import service.MailSender;

public class MailSendRecette implements MailSender{

	@Override
	public void envoyerMail(Mail leMail) {	
		System.out.println("Mail envoyé dans l'environnement de recette.");
	}

}
