package Parte_1;

public class BufferLimitadoMonitor {

	final int sizeBuf = 10; 
	double buffer[]	= new double[sizeBuf]; 
	int inBuf		= 0, outBuf = 0, count = 0; 
	
	public synchronized void deposit( double value ) 
	{ 
		while( count == sizeBuf ) 
			// buffer lleno 
			Util.myWait( this ); 
		buffer[inBuf]	= value; 
		inBuf 			= ( inBuf + 1 ) % sizeBuf; 
		count++; 
		if( count == 1 ) // items disponibles para extraer 
			notify(); 
	} 
	
	public synchronized double fetch( ) 
	{ 
		double value; 
		while( count == 0 ) 
			// buffer vacío 
			Util.myWait( this ); 
		value = buffer[outBuf]; 
		outBuf = ( outBuf + 1 ) % sizeBuf; 
		count--; 
		if( count == sizeBuf - 1 ) 
			// slots vacíos disponibles 
			notify(); 
		return value; 
	}
}
