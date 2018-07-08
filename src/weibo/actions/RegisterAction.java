/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weibo.actions;

import com.opensymphony.xwork2.ActionSupport;
import myHibernate.DB_Helper;
import myHibernate.User;

/**
 *
 * @author Administrator
 */
public class RegisterAction extends ActionSupport{
    private User user=new User();
    private String username;
    private String password;

   
   
    @Override
    public String execute(){
    	 if(username == null||password==null) {
       	  
    	      return "input";
    	  }
    	user.setId(username);
    	user.setCode(password);
        DB_Helper help=new DB_Helper();
        if(help.register_helper(user)){
            return SUCCESS;
        }
        return ERROR;
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
