package Controller;

import java.sql.Date;
import java.util.ArrayList;

import Data.Entity;

public interface iDataRouter {
    public ArrayList<? extends Entity> Get();

    public ArrayList<? extends Entity> Get(short oid);

    public ArrayList<? extends Entity> Get(String search);

    public ArrayList<? extends Entity> Get(String search, int linkval);

    public ArrayList<? extends Entity> Get(Date search, Date linkval);

    public boolean Post(Entity odata);

    public boolean Put(Entity odata);

    public boolean Delete(Entity odata);

    public String Serializer();

    public ArrayList<? extends Entity> getData();

    public String getMensaje();
}
