package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.PokemonPetABM;

/**
 * Servlet implementation class PokemonPet
 */
@WebServlet("/PokemonPet")
public class PokemonPet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PokemonPet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PokemonPetABM pokemonPetABM=new PokemonPetABM();
		
		// TODO Auto-generated method stub
		
		if(request.getParameter("borrar")!=null){
		int idPokemonPet=Integer.parseInt(request.getParameter("idPokemonPet"));
		pokemonPetABM.eliminar(idPokemonPet);
		}
		if(request.getParameter("guardar")!=null) {
			String name=request.getParameter("name");
			//String apellido=request.getParameter("type"); el usuario no debería escribir los types names por acá...En tal caso debería hacer un select
			int levelToFound=Integer.parseInt(request.getParameter("levelToFound"));
			String habilities=request.getParameter("habilities");
			try {
				pokemonPetABM.agregar(name,levelToFound,habilities);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		//response.getWriter().append("Served at: "+request.getParameter("borrar")).append(request.getContextPath());
		
		if(request.getParameter("mostrar").equals("1")) {
			pokemonPetABM.mostrarFilas(response);
			//Tambien muestro el boton para añadir una nueva fila
			response.getWriter().append(
				 "<br>	\n" + 
				 "			<button align=\"center\" onclick=\"agregarPokemonPet()\">Agregar pokemonPet</button>\n" + 
				 "		");
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
