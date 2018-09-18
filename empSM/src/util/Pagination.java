package util;

public class Pagination {
	private int ye;
	private int maxYe;
	private int beginYe;
	private int endYe;
	private int begin;

	// numInPage 一页表格显示多少条记录 numOfPage 一页中显示多少页码
	public Pagination(int ye, int count, int numInPage, int numOfPage) {
		if (count != 0) {
			this.ye = ye;

			if (this.ye <= 1) {
				this.ye = 1;
			}
			maxYe = count % numInPage == 0 ? count / numInPage : count / numInPage + 1;
			if (this.ye > maxYe) {
				this.ye = maxYe;
			}
			beginYe = this.ye - numOfPage / 2;
			if (beginYe <= 1) {
				beginYe = 1;
			}
			endYe = beginYe + numOfPage - 1;
			if (endYe >= maxYe) {
				endYe = maxYe;
				beginYe = endYe - numOfPage + 1;
			}
			if (beginYe <= 1) {
				beginYe = 1;
			}
			if (this.ye <= 1) {
				this.ye = 1;
			}
			begin = (this.ye - 1) * numInPage;
		}
	}

	public int getYe() {
		return ye;
	}

	public int getMaxYe() {
		return maxYe;
	}

	public int getBeginYe() {
		return beginYe;
	}

	public int getEndYe() {
		return endYe;
	}

	public int getBegin() {
		return begin;
	}

}
// int maxYe=0;
// if(count%size==0) {
// maxYe=count/size;
// }else {
// maxYe=count/size+1;
// }
// 三目运算符 A?B:C 语法糖
// request.setAttribute("ye", p.getYe());
// request.setAttribute("maxYe", p.getMaxYe());
// request.setAttribute("beginYe", p.getBeginYe());
// request.setAttribute("endYe", p.getEndYe());
