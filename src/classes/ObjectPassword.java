package classes;

public class ObjectPassword {

	 private String ID, alias, password; 
	 

	 public ObjectPassword(String ID, String alias, String password) { 

        this.ID = ID; 
        this.alias = alias;
        this.password = password; 
    } 
	 
	 public String getID(){
		 return ID;
	 }
	 
	 public String getAlias(){
		 return alias;
	 }
	 
	 public String getPassword(){
		 return password;
	 }
}
