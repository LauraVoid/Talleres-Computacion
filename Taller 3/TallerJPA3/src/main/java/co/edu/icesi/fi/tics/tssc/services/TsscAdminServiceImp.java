package co.edu.icesi.fi.tics.tssc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.modelo.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.repositories.TsscAdminRepository;

@Service
public class TsscAdminServiceImp implements TsscAdminService{
	
	private TsscAdminRepository adminRepo;

	@Autowired
	public  TsscAdminServiceImp( TsscAdminRepository admin) {
		adminRepo= admin;
	}
	
	@Override
	public TsscAdmin saveAdmin(TsscAdmin name) {
		return adminRepo.save(name);
	}


	
	

}
