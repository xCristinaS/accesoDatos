package objetosBDD;

/**
 * Created by Cristina on 16/01/2016.
 */
public class Direccion {

    private String calle;
    private String numero;
    private Poblacion poblacion;
    private String codPostal;

    public Direccion (String calle, String numero, Poblacion poblacion, String codPostal){
        this.calle = calle;
        this.numero = numero;
        this.poblacion = poblacion;
        this.codPostal = codPostal;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Poblacion getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(Poblacion poblacion) {
        this.poblacion = poblacion;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getStringTypeT_Direccion(){
        return String.format("('%s', '%s', %s, '%s')", calle, numero, poblacion.getStringTypeT_Poblacion(), codPostal);
    }

    public static Direccion newInstance(String cadenaDir){
        String aux[];
        cadenaDir = cadenaDir.replaceAll("\"", "");
        cadenaDir = cadenaDir.replaceAll("\\)", "");
        cadenaDir = cadenaDir.replaceAll("\\(", "");
        aux = cadenaDir.split(",");
        return new Direccion(aux[0], aux[1], new Poblacion(aux[2], new Provincia(aux[3], aux[4]), aux[5]), aux[6]);
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", poblacion=" + poblacion +
                ", codPostal='" + codPostal + '\'' +
                '}';
    }
}
