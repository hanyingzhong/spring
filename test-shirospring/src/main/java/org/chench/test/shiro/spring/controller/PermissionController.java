package org.chench.test.shiro.spring.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.chench.test.shiro.spring.annotation.PermissionName;
import org.chench.test.shiro.spring.bean.Permission;
import org.chench.test.shiro.spring.dao.PermissionsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
public class PermissionController {

	@Autowired
	// springmvc在启动时将所有贴有请求映射标签：RequestMapper方法手机起来封装到该对象中
	private RequestMappingHandlerMapping rmhm;

	@Autowired
	private PermissionsDAO permDao;

	@RequestMapping("reload")
	public String reload() throws Exception {
		// 0：从数据库中查询出所有的的权限表达式，去除重复的表达式
		List<String> perms = permDao.getAllPerm();

		// 将系统中所有权限表达式加载进入数据库
		// CTRL+2 L,可以快速的将返回值自动打印出来
		// 1.获取Controller中所有带有@RequestMapping标签的方法
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods();
		Collection<HandlerMethod> values = handlerMethods.values();

/*		if (perms.contains("employee:add")) {
			System.out.println("permission exist.");
		} else {
			permDao.addPerm("employee:add", "添加员工");
		}*/

		for (HandlerMethod method : values) {
			// 2.遍历所有方法，判断当前方法是否贴有@RequiresPermission权限控制标签
			RequiresPermissions annotation = method.getMethodAnnotation(RequiresPermissions.class);
			PermissionName permAnnotation = method.getMethodAnnotation(PermissionName.class);

			if (annotation != null && permAnnotation != null) {
				// 3.如果有，解析权限表达式，封装成Permission对象保存到Permission表中
				if (perms.contains(annotation.value()[0])) {
					System.out.println("permission exist:" + annotation.value()[0]);
				} else {
					//method 1:
					//permDao.addPerm(annotation.value()[0], permAnnotation.value());
					
					//method 2:
/*					Permission perm = new Permission();
					perm.setPerm(annotation.value()[0]);
					perm.setName(permAnnotation.value());					
					System.out.println("insert to table use pojo");
					permDao.addPerm2(perm);*/
					
					//method3
					Map<String, String> map = new HashMap<String, String>();
					map.put("perm", annotation.value()[0]);
					map.put("name", permAnnotation.value());
					permDao.addPerm3(map);
				}

/*				String value = annotation.value()[0];
				Permission perm = new Permission();
				perm.setPerm(value);

				perm.setName(permAnnotation.value());

				permDao.addPerm(perm.getPerm(), perm.getName());*/
			}
		}

		return "home";
	}

}
