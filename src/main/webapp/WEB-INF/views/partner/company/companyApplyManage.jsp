<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div class="container">
		<h2>지원자 관리</h2>
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-dialog-scrollable">

				<!-- Modal content-->
				<div class="modal-content">

					<div class="modal-header">
						<h5 class="modal-title" id="modal_title"></h5>
						<button type="button" class="close" data-dismiss="modal">×</button>
					</div>
					<div class="modal-body">
						<div class="partnerInfoModalBody" style="text-align: left">
							<div class="row">
								<div class="col-3" style="color: #444444; font-weight: bold">
									<p>소개</p>
									<p>주소</p>
									<p>사원수</p>
									<p>이메일</p>
									<p>웹사이트</p>
								</div>
								<div class="col-8">
									<p>qqq</p>
									<p>qqq</p>
									<p>qqq</p>
									<p>qqq</p>
									<p>qqq</p>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
					</div>
				</div>

			</div>
		</div>



		<section id="tabs" class="project-tab">
			<div>
				<div class="row">
					<div class="col-md-12">
						<nav style="margin-top: 100px;">

							<ul class="nav nav-tabs" id="myTab" role="tablist">
								<li class="nav-item"><span id="firstNav" href="#nav-home"
									data-toggle="tab" class="nav-link active">지원 목록</span></li>
							</ul>
						</nav>

						<div class="tab-content" id="nav-tabContent">
							<div class="tab-pane fade show active" id="nav-home"
								role="tabpanel" aria-labelledby="nav-home-tab">
								<%--                첫번째 탭의 테이블                --%>
								<table class="table" cellspacing="0">
									<thead>
										<tr>
											<th>이름</th>
											<th>이력서</th>
											<th>수강정보</th>
											<th>합격여부</th>
										</tr>
									</thead>
									<tbody>
										<tr align="center">
											<td>강민주</td>

											<td><a title="기업정보 보기"
												style="text-decoration: underline" class="info"
												data-toggle="modal" href="#myModal"><i
													class="fas fa-file-alt"></i></a></td>
											<td><a title="기업정보 보기"
												style="text-decoration: underline" class="info"
												data-toggle="modal" href="#myModal"><i
													class="fas fa-user-graduate"></i></a></td>
											<td><select class="form-select"
												style="border: solid 1px black;">
													<option selected>대기</option>
													<option value="유료과정">합격</option>
													<option value="재직자향상">불합격</option>
											</select>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>

