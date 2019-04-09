package com.acme.mailreader.domain;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.domain.MailComparator;
import com.acme.mailreader.domain.Mail.Statut;

public class MailComparatorTest {
	
	private MailComparator comparator;

	@Before
	public void setUp() throws Exception {
		this.comparator = new MailComparator();
	}

	@Test
	public final void egauxSiDeuxMailsNuls() {
		Mail mail1 = null;
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	@Test
	public final void egauxSiUnDesMailsNuls() {
		Mail mail1 = new Mail();
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	@Test 
	public final void egauxSiMailsEgaux() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("uyyuy").important(true).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(true).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(0));	
	}	
	
	@Test
	public final void premierPlusPetitSiDateNulle() throws DateIncorrecteException  {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));
				
	}
	
	@Test 
	public final void secondPlusPetitSiDateNulle() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).build();
		assertThat(comparator.compare(mail1, mail2),is(-1));	
	}
	
	@Test 
	public final void premierPlusPetitSiSujetNul() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder(null).important(false).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));	
	}
	
	@Test 
	public final void secondPlusPetitSiSujetNul() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder(null).important(false).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(-1));	
	}
	
	@Test 
	public final void premierPlusPetitSiImportanceFalse() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(true).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));	
	}	
	
	@Test 
	public final void secondPlusPetitSiImportanceFalse() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("uyyuy").important(true).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(-1));	
	}	
	
	@Test 
	public final void premierPlusPetitSiStatutEgaux() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(true).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));	
	}	
	
	@Test 
	public final void premierPlusPetitSiInferieur() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.SENT).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(true).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));	
	}
	@Test 
	
	public final void secondPlusPetitSiInferieur() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(true).statut(Statut.UNSENT).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(-1));	
	}
	
}
