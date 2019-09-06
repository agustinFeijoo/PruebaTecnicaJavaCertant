package datos;

import java.util.Set;

public class PokemonEvolution extends Pokemon{
	private int idPokemonEvolution;
	private Set<Type> types;
	
	private PokemonEvolution prePokemonEvolution;
	
	
	public PokemonEvolution() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PokemonEvolution(Set<Type> types) {
		super();
		this.types = types;
	}

	public PokemonEvolution(Set<Type> types, PokemonEvolution prePokemonEvolution) {
		super();
		this.types = types;
		this.prePokemonEvolution = prePokemonEvolution;
	}

	
	public PokemonEvolution(String name, int levelToFound) {
		super(name, levelToFound);
		// TODO Auto-generated constructor stub
	}
	


	public Set<Type> getTypes() {
		return types;
	}




	public void setTypes(Set<Type> types) {
		this.types = types;
	}




	public int getIdPokemonEvolution() {
		return idPokemonEvolution;
	}


	public void setIdPokemonEvolution(int idPokemonEvolution) {
		this.idPokemonEvolution = idPokemonEvolution;
	}
	



	public PokemonEvolution getPrePokemonEvolution() {
		return prePokemonEvolution;
	}


	public void setPrePokemonEvolution(PokemonEvolution prePokemonEvolution) {
		this.prePokemonEvolution = prePokemonEvolution;
	}
	
}
