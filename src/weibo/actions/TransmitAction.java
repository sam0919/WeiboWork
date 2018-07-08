/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weibo.actions;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import myHibernate.DB_Helper;
import myHibernate.HibernateUtil;
import myHibernate.User;
import myHibernate.Weibo;

/**
 *
 * @author Administrator
 */
public class TransmitAction extends ActionSupport{
    private Weibo weibo=new Weibo();
    private String id;
    private int msgId;
    

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = (String) ActionContext.getContext().getSession().get("id");
    }
    
    public Weibo getWeibo(){
        return this.weibo;
    }
    public void setWeibo(Weibo weibo){
        this.weibo=weibo;
    }
    @Override
    public String execute(){
    	List<Weibo> weiboList = null;
    	SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session=sf.openSession();
        String sql="select imagePath from weibo w where w.id="+msgId;
        NativeQuery q=session.createSQLQuery(sql);
        weiboList = q.getResultList();
        weibo=weiboList.get(0);
        DB_Helper help=new DB_Helper(); 
        if( help.transmission_helper(weibo, id)){
            return SUCCESS;
        }
        return ERROR;
    }
    int getmsgId(){
    	return this.msgId;
    }
    void sermsgId(int msgId){
    	this.msgId=msgId;
    }
}
