package main;

import javax.xml.namespace.QName;
import javax.xml.xquery.*;

import net.xqj.basex.BaseXXQDataSource;

public class Main {

	public static void main(String[] args) {
		try {
			XQDataSource xqs = new BaseXXQDataSource();
			xqs.setProperty("serverName", "localhost");
			xqs.setProperty("port", "1984");
			XQConnection conn = xqs.getConnection("USERNAME", "PASSWORD");
			XQPreparedExpression xqpe = conn.prepareExpression("declare variable $x as xs:string external; $x");
			
			xqpe.bindString(new QName("x"), "Hello World!", null);
			
			XQResultSequence rs = xqpe.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getItemAsString(null));
			}
			
			conn.close();
		} catch (XQException e) {
			e.printStackTrace();
		}
		
	}

}
