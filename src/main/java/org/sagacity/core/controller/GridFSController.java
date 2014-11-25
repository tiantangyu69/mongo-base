/**
 * 
 */
package org.sagacity.core.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.sagacity.core.service.GridFSService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.gridfs.GridFSDBFile;

/**
 * @author LIZHITAO 基础controller 增加日期转换函数
 */
@Controller
@Scope("request")
public class GridFSController {
	@Resource
	private GridFSService service;

	/**
	 * 图片展示
	 * 
	 * @param response
	 * @param imageid
	 */
	@RequestMapping(value = "common/file")
	@ResponseBody
	public void getImage(HttpServletResponse response, String id) {
		GridFSDBFile file = service.getGridFSDBFile(id);
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			file.writeTo(os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
