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
	public TecnicoBean doRetriveByKey(String cf) throws SQLException {

		PreparedStatement statement = null;

		TecnicoBean bean = new TecnicoBean();
		String sql = "SELECT * FROM tecnico WHERE cf=?";
		if (cf != null) {
			try (Connection con = DriverManagerConnectionPool.getConnection()) {
				statement = con.prepareStatement(sql);
				statement.setString(1, cf);

				System.out.println("DoRetriveByKey=" + statement.toString());
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					bean.setCF(rs.getString("CF"));
					bean.setRecapito(rs.getString("recapito"));
					bean.setIndirizzo(rs.getString("indirizzo"));
					bean.setNome(rs.getString("nome"));
					bean.setCognome(rs.getString("cognome"));
					bean.setDataDiNascita(rs.getString("dataN"));
					bean.setSpecializzazione(rs.getString("specializzazione"));
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

				bean.setCF(rs.getString("CF"));
				bean.setRecapito(rs.getString("recapito"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setDataDiNascita(rs.getString("dataN"));
				bean.setSpecializzazione(rs.getString("specializzazione"));
				
				collection.add(bean);
			}
		}
		return collection;

	}
	
	

	public int doRetrieveTecniciFisici() throws SQLException {
		

		String sql = "SELECT * FROM tecnico WHERE specializzazione='locale'";
		
		ArrayList<TecnicoBean> collection= new ArrayList<TecnicoBean>();

		try (Connection con = DriverManagerConnectionPool.getConnection();PreparedStatement statement = con.prepareStatement(sql)) {
			
			System.out.println("DoRetriveByKey=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				TecnicoBean bean = new TecnicoBean();

				bean.setCF(rs.getString("CF"));
				bean.setRecapito(rs.getString("recapito"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setDataDiNascita(rs.getString("dataN"));
				bean.setSpecializzazione(rs.getString("specializzazione"));
				
				collection.add(bean);
			}
			return collection.size();
			
		}
	}
	
	
	@Override
	public void doSave(TecnicoBean tecnico) throws SQLException {
		PreparedStatement statement = null;
		String sql = "INSERT INTO tecnico values (?,?,?,?,?,?,?)";
		
		try(Connection con = DriverManagerConnectionPool.getConnection()){
		
			statement=con.prepareStatement(sql);
			statement.setString(1, tecnico.getCF());
			statement.setString(2, tecnico.getRecapito());
			statement.setString(3, tecnico.getIndirizzo());
			statement.setString(4, tecnico.getNome());
			statement.setString(5, tecnico.getCognome());
			statement.setString(6, tecnico.getDataDiNascita());
			statement.setString(7, tecnico.getSpecializzazione());
			
			System.out.println("doSave="+statement);
			statement.executeUpdate();
			con.commit();//<----- a volte vorrei non essere così tanto forte
		}


	}

	
	
	@Override
	public void doUpdate(TecnicoBean product) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doDelete(String cf) throws SQLException {
		
		String sql = "DELETE FROM tecnico WHERE cf = ?";
		
		try(Connection con = DriverManagerConnectionPool.getConnection();PreparedStatement statement= con.prepareStatement(sql)){
			
			statement.setString(1,cf);	
			System.out.println("doDelete="+statement);
			statement.executeUpdate();
			con.commit();//<----- a volte vorrei non essere così tanto forte
		}
		
		
		
	}

}
