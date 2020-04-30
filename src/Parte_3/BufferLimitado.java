package Parte_3;


public class BufferLimitado
{
	final int size			= 5;
	
	SemaforoBinario mutex	= new SemaforoBinario(true);
	SemaforoContador isFull = new SemaforoContador( 5 );
	SemaforoBinario sinc[]	= {
								new SemaforoBinario(false),
								new SemaforoBinario(false),
								new SemaforoBinario(false),
								new SemaforoBinario(false),
								new SemaforoBinario(false),
								new SemaforoBinario(false)
							  };

	String estadof[]		= { 
								"PENSAR", "PENSAR", "PENSAR",
							    "PENSAR", "PENSAR"
							  };
	
	public synchronized  void bajar_tenedores( int i ) 
	{
		isFull.P(); // espera si el buffer está lleno
		mutex.P();
		if(i-1>=0)
			probar_bocado(4);
		else
			probar_bocado(i-1);
		if(i+1<=4){
			probar_bocado(1);
		}
		else
			probar_bocado(i+1);
		estadof[i]="PENSAR";
		mutex.V();
		isFull.V(); // notifica a cualquier productor en espera
	}
	
	public synchronized  void tomar_tenedores( int i ) // INGRESO
	{
		isFull.P(); // espera si el buffer está lleno
		mutex.P(); // asegura la exclusión mutua
		if(i-1<=0){
			while(estadof[4]=="COMER"
			        ||estadof[i+1]=="COMER")
			    {//Condición de sincronización
			      Util.myWait( this );
			    }
		}
		if(i-1>=4){
			while(estadof[i-1]=="COMER"
			        ||estadof[0]=="COMER")
			    {//Condición de sincronización
			      Util.myWait( this );
			    }
		}
		if(i<0&&i<4){
			if(i-1<=0){
				while(estadof[4]=="COMER"||estadof[i+1]=="COMER")
				    {//Condición de sincronización
				      Util.myWait( this );
				    }
			}
		}
		estadof[i] = "HAMBRE";
		probar_bocado(i);
		mutex.V();
		isFull.V(); // notifica a cualquier productor en espera
		//sinc[i].P();
		return;
	}
	
	public void probar_bocado(int i){
		/* Permitir comer al filosofo si esta hambriento */
		if(i-1<=0){
			probar_bocado(4);
			if(estadof[4]=="HAMBRE"
					&& estadof[4] != "COMER"
					&& estadof[1] != "COMER"){
				estadof[i] = "COMER";
				//sinc[i].V();
				//System.out.println("Bocado . . .\n . ");
			}
		}
		else {
			if(i+1>4){
				if(estadof[4]=="HAMBRE"
						&& estadof[3] != "COMER"
						&& estadof[0] != "COMER"){
					estadof[i] = "COMER";
					//sinc[i].V();
					//System.out.println("Bocado . . .\n . ");
				}
			}
		}
		if(i<4&&i>0){
			if(estadof[i]=="HAMBRE"
					&& estadof[i-1] != "COMER"
					&& estadof[i+1] != "COMER"){
				estadof[i] = "COMER";
				//sinc[i].V();
				//System.out.println("Bocado . . .\n . ");
			}
		}
		return;
	}
}

	
	