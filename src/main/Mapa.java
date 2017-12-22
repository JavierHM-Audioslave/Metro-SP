package main;

import java.io.File;
import java.util.Scanner;

public class Mapa {

	//private Integer cantNodos;
	//private Integer cantAristas;
	//private Tunel[] tuneles;
	private Puente[] puentes;
	private Grafo grafo;
	private Integer cantPuentes;

	public Mapa(File archEntr)
	{
		File archIn = archEntr;
		Scanner sc;
		String[] aux = null;
		Integer cantNodos=null;
		Integer cantAristas=null;
		try
		{
			sc = new Scanner(archIn);
			cantNodos = sc.nextInt();
			Integer cantTuneles = sc.nextInt();
			//tuneles = new Tunel[cantTuneles];
			cantPuentes = sc.nextInt();
			cantAristas = (cantTuneles+cantPuentes)*2;
			puentes = new Puente[cantPuentes];
			aux = new String[cantTuneles+cantPuentes];
			for(int i = 0; i<cantTuneles; i++)
			{
				//tuneles[i] = new Tunel(String.valueOf(sc.nextInt()), String.valueOf(sc.nextInt()));
				aux[i] = sc.nextInt()+" "+sc.nextInt()+" 1";
			}
			for(int i = cantTuneles; i<cantPuentes+cantTuneles; i++)
			{
				//puentes[i] = new Puente(String.valueOf(sc.nextInt()), String.valueOf(sc.nextInt()));
				String inicio = String.valueOf(sc.nextInt());
				String fin = String.valueOf(sc.nextInt());
				puentes[i-cantTuneles] = new Puente(inicio,fin);
				
				aux[i] = inicio+" "+fin+" 2";				
			}
			
			try
			{
				sc.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		grafo = new Grafo(cantNodos, cantAristas);
		grafo.cargarGrafoNoDirigido(aux);
	}
	
	
	public void resolver()
	{
		Prim prim = new Prim(grafo);
		String[] dev = prim.resolver();
		Integer tunelesTotales = 0;
		for(int i = 0; i<dev.length-1; i++)
		{
			String inicio = dev[i].substring(0, 1);
			String fin = dev[i].substring(2, 3);			
			
			for(int j = 0; j<cantPuentes; j++)
			{
				if(puentes[j].esPuente(inicio, fin))
				{
					tunelesTotales++;
					break;
				}
			}
		}
		
		System.out.println(tunelesTotales);
	}

}
