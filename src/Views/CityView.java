package Views;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.CityController;
import Data.City;
import Data.Entity;

public class CityView extends View {
    public CityView(Scanner pin) {
        super.oScanner = pin;
        super.elcontol = new CityController();
    }

    public Entity Show() {
        int option = -1;
        City oCity = null;
        elcontol.Get();
        ArrayList<City> listModel = (ArrayList<City>) elcontol.getData();
        System.out.println(listModel);
        do {
            mostrarList(listModel);
            option = getOption();
            if (option < 0) {
                elcontol.Get(Refinar());
                listModel = (ArrayList<City>) elcontol.getData();
            } else if (option > 0 && option <= listModel.size()) {
                oCity = (City) elcontol.getData().get(option - 1);
                break;
            }
        } while (option != 0);

        return oCity;
    }

    private void mostrarList(ArrayList<City> listModel) {
        System.out.println("No.| ID     |City           |Country ID     |Last Mod. ");
        System.out.println("---|--------|---------------|---------------|----------");
        Short k = 1;
        for (City one : listModel) {
            System.out.print(View.getDisplayFill(k, 3));
            System.out.print("|" + View.getDisplayFill(one.ID, 5));
            System.out.print("|" + View.getDisplayFill(one.Name, 20));
            System.out.print("|" + View.getDisplayFill(one.CountryId, 20));
            System.out.print("|" + View.getDisplayFill(one.UpdateDate, 12));
            System.out.println("---|-----------------|--------------");
            k++;
        }
    }

    @Override
    public void Show(Entity omodel) {
        // TODO Auto-generated method Stub
        if (!(omodel != null && omodel instanceof City))
            return;

        City oCity = (City) omodel;
        System.out.println("     ID: " + oCity.ID);
        System.out.println("     City: " + oCity.Name);
        System.out.println("     Country ID: " + oCity.CountryId);
        System.out.println("      Last Mod.: " + oCity.UpdateDate);
        return;
    }

    @Override
    public Entity Capture() {
        // TODO Auto-generated method Stub
        City oPais = new City();
        System.out.println(" ID : <automatic ");
        // oPais.ID = oScan.nextShort();
        System.out.print("     Name : ");
        oScanner.nextLine();
        oPais.Name = oScanner.nextLine();
        System.out.print("Last Name: ");
        oPais.CountryId = oScanner.nextShort();
        oScanner.nextLine();
        System.out.print("Last Mod. : " + Entity.getcurrentDate());
        SaveChanges(EnumActionView.PostNewRec, oPais);
        return oPais;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Entidad City";
    }

    @Override
    public Entity Capture(Entity omodel) {
        // TODO Auto-generated method Stub
        if (!(omodel != null && omodel instanceof City))
            return null;

        City oPais = (City) omodel;
        System.out.println("   ID:" + oPais.ID);
        System.out.print("   City: " + oPais.Name);
        System.out.print(" New?: ");
        /* oScanner. nextLine() ; */
        oPais.Name = oScanner.next();
        System.out.print("Country ID: " + oPais.CountryId);
        System.out.print(" Last New?: ");
        /* oScanner.next(); */
        oPais.CountryId = oScanner.nextShort();
        System.out.println("Last Mod. : " +
                Entity.getcurrentDate());
        SaveChanges(EnumActionView.PutUpdateRec, oPais);
        return oPais;
    }
}