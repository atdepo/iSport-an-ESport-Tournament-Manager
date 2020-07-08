package it.unisa.model.struttura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.unisa.model.ModelInterface;
import it.unisa.model.connessione.DriverManagerConnectionPool;
import it.unisa.model.torneo.TournamentBean;

public class StrutturaModel implements ModelInterface<StrutturaBean,KeyStruttura> {

	@Override
	public StrutturaBean doRetriveByKey(KeyStruttura key) throws SQLException {
		
			PreparedStatement statement= null;
			
			StrutturaBean bean= new StrutturaBean();
			String sql="SELECT * FROM struttura WHERE CAP=? AND Indirizzo=?";
			if(key!=null) {
			try (Connection con=DriverManagerConnectionPool.getConnection()){
				statement = con.prepareStatement(sql);
				statement.setString(1,key.getCAP());
				statement.setString(2,key.getIndirizzo());

				System.out.println("DoRetriveByKey="+statement.toString());
				ResultSet rs= statement.executeQuery();
				
				while(rs.next()) {
					bean.setCAP(rs.getString("CAP"));
					bean.setIndirizzo(rs.getString("Indirizzo"));
					bean.setNome(rs.getString("Nome"));
					bean.setCapienza(rs.getInt("Capienza"));
				}
			}
			return bean;
			}
			else {
				//TODO ERRORE
				return null;
			}
	}

	@Override
	public Collection<StrutturaBean> doRetriveAll(String order) throws SQLException {

	
		PreparedStatement statement= null;
		
		Collection<StrutturaBean>collection= new ArrayList<StrutturaBean>();

		String sql="SELECT * FROM struttura";

		try (Connection con=DriverManagerConnectionPool.getConnection()){
			statement = con.prepareStatement(sql);

			System.out.println("DoRetriveALL="+statement.toString());
			ResultSet rs= statement.executeQuery();
			
			while(rs.next()) {
				StrutturaBean bean= new StrutturaBean();

				bean.setCAP(rs.getString("CAP"));
				bean.setIndirizzo(rs.getString("Indirizzo"));
				bean.setNome(rs.getString("Nome"));
				bean.setCapienza(rs.getInt("Capienza"));
				
				collection.add(bean);
			}
		}
		
		
		return collection;
		
	}

	@Override
	public void doSave(StrutturaBean product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(StrutturaBean product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(StrutturaBean product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
	
}
