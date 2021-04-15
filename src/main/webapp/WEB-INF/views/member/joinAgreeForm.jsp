<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
request.setCharacterEncoding("UTF-8");
%>


<!DOCTYPE html>
<html lang="ko">

<head>
<title></title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/style.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box
}

body {
	background-color: #f7f7f7;
}

ul>li {
	list-style: none
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
	background: linear-gradient( rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5) ), url("${pageContext.request.contextPath}/resources/image/sub_visual/faq.jpg");
	border: 0;
	font-size: 32px;
	font-weight: 500;
	height: 190px;
	padding-top: 69px;
	background-position: 50% 50%;
	background-size: cover;
	background-repeat: no-repeat;
}

a {
	text-decoration: none;
}

.clearfix::after {
	content: "";
	display: block;
	clear: both;
}


.container {
	font-family: 'Noto Sans KR', sans-serif;
	width: 80%;
}

#joinForm {
	width: 697px;
	margin: 0 auto;
	margin-top: 5%;
	margin-left: auto;
	margin-right: auto;
}

ul.join_box {
	border: 1px solid #ddd;
	background-color: #fff;
	height: 500px;
	text-align: left;
	border-radius: 5px;
}

.checkBox, .checkBox>ul {
	position: relative;
}

.checkBox>ul>li {
	float: left;
}

.checkBox>ul>li:first-child {
	width: 85%;
	padding: 15px;
	font-weight: 600;
	color: #888;
	text-decoration: underline;
}

.checkBox>ul>li:nth-child(2) {
	position: absolute;
	top: 50%;
	right: 30px;
	margin-top: -12px;
}

.checkBox textarea {
	width: 96%;
	height: 143px;
	margin: 0 2%;
	background-color: #f7f7f7;
	color: #888;
	border: none;
}

.footBtwrap {
	margin-top: 15px;
}

.footBtwrap>li {
	float: left;
	width: 50%;
	height: 60px;
}

.footBtwrap>li>button {
	display: block;
	width: 100%;
	height: 100%;
	font-size: 20px;
	text-align: center;
	line-height: 60px;
}

.fpmgBt1 {
	background-color: #fff;
	color: #888
}

.fpmgBt2 {
	background-color: #FF0000;
	color: #fff
}

#123 {
	text-align: left;
	text-decoration: underline;
}
</style>


<script type="text/javascript">
	$(document).ready(
			function() {

				// 체크박스 전체 선택
				$(".join_box").on(
						"click",
						"#allchx",
						function() {
							$(this).parents(".join_box").find('input').prop(
									"checked", $(this).is(":checked"));
						});

				// 체크박스 개별 선택
				// 개별선택을 하나 해제할 경우 전체선택도 해제
				$(".join_box").on("click", ".ab", function() {
					var is_checked = true;

					$(".join_box .ab").each(function() {
						is_checked = is_checked && $(this).is(":checked");
					});

					$("#allchx").prop("checked", is_checked);
				});

				//뭐 하나 체크 안되었을 경우 경고창 띄움
				$("#fpmgBt2").click(function() {
					if ($("#cb1").is(":checked") == false) {
						Swal.fire({
							icon : 'error',
							title : '모든 약관에 동의하세요!',

						})
						return false;
					} else if ($("#cb2").is(":checked") == false) {
						Swal.fire({
							icon : 'error',
							title : '모든 약관에 동의하세요!',

						})
						return false;
					} else {
						$("#fpmgBt2").submit();
					}
				});
			});
</script>
</head>

<div class="sub_visual">
	<span style="color: white;">회원가입</span>
</div>


<div class="container">
	<body>
		<form action="${contextPath}/member/memberJoinForm.do" id="joinForm">
			<ul class="join_box">
				<li class="checkBox check01">
					<ul class="clearfix">
						<li id="123">이용약관, 개인정보 수집 및 이용에 모두 동의합니다.</li>
						<li class="checkAllBtn"><input type="checkbox" name="all"
							id="allchx" class="check-all"></li>
					</ul>
				</li>
				<li class="checkBox check02">
					<ul class="clearfix">
						<li>이용약관 동의(필수)</li>
						<li class="checkBtn"><input type="checkbox" name="cb1"
							id="cb1" class="ab"></li>
					</ul> <textarea readonly>제 1 장 
