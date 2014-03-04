Titan-Accumulo
=====

* Provides vertex and edge level granuality. 
* Useful especially when analytics done on master data and restricted to departments.
* Can expose same nodes and few edges to everyone but restrict few edges between same set of nodes.
* Set visibility and everything created after it is done for that visibility. 
* Once data is set committed we can change visibility. 
* Once stored only users with with rights will have access to vertex and edges.

Sample Code
-----------

```Java

 Configuration conf = new BaseConfiguration();
 // Using AccumuloStoreManager as backend storage
 conf.setProperty("storage.backend","com.thinkaurelius.titan.diskstorage.accumulo.AccumuloStoreManager");
 // Accumulo instance name
 conf.setProperty("storage.instance","graph");
 // Accumulo credentials
 conf.setProperty("storage.user","userName");
 conf.setProperty("storage.password","password");
 SecureTitanGraph graph = SecureTitanFactory.open(conf);

 // Everything from now will be stored with "manager"
 graph.setVisibility("manager".getBytes());
 Vertex juno = graph.addVertex(null);
 juno.setProperty("name", "juno");
 Vertex jupiter = graph.addVertex(null);
 jupiter.setProperty("name", "jupiter");
 Edge married = graph.addEdge(null, juno, jupiter, "married");
 // All data has been committed and visibility can be changed.
 graph.commit();
 graph.setVisibility("supervisor".getBytes());
 // Add new data

```

TO-DO: 

* Discuss with titan-community various design decisions such as having visbility in transactions vs having visibility at vertex level.
* Handle visibility at cache level too. Currently visibility is involved only when local cache is committed. 
