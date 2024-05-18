package Model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import Data.Entity;

/**
 * 
 * Interface general de modelo para los hijos Entity que se comunicaran
 * Con los consumidores y modelaran los atributos de cada entidad
 * 
 * @author Edward Minaya
 *         Uso academico exclusivamente
 *         Interface iORMObject para definir las operaciones estanndar que debe
 *         cumplicar cada objeto
 *         A nivel de Data y los mapping para el modelo CRUD ORM
 * 
 */
public interface iORMObject {
    public void Mapping(ResultSet rSet);

    public HashMap<String, String> SerializerMap(Entity odata);

    public ArrayList<? extends Entity> Get();

    public boolean Add(Entity odata);

    public boolean Update(Entity odata);

    public boolean Delete(Entity odata);

    public String Serializer(); // json {...}

    public Entity inMemSearch(Object pid);

    public ArrayList<? extends Entity> getData();

    public ArrayList<? extends Entity> Get(Object id);

    public ArrayList<? extends Entity> Get(String search, Object fklink);

    public ArrayList<? extends Entity> Get(String search);

    public ArrayList<? extends Entity> Get(Date dtein, Date dteout);

}