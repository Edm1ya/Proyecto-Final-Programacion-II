package Data;

import java.time.LocalDate;

/**
 * 
 * Class Attribute abstract para agrupar todas las enetidades de datos
 * Y servir de transporte del modelo
 * 
 * @author Edward Minaya
 *         Uso academico exclusivamente
 *         Class Entity abstract class agrupador
 *         tiene un utilitario estatico para leer la fecha del actual.
 * 
 */
public abstract class Entity {
    public static LocalDate getcurrentDate() {
        LocalDate today = LocalDate.now();
        return today;
    }
}