총칙 제1조 [목적]
이 약관은 주식회사 케이티디에스(이하 "회사")가 제공하는 웹사이트(이하 "사이트")를
이용함에 있어 사이트와 회원간의 권리, 의무 및 책임 사항, 절차 등을 규정함을 목적으로 합니다.
                    
제2조 [용어의 정의]
① 본 약관에서 사용하는 용어의 정의는 다음과 같습니다.
1. 사이트 : 회사가 서비스를 제공하기 위하여 컴퓨터 등 정보통신 설비를 이용하여 설정한 웹사이트를 말한다.
2. 회원 : 사이트에 접속하여 본 약관에 동의하고 개인정보를 제공하여 회원등록을 하고 회사가 제공하는 서비스를 이용하는 자
3. 고유번호(ID) : 회원등록시 회원 식별과 회원의 서비스 이용을 위하여 회원이 선정하고 회사가 승인하는 문자와 숫자의 조합
4. 비밀번호(Password) : 회원이 통신상의 자신의 비밀을 보호하기 위해 선정한 문자와 숫자의 조합 (영문과 숫자로 이루어진 8자리 이상 등록가능)
5. 교육과정 : 회사가 교육 수강 희망자에게 제공하는 교육 서비스
6. 교육비 : 교육과정을 이용한 대가로 회사가 별도로 정한 요금체계에 의해 특정 기간단위로 부과하는 금액
7. 수강승인 : 회원의 수강신청에 대하여 회사가 정한 요건을 충족시킨 경우 수강 신청자가 정상적으로 교육서비스를 이용할 수 있도록 승인하는 것
② 본 약관에서 사용하는 용어의 정의는 제1항에서 정하는 것을 제외하고는 관계법령 및 서비스별 안내에서 정하는 바에 따릅니다.
                    
제3조 [약관의 효력]
① 본 약관은 사이트를 이용하고자 하는 모든 회원에 대하여 그 효력을 발생합니다.
② 회사는 본 약관의 내용과 회사의 상호, 소재지, 대표자의 성명, 사업자등록번호, 연락처(전화, 팩스, e-mail주소 등) 등을 이용자가 알 수 있도록 사이트의 초기 서비스화면에 게시합니다.
③ 회사는 약관의규제에관한법률, 전자거래기본법, 전자서명법, 정보통신망이용촉진및정보보호등에관한법률, 전자상거래등에서의소비자보호에관한법률, 방문판매등에관한법률, 소비자보호법, 개인정보보호법 등 관련법을 위배하지 않는 범위에서 본 약관을 변경할 수 있습니다.
④ 회사는 필요하다고 인정되는 경우 본 약관을 변경할 수 있으며, 약관이 변경되는 경우에는 변경된 약관의 적용일자 및 사유를 명시한 변경된 약관과 현행 약관을 함께 지체없이 제8조에서 정한 방법으로 회원에게 공시합니다. 약관 변경 이전의 기존 회원이 제4항의 공시 기간 동안 변경된 약관에 동의하는 경우 변경약관의 조항이 적용됩니다.
⑤ 회원이 변경된 약관에 동의하지 않으면 사이트를 탈퇴하여 서비스의 이용 계약을 해지할 수 있습니다. 단, 이의가 있음에도 제4항에 정한 바에 따른 회사의 고지가 있은 후 30일 이내에 이용 계약을 해지하지 않은 회원은 변경된 약관에 동의한 것으로 간주합니다.
                    
제4조 [약관 이외의 준칙]
① 본 약관에 명시되지 않은 사항은 공정거래법 및 기타 관련법령 규정에 따릅니다.
② 회사와 회원간의 서비스 이용계약과 관련하여 본 약관에서 정하지 아니한 사항과 본 약관의 해석은 일반 상관례에 따릅니다.
                    
제 2 장 서비스 이용계약
                    
