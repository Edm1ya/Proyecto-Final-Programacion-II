package Data;

import java.sql.Date;

public class Actor extends Entity {
    public short ID;
    public String FirstName;
    public String LastName;
    public Date UpdateDate;

    public Actor() {
    }

    public Actor(short id, String firstName, String lastName, Date updateDate) {
        this.ID = id;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.UpdateDate = updateDate;
    }

}
