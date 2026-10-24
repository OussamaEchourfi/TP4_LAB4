package services;

import dao.IDao;
import entities.Salle;
import java.util.List;

public class SalleService implements IDao<Salle> {
    private IDao<Salle> dao = new dao.SalleDao();

    @Override
    public boolean create(Salle o) {
        return dao.create(o);
    }

    @Override
    public boolean delete(Salle o) {
        return dao.delete(o);
    }

    @Override
    public boolean update(Salle o) {
        return dao.update(o);
    }

    @Override
    public Salle getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<Salle> getAll() {
        return dao.getAll();
    }
}
