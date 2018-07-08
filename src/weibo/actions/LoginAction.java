/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weibo.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import myHibernate.DB_Helper;
import myHibernate.User;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.struts2.ServletActionContext;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class LoginAction extends ActionSupport{
    private User user;
    private String username;
    private String password;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Override
     public String execute() {
      if(username == null||password==null) {
    	  
      return "input";
  }
         DB_Helper help=new DB_Helper();
         if(help.login_helper(this.username,this.password)){
//      	  Map m1 = new HashMap(); 
        	 System.out.println(username);
//             m1.put("username", username);
        
        	 ActionContext.getContext().getSession().put("username", username);
             return SUCCESS;
         }


        // TODO 鏀逛负鏁版嵁搴撴煡鎵�
//        if(getUsername().equals("123")) {
//            return SUCCESS;
//        }
         return "input";
     }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
