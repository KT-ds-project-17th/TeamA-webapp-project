package com.mySpring.springEx.common.pagination;

public class Pagination {

	// ����������
	private int currentPage;
	// �������� ����� ������ ����
	private int cntPerPage;
	// ȭ�� �ϴ� ������ ������ 1~10, 10~20 20~30 ...
	private int pageSize;
	// ��ü ������ ����
	private int totalRecordCount;
	// ��ü ������ ����
	private int totalPageCount;
	// ������ ����Ʈ�� ù ������ ��ȣ
	private int firstPage;
	// ������ ����Ʈ�� ������ ������ ��ȣ
	private int lastPage;
	// SQL�� �������� ���Ǵ� ù RNUM
	private int firstRecordIndex;
	// SQL�� �������� ���Ǵ� ������ RNUM
	private int lastRecordIndex;
	// ���� ������ ���� ����
	private boolean hasPreviousPage;
	// ���� ������ ���� ����
	private boolean hasNextPage;

	public Pagination(int currentPage, int cntPerPage, int pageSize) {
		// �����Է¹���
		if (currentPage < 1) {
			currentPage = 1;
		}
		// 10,20,30�� ���� �̿� ó�� ����
		if (cntPerPage != 10 && cntPerPage != 20 && cntPerPage != 30) {
			cntPerPage = 10;
		}
		// �ϴ� ������ ���� 10���� ����
		if (pageSize != 10) {
			pageSize = 10;
		}
		this.currentPage = currentPage;
		this.cntPerPage = cntPerPage;
		this.pageSize = pageSize;

	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;

		if (totalRecordCount > 0) {
			calculation();
		}
	}

	private void calculation() {

		// ��ü ������ �� (���� ������ ��ȣ�� ��ü ������ ������ ũ�� ���� ������ ��ȣ�� ��ü ������ ���� ����)
		totalPageCount = ((totalRecordCount - 1) / this.getCntPerPage()) + 1;
		if (this.getCurrentPage() > totalPageCount) {
			this.setCurrentPage(totalPageCount);
		}

		// ������ ����Ʈ�� ù ������ ��ȣ
		firstPage = ((this.getCurrentPage() - 1) / this.getPageSize()) * this.getPageSize() + 1;

		// ������ ����Ʈ�� ������ ������ ��ȣ (������ �������� ��ü ������ ������ ũ�� ������ �������� ��ü ������ ���� ����)
		lastPage = firstPage + this.getPageSize() - 1;
		if (lastPage > totalPageCount) {
			lastPage = totalPageCount;
		}

		// SQL�� �������� ���Ǵ� ù RNUM
		firstRecordIndex = (this.getCurrentPage() - 1) * this.getCntPerPage();

		// SQL�� �������� ���Ǵ� ������ RNUM
		lastRecordIndex = this.getCurrentPage() * this.getCntPerPage();

		// ���� ������ ���� ����
		hasPreviousPage = firstPage == 1 ? false : true;
		if(hasPreviousPage == false) {
			if(currentPage != firstPage) {
				hasPreviousPage = true;
			}else {
				hasPreviousPage = false;
			}
		}
		// ���� ������ ���� ����
		hasNextPage = (lastPage * this.getCntPerPage()) >= totalRecordCount ? false : true;
		if (hasNextPage == false) {
			// ������ ���������� ������������ ������ �������� �ƴѰ�� nextó��
			if (currentPage != lastPage) {
				hasNextPage = true;
			} else {
				hasNextPage = false;
			}
		}
	}

	/*
	 * GETTER SETTER //
	 */

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCntPerPage() {
		return cntPerPage;
	}

	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getFirstRecordIndex() {
		return firstRecordIndex;
	}

	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}

	public int getLastRecordIndex() {
		return lastRecordIndex;
	}

	public void setLastRecordIndex(int lastRecordIndex) {
		this.lastRecordIndex = lastRecordIndex;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

}
