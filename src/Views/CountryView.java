package Views;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.CountryController;
import Data.Country;
import Data.Entity;

public class CountryView extends View {
    public CountryView(Scanner pin) {
        super.oScanner = pin;
        super.elcontol = new CountryController();
    }

    public Entity Show() {
        int option = -1;
        Country oCountry = null;
        elcontol.Get();
        ArrayList<Country> listModel = (ArrayList<Country>) elcontol.getData();
        System.out.println(listModel);
        do {
            mostrarList(listModel);
            option = getOption();
            if (option < 0) {
                elcontol.Get(Refinar());
                listModel = (ArrayList<Country>) elcontol.getData();
            } else if (option > 0 && option <= listModel.size()) {
                oCountry = (Country) elcontol.getData().get(option - 1);
                break;
            }
        } while (option != 0);

        return oCountry;
    }

    private void mostrarList(ArrayList<Country> listModel) {
        System.out.println("No.| ID     |Country        |Last Mod. ");
        System.out.println("---|--------|---------------|----------");
        Short k = 1;
        for (Country one : listModel) {
            System.out.print(View.getDisplayFill(k, 3));
            System.out.print("|" + View.getDisplayFill(one.ID, 5));
            System.out.print("|" + View.getDisplayFill(one.Name, 20));
            System.out.print("|" + View.getDisplayFill(one.UpdateDate, 12));
            System.out.println("---|-----------------|------------ ");
            k++;
        }
    }

    @Override
    public void Show(Entity omodel) {
        // TODO Auto-generated method Stub
        if (!(omodel != null && omodel instanceof Country))
            return;

        Country oCountry = (Country) omodel;
        System.out.println("     ID: " + oCountry.ID);
        System.out.println("     Name: " + oCountry.Name);
        System.out.println("     Last Mod.: " + oCountry.UpdateDate);
        return;
    }

    @Override
    public Entity Capture() {
        // TODO Auto-generated method Stub
        Country oPais = new Country();
        System.out.println(" ID : <automatic ");
        // oPais.ID = oScan.nextShort();
        System.out.print("     Name : ");
        oScanner.nextLine();
        oPais.Name = oScanner.nextLine();
        oScanner.nextLine();
        System.out.print("Last Mod. : " + Entity.getcurrentDate());
        SaveChanges(EnumActionView.PostNewRec, oPais);
        return oPais;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Entidad Country";
    }

    @Override
    public Entity Capture(Entity omodel) {
        // TODO Auto-generated method Stub
        if (!(omodel != null && omodel instanceof Country))
            return null;

        Country oPais = (Country) omodel;
        System.out.println("   ID:" + oPais.ID);
        System.out.print("   Name: " + oPais.Name);
        System.out.print(" New?: ");
        /* oScanner. nextLine() ; */
        oPais.Name = oScanner.next();
        System.out.print("Name: " + oPais.Name);
        System.out.println("Last Mod. : " +
                Entity.getcurrentDate());
        SaveChanges(EnumActionView.PutUpdateRec, oPais);
        return oPais;
    }
}