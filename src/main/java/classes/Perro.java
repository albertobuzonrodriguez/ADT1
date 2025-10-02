package classes;

public class Perro {
    public int id;
	public String raza;
	public int tamanyo;
	public int edad;
	public String color;

	public Perro (int id, String raza, int tamanyo, int edad, String color){
		this.id = id;
		this.raza = raza;
		this.tamanyo = tamanyo;
		this.edad = edad;
		this.color = color;
	}

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public int getTamanyo() {
		return tamanyo;
	}

	public void setTamanyo(int tamanyo) {
		this.tamanyo = tamanyo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
