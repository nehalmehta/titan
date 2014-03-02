package com.thinkaurelius.titan.graphdb.database.accumulo;

import com.thinkaurelius.titan.core.TitanException;
import com.thinkaurelius.titan.diskstorage.StorageException;
import com.thinkaurelius.titan.graphdb.configuration.GraphDatabaseConfiguration;
import com.thinkaurelius.titan.graphdb.database.IndexSerializer;
import com.thinkaurelius.titan.graphdb.database.StandardTitanGraph;
import com.thinkaurelius.titan.graphdb.transaction.StandardTitanTx;
import com.thinkaurelius.titan.graphdb.transaction.TransactionConfiguration;
import com.thinkaurelius.titan.graphdb.util.ExceptionFactory;

/**
 * @author nmehta
 *
 */

public class SecureTitanGraph extends StandardTitanGraph {

	private byte[] visibility = null; 
	
	public SecureTitanGraph(GraphDatabaseConfiguration configuration) {
		// TODO Auto-generated constructor stub
		super(configuration);
	}

    public StandardTitanTx newTransaction(TransactionConfiguration configuration) {
        if (!isOpen()) ExceptionFactory.graphShutdown();
        try {
            IndexSerializer.IndexInfoRetriever retriever = indexSerializer.getIndexInforRetriever();
            StandardTitanTx tx = new StandardTitanTx(this, configuration, getBackend().beginTransaction(configuration,retriever, visibility));
            retriever.setTransaction(tx);
            return tx;
        } catch (StorageException e) {
            throw new TitanException("Could not start new transaction", e);
        }
    }

	public byte[] getVisibility() {
		return visibility;
	}

	public void setVisibility(byte[] visibility) {
		this.visibility = visibility;
	}
    
 }
