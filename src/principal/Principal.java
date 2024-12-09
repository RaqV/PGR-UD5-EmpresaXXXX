package principal;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Scanner;

import clases.*;
import utilidades.LineaListado;
import utilidades.Utilidades;

public class Principal {

	// Metodo para introducir los datos de los empleados
	public static void sartuDatuak(List<Empleado> emp) {
		boolean masEmpleados;

		do {
			Empleado nuevo = new Empleado();
			nuevo.setEmpleado();
			emp.add(nuevo);
			masEmpleados = Utilidades.esBoolean("�Quieres introducir otro empleado? (S/N)");
		} while (masEmpleados);
	}

	// Datuak bistaratuko ditugu: langileenak zein nagusienak
	public static void bistaratuDatuak(List<Empleado> emp) {

		ListIterator<Empleado> lee = emp.listIterator();
		while (lee.hasNext()) {
			System.out.println("----- EMPLEADO-------");
			lee.next().getEmpleado();
		}

	}

	private static void listarEmpleadosPorApellido(List<Empleado> empleados) {

		System.out.println("-----LISTADO DE EMPLEADOS ORDENADO POR APELLIDO--------");
		Collections.sort(empleados);
		bistaratuDatuak(empleados);
	}

	public static void consultarModificarEmpleado(List<Empleado> emp) {
		boolean encontrado = false;
		boolean resp;

		String dni = Utilidades.introducirCadena("Introduce el DNI del empleado");

		for (Empleado e : emp) {
			if (e.getDniEmpleado().equalsIgnoreCase(dni)) {
				e.getEmpleado();
				resp = Utilidades.esBoolean("\nDesea modificar los datos de la empleada? (S/N)");
				if (resp) {
					e.modificarEmpleado(dni); // Modificamos los datos del empleado
				}
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			System.out.println("No existe ning�n empleado con DNI: " + dni);
		}
	}

	public static void borrarEmpleado(List<Empleado> empleados) {
		boolean encontrado = false;
		boolean resp;

		String dni = Utilidades.introducirCadena("Introduce el DNI del empleado");

		for (int i=0; i<empleados.size(); i++) {
			if (empleados.get(i).getDniEmpleado().equalsIgnoreCase(dni)) {
				empleados.get(i).getEmpleado();
				resp = Utilidades.esBoolean("\nEsta seguro de que quiere borrar la empleada?(S/N)");
				if (resp) {
					empleados.remove(i); // Eliminamos el empleado
				}
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			System.out.println("No existe ning�n empleado con DNI: " + dni);
		}
	}

	private static void zerrendatuLangileakAdinaz(List<Empleado> emp) {

		System.out.println("-----LISTADO DE EMPLEADO ORDENADO POR EDAD--------");
		// Ordenamos el array. Para ello copiamos la informaci�n en un array intermedio,
		// para luego ordenarlo
		ArrayList<Empleado> arrayOrdenado = new ArrayList<>();
		Empleado masJoven;
		for (int i = 0; i < emp.size(); i++) {
			arrayOrdenado.add(emp.get(i));
		}
		// Ordenamos el array intermedio
		for (int i = 0; i < arrayOrdenado.size(); i++) {
			masJoven = arrayOrdenado.get(i);
			for (int j = i + 1; j < arrayOrdenado.size(); j++) {
				if (arrayOrdenado.get(j).getFecNac().isAfter(arrayOrdenado.get(i).getFecNac())) {
					masJoven = arrayOrdenado.get(j);
					arrayOrdenado.set(j, arrayOrdenado.get(i));
					arrayOrdenado.set(i, masJoven);
				}
			}
		}

		// Visualizamos el array ordenado
		for (int i = 0; i < arrayOrdenado.size(); i++) {
			if (i == 0) {
				System.out.print("Este es el empleado m�s joven: ");
			}
			if (i == emp.size() - 1) {
				System.out.print("Este es el empleado m�s mayor: ");
			}
			System.out.println(arrayOrdenado.get(i).toString());
		}
	}

	private static void zerrendatuLangileakAdinAlta(List<Empleado> emp) {

		System.out.println("-----LISTADO DE LA EDAD DE LOS EMPLEADOS AL ENTRAR EN LA EMPRESA --------");

		// Vamos a utilizar el periodo para aplicar los metodos between y getYear
		Period periodo;

		// Visualizamos el array
		for (int i = 0; i < emp.size(); i++) {
			// Primero calculamos la diferencia de a�os y luego obtenemos solo los a�os
			periodo = Period.between(emp.get(i).getFecNac(), emp.get(i).getFecAlta());
			System.out.println("El empleado " + emp.get(i).getNomEmpleado() + " " + emp.get(i).getApeEmpleado()
					+ " tenia " + periodo.getYears() + " cuando entr� en la empresa");
		}
	}

	private static void zerrendatuLangileakEnpresaldia(List<Empleado> emp) {

		System.out.println("-----LISTADO DE LOS EMPLEADOS SEG�N SU ANTIG�EDAD EN LA EMPRESA--------");

		// Ordenamos el array. Para ello copiamos la informaci�n en un array intermedio,
		// para luego ordenarlo
		ArrayList<Empleado> arrayOrdenado = new ArrayList<>();
		Empleado masNuevo;
		for (int i = 0; i < emp.size(); i++) {
			arrayOrdenado.add(emp.get(i));
		}

		// Ordenamos el array intermedio
		for (int i = 0; i < arrayOrdenado.size(); i++) {
			masNuevo = arrayOrdenado.get(i);
			for (int j = i + 1; j < arrayOrdenado.size(); j++) {
				if (arrayOrdenado.get(j).getFecAlta().isAfter(arrayOrdenado.get(i).getFecAlta())) {
					masNuevo = arrayOrdenado.get(j);
					arrayOrdenado.set(j, arrayOrdenado.get(i));
					arrayOrdenado.set(i, masNuevo);
				}
			}
		}

		// Vamos a utilizar el periodo para aplicar los metodos between y getYear
		Period periodo;

		// Visualizamos el array ordenado
		for (int i = 0; i < arrayOrdenado.size(); i++) {
			// Primero calculamos la antiguedad
			periodo = Period.between(arrayOrdenado.get(i).getFecAlta(), LocalDate.now());
			System.out.println("El empleado " + arrayOrdenado.get(i).getNomEmpleado() + " "
					+ arrayOrdenado.get(i).getApeEmpleado() + " tiene una antig�edad de: " + periodo.getYears()
					+ " a�os y " + periodo.getMonths() + " meses");
		}
	}

	private static void egunekoZozketa(List<Empleado> emp) {

		// Variable para encontrar el numero del sorteo
		boolean noencontrado = true;

		// Obtenemos la fecha del d�a
		LocalDateTime hoy = LocalDateTime.now();

		/*
		 * Random aleatorio = new Random(System.currentTimeMillis()); // Producir nuevo
		 * int aleatorio entre 0 y 99 int intAletorio = aleatorio.nextInt(100); // M�s
		 * c�digo
		 * 
		 * // Refrescar datos aleatorios aleatorio.setSeed(System.currentTimeMillis());
		 * // ... o mejor aleatorio.setSeed(aleatorio.getLong());
		 */

		System.out.println("-----VAMOS A PROCEDER AL SORTEO--------");

		while (noencontrado) {
			// Aleatorios para los primeros rand.nextInt(max - min + 1) + min;
			// Generamos un numero aleatorio entre 1 y 100
			int numero = (int) (Math.random() * 100) + 1;
			for (int i = 0; i < emp.size(); i++) {
				if (emp.get(i).getNumSorteo() == numero) {
					noencontrado = false;
					System.out.println("El empleado premiado hoy d�a " + hoy.getDayOfMonth() + " de "
							+ hoy.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")) + " de "
							+ hoy.getYear() + " es " + emp.get(i).getNomEmpleado() + " " + emp.get(i).getApeEmpleado()
							+ " con el n�mero " + numero);
					// System.out.println("El empleado premiado hoy d�a " + hoy.getDayOfMonth() + "
					// de " + hoy.getMonth().toString() + " de " + hoy.getYear() + " es " +
					// emp.get(i).getNomEmpleado() + " " + emp.get(i).getApeEmpleado() + " con el
					// n�mero " + numero);
				}
			}
		}
	}

	private static void galdetegiak(List<Empleado> emp) {

		int numMes = Utilidades.leerInt(1, 12, "Introduce un mes de 1 a 12: ");
		Empleado primero;

		// Creamos un arraylist auxiliar para meter solo los empleados que cumplen en un
		// mes concreto. As� luego podemos ordenarlo
		ArrayList<Empleado> aux = new ArrayList<>();

		for (int i = 0; i < emp.size(); i++) {
			if (emp.get(i).getFecNac().getMonthValue() == numMes) {
				aux.add(emp.get(i));
			}
		}
		// Ordenamos el array auxiliar
		for (int i = 0; i < aux.size(); i++) {
			primero = aux.get(i);
			for (int j = i + 1; j < aux.size(); j++) {
				if (aux.get(j).getFecNac().getDayOfMonth() < aux.get(i).getFecNac().getDayOfMonth()) {
					primero = aux.get(j);
					aux.set(j, aux.get(i));
					aux.set(i, primero);
				}
			}
		}

		// Visualizamos el array ordenado
		System.out.println("-----------------LOS EMPLEADOS QUE CUMPLEN EN ESTE MES DE " + numMes + "-----------------");
		for (int i = 0; i < aux.size(); i++) {
			System.out.println("El empleado " + aux.get(i).getNomEmpleado() + " " + aux.get(i).getApeEmpleado()
					+ " cuple los a�os el: " + aux.get(i).getFecNac().getDayOfMonth() + " de "
					+ aux.get(i).getFecNac().getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")) + " de "
					+ aux.get(i).getFecNac().getYear());
		}
	}

	private static void galdetegiaIzenak(List<Empleado> empleados) {
		//
		boolean encontrado;
		LineaListado lin;
		List<LineaListado> listado = new ArrayList<>();

		if (empleados.isEmpty()) {
			System.out.println("No tenemos ning�n empleado");
		} else {
			// Filtramos la informaci�n en el array para el listado
			for (Empleado emp : empleados) {
				encontrado = false;
				for (LineaListado linea : listado) {
					if (emp.getNomEmpleado().equalsIgnoreCase(linea.getNombre())) {
						encontrado = true;
						linea.setNumero(linea.getNumero() + 1);
					}
				}

				if (!encontrado) {
					lin = new LineaListado(emp.getNomEmpleado(), 1);
					listado.add(lin);
				}
			}

			// Ordenamos por n�mero de veces (mayor a menor)
			for (int i = 0; i < listado.size(); i++) {
				for (int j = i + 1; j < listado.size(); j++) {
					if (listado.get(j).getNumero() > listado.get(i).getNumero()) {
						lin = listado.get(j);
						listado.set(j, listado.get(i));
						listado.set(i, lin);
					}
				}
			}

			System.out.println("*****************LISTADO DE NOMBRES DE EMPLEADOS************************************");
			System.out.println("\n\t\t\t NOMBRE \t\t N�MERO DE PERSONAS");
			// Imprimimos la informaci�n
			for (LineaListado linea : listado) {
				System.out.println("\t\t\t " + linea.getNombre() + "\t\t\t\t\t " + linea.getNumero());
			}
		}
	}

	// Menua
	private static int menu() {
		int resp;

		System.out.println("\n***************************************************************************");
		System.out.println("1. Introducir empleado");
		System.out.println("2. Listado de empleados");
		System.out.println("3. Listado de empleados ordenados por apellido");
		System.out.println("4. Consultar/Modificar datos del empleado a partir del DNI");
		System.out.println("5. Borrado de empleado a partir de DNI");
		System.out.println("6. Listado de la edad de los empleados indicando el m�s joven y el m�s mayor");
		System.out.println("7. Listado de la edad de los empleados en el momento en el que entraron en la empresa");
		System.out.println("8. Listado ordenado en  descendente de la antig�edad de los empleados");
		System.out.println("9. Sorteo Diario");
		System.out.println("10. Estad�sticas");
		System.out.println("11. Salir");
		resp = Utilidades.leerInt(1, 11, "ELIJA UNA OPCI�N");
		return resp;
	}

	private static int subMenu() {
		int resp;

		System.out.println("\n*******************************ESTAD�STICAS*****************************************");
		System.out.println("1. Empleados que cumplen a�os en este mes");
		System.out.println("2. Estad�stica de nombres");

		resp = Utilidades.leerInt(1, 2, "ELIJA UNA OPCI�N");
		return resp;
	}

	public static void main(String[] args) {

		List<Empleado> empleados = new ArrayList<>();

		// Hasierako menua bistaratuko dugu
		int opc;
		// Scanner sc = new Scanner(System.in);

		do {
			opc = menu();
			if (empleados.isEmpty() && opc > 1 && opc < 11) {
				System.out.println("No hay ning�n empleado");
			} else {
				switch (opc) {
				case 1:
					sartuDatuak(empleados);
					break;
				case 2:
					bistaratuDatuak(empleados);
					break;
				case 3:
					listarEmpleadosPorApellido(empleados);
					break;
				case 4:
					consultarModificarEmpleado(empleados);
				
				case 5:
					borrarEmpleado(empleados);
					break;
				case 6:
					zerrendatuLangileakAdinaz(empleados);
					break;
				case 7:
					zerrendatuLangileakAdinAlta(empleados);
					break;
				case 8:
					zerrendatuLangileakEnpresaldia(empleados);
					break;
				case 9:
					egunekoZozketa(empleados);
					break;
				case 10:
					opc = subMenu();
					if (opc == 1)
						galdetegiak(empleados);
					else
						galdetegiaIzenak(empleados);
					break;
				default:
					System.out.println("Opci�n erronea");
				}
			}
		} while (opc != 11);

	}

}
