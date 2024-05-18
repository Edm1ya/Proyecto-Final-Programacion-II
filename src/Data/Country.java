package Data;

import java.util.Date;

public class Country extends Entity {
    public short ID;
    public String Name;
    public Date UpdateDate;

    public Country() {

    }

    public Country(short id, String name, Date updateDate) {
        this.ID = id;
        this.Name = name;
        this.UpdateDate = updateDate;
    }
}
