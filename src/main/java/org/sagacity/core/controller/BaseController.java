/**
 * 
 */
package org.sagacity.core.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author LIZHITAO 基础controller 增加日期转换函数
 */
@Controller
@Scope("request")
public class BaseController {

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor editor = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, editor);
	}

}
