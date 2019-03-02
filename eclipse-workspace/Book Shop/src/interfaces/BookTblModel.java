package gui;

import java.util.ArrayList;
import java.util.Date;
import entities.Book;
import entities.Warehouse;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class BookTblModel extends AbstractTableModel {

	private String[] columnNames = { "ISBN", "Title", "Author", "Publisher", "Edition", "Publishing Date", "Price" };
	private ArrayList<Book> books;

	public BookTblModel() {
		this.books = new ArrayList<Book>();
	}

	public BookTblModel(ArrayList<Book> books) {
		this.books = books;
	}

	public int getRowCount() {
		if (books == null) {
			return 0;
		} else {
			return books.size();
		}
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return books.get(row).getIsbn();
		case 1:
			return books.get(row).getTitle();
		case 2:
			return books.get(row).getAuthor();
		case 3:
			return books.get(row).getPublisher();
		case 4:
			return books.get(row).getEdition();
		case 5:
			return books.get(row).getPublishingDate();
		case 6:
			return books.get(row).getPrice();
		default:
			return null;
		}
		
	}
	
	public Book getBook(int row) {
		Book book = new Book();
		String isbn = (String) getValueAt(row,0);
		String title = (String) getValueAt(row,1);
		String author = (String) getValueAt(row,2);
		String publisher = (String) getValueAt(row,3);
		String edition = (String) getValueAt(row,4);
		Date publishingDate = (Date) getValueAt(row,5);
		float price = (float) getValueAt(row, 6);
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setEdition(edition);
		book.setPublishingDate(publishingDate);
		book.setPrice(price);
		
		return book;
	}
	
	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public void addBook(Book book) {
		this.books.add(book);
	}
}
