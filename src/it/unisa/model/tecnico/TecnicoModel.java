package it.unisa.model.tecnico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.unisa.model.ModelInterface;
import it.unisa.model.connessione.DriverManagerConnectionPool;
import it.unisa.model.modalita.ModalitaBean;

public class TecnicoModel implements ModelInterface<TecnicoBean, String> {

	@Override
	public TecnicoBean doRetriveByKey(String code) throws SQLException {

		PreparedStatement statement = null;

		TecnicoBean bean = new TecnicoBean();
		String sql = "SELECT * FROM tecnico WHERE codstaff=?";
		if (code != null) {
			try (Connection con = DriverManagerConnectionPool.getConnection()) {
				statement = con.prepareStatement(sql);
				statement.setString(1, code);

				System.out.println("DoRetriveByKey=" + statement.toString());
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					bean.setSpecializzazione(rs.getString("specializzazione"));
					bean.setCodStaff(rs.getString("codstaff"));
				}
			}
			return bean;
		} else {
			// TODO ERRORE
			return null;
		}
	}

	@Override
	public Collection<TecnicoBean> doRetriveAll(String order) throws SQLException {

		PreparedStatement statement = null;

		String sql = "SELECT * FROM tecnico";
		
		ArrayList<TecnicoBean> collection= new ArrayList<TecnicoBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);

			System.out.println("DoRetriveByKey=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				TecnicoBean bean = new TecnicoBean();

				bean.setSpecializzazione(rs.getString("specializzazione"));
				bean.setCodStaff(rs.getString("codstaff"));
				
				collection.add(bean);
			}
		}
		return collection;

	}

	@Override
	public void doSave(TecnicoBean product) throws SQLException {
		PreparedStatement statement = null;
		String sql = "INSERT INTO tecnico values (?,?)";
		
		try(Connection con = DriverManagerConnectionPool.getConnection()){
		
			statement=con.prepareStatement(sql);
			statement.setString(1,product.getCodStaff());
			statement.setString(2,product.getSpecializzazione());
			
			System.out.println("doSave="+statement);
			statement.executeUpdate();
		}


	}

	@Override
	public void doUpdate(TecnicoBean product) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doDelete(TecnicoBean product) throws SQLException {
		// TODO Auto-generated method stub

	}

}
