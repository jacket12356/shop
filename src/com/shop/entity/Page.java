package com.shop.entity;

public class Page {
	private int itemNum;               //产品总数
	private int pageNum;               //总页数
	private int currentPageNum = 1;    //当前页数
	private int nextPageNum = 2;       //下一页
	private int prePageNum = 1;        //上一页
	private int itemNumPerPage;        //每一页应有的产品个数
	private int[] pages = null;        //产生页表的辅助数组
	
	public Page(int itemNum, int itemNumPerPage) {
		this.itemNum = itemNum;
		this.itemNumPerPage = itemNumPerPage;
		//剩下的值根据已有的条件计算就行
		if(itemNum % itemNumPerPage == 0) {this.pageNum = itemNum / itemNumPerPage;}
		else {this.pageNum = itemNum / itemNumPerPage + 1;}
		
		pages = new int[pageNum];
		for(int i=1;i<=pageNum;i++) {
			pages[i-1] = i;
		}
	}
	
	
	public int[] getPages() {
		return pages;
	}


	public void setPages(int[] pages) {
		this.pages = pages;
	}


	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getCurrentPageNum() {
		return currentPageNum;
	}
	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}
	public int getNextPageNum() {
		return nextPageNum;
	}
	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}
	public int getPrePageNum() {
		return prePageNum;
	}
	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}
	public int getItemNumPerPage() {
		return itemNumPerPage;
	}
	public void setItemNumPerPage(int itemNumPerPage) {
		this.itemNumPerPage = itemNumPerPage;
	}
	
	
}