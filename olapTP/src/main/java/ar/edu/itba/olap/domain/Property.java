package ar.edu.itba.olap.domain;

public class Property {

	private String type;
	private String name;
	private boolean id;
	
	public Property(String name, String type, boolean id){
		this.name = name;
		this.type = type;
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public boolean isId() {
		return id;
	}
	
	public String toString(){
		return "PROPERTY:name: " + name + "- type: " + type + "- id: " + id + "\n";
	}
	
}
