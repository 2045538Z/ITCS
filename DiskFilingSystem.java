package CompositePattern;

public class DiskFilingSystem {
	public static StringBuffer g_indent = new StringBuffer();
	public static void main(String[] args) {
		
		ForderComponent file1 = new Document("File1",1);
		ForderComponent file2 = new Document("File2",2);
		ForderComponent file3 = new Document("File3",3);
		ForderComponent file4 = new Document("File4",4);
		
		ForderSite Forder1 = new ForderSite("Forder1: ");
		ForderSite Forder2 = new ForderSite("Forder2: ");
	    ForderSite Forder3 = new ForderSite("Forder3: ");
		

		Forder1.add(file3);
		Forder1.add(file4);
		Forder1.add(Forder2);
		Forder2.add(file1);
		Forder2.add(Forder3);
		Forder3.add(file2);
		
		Forder1.display();
		

		System.out.println("Forder1 Total Size: " + Forder1.caculateSize());
		System.out.println("Forder1 HOW MANY files: " + Forder1.numOfFiles());
		
		

		
	}

}
