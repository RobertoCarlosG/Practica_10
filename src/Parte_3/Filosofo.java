package Parte_3;

public class Filosofo implements Runnable {

	BufferLimitado b = null;
	int consumidor;
	
	Filosofo(BufferLimitado init , int n_consumidor){
		b			=	init;
		this.consumidor	= n_consumidor;
		new Thread (this) .start();
		
	}


	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("Filosofo "+consumidor+" pensando");
			b.tomar_tenedores(consumidor);
			Util.mySleep(2000);
			System.out.println("Filosofo "+consumidor+" comiendo con tenedor "+consumidor+" y tenedor"+ (consumidor+1)%5);
			b.bajar_tenedores(consumidor);
			System.out.println("Filósofo "+consumidor+" bajando tenedor "+consumidor+" tenedor "+((consumidor+1)%5));
		}
	}
	
}