제5조 [이용계약의 성립]
① 이용계약은 서비스이용신청자의 이용신청에 대하여 회사가 승낙함으로써 성립합니다.
② 서비스 이용신청자는 회사가 정한 양식에 따라 회원정보를 기입한 후 본 약관에 동의한다는 의사표시를 함으로써 회원가입 신청을 할 수 있습니다. 단, 서비스 이용신청자는 반드시 실명으로 이용신청을 하여야 하며, 1개의 ID만 신청을 할 수 있습니다.
③ 회사는 다음 각 호의 1에 해당하는 경우 이용 신청에 대한 승낙을 제한할 수 있고, 그 사유가 해소될 까지 승낙을 유보할 수 있습니다.
1. 서비스 관련 설비의 용량이 부족한 경우
2. 기술상 장애 사유가 있는 경우
3. 기타 회사가 필요하다고 인정되는 경우
④ 회사는 다음 각 호의 1에 해당하는 사항을 인지하는 경우 동 이용 신청을 승낙하지 아니합니다.
1. 이름이 실명이 아닌 경우
2. 다른 사람의 명의를 사용하여 신청한 경우
3. 가입신청서의 내용을 허위로 기재한 경우
4. 사회의 안녕질서 또는 미풍양속을 저해할 목적으로 신청한 경우
5. 기타 회사 소정의 이용신청요건을 충족하지 못하는 경우
⑤ 제3항 또는 제4항에 의하여 이용신청의 승낙을 유보하거나 승낙하지 아니하는 경우, 회사는 이를 이용신청자에 알려야 합니다.
다만, 회사의 귀책사유 없이 이용신청자에게 통지할 수 없는 경우는 예외로 합니다.
                    
제6조 [회원 ID(고유번호)와 Password(비밀번호)관리에 대한 의무와 책임]
① 회사는 사이트 내에서의 유료 서비스 신청시 이용료를 부과할 수 있으므로, 회원은 ID 및 Password 관리를 철저히 하여야 합니다.
② 회원 ID와 Password의 관리 소홀, 부정 사용에 의하여 발생하는 모든 결과에 대한 책임은 회원 본인에게 있으며, 회사의 시스템 고장 등 회사의 책임있는 사유로 발생하는 문제에 대한 책임은 회사에 있습니다.
③ 다음 각 호의 1에 해당하는 경우 회사는 직권 또는 회원의 신청에 의하여 회원ID를 변경할 수 있습니다.
1. 회원ID가 회원의 전화번호,주민등록번호 등으로 등록되어 있어서 회원의 사생활을 침해할 우려가 있는 경우
2. 타인에게 협오감을 주거나 미풍양속에 어긋하는 경우
3. 기타 회사 소정의 합리적인 사유가 있는 경우
                    
제7조 [계약사항의 변경]
회원은 이용신청 시 기재한 사항이 변경되었을 경우 회사가 정한 별도의 정해진 양식 및 이용방법으로 변경사항을 수정해야 합니다.
                    
제8조 [회원에 대한 통지]
① 회사는 회원이 회사에 제출한 e-mail 또는 SMS 등 기타 통신수단을 이용하여 회원에 대한 통지를 할 수 있습니다.
② 회사는 불특정다수 회원에 대한 통지의 경우 1주일 이상 사이트 또는 사이트의 게시판 등에 게시함으로써 개별통지를 갈음 할 수 있습니다.
                    
제9조 [회원 탈퇴]
① 회원은 언제든지 탈퇴를 요청할 수 있으며 회사는 즉시 해당 탈퇴요청을 처리합니다.
② 탈퇴시 회원이 제공한 정보는 지체없이 파기함을 원칙으로 합니다.
단, 법령의 규정에 의하여 보존할 필요성이 있는 경우에는 예외로 회원의 정보의 파기를 유보합니다.
                    
제 3 장 서비스 제공 및 이용
                    
제10조 [서비스의 내용]
① 회사가 제공하는 서비스는 다음과 같습니다.
1. 전문교육 온라인 신청
2. 장기교육 온라인 지원
3. My Page
② 회사는 필요한 경우 서비스의 내용을 추가 또는 변경할 수 있습니다.
                    
제11조 [서비스의 이용 개시]
① 회사는 회원의 이용신청을 승낙한 때부터 서비스를 개시합니다.
② 회사의 업무상 또는 기술상의 장애로 인하여 서비스를 개시하지 못하는 경우에는 회사는 제8조에 정한 방법으로 회원에게 통지합니다.
                    
