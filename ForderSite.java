package CompositePattern;

import java.util.ArrayList;

public class ForderSite implements ForderComponent {
	private String name;
	private ArrayList<ForderComponent> children;
	private int size = 0;
	private int num = 0;
	
	public ForderSite(String name1) {
		children = new ArrayList<ForderComponent>();
		name = name1;
	}
	public void display() {
		System.out.println(DiskFilingSystem.g_indent + name);
	    DiskFilingSystem.g_indent.append("   ");
	     for (ForderComponent c: children){				  
			 c.display();
		}
		DiskFilingSystem.g_indent.setLength(DiskFilingSystem.g_indent.length() - 3);	
	}
	
	public int caculateSize() {
		for(ForderComponent c: children) {
			size += c.caculateSize();
		}
		return size;
	}
	
	public int numOfFiles() {
		for(ForderComponent c: children) {
			num += c.numOfFiles();
		}
		return num;
	}
	// Need add and remove methods
	// to add objects to the composite
	public void add(ForderComponent c) {	
		children.add(c);	 
	}
	
	public void remove(ForderComponent c) {
		children.remove(c);
		
	}
	
}
