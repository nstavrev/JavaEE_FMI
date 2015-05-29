package bg.uni_sofia.fmi.javaee.util;
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
	private Long value;
	
	public DonutChartData(String label, Long value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
}
