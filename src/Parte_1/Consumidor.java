package Parte_1;

public class Consumidor implements Runnable { 
	BufferLimitadoMonitor b = null;
	
	public Consumidor( BufferLimitadoMonitor initb ) {
		b = initb; 
		new Thread( this ).start(); 
	}
	
	public void run() { 
		double item; 
		while( true ) { 
			item = b.fetch(); 
			System.out.println( "Artículo extraído " + item ); 
			//primera captura debe tener 200
			Util.mySleep(40); 
		} 
	}
}