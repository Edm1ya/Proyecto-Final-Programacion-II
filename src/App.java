import java.util.Scanner;

import Util.PropertyFile;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        MenuControl menuControl = new MenuControl(scanner);
        menuControl.Show();
    }
}
