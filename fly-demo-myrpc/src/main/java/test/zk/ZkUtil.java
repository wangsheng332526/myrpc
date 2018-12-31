package test.zk;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.I0Itec.zkclient.ZkClient;

/**
  * TODO 请在此处添加注释
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年12月31日  下午3:28:08  
  * @since 2.0
  */
public class ZkUtil {
	private static String path = "/myrpc/provider";
	private static String addr = "127.0.0.1:2181";
	
	public static void main(String[] args) {
		ZkUtil z = new ZkUtil();
		System.out.println(z.readProviderAddr()+".......");
	}
	
	public static void registerProvider(String address,Integer port){
		ZkClient zkClient = new ZkClient(addr,5000);
		if(!zkClient.exists(path)){
			zkClient.createPersistent(path, true);
		}
		zkClient.writeData(path, address+":"+port);
		
	}
	
	public static String readProviderAddr(){
		ZkClient zkClient = new ZkClient(addr,5000);
		return zkClient.readData(path);
		
	}
	
}
