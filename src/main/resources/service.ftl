package ${servicePackage};

import java.util.List;
import java.util.Map;
import ${beanpackage}.${beanName};

public interface ${beanName}${serviceSubfix} {

		//分页查询
		public List<${beanName}> get${beanName}List(Map<Object, Object> param);
		//分页查询总条数
		public Integer get${beanName}Count(Map<Object, Object> param);
		//添加
		public Integer add${beanName}(${beanName} entity);
	    //根据id删除
		public Integer del${beanName}ById(${ormFields.pk.javaType} id);
		//根据id查询
		public ${beanName} get${beanName}ById(${ormFields.pk.javaType} id);
		//更新
		public Integer update${beanName}(${beanName} entity);
}