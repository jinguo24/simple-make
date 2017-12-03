package ${beanpackage};
import java.io.Serializable;

public class ${beanName} implements Serializable {

	private static final long serialVersionUID = -3322185517539871246L;
	
<#list beanContentlist as a>
    ${a}
</#list>
}
