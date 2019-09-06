package datos;

public class PokemonPet extends Pokemon {
	private int idPokemonPet;
	private PokemonEvolution pokemonEvolution ;
	private String habilities;
	
	
	
	
	public PokemonPet() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public PokemonPet(PokemonEvolution pokemonEvolution, String habilities) {
		super();
		this.pokemonEvolution = pokemonEvolution;
		this.habilities = habilities;
	}



	public PokemonPet(String name, int levelToFound) {
		super(name, levelToFound);
		// TODO Auto-generated constructor stub
	}

	public int getIdPokemonPet() {
		return idPokemonPet;
	}

	public void setIdPokemonPet(int idPokemonPet) {
		this.idPokemonPet = idPokemonPet;
	}

	public PokemonEvolution getPokemonEvolution() {
		return pokemonEvolution;
	}

	public void setPokemonEvolution(PokemonEvolution pokemonEvolution) {
		this.pokemonEvolution = pokemonEvolution;
	}

	public String getHabilities() {
		return habilities;
	}

	public void setHabilities(String habilities) {
		this.habilities = habilities;
	}
	
	

	
	
	
}
