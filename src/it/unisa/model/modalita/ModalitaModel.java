package it.unisa.model.modalita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.unisa.model.ModelInterface;
import it.unisa.model.connessione.DriverManagerConnectionPool;
import it.unisa.model.struttura.StrutturaBean;

public class ModalitaModel implements ModelInterface<ModalitaBean, ModalitaKey> {

	@Override
	public ModalitaBean doRetriveByKey(ModalitaKey key) throws SQLException {

		PreparedStatement statement = null;

		ModalitaBean bean = new ModalitaBean();
		String sql = "SELECT * FROM modalita WHERE tipo=? AND nomegioco=?";
		if (key != null) {
			try (Connection con = DriverManagerConnectionPool.getConnection()) {
				statement = con.prepareStatement(sql);
				statement.setString(1, key.getTipo());
				statement.setString(2, key.getNomeGioco());

				System.out.println("DoRetriveByKey=" + statement.toString());
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					bean.setTipo(rs.getString("tipo"));
					bean.setNomeGioco(rs.getString("nomegioco"));
					bean.setNumPartecipanti(rs.getInt("numerogiocatori"));

				}
			}
			return bean;
		} else {
			// TODO ERRORE
			return null;
		}
	}

	@Override
	public Collection<ModalitaBean> doRetriveAll(String order) throws SQLException {
		PreparedStatement statement = null;

		ArrayList<ModalitaBean> collection= new ArrayList<ModalitaBean>();
		String sql = "SELECT * FROM modalita";
		
			try (Connection con = DriverManagerConnectionPool.getConnection()) {
				statement = con.prepareStatement(sql);
				System.out.println("DoRetriveAll=" + statement.toString());
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					ModalitaBean bean = new ModalitaBean();
					
					bean.setTipo(rs.getString("tipo"));
					bean.setNomeGioco(rs.getString("nomegioco"));
					bean.setNumPartecipanti(rs.getInt("numeroGiocatori"));
					collection.add(bean);
				}
			}
			return collection;
		
	}

	public ArrayList<ModalitaBean> doRetriveByGame(String game) throws SQLException {

		PreparedStatement statement = null;

		String sql = "SELECT * FROM modalita WHERE nomegioco=?";
		if (!game.isEmpty()) {
			try (Connection con = DriverManagerConnectionPool.getConnection()) {
				ArrayList<ModalitaBean> modalita= new ArrayList<ModalitaBean>();
				statement = con.prepareStatement(sql);
				statement.setString(1, game);

				System.out.println("DoRetriveByGame=" + statement.toString());
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					ModalitaBean bean = new ModalitaBean();

					bean.setTipo(rs.getString("tipo"));
					bean.setNomeGioco(rs.getString("nomegioco"));
					
					modalita.add(bean);
				}
				return modalita;

			}
		} else {
			// TODO ERRORE
			return null;
		}
	}
	
	
	@Override
	public void doSave(ModalitaBean product) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUpdate(ModalitaBean product) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doDelete(ModalitaBean product) throws SQLException {
		// TODO Auto-generated method stub

	}
}
