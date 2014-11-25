package org.sagacity.core.page;

import java.util.List;

/**
 * 分页接口
 * 
 * @author LIZHITAO
 * 
 */
public interface Paginable<T> {
	int DEFAULT_PAGESIZE = 15;// 默认每页显示的条数
	int DEFAULT_CURRENTPAGE = 1;// 默认第一页
	int DEFAULT_TOTALCOUNT = 0;// 默认总条数

	/**
	 * 设置总记录数
	 * 
	 * @param totalCount
	 */
	void setTotalCount(int totalCount);

	/**
	 * 总记录数
	 * 
	 * @return
	 */
	int getTotalCount();

	/**
	 * 设置总页数
	 * 
	 * @param pageCount
	 */
	void setPageCount(int pageCount);

	/**
	 * 总页数
	 * 
	 * @return
	 */
	int getPageCount();

	/**
	 * 设置每页记录数
	 * 
	 * @param pageSize
	 */
	void setPageSize(int pageSize);

	/**
	 * 每页记录数
	 * 
	 * @return
	 */
	int getPageSize();

	/**
	 * 设置当前页数
	 * 
	 * @param currentPage
	 */
	void setCurrentPage(int currentPage);

	/**
	 * 获取当前页号
	 * 
	 * @return
	 */
	int getCurrentPage();

	/**
	 * 是否第一页
	 * 
	 * @return
	 */
	boolean isFirstPage();

	/**
	 * 是否最后一页
	 * 
	 * @return
	 */
	boolean isLastPage();

	/**
	 * 返回下页的页号
	 */
	int getNextPage();

	/**
	 * 返回上页的页号
	 */
	int getPrePage();

	/**
	 * 获取结果集
	 * 
	 * @return
	 */
	List<T> getDataList();

	/**
	 * 设置结果集
	 * 
	 * @param dataList
	 */
	void setDataList(List<T> dataList);
	
	/**
	 * 获得纪录的开始位置
	 * @return
	 */
	public int getFirstResult();
}
