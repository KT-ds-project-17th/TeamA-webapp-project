<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<style>
</style>
<script>
	$(document).ready(function() {
		$('#myModal').appendTo("body").modal('show');
	})
</script>
<body>
	<div class="container" style="float: left; transform: translateY(20%);">
		<h2>������ ����</h2>
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-dialog-scrollable">

				<!-- Modal content-->
				<div class="modal-content">

					<div class="modal-header">
						<h5 class="modal-title" id="modal_title"></h5>
						<button type="button" class="close" data-dismiss="modal">��</button>
					</div>
					<div class="modal-body">
						<div class="partnerInfoModalBody" style="text-align: left">
							<div class="row">
								<div class="col-3" style="color: #444444; font-weight: bold">
									<p>�̸�</p>
									<p>�ּ�</p>
									<p>�̸���</p>
									<p>�ڱ�Ұ�</p>
									<p>�������</p>
								</div>
								<div class="col-8">
									<p>������</p>
									<p>���� ���ʱ� ��赿 ��迪 1���ⱸ</p>
									<p>rkdalswn1209@naver.com</p>
									<p>�̾Ƹ� �ֽŴٸ� ȸ�縦 ���� �а� ����ϸ� ������ ȸ�翡 �ٴϵ��� �ϰ����ϴ�.</p>
									<p>Spring, Oracle</p>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Ȯ��</button>
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
									data-toggle="tab" class="nav-link active">���� ���</span></li>
							</ul>
						</nav>

						<div class="tab-content" id="nav-tabContent">
							<div class="tab-pane fade show active" id="nav-home"
								role="tabpanel" aria-labelledby="nav-home-tab">
								<%--                ù��° ���� ���̺�                --%>
								<table class="table" cellspacing="0">
									<thead>
										<tr>
											<th>�̸�</th>
											<th>�̷¼�</th>
											<th>�հݿ���</th>
										</tr>
									</thead>
									<tbody>
										<tr align="center">
											<td>������</td>

											<td><a title="������� ����"
												style="text-decoration: underline" class="info"
												data-toggle="modal" href="#myModal"><i
													class="fas fa-file-alt"></i></a></td>
											<td><select class="form-select"
												style="border: solid 1px black;">
													<option selected>���</option>
													<option value="�������">�հ�</option>
													<option value="���������">���հ�</option>
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

