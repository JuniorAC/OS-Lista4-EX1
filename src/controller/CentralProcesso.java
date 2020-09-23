package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CentralProcesso extends Thread {

	private int ID;
	Random tempo = new Random();
	private Semaphore semaforo;

	public CentralProcesso(int ID, Semaphore semaforo) {

		this.ID = ID;
		this.semaforo = semaforo;
	}

	public void run() {
		if (ID % 3 == 1) {
			calculo(ID, 2, tempo.nextInt(800) + 200, 1000);
			
		} else if (ID % 3 == 2) {
			calculo(ID, 3, tempo.nextInt(1000) + 500, 1500 );

		} else if (ID % 3 == 0) {
			calculo(ID, 3 , tempo.nextInt(1000) + 1000, 1500);

		}
	}

	private void calculo(int ID, int max, int temp, int tempBD) {
		for (int i = 0; i <= max; i++) {
			try {
				
				sleep(temp);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("ID#"+ ID + "  Está realizando Cálculos");
			calculoBD(tempBD, ID);
			
		}

	}

	private void calculoBD(int temp, int ID) {

		try {
			semaforo.acquire();
			System.out.println("ID#"+ ID + "  Está realizando transação no Banco de dados");
			sleep(temp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("ID#"+ ID + "  Finalizou acesso ao Banco de dados");
		semaforo.release();

	}

}
