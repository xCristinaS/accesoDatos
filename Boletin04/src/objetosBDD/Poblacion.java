package objetosBDD;

/**
 * Created by Cristina on 16/01/2016.
 */
public class Poblacion {

    private String poblacion;
    private Provincia provincia;
    private String pais;

    public Poblacion(String poblacion, Provincia provincia, String pais){
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.pais = pais;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getStringTypeT_Poblacion(){
        return String.format("('%s', %s, '%s')", poblacion, provincia.getStringTypeT_Provincia(), pais);
    }

    public static Poblacion newInstance(String cadenaPob){
        String aux[];
        cadenaPob = cadenaPob.replaceAll("\"", "");
        cadenaPob = cadenaPob.replaceAll("\\)", "");
        cadenaPob = cadenaPob.replaceAll("\\(", "");
        aux = cadenaPob.split(",");

        return new Poblacion("", new Provincia("", ""), "");
    }

    @Override
    public String toString() {
        return "Poblacion{" +
                "poblacion='" + poblacion + '\'' +
                ", provincia=" + provincia +
                ", pais='" + pais + '\'' +
                '}';
    }
}
