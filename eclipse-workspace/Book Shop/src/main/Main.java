package main;

import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.*;
import org.basex.query.QueryProcessor;
import org.basex.query.iter.Iter;
import org.basex.query.value.item.Item;

public class Main {
	
	static final String DB_PATH = "db/test.xml";
	
	public static void main(final String... args) throws Exception {
	    // Database context.
	    Context context = new Context();

	    // Create a database from a local or remote XML document or XML String
	    System.out.println("\n* Create a database.");
	    new CreateDB("DBExample", DB_PATH).execute(context);
	    
	    // Query
	    System.out.println("\n* Use the database command:");
	    String query = "for $x in doc('" + DB_PATH + "')//nodo/adios return data($x)";
	    System.out.println(new XQuery(query).execute(context));
	   
	    // Close the database context
	    context.close();
	  }
}
