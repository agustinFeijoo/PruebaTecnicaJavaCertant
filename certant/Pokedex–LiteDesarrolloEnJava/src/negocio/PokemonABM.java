package negocio;

import org.hibernate.HibernateException;

import dao.PokemonDao;
import datos.Pokemon;

public class PokemonABM {
	public PokemonDao dao;
	public PokemonABM() {
		
	}
	public PokemonDao getInstanciaDao() {
		if(this.dao==null) {
			this.dao=new PokemonDao();
		}	
	return this.dao;
	}
	
}
