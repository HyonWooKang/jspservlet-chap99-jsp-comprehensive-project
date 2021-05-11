<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	<div class="outer outer-board-list">
		<br>
		<h2 align="center">게시판</h2>
		<div class="table-area">
			<table align="center" id="listArea">
			<tr>
				<th width="100px">글번호</th>
				<th width="100px">카테고리</th>
				<th width="300px">글제목</th>
				<th width="100px">작성자</th>
				<th width="100px">조회수</th>
				<th width="150px">작성일</th>
			</tr>
			<c:forEach var="board" items="${ requestScope.boardList }">
			<tr>
				<td><c:out value="${ board.no }"/></td>
				<td><c:out value="${ board.category.name }"/></td>
				<td><c:out value="${ board.title }"/></td>
				<td><c:out value="${ board.writer.nickname }"/></td>
				<td><c:out value="${ board.count }"/></td>
				<td><c:out value="${ board.createdDate }"/></td>
			</tr>
			</c:forEach>
		</table>
		</div>
		
		<%-- 페이지 처리 --%>
		<div class="pagingArea" align="center">
			<c:choose>
			    <c:when test="${ empty requestScope.searchValue }">
				    <button id="startPage"><<</button>
	
					<c:if test="${ requestScope.pageInfo.pageNo <= 1 }">
						<button disabled><</button>
					</c:if>
					<c:if test="${ requestScope.pageInfo.pageNo > 1 }">
						<button id="prevPage"><</button>
					</c:if>
		
					<c:forEach var="p" begin="${ requestScope.pageInfo.startPage }" end="${ requestScope.pageInfo.endPage }" step="1">
						<c:if test="${ requestScope.pageInfo.pageNo eq p }">
							<button disabled><c:out value="${ p }"/></button>
						</c:if>
						<c:if test="${ requestScope.pageInfo.pageNo ne p }">
							<button onclick="pageButtonAction(this.innerText);"><c:out value="${ p }"/></button>
						</c:if>
					</c:forEach>
					
					<c:if test="${ requestScope.pageInfo.pageNo >= requestScope.pageInfo.maxPage }">
						<button disabled>></button>
					</c:if>
					<c:if test="${ requestScope.pageInfo.pageNo < requestScope.pageInfo.maxPage }">
						<button id="nextPage">></button>
					</c:if>
					
					<button id="maxPage">>></button> 
			     </c:when>
			    <c:otherwise>
   				    <button id="searchStartPage"><<</button>
	
					<c:if test="${ requestScope.pageInfo.pageNo <= 1 }">
						<button disabled><</button>
					</c:if>
					<c:if test="${ requestScope.pageInfo.pageNo > 1 }">
						<button id="searchPrevPage"><</button>
					</c:if>
		
					<c:forEach var="p" begin="${ requestScope.pageInfo.startPage }" end="${ requestScope.pageInfo.endPage }" step="1">
						<c:if test="${ requestScope.pageInfo.pageNo eq p }">
							<button disabled><c:out value="${ p }"/></button>
						</c:if>
						<c:if test="${ requestScope.pageInfo.pageNo ne p }">
							<button onclick="seachPageButtonAction(this.innerText);"><c:out value="${ p }"/></button>
						</c:if>
					</c:forEach>
					
					<c:if test="${ requestScope.pageInfo.pageNo >= requestScope.pageInfo.maxPage }">
						<button disabled>></button>
					</c:if>
					<c:if test="${ requestScope.pageInfo.pageNo < requestScope.pageInfo.maxPage }">
						<button id="searchNextPage">></button>
					</c:if>
					
					<button id="searchMaxPage">>></button> 
			    </c:otherwise>
			</c:choose>   
		</div>

		<!-- 검색 폼 -->
		<form id="loginForm" action="${ pageContext.servletContext.contextPath }/board/search" method="get">		
			<div class="search-area" align="center">
				<c:choose>
				    <c:when test="${ !empty requestScope.searchValue }">
   					    <select id="searchCondition" name="searchCondition">
							<option value="category" <c:if test="${requestScope.searchCondition eq 'category'}">selected</c:if>>카테고리</option>
							<option value="writer" <c:if test="${requestScope.searchCondition eq 'writer'}">selected</c:if>>작성자</option>
							<option value="title" <c:if test="${requestScope.searchCondition eq 'title'}">selected</c:if>>제목</option>
							<option value="content" <c:if test="${requestScope.searchCondition eq 'content'}">selected</c:if>>내용</option>
						</select>
				        <input type="search" id="searchValue" name="searchValue" value="${ requestScope.searchValue }">
				    </c:when>
				    <c:otherwise>
					    <select id="searchCondition" name="searchCondition">
							<option value="category">카테고리</option>
							<option value="writer">작성자</option>
							<option value="title">제목</option>
							<option value="content">내용</option>
						</select>
				        <input type="search" id="searchValue" name="searchValue" >
				    </c:otherwise>
				</c:choose>
				<button type="submit">검색하기</button>
				<c:if test="${ !empty requestScope.loginMember }">
					<button id="writeBoard">작성하기</button>
				</c:if>
			</div>
		</form>
	</div>
	<script>
		const link = "${ pageContext.servletContext.contextPath }/board/list";
		const searchLink = "${ pageContext.servletContext.contextPath }/board/search";
			
		if(document.getElementById("startPage")) {
			const $startPage = document.getElementById("startPage");
			$startPage.onclick = function() {
				location.href = link + "?currentPage=1";
			}
		}
		
		if(document.getElementById("prevPage")) {
			const $prevPage = document.getElementById("prevPage");
			$prevPage.onclick = function() {
				location.href = link + "?currentPage=${ requestScope.pageInfo.pageNo - 1 }";
			}
		}
		
		if(document.getElementById("nextPage")) {
			const $nextPage = document.getElementById("nextPage");
			$nextPage.onclick = function() {
				location.href = link + "?currentPage=${ requestScope.pageInfo.pageNo + 1 }";
			}
		}
		
		if(document.getElementById("maxPage")) {
			const $maxPage = document.getElementById("maxPage");
			$maxPage.onclick = function() {
				location.href = link + "?currentPage=${ requestScope.pageInfo.maxPage }";
			}
		}
		
		if(document.getElementById("searchStartPage")) {
			const $searchStartPage = document.getElementById("searchStartPage");
			$searchStartPage.onclick = function() {
				location.href = searchLink + "?currentPage=1&searchCondition=${ requestScope.searchCondition}&searchValue=${ requestScope.searchValue}";
			}
		}
		
		if(document.getElementById("searchPrevPage")) {
			const $searchPrevPage = document.getElementById("searchPrevPage");
			$searchPrevPage.onclick = function() {
				location.href = searchLink + "?currentPage=${ requestScope.pageInfo.pageNo - 1 }&searchCondition=${ requestScope.searchCondition}&searchValue=${ requestScope.searchValue}";
			}
		}
		
		if(document.getElementById("searchNextPage")) {
			const $searchNextPage = document.getElementById("searchNextPage");
			$searchNextPage.onclick = function() {
				location.href = searchLink + "?currentPage=${ requestScope.pageInfo.pageNo + 1 }&searchCondition=${ requestScope.searchCondition}&searchValue=${ requestScope.searchValue}";
			}
		}
		
		if(document.getElementById("searchMaxPage")) {
			const $searchMaxPage = document.getElementById("searchMaxPage");
			$searchMaxPage.onclick = function() {
				location.href = searchLink + "?currentPage=${ requestScope.pageInfo.maxPage }&searchCondition=${ requestScope.searchCondition}&searchValue=${ requestScope.searchValue}";
			}
		}
		
		if(document.getElementsByTagName("td")) {
			
			const $tds = document.getElementsByTagName("td");
			for(let i = 0; i < $tds.length; i++) {
				
				$tds[i].onmouseenter = function() {
					this.parentNode.style.backgroundColor = "orangered";
					this.parentNode.style.cursor = "pointer";
				}
				
				$tds[i].onmouseout = function() {
					this.parentNode.style.backgroundColor = "black";
				}
				
				$tds[i].onclick = function() {
					/* 게시물 번호까지 알아왔으니 이제 상세보기는 할 수 있겠지? */
					alert(this.parentNode.children[0].innerText);
				}
				
			}
			
		}
		
		function pageButtonAction(text) {
			location.href = link + "?currentPage=" + text;
		}
		function seachPageButtonAction(text) {
			location.href = searchLink + "?currentPage=" + text + "&searchCondition=${ requestScope.searchCondition}&searchValue=${ requestScope.searchValue}";
		}
	</script>
	
</body>
</html>