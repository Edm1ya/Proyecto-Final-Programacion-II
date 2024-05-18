package Controller;

import java.sql.Date;
import java.util.ArrayList;

import Data.Entity;
import Model.CountryModel;

public class CountryController extends Controller {
    private CountryModel currentModel;

    public CountryController() {
        this.currentModel = new CountryModel();
        super.DataModel = currentModel;
    }

    @Override
    public ArrayList<? extends Entity> Get() {
        return currentModel.Get();
    }

    @Override
    public ArrayList<? extends Entity> Get(short oid) {
        return currentModel.Get(oid);
    }

    @Override
    public ArrayList<? extends Entity> Get(String search) {
        return currentModel.Get(search);
    }

    @Override
    public ArrayList<? extends Entity> Get(String search, int linkval) {
        return currentModel.Get(search, linkval);
    }

    @Override
    public ArrayList<? extends Entity> Get(Date dtein, Date dteout) {
        return currentModel.Get(dtein, dteout);
    }

    @Override
    public boolean Post(Entity odata) {
        return currentModel.Add(odata);
    }

    @Override
    public boolean Put(Entity odata) {
        return currentModel.Update(odata);
    }

    @Override
    public boolean Delete(Entity odata) {
        return currentModel.Delete(odata);
    }

    @Override
    public String Serializer() {
        return currentModel.Serializer();
    }

    @Override
    public ArrayList<? extends Entity> getData() {
        return currentModel.getData();
    }

    @Override
    public String getMensaje() {
        return currentModel.getMessage();
    }

}