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
	                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                                    <colgroup>
											<col width="80px">
											<col width="*">
											<col width="*">
											<col width="*">
											<col width="*">
										</colgroup>
										<thead>
											<tr class="headings">
												<th class="text-center column-title" style="display: table-cell;">
										    		<input type="checkbox" id="check-all">
												</th>
												<th class="text-center column-title" style="display: table-cell;">No</th>
												<th class="text-center column-title" style="display: table-cell;">제목</th>
												<th class="text-center column-title" style="display: table-cell;">내용</th>
												<th class="text-center column-title" style="display: table-cell;">등록자</th>								
												<th class="text-center column-title no-link last" style="display: table-cell;">등록일</th>
												<th class="text-center column-title no-link last" style="display: table-cell;">수정일</th>
											</tr>
										</thead>
										<tbody id="boardList">
		                        		</tbody>
		                        		<tfoot style="display:none;">
		                        			<tr class="pointer" id="boardListCron">
		                        				<td class="text-center">
		                           					<input type="checkbox" name="seqArr" class="seq" value="">
		                            			</td>
			                            		<td class="text-center order"></td>                        	                            	
			                            		<td class="text-center"><a href="#" class="title"> </a></td>
			                            		<td class="text-center contents"></td>
			                            		<td class="text-center regId"></td>	                            		
		                            			<td class="text-center last regDt"></td>
		                            			<td class="text-center last updDt"></td>
		                        			</tr>
		                        		</tfoot>
	                                </table>
	                                <span class="col-xs-12 text-center">
										<nav id="nav">
										</nav>
									</span>
									<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
										<button type="button" class="btn btn-primary" onclick="boardObj.fn_delete()">삭제</button>
										<button type="button" class="btn btn-primary" onclick="boardObj.fn_write()">등록</button>	
									</div>
	                            </div>
	                        </form>    
                        </div>
                    </div>

                </div>
            <!-- End of Main Content -->

           

<script>
	var page;
	if($('#nowPage').val()){
		page=$('#nowPage').val()
	}else{
		page=1;
	}
	$(document).ready(function(){
		boardObj.fn_list(page);
		$("#check-all").click(function(){
	        //클릭되었으면
	        if($("#check-all").prop("checked")){
	            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
	            $("#boardList input[name=seqArr]:checkbox").prop("checked",true);
	            //클릭이 안되있으면
	        }else{
	            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
	            $("#boardList input:checkbox[name=seqArr]:checkbox").prop("checked",false);
	        }
	    })
	})
	
	
	let boardObj = {
			fn_list :  function(page){
				var params = "nowPage="+page;
				ajaxParamExecute(params,"/board/rest/indexList", "post", false, false, boardObj.fn_listReturn);
			},
			fn_listReturn : function(rst){
				$('#nowPage').val(rst.pageMap.nowPage);
				$('#boardList').html('');
				if(rst.resultList.length > 0) {
					for(var i in rst.resultList) {
						var trAddClass = 'even';
						
						if (i%2 == 0) trAddClass = 'even';
						if (i%2 == 1) trAddClass = 'odd';
						
						var list = $('#boardListCron').clone().addClass(trAddClass).removeAttr('id').show();
						list.find('.seq').val(rst.resultList[i].seq);
						list.find('.order').text(rst.pageMap.rowMax - ((rst.pageMap.nowPage-1) * 10) - i );
						list.find('.title').html(rst.resultList[i].title);
						list.find('.title').attr('onclick', 'boardObj.fn_view(\''+ rst.resultList[i].seq + '\')');
						list.find('.contents').html(rst.resultList[i].content);
						list.find('.regId').html(rst.resultList[i].regId);
						list.find('.regDt').text(rst.resultList[i].regDtYmd);
						
						$('#boardList').append(list);
					}					
						
					$("#nav").html('');
					$("#nav").html(rst.pageMap.nav);
				}	else {
					$('#nav').html('');
					$('boardList').html('');
				}
			},
			fn_view : function(seq){
				$('#seq').val(seq);
				$('#boardFrm').prop("action", "indexview");
				$('#boardFrm').submit();				
			},
			fn_delete : function(){
				if($("#boardList input[name=seqArr]:checked").length == 0){
					alert("삭제할 데이터를 선택해 주세요.");
				}else{
					if(confirm("삭제하시겠습니까?")){
						var seqs = [];
						$("#boardList input[name=seqArr]:checked").each(function(){
							seqs[seqs.length] = $(this).val();
						});
						
						var seq = seqs.join(",");
						
						ajaxParamExecute("seq="+seq, "/board/rest/indexDelete", "post", false, false, boardObj.fn_deleteReturn);
					}
				}
			},
			fn_deleteReturn : function(rst){
				if(rst){
					alert("삭제되었습니다.");
					$("#check-all").attr("checked", false);
					boardObj.fn_list(1);
				}else{
					alert("삭제에 실패하였습니다. \n관리자에게 문의바랍니다.");
				}
			},
			fn_write : function(){
				window.location="indexwrite";
			}
			
		}
		
		function goPage(page){
			boardObj.fn_list(page);
		}		
	
</script>

