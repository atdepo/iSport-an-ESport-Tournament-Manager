package it.unisa.model.torneo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.unisa.model.connessione.DriverManagerConnectionPool;
import it.unisa.model.ModelInterface;

public class TournamentModel implements ModelInterface<TournamentBean,String>{

	@Override
	public TournamentBean doRetriveByKey(String code) throws SQLException {
		PreparedStatement statement= null;
		
		TournamentBean bean= new TournamentBean();
		String sql="SELECT * FROM torneo WHERE codice=?";
		
		try (Connection con=DriverManagerConnectionPool.getConnection()){
			statement = con.prepareStatement(sql);
			statement.setInt(1,Integer.parseInt(code));
			System.out.println("DoRetriveByKey="+statement.toString());
			ResultSet rs= statement.executeQuery();
			
			while(rs.next()) {
				
				bean.setCAPStruttura(rs.getInt("CAPStruttura"));
				bean.setCodGioco(rs.getString("CodGioco"));
				bean.setData(rs.getString("DataTorneo"));
				bean.setIndirizzoStruttura(rs.getString("IndirizzoStruttura"));
				bean.setNome(rs.getString("Nome"));
				bean.setCodice(rs.getInt("Codice"));
				
			}
		}
		return bean;
	}

	@Override
	public Collection<TournamentBean> doRetriveAll(String order) throws SQLException {
		
		PreparedStatement statement= null;
		Collection<TournamentBean> collection= new ArrayList<TournamentBean>();
		
		String sql="SELECT * FROM torneo";
		
		try (Connection con=DriverManagerConnectionPool.getConnection()){
			statement = con.prepareStatement(sql);
			
			System.out.println("DoRetriveAll="+statement.toString());
			ResultSet rs= statement.executeQuery();
			
			while(rs.next()) {
				TournamentBean bean= new TournamentBean();

				bean.setCAPStruttura(rs.getInt("CAPStruttura"));
				bean.setCodGioco(rs.getString("CodGioco"));
				bean.setData(rs.getString("DataTorneo"));
				bean.setIndirizzoStruttura(rs.getString("IndirizzoStruttura"));
				bean.setNome(rs.getString("Nome"));
				bean.setCodice(rs.getInt("Codice"));
				
				collection.add(bean);
			}
		}
		return collection;
	}

	@Override
	public void doSave(TournamentBean product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(TournamentBean product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(TournamentBean product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