제12조 [서비스의 이용시간]
① 서비스의 이용은 회사의 업무상 또는 기술상의 특별한 지장이 없는 한 연중무휴, 1일 24시간을 원칙으로 합니다.
② 회사는 다음 각 호에 해당하는 경우 서비스의 전부 또는 일부를 제한하거나 중지할 수 있습니다.
1. 컴퓨터 등 정보통신설비의 보수점검, 교체 및 고장, 통신의 두절 등의 사유가 발생한 경우
2. 서비스를 위한 설비의 보수 등 공사로 인해 부득이한 경우
3. 정전, 제반설비의 장애 또는 이용량의 폭주 등으로 정상적인 서비스 이용에 지장이 있는 경우
4. 기타 천재지변, 국가비상사태 등 불가항력적 사유가 있는 경우
③ 본조 제2항에 의한 서비스 중단의 경우에는 회사가 제8조에서 정한 방법으로 회원에게 통지합니다.
단, 회사가 통제할 수 없는 사유로 인한 서비스의 중단(운영자의 고의, 과실이 없는 시스템 장애 등)으로 인하여 사전 통지가 불가능한 경우에는 그러하지 아니합니다.
                    
제13조 [서비스 요금]
회사가 제공하는 서비스는 무료서비스와 유료서비스로 구분됩니다.
                    
제14조 [정보의 제공]
① 회사는 서비스를 운영함에 있어 각종 정보를 사이트 화면에 게재하거나 e-mail, SMS 등의 방법으로 회원에게 제공할 수 있습니다.
② 회사는 서비스 운영과 관련하여 사이트화면, SMS, e-mail 등에 광고 등의 정보를 게재할 수 있습니다.
③ 회원이 게재되어 있는 광고를 이용하거나 광고주와 교신 또는 거래를 하는 것은 전적으로 회원과 광고주 간의 문제입니다.
따라서 회원과 광고주 간에 문제가 발생한 경우 회사는 책임이 없습니다.
                    
제15조 [게시물의 저작권]
① 회원이 사이트 내에 게시한 게시물(회원간 전달 포함)의 저작권은 회원이 소유하며 회사는 사이트 내에 이를 게시할 수 있는 권리를 갖습니다.
② 회사는 게시한 회원의 동의 없이 게시물을 다른 목적으로 사용할 수 없습니다.
③ 회사는 회원이 서비스 내에 게시한 게시물이 타인의 저작권, 프로그램저작권 등을 침해 하더라도 이에 대한 민형사상의 책임을 부담하지 않습니다.
만일 회원이 타인의 저작권, 프로그램저작권 등을 침해하였음을 이유로 회사가 타인으로부터 손해배상청구 등 이의 제기를 받은 경우 회원은 회사의 면책을 위하여 노력하여야 하며, 회사가 면책되지 못한 경우 회원은 그로 인해 회사에 발생한 모든 손해를 부담하여야 한다.
④ 회사는 회원이 계약을 해지하거나 적법한 사유로 해지된 경우 해당 회원이 게시하였던 게시물을 삭제할 수 있습니다.
⑤ 회사가 작성한 저작물에 대한 저작권은 회사에 귀속합니다.
                    
제 4 장 계약 당사자의 법률관계
                    
제16조 [회사의 의무]
① 회사는 서비스의 제공과 관련하여 알고 있는 회원의 신상정보를 본인의 승락없이 제3자에게 누설, 배포하지 않습니다.
단, 법률의 규정에 따른 적법한 절차에 의한 경우에는 그러하지 않습니다.
② 회사는 개인정보 보호정책을 공시하고 준수합니다.
③ 회사는 이용고객으로부터 제기되는 의견이나 불만이 정당하다고 객관적으로 인정될 경우에는 적절한 절차를 거쳐 즉시 처리하여야 합니다.
다만, 즉시처리가 곤란한 경우는 이용자에게 그 사유와 처리일정을 통보하여야 합니다.
                    
