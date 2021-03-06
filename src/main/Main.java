 /** 
  *  Asignatura: Complejidad Computacional
  *  Universidad de La Laguna.
  *  Curso: 2015-2016
  *  
  *  
  *  Autores: 
  * 	Germ�n Paz M�ndez
  * 		Contacto: alu0100503647@edu.ull.es
  * 	V�ctor Hern�ndez Perez
  * 		Contacto: alu0100697032@edu.ull.es
  * 	Jose Manuel Hern�ndez Hern�ndez
  * 		Contacto: alu0100775846@edu.ull.es
  */
package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import objects.Instance3SAT;
import objects.InstanceSAT;
import objects.InstanceVC;
import solver.Solver;
import transform.To3SAT;
import transform.ToVC;
import utils.Loader;
import utils.Sys;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException {

		// Cargamos Instancia SAT de un archivo.
		System.out.println("----- Fichero de carga -----");

		InstanceSAT inst = Loader.LoadInstanceFromFile("examples/To3SATcase1.txt");
		// Imprimimos instancia

		System.out.println("\n----- Instancia SAT -----");
		Sys.out(inst);
		
		// SAT Satisfactible?
		System.out.println("\n----- � Satisfacibilidad ? -----");
		Solver.solve(inst);
		
		// transform to 3sat
		Instance3SAT inst3 = To3SAT.to3SAT(inst);
		
		// Imprimimos instancia 3SAT
        System.out.println("\n----- Instancia 3SAT -----");
        Sys.out(inst3);
		
		// 3SAT Satisfactible?
        System.out.println("\n----- � Satisfacibilidad ? -----");
        Solver.solve(inst3);
        
        // transform to vc
        InstanceVC instVC = ToVC.toVC(inst3);
        
        // Imprimimos instancia VC
        System.out.println("\n----- Instancia VC -----");
        Sys.out(instVC);
        
        // 3SAT Satisfactible?
        System.out.println("\n----- � Satisfacibilidad ? -----");
        instVC.solve();
	}

}
