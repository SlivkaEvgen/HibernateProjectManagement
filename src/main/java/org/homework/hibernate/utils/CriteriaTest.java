//package org.homework.hibernate.utils;
//
//import org.hibernate.Session;
//import org.hibernate.query.criteria.JpaCriteriaQuery;
//import org.homework.hibernate.model.BaseModel;
//
//import java.io.Closeable;
//import java.io.IOException;
//import java.util.List;
//
//public class CriteriaTest<T extends BaseModel<ID>, ID> implements Closeable {
//
//    private final Class<T> modelClass;
//
//    SessionsOpenClose sessionsOpenClose = SessionsOpenClose.getInstance();
//
//    Session session = sessionsOpenClose.createSession();
//
//
//    CriteriaTest(Class<T> modelClass) {
//        this.modelClass = modelClass;
//    }
//
//    public List<T> getCriteria(){
//        JpaCriteriaQuery<T> query = session.getCriteriaBuilder().createQuery(modelClass);
//        List<T> resultList = session.createQuery(query.select(query.from(modelClass))).getResultList();
//        sessionsOpenClose.closeSession(session);
//        return resultList;
//    }
//
//
//
//    @Override
//    public void close() throws IOException {
//        session.close();
//    }
//}
