package persistence;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.inject.Inject;
import javax.persistence.GeneratedValue;

import br.gov.frameworkdemoiselle.annotation.Name;

public class GenericDAOImpl<T> implements GenericDAO<T>, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Connection connection;

	@Override
	public void insert(T object) throws Exception {

		Class clazz = Class.forName(object.getClass().getName());
		
		String tableName = ((Name) clazz.getAnnotation(Name.class)).value();
		
		Field[] fields = clazz.getDeclaredFields();

		String sFields = "";
		String sValues = "";
		for (int i = 0; i < fields.length; i++) {
			if(!fields[i].getName().equals("serialVersionUID") && 
			   !fields[i].isAnnotationPresent(GeneratedValue.class)){
				if (i == fields.length - 1){
					sFields += fields[i].getName();
					sValues += "?";
				} else {
					sFields += fields[i].getName() + ", ";
					sValues += "?, ";
				}
			}
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append(tableName);
		sql.append(" ( ");
		sql.append(sFields);
		sql.append(" ) ");
		sql.append("values ( ");
		sql.append(sValues);
		sql.append(" ) ");
		
		System.out.println(sql);
		
		PreparedStatement pstmt = connection.prepareStatement(sql.toString());
		
		int pIndex = 0;
		for (int i = 0; i < fields.length; i++) {
			
			if(!fields[i].getName().equals("serialVersionUID") && 
					   !fields[i].isAnnotationPresent(GeneratedValue.class)){
				
				fields[i].setAccessible(true);
				
				System.out.println(fields[i].get(object).toString());
				
				pIndex++;
				pstmt.setString(pIndex, fields[i].get(object).toString());

			}
		}
		
		pstmt.execute();
		pstmt.close();
		
	}

}
