/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao.impl;

import java.io.File;
import java.util.Scanner;

import santaclara.dao.IGenericoDAO;
import santaclara.factorymethod.Conexion;
import santaclara.factorymethod.FabricaConexionDataBase;

public abstract class GenericoDAO implements IGenericoDAO {
				
	private FabricaConexionDataBase fabricaConexionDataBase; 
	private Conexion conexion;
	
	public void activarConexionBaseDato() throws Exception {
		Scanner scaner = new Scanner(new File("archivosConexionBaseDato/ultimaConexion.txt"));
		if(scaner.hasNext())
		{
			fabricaConexionDataBase = new FabricaConexionDataBase(scaner.skip("tipo:").nextLine().trim());
			conexion = fabricaConexionDataBase.fabricarConexion();
		}
		scaner.close();
	}
	
	public Conexion getConexion(){
		return conexion;
	}
}
