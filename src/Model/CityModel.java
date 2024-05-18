package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import Data.Actor;
import Data.City;
import Data.Entity;

public class CityModel extends EntityModel implements iORMObject {
    private ArrayList<City> allData;
    /*
     * Unico constructor
     * Sobrecarga el constructor mas avanzado de padre EntityMode1:
     * -Ob •eto: City
     * -PK: city_id
     * -Search Col: actor
     * -FK Col: no tiene FKs
     * -Order by: actor
     */

    public CityModel() {
        super("City", "city_id", "concat(city)", "", "city", "last_update");
    }

    /**
     * Recibe la data de search y la pasa al List<City>
     * Record by Records
     * 
     * @param rSet ResultSet que recibe la data del Search
     */
    @Override
    public void Mapping(ResultSet rSet) {
        allData = null; // destroy before list
        allData = new ArrayList<City>();
        try {
            while (rSet.next()) {
                City objcCity = new City();
                objcCity.ID = rSet.getShort("city_id");
                objcCity.Name = rSet.getString("city");
                objcCity.ContryName = rSet.getString("country_id");
                objcCity.UpdateDate = rSet.getDate("last_update");
                allData.add(objcCity);
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
     * @return ArrayList<? extends Entity> de City (alldata)
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
     * @return ArrayList<? extends Entity> de City (alldata)
     */
    @Override
    public ArrayList<City> Get(Object id) {
        // TODO Auto-generated method stub
        Mapping(super.Find(id));
        return allData;
    }

    /**
     * Invoca el padre Find(texto, object) por busqueda like abierta
     * 
     * @return ArrayList<? extends Entity> de City (alldata)
     */
    public ArrayList<City> Get(String search) {
        // TODO Auto-generated method stub
        Mapping(super.Find(search));
        return allData;
    }

    /**
     * Invoca el padre Find(texto, object) por busqueda like abierta y por
     * un FK preestablecido en la construccion "city_id"
     * 
     * @return ArrayList<? extends Entity> de City (alldata)
     */
    @Override
    public ArrayList<City> Get(String search, Object fkval) {
        // TODO Auto-generated method stub
        // throw new Exception("Esta entidad no posee search FK");
        return null;
    }

    /**
     * Invoca el padre Find(date, date) en un rango de fecha
     * Segun columna especificada en la construccion
     * Sdelorbe 2023-20 INF-515 LAB 008 DB ORM
     * 
     * @return ArrayList<? extends Entity> de City (alldata)
     */
    public ArrayList<City> Get(Date dtein, Date dteout) {
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
     * Recibe la entidad City y hace la actualizacion mediante el padre
     * super.Put
     * 
     * @return boolean indicando positivo o negativo
     */
    public ArrayList<City> Get(boolean pisfull) {
        // TODO Auto-generated method stub
        if (pisfull)
            Mapping(super.Find(true));
        else
            Mapping(super.Find());
        return allData;
    }

    /**
     * Recibe la entidad City y hace un nuevo mediante el padre
     * super.Put. Primero busca el Max city_id,
     * segun method en le padre geMaxID
     * 
     * @return boolean indicando positivo o negativo
     */
    @Override
    public boolean Add(Entity odata) {
        // TODO Auto-generated method stub
        // busca el ultimo PK y le suma 1;
        ((City) odata).ID = Short.parseShort(Long.toString(getMaxID() + 1));
        return super.Post(SerializerMap(odata));
    }

    /**
     * Recibe la entidad City y hace un delete padre
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
     * Pasa el City a un HaspMap, para poderselo pasar al padre como parametro
     * Universal de interface con este para C.U.D (Create o update, DELETE)
     * super.Put. NO IMPLEMENTADO AUN.
     * 
     * @return boolean indicando positivo o negativo
     *         Sdelorbe 2023-20 INF-515 LAB 008 DB ORM
     */
    @Override
    public HashMap<String, String> SerializerMap(Entity odata) {
        if (!(odata instanceof City))
            return null;

        City obj1 = (City) (odata);
        HashMap<String, String> paisObj = new HashMap<String, String>();
        paisObj.put("city_id", Integer.toString(obj1.ID));
        paisObj.put("city", obj1.Name);
        paisObj.put("country_id", Integer.toString(obj1.CountryId));
        paisObj.put("last_update", Entity.getcurrentDate().toString());
        return paisObj;
    }

    /**
     * Lleva la List<City> a Json string
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
        for (City octy : allData) {
            sb.append(separa + "{ID:" + octy.ID);
            sb.append(",Name:\"" + octy.Name);
            sb.append(",Country ID:\"" + octy.CountryId);
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
        City oPais = null;
        List<City> findPais = allData.stream() // convert list to stream
                .filter(octry -> octry.ID == Short.parseShort(pid.toString())) // we dont like mkyong
                .collect(Collectors.toList());
        if (findPais.size() >= 1)
            oPais = findPais.get(0);
        return oPais;
    }

    /**
     * Sdelorbe 2023-20 INF-515 LAB 008 DB ORM
     * getters/accessors con la data de la ultima busqueda list<City>
     * 
     * @return ArrayList<? extends Entity> buscada o null
     */
    @Override
    public ArrayList<? extends Entity> getData() {
        // TODO Auto-generated method stub
        return allData;
    }
}
