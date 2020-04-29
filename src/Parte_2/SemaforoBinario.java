package Parte_2;

public class SemaforoBinario { 
	boolean value; 

	SemaforoBinario( boolean initValue ) 
	{ 
		value = initValue; 
		//System.out.println("hola"+initValue);
	} 
	
	public synchronized void P() 
	{ 
		while( value == false ) 
			Util.myWait(this); //en cola de procesos bloqueados 
		value = false;
	} 
	
	public synchronized void V() 
	{ 
		value = true;
		notify();
	}
	
	public synchronized void CR()
	{
		System.out.println("ESTOY EN LA ZONA CRITICA: ");
	}

}

