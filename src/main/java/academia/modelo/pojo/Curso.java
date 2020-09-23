package academia.modelo.pojo;


import javax.validation.constraints.Size;



public class Curso {
	
	
	private int id;
	
	
	@Size(min= 2 , max = 100, message= "Debe contener entre 2 y 100 caracteres")
	private String nombre;
	

	@Size(max = 4)
	private String identificador;
	
	
	private int horas;
	
	private Usuario profesor;
	
	private int numAlumnos;
	
	public Curso() {
		super();
		this.id = 0;
		this.nombre = "";
		this.identificador = "";
		this.horas = 0;
		this.numAlumnos = 0;
		this.profesor = new Usuario();
		
	}

	public int getId() {
		return id;
	}

	public int getNumAlumnos() {
		return numAlumnos;
	}

	public void setNumAlumnos(int numAlumnos) {
		this.numAlumnos = numAlumnos;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public Usuario getProfesor() {
		return profesor;
	}

	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", identificador=" + identificador + ", horas=" + horas
				+ ", profesor=" + profesor + ", numAlumnos=" + numAlumnos + "]";
	}
	
	
	
	
}
