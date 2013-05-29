package ar.edu.itba.olap.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		return string + "\n";
	}

	public List<String> getMultiDimNames(){
		List<String> columns = new LinkedList<String>();
		for(Cubo c: cubos){
			Map<Dimension,String> dim_col = c.getColumnNames();		
			 List<String> names = new LinkedList<String>();
			for(Entry<Dimension,String> d: dim_col.entrySet()){
				names.addAll(d.getKey().getColumnNames(c.getName()));
			}
			
		}
		return columns;
	}
	
}
