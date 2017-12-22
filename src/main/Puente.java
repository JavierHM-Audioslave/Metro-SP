package main;

public class Puente {
	
	private String inicio;
	private String fin;
	private Integer costo;
	
	public Puente(String inicio, String fin)
	{
		this.inicio = inicio;
		this.fin = fin;
		costo = 1;
	}
	
	public boolean esPuente(String inicio, String fin)
	{
		if(this.inicio.equals(inicio) && this.fin.equals(fin))
		{
			return true;
		}
		return false;
	}

}
