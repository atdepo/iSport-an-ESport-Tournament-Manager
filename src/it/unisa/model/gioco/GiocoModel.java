package it.unisa.model.gioco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.unisa.model.ModelInterface;
import it.unisa.model.connessione.DriverManagerConnectionPool;
import it.unisa.model.torneo.TournamentBean;

public class GiocoModel implements ModelInterface<GiocoBean, String> {

	@Override
	public GiocoBean doRetriveByKey(String nome) throws SQLException {
		PreparedStatement statement = null;

		GiocoBean bean = new GiocoBean();
		String sql = "SELECT * FROM gioco WHERE nome=?";

		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);
			statement.setString(1, nome);
			System.out.println("DoRetriveByKey=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				bean.setNomeGioco(rs.getString("nome"));
			}
		}
		return bean;
	}

	@Override
	public Collection<GiocoBean> doRetriveAll(String order) throws SQLException {
		PreparedStatement statement = null;
		ArrayList<GiocoBean> collection = new ArrayList<GiocoBean>();

		String sql = "SELECT * FROM gioco";

		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);

			System.out.println("DoRetriveAll=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				GiocoBean bean = new GiocoBean();
				bean.setNomeGioco(rs.getString("nome"));
				
				collection.add(bean);
			}
		}
		return collection;
	}

	@Override
	public void doSave(GiocoBean product) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUpdate(GiocoBean product) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doDelete(GiocoBean product) throws SQLException {
		// TODO Auto-generated method stub

	}

}
