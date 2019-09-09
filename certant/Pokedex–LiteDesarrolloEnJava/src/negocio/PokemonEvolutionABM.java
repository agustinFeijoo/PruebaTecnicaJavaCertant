package negocio;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import dao.PokemonEvolutionDao;
import datos.PokemonEvolution;
import datos.PokemonEvolution;
import datos.Type;

public class PokemonEvolutionABM {
	PokemonEvolutionDao dao = PokemonEvolutionDao.getInstanciaPokemonEvolutionDao();

	public PokemonEvolution traerPokemonEvolution(int idPokemonEvolution) {
		PokemonEvolution c = dao.traerPokemonEvolution(idPokemonEvolution);
		return c;
	}



	public void eliminar(int idPokemonEvolution) {
		
		PokemonEvolution pp = dao.traerPokemonEvolution(idPokemonEvolution);
		dao.eliminar(pp);
	}

	public List<PokemonEvolution> traerPokemonEvolution() {
		return dao.traer();
	}
	//sacado del pokemonPet no aplicaría acá,lo dejo por si me sirve para hacer el proximo ABM
	public void mostrarFilas(HttpServletResponse response) throws IOException{
		
		PokemonEvolutionDao PokemonEvolutionDao=PokemonEvolutionDao.getInstanciaPokemonEvolutionDao(); 
		List<PokemonEvolution> lstPokemonEvolution=PokemonEvolutionDao.traer();
		String sClases;//String de clases
		Iterator<PokemonEvolution> itPp=lstPokemonEvolution.iterator();//pokemon pet
		Iterator<Type> itType;
		Type type;
		PokemonEvolution pkPt;
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
			pkPt=(PokemonEvolution) itPp.next();
			
			pe=PokemonEvolutionDao.traerPokemonEvolutionYTypes(pkPt.getIdPokemon());
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
			
		 
			
			// No me salió realizar la cadena de types
			
			
			response.getWriter().append(
					
							 "<tr>"	
								+ "<td id='c1f"+pkPt.getIdPokemon()+"'>"+pkPt.getName()+"</td>"
								+ "<td id='c2f"+pkPt.getIdPokemon()+"'>"+sClases+"</td>"
								+ "<td id='c3f"+pkPt.getIdPokemon()+"'>"+pkPt.getLevelToFound()+"</td>"
								+ "<td id='c4f"+pkPt.getIdPokemon()+"'>"+pkPt.getHabilities()+"</td>"
								+"<td><button onclick='modificarPokemonEvolution("+pkPt.getIdPokemon()+")'id='boton"+pkPt.getIdPokemon()+ 
								"'>Modificar</button><button onclick='eliminarPokemonEvolution("+pkPt.getIdPokemon()+")'>Eliminar</button></tr>");
			
		}
		response.getWriter().append("</tbody>");
			
		}
		
	public int agregar(String name,int levelToFound,String habilities) throws Exception{
		
		PokemonEvolution p = new PokemonEvolution();
		return dao.agregar(p);
	}
	public PokemonEvolution traer(String name) {
		return dao.traerPokemonEvolution(name);
	}
	
	public List<PokemonEvolution> traer() {
		return dao.traer();
	}
	}


