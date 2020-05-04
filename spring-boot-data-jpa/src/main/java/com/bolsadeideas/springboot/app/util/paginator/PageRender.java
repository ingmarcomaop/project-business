package com.bolsadeideas.springboot.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;
	private int totalPages;
	private int numElementsByPage;
	private int actualPage;
	private List<PageItem> pages;

	public PageRender(String url, Page<T> page) {

		this.url = url;
		this.page = page;
		this.pages = new ArrayList<PageItem>();

		numElementsByPage = page.getSize();
		totalPages = page.getTotalPages();
		actualPage = page.getNumber() + 1;

		int pageInit, pageFinal;

		if (totalPages <= numElementsByPage) {

			pageInit = 1;
			pageFinal = totalPages;

		} else {
			if (actualPage <= numElementsByPage / 2) {
				pageInit = 1;
				pageFinal = numElementsByPage;
			} else if (actualPage >= totalPages - numElementsByPage/2) {
				pageInit = totalPages - numElementsByPage + 1;
				pageFinal = numElementsByPage;
			} else {
				pageInit = actualPage - numElementsByPage / 2;
				pageFinal = numElementsByPage;
			}
		}

		for (int i = 0; i < pageFinal; i++) {
			pages.add(new PageItem(pageInit + i, actualPage == pageInit + i));
		}

	}

	public String getUrl() {
		return url;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getActualPage() {
		return actualPage;
	}

	public List<PageItem> getPages() {
		return pages;
	}

	public boolean isFirst() {
		return page.isFirst();
	}

	public boolean isLast() {
		return page.isLast();
	}

	public boolean isHasNext() {
		return page.hasNext();
	}

	public boolean isHasPrevious() {
		return page.hasPrevious();
	}

}
