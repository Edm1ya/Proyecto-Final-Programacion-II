import java.util.Scanner;

import Data.Entity;
import Views.ActorView;
import Views.CityView;
import Views.CountryView;
import Views.View;

public class MenuControl {
    Scanner scan = null;
    View oView = null;

    public MenuControl(Scanner pin) {
        this.scan = pin;
    }

    public void Show() {
        short opt = 0;
        do {
            System.out.flush();
            System.out.println(" Menu Principal de Entidades: Renta Cine ");
            System.out.println("---------------------");
            System.out.println("1.- Gestor City");
            System.out.println("2.- Gestor Pais");
            System.out.println("3.- Gestor Actor");
            System.out.println("0.- Salir");
            System.out.println("---------------------");
            System.out.print("Elija una Opcion: ");
            opt = scan.nextShort();
            oView = null;
            InitView(opt);
        } while (opt != 0);
    }

    // private static void TextORMMVC(Scanner pin) {
    // MenuControl oMenu = new MenuControl(pin);
    // oMenu.Show();
    // }

    private void InitView(short pobj) {
        switch (pobj) {
            case 1:
                oView = new CityView(scan);
                break;
            case 2:
                oView = new CountryView(scan);
                break;
            case 3:
                oView = new ActorView(scan);
                break;

            case 0:
                System.out.println("Saliendo...");
                break;
        }
        if (oView == null)
            return;
        MostrarObj();
        return;
    }

    private void MostrarObj() {
        short opt = -1;
        do {
            System.out.flush();
            System.out.println(oView.getName());
            System.out.print(": 1.- Buscar");
            System.out.print("; 2.- Agregar");
            System.out.print("; 3.- Modificar");
            System.out.print("; 4.- Eliminar");
            System.out.println("; 0.- Retornar");
            System.out.println("---------------------");
            System.out.println("Elija Opcion: ");

            opt = scan.nextShort();
            EvalOpcion(opt);
        } while (opt != 0);
    }

    private void EvalOpcion(short popt) {
        switch (popt) {
            case 1:
                oView.Show();
                break;
            case 2:
                oView.Capture();
                break;
            case 3:
                Entity objUpdate = oView.Show();
                oView.Capture(objUpdate);
                break;
        }
    }
}
