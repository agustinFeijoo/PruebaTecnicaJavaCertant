package datos;

public class Type {
	private int idType;
	private String name;
	private String description;
	
	public Type() {}
	
	public Type(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public int getIdType() {
		return idType;
	}
	public void setIdType(int idType) {
		this.idType = idType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Type [name=" + name + ", description=" + description + "]";
	}
	
	
}
