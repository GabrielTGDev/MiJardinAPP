package com.mijardin.dao;

import com.mijardin.entities.Riego;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class RiegoDAOImpl implements RiegoDAO {
    private final SessionFactory sessionFactory;

    public RiegoDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Riego save(Riego riego) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(riego);
            transaction.commit();
            return riego;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public Riego findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Riego.class, id);
        }
    }

    @Override
    public List<Riego> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Riego", Riego.class).list();
        }
    }

    @Override
    public void update(Riego riego) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(riego);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Riego riego) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(riego);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}