제17조 [회원의 의무]
① 회원은 회원가입 신청 또는 회원정보 변경 시 실명으로 모든 사항을 사실에 근거하여 작성하여야 하며, 허위 또는 타인의 정보를 등록할 경우 일체의 권리를 주장할 수 없습니다.
② 회원은 본 약관에서 규정하는 사항과 기타 회사가 정한 제반 규정, 공지사항 등 회사가 공지하는 사항 및 관계 법령을 준수하여야 하며, 기타 회사의 업무에 방해가 되는 행위, 회사의 명예를 손상시키는 행위를 해서는 안됩니다.
③ 회원은 서비스를 이용할 때 다음 각호의 행위를 해서는 안됩니다.
1. 이용신청 또는 변경 시 허위 사실을 기재하거나, 다른 회원의 ID 및 비밀번호를 도용, 부정하게 사용하는 행위
2. 회사의 서비스 정보를 이용하여 얻은 정보를 회사의 사전 승낙없이 복제 또는 유통시키거나 상업적으로 이용하는 행위
3. 타인의 명예를 손상시키거나 불이익을 주는 행위
4. 게시판 등에 음란물을 게재하거나 음란사이트를 연결(링크)하는 행위
5. 회사의 저작권, 제3자의 저작권 등 기타 권리를 침해하는 행위
6. 공공질서 및 미풍양속에 위반되는 내용의 정보, 문장, 도형, 음성 등을 타인에게 유포하는 행위
7. 서비스와 관련된 설비의 오 동작이나 정보 등의 파괴 및 혼란을 유발시키는 컴퓨터 바이러스 감염 자료를 등록 또는 유포하는 행위
8. 서비스 운영을 고의로 방해하거나 서비스의 안정적 운영을 방해할 수 있는 정보를 전송하는 행위
9. 타인으로 가장하는 행위 및 타인과의 관계를 허위로 명시하는 행위
10. 다른 회원의 개인정보를 수집, 저장, 공개하는 행위
11. 자기 또는 타인에게 재산상의 이익을 주거나 타인에게 손해를 가할 목적으로 허위의 정보를 유통시키는 행위
12. 서비스에 게시된 정보를 변경하는 행위
13. 법령에 의하여 그 전송 또는 게시가 금지되는 정보(컴퓨터 프로그램 포함)의 전송 또는 게시하는 행위
14. 회사의 직원이나 운영자를 가장하거나 사칭하여 또는 타인의 명의를 도용하여 글을 게시하거나 메일을 발송하는 행위
15. 컴퓨터 소프트웨어, 하드웨어, 전기통신 장비의 정상적인 가동을 방해, 파괴할 목적으로 고안된 소프트웨어 바이러스 기타 다른 컴퓨터 코드, 파일, 프로그램을 포함하고 있는 자료를 게시하거나 e-mail을 발송하는 행위
16. 기타 불법적이거나 부당한 행위
                    
제18조 [양도금지]
① 회원은 서비스의 이용권한, 기타 이용 계약상 지위를 타인에게 양도, 증여할 수 없으며, 게시물에 대한 저작권을 포함한 모든 권리 및 책임은 이를 게시한 회원에게 있습니다.
② 회사가 제3자에게 합병 또는 분할합병되거나 서비스를 제3자에게 양도함으로써 서비스의 제공 주체가 변경되는 경우, 사전에 제8조의 통지방법으로 회원에게 통지합니다. 이 경우 합병, 분할합병, 서비스 양도에 반대하는 회원은 회원탈퇴를 함으로써 서비스 이용계약을 해지할 수 있습니다.
                    
제19조 [개인정보의 위탁]
회사는 수집된 개인정보의 취급 및 관리 등의 업무를 스스로 수행함을 원칙으로 하나, 필요한 경우 업무의 일부 또는 전부를 회사가 선정한 업체에 위탁할 수 있습니다.
                    
제20조 [소비자 불만 접수처리]
① 회원은 회사에 대해서 불만사항이나 문의사항이 있을 경우 홈페이지 고객의 소리나 e-mail, 전화 등을 이용하여 처리 접수를 할 수 있고 회사는 이의 처리에 성실하게 수행합니다.
② e-mail주소, 전화번호 등은 홈페이지 상에 공지합니다.
                    
제 5 장 집합교육 서비스의 이용
                    
제21조 [사용자 PC환경]
① 수강신청자는 집합교육 신청 서비스를 이용하기 위하여 인터넷에 연결된 PC가 준비되어야 합니다.
                    
제22조 [수강신청]
① 집합교육 신청자는 회사가 정한 수강신청절차를 통하여 수강신청을 할 수 있습니다.
② 집합교육 수강신청자는 반드시 실명으로 수강신청을 하여야 합니다.
                    
제23조 [수강신청 효력발생]
수강신청은 수강신청 희망자의 수강신청을 회사가 승인함으로써 효력이 발생합니다.
                    
