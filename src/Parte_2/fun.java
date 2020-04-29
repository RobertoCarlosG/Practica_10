package Parte_2;

public class fun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferLimitado buffer		=	new BufferLimitado();
		Filosofo filo[] = new Filosofo[5];
		for(int i=1; i<5;i++)
			filo[i]	= new Filosofo(buffer,i);
	}

}
