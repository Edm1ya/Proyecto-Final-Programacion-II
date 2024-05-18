package Model;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import Data.Country;
import Data.Entity;

/**
 * 
 * Class gestion ORM List Model de paises (Country entity)
 * 
 * @author Edward Minaya
 *         Uso academico exclusivamente
 *         Class CountryModel conectada a Entity<|--Country Data Atribute class
 *         Invoca el super (EntityModel) recibe el ResultSet lo mapea y cierra
 * 
 */
public class CountryModel extends EntityModel implements iORMObject {
    private ArrayList<Country> allData;

    /**
     * Unico constructor
     * Sobrecarga el constructor mas avanzado de padre EntityModel:
     * -Objeto: Country
     * -PK: country_id
     * -Search Col: country
     * -FK Col: "" -- no tiene FKs
     * - Order by: country
     */
    public CountryModel() {
        super("Country", "country_id", "country", "", "country", "last_update");
    }

    /**
     * Recibe la data de search y la pasa al List<Country>
     * Record by Records
     * 
     * @param rSet ResultSet que recibe la data del Search
     */
    @Override
    public void Mapping(ResultSet rSet) {
        short id;
        String sname;
        Date dt;
        allData = null; // destroy before list
        allData = new ArrayList<Country>();
        try {
            while (rSet.next()) {

                id = rSet.getShort("country_id");
                sname = rSet.getString("country");
                dt = rSet.getDate("last_update");
                Country objPais = new Country(id, sname, dt);
                allData.add(objPais);
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
     * @return ArrayList<? extends Entity> de Country (alldata)
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
     * @return ArrayList<? extends Entity> de Country (alldata)
     */
    @Override
    public ArrayList<Country> Get(Object id) {
        // TODO Auto-generated method stub
        Mapping(super.Find(id));
        return allData;
    }

    /**
     * Invoca el padre Find(texto, object) por busqueda like abierta
     * 
     * @return ArrayList<? extends Entity> de Country (alldata)
     */
    public ArrayList<Country> Get(String search) {
        // TODO Auto-generated method stub
        Mapping(super.Find(search));
        return allData;
    }

    /**
     * Invoca el padre Find(texto, object) por busqueda like abierta y por
     * un FK preestablecido en la construccion "country_id"
     * 
     * @return ArrayList<? extends Entity> de Country (alldata)
     */
    @Override
    public ArrayList<Country> Get(String search, Object fkval) {
        // TODO Auto-generated method stub
        // throw new Exception("Esta entidad no posee search FK");
        return null;
    }

    /**
     * Invoca el padre Find(date, date) en un rango de fecha
     * Segun columna especificada en la construccion
     * Sdelorbe 2023-20 INF-515 LAB 008 DB ORM
     * 
     * @return ArrayList<? extends Entity> de Country (alldata)
     */
    public ArrayList<Country> Get(Date dtein, Date dteout) {
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
     * Recibe la entidad Country y hace la actualizacion mediante el padre
     * super.Put
     * 
     * @return boolean indicando positivo o negativo
     */
    public ArrayList<Country> Get(boolean pisfull) {
        // TODO Auto-generated method stub
        if (pisfull)
            Mapping(super.Find(true));
        else
            Mapping(super.Find());
        return allData;
    }

    /**
     * Recibe la entidad Country y hace un nuevo mediante el padre
     * super.Put. Primero busca el Max Country_id,
     * segun method en le padre geMaxID
     * 
     * @return boolean indicando positivo o negativo
     */
    @Override
    public boolean Add(Entity odata) {
        // TODO Auto-generated method stub
        // busca el ultimo PK y le suma 1;
        ((Country) odata).ID = Short.parseShort(Long.toString(getMaxID() + 1));
        return super.Post(SerializerMap(odata));
    }

    /**
     * Recibe la entidad Country y hace un delete padre
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
     * Pasa el Country a un HaspMap, para poderselo pasar al padre como parametro
     * Universal de interface con este para C.U.D (Create o update, DELETE)
     * super.Put. NO IMPLEMENTADO AUN.
     * 
     * @return boolean indicando positivo o negativo
     *         Sdelorbe 2023-20 INF-515 LAB 008 DB ORM
     */
    @Override
    public HashMap<String, String> SerializerMap(Entity odata) {
        if (!(odata instanceof Country))
            return null;
        Country obj1 = (Country) (odata);
        HashMap<String, String> paisObj = new HashMap<String, String>();
        paisObj.put("country_id", Integer.toString(obj1.ID));
        paisObj.put("country", obj1.Name);
        paisObj.put("last_update", Entity.getcurrentDate().toString());
        return paisObj;
    }

    /**
     * Lleva la List<Country> a Json string
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
        for (Country octy : allData) {
            sb.append(separa + "{ID:" + octy.ID);
            sb.append(",Name:\"" + octy.Name);
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
        Country oPais = null;
        List<Country> findPais = allData.stream() // convert list to stream
                .filter(octry -> octry.ID == Short.parseShort(pid.toString())) // we dont like mkyong
                .collect(Collectors.toList());
        if (findPais.size() >= 1)
            oPais = findPais.get(0);
        return oPais;
    }

    /**
     * Sdelorbe 2023-20 INF-515 LAB 008 DB ORM
     * getters/accessors con la data de la ultima busqueda list<Country>
     * 
     * @return ArrayList<? extends Entity> buscada o null
     */
    @Override
    public ArrayList<? extends Entity> getData() {
        // TODO Auto-generated method stub
        return allData;
    }
}
