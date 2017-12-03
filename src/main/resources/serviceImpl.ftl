package ${serviceImplPackage};

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${servicePackage}.${beanName}${serviceSubfix};
import ${daoPackage}.${beanName}${daoSubfix};
import ${beanpackage}.${beanName};
@Service("${beanName}${serviceSubfix}")
public class ${beanName}${serviceImplSubfix} implements ${beanName}${serviceSubfix} {

	@Autowired
	private ${beanName}${daoSubfix} dao;


	/**
	 * 分页获取模板数据
	 */
	public List<${beanName}> get${beanName}List(Map<Object, Object> param) {
		return dao.get${beanName}List(param);
	}

	/**
	 * 分页获取模板数据总条数
	 */
	public Integer get${beanName}Count(Map<Object, Object> param) {
		return dao.get${beanName}Count(param);
	}

	/**
	 * 添加模板数据
	 */
	public Integer add${beanName}(${beanName} entity) {
		
		return dao.add${beanName}(entity);
	}

	/**
	 * 删除模板数据
	 */
	public Integer del${beanName}ById(${ormFields.pk.javaType} id) {
		return dao.del${beanName}ById(id);
	}

	/**
	 * 根据id获取模板数据
	 */
	public ${beanName} get${beanName}ById(${ormFields.pk.javaType} id) {
		return dao.get${beanName}ById(id);
	}

	/**
	 * 更新模板数据
	 */
	public Integer upd${beanName}(${beanName} entity) {
		return dao.update${beanName}(entity);
	}
}
