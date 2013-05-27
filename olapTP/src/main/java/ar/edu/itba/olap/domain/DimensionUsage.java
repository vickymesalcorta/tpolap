package olap;

public class DimensionUsage {
	
	private String name;
	private String ptr;
	private Dimension dimension;
	
	public DimensionUsage(String name, String ptr){
		this.name = name;
		this.ptr = ptr;
	}
	
	public void setDimension(Dimension dimension){
		this.dimension = dimension;
	}
	
	public Dimension getDimension(){
		return this.dimension;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPtr() {
		return ptr;
	}

	public void setPtr(String ptr) {
		this.ptr = ptr;
	}
	
	public String toString(){
		return "DIMENSION_USAGE:name: " + name + "- ptr: " + ptr + "- dimension: " + dimension + "\n";
	}
	
}
