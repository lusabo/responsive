package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.transaction.Transactional;

public class DDL {

	@Inject
	private Connection connection;

	@Startup
	@Transactional
	public void dropAndCreate() throws Exception {
		dropTableCidade();
		createTableCidades();
	}

	private void dropTableCidade() throws Exception {
		PreparedStatement pstmt;

		pstmt = connection.prepareStatement("DROP TABLE cidades IF EXISTS");

		pstmt.execute();
		pstmt.close();
	}

	private void createTableCidades() throws Exception {
		StringBuffer sql = new StringBuffer();

		sql.append("CREATE TABLE cidades ( ");
		sql.append("	id serial NOT NULL, ");
		sql.append("	nome character varying(255) NOT NULL, ");
		sql.append("CONSTRAINT cidades_pk PRIMARY KEY (id) ");
		sql.append("); ");

		PreparedStatement pstmt = connection.prepareStatement(sql.toString());
		pstmt.execute();
		pstmt.close();
	}
	
}
