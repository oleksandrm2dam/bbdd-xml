package control;

import java.util.ArrayList;
import java.util.Arrays;

import org.basex.core.BaseXException;
import org.basex.core.cmd.XQuery;
import org.basex.query.QueryException;
import org.basex.query.QueryProcessor;
import org.basex.query.iter.Iter;
import org.basex.query.value.item.Item;

import entities.Client;
import main.mainBook;

public class ClientControl {
	
	public Client selectClientDNI(String dni) {
		Client client = new Client();
		QueryProcessor qp = null;
		try {
			String query = "for $i in doc('DBExample')//clients/client " + "where $i/dni = '" + dni + "' "
					+ "let $array := array{(data($i/dni), data($i/name), data($i/surnames))}"
					+ "return $array";
			qp = new QueryProcessor(query, mainBook.context);
			Iter iter = qp.iter();
			Item item;
			while((item = iter.next()) != null) {
				loadClient(client, item);
			}
		} catch (QueryException e) {
			e.printStackTrace();
		} finally {
			if(qp != null) {
				qp.close();
			}
		}
		return client;
	}
	
	public ArrayList<Client> selectClient(String field, String value) {
		ArrayList<Client> clients = new ArrayList<Client>();
		QueryProcessor qp = null;
		
		try {
			String query = "for $i in doc('DBExample')//clients/client " + "where " + field + " = '" + value + "' "
					+ "let $array := array{(data($i/dni), data($i/name), data($i/surnames))}"
					+ "return $array";
			qp = new QueryProcessor(query, mainBook.context);
			Iter iter = qp.iter();
			Item item;
			while((item = iter.next()) != null) {
				Client client = new Client();
				loadClient(client, item);
				clients.add(client);
			}
		} catch (QueryException e) {
			e.printStackTrace();
		} finally {
			if(qp != null) {
				qp.close();
			}
		}
		
		return clients;
	}
	
	public ArrayList<Client> selectAllClients() {
		ArrayList<Client> clients = new ArrayList<Client>();
		QueryProcessor qp = null;
		
		try {
			String query = "for $i in doc('DBExample')//clients/client "
					+ "let $array := array{(data($i/dni), data($i/name), data($i/surnames))}"
					+ "return $array";
			qp = new QueryProcessor(query, mainBook.context);
			Iter iter = qp.iter();
			Item item;
			while((item = iter.next()) != null) {
				Client client = new Client();
				loadClient(client, item);
				clients.add(client);
			}
		} catch (QueryException e) {
			e.printStackTrace();
		} finally {
			if(qp != null) {
				qp.close();
			}
		}
		
		return clients;
	}
	
	public void insertClient(Client client) {
		try {
			String query = "let $up := <client>" 
					+ "<dni>" + client.getDni() + "</dni>" 
					+ "<name>" + client.getName() + "</name>" 
					+ "<surnames>" + client.getSurnames() + "</surnames>"
					+ " </client> " 
					+ "return insert node $up as last into doc('DBExample')//clients ";
			new XQuery(query).execute(mainBook.context);
		} catch (BaseXException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteClient(String dni) {
		try {
			String query = "for $i in doc('DBExample')//clients/client where $i/dni = '" + dni + "' "
					+ " return delete node $i";
			new XQuery(query).execute(mainBook.context);
		} catch (BaseXException e) {
			e.printStackTrace();
		}
	}
	
	public void updateClient(Client client) {
		try {
			String query = "let $up := <client>" 
					+ "<dni>" + client.getDni() + "</dni>" 
					+ " <name>" + client.getName() + "</name>" 
					+ "<surnames>" + client.getSurnames() + "</surnames>"
					+ " </client> " 
					+ "for $i in doc('DBExample')//clients/client where $i/dni = '" + client.getDni() + "' "
					+ " return replace node $i with $up";
			new XQuery(query).execute(mainBook.context);
		} catch (BaseXException e) {
			e.printStackTrace();
		}
	}
	
	public void loadClient(Client client, Item item) {
		try {
			Object[] objArr = (Object[]) item.toJava();
			String[] strArr = Arrays.copyOf(objArr, objArr.length, String[].class);
			client.setDni(strArr[0]);
			client.setName(strArr[1]);
			client.setSurnames(strArr[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
