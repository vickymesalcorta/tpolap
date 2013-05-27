package olap;

import java.util.SortedSet;
import java.util.TreeSet;

public class Hierachy {

	private String name;
	private SortedSet<Level> levels;
	
	public Hierachy(String name){
		this.name = name;
		this.levels = new TreeSet<Level>() ;
	}
	
	public SortedSet<Level> getLevels(){
		return this.levels;
	}
	
	public void addLevel(Level level){
		levels.add(level);
	}
	
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		String string = "HIERACHY:name: "+name+ "\n";
		string = string.concat("levels" + "\n");
		for (Level p : levels) {
			string = string.concat(p.toString());
		}
		return string  + "\n";
	}
}
