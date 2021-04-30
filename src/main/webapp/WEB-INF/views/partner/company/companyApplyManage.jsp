<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/modal.css" />

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<style>
.table thead th {
	width: 25%;
	vertical-align: bottom;
	border-bottom: 2px solid #dee2e6;
}

.flex-box {
	display: flex;
	justify-content: space-evenly;
}

.flex-col {
	display: flex;
	flex-direction: column;
}

#resumeTable {
	color: black;
	font-family: 'Noto Sans KR', sans-serif;
	width: 600px;
	margin-right: 40px;
}

#resumeTable th {
	text-align: center;
	background-color: #eee;
}

#resumeTable td {
	padding: 5px;
}

.d_divider {
	border-left: 3px solid green;
	height: 500px;
}

.c_content {
	float: left;
	width: 770px;
	word-break: keep-all;
	word-wrap: break-word;
}

.c_context {
	width: 990px;
	word-break: keep-all;
	word-wrap: break-word;
}

.s_str {
	float: left;
	width: 200px;
}

.r_row {
	clear: left;
}

.c_containerItem {
	margin-top: 100px;
	clear: left;
	border-bottom: 1px solid black;
}
</style>
<script>
        $(document).ready(function () {
            let activeTab = sessionStorage.getItem('activeTab');
            // ���ΰ�ħ �� �� ���� ����
            $('#myTab a[href="'+activeTab+'"]').trigger('click');
            $(".next").click(function(){
            	
           	$( '.modal-body' ).animate( { scrollTop : 0 }, 1000 );
			current_fs = $(this).parent();
            next_fs = $(this).parent().next();
            
            //Add Class Active
            $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

            //show the next fieldset
            next_fs.show();
            //hide the current fieldset with style
            current_fs.animate({opacity: 0}, {
            step: function(now) {
            // for making fielset appear animation
            opacity = 1 - now;

            current_fs.css({
            'display': 'none',
            'position': 'relative'
            });
            next_fs.css({'opacity': opacity});
            },
            duration: 600
            });
            });

            $(".previous").click(function(){
			
            $( '.modal-body' ).animate( { scrollTop : 0 }, 1000 );
            	
            current_fs = $(this).parent();
            previous_fs = $(this).parent().prev();

            //Remove class active
            $("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

            //show the previous fieldset
            previous_fs.show();

            //hide the current fieldset with style
            current_fs.animate({opacity: 0}, {
            step: function(now) {
            // for making fielset appear animation
            opacity = 1 - now;

            current_fs.css({
            'display': 'none',
            'position': 'relative'
            });
            previous_fs.css({'opacity': opacity});
            },
            duration: 600
            });
            });

            $('.radio-group .radio').click(function(){
            $(this).parent().find('.radio').removeClass('selected');
            $(this).addClass('selected');
            });

            $("#reset").click(function(){
            	location.reload(); 
				})
            
        });

        // ��� body text ����
        
        function getResumeInfo(resumeID, userID) {
        	
        	$.ajax({				// �񵿱����, �̷¼� ��������
	            method: "GET",
	            url: "${contextPath}/partner/getResumeByID.do?partnerApplyResumeID="+resumeID+"&partnerApplyUserID="+userID,
	            success: (resp) => {	// ��� ����� success�� ����
	            	
	            	console.log(resp);
	            	$("#modal_title").text(resp.resume.resumeUser+"�� �̷¼�");
                    $("#partner_info").text(resp.resume.resumeDate);
                    $("#partner_addr").text(resp.resume.resumeID);
                    $("#partner_email").text(resp.resume.resumeUser);
	            },
	            error: (err) => {
	                console.log(err+" �񵿱� ����");
	     	}
	        })
        	
            }

        // �� Ŭ�� �� session�� ���� �� �� ����
        function tabtab(h) {
            sessionStorage.setItem('activeTab', h);
            console.log('href   yyyy' + h);
        }


        // Check Pass Or Fail 
        function chk_passOrFail(userID, userName, partnerID) {
        	    Swal.fire({
        	        title:userName+'('+userID+')',
     				icon:'warning',
        	        confirmButtonText: `�հ�`,
        	        confirmButtonColor: '#3085d6',
        	        showCloseButton: true,
        	        showCancelButton: true,
        	        cancelButtonText: `���հ�`,
        	        cancelButtonColor: '#d33',
        	        }).then((res) => {
          			if(res.isConfirmed){
          				
          			Swal.fire({               /* update operation start */
          				     title:'�հ� ó��',
          				     text: '�հ� ó���Ͻðڽ��ϱ�?',
          				     showCancelButton: true,
          				     showCloseButton: true,
          				     icon:"success",
          				     confirmButtonColor: '#3085d6',
          				     cancelButtonColor: '#d33',
							 cancelButtonText: '���',
          				     confirmButtonText: '�հ�'
          				   }).then((result) => {
          				     /* Read more about isConfirmed, isDenied below */
          				     if (result.isConfirmed) {
          				    	 $.ajax({				//check update to pass
          		      	              method: "POST",
          		      	              url: "${contextPath}/partner/company/manageApply.do",
          		      	              data: {
          		      	            	partnerApplyUserID    : userID,
          		      	              	partnerApplyPartnerID : partnerID,
          		      	                partnerApplyState : '�հ�'
          		      	              },
          		      	              success: (resp) => {	// update to pass and reloading
          		      	            	location.reload();        
          		      	              },
          		      	              error: (data) => {
          		      	                  console.log("������ ���� ����"+data);
          		      	               }
          		      	          }) 
          				     } else{
          				         return;     /* cancel operation */
          				     }
          				   })
          			
      	          } else if( res.dismiss ==='cancel'){
      	        	Swal.fire({               /* check update to fail */
     				     title:'���հ� ó��',
     				     text: '���հ� ó���Ͻðڽ��ϱ�?',
     				     showCloseButton: true,
     				     showCancelButton: true,
     				     icon:"error",
     				     confirmButtonColor: '#d33',
     				     cancelButtonColor: '#3085d6#d33',
     				    cancelButtonText: '���',
     				     confirmButtonText: '���հ�'
     				   }).then((result) => {
     				     /* Read more about isConfirmed, isDenied below */
     				     if (result.isConfirmed) {
     				    	 $.ajax({				// update to fail
     		      	              method: "POST",
     		      	              url: "${contextPath}/partner/company/manageApply.do",
     		      	              data: {
     		      	            	partnerApplyUserID    : userID,
      		      	              	partnerApplyPartnerID : partnerID,
      		      	                partnerApplyState : '���հ�'
      		      	              
     		      	              },
     		      	              success: (resp) => {	// update to fail and reloading
     		      	            	location.reload();        
     		      	              },
     		      	              error: (data) => {
     		      	                  console.log("������ ���� ����"+data);
     		      	               }
     		      	          }) 
     				     } else{
     				         return;     /* cancel update */
     				     }
     				   })
      	        } else{
      	        	 return;
      	          }
          			  
        	      })
        	}
        
        function chk_reset(userID, userName, partnerID) {
    	    Swal.fire({
    	        title:'���� ó��',
    	        text:'�ش� ����� �����մϴ�',
 				icon:'warning',
    	        confirmButtonText: `�հ�`,
    	        confirmButtonColor: '#3085d6',
    	        showCloseButton: true,
    	        showCancelButton: true,
    	        cancelButtonText: `���հ�`,
    	        cancelButtonColor: '#d33',
    	        }).then((res) => {
      			if(res.isConfirmed){
      				
      			Swal.fire({               /* check editing */
      				     title:'�հ� ó��',
      				     text: '�հ� ó���Ͻðڽ��ϱ�?',
      				     showCancelButton: true,
      				     showCloseButton: true,
      				     icon:"success",
      				     confirmButtonColor: '#3085d6',
      				     cancelButtonColor: '#d33',
						 cancelButtonText: '���',
      				     confirmButtonText: '�հ�'
      				   }).then((result) => {
      				     /* Read more about isConfirmed, isDenied below */
      				     if (result.isConfirmed) {
      				    	 $.ajax({				// update to pass 
      		      	              method: "POST",
      		      	              url: "${contextPath}/partner/company/manageApply.do",
      		      	              data: {
      		      	            	partnerApplyUserID    : userID,
      		      	              	partnerApplyPartnerID : partnerID,
      		      	                partnerApplyState : '�հ�'
      		      	              },
      		      	              success: (resp) => {	// update success and reloading
      		      	            	location.reload();        
      		      	              },
      		      	              error: (data) => {
      		      	                  console.log("������ ���� ����"+data);
      		      	               }
      		      	          }) 
      				     } else{
      				         return;     /* cancel editing */
      				     }
      				   })
      			
  	          } else if( res.dismiss ==='cancel'){
  	        	Swal.fire({               /* edit to fail */
 				     title:'���հ� ó��',
 				     text: '���հ� ó���Ͻðڽ��ϱ�?',
 				     showCloseButton: true,
 				     showCancelButton: true,
 				     icon:"error",
 				     confirmButtonColor: '#d33',
 				     cancelButtonColor: '#3085d6',
 				    cancelButtonText: '���',
 				     confirmButtonText: '���հ�'
 				   }).then((result) => {
 				     /* Read more about isConfirmed, isDenied below */
 				     if (result.isConfirmed) {
 				    	 $.ajax({				// update to not allowed
 		      	              method: "POST",
 		      	              url: "${contextPath}/partner/company/manageApply.do",
 		      	              data: {
 		      	            	partnerApplyUserID    : userID,
  		      	              	partnerApplyPartnerID : partnerID,
  		      	                partnerApplyState : '���հ�'
  		      	              
 		      	              },
 		      	              success: (resp) => {	// update success and reloading
 		      	            	location.reload();        
 		      	              },
 		      	              error: (data) => {
 		      	                  console.log("������ ���� ����"+data);
 		      	               }
 		      	          }) 
 				     } else{
 				         return;     /* �������� ��� */
 				     }
 				   })
  	        } else{
  	        	 return;
  	          }
      			  
    	      })
    	}
        
        function suggestToUser(userID, partnerID, userName) {
        	Swal.fire({
    	    	html: `
    	    	<h2>ä�� ����</h2>
    	    	<input type="text" id="user" style="background-color: #eee;" class="swal2-input" readonly>
    	    	<input type="text" id="suggestTitle" placeholder="����" class="swal2-input">
    	    	<textarea type="text" id="suggestDesc" placeholder="���� ����" style="height:300px; padding-top: 5px;" class="swal2-input" rows="100"></textarea>`,
    	        confirmButtonText: `����`,
    	        confirmButtonColor: '#3085d6',
    	        showCloseButton: true,
    	        showCancelButton: true,
    	        cancelButtonText: `���`,
    	        cancelButtonColor: '#d33',
    	        onOpen: function() {
                    $('#user').attr("value", userName);
                },
                preConfirm: () => {			// pre confirm
                	const suggestDesc = Swal.getPopup().querySelector('#suggestDesc').value;
          			const suggestTitle = Swal.getPopup().querySelector('#suggestTitle').value;
          			if (!suggestDesc || !suggestTitle) {
        		      Swal.showValidationMessage(`Please enter title and Description`)	// check title and Descrtion is not null
        		    }
        		    else{
        		    	return;
        		}
          		}
    	        }).then((res) => {
      			if(res.isConfirmed){
      			const suggestDesc = Swal.getPopup().querySelector('#suggestDesc').value;
          		const suggestTitle = Swal.getPopup().querySelector('#suggestTitle').value;
          		Swal.fire({               /* check editing */
      				     title:'������ ����',
      				     text: 'ä�� �����Ͻðڽ��ϱ�?',
      				     showCancelButton: true,
      				     showCloseButton: true,
      				     icon:"success",
      				     confirmButtonColor: '#3085d6',
      				     cancelButtonColor: '#d33',
						 cancelButtonText: '���� ���',
      				     confirmButtonText: 'ä�� ����'
      				   }).then((result) => {
      				     /* Read more about isConfirmed, isDenied below */
      				     if (result.isConfirmed) {
      				    	
      				    	 $.ajax({				// update to pass 
      		      	              method: "POST",
      		      	              url: "${contextPath}/partner/company/manageSuggest.do",
      		      	              data: {
      		      	            	partnerSuggestionUserID    : userID,
      		      	              	partnerSuggestionPartnerID : partnerID,
      		      	              	partnerSuggestionTitle : suggestTitle,
      		      	                partnerSuggestionDescription : suggestDesc
      		      	              },
      		      	              success: (resp) => {	// update success and reloading
      		      	            	 Swal.fire('���ȿϷ�!', '', 'success').then(()=>{
      		      	            	 location.reload(); 
      		      	             	})
      		      	              },
      		      	              error: (data) => {
      		      	                  console.log("������ ���� ����"+data);
      		      	               }
      		      	          })  
      				     } else{
      				         return;     /* cancel editing */
      				     }
      				   })
      			
  	          } else{
  	        	 return;
  	          } 
    	      })
    	}
        
        function deleteSuggestion(userID, partnerID){
        	 Swal.fire({
     	        title:'ä������ ����',
     	        text:'���� �Ŀ� ������ �� �����ϴ�.',
  				icon:'warning',
     	        confirmButtonText: `����`,
     	        confirmButtonColor: '#d33',
     	        showCloseButton: true,
     	        showCancelButton: true,
     	        cancelButtonText: `���`,
     	        cancelButtonColor: '#3085d6',
     	        }).then((res) => {
       			if(res.isConfirmed){
       				    	 $.ajax({				//check update to pass
       		      	              method: "POST",
       		      	              url: "${contextPath}/partner/company/deleteSuggest.do",
       		      	              data: {
       		      	            	partnerSuggestionUserID    : userID,
       		      	              	partnerSuggestionPartnerID : partnerID,
       		      	                partnerSuggestionPartnerD : 'N'
       		      	              },
       		      	              success: (resp) => {	// update to pass and reloading
       		      	            	location.reload();        
       		      	              },
       		      	              error: (data) => {
       		      	                  console.log("������ ���� ����"+data);
       		      	               }
       		      	          }) 
       			} 
   	          else{
   	        	 return;
   	          	 }
       			})
        }
     </script>
<body>
	<div id="applyContents">
		<div class="sub_visual">
			<span style="color: white;">���»� ����</span>
		</div>
		<div class="container"
			style="display: flex; flex-wrap: wrap; width: 75%; justify-content: space-around; flex-direction: column; padding-bottom: 200px;">

			<!-- Modal -->
			<!-- <div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog modal-dialog-scrollable">
 -->
			<div class="modal fade bd-example-modal-lg" id="myModal"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-lg modal-dialog-scrollable">
					<!-- Modal content-->
					<div class="modal-content">

						<div class="modal-header">
							<h5 class="modal-title" id="modal_title"></h5>
							<button type="button" class="close" data-dismiss="modal">��</button>
						</div>
						<div class="modal-body">
							<div class="container-fluid" id="grad1">
								<div class="row justify-content-center mt-0">
									<div>
										<div class="card px-0 pt-4 pb-0 mt-3 mb-3">
											<div class="row">
												<div class="col-md-12 mx-0">
													<form id="msform">
														<!-- progressbar -->
														<ul id="progressbar">
															<li class="active" id="basic"><strong>�⺻����</strong></li>
															<li id="personal"><strong>�ڰ��� ����</strong></li>
															<li id="education"><strong>��»���</strong></li>
															<li id="project"><strong>������Ʈ</strong></li>
															<li id="introduce"><strong>�ڱ�Ұ���</strong></li>
														</ul>
														<!-- fieldsets -->
														<fieldset id="init">
															<div class="form-card">
																<h2 class="fs-title">�⺻����</h2>
																<table border id="resumeTable">
																	<tr>
																		<th rowspan="4"><img
																			src="http://jjunstudio.com/zbxe/files/attach/images/351/652/85a698d051126aa4043e83f4ff2376a0.jpg"
																			style="width: 122px; height: 163px;" /></th>
																	</tr>
																	<tr>
																		<th>����</th>
																		<td>������</td>
																		<th>������</th>
																		<td>Min ju Kang</td>
																	</tr>
																	<tr>
																		<!-- &nbsp; = ��ĭ ���� -->
																		<th colspan="1" style="width: 84px;">����</th>
																		<td colspan="1" style="width: 165px;">28��</td>
																		<th colspan="1" width="15%">����</th>
																		<td colspan="1" width="200px">����</td>

																	</tr>
																	<tr>
																		<th colspan="1">�������</th>
																		<td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;
																			��&nbsp;&nbsp;&nbsp;��</td>
																	</tr>
																	<tr>
																		<th>�ּ�</th>
																		<td colspan="4">����� ��걸 û�ķ� 251 �ٿó����
																			513ȣkkkkkkkkkkkkkkkkkkkkkkkkkkkkk</td>
																	</tr>
																	<tr>
																		<th rowspan="2">����ó</th>
																		<th>��ȭ��ȣ</th>
																		<td colspan="4">031-000-0000</td>
																	</tr>
																	<tr>
																		<th>Email</th>
																		<td colspan="4">test@test.com</td>
																	</tr>
																	<tr>
																		<th rowspan="3">�з»���</th>
																		<th>�����з�</th>
																		<td colspan="4">���б� ����</td>
																	</tr>
																	<tr>
																		<th>����</th>
																		<td colspan="4">��ǻ�Ͱ��а� (���� :4.5)</td>
																	</tr>
																	<tr>
																		<th>����</th>
																		<td colspan="4">4.0/4.5</td>
																	</tr>

																</table>
															</div>
															<input type="button" name="next"
																class="next action-button" value="����" />
														</fieldset>
														<fieldset>
															<div class="form-card">
																<h2 class="fs-title">�ڰ��� ����</h2>
																<table border id="resumeTable">
																<tr>
																<th>�ڰ��� ��</th>
																<th>����ó/���</th>
																<th>�����</th>
																</tr>
																<tr>
																<td>SQLD</td>
																<td>�ѱ� �����ͺ��̽� ��������</td>
																<td>2021.04.01</td>
																</tr>
																</table>
																
																<h2 class="fs-title" style="margin-top:100px">���� ����</h2>
																<table border id="resumeTable">
																<tr>
																<th>���</th>
																<th>��������</th>
																<th>����</th>
																<th>�����</th>
																</tr>
																<tr>
																<td>����</td>
																<td>TOEIC</td>
																<td>990</td>
																<td>2021.04.01</td>
																</tr>
																</table>
															</div>
															<input type="button" name="previous"
																class="previous action-button-previous" value="����" /> <input
																type="button" name="next" class="next action-button"
																value="����" />
														</fieldset>
														<fieldset>
															<div class="form-card">
																<h2 class="fs-title">��»���</h2>
																<table border id="resumeTable">
																<tr>
																<td colspan="2" style="background-color:#eee;">ȸ���</td>
																</tr>
																<tr>
																<td>��±Ⱓ</td>
																<td> 2021.02.01~2021.02.28</td>
																</tr>
																<tr>
																<td>����</td>
																<td>
																����</td>
																</tr>
																<tr>
																<td>�������</td>
																<td>
																������
																</td>
																</tr>
																
																<tr>
																<td colspan="2" style="background-color:#eee;">ȸ���</td>
																</tr>
																<tr>
																<td>��±Ⱓ</td>
																<td> 2021.02.01~2021.02.28</td>
																</tr>
																<tr>
																<td>����</td>
																<td>
																����</td>
																</tr>
																<tr>
																<td>�������</td>
																<td>
																������
																</td>
																</tr>
																
																<tr>
																<td colspan="2" style="background-color:#eee;">ȸ���</td>
																</tr>
																<tr>
																<td>��±Ⱓ</td>
																<td> 2021.02.01~2021.02.28</td>
																</tr>
																<tr>
																<td>����</td>
																<td>
																����</td>
																</tr>
																<tr>
																<td>�������</td>
																<td>
																������
																</td>
																</tr>
																
																</table>
															</div>
															<input type="button" name="previous"
																class="previous action-button-previous" value="����" /> <input
																type="button" name="next" class="next action-button"
																value="����" />
														</fieldset>

														<fieldset>
															<div class="form-card">
																	<h2 class="fs-title">������Ʈ</h2>
																<table border id="resumeTable">
																<tr>
																<td colspan="2" style="background-color:#eee;">�����</td>
																</tr>
																<tr>
																<td style="width: 110px;">������Ʈ ��</td>
																<td>������ ������Ʈ</td>
																</tr>
																<tr>
																<td>�Ⱓ</td>
																<td> 2021.02.01~2021.02.28</td>
																</tr>
																<tr>
																<td>����ȯ��<br>
																�� �����</td>
																<td>
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																</td>
																</tr>
																<tr>
																<td>������Ʈ �Ұ�</td>
																<td>
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																</td>
																</tr>
																<tr>
																<td>����� ����</td>
																<td>�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��</td>
																</tr>
																
																<tr>
																<td colspan="2" style="background-color:#eee;">�����</td>
																</tr>
																<tr>
																<td style="width: 110px;">������Ʈ ��</td>
																<td>������ ������Ʈ</td>
																</tr>
																<tr>
																<td>�Ⱓ</td>
																<td> 2021.02.01~2021.02.28</td>
																</tr>
																<tr>
																<td>����ȯ��<br>
																�� �����</td>
																<td>
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																</td>
																</tr>
																<tr>
																<td>������Ʈ �Ұ�</td>
																<td>
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																</td>
																</tr>
																<tr>
																<td>����� ����</td>
																<td>�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��</td>
																</tr>
																
																</table>
															</div>
															<input type="button" name="previous"
																class="previous action-button-previous" value="����" /> <input
																type="button" name="next" class="next action-button"
																value="����" />
														</fieldset>

														<fieldset>
															<div class="form-card">
															<h2 class="fs-title">�ڱ�Ұ���</h2>
																<table border id="resumeTable">
																<tr>
																<th style="width: 110px;">�������</th>
																<td>
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																</td>
																</tr>
																<tr>
																<th>�б���Ȱ</th>
																<td>
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																</td>
																</tr>
																<tr>
																<th>���� (��/����)</th>
																<td>
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																</td>
																</tr>
																<tr>
																<th>������� �� �巡����</th>
																<td>
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��
																</td>
																</tr>
																<tr>
																<th>��Ÿ����</th>
																<td>�����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ�İ����ٶ󸶹ٻ����īŸ��</td>
																</tr>
																
																
																</table>
															</div>
															<input type="button" name="previous"
																class="previous action-button-previous" value="����" />
														</fieldset>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- <div class="partnerInfoModalBody" style="text-align: left">
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
							</div> -->
						</div>
						<div class="modal-footer">
							<button type="button" id="reset" class="btn btn-default"
								data-dismiss="modal">Ȯ��</button>
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
									<li class="nav-item"><a id="firstNav" href="#nav-home"
										data-toggle="tab" onclick="tabtab('#nav-home')"
										class="nav-link active">���� ���</a></li>
									<li class="nav-item"><a id="secondNav" href="#nav-profile"
										data-toggle="tab" onclick="tabtab('#nav-profile')"
										class="nav-link">ä�� ����</a></li>
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
												<th>������</th>
												<th>�� �� ����</th>
												<%-- <c:forEach var="apList" items="${applyList}">
												<c:choose>
												<c:when test="${apList.partnerApplyState != '������'}">
												 --%>
												<th>��� ����</th>
												<%-- </c:when>
												</c:choose>
												</c:forEach>
											 --%>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="apList" items="${applyList}">
												<tr align="center">
													<td>${apList.memberVO.userName}</td>
													<td><a class="info" data-toggle="modal"
														href="#myModal"
														onclick="getResumeInfo('${apList.partnerApplyResumeID}','${apList.memberVO.userId}');">
															<i class="fas fa-search"></i>
													</a></td>
													<c:choose>
														<c:when test="${apList.partnerApplyState == '������'}">
															<td><a style="text-decoration: underline" href="#"
																onclick="chk_passOrFail('${apList.memberVO.userId}','${apList.memberVO.userName}','${apList.partnerApplyPartnerID}');"><i
																	class="fas fa-user-check"></i></a></td>
															<td></td>
														</c:when>
														<c:when test="${apList.partnerApplyState == '�հ�   '}">
															<td style="color: blue;">${apList.partnerApplyState}</td>
															<td><a style="text-decoration: underline" href="#"
																onclick="chk_reset('${apList.memberVO.userId}','${apList.memberVO.userName}','${apList.partnerApplyPartnerID}');"><i
																	class="fas fa-user-edit"></i></a>
														</c:when>
														<c:otherwise>
															<td style="color: red;">${apList.partnerApplyState}</td>
															<td><a style="text-decoration: underline" href="#"
																onclick="chk_reset('${apList.memberVO.userId}','${apList.memberVO.userName}','${apList.partnerApplyPartnerID}');"><i
																	class="fas fa-user-edit"></i></a>
														</c:otherwise>
													</c:choose>
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
												<th>�̸�</th>
												<th>�̷¼�</th>
												<th>ä������</th>
												<th>����</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="sugList" items="${suggestionList}">
												<tr>
													<td>${sugList.userName}</td>
													<td><a class="info" data-toggle="modal"
														href="#myModal"
														onclick="getResumeInfo('${sugList.resumeVO.resumeID}','${sugList.userId}');">
															<i class="fas fa-search"></i>
													</a></td>

													<c:choose>
														<c:when
															test="${sugList.suggestionVO.partnerSuggestionAcception == null}">
															<td><a style="text-decoration: underline" href="#"
																onclick="suggestToUser('${sugList.userId}','${partner.partnerLicenseNum}','${sugList.userName}');return false;"><i
																	class="fas fa-hands-helping"></i></a></td>
														</c:when>
														<c:when
															test="${sugList.suggestionVO.partnerSuggestionAcception == '����'}">
															<td style="color: blue;">${sugList.suggestionVO.partnerSuggestionAcception}</td>
														</c:when>
														<c:when
															test="${sugList.suggestionVO.partnerSuggestionAcception == '���'}">
															<td style="color: green;">${sugList.suggestionVO.partnerSuggestionAcception}</td>
														</c:when>
														<c:otherwise>
															<td style="color: red;">${sugList.suggestionVO.partnerSuggestionAcception}</td>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when
															test="${sugList.suggestionVO.partnerSuggestionPartnerD != null }">
															<td><a style="text-decoration: underline" href="#"
																onclick="deleteSuggestion('${sugList.userId}','${partner.partnerLicenseNum}');"><i
																	class="fas fa-user-times"></i></a></td>
														</c:when>
														<c:otherwise>
															<td></td>
														</c:otherwise>
													</c:choose>
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

