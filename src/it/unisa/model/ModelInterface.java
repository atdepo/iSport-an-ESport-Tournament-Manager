package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

public interface ModelInterface <T>{

	public T doRetriveByKey(String code) throws SQLException;
	
	public Collection<T> doRetriveAll(String order) throws SQLException;
	
	public void doSave(T product) throws SQLException;
	
	public void doUpdate(T product) throws SQLException;
	
	public void doDelete(T product) throws SQLException;
	
	
}
