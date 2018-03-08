public class sampleColumnWrapper {
	String sampleName = "";
	String sampleID = "";

	public sampleColumnWrapper(String name, String id) {
		this.sampleName = name;
		this.sampleID = id;
	}

	public String toString() {
		return this.sampleName + " " + sampleID + " \n";
	}
	
}