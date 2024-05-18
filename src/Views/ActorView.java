package Views;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.ActorController;
import Data.Actor;
import Data.Entity;

public class ActorView extends View {
    public ActorView(Scanner pin) {
        super.oScanner = pin;
        super.elcontol = new ActorController();
    }

    public Entity Show() {
        int option = -1;
        Actor oActor = null;
        elcontol.Get();
        ArrayList<Actor> listModel = (ArrayList<Actor>) elcontol.getData();
        System.out.println(listModel);
        do {
            mostrarList(listModel);
            option = getOption();
            if (option < 0) {
                elcontol.Get(Refinar());
                listModel = (ArrayList<Actor>) elcontol.getData();
            } else if (option > 0 && option <= listModel.size()) {
                oActor = (Actor) elcontol.getData().get(option - 1);
                break;
            }
        } while (option != 0);

        return oActor;
    }

    private void mostrarList(ArrayList<Actor> listModel) {
        System.out.println("No.| ID     |Name           |LastName       |Last Mod. ");
        System.out.println("---|--------|---------------|---------------|----------");
        Short k = 1;
        for (Actor one : listModel) {
            System.out.print(View.getDisplayFill(k, 3));
            System.out.print("|" + View.getDisplayFill(one.ID, 5));
            System.out.print("|" + View.getDisplayFill(one.FirstName, 20));
            System.out.print("|" + View.getDisplayFill(one.LastName, 20));
            System.out.print("|" + View.getDisplayFill(one.UpdateDate, 12));
            System.out.println("---|-----------------|------------ ");
            k++;
        }
    }

    @Override
    public void Show(Entity omodel) {
        // TODO Auto-generated method Stub
        if (!(omodel != null && omodel instanceof Actor))
            return;

        Actor oActor = (Actor) omodel;
        System.out.println("     ID: " + oActor.ID);
        System.out.println("     Name: " + oActor.FirstName);
        System.out.println("     Name: " + oActor.LastName);
        System.out.println("      Last Mod.: " + oActor.UpdateDate);
        return;
    }

    @Override
    public Entity Capture() {
        // TODO Auto-generated method Stub
        Actor oPais = new Actor();
        System.out.println(" ID : <automatic ");
        // oPais.ID = oScan.nextShort();
        System.out.print("     Name : ");
        oScanner.nextLine();
        oPais.FirstName = oScanner.nextLine();
        System.out.print("Last Name: ");
        oPais.LastName = oScanner.nextLine();
        oScanner.nextLine();
        System.out.print("Last Mod. : " + Entity.getcurrentDate());
        SaveChanges(EnumActionView.PostNewRec, oPais);
        return oPais;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Entidad Actor";
    }

    @Override
    public Entity Capture(Entity omodel) {
        // TODO Auto-generated method Stub
        if (!(omodel != null && omodel instanceof Actor))
            return null;

        Actor oPais = (Actor) omodel;
        System.out.println("   ID:" + oPais.ID);
        System.out.print("   Name: " + oPais.FirstName);
        System.out.print(" New?: ");
        /* oScanner. nextLine() ; */
        oPais.FirstName = oScanner.next();
        System.out.print("Last Name: " + oPais.LastName);
        System.out.print(" Last New?: ");
        /* oScanner.next(); */
        oPais.LastName = oScanner.next();
        System.out.println("Last Mod. : " +
                Entity.getcurrentDate());
        SaveChanges(EnumActionView.PutUpdateRec, oPais);
        return oPais;
    }
}