package no.hvl.dat102;

import no.hvl.dat102.mengde.adt.MengdeADT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
/**
 * Abstrakt klasse for testing av Mengder. Subklassene TabellMengdeTest og KjedetMengdeTest må opprette
 * testobjekter av relevant klasse
 * @author tohov
 *
 */
public abstract class MengdeADTTest {
	
	protected MengdeADT<Integer> mengde;
	protected MengdeADT<Integer> mengde2;
	protected MengdeADT<Integer> test;
	protected MengdeADT<Integer> test2;
	
	protected Integer e0 = 1;
	protected Integer e1 = 2;
	protected Integer e2 = 3;
	protected Integer e3 = 4;
	protected Integer e4 = 5;
	protected Integer e5 = 6;
	protected Integer e6 = 7;
	protected Integer e7 = 8;

	//Abstrakt setup som må defineres i subklassene
	@Before
	public abstract void setup();
	
	/**
	 * Tester union av to mengder hvor ingen elementer er felles
	 */
	@Test
	public final void testUnionIngenFellesElementer() {
		mengde.leggTil(e0);
		mengde.leggTil(e1);
		mengde.leggTil(e2);
		
		mengde2.leggTil(e3);
		mengde2.leggTil(e4);
		mengde2.leggTil(e5);
		
		test.leggTil(e0);
		test.leggTil(e1);
		test.leggTil(e2);
		test.leggTil(e3);
		test.leggTil(e4);
		test.leggTil(e5);
		
		MengdeADT<Integer> union = mengde.union(mengde2);
		assertEquals(6,union.antall());
		assertTrue(test.equals(union));
		
	}
	
	/**
	 * Tester union av to mengder med noen overlappende elementer
	 */
	@Test
	public final void testUnionFellesElementer() {
		mengde.leggTil(e0);
		mengde.leggTil(e1);
		mengde.leggTil(e2);
		
		mengde2.leggTil(e1);
		mengde2.leggTil(e2);
		mengde2.leggTil(e3);
		
		test.leggTil(e0);
		test.leggTil(e1);
		test.leggTil(e2);
		test.leggTil(e3);
		
		MengdeADT<Integer> union = mengde.union(mengde2);
		assertEquals(4, union.antall());
		assertTrue(test.equals(union));
	}
	
	@Test
	public final void testEquals() {
		mengde.leggTil(e0);
		mengde.leggTil(e1);
		mengde.leggTil(e2);
		
		mengde2.leggTil(e0);
		mengde2.leggTil(e1);
		mengde2.leggTil(e2);
		
		test.leggTil(e3);
		test.leggTil(e4);
		test.leggTil(e5);
		
		assertTrue(mengde.equals(mengde2));
		assertFalse(mengde.equals(test));
		
	}
	
	@Test
	public final void testSnittIngenFellesElementer() {
		mengde.leggTil(e0);
		mengde.leggTil(e1);
		mengde.leggTil(e2);
		
		mengde2.leggTil(e3);
		mengde2.leggTil(e4);
		mengde2.leggTil(e5);
		
		MengdeADT<Integer> snitt = mengde.snitt(mengde2);
		
		
		assertEquals(0, snitt.antall());
		assertFalse(snitt.equals(mengde));
		assertFalse(snitt.equals(mengde2));
		
		assertTrue(snitt.undermengde(test));
		assertTrue(snitt.undermengde(test));
		
		
		
	}
	
	public final void testSnittNoenFellesElementer() {
		mengde.leggTil(e0);
		mengde.leggTil(e1);
		mengde.leggTil(e2);
		
		mengde2.leggTil(e1);
		mengde2.leggTil(e2);
		mengde2.leggTil(e3);
		
		MengdeADT<Integer> snitt = mengde.snitt(mengde2);
		
		
		assertEquals(2, snitt.antall());
		
		
		test.leggTil(e1);
		test.leggTil(e2);
		
		assertTrue(test.equals(snitt));
		assertTrue(mengde.undermengde(snitt));
		assertTrue(mengde2.undermengde(snitt));
	}
	
	@Test
	public final void testDifferensIngenFellesElementer() {
		mengde.leggTil(e0);
		mengde.leggTil(e1);
		mengde.leggTil(e2);
		
		mengde2.leggTil(e3);
		mengde2.leggTil(e4);
		mengde2.leggTil(e5);
		
		MengdeADT<Integer> differens = mengde.differens(mengde2);
		
		assertTrue(mengde.equals(differens));
		assertTrue(mengde.undermengde(differens));
		assertFalse(mengde2.undermengde(differens));
		
		test.leggTil(e0);
		test.leggTil(e1);
		test.leggTil(e2);
		
		assertTrue(test.equals(differens));
		
	}
	
	@Test
	public final void testDifferensNoenFellesElementer() {
		mengde.leggTil(e0);
		mengde.leggTil(e1);
		mengde.leggTil(e2);
		
		mengde2.leggTil(e1);
		mengde2.leggTil(e2);
		mengde2.leggTil(e3);
		
		MengdeADT<Integer> differens = mengde.differens(mengde2);
		
		assertTrue(mengde.undermengde(differens));
		assertFalse(mengde2.undermengde(differens));
		
		test.leggTil(e0);
		
		assertTrue(test.equals(differens));
	}
	
	@Test
	public final void testUnderMengde() {
		mengde.leggTil(e0);
		mengde.leggTil(e1);
		mengde.leggTil(e2);
		
		assertTrue(mengde.undermengde(test));
		
		test.leggTil(e0);
		
		assertTrue(mengde.undermengde(test));
		
		test.leggTil(e1);
		
		assertTrue(mengde.undermengde(test));
		
		test.leggTil(e3);
		assertFalse(mengde.undermengde(test));
	}
	
	
}
