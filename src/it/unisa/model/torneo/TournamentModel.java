package it.unisa.model.torneo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import it.unisa.model.ModelInterface;

public class TournamentModel implements ModelInterface<TournamentBean>{

	@Override
	public TournamentBean doRetriveByKey(String code) throws SQLException {
		PreparedStatement statement= null;
		
		TournamentBean bean= null;
		
		
		return null;
	}

	@Override
	public Collection<TournamentBean> doRetriveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
