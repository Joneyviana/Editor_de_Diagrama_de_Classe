package editor.editors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class diagrams {
	 private static diagrams instance = null;
     public HashMap<String, ArrayList<HashMap<String , Matcher>>> pacotes = new HashMap<>();
	 public static diagrams  getInstance() {
	      if(instance == null) {
	         instance = new diagrams();
	      }
	      return instance;
	   }

}
