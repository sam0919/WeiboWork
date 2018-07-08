/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myHibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StringType;
import myHibernate.User;

/**
 *
 * @author Administrator
 */

public class DB_Helper {
    public boolean transmission_helper(Weibo weibo,String id){
    	SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session=sf.openSession();
        NativeQuery q=session.createSQLQuery("select count(*) from weibo");
        List<Long> count=q.getResultList();
        Weibo nmessage=new Weibo();
        nmessage.setMsgId(Integer.parseInt(String.valueOf(count.get(0))));
        nmessage.setId(id);
        nmessage.setImagePath(weibo.getImagePath());
        nmessage.setText(weibo.getText());
        session.beginTransaction();
        String sql="insert into weibo (msgId,id,imagePath,text) values ("+Integer.toString(nmessage.getMsgId())+","+id+","+nmessage.getImagePath()+","+nmessage.getText()+")";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        return true;
    }
    public boolean login_helper(String username,String password){
        List<User> userList = null;
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session=sf.openSession();
//        session.beginTransaction();
//        String sq="insert into user (id,code,nickname) values (\"6\",\"6\",\"hah\");";
//        session.createSQLQuery(sq).executeUpdate();
       
//      session.getTransaction().commit();
        String sql="select * from user u where u.id = '"+username+"' and u.code = '" + password+"';";
        System.out.println(sql);
        NativeQuery q=session.createSQLQuery(sql);//"FROM myHibernate.User u where u.id = "+username+" and u.code = " + password+";");
        userList = q.getResultList();
        // userList = q.list();
        //System.out.println("�����Ŀ:"+q.getSingleResult());
        //em.getTransaction().commit();
       // System.out.println("�����Ŀ:"+userList.size());
        //q.setString("id", username).setString("code", password);
      //  Query q=session.createQuery("from myHibernate.User where id=:p0 and code=p1");
       // System.out.println("lmjzs");
//        q.setParameter(0,username);
//        q.setParameter(1,password);
      //  System.out.println("lmjzs");
      if(userList.isEmpty()){
        	
            return false;
            
        }
        //System.out.println("lmj");
        return true;
    }
    public boolean register_helper(User user){
        List<User> userList = null;
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session=sf.openSession();
        NativeQuery q=session.createSQLQuery("select * from User where id="+user.getId());
        userList = (List<User>) q.getResultList();
         if(userList.isEmpty()){
        	session.beginTransaction();
        	String sql="insert into user (id,code,nickname) values ("+user.getId()+","+user.getCode()+","+"\'hi\'"+")";
        	session.createSQLQuery(sql).executeUpdate();
        	session.getTransaction().commit();
            return true;
        }
        return false;
    }
    public HashMap<String, String> homepage_helper(){
    	
        ArrayList<Weibo> weiboList = new ArrayList<Weibo>();
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session=sf.openSession();
        NativeQuery q=session.createSQLQuery("select * from weibo");
        weiboList=(ArrayList<Weibo>) q.getResultList();
        System.out.println(weiboList);
        HashMap<String, String> jsonData = new HashMap<String, String>();
        for(int i = 0; i < weiboList.size(); i++) {
      	  Weibo w = weiboList.get(i);
      	  jsonData.put("id", w.getId());
      	  jsonData.put("text", w.getText());
      	  jsonData.put("weibo_id", Integer.toString(w.getMsgId()));
      	  jsonData.put("img", w.getImagePath());
        }
        return jsonData;       
    }
    public String isFocus_helper(String id, String id1){
    	List<String> userList = null;
    	Session session = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        org.hibernate.query.Query  s=session.createQuery("select symbol from focus where id = ? and id1 = ?");
        s.setParameter(0, id);
        s.setParameter(1, id1);
        userList = (List<String>) s.list();
        if(userList.isEmpty()){
           return "-1";
       }
       return userList.get(0);
    }
    
    public List<String> myLikeList_helper(String id){
    	List<String> userList = null;
    	Session session = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query s=session.createQuery("select id1 from focus where id = ?");
        s.setParameter(0, id);
        userList = (List<String>) s.list();
        if(userList.isEmpty()){
        	userList.add("error");
        }
        return userList;
    }
    public List<String> fansList_helper(String id1){
    	List<String> userList = null;
    	Session session = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query s=session.createQuery("select id from focus where id1 = ?");
        s.setParameter(0, id1);
        userList = (List<String>) s.list();
        if(userList.isEmpty()){
        	userList.add("error");
        }
        return userList;
    }
    public boolean addMyLike(Focus focus){
    	List<String> userList = null;
    	Session session = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query s=session.createQuery("from Focus where symbol=?");
        s.setParameter(0, focus.getSymbol());
        session.save(focus);
        tx.commit();
        return true;
    }
    public boolean deleteMyLike(Focus focus){
    	List<String> userList = null;
    	Session session = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query s=session.createQuery("delete from Focus where symbol=?");
        s.setParameter(0, focus.getSymbol());
        session.save(focus);
        tx.commit();
        return true;
    }
    
    public int countWeibo(){
    	
    	SessionFactory sf=HibernateUtil.getSessionFactory();
    	Session session=sf.openSession();
    	NativeQuery q=session.createSQLQuery("select count(*) from Weibo");
        List<Long> count=q.getResultList();
        int num=Integer.parseInt(String.valueOf(count.get(0)));
        System.out.println(num);
        return num;
    }
    
    public void addWeibo(Weibo weibo){
    	
    	SessionFactory sf=HibernateUtil.getSessionFactory();
    	Session session=sf.openSession();
    	session.beginTransaction();
    	String sq="insert into weibo (msgId,id,imagePath,text) values ("+Integer.toString(weibo.getMsgId())+","+weibo.getId()+","+"\'"+weibo.getImagePath()+"\'"+","+"\'"+weibo.getText()+"\'"+")";
        session.createSQLQuery(sq).executeUpdate();
        session.getTransaction().commit();
    }
    public boolean commentHelp(Opinion op){
    	SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session=sf.openSession();
        session.beginTransaction();
        NativeQuery q=session.createSQLQuery("select count(*) from weibo");
        List<Long> count=q.getResultList();
        op.setSymbol(count.get(0).intValue()+1);
    	String sql="inser into user (msgID,id,comment,praise,symbol) values ("+op.getMsgId()+","+op.getId()+","+op.getComment()+","+op.getPraise()+","+op.getSymbol()+","+")";
    	session.createSQLQuery(sql).executeUpdate();
    	session.getTransaction().commit();
        return true;
    }
}
