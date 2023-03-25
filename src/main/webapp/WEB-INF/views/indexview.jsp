<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
	                            <div class="table-responsive">
	                             
	                            <div class="form-group">
                                  <label for="title" class="control-label col-md-3 col-sm-3 col-xs-12">제목<span class="required">*</span></label>
								  <div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" id="title" name="title" class="form-control col-md-7 col-xs-12 exp" value=""  disabled="disabled">
								  </div>
                                </div>   
	                               
	                            <div class="form-group">
                                   <label for="contents" class="control-label col-md-3 col-sm-3 col-xs-12">내용<span class="required">*</span></label>
									<div class="col-md-6 col-sm-6 col-xs-12">
										<textarea id="contents" name="contents" class="form-control col-md-7 col-xs-12 exp" value=""  disabled="disabled"></textarea>
									</div>
                                 </div>  
                                  
									<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
										<button type="button" class="btn btn-primary" onclick="indexViewObj.fn_delete()">삭제</button>
										<button type="button" class="btn btn-primary" onclick="indexViewObj.fn_modify()">수정</button>
									</div>
	                            </div>
	                        </form>    
                        </div>
                    </div>

                </div>
            <!-- End of Main Content -->

<script type = "text/javascript">
	var seq = "${seq}";
	$(document).ready(function(){
		indexViewObj.fn_siteInfo('${seq}');
		console.log('nowPage =' + '${nowPage}');
	});
	
	let indexViewObj={
			
			fn_siteInfo : function(seq){
				ajaxParamExecute("seq="+seq, "/board/rest/indexInfo", "post", false, false, indexViewObj.fn_siteInfoReturn);
			},	
			fn_siteInfoReturn : function(rst){
				console.log(rst.resultInfo.content);
				$('#seq').val(rst.resultInfo.seq);
				$('#title').val(rst.resultInfo.title);
				$('#contents').val(rst.resultInfo.content);
			},
			fn_list : function(){
				$('#boardFrm').prop("action", "index");
				$('#boardFrm').submit();
			},
			fn_modify : function(){
				$('#boardFrm').prop("action", "indexwrite");
				$('#boardFrm').submit();
			},
			fn_delete : function(){
				if(confirm("삭제하시겠습니까?"))
					console.log(seq);
					ajaxParamExecute("seq="+seq, "/board/rest/indexDelete", "post", false, false, indexViewObj.fn_deleteReturn);
			},
			fn_deleteReturn : function(rst){
				if(rst){
					alert("삭제되었습니다.");
					indexViewObj.fn_list(1);
				}else{
					alert("삭제에 실패하였습니다. \n관리자에게 문의바랍니다.");
				}
			}
		}
</script>
