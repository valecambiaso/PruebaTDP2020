package tdp;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.Filter;

public class Graph {
	
	private int cantVertices; //Cantidad de vértices del grafo.
	private boolean[][] grafo; //Grafo.
	private int[] vertices; //Arreglo con los vértices.
	private final int capacidad = 15; //Capacidad inicial de vértices en el grafo.
	private static Logger logger;
	
	/**
	 * Crea un nuevo grafo vacío.
	 */
	public Graph(){
		cantVertices = 0;
		grafo = new boolean[capacidad][capacidad];
		vertices = new int[capacidad];
		
		if(logger == null) {
			logger = logger.getLogger(Graph.class.getName());
			
			Handler hnd = new ConsoleHandler();
			hnd.setLevel(Level.FINE);
			logger.addHandler(hnd);
			
			//logger.setLevel(Level.WARNING);
			
			Logger rootLogger = logger.getParent();
			for(Handler h : rootLogger.getHandlers()) {
				h.setLevel(Level.OFF);
			}
		}
	}
	
	/**
     * Agrega un nodo al grafo.
     * @param node Nodo a agregar.
     */
    public void addNode (int node){
    	if(pertenece(node) == -1) {
	    	if (cantVertices == vertices.length) {
	        	expandir();
	    	}
	    	
	      	vertices[cantVertices] = node;
	      	
	      	for (int i = 0; i <= cantVertices; i++){
	        	grafo[cantVertices][i] = false;
	        	grafo[i][cantVertices] = false;
	      	}   
	
	      	cantVertices++;
	      	
	
	    	logger.info("Se agrego el vertice " +node+ " al grafo, ahora hay "+cantVertices+" vertices.");
	    }else {
	    	logger.warning("El nodo "+ node +" ya pertenecia al grafo.");
	    }
	}

	/**
	 * Agrega un arco entre el nodo1 y el nodo2.
	 * @param node1 Nodo origen.
	 * @param node2 Nodo destino.
	 */
	public void addEdge (int node1, int node2){
    	int pos1 = pertenece(node1);
    	int pos2 = pertenece(node2);
    	if ((pos1 != -1) && (pos2 != -1)){
     		grafo[pos1][pos2] = true;
     		logger.info("Se agrego un arco al grafo que conecta "+ node1 + " con " + node2 + ".");
    	}else {
    		if(pos1 == -1)
    			logger.warning(node1 + " no pertenece al grafo.");
    		if(pos2 == -1)
    			logger.warning(node2 + " no pertenece al grafo.");
    		logger.warning("No se agrego ningun arco.");
    	}
    }
	
	/**
     * Elimina un nodo del grafo.
     * @param node Nodo a eliminar.
     */
	public void removeNode (int node){
   		int pos = pertenece(node);

     	if (pos != -1){
        	cantVertices--;

        	comprimir(pos);
        	
        	logger.info("Se elimino el vertice " +node+ " del grafo, ahora hay "+cantVertices+" vertices.");
      	}else {
      		logger.warning("El nodo "+ node +" no pertenecía al grafo.");
      	}
	}

	/**
     * Elimina el arco entre el nodo1 y el nodo2.
     * @param node1 Nodo origen.
     * @param node2 Nodo destino.
     */
    public void removeEdge (int node1, int node2){
   		int pos1 = pertenece(node1);
    	int pos2 = pertenece(node2);
    	if ((pos1 != -1) && (pos2 != -1)){
    		if(grafo[pos1][pos2] == true) {
	     		grafo[pos1][pos2] = false;
	     		logger.info("Se elimino el arco del grafo que conecta "+ node1 + " con " + node2 + ".");
     		}else {
     			logger.warning("Ningun arco conectaba " + node1 + " con " + node2 +", luego no se elimino ninguno.");
     		}
    	}else {
    		if(pos1 == -1)
    			logger.warning(node1 + " no pertenece al grafo.");
    		if(pos2 == -1)
    			logger.warning(node2 + " no pertenece al grafo.");
    		logger.warning("No se elimino ningun arco.");
    	}
   	}
    
	/**
     * Verifica si el nodo pertenece a la estructura.
     * @param nodo Nodo que se quiere verificar si pertenece al grafo.
     * @return En caso de que el nodo pertenezca a la estructura, devuelve la posición del mismo 
     * en la lista de vértices, en caso contrario devuelve -1.
     */
	private int pertenece(int nodo){
		int pos = -1;
    	for(int i = 0;(i<vertices.length) && (pos != i);i++){
    		if(vertices[i] == nodo){
    			pos = i;
    		}
    	}
    	return pos;
   	}
	
	/**
	 * Expande el arreglo de vertices y la matriz de arcos.
	 */
   	private void expandir(){
   		int longitud = vertices.length * 2;

      	int [] nuevoVertices = new int[longitud];
     	boolean[][] nuevoGrafo = new boolean[longitud][longitud];

      	for (int i = 0; i < cantVertices; i++){
      		nuevoVertices[i] = vertices[i];
        	for (int j = 0; j < cantVertices; j++){
            	nuevoGrafo[i][j] = grafo[i][j];
         	}
      	}

      	vertices = nuevoVertices;
      	grafo = nuevoGrafo;
	}
   	
   	/**
   	 * Comprime el arreglo de vertices y la matriz de arcos.
   	 * @param pos Posición del nodo que se quiere eliminar.
   	 */
	private void comprimir(int pos){

        for (int i = pos; i < cantVertices; i++) {
            vertices[i] = vertices[i+1];
        }
        for (int i = pos; i < cantVertices; i++) {
            for (int j = 0; j <= cantVertices; j++) {
               	grafo[i][j] = grafo[i+1][j];
            }
        }
        for (int i = pos; i < cantVertices; i++) {
            for (int j = 0; j < cantVertices; j++) {
               	grafo[j][i] = grafo[j][i+1];
            }
        }
	}

}