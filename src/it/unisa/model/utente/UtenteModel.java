package it.unisa.model.utente;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.unisa.model.ModelInterface;
import it.unisa.model.connessione.DriverManagerConnectionPool;
import it.unisa.model.utente.UtenteBean.Tipo;

public class UtenteModel implements ModelInterface<UtenteBean, String>{

	@Override
	public UtenteBean doRetriveByKey(String code) throws SQLException {
		PreparedStatement statement = null;
		UtenteBean bean= new UtenteBean();
		String sql="SELECT * FROM utente WHERE email=?";
		if(code!=null) {
			try (Connection con = DriverManagerConnectionPool.getConnection()) {
				statement = con.prepareStatement(sql);
				statement.setString(1, code);

				System.out.println("DoRetriveByKey=" + statement.toString());
				ResultSet rs = statement.executeQuery();
				
				if(!rs.next())  // se non sono stati trovati utenti
					return null;
				
				rs.previous(); //altrimenti ritorna alla riga analizzata in precedenza e restituisci l'oggetto
				
				while (rs.next()) {
					bean.setDataIscrizione(rs.getString("dataiscrizione"));
					bean.setEmail(rs.getString("email"));
					bean.setImg(rs.getString("imgProfilo"));
					bean.setPassword(rs.getString("passw"));
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
	/**
	 * Questo metodo deve essere utilizzato specificando il tipo di utente da prelevare =utente|tecnico|admin
	 * @param tipo il tipo di utente da scegliere
	 */
	public Collection<UtenteBean> doRetriveAll(String tipo) throws SQLException {
		PreparedStatement statement = null;
		
		if(tipo!=null) {
		
		String sql="SELECT * FROM UTENTI WHERE tipoDiUtente=?";
		ArrayList<UtenteBean> collection= new ArrayList<UtenteBean>();
		
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);
			statement.setString(1, tipo);

			System.out.println("DoRetriveAll=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				UtenteBean bean = new UtenteBean();

				bean.setDataIscrizione(rs.getString("dataiscrizione"));
				bean.setEmail(rs.getString("email"));
				bean.setImg(rs.getString("imgProfilo"));
				bean.setPassword(rs.getString("passw"));
				bean.setUsername(rs.getString("username"));
				bean.setpIVA(rs.getString("pIVA"));
				collection.add(bean);
			}
		}
		return collection;
		}
		else
			return null;
	}
	

	@Override
	public void doSave(UtenteBean utente) throws SQLException {
		PreparedStatement statement=null;
		
		String sql="INSERT INTO utenti VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			
			statement = con.prepareStatement(sql);
			statement.setString(1,utente.getUsername());
			statement.setString(2,utente.getEmail());
			statement.setBytes(3,utente.getPassword());
			statement.setString(4,utente.getpIVA());
			statement.setString(5,utente.getDataIscrizione());
			statement.setString(6,utente.getTipo());
			statement.setString(7,utente.getImg());
			
			System.out.println("DoSave=" + statement.toString());
			 statement.executeUpdate();
			 con.commit();//<----- a volte vorrei non essere così tanto forte
			 System.out.println("dovrebbe essere andato tutto bene");
			
			} catch (SQLException e) {
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
	
	public byte[] getUserPassword(String email) throws SQLException {
		PreparedStatement stat= null;
		String sql="SELECT * FROM utenti WHERE email= ? ";
		
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			stat = con.prepareStatement(sql);
			stat.setString(1,email);
			
			System.out.println("getUserPassword=" + stat.toString());
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				return rs.getBytes("passw");
			}
			
			return null;
			
		
	}
		
	}
	
	

}
