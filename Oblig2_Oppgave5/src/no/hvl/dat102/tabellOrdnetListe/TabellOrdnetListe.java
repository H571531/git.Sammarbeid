package no.hvl.dat102.tabellOrdnetListe;

import no.hvl.dat102.adt.OrdnetListeADT;

public class TabellOrdnetListe<T extends Comparable<T>> implements OrdnetListeADT<T> {
	
	private final static int STDK = 100;
	private final static int IKKE_FUNNET = -1;
	private int bak;
	private T[] liste;

	public TabellOrdnetListe() {
		this(STDK);
	}

	public TabellOrdnetListe(int startKapasitet) {
		bak = 0;
		liste = (T[]) (new Comparable[startKapasitet]);
	}

	@Override
	public T fjernSiste() {
		T resultat = null;
		if(!erTom()) {
			resultat = liste[bak-1];
			liste[bak] = null;
			bak--;
		}
		return resultat;
	}

	@Override
	public T fjernFoerste() {
		T resultat = null;
		if(!erTom()) {
			resultat = liste[0];
			bak--;
			for(int i = 0; i < bak; i++) {
				liste[i] = liste[i+1];
			}
 		}
		return resultat;
	}

     @Override
	public T foerste() {
		T resultat = null;
		if (!erTom()){
			resultat = liste[0];
		}
		return resultat;
	}

	@Override
	public T siste() {
		T resultat = null;
		if(!erTom()) {
			resultat = liste[bak];
		}
	return resultat;
	}

	@Override
	public boolean erTom() {
		return (bak == 0);
	}

	@Override
	public int antall() {
		return bak;
	}
	
    @Override
	public void leggTil(T element) {
    	if(bak == liste.length) {
    		utvid();
    	}
    	int scan = 0;
    	while(scan < bak && element.compareTo(liste[scan]) > 0) {
    		scan++;
    	}
    	for(int j = bak; j > scan; j--) {
    		liste[j] = liste[j-1];
    	}
    	liste[scan] = element;
    	bak++;
	}

	
	@Override
	public boolean inneholder(T element) {
		return (finn(element) != IKKE_FUNNET);
	}

	@Override
	public T fjern(T element) {
		int indeks = finn(element);
		T svar = null;
		if(indeks != -1) {
			svar = liste[indeks];
			for(int i = indeks; i < bak-1; i++) {
				liste[i] = liste[i+1];
			}
			liste[bak] = null;
			bak--;
		}
		return svar;
	}		

	private int finn(T el) {
		int i = 0, resultat = IKKE_FUNNET;
		if(!erTom()) {
			while(i < bak && el.compareTo(liste[i]) > 0) {
				i++;
			}
			if(i < bak && liste[i].equals(el)) {
				resultat = i;
			}
		}
		return resultat;
	}

	public String toString() {
		String resultat = "";

		for (int i = 0; i < bak; i++) {
			resultat = resultat + liste[i].toString() + "\n";
		}
		return resultat;
	}


	private void utvid() {
		T[] hjelpeTabell = (T[]) (new Comparable[liste.length * 2]);

		for (int i = 0; i < liste.length; i++){
			hjelpeTabell[i] = liste[i];
		}
		
		liste = hjelpeTabell;
	}

}// class
