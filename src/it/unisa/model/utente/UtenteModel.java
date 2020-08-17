package it.unisa.model.utente;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.unisa.model.ModelInterface;
import it.unisa.model.connessione.DriverManagerConnectionPool;
import it.unisa.model.tecnico.TecnicoBean;

public class UtenteModel implements ModelInterface<UtenteBean, String>{

	@Override
	public UtenteBean doRetriveByKey(String code) throws SQLException {
		PreparedStatement statement = null;
		UtenteBean bean= new UtenteBean();
		String sql="Select * from utente where email=?";
		if(code!=null) {
			try (Connection con = DriverManagerConnectionPool.getConnection()) {
				statement = con.prepareStatement(sql);
				statement.setString(1, code);

				System.out.println("DoRetriveByKey=" + statement.toString());
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					bean.setDataIscrizione(rs.getString("dataiscrizione"));
					bean.setEmail(rs.getString("email"));
					bean.setImg(rs.getString("imgProfilo"));
					bean.setPassword(rs.getBytes("passw"));
					bean.setUsername(rs.getString("username"));
					bean.setpIVA(rs.getString("pIVA"));
				}
			}
			return bean;
		} else {
			// TODO ERRORE
			return null;
		}
	}
	
	

	@Override
	public Collection<UtenteBean> doRetriveAll(String filter) throws SQLException {
		PreparedStatement statement = null;
		
		String sql="Select * from utente where tipoDiUtente=?";
		ArrayList<UtenteBean> collection= new ArrayList<UtenteBean>();
		
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);
			statement.setString(1, filter);

			System.out.println("DoRetriveAll=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				UtenteBean bean = new UtenteBean();

				bean.setDataIscrizione(rs.getString("dataiscrizione"));
				bean.setEmail(rs.getString("email"));
				bean.setImg(rs.getString("imgProfilo"));
				bean.setPassword(rs.getBytes("passw"));
				bean.setUsername(rs.getString("username"));
				bean.setpIVA(rs.getString("pIVA"));

				
				collection.add(bean);
			}
		}
		return collection;

	}
	

	@Override
	public void doSave(UtenteBean utente) throws SQLException {
		PreparedStatement statement=null;
		
		String sql="INSERT INTO utenti \" +\r\n" + 
				"\"(username,email,passw, pIVA, DataIscrizione,TipoDiUtente,imgProfilo) \" +\r\n" + 
				"\"VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			MessageDigest k=MessageDigest.getInstance("SHA-256");
			byte pass[]=k.digest(utente.getPassword());
			
			statement = con.prepareStatement(sql);
			statement.setString(1,utente.getUsername());
			statement.setString(2,utente.getEmail());
			statement.setBytes(3,pass);
			statement.setString(4,utente.getpIVA());
			statement.setString(5,utente.getDataIscrizione());
			statement.setString(6,utente.getTipo());
			statement.setString(7,utente.getImg());
			
			System.out.println("DoSave=" + statement.toString());
			 statement.executeUpdate(sql);

			
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	

	@Override
	public void doUpdate(UtenteBean product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(UtenteBean utente) throws SQLException {
		PreparedStatement statement=null;
		
		String sql="Delete from utenti \" +\r\n" + 
				"\"where username=? \" +\r\n";
		
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);
			statement.setString(1,utente.getUsername());
			
			
			System.out.println("DoDelete=" + statement.toString());
			 statement.executeUpdate(sql);
		
	}
		}

}
