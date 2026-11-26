package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestLlenar {

	public static void main(String[] args) {
		
		Maquina rubia = new Maquina("M1", "Pilsener", "Cerveza Froa", 0.02, 8000);
		
		rubia.imprimir();
		
		rubia.llenarMaquina();
		
		rubia.imprimir();
		
		Maquina negra = new Maquina("M2", "Club", "Cerveza Buena", 0.03);
		
		negra.imprimir();
		negra.llenarMaquina();
		negra.imprimir();
		

	}

}
