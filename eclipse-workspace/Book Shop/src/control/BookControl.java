package control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.XQuery;
import org.basex.query.QueryException;
import org.basex.query.QueryProcessor;
import org.basex.query.iter.Iter;
import org.basex.query.value.item.Item;

import entities.Book;
import main.mainBook;

public class BookControl {

	static final String DB_PATH = "book.xml";
	//Variables usadas para pasar como prámetro field en el método selectBook(String fiel, String value)
	public static final String ISBN_FIELD = "$i/isbn";
	public static final String TITLE_FIELD ="$i/title";
	public static final String AUTHOR_FIELD="$i/author";
	
	
	public Book selectBookISBN(String isbn) {
		    Book book = new Book();
		 try {
	    String queryArray = "for $i in doc('DBExample')//books/book "
	    				  + "where $i/isbn = '" + isbn + "' "
		+ "let $array := array{(data($i/isbn), data($i/title), data($i/author), data($i/publisher), data($i/edition), data($i/publishingDate), data($i/price))}"
		+ "return $array";
	    QueryProcessor proc = new QueryProcessor(queryArray, mainBook.context);
	    //Conseguir iterador para iterar por cada resultado de la query
	    Iter itr = proc.iter();
	    Item item;
	    while((item = itr.next())!= null) {
	    	loadBook(book, item);
	    }
	    // Close the database context
	    proc.close();
		 }catch(QueryException e) {
			 e.printStackTrace();
		 }finally {
		 }
		return book;
	}
	
	//Esta función sirve para buscar libros según el campo que se pase como parámetro
	//Esta es la función que deberían tener todos los demás clases
	public ArrayList<Book> selectBook(String field, String value) {
		    ArrayList<Book> books = new ArrayList<Book>();
		 try {
	    String queryArray = "for $i in doc('DBExample')//books/book "
	    				  + "where " + field + " = '" + value + "' "
		+ "let $array := array{(data($i/isbn), data($i/title), data($i/author), data($i/publisher), data($i/edition), data($i/publishingDate), data($i/price))}"
		+ "return $array";
	    QueryProcessor proc = new QueryProcessor(queryArray, mainBook.context);
	    //Conseguir iterador para iterar por cada resultado de la query
	    Iter itr = proc.iter();
	    Item item;
	    while((item = itr.next())!= null) {
		Book book = new Book();
	    	loadBook(book, item);
		books.add(book);
	    }
	    proc.close();
		 }catch(QueryException e) {
			 e.printStackTrace();
		 }finally {
		 }
		return books;
	}
	
	public ArrayList<Book> selectAllBooks(){
		ArrayList<Book> books = new ArrayList<Book>();
		try {
		String queryArray = "for $i in doc('DBExample')//books/book "
				+ "let $array := array{(data($i/isbn), data($i/title), data($i/author), data($i/publisher), data($i/edition), data($i/publishingDate), data($i/price))}"
				+ "return $array";
		QueryProcessor proc = new QueryProcessor(queryArray, mainBook.context);
	    Iter itr = proc.iter();
	    Item item;
	    while((item = itr.next())!= null) {
		    Book book = new Book();
	    	loadBook(book, item);
	    	books.add(book);
	    }
		}catch(QueryException f) {
			f.printStackTrace();
		}
		return books;
	}
	
	public void insertBook(Book book) {
		 try {
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 String date = sdf.format(book.getPublishingDate());
	    String queryInsert = "let $up := <book>"+ 
	    		"<isbn>" + book.getIsbn() + "</isbn>" +  
	    		" <title>"+ book.getTitle() + "</title>" + 
	    		"<author>" + book.getAuthor() + "</author>" + 
	    		"<publisher>" + book.getPublisher() +"</publisher>" + 
	    		"<edition>"+ book.getEdition() +"</edition>" + 
	    		"<publishingDate>" + date + "</publishingDate>" + 
	    		"<price>" + book.getPrice() + "</price>" +
	    		" </book> " +
	    		"return insert node $up as last into doc('DBExample')//books ";
	    	new XQuery(queryInsert).execute(mainBook.context);
		 }catch(BaseXException e){
			 e.printStackTrace();
		 }finally {
		 }
	}
	
	public void deleteBook(String isbn) {
		try {
			String queryDelete = "for $i in doc('DBExample')//books/book where $i/isbn = '" + isbn + "' "
					+ " return delete node $i";
		new XQuery(queryDelete).execute(mainBook.context);
		}catch(BaseXException e) {
			e.printStackTrace();
		}
	}
	
	public void updateBook(Book book) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String date = sdf.format(book.getPublishingDate());
			String queryReplace = "let $up := <book>"+ 
		    		"<isbn>" + book.getIsbn() + "</isbn>" +  
		    		" <title>"+ book.getTitle() + "</title>" + 
		    		"<author>" + book.getAuthor() + "</author>" + 
		    		"<publisher>" + book.getPublisher() +"</publisher>" + 
		    		"<edition>"+ book.getEdition() +"</edition>" + 
		    		"<publishingDate>" + date + "</publishingDate>" + 
		    		"<price>" + book.getPrice() + "</price>" +
		    		" </book> "+
		    		"for $i in doc('DBExample')//books/book where $i/isbn = '" + book.getIsbn() + "' "
					+ " return replace node $i with $up";
		new XQuery(queryReplace).execute(mainBook.context);
		}catch(BaseXException e) {
			e.printStackTrace();
		}
	}
	
	private void loadBook(Book book, Item item) {
		try {
		Object[] obj = (Object[])item.toJava();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	//Convertir el array de objetos a un array de strings
    	String[] stringArray = Arrays.copyOf(obj, obj.length, String[].class);
    	book.setIsbn(stringArray[0]);
    	book.setTitle(stringArray[1]);
    	book.setAuthor(stringArray[2]);
    	book.setPublisher(stringArray[3]);
    	book.setEdition(stringArray[4]);
    	String date = stringArray[5];
    	book.setPublishingDate(sdf.parse(date));
    	book.setPrice(Float.parseFloat(stringArray[6]));
		}catch(ParseException e) {
			e.printStackTrace();
		}catch(QueryException f) {
			
		}
	}
}
