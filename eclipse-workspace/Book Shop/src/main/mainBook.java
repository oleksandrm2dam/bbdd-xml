package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import javax.xml.transform.Result;

import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.*;
import org.basex.data.Data;
import org.basex.io.out.ArrayOutput;
import org.basex.io.serial.Serializer;
import org.basex.query.QueryProcessor;
import org.basex.query.iter.Iter;
import org.basex.query.value.Value;
import org.basex.query.value.item.Item;
import entities.Book;

public class mainBook {
	
	static final String DB_PATH = "db/book.xml";
	
	public static void main(final String... args) throws Exception {
	    // Database context.
	    Context context = new Context();

	    // Create a database from a local or remote XML document or XML String
	    System.out.println("\n* Create a database.");
	    new CreateDB("DBExample", DB_PATH).execute(context);
	    
	    
	    System.out.println("\n* Use the database command:");
	 // Query
	    String queryArray = "for $i in doc('" + DB_PATH + "')//book "
	    		+ "let $array := array{(data($i/isbn), data($i/title), data($i/author), data($i/publisher), data($i/edition), data($i/publishingDate), data($i/price))}"
	    		+ "return $array";
	    QueryProcessor proc = new QueryProcessor(queryArray, context);
	    //Conseguir iterador para iterar por cada resultado de la query
	    Iter itr = proc.iter();
	    Item item;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    //Lista para almacenar los libros encontrados
	    ArrayList<Book> books = new ArrayList<Book>();
	    while((item = itr.next())!= null) {
	    	Object[] obj = (Object[])item.toJava();
	    	//Convertir el array de objetos a un array de strings
	    	String[] stringArray = Arrays.copyOf(obj, obj.length, String[].class);
	    	Book book = new Book();
	    	book.setIsbn(stringArray[0]);
	    	book.setTitle(stringArray[1]);
	    	book.setAuthor(stringArray[2]);
	    	book.setPublisher(stringArray[3]);
	    	book.setEdition(stringArray[4]);
	    	String date = stringArray[5];
	    	book.setPublishingDate(sdf.parse(date));
	    	book.setPrice(Float.parseFloat(stringArray[6]));
	    	books.add(book);
	    	System.out.println(book.toString());
	    }
	    // Close the database context
	    context.close();
	  }
}
