/*
 * Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Miniproyecto 1: Juego Atento y rapido.
 */

package atentoYRapido;

public class ControlAtentoYRapido {
	
	//Atributos:
	
	//Constantes
	private TableroImagenes tablero;
	private int cantidadInicialImagenes;
	private int puntosAumentoDificultad; /* Es una cantidad fija de puntos que cada vez que aumenta la cantidad 
	(puntos % puntosAumentoDificultad) hace que aumente en 1 la cantidad de imagenes mostradas.*/
	private int lapsoDeTiempo; // En milisegundos.
	private int puntosPorAcierto;

	//Variables
	
	private int auxNuevaRonda;
	private Integer[] imagenesMostradas;
	private int puntos;
	private int vidasRestantes;
	private int aciertos;
	private int fallos;
	private boolean hayImagenRepetida;
	private int imagenRepetida;
	private int primeraOcurrenciaImagenRepetida;
	private int posicionCuadroIluminado;
	private int cuadroNuevo;
	private boolean juegoEnCurso;
	private Integer[] auxCambioCuadro;
	

	
	//Métodos
	
	//Constructor
	public ControlAtentoYRapido() {
		
		//Constantes
		tablero = new TableroImagenes();
		cantidadInicialImagenes = 3;
		puntosAumentoDificultad = 30;
		lapsoDeTiempo = 3;
		puntosPorAcierto = 5;
		
		//Variables
		auxNuevaRonda = 0;
		imagenesMostradas = new Integer[cantidadInicialImagenes];
		puntos = 0;
		vidasRestantes = 3;
		aciertos = 0;
		fallos = 0;		
		hayImagenRepetida = false;
		posicionCuadroIluminado = -1;
		juegoEnCurso = false;
		auxCambioCuadro = new Integer[4];
	}
	
	/*Cada vez que se quiera iniciar a jugar se invoca a nuevoJuego, el cual establece los valores de atributos adecuados.
	 * Un juego es lo que ocurre desde que se da click al botón jugar, hasta que el jugador gana, pierde o abandona.
	 * Este método devuelve el conjunto de imágenes a mostrar.*/
	public Integer[] nuevoJuego() {
		puntos = 0;
		vidasRestantes = 3;
		aciertos = 0;
		fallos = 0;		
		hayImagenRepetida = false;
		posicionCuadroIluminado = -1;
		juegoEnCurso = true;
		
		
		imagenesMostradas = this.nuevaRonda();
		return imagenesMostradas;
	}
	
	/*Una ronda ocurre desde que se muestra un nuevo conjunto de imágenes hasta que el jugador acierta o falla ante un conjunto de 
	 * imágenes que tienen imagen repetida. Cuando sólamente cambia una imagen, eso es un nuevo cuadro, no una nueva ronda.
	 * Este método calcula la cantidad de imagenes correspondiente a la puntuación y devuelve el conjunto de imágenes
	 * a mostrar.*/
	public Integer[] nuevaRonda() {
		auxNuevaRonda = cantidadInicialImagenes + (puntos / puntosAumentoDificultad);
		imagenesMostradas = tablero.nuevasImagenes(auxNuevaRonda);
		hayImagenRepetida = false;
		posicionCuadroIluminado = -1;
		return imagenesMostradas;
	}
	
	//Devuelve el arreglo de imágenes a mostrar con el nuevo cuadro en la posición del anterior.
	public Integer[] cambiarCuadro() {
		tablero.cambioCuadro();
		posicionCuadroIluminado = tablero.getPosicionCuadroNuevo();
		imagenesMostradas = tablero.getImagenesMostradas();
		cuadroNuevo = imagenesMostradas[posicionCuadroIluminado];
		hayImagenRepetida = tablero.getHayImagenRepetida();
		imagenRepetida = tablero.getImagenRepetida();
		auxCambioCuadro = new Integer[imagenesMostradas.length + 1];
		
		/*for (int c = 0; c < (imagenesMostradas.length); c++) {
			auxCambioCuadro[c] = imagenesMostradas[c];
		}

		auxCambioCuadro[imagenesMostradas.length] = posicionCuadroIluminado;
		return auxCambioCuadro;*/
		
		return imagenesMostradas;
	}
	
	
	/*Este método determina si el jugador acertó o falló al acabarse el tiempo o hacer click. 
	 * Si el jugador acertó, validarJugada devuelve true. En caso contrario devuelve false.*/
	public boolean validarJugada(boolean hizoClick) {
		if ((hayImagenRepetida & hizoClick) || (!hayImagenRepetida & !hizoClick)) {
			puntos = puntos + puntosPorAcierto;
			aciertos ++;
			return true;
		}else {
			vidasRestantes = vidasRestantes - 1;
			fallos ++;
			return false;
		}
	}
	
	
	/*Devuelve true si la partida sigue en juego, es decir, si vidasRestantes > 0, y false en caso contrario.
	 * Se asume que este método sólo es invocado cuando la el juego está en curso.*/
	public boolean determinarEstadoJuego(){
		if (vidasRestantes == 0) {
			juegoEnCurso = false;
		}
		return juegoEnCurso;
	}
	
	
	
	
	
	/*Devuelve un arreglo de enteros que muestra en un orden específico las estadísticas del juego para que la interfaz pueda 
	 * hacer uso de esa información. Vidas restantes se debe mostrar porque la partida puede terminar antes de que sea acaben 
	 * las vidas.*/
	public Integer[] estadisticas(){
		Integer[] estadisticas = {aciertos, fallos, puntos, vidasRestantes};
		return estadisticas;
	}
	
	
	public int primeraOcurrenciaImagenRepetida() {
		imagenesMostradas = tablero.getImagenesMostradas();
		for (int c = 0; c < imagenesMostradas.length; c++) {
			if (imagenesMostradas[c] == imagenRepetida) {
				primeraOcurrenciaImagenRepetida = c;
				break;
			}
		}
		return primeraOcurrenciaImagenRepetida;
	}
	
	public Integer[] abandonar() {
		juegoEnCurso = false;
		return this.estadisticas();
	}
	
	public int getCuadroNuevo() {
		return cuadroNuevo;
	}
	
	public boolean getHayImagenRepetida() {
		return hayImagenRepetida;
	}
	
	public Integer[] getImagenes() {
		return imagenesMostradas;
	}
	
	public int getImagenRepetida() {
		return imagenRepetida;
	}
	
	public int getLapsoDeTiempo() {
		return lapsoDeTiempo;
	}
	
	
	public int getPosicionCuadroIluminado() {
		return posicionCuadroIluminado;
	}
	
	
}
