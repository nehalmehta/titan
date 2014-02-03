package com.thinkaurelius.titan.diskstorage.connectionpool;

import org.apache.commons.pool2.impl.GenericObjectPool;

public class AccumuloConnectorPool extends GenericObjectPool<AccumuloConnector> {
	public AccumuloConnectorPool(AccumuloConnectorFactory factory) {
		super(factory);
	}

}