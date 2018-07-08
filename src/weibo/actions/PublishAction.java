package weibo.actions;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import myHibernate.DB_Helper;
import myHibernate.User;
import myHibernate.Weibo;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Administrator
 */
public class PublishAction extends ActionSupport{
    private Weibo weibo=new Weibo();
    private String id;              //要从session获得
    private String imagePath;           //     \id\msgId.png
    private String text;
    private int msgId;
    private File uploader;
    

    public Weibo getWeibo() {
        return weibo;
    }

    public void setWeibo(Weibo weibo) {
        this.weibo = weibo;
    }
    
    public String getText(){
    	return this.text;
    }
    
    public void setText(String text){
    	this.text=text;
    }
    
    public File getUploader() {
		return uploader;
	}

	public void setUploader(File uploader) {
		this.uploader = uploader;
	}

	@Override
     public String execute() {
    	
    	id = (String) ActionContext.getContext().getSession().get("username");
//    	text=ActionContext.getContext().getParameters().get("text").toString();
//    	base64str=ActionContext.getContext().getParameters().get("base64str").toString();
    	
    	
    	System.out.println(text);
    	if(uploader==null || text==null){
    		return "publish";
    	}
        DB_Helper help=new DB_Helper();
         
        //设置msgId
        msgId=help.countWeibo()+1;
         
        //设置imagePath
        imagePath="C:\\"+id+"\\"+Integer.toString(msgId)+".jpg";
        System.out.println(imagePath);
        File dir=new File("C:\\"+id);
        if(!dir.exists()) {
        	dir.mkdirs();
 		}
        
         //更改图片，存入数据库
        OutputStream os=null;
        
        byte[] bs=new byte[1024*1024*10];
        int len;
        File tempFile=new File(imagePath);
        if(!tempFile.exists()){
        	tempFile.exists();
        }
        try {
			os=new FileOutputStream(tempFile);
			InputStream inputStream=new FileInputStream(uploader);
			while((len=inputStream.read(bs))!=-1){
				os.write(bs,0,len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
        
        
        System.out.println(msgId);
        ActionContext.getContext().getSession().put("msgId", msgId);
        
   	 	
        weibo.setMsgId(msgId);
        weibo.setId(id);
        weibo.setImagePath(imagePath);
        weibo.setText(text);
        help.addWeibo(weibo);
        
        return "success";
     }

    
}
