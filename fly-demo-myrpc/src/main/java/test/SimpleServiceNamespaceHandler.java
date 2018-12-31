package test;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
  * TODO 请在此处添加注释
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年11月30日  下午2:27:40  
  * @since 2.0
  */
public class SimpleServiceNamespaceHandler extends NamespaceHandlerSupport{

	@Override
	public void init() {
		registerBeanDefinitionParser("reference", new ReferenceBeanDefinitionParser(ReferenceBean.class));
	}
	

}
