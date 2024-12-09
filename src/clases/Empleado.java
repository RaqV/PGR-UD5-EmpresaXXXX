package clases;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Random;

import utilidades.Utilidades;


public class Empleado implements Comparable <Empleado> {
	
	private String nomEmpleado;
	private String apeEmpleado;
	private String dniEmpleado;
	private LocalDate fecNac;
	private LocalDate fecAlta;
	private int numSorteo;
	
	//Constructores
	public Empleado(String nomEmpleado, String apeEmpleado, String dniEmpleado, LocalDate fecNac, LocalDate fecAlta, int numSorteo) {
		this.nomEmpleado = nomEmpleado;
		this.apeEmpleado = apeEmpleado;
		this.dniEmpleado = dniEmpleado;
		this.fecNac = fecNac;
		this.fecAlta = fecAlta;
		this.numSorteo = numSorteo;
	}

	public Empleado() {
		this.nomEmpleado = "";
		this.apeEmpleado = "";
		this.dniEmpleado = "";
		this.fecNac = null;
		this.fecAlta = null;
		this.numSorteo = 0;
	}
	
	//Metodoak
	public String getNomEmpleado() {
		return nomEmpleado;
	}
	public void setNomEmpleado(String nomEmpleado) {
		this.nomEmpleado = nomEmpleado;
	}
	
	public String getApeEmpleado() {
		return apeEmpleado;
	}
	public void setApeEmpleado(String apeEmpleado) {
		this.apeEmpleado = apeEmpleado;
	}
	public String getDniEmpleado() {
		return dniEmpleado;
	}
	public void setDniEmpleado(String dniEmpleado) {
		this.dniEmpleado = dniEmpleado;
	}
	public LocalDate getFecNac() {
		return fecNac;
	}
	public void setFecNac(LocalDate fecNac) {
		this.fecNac = fecNac;
	}
	public LocalDate getFecAlta() {
		return fecAlta;
	}
	public void setFecAlta(LocalDate fecAlta) {
		this.fecAlta = fecAlta;
	}
	public int getNumSorteo() {
		return numSorteo;
	}
	public void setNumSorteo(int numSorteo) {
		this.numSorteo = numSorteo;
	}
	
	public void setEmpleado() {
		int op;

		this.nomEmpleado = Utilidades.introducirCadena("Introduce el nombre del empleado ");
		this.apeEmpleado = Utilidades.introducirCadena("Introduce el apellido del empleado ");
		this.dniEmpleado = Utilidades.introducirCadena("Introduce el DNI del empleado ");		
		this.fecNac = Utilidades.pidoFechaDMA("Introduce la fecha de nacimiento del empleado");
		System.out.println("Introduce la fecha de alta del empleado en la empresa:");
		System.out.println("\t1. Fecha de hoy");
		System.out.println("\t2. Introducir una fecha");
		op=Utilidades.leerInt(1, 2, "Elige una opción: ");
		if(op==1) {
			this.fecAlta = LocalDate.now();
		}else {
			this.fecAlta = Utilidades.pidoFechaDMA("Introduce la fecha de alta del empleado");
		}
		this.numSorteo = valorAleatorio(1, 100);
	}
	
	public void setEmpleado(String dni) {
		int op;

		this.nomEmpleado = Utilidades.introducirCadena("Introduce el nombre del empleado ");
		this.apeEmpleado = Utilidades.introducirCadena("Introduce el apellido del empleado ");
		this.dniEmpleado = dni;
		this.fecNac = Utilidades.pidoFechaDMA("Introduce la fecha de nacimiento del empleado");
		System.out.println("Introduce la fecha de alta del empleado en la empresda:");
		System.out.println("\t1. Fecha de hoy");
		System.out.println("\t2. Introducir una fecha");
		op=Utilidades.leerInt(1, 2, "Elige una opción: ");
		if(op==1) {
			this.fecAlta = LocalDate.now();
		}else {
			this.fecAlta = Utilidades.pidoFechaDMA("Introduce la fecha de alta del empleado");
		}
		this.numSorteo = valorAleatorio(1, 100);
	}
	
	private int valorAleatorio(int inf, int sup){
		Random rAleatorio= new Random();
		int iAleatorio=inf + Math.abs(rAleatorio.nextInt()%(sup-inf+1));
		return iAleatorio;
	}

	
	public void getEmpleado() {
		System.out.println("El nombre de la empleada es: " + nomEmpleado);
		System.out.println("El apellido de la empleada es: " + apeEmpleado);
		System.out.println("El dni de la empleada es: "+ dniEmpleado);
		System.out.println("La fecha de nacimiento: "+ fecNac.getDayOfMonth() +" de "+fecNac.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"))+" de "+ fecNac.getYear());
		System.out.println("La fecha de alta en la empresa: "+ fecAlta.getDayOfMonth() +" de "+fecAlta.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"))+" de "+ fecAlta.getYear());
		System.out.println("El número del sorteo del empleado es: " + numSorteo);
	}
	
	@Override
    public int compareTo(Empleado otroEmpleado) {
       
       return this.apeEmpleado.compareTo(otroEmpleado.getApeEmpleado());
   }
	@Override
	public String toString() {
		return "Empleado [nomEmpleado=" + nomEmpleado + ", apeEmpleado=" + apeEmpleado + ", dniEmpleado=" + dniEmpleado
				+ ", fecNac=" + fecNac + ", fecAlta=" + fecAlta + ", numSorteo=" + numSorteo + "]";
	}

	public void modificarEmpleado(String dni) {
		int op;
		boolean resp;
		
		resp = Utilidades.esBoolean("\nDesea modificar el nombre de la empleada? (S/N)");
		if (resp) {
			this.nomEmpleado = Utilidades.introducirCadena("Introduce el nombre del empleado ");
		}
		resp = Utilidades.esBoolean("\nDesea modificar el apellido de la empleada? (S/N)");
		if (resp) {
			this.apeEmpleado = Utilidades.introducirCadena("Introduce el apellido del empleado ");
		}
		resp = Utilidades.esBoolean("\nDesea modificar el apellido de la empleada? (S/N)");
		if (resp) {
			this.dniEmpleado = dni;
		}
		this.fecNac = Utilidades.pidoFechaDMA("Introduce la fecha de nacimiento del empleado");
		System.out.println("Introduce la fecha de alta del empleado en la empresda:");
		System.out.println("\t1. Fecha de hoy");
		System.out.println("\t2. Introducir una fecha");
		op=Utilidades.leerInt(1, 2, "Elige una opción: ");
		if(op==1) {
			this.fecAlta = LocalDate.now();
		}else {
			this.fecAlta = Utilidades.pidoFechaDMA("Introduce la fecha de alta del empleado");
		}
		this.numSorteo = valorAleatorio(1, 100);
	}
}
