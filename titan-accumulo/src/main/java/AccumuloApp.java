import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanFactory;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;



public class AccumuloApp {

static TitanGraph graph;
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        configureGraph();
        createNodesAndEdges();
        retrieveNodes();
    }
    
    static void configureGraph(){
    	 System.out.println("Configuring Graph...");
    	 Configuration conf = new BaseConfiguration();
         conf.setProperty("storage.backend","com.thinkaurelius.titan.diskstorage.AccumuloStoreManager");
         conf.setProperty("storage.instance","graph");
         conf.setProperty("storage.user","root");
         conf.setProperty("storage.password","root");
         graph = TitanFactory.open(conf);
         System.out.println("Configure Graph....");
    }
    
    static void createNodesAndEdges(){
    	System.out.println("Creating Nodes and Edges...");
    	Vertex juno = graph.addVertex(null);
    	juno.setProperty("name", "juno");
    	Vertex jupiter = graph.addVertex(null);
    	jupiter.setProperty("name", "jupiter");
    	Edge married = graph.addEdge(null, juno, jupiter, "married");
    	Vertex turnus = graph.addVertex(null);
    	turnus.setProperty("name", "turnus");
    	Vertex hercules = graph.addVertex(null);
    	hercules.setProperty("name", "hercules");
    	Vertex dido = graph.addVertex(null);
    	dido.setProperty("name", "dido");
    	Vertex troy = graph.addVertex(null);
    	troy.setProperty("name", "troy");
    	
    	Edge edge = graph.addEdge(null, juno, turnus, "knows");
    	edge.setProperty("since",2010);
    	edge.setProperty("stars",5);
    	edge = graph.addEdge(null, juno, hercules, "knows");
    	edge.setProperty("since",2011);
    	edge.setProperty("stars",1);
    	edge = graph.addEdge(null, juno, dido, "knows");
    	edge.setProperty("since", 2011);
    	edge.setProperty("stars", 5);
    	graph.addEdge(null, juno, troy, "likes").setProperty("stars",5);
    	graph.commit();
    	System.out.println("Created Nodes and Edges...");
    }
    
    static void retrieveNodes(){
    	System.out.println("Retrieving Nodes");
    	for(Vertex vertex: graph.getVertices()){
    		System.out.println("Vertex:" + vertex.getProperty("name"));
    	}
    	System.out.println("Retrieved Nodes");
    }

}
