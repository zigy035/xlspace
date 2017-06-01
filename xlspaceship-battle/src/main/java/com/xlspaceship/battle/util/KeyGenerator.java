package com.xlspaceship.battle.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public abstract class KeyGenerator implements IdentifierGenerator {
	
	private String entityClassName;
	
	KeyGenerator(String entityClassName) {
		this.entityClassName = entityClassName;
	}

	@Override
	public synchronized Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		
		Connection connection = session.connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(obj.id) FROM " + entityClassName + " obj");
            if (rs.next()) {
                int id = rs.getInt(1) + 1;
                String generatedId = entityClassName.toLowerCase() + "-" + String.valueOf(id);
                System.out.println("Generated Id: " + generatedId);
                return generatedId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return entityClassName.toLowerCase() + "-0";
	}
	
}
