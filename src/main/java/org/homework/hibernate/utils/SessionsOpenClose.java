package org.homework.hibernate.utils;

import org.hibernate.Session;

public class SessionsOpenClose {

    private static SessionsOpenClose sessionsOpenClose;

    public static SessionsOpenClose getInstance() {
        if (sessionsOpenClose == null) {
            synchronized (SessionsOpenClose.class) {
                if (sessionsOpenClose == null) {
                    sessionsOpenClose = new SessionsOpenClose();
                }
            }
        }
        return sessionsOpenClose;
    }

    public Session createSession() {
        Session openSession = HibernateSessionFactory.getSessionFactory().openSession();
        openSession.beginTransaction();
        return openSession;
    }

    public void closeSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }
}
