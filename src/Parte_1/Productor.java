package Parte_1;

import java.util.Random;
 
public class Productor implements Runnable { 
		BufferLimitadoMonitor b = null; 
		public Productor( BufferLimitadoMonitor initb ) {
			b = initb; 
			new Thread( this ).start(); 
		}
		
		public void run() { 
			double item; 
			Random r = new Random(); 
			while( true ) { 
			item = r.nextDouble(); 
			System.out.println( "Artículo producido " + item ); 
			b.deposit( item ); 
			//primera captura debe tener 200
			Util.mySleep(2000); } 
		}
	}
