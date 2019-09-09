package negocio;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import dao.PokemonPetDao;
import datos.PokemonEvolution;
import datos.PokemonPet;
import datos.Type;

public class PokemonPetABM {
	PokemonPetDao dao = PokemonPetDao.getInstanciaPokemonPetDao();

	public PokemonPet traerPokemonPet(int idPokemonPet) {
		PokemonPet c = dao.traerPokemonPet(idPokemonPet);
		return c;
	}



	public void eliminar(int idPokemonPet) {
		
		PokemonPet pp = dao.traerPokemonPet(idPokemonPet);
		dao.eliminar(pp);
	}

	public List<PokemonPet> traerPokemonPet() {
		return dao.traer();
	}
	public void mostrarFilas(HttpServletResponse response) throws IOException{
		
		PokemonPetDao pokemonPetDao=PokemonPetDao.getInstanciaPokemonPetDao(); 
		List<PokemonPet> lstPokemonPet=pokemonPetDao.traer();
		String sClases;//String de clases
		Iterator<PokemonPet> itPp=lstPokemonPet.iterator();//pokemon pet
		Iterator<Type> itType;
		Type type;
		PokemonPet pkPt;
		String nameClass;
		PokemonEvolution pe;
		
		
		response.getWriter().append(
				"<thead id='cabeceraTabla'>"//esto podría hacerse con un getContent? sería mas facil con un IFrame?
				+ "<tr>"
					+ "<th>"
						+ "Name"
					+ "</th>"
					
					+ "<th>"
						+ "Type"
					+ "</th>"
					+ "<th>"
					+ "Name class"
					+ "</th>"
					
					+ "<th>"
					+ "levelToFound"
					+ "</th>"
					+ "<th>"
					+ "Habilities"
					+ "</th>"
					+ "<th>"
					+ "action"
					+ "</th>"
					+ "</tr>"
					+ "</thead>"
					+ "<tbody id='tbody'>");
		while(itPp.hasNext()) {
		
			sClases="";//cada vez que cambia de pokemon su cadena de types tambien deberia
			pkPt=(PokemonPet) itPp.next();
			
			pe=pokemonPetDao.traerPokemonEvolutionYTypes(pkPt.getIdPokemon());
			nameClass=pe.getName();
			
			if(pe!=null) {		//En otras paralabras si no tiene evolucion no forma string de nombres de evolucion
				
				itType=pe.getTypes().iterator();
				while(itType.hasNext()) { 		
					type=(Type)itType.next();
					if(itType.hasNext()) {
						sClases=sClases+type.getName()+","; // tomo los nombres de la lista de types de la clase pokemonEvolution
					}else {
						sClases=sClases+type.getName()+".";
					}
					
				}
			}
			
		 
			
	
			
			
			response.getWriter().append(
					
							 "<tr>"	
								+ "<td id='c1f"+pkPt.getIdPokemon()+"'>"+pkPt.getName()+"</td>"
								+ "<td id='c2f"+pkPt.getIdPokemon()+"'>"+sClases+"</td>"
								+ "<td id='c3f"+pkPt.getIdPokemon()+"'>"+nameClass+"</td>"
								+ "<td id='c4f"+pkPt.getIdPokemon()+"'>"+pkPt.getLevelToFound()+"</td>"
								+ "<td id='c5f"+pkPt.getIdPokemon()+"'>"+pkPt.getHabilities()+"</td>"
								+"<td><button onclick='modificarPokemonPet("+pkPt.getIdPokemon()+")'id='boton"+pkPt.getIdPokemon()+ 
								"'>Modificar</button><button onclick='eliminarPokemonPet("+pkPt.getIdPokemon()+")'>Eliminar</button></tr>");
			
		}
		response.getWriter().append("</tbody>");
			
		}
		
	public int agregar(String name,int levelToFound,String habilities) throws Exception{
		
		PokemonPet p = new PokemonPet();
		return dao.agregar(p);
	}		
public int agregar(String name,int levelToFound,PokemonEvolution pokemonEvolution,String habilities) throws Exception{
		
		PokemonPet p = new PokemonPet(name,levelToFound,pokemonEvolution,habilities);
		System.out.println(pokemonEvolution.getIdPokemon());
		return dao.agregar(p);
	}		
	}


