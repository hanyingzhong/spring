package org.chench.test.shiro.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.chench.test.shiro.spring.annotation.PermissionName;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cabinet")
public class CaninetController {
	@RequiresPermissions(value = { "cabinet:create" })
	@PermissionName(value = "创建换电柜")
	@RequestMapping("/create")
	public String create(HttpServletRequest request, HttpServletResponse response, 
		@RequestParam("cabinetName") String cabinetName,
		@RequestParam("numberOfBox") String numberOfBox
		) {
		
		
		return null;
	}
}
