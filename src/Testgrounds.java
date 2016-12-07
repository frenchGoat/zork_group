
public class Testgrounds {

	public Testgrounds() {
		// TODO Auto-generated constructor stub
		
		
	}
	
	public static void main (String args[]) {
		NpcMaker maker = new NpcMaker();
		NPC v = maker.makeNpc("Vendor");
		
		System.out.println(v.getClass());
		
		
	}

}
