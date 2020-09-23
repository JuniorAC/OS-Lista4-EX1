package view;

import java.util.concurrent.Semaphore;

import controller.CentralProcesso;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore (1);
		
		for(int i = 1; i<=21; i++) {
		CentralProcesso Processo = new CentralProcesso (i, semaforo);
		Processo.start();
			

		}
		
	}

}
