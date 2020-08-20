package it.unisa.model.sponsor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Collection;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import it.unisa.model.ModelInterface;
import it.unisa.model.connessione.DriverManagerConnectionPool;
import it.unisa.model.modalita.ModalitaBean;


public class SponsorModel implements ModelInterface<SponsorBean, SponsorKey>{

	@Override
	public SponsorBean doRetriveByKey(SponsorKey code) throws SQLException {
		PreparedStatement statement = null;

		SponsorBean bean = new SponsorBean();
		String sql = "SELECT * FROM sponsor WHERE nome=? AND codtorneo=?";
		if (code != null) {
			try (Connection con = DriverManagerConnectionPool.getConnection()) {
				statement = con.prepareStatement(sql);
				statement.setString(1, code.getNome());
				statement.setInt(2, code.getCodTorneo());

				System.out.println("DoRetriveByKey=" + statement.toString());
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					bean.setNome(rs.getString("nome"));
					bean.setCodTorneo(rs.getInt("codtorneo"));
				}
			}
			return bean;
		} else {
			// TODO ERRORE
			return null;
		}
	}

	@Override
	public Collection<SponsorBean> doRetriveAll(String order) throws SQLException {
		PreparedStatement statement = null;

		ArrayList<SponsorBean> collection= new ArrayList<SponsorBean>();
		String sql = "SELECT DISTINCT nome FROM sponsor";
		
			try (Connection con = DriverManagerConnectionPool.getConnection()) {
				statement = con.prepareStatement(sql);
				System.out.println("DoRetriveAll=" + statement.toString());
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					SponsorBean bean = new SponsorBean();
					
					bean.setNome(rs.getString("nome"));
					collection.add(bean);
				}
			}
			return collection;
	}

	@Override
	public void doSave(SponsorBean bean) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(SponsorBean bean) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(SponsorBean bean) throws SQLException {
		// TODO Auto-generated method stub
		
	}



}
