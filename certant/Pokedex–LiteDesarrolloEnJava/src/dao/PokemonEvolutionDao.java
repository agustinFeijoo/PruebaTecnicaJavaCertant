package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.PokemonEvolution;


/**
 * @author alberto
 *
 */
public class PokemonEvolutionDao {
	private static PokemonEvolutionDao instanciaPokemonEvolutionDao;
	
	private static Session session;
	private Transaction tx;
	
	protected PokemonEvolutionDao() {
	}
	
	public static PokemonEvolutionDao getInstanciaPokemonEvolutionDao() {
		if(instanciaPokemonEvolutionDao==null)
			instanciaPokemonEvolutionDao= new PokemonEvolutionDao();
		return instanciaPokemonEvolutionDao;
	}
	

	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}
	/*
	public PokemonEvolution traerPokemonEvolutionYTypes(int idPokemonEvolution) throws HibernateException {
		PokemonEvolution objeto = null;
		try {
			iniciaOperacion();
			objeto = (PokemonEvolution) session.get(PokemonEvolution.class, idPokemonEvolution);
			Hibernate.initialize(objeto.getTypes());
		} finally {
			session.close();
		}
		return objeto;
	}
	*/

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}

	public int agregar(PokemonEvolution objeto) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}

	public void actualizar(PokemonEvolution objeto) throws HibernateException {
		try {
			iniciaOperacion();
			session.update(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}

	public void eliminar(PokemonEvolution objeto) throws HibernateException {
		try {
			iniciaOperacion();
			session.delete(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}

	public PokemonEvolution traerPokemonEvolution(int idPokemonEvolution) throws HibernateException {
		PokemonEvolution objeto = null;
		try {
			iniciaOperacion();
			objeto = (PokemonEvolution) session.get(PokemonEvolution.class, idPokemonEvolution);
		} finally {
			session.close();
		}
		return objeto;
	}
	public PokemonEvolution traerPokemonEvolution(String name) throws HibernateException {
		PokemonEvolution objeto = null;
		try {
			iniciaOperacion();
			objeto = (PokemonEvolution) session.createQuery("from Pokemon where name like '"+name+"'").uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}

	

	@SuppressWarnings("unchecked")
	public List<PokemonEvolution> traer() throws HibernateException {
		List<PokemonEvolution> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from PokemonEvolution ").list();
		} finally {
			session.close();
		}
	return lista;
	}
	public PokemonEvolution traerPokemonEvolutionYTypes(int idPokemonEvolution) throws HibernateException {
		PokemonEvolution  PokemonEvolution= null;
		try {
			iniciaOperacion();
			PokemonEvolution = (PokemonEvolution)session.createQuery("from PokemonEvolution where idPokemonEvolution="+idPokemonEvolution).uniqueResult();
			Hibernate.initialize(PokemonEvolution.getTypes());
		} finally {
			session.close();
		}
	return PokemonEvolution;
	}
}
