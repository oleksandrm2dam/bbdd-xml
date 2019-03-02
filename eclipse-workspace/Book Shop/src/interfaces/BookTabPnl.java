package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;

import entities.Book;
import entities.Warehouse;

public class BookTabPnl extends TabPnl {

	ArrayList<Book> books = new ArrayList<Book>();
	
	public BookTabPnl() {
		super();
		initBooks();
	}
	@Override
	public void deleteAction() {
		int[] rows = table.getSelectedRows();
		delete(rows, books);
	}
	@Override
	public void textFieldSearchAction() {
		String value = textField.getText();
		switch((String)comboBox.getSelectedItem()) {
		case "ISBN": //searchWarehouseCode
			break;
		case "TITLE": //searchWarehouseName
			break;
		case "AUTHOR": //searchWarehousePopulation
			break;
		}
		((BookTblModel)table.getModel()).setBooks(books);
		table.clearSelection();
		table.repaint();
	}
	@Override
	public void addAction() {
		BookDialog addPnl = new BookDialog();
		addPnl.setVisible(true);
	}
	@Override
	public void editAction() {
		int row = table.getSelectedRow();
		Book book = new Book();
		if(row>=0) {
			book = ((BookTblModel)table.getModel()).getBook(row);
		}
		BookDialog addPnl = new BookDialog(book);
		addPnl.setVisible(true);
	}
	
	public void initBooks() {

		books = new ArrayList<Book>();
		Date date = new Date();
		Book book = new Book("1", "sad", "asd", "asd", "asd", date, 34f);
		Book book1 = new Book("2", "sad", "asd", "asd", "asd", date, 34f);
		Book book2 = new Book("3", "sad", "asd", "asd", "asd", date, 34f);
		Book book3 = new Book("4", "sad", "asd", "asd", "asd", date, 34f);
		books.add(book);
		books.add(book1);
		books.add(book2);
		books.add(book3);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "ISBN", "AUTHOR", "TITLE" }));
		table.setModel(new BookTblModel(books));
		scrollPane.setViewportView(table);
	}

}
