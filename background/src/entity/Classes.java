package entity;

import java.util.List;

public class Classes {
	private int id;
	private String name;
	private List<MClass> mcList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MClass> getMcList() {
		return mcList;
	}
	public void setMcList(List<MClass> mcList) {
		this.mcList = mcList;
	}
	

}
