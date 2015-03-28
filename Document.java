package CompositePattern;

public class Document implements ForderComponent {
	private String name;
	private int size;
	private int num=1;

	public Document (String name1,int size){
		name=name1;
		this.size=size;
	}
	
	public int caculateSize(){
		return size;
	}

	public int numOfFiles(){
		return num;
	}
	public void display() {
		System.out.println(DiskFilingSystem.g_indent + name);	
	}
	
}
