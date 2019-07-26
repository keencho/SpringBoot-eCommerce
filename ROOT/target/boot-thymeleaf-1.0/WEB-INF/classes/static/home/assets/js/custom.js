	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var addr = ''; // 주소 변수
						var extraAddr = ''; // 참고항목 변수

						//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							addr = data.roadAddress;
						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							addr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
						if (data.userSelectedType === 'R') {
							// 법정동명이 있을 경우 추가한다. (법정리는 제외)
							// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
							if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
								extraAddr += data.bname;
							}
							// 건물명이 있고, 공동주택일 경우 추가한다.
							if (data.buildingName !== '' && data.apartment === 'Y') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
							if (extraAddr !== '') {
								extraAddr = ' (' + extraAddr + ')';
							}
							// 조합된 참고항목을 해당 필드에 넣는다.
							document.getElementById("sample6_extraAddress").value = extraAddr;

						} else {
							document.getElementById("sample6_extraAddress").value = '';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample6_postcode').value = data.zonecode;
						document.getElementById("sample6_address").value = addr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("sample6_detailAddress").focus();
					}
				}).open();
	}

	/* 폰 번호 합치기 */
	function phone_submit() {
		var r = document.register;
		r.phone.value = r.phone1.value + r.phone2.value + r.phone3.value;
	}

	/* 비밀번호 일치 확인 */
	function pwCheck() {
	    if(document.getElementById('password').value!='' && document.getElementById('passwordC').value!='') {
	        if(document.getElementById('password').value==document.getElementById('passwordC').value) {
	            document.getElementById('same').innerHTML='비밀번호가 일치합니다.';
	            document.getElementById('same').style.color='blue';
	            document.getElementById('check_button').disabled=false;
	        }
	        else {
	            document.getElementById('same').innerHTML='비밀번호가 일치하지 않습니다.';
	            document.getElementById('same').style.color='red';
	            document.getElementById('check_button').disabled=true;
	        }
	    }
	}

	/* ajax id중복체크 */
	function idCheck() {
		var id = $("#id").val();
		$.ajax({
			url : "/user/idCheck",
			type : "POST",
			data : {
				"id" : id
			},
			dataType : "html",
			complete : function(xhr, textStatus) {
				var num = xhr.responseText;
				if (num > 0) {
					$('#cktext').text("※중복되는 아이디입니다. 다른 아이디를 입력해주세요");
					$('#cktext').css("color", "#F47520");
					$("#id").css("border-color", "#F47520");
					$(".check_button").prop("disabled", true);
					idck = 0;
				} else {
					$('#cktext').text("");
					$("#id").css("border-color", "");
					$(".check_button").prop("disabled", false);
					idck = 1;
				}
			},

		});
	}
	/* product list 부분의 dropdown 관련 jQuery */
    $(document).ready(function () {
        $('.navbar-default .navbar-nav > li.dropdown').click(function () {
            if ($(this).hasClass('open')) {
            	$('ul.dropdown-menu', this).stop(true, true).slideUp(100);
                $(this).removeClass('open');
            } else {
            	$('ul.dropdown-menu', '.open').stop(true, true).slideUp(100);
            	$('.open').removeClass('open');
                $('ul.dropdown-menu', this).stop(true, true).slideDown('fast');
                $(this).addClass('open');
            }
        });
    });
    
    /* category번호로 접근시 sorting */
    function sorting(cno) {
    	$(document).ready(function(){
    		var sort = $('#orderby').val();
    		var variable = sort.split(',');
    		var url= "/product/list/category/" + cno + "?sort=" + variable[0] + "," + variable[1];
    		location.href=url;
        })
    }