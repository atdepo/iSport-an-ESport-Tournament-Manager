package it.unisa.model.giocatore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.unisa.model.ModelInterface;
import it.unisa.model.connessione.DriverManagerConnectionPool;
import it.unisa.model.gioco.GiocoBean;

public class GiocatoreModel implements ModelInterface<GiocatoreBean, String> {

	@Override
	public GiocatoreBean doRetriveByKey(String nome) throws SQLException {
		
		PreparedStatement statement = null;

		GiocatoreBean bean = new GiocatoreBean();
		String sql = "SELECT * FROM gioco WHERE nickname=?";

		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);
			statement.setString(1, nome);
			System.out.println("DoRetriveByKey=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				bean.setNickname(rs.getString("nickname"));
				bean.setCodtecnico(rs.getString("codtecnico"));
				bean.setCognome(rs.getString("cognome"));
				bean.setDatanascita(rs.getString("dataN"));
				bean.setNome(rs.getString("nome"));
				bean.setNomesquadra(rs.getString("nomesquadra"));
				bean.setRuolo(rs.getString("ruolo"));
				
			}
		}
		return bean;
	}

	@Override
	public Collection<GiocatoreBean> doRetriveAll(String order) throws SQLException {
		PreparedStatement statement = null;

		String sql = "SELECT * FROM gioco";
		
		ArrayList<GiocatoreBean> collection= new ArrayList<GiocatoreBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);
			System.out.println("DoRetriveByKey=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				GiocatoreBean bean = new GiocatoreBean();

				bean.setNickname(rs.getString("nickname"));
				bean.setCodtecnico(rs.getString("codtecnico"));
				bean.setCognome(rs.getString("cognome"));
				bean.setDatanascita(rs.getString("dataN"));
				bean.setNome(rs.getString("nome"));
				bean.setNomesquadra(rs.getString("nomesquadra"));
				bean.setRuolo(rs.getString("ruolo"));
				collection.add(bean);
			}
		}
		return collection;
	}

	@Override
	public void doSave(GiocatoreBean product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(GiocatoreBean product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(GiocatoreBean product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
