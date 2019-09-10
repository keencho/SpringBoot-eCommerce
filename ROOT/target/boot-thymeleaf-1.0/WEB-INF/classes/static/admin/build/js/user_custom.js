	// 상품 문의 답변 ajax
	function product_question_answer(no) {
		var answer = $('#answer').val();
		answer = answer.replace(/(?:\r\n|\r|\n)/g, '<br>');
		 if(answer == '') {
			 alert("답변을 입력하세요");
		 } else {
			 $.ajax({
					url : "/admin/communication/product/question/update",
					type : "POST",
					data : {
						"no" : no,
						"status" : 1,
						"answer" : answer
					},
					dataType : "html",
					success : function(result) {
						location.href=result;		 
					},
					error : function() {
						alert("실패");
					}
				});
		 }
	}
	
	// 주문 상태 변환 ajax
	function status_ajax(no, status){
	 	$.ajax({
			url : "/admin/order/statusUpdate",
			type : "POST",
			data : {
				"no" : no,
				"status" : status
			},
			dataType : "html",
			success : function(result) {	 
			},
			error : function() {
				alert("실패");
			}
		});
	}
	
	// 주문 상태 변환 ajax(reload)
	function status_ajax_reload(no, status){
	 	$.ajax({
			url : "/admin/order/statusUpdate",
			type : "POST",
			data : {
				"no" : no,
				"status" : status
			},
			dataType : "html",
			success : function(result) {
				location.reload();
			},
			error : function() {
				alert("실패");
			}
		});
	}
	
// datepicker
$(function() {
  $( ".date-input" ).datepicker({
    dateFormat: 'yy-mm-dd',
    prevText: '이전 달',
    nextText: '다음 달',
    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
    dayNames: ['일','월','화','수','목','금','토'],
    dayNamesShort: ['일','월','화','수','목','금','토'],
    dayNamesMin: ['일','월','화','수','목','금','토'],
    showMonthAfterYear: true,
    changeMonth: true,
    changeYear: true,
    yearSuffix: '년'
  });
});

// 날짜 & 상태 검색
function date_status_search(no){
	var date1 = $("#date-fld1").val();
	var date2 = $("#date-fld2").val();
	var status = $("#status_select_ajax option:selected").val();
	var status_int = 0;
	if (status == "전체"){
		status_int = -1;
	} else if (status == "결제완료"){
		status_int = 0;
	} else if (status == "결제대기"){
		status_int = 1;
	} else if (status == "배송준비중"){
		status_int = 2;
	} else if (status == "배송중"){
		status_int = 3;
	} else if (status == "배송완료"){
		status_int = 4;
	} else if (status == "교환요청"){
		status_int = 5;
	} else if (status == "교환완료"){
		status_int = 6;
	} else if (status == "환불요청"){
		status_int = 7;
	} else if (status == "환불완료"){
		status_int = 8;
	} else if (status == "구매확정"){
		status_int = 9;
	}
	
	if(no == 0){
		location.href = "/admin/order";
	} else if (date1 > date2){
		alert("시작일은 종료일보다 클수 없습니다.");
	}  else if(no == 1){
		location.href = "/admin/order/" + date1 + "/" + date2 + "/" + status_int;
	}
}

// 첨부파일 삭제
function attach_delete(){
	var check = confirm("정말 첨부파일을 삭제하시겠습니까?");
	if (check == true){
		$("#attach").hide();
		$("#attach_status").val(1);
	} else {
		return false;
	}
}

// 1:1문의 날짜 & 유형 검색
function consulting_status_search(no){
	var date1 = $("#date-fld1").val();
	var date2 = $("#date-fld2").val();
	var type = $("#type_select_ajax option:selected").attr('id');
	var type_string = null;

	if(no == 0){
		location.href = "/admin/consulting";
	} else if (date1 > date2){
		alert("시작일은 종료일보다 클수 없습니다.");
	}  else if(no == 1){
		location.href = "/admin/consulting?date1=" + date1 + "&date2=" + date2 + "&type=" + type;
	}
	
}

//상품별 첨부파일 삭제
function attach_delete_product(no){
	var check = confirm("정말 첨부파일을 삭제하시겠습니까?");
	if(no == 1){
		alert("첫번째 첨부파일은 삭제하실 수 없습니다.");
	}
	else if (check == true){
		$("#attach"+no).hide();
		$("#attach_status"+no).val(1);
	} else {
		return false;
	}
}