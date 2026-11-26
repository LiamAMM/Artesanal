package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class TestServirCervezaAI {

	// Tolerancia (delta) para comparaciones entre valores double
	private static final double DELTA = 0.0001;

	@Test
	void deberiaServirCervezaCuandoHayCantidadSuficienteUsandoConstructorConCapacidad() {
		// Valida que, usando el constructor de 5 parámetros (incluyendo código), si hay suficiente cerveza disponible:
		// 1) se descuenta la cantidad servida de la cantidad actual,
		// 2) se retorna el valor a pagar (cantidad * precioPorMl), y
		// 3) se conserva el código asignado.
		Maquina maquina = new Maquina("M1", "IPA Artesanal", "Cerveza rubia lupulada", 0.02, 5000.0);
		maquina.recargarCerveza(1000.0);

		double totalPagar = maquina.servirCerveza(350.0);

		assertEquals(350.0 * 0.02, totalPagar, DELTA);
		assertEquals(650.0, maquina.getCantidadActual(), DELTA);
		assertEquals("M1", maquina.getCodigo());
	}

	@Test
	void deberiaServirCervezaCuandoHayCantidadSuficienteUsandoConstructorPorDefecto() {
		// Valida que, usando el constructor de 4 parámetros (con código y capacidad por defecto de 10000),
		// llenarMaquina() deja 9800.0 ml (capacidadMaxima - 200) y al servir 500.0 ml quedan 9300.0 ml.
		Maquina maquina = new Maquina("M2", "Stout Negra", "Cerveza oscura con notas a café", 0.03);
		maquina.llenarMaquina(); // Deja 9800.0 ml disponibles

		double totalPagar = maquina.servirCerveza(500.0);

		assertEquals(500.0 * 0.03, totalPagar, DELTA);
		assertEquals(9300.0, maquina.getCantidadActual(), DELTA);
		assertEquals("M2", maquina.getCodigo());
	}

	@Test
	void deberiaServirTodaLaCantidadDisponibleCuandoSolicitaExactamenteLoQueHay() {
		// Valida el caso límite donde la cantidad solicitada es exactamente igual
		// a la cantidad actual disponible en la máquina, dejando la cantidad actual en 0.
		Maquina maquina = new Maquina("M3", "Amber Ale", "Cerveza ámbar balanceada", 0.025, 2000.0);
		maquina.recargarCerveza(750.0);

		double totalPagar = maquina.servirCerveza(750.0);

		assertEquals(750.0 * 0.025, totalPagar, DELTA);
		assertEquals(0.0, maquina.getCantidadActual(), DELTA);
		assertEquals("M3", maquina.getCodigo());
	}

	@Test
	void noDeberiaServirCuandoLaMaquinaEstaVacia() {
		// Valida que si la máquina no tiene cerveza (cantidadActual = 0), no sirve nada,
		// mantiene la cantidad actual en 0 y retorna 0.
		Maquina maquina = new Maquina("M4", "Pilsen Clásica", "Cerveza ligera y refrescante", 0.015, 4000.0);

		double totalPagar = maquina.servirCerveza(250.0);

		assertEquals(0.0, totalPagar, DELTA);
		assertEquals(0.0, maquina.getCantidadActual(), DELTA);
		assertEquals("M4", maquina.getCodigo());
	}

	@Test
	void noDeberiaServirCuandoLaCantidadSolicitadaSuperaLoDisponible() {
		// Valida que si se pide más mililitros de los que hay disponibles,
		// no se sirve nada, no se modifica la cantidad actual y se retorna 0.
		Maquina maquina = new Maquina("M5", "Porter", "Cerveza maltosa", 0.04);
		maquina.recargarCerveza(400.0);

		double totalPagar = maquina.servirCerveza(400.1);

		assertEquals(0.0, totalPagar, DELTA);
		assertEquals(400.0, maquina.getCantidadActual(), DELTA);
		assertEquals("M5", maquina.getCodigo());
	}

	@Test
	void deberiaServirCeroCuandoSeSolicitanCeroMililitros() {
		// Valida que al solicitar 0 ml (habiendo cerveza disponible), se retorna 0 a pagar
		// y la cantidad actual permanece sin cambios.
		Maquina maquina = new Maquina("M6", "Golden Ale", "Cerveza suave", 0.02, 3000.0);
		maquina.recargarCerveza(1200.0);

		double totalPagar = maquina.servirCerveza(0.0);

		assertEquals(0.0, totalPagar, DELTA);
		assertEquals(1200.0, maquina.getCantidadActual(), DELTA);
		assertEquals("M6", maquina.getCodigo());
	}

	@Test
	void deberiaReflejarCambioDePrecioPorMlEnElCalculoDelPago() {
		// Valida que el valor a pagar utiliza el precioPorMl vigente (incluyendo cambios vía setter)
		// multiplicado por la cantidad servida.
		Maquina maquina = new Maquina("M7", "Weissbier", "Cerveza de trigo", 0.02);
		maquina.recargarCerveza(1000.0);
		maquina.setPrecioPorMl(0.05);

		double totalPagar = maquina.servirCerveza(200.0);

		assertEquals(200.0 * 0.05, totalPagar, DELTA);
		assertEquals(800.0, maquina.getCantidadActual(), DELTA);
		assertEquals("M7", maquina.getCodigo());
	}
}