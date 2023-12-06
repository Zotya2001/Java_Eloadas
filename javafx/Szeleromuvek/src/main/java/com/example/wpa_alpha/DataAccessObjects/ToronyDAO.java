package com.example.wpa_alpha.DataAccessObjects;

import com.example.wpa_alpha.PersistenceModels.Torony;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;

public class ToronyDAO {

    /**
     * Visszaadja id alapján a megfelelő tornyot. Ha nincs olyan Torony akkor null értéket ad vissza.
     * @param id A kért Torony id-je
     * @return Torony object
     */
    public static Torony getToronyById(int id){
        DatabaseConn conn = new DatabaseConn(new Torony());
        return conn.getSession().get(Torony.class,id);
    }

    /**
     * Visszaadja az összes tornyot.
     * @return ArrayList<Torony>
     */
    public static ArrayList<Torony> getAllTorony(){
        DatabaseConn conn = new DatabaseConn(new Torony());
        CriteriaBuilder criteriaBuilder = conn.getSession().getCriteriaBuilder();
        CriteriaQuery<Torony> criteriaQuery = criteriaBuilder.createQuery(Torony.class);
        criteriaQuery.from(Torony.class);
        ArrayList<Torony> result = (ArrayList<Torony>) conn.getSession().createQuery(criteriaQuery).getResultList();

        /*
        * Session session = conn.getSession();
        Filter filter = session.enableFilter("filterTeljesitmeny");
        filter.setParameter("minTelj", 1900);
        * */
        return result;
    }

    /**
     * Átadott Tornyot hozzáadja az adatbázishoz.
     * @param item
     */
    public static void addTorony(Torony item){
        DatabaseConn conn = new DatabaseConn(new Torony());
        Transaction transaction = conn.getSession().beginTransaction();
        conn.getSession().save(item);
        transaction.commit();
    }

    /**
     * Átadott Tornyot felülírja/szerkeszti.
     * @param item
     */
    public static void updateTorony(Torony item){
        DatabaseConn conn = new DatabaseConn(new Torony());
        Transaction transaction = conn.getSession().beginTransaction();
        conn.getSession().update(item);
        transaction.commit();
    }

    /**
     * Átadott Tornyot törli.
     * @param item
     */
    public static void deleteTorony(Torony item){
        DatabaseConn conn = new DatabaseConn(new Torony());
        Transaction transaction = conn.getSession().beginTransaction();
        conn.getSession().remove(item);
        transaction.commit();
    }

    /**
     * Átadott Torony id-je alapján töröl.
     * @param id
     */
    public static void deleteTorony(int id){
        Torony item = ToronyDAO.getToronyById(id);
        DatabaseConn conn = new DatabaseConn(new Torony());
        Transaction transaction = conn.getSession().beginTransaction();
        conn.getSession().remove(item);
        transaction.commit();
    }

    /**
     * Tornyokat szűri egy Minimum teljesítmény szerint. Ha Torony teljesítménye nagyobb mint a min akkor átadja.
     * @param int min
     */
    public static ArrayList<Torony> filterByMinTeljesitmeny(int min){
        DatabaseConn conn = new DatabaseConn(new Torony());
        CriteriaBuilder criteriaBuilder = conn.getSession().getCriteriaBuilder();
        CriteriaQuery<Torony> criteriaQuery = criteriaBuilder.createQuery(Torony.class);
        criteriaQuery.from(Torony.class);
        Session session = conn.getSession();
        Filter filter = session.enableFilter("filterTeljesitmenyMin");
        filter.setParameter("minTelj", min);
        ArrayList<Torony> result = (ArrayList<Torony>) session.getSession().createQuery(criteriaQuery).getResultList();
        return result;
    }

    /**
     * Tornyokat szűri egy Maximum teljesítmény szerint. Ha Torony teljesítménye kisebb mint a max akkor átadja.
     * @param int max
     */
    public static ArrayList<Torony> filterByMaxTeljesitmeny(int max){
        DatabaseConn conn = new DatabaseConn(new Torony());
        CriteriaBuilder criteriaBuilder = conn.getSession().getCriteriaBuilder();
        CriteriaQuery<Torony> criteriaQuery = criteriaBuilder.createQuery(Torony.class);
        criteriaQuery.from(Torony.class);
        Session session = conn.getSession();
        Filter filter = session.enableFilter("filterTeljesitmenyMax");
        filter.setParameter("maxTelj", max);
        ArrayList<Torony> result = (ArrayList<Torony>) session.getSession().createQuery(criteriaQuery).getResultList();
        return result;
    }

    /**
     * Tornyokat szűri egy Minimum darab szerint. Ha Torony darabszáma nagyobb vagy egyenlő mint a min akkor átadja.
     * @param int min
     */
    public static ArrayList<Torony> filterByMinDarab(int min){
        DatabaseConn conn = new DatabaseConn(new Torony());
        CriteriaBuilder criteriaBuilder = conn.getSession().getCriteriaBuilder();
        CriteriaQuery<Torony> criteriaQuery = criteriaBuilder.createQuery(Torony.class);
        criteriaQuery.from(Torony.class);
        Session session = conn.getSession();
        Filter filter = session.enableFilter("filterDarabMin");
        filter.setParameter("minDarab", min);
        ArrayList<Torony> result = (ArrayList<Torony>) session.getSession().createQuery(criteriaQuery).getResultList();
        return result;
    }

    /**
     * Tornyokat szűri egy Maximum darab szerint. Ha Torony darabszáma kisebb vagy egyenlő mint a max akkor átadja.
     * @param int max
     */
    public static ArrayList<Torony> filterByMaxDarab(int max){
        DatabaseConn conn = new DatabaseConn(new Torony());
        CriteriaBuilder criteriaBuilder = conn.getSession().getCriteriaBuilder();
        CriteriaQuery<Torony> criteriaQuery = criteriaBuilder.createQuery(Torony.class);
        criteriaQuery.from(Torony.class);
        Session session = conn.getSession();
        Filter filter = session.enableFilter("filterDarabMax");
        filter.setParameter("maxDarab", max);
        ArrayList<Torony> result = (ArrayList<Torony>) session.getSession().createQuery(criteriaQuery).getResultList();
        return result;
    }
}
