/**
 * 
 */
package com.thinkaurelius.titan.core.accumulo;

import org.apache.commons.configuration.Configuration;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.graphdb.configuration.GraphDatabaseConfiguration;
import com.thinkaurelius.titan.graphdb.database.accumulo.SecureTitanGraph;

/**
 * @author nmehta
 *
 */
public class SecureTitanFactory extends TitanFactory {
	
	public static SecureTitanGraph open(Configuration configuration) {
        return new SecureTitanGraph(new GraphDatabaseConfiguration(configuration));
    }
}
