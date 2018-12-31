package test;


 /**
  * TODO 请在此处添加注释
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年11月29日  下午2:03:40  
  * @since 2.0
  */
public class UserServiceImpl implements UserService{

	@Override
	public String sayHi(String str,Integer sId) {
		return "Hi,"+str+",sId为"+sId;
	}

}
