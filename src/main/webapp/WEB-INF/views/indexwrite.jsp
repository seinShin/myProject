<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var = "modeText" value = "등록"/>
<c:choose>
	<c:when test = "${mode == 'INS'}">
		<c:set var = "modeText" value = "등록" />	
	</c:when>
	<c:otherwise>
		<c:set var = "modeText" value = "수정"/>
	</c:otherwise>
</c:choose>

        <!-- Content Wrapper -->
            <!-- Main Content -->
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">게시판</h1>
                    <p class="mb-4">BOARD PROJECT</p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"></h6>
                        </div>
                        <div class="card-body">
                        	<form id="boardFrm" name="boardFrm" method="post">
                        		<input type="hidden" id="seq" name="seq" value="">
								<input type="hidden" id="nowPage" name="nowPage" value="${nowPage}">
								<input type="hidden" id="mode" name="mode" value="${mode}">
	                            <div class="table-responsive">
	                             
	                            <div class="form-group">
                                  <label for="title" class="control-label col-md-3 col-sm-3 col-xs-12">제목<span class="required">*</span></label>
								  <div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" id="title" name="title" class="form-control col-md-7 col-xs-12 exp" value="">
								  </div>
                                </div>   
	                                
	                               
	                            <div class="form-group">
                                   <label for="contents" class="control-label col-md-3 col-sm-3 col-xs-12">내용<span class="required">*</span></label>
									<div class="col-md-6 col-sm-6 col-xs-12">
										<textarea id="contents" name="contents" class="form-control col-md-7 col-xs-12 exp" value="">
										</textarea>
									</div>
                                 </div>   
									<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
										<button type="button" class="btn btn-primary" onclick="indexwrite.fn_delete()">삭제</button>
										<button type="button" class="btn btn-primary" onclick="indexwrite.fn_save()">완료</button>	
									</div>
	                            </div>
	                        </form>    
                        </div>
                    </div>

                </div>
            <!-- End of Main Content -->
<script type="text/javascript">
	var mode="${mode}"
	var seq="${seq}";
	console.log(mode);
	$(function(){
		if (mode=="MOD"){
			ajaxParamExecute("seq="+seq, "/board/rest/indexInfo", "post", false, false, indexwrite.fn_siteInfoReturn);
		}
	});
	
	let indexwrite={

			fn_siteInfoReturn : function(rst){
				console.log(rst);
				$('#title').val(rst.resultInfo.title);
				$('#contents').val(rst.resultInfo.content);
				$('#seq').val(rst.resultInfo.seq);
				
			
			},
			fn_list : function(){
				$("#boardFrm").prop("action", "index");
				$("#boardFrm").submit();
			},
			fn_view : function(seq){
				$('#seq').val(seq);
				$("#boardFrm").prop("action", "indexview");
				$("#boardFrm").submit();
			},
			fn_save : function(){
				
				if(!$('#title').val() || !$('#title').val().trim()){
					alert("제목을 입력해주세요.");
					$('#title').focus();
					return false;
				}

				if(!$('#contents').val() || !$('#contents').val().trim()){
					alert("내용을 입력해주세요.");
					$('#contents').focus();
					return false;
				}
				
				if(confirm('${modeText}하시겠습니까?')){
					ajaxFileFormExecute("boardFrm", "/board/rest/indexWrite", "post", false, false, indexwrite.fn_saveReturn);
				}
			},
			fn_saveReturn : function(rst){
				if(rst.sw){
					alert('${modeText}되었습니다.');
					if(rst.seq != ""){
						indexwrite.fn_view(rst.seq);
					}
					else{
						indexwrite.fn_list();
					}
				}else{
					alert('${modeText}에 실패하였습니다. \n관리자에게 문의바랍니다.');
				}
			},
	}
	
</script>