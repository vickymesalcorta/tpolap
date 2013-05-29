package ar.edu.itba.olap.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Cubo {

	private String name;
	private List<Measure> measures;
	private List<DimensionUsage> dimensionUsages;

	public Cubo(String name){
		this.name = name;
		this.measures = new LinkedList<Measure>();
		this.dimensionUsages = new LinkedList<DimensionUsage>();
	}
	
	public void addMeasure(Measure measure){
		measures.add(measure);
	}
	
	public void addDimensionUsage(DimensionUsage du){
		dimensionUsages.add(du);
	}
	
	public List<Measure> getMeasures(){
		return measures;
	}
	
	public List<DimensionUsage> getDimensionUsage(){
		return dimensionUsages;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		String string = "CUBO:name: " + name + "\n";
		string = string.concat("MeasuresLIST:" + "\n");
		for (Measure p : measures) {
			string = 	string.concat(p.toString());
		}
		
		string = string.concat("DimensionUsagesLIST:" + "\n");
		for (DimensionUsage p : dimensionUsages) {
			string = string.concat(p.toString());
		}
		
		return string  + "\n";
	}
	
	public Map<Dimension,String> getColumnNames(){
		Map<Dimension,String> dim_col = new HashMap<Dimension,String>();
		for(DimensionUsage d: dimensionUsages){
			dim_col.put(d.getDimension(),d.getName());
		}
		
		return dim_col;
		
	}
}
