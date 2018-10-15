package entity;

import java.util.List;

public class Car {
	private int id;
	private Double total;
	private int allNum;
	
	private List<CarDetail> cds;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public int getAllNum() {
		return allNum;
	}

	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}

	public List<CarDetail> getCds() {
		return cds;
	}

	public void setCds(List<CarDetail> cds) {
		this.cds = cds;
	}

}
