package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestRecargar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean resultado;
		Maquina rubia = new Maquina("M1", "Pilsener", "Cerveza FrIa", 0.02, 8000);
		
		System.out.println("-------ESTADO INICIAL-------");
		rubia.imprimir();
		
		System.out.println("-------RECARGANDO 1-------");
		
		resultado = rubia.recargarCerveza(3000);
		
		System.out.println("¿Se recargo correctamente? " + resultado);
		rubia.imprimir();
		
		System.out.println("-------RECARGANDO 2-------");
		
		resultado = rubia.recargarCerveza(2000);
		
		System.out.println("¿Se recargo correctamente? " + resultado);
		rubia.imprimir();
		
		System.out.println("-------RECARGANDO 3-------");
		
		resultado = rubia.recargarCerveza(2900);
		
		System.out.println("¿Se recargo correctamente? " + resultado);
		rubia.imprimir();
		
		
	}

}
