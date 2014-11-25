package org.sagacity.core.page;

import java.util.List;

/**
 * 
 * @author LIZHITAO 分页实体
 * @param <T>
 */
public class Pager<T> implements Paginable<T> {
	private List<T> dataList = null;// 分页结果集
	private int totalCount = 0;// 记录总数
	private int pageSize = 0;// 每页显示记录数
	private int currentPage = 1;// 当前页数
	private int pageCount = 1;// 记录总页数

	/**
	 * 无参构造函数
	 */
	public Pager() {
	}

	/**
	 * 构造函数
	 * 
	 * @param pageSize
	 *            每页显示条数
	 * @param totalCount
	 *            总条数
	 * @param currentPage
	 *            当前页码
	 */
	public Pager(Integer pageSize, Integer totalCount, Integer currentPage) {
		if (null == pageSize) {
			pageSize = DEFAULT_PAGESIZE;
		}

		setPageSize(pageSize);

		if (null == totalCount) {
			totalCount = DEFAULT_TOTALCOUNT;
		}

		setTotalCount(totalCount);

		Double pageNum = Math.ceil((getTotalCount() + 0.00) / pageSize);

		if (null == currentPage || currentPage < 1 || totalCount == 0) {
			setCurrentPage(DEFAULT_CURRENTPAGE);
		} else if (currentPage > pageNum.intValue()) {
			setCurrentPage(pageNum.intValue());
		} else {
			setCurrentPage(currentPage);
		}

		setPageCount(pageNum.intValue());
	}

	/**
	 * 构造函数
	 * 
	 * @param pageSize
	 *            每页显示条数
	 * @param totalCount
	 *            总条数
	 * @param currentPage
	 *            当前页数
	 * @param dataList
	 *            数据集
	 */
	public Pager(Integer pageSize, Integer totalCount, Integer currentPage,
			List<T> dataList) {
		if (null == pageSize) {
			pageSize = DEFAULT_PAGESIZE;
		}

		setPageSize(pageSize);

		if (null == totalCount) {
			totalCount = DEFAULT_TOTALCOUNT;
		}

		setTotalCount(totalCount);

		Double pageNum = Math.ceil((getTotalCount() + 0.00) / pageSize);

		if (null == currentPage || currentPage < 1 || totalCount == 0) {
			setCurrentPage(DEFAULT_CURRENTPAGE);
		} else if (currentPage > pageNum.intValue()) {
			setCurrentPage(pageNum.intValue());
		} else {
			setCurrentPage(currentPage);
		}

		setPageCount(pageNum.intValue());
		setDataList(dataList);
	}

	/**
	 * 构造函数
	 * 
	 * @param pager
	 *            对象
	 */
	public Pager(Pager<T> pager) {
		pageSize = pager.getPageSize();
		currentPage = pager.getCurrentPage();
		totalCount = pager.getTotalCount();
		pageCount = pager.getPageCount();
		dataList = pager.getDataList();
	}

	/**
	 * 是否为第一页
	 */
	@Override
	public boolean isFirstPage() {
		return currentPage <= 1;
	}

	/**
	 * 是否为最后一页
	 */
	@Override
	public boolean isLastPage() {
		return currentPage >= getPageCount();
	}

	/**
	 * 获取下一页的页码
	 */
	@Override
	public int getNextPage() {
		if (isLastPage()) {
			return currentPage;
		} else {
			return currentPage + 1;
		}
	}

	/**
	 * 获取上一页的页码
	 */
	@Override
	public int getPrePage() {
		if (isFirstPage()) {
			return currentPage;
		} else {
			return currentPage - 1;
		}
	}

	@Override
	public List<T> getDataList() {
		return dataList;
	}

	@Override
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public int getCurrentPage() {
		return currentPage;
	}

	@Override
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public int getPageCount() {
		return pageCount;
	}

	@Override
	public int getTotalCount() {
		return totalCount;
	}

	@Override
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public int getFirstResult() {
		return (getCurrentPage() - 1) * pageSize;
	}
}