제24조 [수강료]
① 집합교육 서비스를 이용하고자 하는 회원은 수강료를 납입하여야 합니다.
② 제1항의 규정에 의한 수강료, 그 납입방법 및 납입기간은 다음 각호에 따릅니다.
1. 수강료는 회사가 정한 요금체계에 의하며 사이트에서 공지한 가격을 기준으로 합니다.
2. 수강료는 회사가 정한 지정은행 및 지정계좌 또는 신용카드로 납입합니다.
3. 납입기간은 수강신청 종료일을 납입종료일로 합니다.
③ 미성년자는 법정대리인을 수강료 납입자로 하여 수강신청을 할 수 있습니다.
④ 개인명의로 집합교육 서비스를 이용하고 개인이 수강료를 납부하는 경우 회원명은 반드시 회원 자신의 본명(실명)이어야 하며, 수강료 납부 책임자는 유료서비스 이용 회원 외에 타인으로 지정할 수 있습니다. 단, 유료서비스 이용자와 수강료 납부 책임자가 다른 경우 회사는 이를 확인할 수 있는 제증명을 요구할 수 있습니다.
⑤ 개인명의로 집합교육 서비스를 이용하고 개인이 속한 법인 등 단체가 수강료를 납부하는 경우 회원명은 반드시 서비스를 이용하는 회원 자신의 본명(실명)이어야 하며, 수강료 납부는 서비스 이용 회원과 이용 회원이 속한 법인 등의 단체가 연대하여 책임을 집니다.
⑥ 법인명의로 집합교육 서비스를 이용하고 법인 등 단체가 수강료를 납부하는 경우 사업자등록증 1부를 팩스, 우편, 방문 등으로 제출하여야 합니다.
                    
제25조 [세금계산서 발급]
수강료 납입과 관련하여 세금계산서 발급을 원하는 경우 수강신청자는 즉시 회사에 사업자등록증 1부를 팩스, 우편, 방문 등으로 제출하여야 합니다.
                    
제26조 [결제수단 및 방법]
수강료의 결제 수단은 현금으로 한정하며 무통장 입금, 계좌이체, 신용카드 또는 기타 회사가 인정하는 결제수단으로 할 수 있습니다.
                    
제27조 [수강승인]
① 회사는 수강신청에 대하여 회사가 정한 지정은행 및 지정계좌에 수강료 납입 사실 확인 후 또는 신용카드 결제승인 후 수강신청을 승인합니다.
② 회사가 수강신청을 승인하는 경우 회원에 대하여 서비스를 통하여 다음 각 호의 사항을 통지합니다.
1. 수강신청 과목, 기간
2. 수료기준
3. 교육 전 준비사항
③ 회사는 다음 각 호의 1에 해당하는 경우 수강신청에 대한 승인을 제한할 수 있고, 그 사유가 해소될 까지 승인을 유보할 수 있습니다.
1. 집합교육 유료서비스 과정의 수강료를 납입하는데 지연이 되는 경우
2. 수강신청 사항 중 계산서 발급에 필요한 회사명, 사업자등록번호 등이 누락된 경우
3. 기타 회사가 필요하다고 인정되는 경우
④ 회사는 다음 각 호의 1에 해당하는 사항을 인지하는 경우 등 수강신청을 승인하지 아니합니다.
1. 집합교육 서비스 과정의 수강료를 납입하지 아니하고 수강신청 기간을 경과한 경우
2. 이름이 실명이 아닌 경우
3. 수강신청서의 내용을 허위로 기재한 경우
4. 기타 회사 소정의 수강신청요건을 충족하지 못하는 경우
⑤ 제3항 또는 제4항에 의하여 수강신청의 승인을 유보하거나 승인하지 아니하는 경우, 회사는 이를 수강신청자에 알려야 합니다. 다만, 회사의 귀책사유 없이 수강신청자에게 통지할 수 없는 경우는 예외로 합니다.
                    
