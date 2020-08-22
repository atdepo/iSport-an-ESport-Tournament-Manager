package it.unisa.model.squadra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.unisa.model.ModelInterface;
import it.unisa.model.connessione.DriverManagerConnectionPool;
import it.unisa.model.giocatore.GiocatoreBean;
import it.unisa.model.gioco.GiocoBean;

public class SquadraModel implements ModelInterface<SquadraBean, String>{

	@Override
	public SquadraBean doRetriveByKey(String nome) throws SQLException {
		PreparedStatement statement = null;

		SquadraBean bean = new SquadraBean();
		String sql = "SELECT * FROM squadra WHERE nome=?";

		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);
			statement.setString(1, nome);
			System.out.println("DoRetriveByKey=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				bean.setNome(rs.getString("nome"));
				bean.setNazionalita(rs.getString("nazionalita"));
				bean.setTeamImage(rs.getString("imgSquadra"));
			}
		}
		return bean;
	}

	@Override
	public Collection<SquadraBean> doRetriveAll(String order) throws SQLException {
		PreparedStatement statement = null;

		String sql = "SELECT * FROM squadra";

		ArrayList<SquadraBean> collection= new ArrayList<SquadraBean>();
		
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);
			System.out.println("DoRetriveAll=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				SquadraBean bean = new SquadraBean();
				bean.setNome(rs.getString("nome"));
				bean.setNazionalita(rs.getString("nazionalita"));
				bean.setTeamImage(rs.getString("imgSquadra"));
				collection.add(bean);
			}
		}
		return collection;
	}

	@Override
	public void doSave(SquadraBean squadra) throws SQLException {
		PreparedStatement statement = null;
		String sql = "INSERT INTO squadra values (?,?,?)";
		GiocatoreBean bean= new GiocatoreBean();
		try(Connection con = DriverManagerConnectionPool.getConnection()){
		
			statement=con.prepareStatement(sql);
			statement.setString(1,squadra.getNome());
			statement.setString(2,squadra.getNazionalita());
			statement.setString(2,squadra.getTeamImage());
			System.out.println("doSave="+statement);
			statement.executeUpdate();
			con.commit();//<----- a volte vorrei non essere così tanto forte
		}
	}

	@Override
	public void doUpdate(SquadraBean product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(String nome) throws SQLException {
		PreparedStatement statement	 = null;
		String sql = "DELETE FROM squadra WHERE (nome=?)";
		try(Connection con = DriverManagerConnectionPool.getConnection()){
		
			statement=con.prepareStatement(sql);
			statement.setString(1,nome);
			
			System.out.println("doDelete="+statement);
			statement.executeUpdate();	
			con.commit();//<----- a volte vorrei non essere così tanto forte
		}
	}

	
	
}
