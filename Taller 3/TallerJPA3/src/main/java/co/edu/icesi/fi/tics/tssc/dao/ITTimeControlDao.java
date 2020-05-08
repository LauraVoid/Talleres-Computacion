package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.modelo.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.modelo.TsscTimecontrol;

public interface ITTimeControlDao {

	public void save (TsscTimecontrol admin);
	public void delete(TsscTimecontrol admin);
	public TsscTimecontrol update (TsscTimecontrol admin);
	public List<TsscTimecontrol> findById(long id);
}
