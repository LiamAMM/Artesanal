package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class TestRecargarJUnit {

	@Test
	public void testRecargaExitosa() {

		Maquina rubia = new Maquina("M1", "Pilsner", "cerveza", 0.02, 8000);

		boolean resultado = rubia.recargarCerveza(3000);

		assertTrue(resultado);
		assertEquals(3000, rubia.getCantidadActual(), 0.0001);
		assertEquals("M1", rubia.getCodigo());
	}

	@Test
	public void testRecargaFallidaPorDesborde() {

		Maquina negra = new Maquina("M2", "Club", "cerveza fria", 0.02, 8000);

		negra.recargarCerveza(7000);

		boolean resultado = negra.recargarCerveza(1000);

		assertFalse(resultado);
		assertEquals(7000, negra.getCantidadActual(), 0.0001);
		assertEquals("M2", negra.getCodigo());
	}
}