제28조 [수강취소 및 환불신청]
① 회사는 회원이 사이트의 <수강취소>메뉴를 이용하여 집합교육 서비스의 수강취소를 요청하는 경우, 다음 각 호의 요건에 따라 환불이 가능합니다.
<1개월 이내 교육>
- 교육시작 전 : 전액환불 가능
- 총 교육시간의 1/3이 지나기 전 : 이미 낸 교육비의 2/3 해당액
- 총 교육시간의 1/2이 지나기 전 : 이미 낸 교육비의 1/2 해당액
- 총 교육시간의 1/2이 지난 후 : 환불액 없음
<1개월 초과 교육>
- 교육 시작 후 환불사유가 발생한 그 달의 환불 대상 교육비(교육비 징수기간이 1개월 이내인 경우에 준하여 산출된 교육비를 말한다)와 나머지 달의 교육비 전액을 합산한 금액을 환불합니다.
② 수강취소 시 제공받은 교보재 등은 환불처리를 위하여 반드시 반납하셔야 합니다.
③ 회사의 귀책사유로 인해 수강취소 처리된 경우에는 상기 조항이 적용되지 않습니다.
④ 수강신청자에게 수강취소 전에 받은 수강료에 대해서는 지체없이 환불에 필요한 조치를 취합니다.
                    
제29조 [수료확인]
① 집합교육 서비스 수강자의 수료처리는 회사가 정한 기준과 절차에 따라 처리를 합니다.
② 집합교육의 수료여부 및 수료증은 사이트에서 확인 할 수 있습니다.
                    
제30조 [고용보험 환급]
집합교육 서비스 수강자가 고용보험법상의 환급을 신청하는 경우에는 관계법 및 법령에서 요구하는 기준 및 요건에 부합되어야 하며, 이를 위해 수강자 소속의 고용보험 담당자를 통해 충분히 숙지하여야 합니다.
                    
제 6 장 손해배상 등
제31조 [손해배상]
① 회원이 본 약관의 규정을 위반함으로 인하여 회사에 손해가 발생하게 되는 경우, 이 약관을 위반한 회원은 회사에 발생하는 모든 손해를 배상하여야 합니다.
② 회원이 서비스를 이용함에 있어 행한 불법행위나 본 약관 위반행위로 인하여 회사가 당해 회원 이외의 제3자로부터 손해배상 청구 또는 소송을 비롯한 각정 이의제기를 받는 경우 당해 회원은 자신의 책임과 비용으로 회사를 면책시켜야 하며, 회사가 면책되지 못한 경우 당해 회원은 그로 인하여 회사에 발생한 모든 손해를 배상하여야 합니다.
                    
제32조 [면책사항]
① 회사는 천재지변 또는 이에 준하는 불가항력으로 인하여 서비스를 제공할 수 없는 경우에는 서비스 제공에 관한 책임이 면제됩니다.
② 회사는 회원의 귀책사유로 인한 서비스의 이용장애에 대하여 책임을 지지 않습니다.
③ 회사는 회원 상호간 또는 회원과 제3자 상호간에 서비스를 매개로 발생한 분쟁에 대해서는 개입할 의무가 없으며 이로 인한 손해를 배상할 책임도 없습니다.
                    
제33조 [관할법원]
① 회사는 천재지변 또는 이에 준하는 불가항력으로 인하여 서비스를 제공할 수 없는 경우에는 서비스 제공에 관한 책임이 면제됩니다.
② 회사는 회원의 귀책사유로 인한 서비스의 이용장애에 대하여 책임을 지지 않습니다.
③ 회사는 회원 상호간 또는 회원과 제3자 상호간에 서비스를 매개로 발생한 분쟁에 대해서는 개입할 의무가 없으며 이로 인한 손해를 배상할 책임도 없습니다.
                    
                    
   </textarea>
				</li>
				<li class="checkBox check03">
					<ul class="clearfix">
						<li>개인정보 수집 및 이용에 대한 안내(필수)</li>
						<li class="checkBtn"><input type="checkbox" name="cb2"
							id="cb2" class="ab"></li>
					</ul> <textarea readonly>개인정보의 수집 및 이용 목적

회사는 수집한 개인정보를 다음의 목적을 위해 활용합니다
가.
교육지원 및 교육이력 관리
나.
고용보험 환급 관련 업무
다.
회원제 서비스 이용에 따른 본인확인, 개인 식별, 불량회원의 부정 이용 방지와 비인가사용 방지, 가입 의사 확인, 연령확인, 불만처리 등 민원처리, 고지사항 전달
                    
                    
수집하는 개인정보의 항목
                    
