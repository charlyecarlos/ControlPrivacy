package recursos;

public class DbQuery {
	private static final String modificaruser = "update USU " +
													"set pwd= ? ," +
													" type= ? ," +
													"email= ? ," +
													"pregunta= ? ," +
													"recordatorio= ? ," +
													"accesosfallidos= ? ," +
													"bloqueado= ? ,activo= ?  where user=?";

	private static final String borraruser = "delete usu where user= ?";

	private static final String todosusers = "select user," +
													"pwd ," +
													" type," +
													"email," +
													"pregunta," +
													"recordatorio," +
													"accesosfallidos," +
													"bloqueado," +
													"activo " +
												    " from usu " ;

	private static final String RecuperarTypeUser = "Select type, descripcion from  typeuser where type=?";
			
	private static String insertarTypeUser = "INSERT INTO typeuser" +
			" (type, descripcion) VALUES (?,?)";
	private static String todosTypeUser = "Select type, descripcion from  typeuser";
	private  static String InsertarUser="INSERT INTO users(" +
											"us," +
											"pwd ," +
											"type," +
											"email," +
											"question," +
											"answer," +
											"accessfail," +
											"locked,active)"+
											" VALUES(?,?,?,?,?,?,?,?,?)";
	private  static String recuperaruser="select us," +
													"pwd, " +
													"type," +
													"email," +
													"question," +
													"answer," +
													"accessfail," +
													"locked,active " +
												" from users " +
												" where us=? and active='S'";
	

	


	public static String getInsertarTypeUser() {
		// TODO Auto-generated method stub
		return DbQuery.insertarTypeUser ;
	}

	
	public static String getTodosTypeUser() {
		// TODO Auto-generated method stub
		return todosTypeUser;
	}
	public static String getInsertarUser() {
		return InsertarUser;
	}
	public static String getRecuperarUser() {
		
		 return recuperaruser;
	}
	public static String getModificarUser() {
		
		 return modificaruser;
	}


	public static String getBorrarUser() {
		
		return borraruser;
	}


	public static String getTodosUser() {
		// TODO Auto-generated method stub
		return todosusers;
	}


	public static String getRecuperarTypeUser() {
		// TODO Auto-generated method stub
		return RecuperarTypeUser;
	}
}
