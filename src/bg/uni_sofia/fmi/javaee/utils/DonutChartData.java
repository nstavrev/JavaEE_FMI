package bg.uni_sofia.fmi.javaee.utils;
/**
 * 
 * @author nstavrev
 *	Representing one donut chart data, which is used in index.jsp
 */
public class DonutChartData {
	
	/**
	 * The string label related to the value
	 */
	private String label;
	
	/**
	 * Value which is displayed in the chart
	 */
	private int value;
	
	public DonutChartData(String label, int value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
