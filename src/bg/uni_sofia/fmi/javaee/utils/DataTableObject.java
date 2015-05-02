package bg.uni_sofia.fmi.javaee.utils;

import java.util.List;

public class DataTableObject<T> {
	
	private List<T> aaData;

	public DataTableObject(List<T> data) {
		this.aaData = data;
	}

	public List<T> getaData() {
		return aaData;
	}

}
