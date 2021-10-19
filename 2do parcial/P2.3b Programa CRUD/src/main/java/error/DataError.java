package error;

/***
 * Anuncia errores de tipo de datos ingresados en el formulario
 */
public class DataError extends Exception{
    public DataError(String msg) {
        super(msg);
    }
}
