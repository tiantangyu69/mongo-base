/**
 * 
 */
package org.sagacity.core.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

/**
 * @author LIZHITAO 操作mongodb文件
 */
@Repository
public class GridFSDao {
	public static final String DEFAULT_BUCKET = "upload";// 默认将文件上传到mongodb
	// GridFS的upload文件夹下

	@Resource
	private MongoTemplate mongoTemplate;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * 通过gridFS上传对象
	 * 
	 * @param obj
	 *            目标对象
	 * @param paramsMap
	 *            参数map
	 * @return
	 * @throws Exception
	 */
	public synchronized boolean gridFSUpload(Object obj,
			Map<String, Object> paramsMap) {
		boolean flag = false;

		GridFS gridFS = new GridFS(getMongoTemplate().getDb(), DEFAULT_BUCKET);// 获取mongo
		// GridFS对象

		GridFSFile gridFSFile = null;
		if (obj instanceof InputStream) {// 判断传入的obj对象
			gridFSFile = gridFS.createFile((InputStream) obj);
		} else if (obj instanceof byte[]) {
			gridFSFile = gridFS.createFile((byte[]) obj);
		} else if (obj instanceof File) {
			try {
				gridFSFile = gridFS.createFile((File) obj);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (gridFSFile != null && paramsMap != null) {// 将参数设置到数据库中
			Iterator<Entry<String, Object>> iter = paramsMap.entrySet()
					.iterator();
			while (iter.hasNext()) {
				Map.Entry<String, Object> entry = (Entry<String, Object>) iter
						.next();
				gridFSFile.put(entry.getKey(), entry.getValue());
			}
			gridFSFile.save();
			flag = true;
		}
		return flag;
	}

	/**
	 * 通过gridFS删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean gridFSDelete(Map<String, Object> paramsMap) {
		boolean flag = false;
		GridFS gridFS = new GridFS(getMongoTemplate().getDb(), DEFAULT_BUCKET);
		DBObject query = new BasicDBObject();
		if (paramsMap != null) {
			Iterator<Entry<String, Object>> iter = paramsMap.entrySet()
					.iterator();
			while (iter.hasNext()) {
				Map.Entry<String, Object> entry = (Entry<String, Object>) iter
						.next();
				query.put(entry.getKey(), entry.getValue());
			}
		}
		DBObject obj = gridFS.findOne(query);
		if (obj != null) {
			gridFS.remove(obj);
			flag = true;
		}
		return flag;
	}

	/**
	 * 根据gridFS的_id查找文件,只返回一个文件
	 * 
	 * @param imageid
	 *            图片id
	 * @return
	 */
	public GridFSDBFile getGridFSDBFile(String id) {
		GridFS gridFS = new GridFS(getMongoTemplate().getDb(), DEFAULT_BUCKET);
		DBObject query = new BasicDBObject("_id", id);
		GridFSDBFile file = gridFS.findOne(query);
		return file;
	}

	/**
	 * 据文件名返回文件，只返回第一个文件
	 * 
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	public GridFSDBFile getGridFSDBFileByFileName(String fileName) {
		GridFS gridFS = new GridFS(getMongoTemplate().getDb(), DEFAULT_BUCKET);
		DBObject query = new BasicDBObject("filename", fileName);
		GridFSDBFile gridFSDBFile = gridFS.findOne(query);
		return gridFSDBFile;
	}

	/**
	 * 据文件名返回文件，返回列表
	 * 
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	public List<GridFSDBFile> getGridFSDBFilesByFileName(String fileName) {
		GridFS gridFS = new GridFS(getMongoTemplate().getDb(), DEFAULT_BUCKET);
		DBObject query = new BasicDBObject("filename", fileName);
		List<GridFSDBFile> gridFSDBFiles = gridFS.find(query);
		return gridFSDBFiles;
	}
}
