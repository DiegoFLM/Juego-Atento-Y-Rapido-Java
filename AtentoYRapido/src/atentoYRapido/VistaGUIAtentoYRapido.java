/*
 * Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Miniproyecto 1: Juego Atento y rapido.
 */

package atentoYRapido;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import javax.management.StringValueExp;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.Timer;

import misComponentes.Titulos;

public class VistaGUIAtentoYRapido extends JFrame {

	//Atributos
	private JPanel superior, opciones, inicio, zonaJuego, /**panelAuxiliar1, panelAuxiliar2,*/ estadisticas;
	private CardLayout card;
	
	private JLabel aciertos, fallos, puntos, vidasRestantes, labelImagenInicio;
	private JTextField numAciertos, numFallos, cantPuntos, cantVidasRestantes;
	private JButton salir, abandonar, nuevoJuego, pulsar;
	private ImageIcon cuadro, imagenFondo, iPulsar;
	private Titulos tituloJuego, tituloEstadisticas;
	private ControlAtentoYRapido controlAtentoYRapido;
	private Escucha escucha;
	private GridBagConstraints constraints;
	private ImageIcon imagenInicio;
	
	//private ImageIcon i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, iTransparent;
	private JLabel j0, j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18;
	private JLabel t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14;
	private JLabel imagenRepetida;
	private JLabel[] jLabelTransparentArray = {t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14};
	private JLabel[] jLabelArray = {j0, j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18};
	

	private Integer[] imagenesVisibles;
	private Integer[] posicionesImagenesVisibles;

	private Random aleatorio;
	private Timer timer;
	private Border border;
	
