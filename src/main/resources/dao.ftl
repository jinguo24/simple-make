package ${daoPackage};

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import ${basepackage}.common.mybatis.annotation.DatabaseTemplate;
import ${basepackage}.common.mybatis.dao.BaseIbatisDao;
import ${beanpackage}.${beanName};

@Repository
@DatabaseTemplate("${datatemplate}")
public class ${beanName}${daoSubfix} extends BaseIbatisDao {

	/**
	 * 分页获取模板数据
	 */
	public List<${beanName}> get${beanName}List(Map<Object, Object> param) {
		return this.sqlSession.selectList("${beanId}.queryPageMap", param);
	}

	/**
	 * 分页获取模板数据总条数
	 * 
	 */
	public Integer get${beanName}Count(Map<Object, Object> param) {
		return this.sqlSession.selectOne("${beanId}.queryCount", param);
	}

	/**
	 * 添加模板数据
	 * 
	 */
	public Integer add${beanName}(${beanName} entity) {
		Integer i = this.sqlSession.insert("${beanId}.add", entity);
		return i;
	}

	/**
	 * 删除模板数据
	 * 
	 */
	public Integer del${beanName}ById(${ormFields.pk.javaType} id) {
		Integer i = this.sqlSession.delete("${beanId}.delById", id);
		return i;
	}

	/**
	 * 根据id获取模板数据
	 */
	public ${beanName} get${beanName}ById(${ormFields.pk.javaType} id) {
		return this.sqlSession.selectOne("${beanId}.queryById", id);
	}

	/**
	 * 更新模板数据
	 */
	public Integer update${beanName}(${beanName} entity) {
		Integer i = this.sqlSession.update("${beanId}.update", entity);
		return i;
	}
}
