/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weibo.actions;

import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import myHibernate.DB_Helper;
import myHibernate.Weibo;


/**
 *
 * @author Administrator
 */
public class HomepageAction extends ActionSupport{
//    private int pageNum=0;
//    int getPageNum(){
//       return this.pageNum;
//    }
//    void setPageNum(int pageNum){
//       this.pageNum=pageNum;
//    }
	HashMap<String, String> jsonData = new HashMap<String, String>();
	
    @Override 
    public String execute(){
    	  System.out.println("hello");
          DB_Helper help=new DB_Helper();
          HashMap<String, String> weiboList = help.homepage_helper();
        
          System.out.println(weiboList.size());
          //Gson gson = new Gson();
          //ActionContext.getContext().getSession().put("list",  gson.toJson(help.homepage_helper()));
          //System.out.println(gson.toJson(help.homepage_helper()));
          return SUCCESS;
    }
}
