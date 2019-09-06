package datos;

public abstract class Pokemon {
	private String name;
	private int levelToFound;
	private int idPokemon;
	
	public Pokemon() {}
	

	public Pokemon(String name, int levelToFound) {
		super();
		this.name = name;
		this.levelToFound = levelToFound;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getLevelToFound() {
		return levelToFound;
	}
	public void setLevelToFound(int levelToFound) {
		this.levelToFound = levelToFound;
	}
	public int getIdPokemon() {
		return idPokemon;
	}
	public void setIdPokemon(int idPokemon) {
		this.idPokemon = idPokemon;
	}
	
	
}
