package sat.utils;

import java.util.ArrayList;
import java.util.Iterator;

import utils.Sys;

/**
 * Configuracion de literales.
 *
 * No puede ser solo un HashMap<Literal, Boolean>
 * porque necesitamos poder incrementar de una configuracion a la siguiente.
 * 
 * No puede ser solo un ArrayList<Literal> o ArrayList<Boolean>
 * porque necesitamos poder pasarle un literal y que devuelva un booleano.
 */
public class Config implements Iterator<Config>{
    
    ArrayList<String> literalNames;
    ArrayList<Boolean> rawValues;
    
    /** Constructor */
    public Config(ArrayList<String> literalNames){
        this.literalNames = literalNames;
        this.rawValues = new ArrayList<Boolean>();
        
        // A cada String, asignamos el valor false.
        for (String str : literalNames)
            rawValues.add(false);
    }
    
    /** Constructor 
     * @throws Exception */
    public Config(ArrayList<String> literals, ArrayList<Boolean> rawValues) throws Exception{
        if (literals.size() != rawValues.size()) 
            Sys.exception("Distinto numero de literales (%d) y raw values (%d)!", literals.size(), rawValues.size());
        this.literalNames = literals;
        this.rawValues = rawValues;
    }
    
    @Override
    public boolean hasNext() {
        // Si algun literal esta a false, se podra incrementar la configuracion
        // y por tanto existiran mas posibilidades (elementos).
        for (Boolean b : rawValues){
            if (b == false) return true;
        }
        // Todos los valores a true == 11111... == no hay mas posibilidades (elementos)! 
        return false;
    }

    @Override
    public Config next() {
        try {
            return returnSelfIncrement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Devuelve una version incrementada de si misma */
    private Config returnSelfIncrement() throws Exception{
        ArrayList<Boolean> newRawValues = this.rawValues;
        int i = newRawValues.size()-1;  // Nos ponemos al final.
        
        // Vamos de derecha a izquierda (<<--).
        while(i >= 0){
            // Ignoramos los 1s, pero paramos si encontramos un 0.
            if(newRawValues.get(i) == false) break;
            i--;
        }
        
        // Nos quedamos en el primer 0 desde la derecha. Lo cambiamos a 1.
        newRawValues.set(i, true);
        
        // Vamos de izquierda a derecha (-->>).
        while (i < newRawValues.size()){
            // Convertimos a 0s todos los 1s que antes ignoramos.
            newRawValues.set(i, false);
            i++;
        }
        
        return new Config(this.literalNames, newRawValues);
    }
    
    /*
     * Getters y setters.
     * Solo existen getters y setters conjuntos para evitar disparidad de datos.
     */
    
    public Boolean getValue(String literalName){
        return rawValues.get(literalNames.indexOf(literalName));
    }
}
