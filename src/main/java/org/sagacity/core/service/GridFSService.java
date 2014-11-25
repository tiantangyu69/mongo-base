/**
 * 
 */
package org.sagacity.core.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.sagacity.core.dao.GridFSDao;
import org.sagacity.core.model.MongoFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;

/**
 * @author LIZHITAO 操作mongodb 文件
 */
@Service
public class GridFSService {
	@Resource
	private GridFSDao gridFSDao;

	/**
	 * 上传文件
	 * 
	 * @param file
	 */
	public MongoFile uploadFile(MultipartFile file) {
		Map<String, Object> map = new HashMap<String, Object>();

		String id = UUID.randomUUID().toString();
		String filename = file.getOriginalFilename();
		String contentType = file.getContentType();

		map.put("_id", id);
		map.put("filename", filename);
		map.put("contentType", contentType);

		MongoFile fs = new MongoFile();
		fs.setId(id);
		fs.setContentType(contentType);
		fs.setFilename(filename);

		try {
			gridFSDao.gridFSUpload(file.getInputStream(), map);
			return fs;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据id取得单个文件
	 * 
	 * @param id
	 * @return
	 */
	public GridFSDBFile getGridFSDBFile(String id) {
		return gridFSDao.getGridFSDBFile(id);
	}

	/**
	 * 根据参数删除文件
	 * 
	 * @param paramsMap
	 * @return
	 */
	public boolean gridFSDelete(Map<String, Object> paramsMap) {
		return gridFSDao.gridFSDelete(paramsMap);
	}

	/**
	 * 根据id删除文件
	 * 
	 * @param id
	 * @return
	 */
	public boolean gridFSDelete(String id) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("_id", id);
		return gridFSDao.gridFSDelete(paramsMap);
	}
}
