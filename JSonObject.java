
public class JSonObject {
	private JSonObject[] children;
	private String name;
	private String content;
	private int size;
	
	public JSonObject(String name, String content){
		this.name = name;
		this.content = content;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public int getSize(){
		return this.size;
	}
	
	
	public JSonObject getChild(int i){
		return this.children[i];
	}
	
	
	public void addChild(JSonObject j, int i){
		children[i] = j;
	}
	
	public void listChild(){
		for(int i = 0; i < size;i++){
			System.out.println(i + " " + children[i].getContent());
		}
	}
	
	public void createChildrenArray(String[][] s){
		this.children = new JSonObject[s[0].length];
		this.size = s[0].length;
		for(int i = 0; i < s[0].length;i++){
			children[i] = new JSonObject(s[0][i], s[1][i]);
		}
	}
	
	public JSonObject searchChild(String name){
		int i = 0;
		while(i < size && children[i] != null){
			if(children[i].getName().equals(name)){
				return children[i];
			}
			i++;
		}
		return null;
	}
}
