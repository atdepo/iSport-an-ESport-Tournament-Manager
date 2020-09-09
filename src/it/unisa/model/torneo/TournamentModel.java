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
				bean.setBudget(rs.getInt("budgetTorneo"));
				bean.setHomePage(rs.getBoolean("isOnHomePage"));
				bean.setProprietario(rs.getString("proprietarioTorneo"));
				
			}
		}
		return bean;
	}
	public Collection<TournamentBean> doRetriveByUser(String email) throws SQLException {
		PreparedStatement statement= null;
		Collection<TournamentBean> collection= new ArrayList<TournamentBean>();
		
		String sql="SELECT * FROM torneo where proprietarioTorneo=?";
		
		try (Connection con=DriverManagerConnectionPool.getConnection()){
			statement = con.prepareStatement(sql);
			statement.setString(1, email);
			System.out.println("DoRetriveByUser="+statement.toString());
			ResultSet rs= statement.executeQuery();
			
			while(rs.next()) {
				TournamentBean bean= new TournamentBean();

				bean.setCAPStruttura(rs.getInt("CAPStruttura"));
				bean.setCodGioco(rs.getString("CodGioco"));
				bean.setData(rs.getString("DataTorneo"));
				bean.setIndirizzoStruttura(rs.getString("IndirizzoStruttura"));
				bean.setNome(rs.getString("Nome"));
				bean.setCodice(rs.getInt("Codice"));
				bean.setBudget(rs.getInt("budgetTorneo"));
				bean.setHomePage(rs.getBoolean("isOnHomePage"));
				bean.setProprietario(rs.getString("proprietarioTorneo"));
				System.out.println(bean.getNome());
				collection.add(bean);
			}
		}
		return collection;
	}

	public Collection<TournamentBean> doRetriveInHome() throws SQLException{
		PreparedStatement statement= null;
		Collection<TournamentBean> collection= new ArrayList<TournamentBean>();
		
		String sql="SELECT * FROM torneo where isOnHomePage=1&&datatorneo<dateadd(day, 0, getdate()) ";
		
		try (Connection con=DriverManagerConnectionPool.getConnection()){
			statement = con.prepareStatement(sql);
			
			System.out.println("DoRetriveInHome="+statement.toString());
			ResultSet rs= statement.executeQuery();
			
			while(rs.next()) {
				TournamentBean bean= new TournamentBean();

				bean.setCAPStruttura(rs.getInt("CAPStruttura"));
				bean.setCodGioco(rs.getString("CodGioco"));
				bean.setData(rs.getString("DataTorneo"));
				bean.setIndirizzoStruttura(rs.getString("IndirizzoStruttura"));
				bean.setNome(rs.getString("Nome"));
				bean.setCodice(rs.getInt("Codice"));
				bean.setBudget(rs.getInt("budgetTorneo"));
				bean.setHomePage(rs.getBoolean("isOnHomePage"));
				bean.setProprietario(rs.getString("proprietarioTorneo"));
				System.out.println(bean.getNome());
				collection.add(bean);
			}
		}
		return collection;
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
				bean.setBudget(rs.getInt("budgetTorneo"));
				bean.setHomePage(rs.getBoolean("isOnHomePage"));
				bean.setProprietario(rs.getString("proprietarioTorneo"));
				System.out.println(bean.getNome());
				collection.add(bean);
			}
		}
		return collection;
	}

	@Override
	public void doSave(TournamentBean torneo) throws SQLException {
		String sql = "INSERT INTO torneo (nome,datatorneo,codgioco,indirizzostruttura,capstruttura,"
				+ "isOnHomePage,budgetTorneo,proprietarioTorneo)"
				+ " VALUES (?,?,?,?,?,?,?,?)";
		
		try(Connection con = DriverManagerConnectionPool.getConnection();PreparedStatement statement= con.prepareStatement(sql)){
			
			
			statement.setString(1,torneo.getNome());
			statement.setString(2,torneo.getData());
			statement.setString(3,torneo.getCodGioco());
			statement.setString(4,torneo.getIndirizzoStruttura());
			statement.setInt(5,torneo.getCAPStruttura());
			statement.setBoolean(6,torneo.isHomePage());
			statement.setInt(7, torneo.getBudget());
			statement.setString(8,torneo.getProprietario());
			

			System.out.println("doSave="+statement);
			statement.executeUpdate();
			con.commit();//<----- a volte vorrei non essere così tanto forte
		}
		
	}

	@Override
	public void doUpdate(TournamentBean product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(String code) throws SQLException {

		String sql = "DELETE FROM torneo WHERE codice=?";
		
		try(Connection con = DriverManagerConnectionPool.getConnection();PreparedStatement statement= con.prepareStatement(sql)){
			
			statement.setString(1,code);	
			System.out.println("doDelete="+statement);
			statement.executeUpdate();
			con.commit();//<----- a volte vorrei non essere così tanto forte
		}
	}

}
