package tdp;

public class Persona {
	private String name;
	private int dni;
	
	public Persona(String name, int dni) {
		this.name = name;
		this.dni = dni;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getDNI() {
		return this.dni;
	}
	
	public void setDNI(int dni) {
		this.dni = dni;

	}
}