가. [필수항목]
o 이름(국문/영문), 회원아이디, 비밀번호, 휴대전화번호, 이메일, 주소
o 교육 신청서 등에 제공한 정보 : 신청한 교육분야 및 교육과정
나. [선택정보] 필수적 정보 외에 고용보험 환급 등을제공하기 위해 추가 오프라인 수집 정보
o 고용보험환급관련 정보(고용보험 환급과정 수강신청 시 별도 수집) : 주민등록번호
o 보험가입관련 정보 (보험가입 필수과정 수강신청 시 별도 수집) : 주민등록번호
o 전화번호(유선), 소속
다. [기타정보] 서비스 이용과정이나 사업처리 과정에서 생성/수집될 수 있는 정보
o 서비스 이용기록(출결사항, 접속로그)
o 오프라인 교육 시 도난 등의 방지 등을 위해 설치한 CCTV 등 취득한 영상정보
o 이용자의 브라우저 종류 및 OS, 방문 기록(IP 주소, 접속시간), 쿠키
                    
                    
개인정보의 보유 및 이용기간
                    
개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체없이 파기합니다. 참고로 kt ds는 '개인정보 유효기간제'에 따라 1년간 서비스를 이용하지 않은 회원의 개인정보를 별도로 분리 보관하여 관리하고 있습니다. 단, 다음의 정보에 대해서는 아래의 이유로 명시한 기간 동안 보관한 뒤 파기합니다
가. 교육서비스 이용
o 보존 항목 :
성명, 회원아이디, 비밀번호, 휴대폰번호, 이메일주소, 소속, 전화번호, 주소, 직급, 교육담당자, 담당자연락처, 담당자 이메일, 관심분야(경영/리더십), 관심분야(IT), 안내정보수신 이메일, 이메일 수신여부, 수강신청정보, 교육이력
o 보존이유 : 교육 이력 관리
o 보존기간 : 학습자 회원 탈퇴시까지

나. 관련 법령에 의한 정보보유 사유
관계법령의 규정에 의하여 보존할 필요가 있는 경우 회사는 관계법령에서 정한 일정한 기간 동안 회원정보를 보관합니다. 이 경우 회사는 보관하는 정보를 그 보관의 목적으로만 이용하며 보존기간은 아래와 같습니다
               
보존항목/내용	보존기간	보존근거 / 사유
개인정보(이름, 자택 전화번호, 자택주소, 휴대전화번호, 이메일, 회사명, 부서, 직책, 회사전화번호, 주민등록번호)	3년	근로자 직업능력 개발법 고용보험 환급 적정성 심의
계약 또는 청약철회 등에 관한 기록	5년	전자상거래 등에서의 소비자 보호에 관한 법률
대금결제 및 재화 등의 공급에 관한 기록	5년
소비자의 불만 또는 분쟁처리에 관한 기록	3년
본인확인에 관한 기록	6개월	정보통신 이용촉진 및 정보보호 등에 관한 법률
방문에 관한 기록(로그인 기록)	3개월	통신비밀 보호법

다. 기타사유

o보유기간을 이용자에게 미리 고지하고 그 보유기간이 경과하지 아니한 경우와 개별적으로 이용자께 사전에 동의를 받은 경우에는 약속한 보유기간 동안 보유
o 보존이유 : 교육 이력 관리
o 보존기간 : 학습자 회원 탈퇴시까지
                    
                    
동의를 거부할 권리 및 동의를 거부할 경우의 불이익
                    
위 개인정보 중 필수적 정보의 수집·이용에 관한 동의는 대외교육 개인식별을 위하여 필수적이므로, 위 사항에 동의하셔야만 교육수강이 가능합니다. 위 개인정보 중 선택적 정보의 수집·이용에 관한 동의는 거부하실 수 있으며, 다만 동의하지 않으시는 경우 서비스 이용에 제한을 받으실 수 있습니다.
   </textarea>

				</li>

			</ul>

			<ul class="footBtwrap clearfix">

				<li><button type="reset" class="fpmgBt1" id="fpmgBt1"
						name="fpmgBt1">비동의</button></li>
				<li><button type="submit" class="fpmgBt2" id="fpmgBt2"
						name="fpmgBt2">동의</button></li>
			</ul>
		</form>
</div>
</body>


</html>