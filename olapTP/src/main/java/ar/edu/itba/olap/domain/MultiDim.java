package olap;

import java.util.LinkedList;
import java.util.List;

public class MultiDim {

	private List<Dimension> dimensions;
	private List<Cubo> cubos;

	public MultiDim() {
		this.dimensions = new LinkedList<Dimension>();
		this.cubos = new LinkedList<Cubo>();
	}

	public void addDimension(Dimension dim) {
		dimensions.add(dim);
	}

	public void addCubo(Cubo cubo) {
		cubos.add(cubo);
	}

	public String toString() {
		String string = "DIMENSIONS_LIST:" + "\n";
		for (Dimension p : dimensions) {
			string = string.concat(p.toString());
		}
		
		string = string.concat("CUBOS_LIST:" + "\n");
		for (Cubo p : cubos) {
			string = string.concat(p.toString());
		}
		return string  + "\n";
	}

}
