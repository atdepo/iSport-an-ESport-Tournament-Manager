package it.unisa.model.utente;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


import it.unisa.model.ModelInterface;
import it.unisa.model.connessione.DriverManagerConnectionPool;
import it.unisa.model.squadra.SquadraBean;
import it.unisa.model.utente.UtenteBean.Tipo;

public class UtenteModel implements ModelInterface<UtenteBean, String>{

	@Override
	public UtenteBean doRetriveByKey(String code) throws SQLException {
		PreparedStatement statement = null;
		UtenteBean bean= new UtenteBean();
		String sql="SELECT * FROM utenti WHERE email=?";
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
					bean.setPassword(rs.getString("passw"));
					bean.setUsername(rs.getString("username"));
					bean.setpIVA(rs.getString("pIVA"));
					
					switch(rs.getString("TipoDiUtente")) {
					
					case "utente":
						bean.setTipo(Tipo.utente);
					break;
					
					case "admin":
						bean.setTipo(Tipo.admin);
					break;
					
					case "tecnico":
						bean.setTipo(Tipo.tecnico);
					break;
					}
				}
			}
			
			if(bean.isEmpty())
				return null;
			else
				return bean;
			
		} else {
			// TODO ERRORE
			return null;
		}
	}
	
	

public Collection<SquadraBean> getSquadreFromTornei(int codTorneo) {
	PreparedStatement statement = null;
	
	String sql="select s.nome,s.nazionalita,s.imgsquadra from torneo as t, composto as c ,squadra as s where t.codice=? and c.CodTorneo=t.codice and c.codsquadra=s.nome";
	ArrayList<SquadraBean> collection=new ArrayList<SquadraBean>();
	try (Connection con = DriverManagerConnectionPool.getConnection()) {
		statement=con.prepareStatement(sql);
		statement.setInt(1,codTorneo);
		
		System.out.println("getSquadreFromTornei="+statement.toString());
		ResultSet rs=statement.executeQuery();
		while(rs.next()) {
			SquadraBean bean = new SquadraBean();
			bean.setNazionalita(rs.getString("nazionalita"));
			bean.setNome(rs.getString("nome"));
			bean.setTeamImage(rs.getString("imgsquadra"));
			
			collection.add(bean);
		}
		return collection;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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

				switch(rs.getString("TipoDiUtente")) {
				
				case "utente":
					bean.setTipo(Tipo.utente);
				break;
				
				case "admin":
					bean.setTipo(Tipo.admin);
				break;
				
				case "tecnico":
					bean.setTipo(Tipo.tecnico);
				break;
				}
				
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
			statement.setString(6,Tipo.tecnico.name());
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
	/**
	 * Questo metodo viene utilizzato per effettuare il cambio della password
	 * @param email la mail dell'utente in cui cambiare la password
	 * @param nuovaPassword la nuova password da inserire
	 * @param vecchiaPassword la vecchia password per controllare se l'utente effettivamente la conosce 
	 * @return <strong>true</strong> se il cambio di password è stato eseguito con successo, <strong>false</strong> altrimenti 
	 * @throws SQLException
	 */
	
	
	
	
	
	public boolean cambiaPassword(String email,String nuovaPassword,String vecchiaPassword,byte[] utentePassw ) throws SQLException {
		
		
		if(email!=null && nuovaPassword!=null) { //Se la password o la mail sono state inserite 	
				//Effettuo l'hashing della password passata
				try {
					MessageDigest md;
					md = MessageDigest.getInstance("SHA-256");
					byte nuova[]=md.digest(nuovaPassword.getBytes());
					byte vecchia[]=md.digest(vecchiaPassword.getBytes());
					
					//Se la vecchia password è la stessa che ho inserito all'interno del database
					System.out.println("La vecchia password e':"+vecchia);
					System.out.println("La password in sessione e':"+utentePassw);
					if(Arrays.compare(vecchia, utentePassw)==0) {
						
						String sql="UPDATE utenti SET passw=? WHERE email=?";
						try (Connection con = DriverManagerConnectionPool.getConnection();PreparedStatement stat=con.prepareStatement(sql)){
							stat.setBytes(1, nuova);
							stat.setString(2, email);
							stat.executeUpdate();//cambio la password
							con.commit(); //e faccio la commit dell'update
							System.out.println("Ho cambiato la password");
							return true;
						}
					}
					else {
						System.out.println("Problemi col cambio password");
						return false;//se le due password non combaciano
				}
					}
					 catch (NoSuchAlgorithmException e) {
						 e.printStackTrace();
						 return false;//se non è presente l'algoritmo id hashing SHA256
				}
			
		}
		
		else 
			return false; //se non ho inserito correttamente mail o passowrd
		
		
	}
	
	
	public boolean isExistingEmail(String mail){
		
		String sql="select count(*)as num from utenti where email=?";
		
		try (Connection con = DriverManagerConnectionPool.getConnection();PreparedStatement stat=con.prepareStatement(sql)){
		
			stat.setString(1, mail);
			ResultSet res=stat.executeQuery();
			res.next();
			if(res.getInt("num")==0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	public boolean isExistingUsername(String username){
		
		String sql="select count(*)as num from utenti where username=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();PreparedStatement stat=con.prepareStatement(sql)){
		
			stat.setString(1, username);
			ResultSet res=stat.executeQuery();
			res.next();
			if(res.getInt("num")==0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	public boolean isExistingPIVA(String pIVA) {
		
		String sql="select count(*)as num from utenti where piva=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();PreparedStatement stat=con.prepareStatement(sql)){
		
			stat.setString(1, pIVA);
			ResultSet res=stat.executeQuery();
			res.next();
			if(res.getInt("num")==0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	public void cambiaUsername(String newUser,String email) {
		
		String sql="UPDATE utenti SET username=? WHERE email=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();PreparedStatement stat=con.prepareStatement(sql)){
			stat.setString(1, newUser);
			stat.setString(2, email);
			stat.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	
	public void cambiaEmail(String newMail,String oldMail) {
		String sql="UPDATE utenti SET email=? WHERE email=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();PreparedStatement stat=con.prepareStatement(sql)){
			
			stat.setString(1, newMail);
			stat.setString(2, oldMail);
			stat.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
	}
	
	public void cambiaPIVA(String pIVA,String mail) {
		String sql="UPDATE utenti SET piva=? WHERE email=?";

		try (Connection con = DriverManagerConnectionPool.getConnection();PreparedStatement stat=con.prepareStatement(sql)){

		stat.setString(1, pIVA);
		stat.setString(2, mail);
		stat.executeUpdate();
		con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doDelete(String email) throws SQLException {
		
		String sql="DELETE FROM utenti WHERE email = ?";
		
		try (Connection con = DriverManagerConnectionPool.getConnection();PreparedStatement statement=con.prepareStatement(sql);) {
			
			statement.setString(1,email);
			System.out.println("DoDelete=" + statement.toString());
			statement.executeUpdate();
			con.commit();//<----- a volte vorrei non essere così tanto forte
		
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
