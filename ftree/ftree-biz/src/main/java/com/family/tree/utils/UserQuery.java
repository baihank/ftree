package com.family.tree.utils;

import java.util.List;

import com.family.tree.domain.UserBaseDO;

public class UserQuery {
	public static final int DEFAULT_PAGE_SIZE = 5;
	public static final int MAX_PAGE_SIZE = 100;

	protected int pageSize;// 一页大小
	private int totalRecord;// 总记录数
	protected int pageIndex = 1;// 当前第几页. base 1
	private int totalPage; // 总页数

	private String userName;
	private List<UserBaseDO> result;

	public List<UserBaseDO> getResult() {
		return result;
	}

	public void setResult(List<UserBaseDO> result) {
		this.result = result;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		if (pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	/**
	 * 设置总的记录数。计算出总页数
	 * 
	 * @param totalRecord
	 */
	public void setTotalRecord(int totalRecord) {
		this.totalPage = (totalRecord + getPageSize() - 1) / getPageSize();
		this.totalRecord = totalRecord;
	}

	/**
	 * 得到当前查询的第几页, base 为1
	 * 
	 * @return
	 */

	public int getPageIndex() {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		// else if (pageIndex > getTotalPage()) {
		// pageIndex = getTotalPage();
		// }

		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getStartPos() {
		return (getPageIndex() - 1) * getPageSize();
	}

	public int getEndPos() {
		if (getPageIndex() * getPageSize() < getTotalRecord()) {
			return getPageIndex() * getPageSize();
		} else {
			return getTotalRecord();
		}
	}

	/**
	 * 得到总页数
	 * 
	 * @return
	 */
	public int getTotalPage() {
		return totalPage;
	}

}
