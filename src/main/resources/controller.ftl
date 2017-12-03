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
import com.simple.common.util.AjaxWebUtil;
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
	@ResponseBody
	public String getList(HttpServletRequest request,HttpServletResponse response) {
		try {
			List<${beanName}> commercialsList = get${beanName}${serviceSubfix}().get${beanName}List(param);
			Integer total = get${beanName}${serviceSubfix}().get${beanName}Count(param);
			PageResult pr = new PageResult(total,pageSize, page,commercialsList);
			request.setAttribute("pageResult", pr);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"发布成功", token);
		}catch(Exception e) {
			e.printStackTrace();
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"发布失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}		
}
