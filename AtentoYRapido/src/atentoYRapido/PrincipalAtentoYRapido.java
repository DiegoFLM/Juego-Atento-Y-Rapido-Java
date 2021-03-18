
/*
 * Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Miniproyecto 1: Juego Atento y rapido.
 */

package atentoYRapido;

import java.awt.EventQueue;

public class PrincipalAtentoYRapido {
		/**
		 * The main method. Método principal en Java.
		 *
		 * @param args the arguments
		 */
		public static void main(String[] args) {
			// TODO Auto-generated method stub
		
			
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//VistaGUICraps myWindow = new VistaGUICraps(); 	
					VistaGUIAtentoYRapido myVista = new VistaGUIAtentoYRapido();
					//myVista.initGUI();
				}
			});
			
		}
	}