	//Metodos 
	public VistaGUIAtentoYRapido() {
		initGUI();
	
		//set default window configuration
		this.setTitle("Atento y rapido");
		this.setSize(600,600);		
		//this.setSize(getMinimumSize());
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	private void initGUI() {
		constraints = new GridBagConstraints();
		this.getContentPane().setLayout(new GridBagLayout());
		border = BorderFactory.createLineBorder(Color.blue);

		//crear objetos escucha, control and others
		escucha = new Escucha();
		controlAtentoYRapido = new ControlAtentoYRapido();
		
		//set up GUIComponents
		tituloJuego = new Titulos("ATENTO Y RAPIDO!", 30, new Color (0, 0, 0)); //RGB
		tituloEstadisticas = new Titulos("Estadisticas: ", 30, new Color (50, 200, 100));
	
		
		//Opciones		 JButton salir, abandonar, nuevoJuego, pulsar;
		opciones = new JPanel();
		//panelAuxiliar1 = new JPanel();
		//panelAuxiliar2 = new JPanel();
		salir = new JButton("Salir");
		salir.addActionListener(escucha);
		salir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		abandonar = new JButton("Abandonar");
		abandonar.addActionListener(escucha);
		abandonar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		nuevoJuego = new JButton("Nuevo Juego");
		nuevoJuego.addActionListener(escucha);
		nuevoJuego.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//opciones.setPreferredSize(new Dimension(310, 180))
		
		opciones.setLayout(new GridLayout(1,3, 50, 0));
		opciones.add(salir);
		opciones.add(abandonar);
		opciones.add(nuevoJuego);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.CENTER;
				//this.add(opciones, BorderLayout.);
		this.add(opciones, constraints);
		
		//Inicio
		inicio = new JPanel();
		
		//Zona de Juego (lo básico)
		zonaJuego = new JPanel();
		zonaJuego.setLayout(new GridBagLayout());
		
		pulsar = new JButton ();							//Este es el botón con el que se juega.
		iPulsar = new ImageIcon(new ImageIcon("src/imagenes/pushButton.png").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
		pulsar.setIcon(iPulsar);
		pulsar.addActionListener(escucha);
		pulsar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		
		//Estadisticas
		estadisticas = new JPanel();
		
		//Panel superior
		superior = new JPanel();
		
		card = new CardLayout(0, 0);
		superior.setLayout(card);
		superior.add("inicio", inicio);
		superior.add("zonaJuego", zonaJuego);
		superior.add("estadisticas", estadisticas);
		//superior.add("opciones", opciones);
		
		card.show(superior, "inicio");
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
				//this.add(superior, BorderLayout.CENTER);
		this.add(superior, constraints);
		
		
		//Componentes gráficos
		imagenInicio = new ImageIcon("src/imagenes/imagenInicio.png");
		//labelImagenInicio.setBounds(0, 500, 2, 2); //No se ven reflejados los cambios en la GUI
		Image dabImage = imagenInicio.getImage();
		Image modifiedDabIcon = dabImage.getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);
		imagenInicio = new ImageIcon(modifiedDabIcon);
		labelImagenInicio = new JLabel(imagenInicio);

		inicio.add(labelImagenInicio);
		
		
		//zonaJuego (Completa)
		constraints.gridx = 3;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		zonaJuego.add(pulsar, constraints);
		

		
		/*Se genera un arreglo de imágenes transparentes asignadas a JLabel para poder rellenar los espacios que no estén siendo ocupados
		 * por una imagen del juego.*/
		
		for (int c = 0; c < 15; c++) {
			jLabelTransparentArray[c] = new JLabel(new ImageIcon(new ImageIcon("src/imagenes/transparent.png").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
		}
		
		
	
		/*Se genera un arreglo con las imágenes del juego asignadas a JLabel*/

		for (int c = 0; c < 19; c++) {
			jLabelArray[c] = new JLabel(new ImageIcon(new ImageIcon("src/imagenes/" + c + ".png").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
		}
		
		
		//Agrega imágenes transparentes a la zona de juego para después cambiar sólo las imágenes necesarias.
		for(int y = 0; y < 4; y++) {
			for(int x = 0; x < 4; x++ ) {
				if ((x != 3) || (y != 3)) {
					constraints.gridx = x;
					constraints.gridy = y;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.BOTH;
					constraints.anchor = GridBagConstraints.CENTER;
					zonaJuego.add(jLabelTransparentArray[(x + (4 * y))], constraints);
				}
			}
		}
		
		
		aleatorio = new Random();
		timer = new Timer(2000, escucha);
	}
	
	
	
	
	

	
	
	private void iniciarJuego() { 
		zonaJuego.removeAll();
		constraints = new GridBagConstraints();
		
		for(int y = 0; y < 4; y++) {
			for(int x = 0; x < 4; x++ ) {
				if ((x != 3) || (y != 3)) {
					constraints.gridx = x;
					constraints.gridy = y;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.BOTH;
					constraints.anchor = GridBagConstraints.CENTER;
					zonaJuego.add(jLabelTransparentArray[(x + (4 * y))], constraints);
				}
			}
		}
		
		constraints.gridx = 3;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		zonaJuego.add(pulsar, constraints);
		
		
		imagenesVisibles = controlAtentoYRapido.nuevoJuego();				//Ya se tienen las imágenes a guardar
		/*Ahora vamos a asignarles posiciones no repetidas; La posición de cada imagen es un entero entre 0 y 15
		que determina su posición en la pantalla contando las posiciones de izquierda a derecha y de arriba hacia abajo.*/
		
		posicionesImagenesVisibles = new Integer[imagenesVisibles.length];
		
		Set<Integer> setPosiciones = new LinkedHashSet<Integer>();
		
		while (setPosiciones.size() < imagenesVisibles.length) {
			setPosiciones.add(aleatorio.nextInt(14));
		}
		posicionesImagenesVisibles = setPosiciones.toArray(new Integer[setPosiciones.size()]);
		
		for(Integer p = 0; p < posicionesImagenesVisibles.length; p++) {

			constraints.gridx = (int) posicionesImagenesVisibles[p] % 4;
			constraints.gridy = (int) (posicionesImagenesVisibles[p] / 4);
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.anchor = GridBagConstraints.CENTER;
			zonaJuego.add(jLabelArray[imagenesVisibles[p]], constraints);
		}
		for (int c = 0; c < jLabelArray.length; c++) {
			jLabelArray[c].setBorder(null);
		}
	}
	
	
	
	
	
	
	
	
	
	private void cambioCuadro() {
		
		constraints = new GridBagConstraints();
		imagenesVisibles = controlAtentoYRapido.cambiarCuadro();
		//System.out.println("i: " + imagenesVisibles);
		//System.out.println("p: " + posicionesImagenesVisibles);
		if (controlAtentoYRapido.getHayImagenRepetida()) {
		imagenRepetida = new JLabel(new ImageIcon(new ImageIcon("src/imagenes/" + controlAtentoYRapido.getImagenRepetida() + ".png").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
		}
		

		zonaJuego.removeAll();
		constraints = new GridBagConstraints();
		
		
		for(int y = 0; y < 4; y++) {
			for(int x = 0; x < 4; x++ ) {
				if ((x != 3) || (y != 3)) {
					constraints.gridx = x;
					constraints.gridy = y;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.BOTH;
					constraints.anchor = GridBagConstraints.CENTER;
					zonaJuego.add(jLabelTransparentArray[(x + (4 * y))], constraints);
				}
			}
		}
		
		constraints.gridx = 3;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		zonaJuego.add(pulsar, constraints);
		
		
		/*Cuando hay imagen repetida no se puede asignar el mismo JLabel a dos posiciones, de modo que hay que usar un JLabel auxiliar
		llamado imagenRepetida, el cual se inserta en la posición de la primera ocurrencia de la imagen repetida en posicionesImagenesVisibles*/
		for(Integer p = 0; p < posicionesImagenesVisibles.length; p++) {
			constraints.gridx = (int) posicionesImagenesVisibles[p] % 4;
			constraints.gridy = (int) (posicionesImagenesVisibles[p] / 4);
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.anchor = GridBagConstraints.CENTER;
			if ((p == controlAtentoYRapido.primeraOcurrenciaImagenRepetida()) & controlAtentoYRapido.getHayImagenRepetida()) {
				zonaJuego.add(imagenRepetida, constraints);
			}
			else {
				zonaJuego.add(jLabelArray[imagenesVisibles[p]], constraints);
			}
		}
		
		for (int c = 0; c < jLabelArray.length; c++) {
			jLabelArray[c].setBorder(null);
		}
		
		jLabelArray[controlAtentoYRapido.getCuadroNuevo()].setBorder(border);
	}
	
	
	
	
	
	
	
	
	
	
	private void nuevaRonda() {
		zonaJuego.removeAll();
		constraints = new GridBagConstraints();
		
		for(int y = 0; y < 4; y++) {
			for(int x = 0; x < 4; x++ ) {
				if ((x != 3) || (y != 3)) {
					constraints.gridx = x;
					constraints.gridy = y;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.BOTH;
					constraints.anchor = GridBagConstraints.CENTER;
					zonaJuego.add(jLabelTransparentArray[(x + (4 * y))], constraints);
				}
			}
		}
		
		constraints.gridx = 3;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		zonaJuego.add(pulsar, constraints);
		
		
		imagenesVisibles = controlAtentoYRapido.nuevaRonda();		/*Se generan nuevas imágenes, pero conservando puntuación y demás valores*/
		
		if (controlAtentoYRapido.getHayImagenRepetida()) {
			imagenRepetida = new JLabel(new ImageIcon(new ImageIcon("src/imagenes/" + controlAtentoYRapido.getImagenRepetida() + ".png").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
			imagenRepetida.setBorder(border);
		}
		
		
		posicionesImagenesVisibles = new Integer[imagenesVisibles.length];
		
		Set<Integer> setPosiciones = new LinkedHashSet<Integer>();
		
		while (setPosiciones.size() < imagenesVisibles.length) {
			setPosiciones.add(aleatorio.nextInt(14));
		}
		posicionesImagenesVisibles = setPosiciones.toArray(new Integer[setPosiciones.size()]);
		
		for(Integer p = 0; p < posicionesImagenesVisibles.length; p++) {

			constraints.gridx = (int) posicionesImagenesVisibles[p] % 4;
			constraints.gridy = (int) (posicionesImagenesVisibles[p] / 4);
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.anchor = GridBagConstraints.CENTER;
			if ((p == controlAtentoYRapido.primeraOcurrenciaImagenRepetida()) & controlAtentoYRapido.getHayImagenRepetida()) {
				zonaJuego.add(imagenRepetida, constraints);
			}
			else {
				zonaJuego.add(jLabelArray[imagenesVisibles[p]], constraints);
			}		
		}
		for (int c = 0; c < jLabelArray.length; c++) {
			jLabelArray[c].setBorder(null);
		}
	}
	
	
	
	
	
	
	
	
	private void mostrarEstadisticas() {
		estadisticas.removeAll();
		zonaJuego.hide();
		//estadisticas = new JPanel();
		estadisticas.setVisible(true);
		/*estadisticas.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();*/
		estadisticas.setLayout(new GridLayout(4, 2));
		
		
		aciertos = new JLabel("Aciertos: ");
		fallos = new JLabel("Fallos: ");
		puntos = new JLabel("Puntos: ");
		vidasRestantes = new JLabel("Vidas Restantes: ");
		
		
		Integer[] arrayEstadisticas = controlAtentoYRapido.estadisticas();
		numAciertos = new JTextField(String.valueOf(arrayEstadisticas[0]));
		numFallos = new JTextField(String.valueOf(arrayEstadisticas[1]));
		cantPuntos = new JTextField(String.valueOf(arrayEstadisticas[2]));
		cantVidasRestantes = new JTextField(String.valueOf(arrayEstadisticas[3]));
		
		estadisticas.add(aciertos);
		estadisticas.add(numAciertos);
		estadisticas.add(fallos);
		estadisticas.add(numFallos);
		estadisticas.add(puntos);
		estadisticas.add(cantPuntos);
		estadisticas.add(vidasRestantes);
		estadisticas.add(cantVidasRestantes);
	}
	
	
	
	
	
	
	
	
	
	
	private class Escucha implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			// TODO Auto-generated method stub
			if (actionEvent.getSource() == salir) {
				System.exit(0);
			}else if (actionEvent.getSource() == nuevoJuego) {
				System.out.println("nj");
				iniciarJuego();
				timer.stop();
				card.show(superior, "zonaJuego");
				superior.revalidate();
				superior.repaint();
				timer.start();
				
				
				
				
			}else if(actionEvent.getSource() == timer) {
				System.out.println("t");
				if (controlAtentoYRapido.validarJugada(false)) {
					if(controlAtentoYRapido.getHayImagenRepetida()) {
						System.out.println("t1");
						nuevaRonda();
					}else {
						System.out.println("t2");
						cambioCuadro();
					}
					System.out.println("t3");
					timer.restart();
				}else {
					System.out.println("t4");
					if (controlAtentoYRapido.determinarEstadoJuego()) {
						System.out.println("t5");
						nuevaRonda();
						timer.restart();
					}else {
						System.out.println("t6");
						mostrarEstadisticas();
						timer.stop();
						card.show(superior, "estadisticas");
					}
				}
				
				superior.revalidate();
				superior.repaint();
				
			}else if(actionEvent.getSource() == pulsar) {
				System.out.println("p");
				if (controlAtentoYRapido.validarJugada(true)) {
					System.out.println("p1");
					nuevaRonda();
					timer.restart();
				}else {
					System.out.println("p2");
					if (controlAtentoYRapido.determinarEstadoJuego()) {
						System.out.println("p3");
						nuevaRonda();
						timer.restart();
					}else {
						System.out.println("p4");
						mostrarEstadisticas();
						timer.stop();
						card.show(superior, "estadisticas");
					}
				}
				
				superior.revalidate();
				superior.repaint();
				
				
				
			}else if(actionEvent.getSource() == abandonar) {
				controlAtentoYRapido.abandonar();
				timer.stop();
				inicio.hide();
				zonaJuego.hide();
				mostrarEstadisticas();
				card.show(superior, "estadisticas");
				superior.revalidate();
				superior.repaint();
			}
			
		}
		
	}
}
