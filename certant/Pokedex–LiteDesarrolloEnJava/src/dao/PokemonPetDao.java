package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.PokemonEvolution;
import datos.PokemonPet;

/**
 * @author alberto
 *
 */
public class PokemonPetDao {
	private static PokemonPetDao instanciaPokemonPetDao;
	
	private static Session session;
	private Transaction tx;
	
	protected PokemonPetDao() {
	}
	
	public static PokemonPetDao getInstanciaPokemonPetDao() {
		if(instanciaPokemonPetDao==null)
			instanciaPokemonPetDao= new PokemonPetDao();
		return instanciaPokemonPetDao;
	}
	

	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}

	public int agregar(PokemonPet objeto) {
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

	public void actualizar(PokemonPet objeto) throws HibernateException {
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

	public void eliminar(PokemonPet objeto) throws HibernateException {
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

	public PokemonPet traerPokemonPet(int idPokemonPet) throws HibernateException {
		PokemonPet objeto = null;
		try {
			iniciaOperacion();
			//objeto = (PokemonPet) session.get(PokemonPet.class, idPokemonPet);
			objeto= (PokemonPet)session.createQuery("from PokemonPet where idPokemon="+idPokemonPet).uniqueResult();
		} finally {
			session.close();
		}
		
		return objeto;
	}
	public PokemonEvolution traerPokemonEvolutionYTypes(int idPokemonPet) throws HibernateException {
		PokemonEvolution pokemonEvolution = null;
		try {
			iniciaOperacion();
			pokemonEvolution=(PokemonEvolution)session.createQuery("select pokemonEvolution from PokemonPet as pp where pp.idPokemon="+idPokemonPet).uniqueResult();
			Hibernate.initialize(pokemonEvolution.getTypes());
		}finally {
			session.close();
		}
		
			
		return pokemonEvolution;
	}
	

	@SuppressWarnings("unchecked")
	public List<PokemonPet> traer() throws HibernateException {
		List<PokemonPet> lista = null;
		try {
			iniciaOperacion();
			lista = (List<PokemonPet>)session.createQuery("from PokemonPet").list();
		} finally {
			session.close();
		}
	return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<PokemonPet> traerPokemonPetsYTypes() throws HibernateException {
		List<PokemonPet> lista = null;
		try {
			iniciaOperacion();
			lista = (List<PokemonPet>)session.createQuery("from PokemonPet ").list();
			for(PokemonPet p:lista) {
				Hibernate.initialize(p);
			}
			
		} finally {
			session.close();
		}
	return lista;
	}
	public PokemonPet traerPokemonPetYTypes(int idPokemonPet) throws HibernateException {
		PokemonPet objeto = null;
		try {
			iniciaOperacion();
			objeto= (PokemonPet)session.createQuery("from PokemonPet where idPokemon="+idPokemonPet).uniqueResult();
			
				Hibernate.initialize(objeto.getPokemonEvolution());
			
			
		} finally {
			session.close();
		}
	return objeto;
	}

}
