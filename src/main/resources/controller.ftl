package ${controllerPackage};
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lenovo.fis.model.PageResult;
import com.lenovo.model.client.ModelServicesClient;
import ${servicePackage}.${beanName}${serviceSubfix};
import ${beanpackage}.${beanName};
@Controller
@RequestMapping("${beanName}")
public class ${beanName}${controllerSubfix} {
	
	private static final Logger logger = LoggerFactory.getLogger(${beanName}${controllerSubfix}.class); 
	
	private ${beanName}${serviceSubfix} get${beanName}${serviceSubfix}() {
		return ModelServicesClient.getInstance().getService(${beanName}${serviceSubfix}.class);
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @return
	 * @date 2015-9-15 下午1:31:35
	 */
	@RequestMapping("${beanNameLowerCase}List")
	public String getList(HttpServletRequest request,HttpServletResponse response) {
		Integer page;
		Integer pageSize = 10;
		Map<Object,Object> param = new HashMap<Object,Object>();
		<#list queryFields as qf>
		    param.put("${qf.field}",StringUtils.trimToNull(request.getParameter("${qf.field}")));
		    request.setAttribute("${qf.field}", StringUtils.trimToNull(request.getParameter("${qf.field}")));
		</#list>
		try{
			 page = Integer.valueOf(request.getParameter("page"));
		}catch(Exception e){
			page=1;
		}
		if (page < 1) {
			page = 1;
		}
		param.put("startnum", (page - 1) * pageSize);
		param.put("pageSize", pageSize);
		List<${beanName}> commercialsList = get${beanName}${serviceSubfix}().get${beanName}List(param);
		Integer total = get${beanName}${serviceSubfix}().get${beanName}Count(param);
		PageResult pr = new PageResult(total,pageSize, page,commercialsList);
		request.setAttribute("pageResult", pr);
		return "${beanNameLowerCase}/list";
	}
	
	/**
	 * 跳转新增
	 * @param request
	 * @param response
	 * @return
	 * @author XuJiaZhen
	 * @date 2015-9-15 下午3:00:03
	 */
	@RequestMapping("${beanNameLowerCase}Add")
	public String toAdd(HttpServletRequest request,HttpServletResponse response){
		return "${beanNameLowerCase}/add";
	}
	
	/**
	 * 新增
	 * @return
	 * @author XuJiaZhen
	 * @date 2015-9-15 下午3:01:06
	 */
	@RequestMapping("${beanNameLowerCase}DoAdd")
	@ResponseBody
	public String doAdd(${beanName} ${beanNameLowerCase},HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> map = new HashMap<Object,Object>();
		try{
			Integer i= get${beanName}${serviceSubfix}().add${beanName}(${beanNameLowerCase});
			if(i==1){
				map.put("result", "添加成功");
			}else{
				map.put("result", "添加失败");
			}
		}catch(Exception e){
			logger.debug(e.getMessage());
			map.put("result", "添加失败");
		}
		
		return JSONObject.toJSONString(map);
	}
	
	/**
	 * 根据id获取数据
	 * @param request
	 * @param response
	 * @return
	 * @date 2015-9-17 下午4:10:13
	 */
	@RequestMapping("${beanNameLowerCase}Info")
	@ResponseBody
	public String getById(HttpServletRequest request,HttpServletResponse response){
		String  id = StringUtils.trimToNull(request.getParameter("id"));
		int idUse=Integer.valueOf(id);
		${beanName} entity = get${beanName}${serviceSubfix}().get${beanName}ById(idUse);
		request.setAttribute("entity", entity);
		return "${beanNameLowerCase}/update";
	}
	
	/**
	 * 修改
	 * @param template
	 * @param request
	 * @param response
	 * @return
	 * @author XuJiaZhen
	 * @date 2015-9-15 下午9:20:33
	 */
	@RequestMapping("${beanNameLowerCase}DoUpdate")
	@ResponseBody
	public String doUpdate(${beanName} ${beanNameLowerCase},HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> map = new HashMap<Object,Object>();
		try{
			Integer i = get${beanName}${serviceSubfix}().upd${beanName}(${beanNameLowerCase});
			if(i>0){
				map.put("result", "修改成功");
			}else{
				map.put("result", "修改失败");
			}
		}catch(Exception e){
			logger.debug(e.getMessage());
			e.printStackTrace();
		}
		return JSONObject.toJSONString(map);
	}
	/**
	 * 删除
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @author XuJiaZhen
	 * @date 2015-9-15 下午2:43:45
	 */
	@RequestMapping("${beanNameLowerCase}Del")
	@ResponseBody
	public String del(HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> map = new HashMap<Object,Object>();
		try{
				String  id = StringUtils.trimToNull(request.getParameter("id"));
				int idUse = Integer.valueOf(id);
				Integer i =get${beanName}${serviceSubfix}().del${beanName}ById(idUse);
				if(i==1){
					map.put("result", "删除成功");
				}else{
					map.put("result", "删除失败");
				}
			
		}catch(Exception e){
			logger.debug(e.getMessage());
			map.put("result", "删除失败");
		}
		return JSONObject.toJSONString(map);
	}
}
