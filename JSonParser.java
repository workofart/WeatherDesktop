
public class JSonParser {
	private JSonObject root;
	
	public JSonParser(Query data){
		root = new JSonObject("root", data.toString());
		parse(root);
	}
	
	private void parse(JSonObject j){
		int size = calcNumSub(j);
		//System.out.println(size);
		if(size == 0) return;
		else{
			j.createChildrenArray(dissembleString(j, size));
			for(int i = 0; i < size; i++){
					parse(j.getChild(i));
			}
			
		}
	}

	private String[][] dissembleString(JSonObject j, int size){
		String[][] result = new String[2][size];
		int i = 0;
		String content = j.getContent();
		//System.out.println(content);
		content = content.replaceAll(" ", "");
		if(content.charAt(0) == '['){
			//System.out.println(content.length());
			content = rewriteArray(content);
			//System.out.println(content.length());
		}
		else{
			content = content.substring(0,content.length()-1) + ",";
		}
		//System.out.println(content);
		int start = 1, colon = 1, comma = 1;
		while(i < size){
			colon = colonPos(content, start);
			comma = commaPos(content, colon);
			//System.out.println(start+" "+colon+" "+ comma);
			result[0][i] = content.substring(start+1, colon-1);
			result[1][i] = content.substring(colon+1, comma);
			start = comma + 1;
			i++;
		}
		return result;
	}
	
	private String rewriteArray(String s){
		int i = 5, stack = 0, count = 1;
		s = s.substring(1, s.length()-1);;
		s = "{\"0\":" + s;
		while(i < s.length()){
			//System.out.println(s.length());
			switch(s.charAt(i)){
			case '{':
				stack++;
				break;
			case '}':
				stack--;
				break;
			case ',':
				if(stack == 0){
					String a = s.substring(0, i+1), b = s.substring(i+1, s.length());
					s =  a + "\"" + count + "\":" + b;
					i += ("\""+count+"\":").length();
					count++;	
				}
			}
			i++;
		}
		//System.out.println(s+",");
		return s+",";
	}
	
	private int colonPos(String s, int start){
		int i = start;
		loop:
		while(i < s.length()){
			if(s.charAt(i) == ':'){
				break loop;
			}
			i++;
		}
		return i;
	}
	
	private int commaPos(String s, int start){
		int i = start, stack = 0, bstack = 0;
		loop:
		while(i < s.length()){
			switch(s.charAt(i)){
			case '[':
				bstack ++;
				break;
			case ']':
				bstack --;
				break;
			case '{':
				stack++;
				break;
			case '}':
				stack--;
				break;
			case ',':
				if(stack == 0 && bstack == 0) break loop;
			}
			i++;
		}
		return i;
	}
	private int calcNumSub(JSonObject j){
		int i = 1, stack = 0, result = 0, quota = 0;
		String content = j.getContent();
		//System.out.println(content);
		content = content.replaceAll(" ", "");
		if(content.charAt(0) == '"') return 0;
		//System.out.println(content);
		if(content.charAt(0) == '['){
			content = rewriteArray(content);
		}
		while(i < content.length()){
			char c = content.charAt(i);
			//System.out.print(c);
			switch(c){
			case '{':
				stack++;
				break;
			case '}':
				stack--;
				break;
			case '"':
				quota++;
				break;
			case ':':
				if(stack == 0 && quota%2 == 0) result++;
				break;
			}
			i++;
		}
		//System.out.println(result);
		return result;
	}
	
	public JSonObject findObject(String path){
		JSonObject current = root;		
		while(!path.equals("") && current != null){
			int i = 0;
			while(i < path.length() && path.charAt(i) != '|') i++;
			//System.out.println(i);

			current = current.searchChild(path.substring(0, i));
			//System.out.println(current.getName());
			if(i+1 < path.length()){
				path = path.substring(i+1,path.length());
			}
			else{
				path ="";
			}
			//System.out.println(path);
		}
		return current;			
	}
	
	public JSonObject findObjectByNumber(String path){
		JSonObject current = root;		
		while(!path.equals("") && current != null){
			current = current.getChild(Integer.parseInt(path.substring(0,2)));
			path = path.substring(2,path.length());
		}
		return current;			
	}
	

	public static void main(String[] args){
		Query getter = new Query("London,ca",0);
		JSonParser test = new JSonParser(getter);
		System.out.println(test.findObject("sys|sunrise").getContent());
		System.out.println(test.findObject("coord").getContent());
		System.out.println(test.findObject("sys|country").getContent());
		System.out.println(test.findObject("weather").getContent());
		System.out.println(test.findObject("weather|0").getContent());
		System.out.println(test.findObject("weather|0|id").getContent());
		System.out.println(test.findObject("base").getContent());
		System.out.println(test.findObject("main|temp").getContent());
		System.out.println(test.findObject("wind|deg").getContent());
		System.out.println(test.findObject("clouds|all").getContent());
		 
		
		/*Query getter = new Query(null,3);
		JSonParser test = new JSonParser(getter);
		System.out.println(test.findObject("report|sunrise").getContent());
		System.out.println(test.findObject("report|terrestrial_date").getContent());
		System.out.println(test.findObject("report").getContent());
		System.out.println(test.findObject("report|max_temp").getContent());
		*/
		
		/*Query getter = new Query("London,uk",1);
		JSonParser test = new JSonParser(getter);
		System.out.println(getter);
		System.out.println(test.findObject("cod").getContent());
		System.out.println(test.findObject("city|id").getContent());
		System.out.println(test.findObject("city|coord|lon").getContent());
		System.out.println(test.findObject("cnt").getContent());
		System.out.println(test.findObject("list").getContent());*/
	}
}
