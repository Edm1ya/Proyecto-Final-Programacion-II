package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import Data.Actor;
import Data.Entity;

public class ActorModel extends EntityModel implements iORMObject {
    private ArrayList<Actor> allData;
    /*
     * Unico constructor
     * Sobrecarga el constructor mas avanzado de padre EntityMode1:
     * -Ob •eto: Actor
     * -PK: actor_id
     * -Search Col: actor
     * -FK Col: no tiene FKs
     * -Order by: actor
     */

    public ActorModel() {
        super("Actor", "actor_id", "concat(last_name, first_name)", "", "last_name, first_name", "last_update");
    }

    /**
     * Recibe la data de search y la pasa al List<Actor>
     * Record by Records
     * 
     * @param rSet ResultSet que recibe la data del Search
     */
    @Override
    public void Mapping(ResultSet rSet) {
        allData = null; // destroy before list
        allData = new ArrayList<Actor>();
        try {
            while (rSet.next()) {
                Actor objactor = new Actor();
                objactor.ID = rSet.getShort("actor_id");
                objactor.FirstName = rSet.getString("first_name");
                objactor.LastName = rSet.getString("last_name");
                objactor.UpdateDate = rSet.getDate("last_update");
                allData.add(objactor);
            }
            rSet.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            super.ActionMessage = e.getMessage();

        }
    }

    /**
     * Invoca el padre Find() basico sin parametros
     * 
     * @return ArrayList<? extends Entity> de Actor (alldata)
     */
    @Override
    public ArrayList<? extends Entity> Get() {
        // TODO Auto-generated method stub
        Mapping(super.Find());
        return allData;
    }

    /**
     * Invoca el padre Find(PK) por primary key
     * 
     * @return ArrayList<? extends Entity> de Actor (alldata)
     */
    @Override
    public ArrayList<Actor> Get(Object id) {
        // TODO Auto-generated method stub
        Mapping(super.Find(id));
        return allData;
    }

    /**
     * Invoca el padre Find(texto, object) por busqueda like abierta
     * 
     * @return ArrayList<? extends Entity> de Actor (alldata)
     */
    public ArrayList<Actor> Get(String search) {
        // TODO Auto-generated method stub
        Mapping(super.Find(search));
        return allData;
    }

    /**
     * Invoca el padre Find(texto, object) por busqueda like abierta y por
     * un FK preestablecido en la construccion "Actor_id"
     * 
     * @return ArrayList<? extends Entity> de Actor (alldata)
     */
    @Override
    public ArrayList<Actor> Get(String search, Object fkval) {
        // TODO Auto-generated method stub
        // throw new Exception("Esta entidad no posee search FK");
        return null;
    }

    /**
     * Invoca el padre Find(date, date) en un rango de fecha
     * Segun columna especificada en la construccion
     * Sdelorbe 2023-20 INF-515 LAB 008 DB ORM
     * 
     * @return ArrayList<? extends Entity> de Actor (alldata)
     */
    public ArrayList<Actor> Get(Date dtein, Date dteout) {
        // TODO Auto-generated method stub
        Mapping(super.Find(dtein, dteout));
        return allData;
    }

    @Override
    public boolean Update(Entity odata) {
        // TODO Auto-generated method stub
        return super.Put(SerializerMap(odata));
    }

    /**
     * Recibe la entidad Actor y hace la actualizacion mediante el padre
     * super.Put
     * 
     * @return boolean indicando positivo o negativo
     */
    public ArrayList<Actor> Get(boolean pisfull) {
        // TODO Auto-generated method stub
        if (pisfull)
            Mapping(super.Find(true));
        else
            Mapping(super.Find());
        return allData;
    }

    /**
     * Recibe la entidad Actor y hace un nuevo mediante el padre
     * super.Put. Primero busca el Max Actor_id,
     * segun method en le padre geMaxID
     * 
     * @return boolean indicando positivo o negativo
     */
    @Override
    public boolean Add(Entity odata) {
        // TODO Auto-generated method stub
        // busca el ultimo PK y le suma 1;
        ((Actor) odata).ID = Short.parseShort(Long.toString(getMaxID() + 1));
        return super.Post(SerializerMap(odata));
    }

    /**
     * Recibe la entidad Actor y hace un delete padre
     * super.Put. NO IMPLEMENTADO AUN.
     * 
     * @return boolean indicando positivo o negativo
     */
    @Override
    public boolean Delete(Entity odata) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Pasa el Actor a un HaspMap, para poderselo pasar al padre como parametro
     * Universal de interface con este para C.U.D (Create o update, DELETE)
     * super.Put. NO IMPLEMENTADO AUN.
     * 
     * @return boolean indicando positivo o negativo
     *         Sdelorbe 2023-20 INF-515 LAB 008 DB ORM
     */
    @Override
    public HashMap<String, String> SerializerMap(Entity odata) {
        if (!(odata instanceof Actor))
            return null;

        Actor obj1 = (Actor) (odata);
        HashMap<String, String> paisObj = new HashMap<String, String>();
        paisObj.put("actor_id", Integer.toString(obj1.ID));
        paisObj.put("first_Name", obj1.FirstName);
        paisObj.put("last_Name", obj1.LastName);
        paisObj.put("last_update", Entity.getcurrentDate().toString());
        return paisObj;
    }

    /**
     * Lleva la List<Actor> a Json string
     * Basic Serializer.... cuando hay composicion hay que mejorar
     * Esto se hace con Dynamic class loader invocation o reflexion.
     * 
     * @return String con el formato json
     */
    @Override
    public String Serializer() {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder("[");
        char separa = ' ';
        for (Actor octy : allData) {
            sb.append(separa + "{ID:" + octy.ID);
            sb.append(",Name:\"" + octy.FirstName);
            sb.append(",Last Name:\"" + octy.LastName);
            sb.append(",UpdateDate:\"" + octy.UpdateDate);
            sb.append("\"}");
            separa = ',';
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * Busqueda en Memoria sobre el List<>
     * Landa basico forech and filter
     * Esta es la gran tendencia, para no tener que ir tantas veces
     * Al oltp.
     * 
     * @return Entity buscada o null
     */
    @Override
    public Entity inMemSearch(Object pid) {
        // TODO Auto-generated method stub
        Actor oPais = null;
        List<Actor> findPais = allData.stream() // convert list to stream
                .filter(octry -> octry.ID == Short.parseShort(pid.toString())) // we dont like mkyong
                .collect(Collectors.toList());
        if (findPais.size() >= 1)
            oPais = findPais.get(0);
        return oPais;
    }

    /**
     * Sdelorbe 2023-20 INF-515 LAB 008 DB ORM
     * getters/accessors con la data de la ultima busqueda list<Actor>
     * 
     * @return ArrayList<? extends Entity> buscada o null
     */
    @Override
    public ArrayList<? extends Entity> getData() {
        // TODO Auto-generated method stub
        return allData;
    }
}
