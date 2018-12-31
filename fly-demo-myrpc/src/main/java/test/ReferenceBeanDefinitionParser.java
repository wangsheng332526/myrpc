package test;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * TODO 请在此处添加注释
 * 
 * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
 * @version 2018年11月30日 下午2:25:14
 * @since 2.0
 */
public class ReferenceBeanDefinitionParser implements BeanDefinitionParser {
    
	private final Class<?> beanClass;

    public ReferenceBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
	
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(beanClass);
		beanDefinition.setLazyInit(false);
		String id = element.getAttribute("id");
		beanDefinition.getPropertyValues().add("sid", element.getAttribute("sid"));
		beanDefinition.getPropertyValues().add("interfaceName", element.getAttribute("interfaceName"));
		// 注册bean到BeanDefinitionRegistry中
		parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        beanDefinition.getPropertyValues().addPropertyValue("id", id);
		return beanDefinition;
	}

}
