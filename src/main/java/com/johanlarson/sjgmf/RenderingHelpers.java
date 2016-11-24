package com.johanlarson.sjgmf;

public class RenderingHelpers {
	/*
		Sample output:
	  			<ul class="pagination">
				<li><a href="threadlist.jsp?f=13&page=1" >1</a></li>
				<li><a href="threadlist.jsp?f=13&page=7" >&lt;</span></a></li>
				<li class="active"><a href="threadlist.jsp?f=13&page=8">8</a></li>
				<li><a href="threadlist.jsp?f=13&page=9">&gt</span></a></li>
				<li><a href="threadlist.jsp?f=13&page=12">12</a></li>
			    </ul>
		Assumes curPage >= 1, totalPages >= 1, curPage <= totalPages.
	 */
	
	private static void producePaginatorCell(String linkBase, int page, String symbol, boolean isCurrent, StringBuilder out) {
		out.append("<li");
		if (isCurrent) {
			out.append(" class=\"active\"");
		}
		out.append("><a href=\"").append(linkBase).append("page=").append(page).append("\">");
		out.append(symbol).append("</a></li>\n");
	}
	
	public static String createPaginatorHtml(String linkBase, PaginatorInfo paginatorInfo) {
		int curPage = paginatorInfo.curPage;
		int totalPages = paginatorInfo.totalPages;
		if (curPage == 1 && totalPages == 1) {
			return ""; // No paginator.
		}
		
		StringBuilder out = new StringBuilder();
		out.append("<ul class=\"pagination\">\n");

		// Page 1.
		if (curPage > 1) {
			producePaginatorCell(linkBase, 1, "1", false, out);
		}
		
		// Page curPage-1;
		if (curPage > 2) {
			producePaginatorCell(linkBase, curPage-1, "&lt;", false, out);
		}
		
		// Page curPage.
		producePaginatorCell(linkBase, curPage, Integer.toString(curPage),  true, out);
		
		// Page curPage+1
		if (curPage+1 < totalPages) {
			producePaginatorCell(linkBase, curPage+1, "&gt;",  false, out);
		}
		
		// Page totalPages.
		if (curPage < totalPages) {
			producePaginatorCell(linkBase, totalPages, Integer.toString(totalPages),  false, out);
		}
		
		out.append("</ul>\n");
		return out.toString();
	}	
}
