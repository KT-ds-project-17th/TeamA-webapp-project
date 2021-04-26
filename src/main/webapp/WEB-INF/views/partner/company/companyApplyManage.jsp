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
        $(document).ready(function () {
            let activeTab = sessionStorage.getItem('activeTab');

            // ���� ���� ���� �� ����
            $("span:contains('������')").css({color: "green"});
            $("span:contains('���հ�')").css({color: "blue"});
            $("span:contains('�հ�')").css({color: "red"});

            // ���ΰ�ħ �� �� ���� ����
            $('#myTab a[href="' + activeTab + '"]').trigger('click');
        });

        // ��� body text ����
        
        function getResumeInfo(resumeID) {
        	console.log(resumeID);
   
        	 fetch("${contextPath}/partner/getResumeByID.do", {
                  method: "GET",
                  mode: "cors",
                  headers: {
                      "Content-Type": "application/json",
                      "accept": "application/json"
                  },
                  body: JSON.stringify({
                      partnerApplyResumeID: resumeID
                  })
              })
                  .then(res => {
                	  console.log(res);
            		/* 
                	  $("#modal_title").text(name);
                      $("#partner_info").text(info);
                      $("#partner_addr").text(addr);
                      $("#partner_email").text(email);
                      $("#partner_headcnt").text(headcnt);
                      $("#partner_purl").text(purl); */
                  })
                  .catch(e => console.log(e));
            }

        // �� Ŭ�� �� session�� ���� �� �� ����
        function tabtab(h) {
            sessionStorage.setItem('activeTab', h);
            console.log('href   yyyy' + h);
        }

        // ���� ���� �Լ�
        function deleteApplication(partnerApplyPartnerID) {
            const id = '${member.userId}';
            fetch("${contextPath}/member/deleteApplication.do", {
                method: "POST",
                mode: "cors",
                headers: {
                    "Content-Type": "application/json",
                    "accept": "application/json"
                },
                body: JSON.stringify({
                    partnerApplyUserID: '${member.userId}',
                    partnerApplyPartnerID: partnerApplyPartnerID
                })
            })
                .then(res => {
                    console.log(res);

                    swal("���� ���� �Ϸ�.", "���� ���� �Ϸ�.", "success");
                    setTimeout(function () { // 0.9�ʵ� ����
                        location.reload(); // ���ΰ�ħ -> list �ٽ� �ҷ���
                    }, 900);

                })
                .catch(e => console.log(e));
        }

        // ���� �Լ�
        function chk_apply(a, b, c) {

            // ������� �̷¼��� ��� �Ǿ��ִٸ�
            if ('${member.resume}' === "Y") {

                fetch("${contextPath}/member/userApplyPartner.do", {
                    method: "POST",
                    mode: "cors",
                    headers: {
                        "Content-Type": "application/json",
                        "accept": "application/json"
                    },
                    body: JSON.stringify({
                        partnerApplyUserID: b,
                        partnerApplyPartnerID: c
                    })
                })
                    .then(res => {
                        if (res.status == '500') {
                            // mybatis ���� �� 500 error
                            swal("�ߺ� ����.", "�ߺ� ����.", "info");
                        } else {
                            swal("���� �Ϸ�.", "���� �Ϸ�.", "success");
                            setTimeout(function () { // 0.9�ʵ� ����
                                location.reload(); // ���ΰ�ħ -> list �ٽ� �ҷ���
                            }, 900);
                        }
                    })
                    .catch(e => console.log(e));

            //�̷¼��� ���ٸ�
            } else {
                swal("��� �� �̷¼� ����.", "�̷¼��� �ʿ��մϴ�.", "warning");
            }
        }
    </script>
<body>
<div id="applyContents">
    <div class="sub_visual">
        <span style="color: white;">���»� ����</span>
    </div>
    <div class="container">

        <!-- Modal -->
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
                                    <p>�Ұ�</p>
                                    <p>�ּ�</p>
                                    <p>�����</p>
                                    <p>�̸���</p>
                                    <p>������Ʈ</p>
                                </div>
                                <div class="col-8">
                                    <p id="partner_info"></p>
                                    <p id="partner_addr"></p>
                                    <p id="partner_headcnt"></p>
                                    <p id="partner_email"></p>
                                    <p id="partner_purl"></p>
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
                                <li class="nav-item">
                                    <a id="firstNav" href="#nav-home" data-toggle="tab" onclick="tabtab('#nav-home')"
                                       class="nav-link active">���� ���</a>
                                </li>
                                <li class="nav-item">
                                    <a id="secondNav" href="#nav-profile" data-toggle="tab"
                                       onclick="tabtab('#nav-profile')" class="nav-link">ä�� ����</a>
                                </li>
                            </ul>
                        </nav>

                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel"
                                 aria-labelledby="nav-home-tab">
                                <%--                ù��° ���� ���̺�                --%>
                                <table class="table" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th>�̸�</th>
                                        <th>������</th>
                                        <th>ä������</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="apList" items="${applyList}">
                                        <tr align="center">
                                      	  <td>${apList}</td>
                                         	<td>${apList.memberVO.userName}</td>
                                         	<td><a title="������� ����" style="text-decoration: underline" class="info"
                                                   data-toggle="modal" href="#myModal"
                                                   onclick="getResumeInfo('${apList.partnerApplyResumeID}');"><i class="far fa-id-card"></i></a>
                                            </td>
                                           
                                            <td><a style="text-decoration: underline" href="#"
                                                   onclick="chk_apply('${member.resume}', '${member.userId}', '${recruit.partnerLicenseNum}');return false;">�����ϱ�</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="nav-profile" role="tabpanel"
                                 aria-labelledby="nav-profile-tab">
                                <%--                �ι�° ���� ���̺�                --%>
                                <table class="table" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th>�����</th>
                                        <th>���� ��¥</th>
                                        <th>���� ����</th>
                                        <th>�����ϱ�</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="application" items="${applicationList}">
                                        <tr>
                                            <td>${application.partnerName}</td>
                                            <td>${application.partnerApplyDate}</td>
                                            <td><span>${application.partnerApplyState}</span></td>
                                            <td><a style="text-decoration: underline" href="#"
                                                   onclick="deleteApplication('${application.partnerApplyResumeID}');return false;">������
                                                ����</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
</body>
</html>

