package weibo.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import myHibernate.DB_Helper;
import myHibernate.Opinion;

public class CommentAction extends ActionSupport{
	  private String comment;
      private int praise=0;
	  public String execute() {
		  Opinion op=new Opinion();
		  op.setComment(comment);
		  op.setPraise(praise);
		  int msdId= (int) ActionContext.getContext().getSession().get("msgId");
		  String id=(String) ActionContext.getContext().getSession().get("id");
		  op.setMsgId(msdId);
		  op.setId(id);
		  DB_Helper help=new DB_Helper();
		  if(help.commentHelp(op))
			  return SUCCESS;
		  return ERROR;
		  
	  }
	  public String getComment(){
		  return this.comment;
	  }
	  public void setComment(String comment){
		  this.comment=comment;
	  }
	  public int getPraise(){
		  return this.praise;
	  }
	  public void setPraise(int praise){
		  this.praise=praise;
	  }

}
