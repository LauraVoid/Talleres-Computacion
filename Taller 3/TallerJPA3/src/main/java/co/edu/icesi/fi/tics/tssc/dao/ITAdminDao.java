package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.modelo.TsscAdmin;

public interface ITAdminDao {

	public void save (TsscAdmin admin);
	public void delete(TsscAdmin admin);
	public TsscAdmin update (TsscAdmin admin);
	public List<TsscAdmin> findById(long id);
}
