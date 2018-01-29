package edu.numberone.studystar.entity;

import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1880019558453064416L;

	private Integer pageSize;// 每页显示多少条记录
	private Integer currentPage;// 当前第几页数据
	private Integer totalRecord;// 一共多少条记录
	private Integer totalPage;// 一共多少也记录
	private List<T> dateList;// 要显示的数据

	public Pager() {
		super();
	}

	public Pager(Integer pageSize, Integer currentPage, Integer totalRecord, Integer totalPage, List<T> dateList) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
		this.dateList = dateList;
	}

	/**
	 * 以Sublist分页方式查询数据库，通过pageNum，pageSize和pageList获得需要Pager对象,
	 * 
	 * @param pageList
	 *            需要分页的集合
	 * @param pageNum
	 *            当前页数
	 * @param 当前页显示的数据大小
	 *
	 * 
	 */
	public Pager(List<T> pageList, int pageNum, int pageSize) {

		if (pageList == null||pageList.size()==0) {
			return;
		}
		// 获得总记录数
		this.totalRecord = pageList.size();
		// 获得当前页数的大小
		this.pageSize = pageSize;
		// 获得总页数
		this.totalPage = this.totalRecord % pageSize == 0 ? this.totalRecord / pageSize
				: this.totalRecord / pageSize + 1;
		System.out.println("totalPage ---->" + totalPage);

		// 获得当前页数
		this.currentPage = pageNum > this.totalPage ? this.totalPage : pageNum;
		System.out.println("currentPage ---->" + currentPage);

		// 获得需要显示的数据
		int start = this.pageSize * (this.currentPage - 1);
		int end = this.pageSize * this.currentPage > this.totalRecord ? this.totalRecord
				: this.pageSize * this.currentPage;

		System.out.println("start ---->" + start);
		System.out.println("end ---->" + end);

		this.dateList = pageList.subList(start, end);

	}

	/**
	 * 以SQL分页方式查询数据库通过pageNum，pageSize和pageList获得需要Pager对象
	 * 
	 * @param pageNum
	 *            当前页数
	 * @param 当前页显示的数据大小
	 *            * @param pageList 需要分页的集合
	 *
	 * 
	 */
	public Pager(int pageNum, int pageSize, List<T> pageList) {

		if (pageList == null) {
			return;
		}
		// 获得总记录数
		this.totalRecord = pageList.size();
		// 获得当前页数的大小
		this.pageSize = pageSize;
		// 获得总页数
		this.totalPage = this.totalRecord % pageSize == 0 ? this.totalRecord / pageSize
				: this.totalRecord / pageSize + 1;
		System.out.println("totalPage ---->" + totalPage);

		// 获得当前页数
		this.currentPage = pageNum > this.totalPage ? this.totalPage : pageNum;
		System.out.println("currentPage ---->" + currentPage);

		

		this.dateList = pageList;

	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getDateList() {
		return dateList;
	}

	public void setDateList(List<T> dateList) {
		this.dateList = dateList;
	}

}
