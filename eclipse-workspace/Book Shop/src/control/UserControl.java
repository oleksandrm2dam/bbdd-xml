package control;

import java.util.ArrayList;
import java.util.Arrays;

import org.basex.core.BaseXException;
import org.basex.core.cmd.XQuery;
import org.basex.query.QueryException;
import org.basex.query.QueryProcessor;
import org.basex.query.iter.Iter;
import org.basex.query.value.item.Item;

import entities.User;
import main.mainBook;

public class UserControl {

	public User selectUserName(String name) {
		User user = new User();
		QueryProcessor qp = null;
		try {
			String query = "for $i in doc('DBExample')//users/user " + "where $i/name = '" + name + "' "
					+ "let $array := array{(data($i/name), data($i/password), data($i/profile))}"
					+ "return $array";
			qp = new QueryProcessor(query, mainBook.context);
			Iter iter = qp.iter();
			Item item;
			while((item = iter.next()) != null) {
				loadUser(user, item);
			}
		} catch (QueryException e) {
			e.printStackTrace();
		} finally {
			if(qp != null) {
				qp.close();
			}
		}
		return user;
	}
	
	public ArrayList<User> selectUser(String field, String value) {
		ArrayList<User> users = new ArrayList<User>();
		QueryProcessor qp = null;
		
		try {
			String query = "for $i in doc('DBExample')//users/user " + "where " + field + " = '" + value + "' "
					+ "let $array := array{(data($i/name), data($i/password), data($i/profile))}"
					+ "return $array";
			qp = new QueryProcessor(query, mainBook.context);
			Iter iter = qp.iter();
			Item item;
			while((item = iter.next()) != null) {
				User user = new User();
				loadUser(user, item);
				users.add(user);
			}
		} catch (QueryException e) {
			e.printStackTrace();
		} finally {
			if(qp != null) {
				qp.close();
			}
		}
		
		return users;
	}
	
	public ArrayList<User> selectAllUsers() {
		ArrayList<User> users = new ArrayList<User>();
		QueryProcessor qp = null;
		
		try {
			String query = "for $i in doc('DBExample')//users/user "
					+ "let $array := array{(data($i/name), data($i/password), data($i/profile))}"
					+ "return $array";
			qp = new QueryProcessor(query, mainBook.context);
			Iter iter = qp.iter();
			Item item;
			while((item = iter.next()) != null) {
				User user = new User();
				loadUser(user, item);
				users.add(user);
			}
		} catch (QueryException e) {
			e.printStackTrace();
		} finally {
			if(qp != null) {
				qp.close();
			}
		}
		
		return users;
	}
	
	public void insertUser(User user) {
		try {
			String query = "let $up := <user>" 
					+ "<name>" + user.getName() + "</name>" 
					+ "<password>" + user.getPassword() + "</password>" 
					+ "<profile>" + user.getProfile() + "</profile>"
					+ " </user> " 
					+ "return insert node $up as last into doc('DBExample')//users ";
			new XQuery(query).execute(mainBook.context);
		} catch (BaseXException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(String name) {
		try {
			String query = "for $i in doc('DBExample')//users/user where $i/name = '" + name + "' "
					+ " return delete node $i";
			new XQuery(query).execute(mainBook.context);
		} catch (BaseXException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user) {
		try {
			String query = "let $up := <user>" 
					+ "<name>" + user.getName() + "</name>" 
					+ "<password>" + user.getPassword() + "</password>" 
					+ "<profile>" + user.getProfile() + "</profile>"
					+ " </user> " 
					+ "for $i in doc('DBExample')//users/user where $i/name = '" + user.getName() + "' "
					+ " return replace node $i with $up";
			new XQuery(query).execute(mainBook.context);
		} catch (BaseXException e) {
			e.printStackTrace();
		}
	}
	
	public void loadUser(User user, Item item) {
		try {
			Object[] objArr = (Object[]) item.toJava();
			String[] strArr = Arrays.copyOf(objArr, objArr.length, String[].class);
			user.setName(strArr[0]);
			user.setPassword(strArr[1]);
			user.setProfile(strArr[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
