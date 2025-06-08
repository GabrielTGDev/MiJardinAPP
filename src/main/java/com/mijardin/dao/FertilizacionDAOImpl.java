package com.mijardin.dao;

import com.mijardin.entities.Fertilizacion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class FertilizacionDAOImpl implements FertilizacionDAO {
    private final SessionFactory sessionFactory;

    public FertilizacionDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Fertilizacion save(Fertilizacion fertilizacion) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(fertilizacion);
            transaction.commit();
            return fertilizacion;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public Fertilizacion findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Fertilizacion.class, id);
        }
    }

    @Override
    public List<Fertilizacion> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Fertilizacion", Fertilizacion.class).list();
        }
    }

    @Override
    public void update(Fertilizacion fertilizacion) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(fertilizacion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Fertilizacion fertilizacion) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(fertilizacion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}
