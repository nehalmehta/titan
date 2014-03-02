package com.thinkaurelius.titan.diskstorage.accumulo;

import com.google.common.base.Preconditions;
import com.thinkaurelius.titan.diskstorage.common.AbstractStoreTransaction;
import com.thinkaurelius.titan.diskstorage.keycolumnvalue.StoreTransaction;
import com.thinkaurelius.titan.diskstorage.keycolumnvalue.StoreTxConfig;

/**
 * It creates a transaction type specific to Accumulo, which lets us check for
 * user errors like passing a HBase transaction into a Accumulo method.
 * 
 * @author Milind Parikh <milindparikh@gmail.com>
 */
public class AccumuloTransaction extends AbstractStoreTransaction {

	private byte[] visibility; 
	
	public AccumuloTransaction(StoreTxConfig config) {
		super(config);
		this.visibility = config.getVisibility();
	}

	public static AccumuloTransaction getTx(StoreTransaction txh) {
		Preconditions.checkArgument(txh != null);
		Preconditions.checkArgument(txh instanceof AccumuloTransaction,
				"Unexpected transaction type %s", txh.getClass().getName());
		return (AccumuloTransaction) txh;
	}

	public byte[] getVisibility() {
		return visibility;
	}

	public void setVisibility(byte[] visibility) {
		this.visibility = visibility;
	}

}
