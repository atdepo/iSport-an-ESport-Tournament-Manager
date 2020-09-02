package it.unisa.model.messaggio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.unisa.model.ModelInterface;
import it.unisa.model.connessione.DriverManagerConnectionPool;
import it.unisa.model.tecnico.TecnicoBean;

public class MessaggioModel implements ModelInterface<MessaggioBean, Integer> {

	@Override
	public MessaggioBean doRetriveByKey(Integer codice) throws SQLException {

		PreparedStatement statement = null;

		MessaggioBean bean = new MessaggioBean();
		String sql = "SELECT * FROM messaggi WHERE codice=?";
		if (codice != null) {
			try (Connection con = DriverManagerConnectionPool.getConnection()) {
				statement = con.prepareStatement(sql);
				statement.setInt(1, codice);

				System.out.println("DoRetriveByKey=" + statement.toString());
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					bean.setCodice(rs.getInt("codice"));
					bean.setTesto(rs.getString("messaggio"));
				}
			}
			return bean;
		} else {
			// TODO ERRORE
			return null;
		}
	}

	@Override
	public Collection<MessaggioBean> doRetriveAll(String order) throws SQLException {
		System.out.println("Bella biond231a3");
		PreparedStatement statement = null;

		String sql = "SELECT * FROM messaggi";
		
		ArrayList<MessaggioBean> collection= new ArrayList<MessaggioBean>();
		System.out.println("Bella bionda3");

		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);

			System.out.println("DoRetriveAll=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				System.out.println("Bella bionda");
				MessaggioBean bean = new MessaggioBean();
				bean.setCodice(rs.getInt("codice"));
				bean.setTesto(rs.getString("messaggio"));
				
				collection.add(bean);
			}
		}
		return collection;
	}

	@Override
	public void doSave(MessaggioBean bean) throws SQLException {
		PreparedStatement statement = null;
		String sql = "INSERT INTO messaggi values (?,?)";
		
		try(Connection con = DriverManagerConnectionPool.getConnection()){
		
			statement=con.prepareStatement(sql);
			statement.setInt(1, bean.getCodice());
			statement.setString(2, bean.getTesto());
			
			System.out.println("doSave="+statement);
			statement.executeUpdate();
			con.commit();//<----- a volte vorrei non essere così tanto forte
		}		
	}

	@Override
	public void doUpdate(MessaggioBean bean) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(Integer codice) throws SQLException {
		String sql = "DELETE FROM messaggi WHERE codice = ?";
		
		try(Connection con = DriverManagerConnectionPool.getConnection();PreparedStatement statement= con.prepareStatement(sql)){
			
			statement.setInt(1,codice);	
			System.out.println("doDelete="+statement);
			statement.executeUpdate();
			con.commit();//<----- a volte vorrei non essere così tanto forte
		}		
	}

	
}
