package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Pokemon;


/**
 * @author alberto
 *
 */
public class PokemonDao {
	private static PokemonDao instanciaPokemonDao;
	
	private static Session session;
	private Transaction tx;
	
	protected PokemonDao() {
	}
	
	public static PokemonDao getInstanciaPokemonDao() {
		if(instanciaPokemonDao==null)
			instanciaPokemonDao= new PokemonDao();
		return instanciaPokemonDao;
	}
	

	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}

	public int agregar(Pokemon objeto) {
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

	public void actualizar(Pokemon objeto) throws HibernateException {
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

	public void eliminar(Pokemon objeto) throws HibernateException {
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

	public Pokemon traerPokemon(int idPokemon) throws HibernateException {
		Pokemon objeto = null;
		try {
			iniciaOperacion();
			objeto = (Pokemon) session.get(Pokemon.class, idPokemon);
		} finally {
			session.close();
		}
		return objeto;
	}

	

	@SuppressWarnings("unchecked")
	public List<Pokemon> traer() throws HibernateException {
		List<Pokemon> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Pokemon ").list();
		} finally {
			session.close();
		}
	return lista;
	}
	public Pokemon traerPokemonYTypes(int idPokemon) throws HibernateException {
		Pokemon  pokemon= null;
		try {
			iniciaOperacion();
			pokemon = (Pokemon)session.createQuery("from Pokemon where idPokemon="+idPokemon).uniqueResult();
			Hibernate.initialize(pokemon.getTypes());
		} finally {
			session.close();
		}
	return pokemon;
	}
}
