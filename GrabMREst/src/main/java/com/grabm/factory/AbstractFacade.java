package com.grabm.factory;

import com.grabm.entity.Administrator;
import com.grabm.listener.GrabMContextListener;
import com.grabm.util.GrabMConstant;
import static com.grabm.util.GrabMConstant.ENTITY_EXCEPTION_UPDATE;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sachith Dickwella
 * @param <T>
 */
public abstract class AbstractFacade<T>
        implements GrabMConstant {

    /**
     * Log4j reference.
     */
    protected final Logger logger = Logger.getLogger(AbstractFacade.class);
    /**
     * Hibernate session factory instance.
     */
    protected final SessionFactory sessionFactory = GrabMContextListener.getSessionFactoryDashboard();
    /**
     * Facade class type reference.
     */
    protected final Class<T> entityClass;

    protected AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Statuses of entities.
     */
    protected enum Status {

        ACTIVE, INACTIVE, BLOCK, DISABLED, FIRST_LOGIN,
        SCHEDULED, DISPATCHED, NEXT_PICKUP, ARRIVED;

        @Override
        public String toString() {
            switch (this) {
                case ACTIVE:
                    return "A";
                case INACTIVE:
                    return "I";
                case BLOCK:
                    return "B";
                case DISABLED:
                    return "D";
                case FIRST_LOGIN:
                    return "F";
                case SCHEDULED:
                    return "S";
                case DISPATCHED:
                    return "P";
                case NEXT_PICKUP:
                    return "N";
                case ARRIVED:
                    return "V";
                default:
                    return "";
            }
        }
    }

    /**
     * Resource common create method.
     *
     * @param type
     * @return
     */
    public long create(T type) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            long id = (long) session.save(type);
            tx.commit();
            return id;
        } catch (Exception ex) {
            tx.rollback();
            logger.error(ENTITY_EXCEPTION_CREATE, ex);
            return -1;
        } finally {
            session.close();
        }
    }

    public int createBulk(List<T> types) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            types.stream().forEach((T type) -> {
                session.save(type);
            });
            session.flush();
            session.clear();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            logger.error(ENTITY_EXCEPTION_BULKCREATE, ex);
            return -1;
        } finally {
            session.close();
        }
        return 0;
    }

    public int update(T type) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(type);
            tx.commit();
            return 0;
        } catch (Exception ex) {
            tx.rollback();
            logger.error(ENTITY_EXCEPTION_UPDATE, ex);
            return -1;
        } finally {
            session.close();
        }
    }

    public int createOrUpdate(T type) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(type);
            tx.commit();
            return 0;
        } catch (Exception ex) {
            tx.rollback();
            logger.error(ENTITY_EXCEPTION_SAVE_OR_UPDATE, ex);
            return -1;
        } finally {
            session.close();
        }
    }

    public T get(long id) {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(entityClass);
            criteria.add(Restrictions.eq("id", id));
            List<T> types = criteria.list();
            if (types.size() > 0) {
                return types.get(0);
            }
        } catch (Exception ex) {
            logger.error(ENTITY_EXCEPTION_FETCH, ex);
        } finally {
            session.close();
        }
        return null;
    }

    public List<T> getAll(Status status) {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(entityClass);
            criteria.add(Restrictions.eq("status", status.toString().charAt(0)));
            return criteria.list();
        } catch (Exception ex) {
            logger.error(ENTITY_EXCEPTION_FETCH, ex);
        } finally {
            session.close();
        }
        return null;
    }

    public List<T> getAll() {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(entityClass);
            return criteria.list();
        } catch (Exception ex) {
            logger.error(ENTITY_EXCEPTION_FETCH, ex);
        } finally {
            session.close();
        }
        return null;
    }

    public int delete(T type) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(type);
            tx.commit();
            return 0;
        } catch (Exception ex) {
            tx.rollback();
            logger.error(ENTITY_EXCEPTION_DELETE, ex);
            return -1;
        }
    }

    public int status(long id, Status status) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(entityClass);
            criteria.add(Restrictions.eq("id", id));
            List<Administrator> list = criteria.list();
            if (list.size() > 0) {
                Administrator admininstrator = list.get(0);
                admininstrator.setStatus(status.toString().charAt(0));
                admininstrator.setLastUpdateDateTime(new Date());

                session.update(admininstrator);
                tx.commit();
                return 0;
            }
            return -2;
        } catch (Exception ex) {
            tx.rollback();
            logger.error(ENTITY_EXCEPTION_UPDATE, ex);
            return -1;
        }
    }
}
