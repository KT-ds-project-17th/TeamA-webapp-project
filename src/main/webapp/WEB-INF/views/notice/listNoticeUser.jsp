 ..<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
request.setCharacterEncoding("UTF-8");
%>

<%@ page import="java.sql.*,java.text.SimpleDateFormat,java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">

<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css" />


<script>
	function deleteCheck(){
		var url = "${contextPath}/notice/deleteNotice.do";
		var cnt = $("input[name='ab']:checked").length;
		var valueArr = new Array();
		$("input[name='ab']:checked").each(function(i){
			valueArr.push($(this).val());
		});
		$.ajax({
			url : url,
			type : 'POST',
			traditional : true,
			data : {
				valueArr : valueArr
			},
			success : function(data){
				console.log("success");
				window.location.reload();
				/*$("#container").load("${contextPath}/syllabus/syllabusList.do");*/
			},
			error : function(data) { 
	            console.log("fail");
	            console.log(${noticeList});
	        }
		});
	};
</script>
<script type="text/javascript">
	$(document).ready(function() {

		$.extend($.fn.dataTable.defaults, {
			ordering : false
		});

		$('#noticeListUser').DataTable({
			dom : 'lBfrtp',
			language : {
				info : '총 _TOTAL_ 개의 공지사항 중 _START_번 부터 _END_번',
				infoEmpty : '데이터가 없습니다.',
				emptyTable : '데이터가 없습니다.',
				thousands : ',',
				lengthMenu : '_MENU_ 개씩 보기',
				loadingRecords : '데이터를 불러오는 중',
				processing : '처리 중',
				zeroRecords : '검색 결과 없음',
				paginate : {
					first : '처음',
					last : '끝',
					next : '다음',
					previous : '이전'
				},
				search : '',
				sSearchPlaceholder : '통합 검색',
			}

		});
	});
</script>


<title>공지사항 관리</title>

<style>
div.table {
	margin-top: 50px;
}

.sub_visual {
	font-family: 'Noto Sans KR', sans-serif;
	/* 	background-image:
		url("${pageContext.request.contextPath}/resources/image/sub_visual/faq.jpg");
	background-color: black;
	background-repeat: no-repeat;
	background-position: 50% 50%;
	background-size: cover; */
	width: 100%;
	background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
		url("${pageContext.request.contextPath}/resources/image/sub_visual/notice.jpg");
	border: 0;
	font-size: 32px;
	font-weight: 500;
	height: 190px;
	padding-top: 69px;
	background-position: 50% 50%;
	background-size: cover;
	background-repeat: no-repeat;
}

.fas {
	margin-left: 10px;
}
</style>
</head>

<body>

	<div class="sub_visual">
		<span style="color: white;">공지사항</span>
	</div>

	<div class="container">
		<div class="lnb">
			<ul>
				<li><a href="/springEx/main.do">홈</a></li>
				<li style="color: grey; font-weight: bold;">〉</li>
				<li class="on"><a href="/springEx/notice/listNoticeUser.do">공지사항
				</a></li>
			</ul>
		</div>

		<div class="table">
			<table id="noticeListUser" class="display">
				<thead>
					<tr>
						<th>분류</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="noticeFixList" items="${noticeFixList}">

						<tr>
							<td style="color: red;">${noticeFixList.notice_category}</td>

							<td class="name"><a
								href="${contextPath}/notice/readNoticeUser.do?notice_no=${noticeFixList.notice_no}">
									${noticeFixList.notice_title}</a> <c:if
									test="${noticeFixList.nt_file_size gt 0}">
									<i class="fas fa-file-alt"></i>
								</c:if> <c:if test="${noticeFixList.notice_date>=nowday}">
									 <span style = "color :darkred; margin-right:10px; font-size:12px;">new</span>
									<!--  <img
										src="${pageContext.request.contextPath}/resources/image/icon/new.png"
										width="30px" height="30px">-->
								</c:if></td>


							<td>${noticeFixList.notice_adminID}</td>
							<td>${noticeFixList.notice_date}</td>
							<td>${noticeFixList.notice_hit}</td>
						</tr>
					</c:forEach>



					<c:forEach var="noticeListUser" items="${noticeListUser}">

						<tr>
							<td>${noticeListUser.notice_category}</td>
							<td class="name"><a
								href="${contextPath}/notice/readNoticeUser.do?notice_no=${noticeListUser.notice_no}">
									${noticeListUser.notice_title}</a> <c:if
									test="${noticeListUser.nt_file_size gt 0}">
									<i class="fas fa-file-alt"></i>
								</c:if> <c:if test="${noticeListUser.notice_date>=nowday}">
									<span style = "color :darkred; margin-right:10px; font-size:12px;">new</span>
									<!--  <img
										src="${pageContext.request.contextPath}/resources/image/icon/new.png"
										width="30px" height="30px"> -->
								</c:if></td>

							<td>${noticeListUser.notice_adminID}</td>
							<td>${noticeListUser.notice_date}</td>
							<td>${noticeListUser.notice_hit}</td>
						</tr>
					</c:forEach>



				</tbody>


			</table>
		</div>



	</div>
</body>
</html>
