package com.mijardin.dao;

import com.mijardin.entities.Ubicacion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UbicacionDAOImpl implements UbicacionDAO {
    private final SessionFactory sessionFactory;

    public UbicacionDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Ubicacion save(Ubicacion ubicacion) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(ubicacion);
            transaction.commit();
            return ubicacion;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public Ubicacion findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Ubicacion.class, id);
        }
    }

    @Override
    public List<Ubicacion> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Ubicacion", Ubicacion.class).list();
        }
    }

    @Override
    public void update(Ubicacion ubicacion) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(ubicacion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Ubicacion ubicacion) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(ubicacion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}
