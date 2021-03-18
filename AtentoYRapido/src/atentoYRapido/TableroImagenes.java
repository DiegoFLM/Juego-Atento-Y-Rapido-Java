/*
 * Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Miniproyecto 1: Juego Atento y rapido.
 */

package atentoYRapido;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class TableroImagenes {

	private int numMaxImagenes;
	private Integer[] imagenesMostradas;
	private boolean hayImagenRepetida;
	private int imagenRepetida;
	private int posicionCuadroNuevo;
	private Integer[] nuevoCuadro;
	private int auxCambioCuadro;
	private Random aleatorio;
	
	public TableroImagenes() {
		numMaxImagenes = 19;
		imagenesMostradas = new Integer[3];
		hayImagenRepetida = false;
		posicionCuadroNuevo = -1;  /*Si el valor de posicionCuadroNuevo es negativo significa que se acaba de mostrar un nuevo 				
		conjunto de imágenes y no hay un "cuadro nuevo" */
		nuevoCuadro = new Integer[2]; /* El primer entero del arreglo indica la posición de imagenesMostradas que va a cambiar, 
				y la segunda posición indica la imagen que va a ser puesta en reemplazo de la que estaba antes.*/
		auxCambioCuadro = -1;
		aleatorio = new Random();
	}
	
	/*Recibe un entero numImagenes, y devuelve un arreglo de imágenes de tamaño numImagenes, el cual también es establecido
	como el nuevo imagenesMostradas. No incluye imágenes repetidas. */
	public Integer[] nuevasImagenes(int numImagenes) {
		imagenesMostradas = new Integer[numImagenes];
		
		/*setImagenes es un Set creado para guardar en él valores aleatorios de manera que luego se puedan pasar esos valores
		 * a una lista sin que haya repeticiones, ya que en la primera pantalla de la ronda se desea que no haya imágenes repetidas.*/
		Set<Integer> setImagenes = new LinkedHashSet<Integer>();
		
		while (setImagenes.size() < numImagenes) {
			setImagenes.add(aleatorio.nextInt(numMaxImagenes));
		}
		imagenesMostradas = setImagenes.toArray(new Integer[setImagenes.size()]);
		hayImagenRepetida = false;
		posicionCuadroNuevo = -1;
		return imagenesMostradas;
	}
	
	
	/*Genera un arreglo de dos enteros aleatorios. El primero es la posición del cuadro que va a cambiar, y el segundo es
	 * la imagen que va a reemplazar a la imagen actual.*/
	public void cambioCuadro(){
		
		nuevoCuadro[0] = aleatorio.nextInt(imagenesMostradas.length);
		posicionCuadroNuevo = nuevoCuadro[0];
		
		do {
			auxCambioCuadro = aleatorio.nextInt(numMaxImagenes);
		}
		while (imagenesMostradas[posicionCuadroNuevo] == auxCambioCuadro); //Así se garantiza que no salga la misma imagen.
		
		nuevoCuadro[1] = auxCambioCuadro;
	
		for (int i = 0; i < imagenesMostradas.length; i++) {
			if (imagenesMostradas[i] == nuevoCuadro[1]) {
				hayImagenRepetida = true;
				imagenRepetida = imagenesMostradas[i];
				break;
			}
		}
		
		imagenesMostradas[posicionCuadroNuevo] = nuevoCuadro[1];
	}
	
	
	
	public boolean getHayImagenRepetida(){
		return hayImagenRepetida;
	}
	
	
	public Integer[] getImagenesMostradas() {
		return imagenesMostradas;
	}
	
	public int getImagenRepetida() {
		return imagenRepetida;
	}
	
	public int getPosicionCuadroNuevo() {
		return posicionCuadroNuevo;
	}
	
}
