package Views;

import java.io.IOException;
import java.util.Scanner;

import Controller.Controller;
import Data.Entity;

/**
 * 
 * MVC Simple demostracion y abstraccion
 * Similar al modelo MVS Modelo, Vista, Controlador
 * 
 * @author Edward Minaya
 *         Uso academico exclusivamente
 *         Class View abstract eje de la interrface de la
 *         vista o UI.
 */

public abstract class View {
    // Fields potegidos para los hijos
    protected Scanner oScanner;
    protected Controller elcontol;

    // inteface abstract de la vista
    public abstract Entity Show();

    public abstract void Show(Entity omodel);

    public abstract Entity Capture();

    public abstract Entity Capture(Entity omodel);

    public abstract String getName();

    public static String getDisplayFill(Object val, int pad) {
        String rsult = val.toString();
        int N = rsult.length();
        for (int i = 0; i < pad - N; i++)
            rsult += ' ';
        return rsult;

    }

    protected boolean isConfirm(String message) {
        System.out.println(message + "[1-Yes; 0-No]?");

        short opt = oScanner.nextShort();
        return opt == 1;
    }

    protected int getOption() {
        System.out.println("Opciones");
        System.out.println("1-Elegir");
        System.out.println("; 2-Refinar");
        System.out.println("; 0-Retornar");
        System.out.println("\t\t Elija:");
        short opt = oScanner.nextShort();
        switch (opt) {
            case 1:
                System.out.println("\t\t Indique No: ");
                opt = oScanner.nextShort();
                return (opt);
            default:
                return (-opt);
        }

    }

    protected String Refinar() {
        System.out.println("Entre el Criterio(Texto):");
        String textosh = oScanner.next();
        return textosh;
    }

    protected boolean SaveChanges(EnumActionView action, Entity model) {
        boolean result = isConfirm("Desea Grabar los Cambios?");
        if (!result) {
            return result;

            switch (action) {
                case PostNewRec:
                    result = elControl.Post(model);
                    break;

                case PutUpdateRec:
                    result = elControl.Put(model);
                    break;

                case DeleteRec:
                    result = elControl.Delete(model);
                    break;

                default:
                    result = false;
            }

        }
    }
}
