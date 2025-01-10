package controlador;

public class ErrorCargarBBDDException extends Exception {

  public ErrorCargarBBDDException(){
    super("Error al realizar la conexion con la base de datos");
  }

  public ErrorCargarBBDDException(String message) {
    super(message);
  }
}
