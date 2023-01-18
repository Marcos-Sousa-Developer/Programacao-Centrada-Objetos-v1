
import java.util.Arrays;

/**
* @author PCO 59, Marcos Leitão 55852, Rafael Ribeiro 56958, Gabriel Fonseca 57155
*/


public class MetodosVerificacao {
	
	 /**Verifica as propriedades da viagem a realizar num trajeto consoante o sentido  
	 * @param  trajeto - Lista de características de cada planeta
	 * @param  propriedade - Características a verificar em cada planeta
	 * @param  sentido - Sentido da viagem
	 * @requires trajeto != null &amp;&amp; propriedade != null &amp;&amp;
	 * sentido in {"REGULAR","INVERSO"} &amp;&amp; 
	 * os elementos de trajeto são sequencias de caracteristicas da
	 * forma caract1,...,caractm
	 * propriedade é da forma k1:prop1;...;kn:propn onde cada kn é
	 * um inteiro e cada propn é uma sequencia de caracteristicas da
	 * forma caract1,...,caractm
	 * @return Boolean - Valor do resultado conforme a verificação de todas as propriedades.
	 */
	
	static boolean verificaPropriedade(String [] trajeto, String propriedade, String sentido) {
		
		String [] caracteristicas = separaPropriedade(propriedade);
		String [] valores = separaValores(propriedade);		
		String [] planetas = new String [trajeto.length];
		
		if (sentido=="REGULAR") {
			
		    planetas = definePlanetaRegular(valores, trajeto);
		    
		} else {
			
			planetas = definePlanetaInverso(valores, trajeto);
		}
		
		for (int i=0;i<planetas.length;i++) {
			
			String [] caracteristicasSeparadas = caracteristicas[i].split(",");
			
			for(int j=0; j<caracteristicasSeparadas.length; j++) {
			
				if(planetas[i].contains(caracteristicasSeparadas[j]) == false) {
					return false;
				}
			}		
		}
		return true;
	}
	
	 /** Obtém o indice da lista que contem as caracteristicas dos planetas
	 *  quando ultrapassa o trajeto
	 *  @param  trajeto - Tamanho da lista de propriedades de cada planeta 
	 *  @param  planeta - Índice desajustado da lista de propriedades de cada planeta 
	 *  @return int - Índice ajustado da lista de propriedades de cada planeta 
	 */
	
	private static int getIndexTrajeto(int trajeto, int planeta) {
		if(trajeto == planeta) {
			return 0;
		}		
		return planeta%trajeto;
	}
	
	 /** Seleciona, quando o sentido é "REGULAR", os planetas consoante os valores indicados
		 *  e faz uma lista com as suas caracteristicas 
		 *  @param  trajeto - Lista de propriedades de cada planeta 
		 *  @param  valores - Lista de valores a percorrer
		 *  @return  String[] - Lista de caracteristicas dos planetas que se pretende visitar
		 */
	
	private static String[] definePlanetaRegular(String [] valores, String [] trajeto) {
			
		
		String[] caminho= new String [valores.length];
		
		int planeta = 0;
		
		for(int i=0;i<valores.length;i++) {	
			
			planeta += Integer.valueOf(valores[i]);
			
			if (planeta>=trajeto.length) {
				planeta= getIndexTrajeto(trajeto.length, planeta);
			}
			caminho[i]=trajeto[planeta]; 
		}
		return caminho;
	}
	
	 /** Seleciona, quando o sentido é "INVERSO", os planetas consoante os valores indicados
	 *  e faz uma lista com as suas caracteristicas 
	 *  @param  trajeto - Lista de propriedades de cada planeta 
	 *  @param  valores - Lista de valores a percorrer
	 *  @return  String[] - Lista de caracteristicas dos planetas que se pretende visitar
	 */

	
	private static String[] definePlanetaInverso(String [] valores, String [] trajeto) {
		
		String[] caminho= new String [valores.length];
		
		int planeta = trajeto.length-1;
		

		for(int i=0; i<valores.length; i++) {	
			
			planeta = planeta - Integer.valueOf(valores[i]);

			while (planeta<=0) {
				
				planeta=planeta+trajeto.length-1;

			}
			
			caminho[i]=trajeto[planeta]; 	
		}
		return caminho;
	}
	
	 /** Separa a String dada consoante o index e faz uma lista
	     *  das posições dos planetas caso este seja 0 e das características dos mesmos caso seja 1 
		 *  @param  propriedade - Características a verificar em cada planeta 
		 *  @param  index - Valor do tipo de dados que se pretende
		 *  @requires index tem como valor 0 ou 1
		 *  @return  String[] - Lista do tipo de dados que se pretende
		 */

	private static String[] separa(String propriedade, int index) {
		
		String[] getArray = propriedade.split(";"); 
		 
		for(int i=0; i<getArray.length; i++) {

			String props = getArray[i];
			getArray[i] = props.split(":")[index];
		}
		return getArray;
	}
	
	 /** Invoca-se a funcao separa para quando se pretende obter as características dos planetas a visitar
		 *  @param  propriedade -  Características a verificar em cada planeta
		 *  @return  String[] - Lista das caracteristicas de cada planeta a visitar
		 */

	
	private static String[] separaPropriedade(String propriedade) {
		
		return separa(propriedade, 1);
        
	}
	
	 /** Invoca-se a funcao separa para quando se pretende obter as posições dos planetas a visitar
		 *  @param  propriedade - Lista de propriedades de cada planeta 
		 *  @return  String[] - Lista das posições de cada planeta a visitar
		 */

	private static String[] separaValores(String propriedade) {

        return separa(propriedade,0);

    }
}