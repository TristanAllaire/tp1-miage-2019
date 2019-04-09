package presentation;

public class ClientMail {

	private static final int NB_ARGS_DEMANDE = 2;

	public static void main(String[] args) {
		if(args.length<NB_ARGS_DEMANDE) {
			System.out.println("Veuillez renseignez une valeur pour le booléen de production puis le sujet du mail");
		} else new InterpreteurLigneCommande().nouveauMail(args);
	}

}
