package Data;

import java.sql.Date;

public class City extends Entity {
    public short ID;
    public String Name;
    public short CountryId;
    public String ContryName;
    public Date UpdateDate;

    public City() {
    }

    public City(short id, String name, short countryId, String countryName, Date updateDate) {
        this.ID = id;
        this.Name = name;
        this.CountryId = countryId;
        this.ContryName = countryName;
        this.UpdateDate = updateDate;
    }
}
