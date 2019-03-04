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

import control.BookControl;
import entities.Book;

public class mainBook {
	
	static final String DB_PATH = "db/book.xml";
	public static Context context;
	
	public static void main(final String... args) throws Exception {
	    // Database context.
		context = new Context();

	    // Create a database from a local or remote XML document or XML String
	    System.out.println("\n* Open database.");
	    try {
	    new Open("DBExample").execute(context);
	    }catch(BaseXException e) {
	    	System.out.println("Doesnt exists");
	    	//Si no existe la base de datos se crea una nueva a partir del documento xml de la carpeta db
	    	new CreateDB("DBExample", "db/book.xml").execute(context);
	    }
	    //Clase queries book
	    BookControl bc = new BookControl();
	    Date date = new Date();
	    //Query del libro almacenado en la base de datos
	    System.out.println("Libro de prueba:	" + bc.selectBookISBN("isbn1").toString());
	    //Modificación de este libro
	    Book book1 = new Book("isbn1", "titlMod", "authorMod", "asdaMod", "asfdasdfMod", date, 80);
	    bc.updateBook(book1);
	    System.out.println("Libro anterior modificado:	" + bc.selectBookISBN("isbn1").toString());
	    //Inserción de un nuevo libro
	    Book book2 = new Book("isbn3", "titl3", "author3", "asdaasdasd", "asfdasdfasdfasdf", date, 90);
	    bc.insertBook(book2);
	    System.out.println("Libro insertado:	" + bc.selectBookISBN("isbn3").toString());
	    //Eliminamos el libro insertado
	    bc.deleteBook("isbn3");
	    System.out.println("Libro insertado eliminado:	" + bc.selectBookISBN("isbn3").toString());
	    //insertamos un par de libros
	    Book bk = new Book("isbn7", "titlasdfa", "author7", "asdaM34563456d", "asfdasdfertyerty", date, 14);
	    Book bk2 = new Book("isbn8", "titlM8", "author8", "asddrhdfrhetrod", "asfdasertyertydate" , date, 25);
	    //bc.insertBook(bk);
	    //bc.insertBook(bk2);
	    //Todos los libros de la base de datos
	    ArrayList<Book> books = bc.selectAllBooks();
	    Iterator itr = books.iterator();
	    System.out.println("Todos los libros de la base de datos");
	    while(itr.hasNext()) {
	    	System.out.println("			Libro:	" + itr.next().toString());
	    }
	  }
}
