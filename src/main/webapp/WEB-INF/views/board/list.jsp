<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
 <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Modern Business - Start Bootstrap Template</title>
        <!-- css favicon -->
        <c:import url="../temp/style.jsp"></c:import>
        <style type="text/css">
        	h1 {
        		text-transform:capitalize;
        	} 
        	a {
        		text-decoration-line:none;"
        	}
        </style>
        
    </head>
   
<body class="d-flex flex-column h-100">
  <main class="flex-shrink-0">
      	<!-- Navigation -->
      <c:import url="../temp/header.jsp"></c:import>
    <section class="bg-light py-5">
    <div class="container px-5 my-5">
 	 	<div class="text-center mb-5">
	         <a href="./list"><h1 class="fw-bolder">${board} List</h1></a>
	         <p class="lead fw-normal text-muted mb-0">${board}</p>
     	 </div>
     	 
     	 <div>
     	 	<table class="table table-hover">
     	 		<thead>
     	 		`	<tr>
     	 				<th>NUM</th>
     	 				<th>Title</th>	
     	 				<th>Writer</th>
     	 				<th>Date</th>
     	 				<th>Hit</th>
     	 			</tr>
     	 		</thead>
     	 		<tbody>
     	 			<c:forEach items="${list}" var="boardVO">
     	 				<tr>
     	 					<td>${boardVO.num}</td>
     	 					<td><a href="./detail?num=${boardVO.num}">${boardVO.title}</a></td>
     	 					<td>${boardVO.writer}</td>
     	 					<td>${boardVO.regDate}</td>
     	 					<td>${boardVO.hit}</td>
     	 				</tr>
     	 			</c:forEach>
     	 		</tbody>
     	 	</table>
     	 </div>
     	 <c:if test="${pager.pre}">
     	 	<a href="./list?page=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}">이전</a>
     	 </c:if>
     	 
     	 <c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
     	 	<a href="./list?page=${i}">${i}</a>
     	 </c:forEach>
     	 
     	 <c:if test="${pager.next}">
     	 	<a href="./list?page=${pager.lastNum+1 }">다음</a>
     	 </c:if>
     	 
     	 <form action="./list" method="get">
     	 	<select name="kind">
     	 		<option value="title">제목</option>
     	 		<option value="writer">작성자</option>
     	 		<option value="contents">내용</option>
     	 	</select>
     	 	
     	 	<input type="text" name="search">
     	 	<button type="submit">Search</button>
     	 </form>
		
		<!-- 페이로드에 잘 보내졌지만 잘 받아졌는지 확인하기 위해서는 controller로 이동 -->
		<!-- controller에서 잘 보내졌는지 확인 -> service -> dao ->  -->
		<!-- mapper에서 쿼리문이 잘 받아졌는지 확인 -->
		<!-- 응답받고도 확인 -->
     </div>    
    </section>
    
    <sec:authorize access="hasRole('ADMIN')">
    <a href="./add">WRITE</a>
    </sec:authorize>
  </main>
  

 <!-- footer 적용 -->
      <c:import url="../temp/footer.jsp"></c:import>
     <!-- footer 끝 -->
     
     
</body>
</html>