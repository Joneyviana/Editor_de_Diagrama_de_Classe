package editor.editors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class diagrams {
	 
	private static diagrams instance = null;
     public String pacote_incial = "" ;
	 public HashMap<String ,ArrayList<Representação_de_classe>> pacotes_salvos = new HashMap<>();
     public HashMap<String, ArrayList<HashMap<String , Matcher>>> pacotes = new HashMap<>();
	 public static diagrams  getInstance() {
	      if(instance == null) {
	         instance = new diagrams();
	      }
	      return instance;
	   }

 public ArrayList<Representação_de_classe> Key(String pacote){
	 if (pacotes_salvos.containsKey(pacote)){
		 return pacotes_salvos.get(pacote);
	 }
	 ArrayList<Representação_de_classe> lista_de_classe = new ArrayList<>();
	 
	 HashMap<String ,ArrayList<String>> pacote_conteudo ;
	 for (HashMap<String, Matcher> chave : pacotes.get(pacote)){
		 Representação_de_classe classe = new Representação_de_classe();
		 Matcher matcher = chave.get("class");   
		   if (matcher.find()){
			   classe.name = matcher.group("name");
		   }
         matcher = chave.get("attribute")  ;
         while(matcher.find()){
        	
        	 classe.atributos.add(new Attribute(matcher));
         }
         lista_de_classe.add(classe);
	     pacotes_salvos.put(pacote , lista_de_classe);
	 }
   return lista_de_classe ;
 }
 }
