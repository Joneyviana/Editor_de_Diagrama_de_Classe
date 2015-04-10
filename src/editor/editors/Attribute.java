package editor.editors;

import java.util.regex.Matcher;

public class Attribute {
	public String name ;
	 public  String visibility ;
	 public String type ;
public Attribute(Matcher matcher){
	
	name =matcher.group("name");
    visibility = matcher.group("visibility").replace("public", "+").replace("private", "-").replace("protected", "#");
    type =matcher.group("type");
	 }
}
