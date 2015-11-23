package UML;

public class VisibilidadeService {

public static String ConverterParaPadraoDiagrama(String str){
	str = str.replace("public", "+");
	str = str.replace("private", "-");
	str = str.replace("protected", "#");
	return str;
	
}
}
