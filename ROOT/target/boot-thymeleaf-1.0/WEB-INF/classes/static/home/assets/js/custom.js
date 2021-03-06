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
	
	function sample4_execDaumPostcode() {
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
							document.getElementById("sample4_extraAddress").value = extraAddr;

						} else {
							document.getElementById("sample4_extraAddress").value = '';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample4_postcode').value = data.zonecode;
						document.getElementById("sample4_address").value = addr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("sample4_detailAddress").focus();
					}
				}).open();
	}
	
	function sample5_execDaumPostcode() {
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
							document.getElementById("sample5_extraAddress").value = extraAddr;

						} else {
							document.getElementById("sample5_extraAddress").value = '';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample5_postcode').value = data.zonecode;
						document.getElementById("sample5_address").value = addr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("sample5_detailAddress").focus();
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
    
    
    /* 사이즈에 active class 추가 / 삭제 */
	$(function(){
		var Btn = $("ul#size_class > li");
			Btn.find("a").click(function(){ 
				if($(this).parent().hasClass("active")){
					$(this).parent().removeClass("active");
				} else {
					$(this).parent().addClass("active");
				}
			});
	});
    
    /* 가격에 active class 추가 / 삭제 */
	$(function(){
		var Btn = $(".price_class");
			Btn.find("a").click(function(){ 
				if($(this).hasClass("active")){
					$(this).removeClass("active");
				} else {
					$(".price_class a").removeClass("active");
					$(this).addClass("active");
				}
			});
	});
	
	 /* 색상에 active class 추가 / 삭제 */
    $(function(){
		var Btn = $("ul.config-swatch-list2 > li");
			Btn.find("a").click(function(){
				if($(this).parent().hasClass("active")){
					$(this).parent().removeClass("active");
				} else {
					$(this).parent().addClass("active");
				}
			});
	});
	
    /* pagination load_hide */
    $(function(){
 	   $(document).on("click","#paginationajax a",function(e) {
 		  	$("#ajax_product_list").hide(); 
 		  	$(".loading-overlay").remove();
			$("#ajax_product_list").load($(this).attr("href") + " div#ajax_product_list", function(){
				reload_main();
			}).show();
			$('html, body').animate({scrollTop : $(".breadcrumb-nav").offset().top}, 500);
			e.preventDefault();
			
 	   });
    });
    
    /* category번호로 접근시 sorting */
    function category_sorting(cno) {
    	$(document).ready(function(){
			var selected = [];
			var selected2 = [];
			if($('.price_class > a.active').attr('value') != null){
    			var selected3 = $('.price_class > a.active').attr('value');
    			var price = selected3.split('~');
    		} else {
    			var selected3;
    			var price;
    		}
			var sizeSelected = [];
    		var colorSelected = [];
    		var sort = $('#orderby').val();
    		var variable = sort.split(',');
    		
    		$('#size_class > li').each(function(i){
    			if($(this).hasClass('active')){
    				selected.push($(this).attr('value'));
    			}
    		});
    		
    		$('#color_class > li').each(function(i){
    			if($(this).hasClass('active')){
    				selected2.push($(this).attr('value'));
    			}
    		});
    		
    		if (selected == ''){
    			$('#size_class > li').each(function(i){
        			sizeSelected.push($(this).attr('value'));
        		});
    		} else{
    			sizeSelected = selected;
    		}
    		
    		if (selected2 == ''){
    			$('#color_class > li').each(function(i){
        			colorSelected.push($(this).attr('value'));
        		});
    		} else{
    			colorSelected = selected2;
    		}
    		if (price == null){
    			var price1 = 0;
    			var price2 = 500000;
    		} else{
    			var price1 = price[0];
    			var price2 = price[1];
    		}
    		
    		var url = "/product/Ajax/list/category/" + cno + "/size/" + sizeSelected + "/color/" + colorSelected + "/price1/" + price1 + "/price2/" + price2 + "?sort=" + variable[0] + "," + variable[1];
    		
    		$("#ajax_product_list").hide();
    		$(".loading-overlay").remove();
    		$("#ajax_product_list").load(url + " div#ajax_product_list", function(){
				reload_main();
			}).show();
    		$('html, body').animate({scrollTop : $(".breadcrumb-nav").offset().top}, 500);

        })
    }
    
    /* division번호로 접근시 sorting */
    function division_sorting(dno) {
    	$(document).ready(function(){
			var selected = [];
			var selected2 = [];
			if($('.price_class > a.active').attr('value') != null){
    			var selected3 = $('.price_class > a.active').attr('value');
    			var price = selected3.split('~');
    		} else {
    			var selected3;
    			var price;
    		}
			var sizeSelected = [];
    		var colorSelected = [];
    		var sort = $('#orderby').val();
    		var variable = sort.split(',');
    		
    		$('#size_class > li').each(function(i){
    			if($(this).hasClass('active')){
    				selected.push($(this).attr('value'));
    			}
    		});
    		
    		$('#color_class > li').each(function(i){
    			if($(this).hasClass('active')){
    				selected2.push($(this).attr('value'));
    			}
    		});
    		
    		if (selected == ''){
    			$('#size_class > li').each(function(i){
        			sizeSelected.push($(this).attr('value'));
        		});
    		} else{
    			sizeSelected = selected;
    		}
    		
    		if (selected2 == ''){
    			$('#color_class > li').each(function(i){
        			colorSelected.push($(this).attr('value'));
        		});
    		} else{
    			colorSelected = selected2;
    		}
    		if (price == null){
    			var price1 = 0;
    			var price2 = 500000;
    		} else{
    			var price1 = price[0];
    			var price2 = price[1];
    		}
    		
    		var url = "/product/Ajax/list/division/" + dno + "/size/" + sizeSelected + "/color/" + colorSelected + "/price1/" + price1 + "/price2/" + price2 + "?sort=" + variable[0] + "," + variable[1];
    		
    		$("#ajax_product_list").hide();
    		$(".loading-overlay").remove();
    		$("#ajax_product_list").load(url + " div#ajax_product_list", function(){
				reload_main();
			}).show();
    		$('html, body').animate({scrollTop : $(".breadcrumb-nav").offset().top}, 500);

        })
    }
    
    /* section번호로 접근시 sorting */
    function section_sorting(sno) {
    	$(document).ready(function(){
			var selected = [];
			var selected2 = [];
			if($('.price_class > a.active').attr('value') != null){
    			var selected3 = $('.price_class > a.active').attr('value');
    			var price = selected3.split('~');
    		} else {
    			var selected3;
    			var price;
    		}
			var sizeSelected = [];
    		var colorSelected = [];
    		var sort = $('#orderby').val();
    		var variable = sort.split(',');
    		
    		$('#size_class > li').each(function(i){
    			if($(this).hasClass('active')){
    				selected.push($(this).attr('value'));
    			}
    		});
    		
    		$('#color_class > li').each(function(i){
    			if($(this).hasClass('active')){
    				selected2.push($(this).attr('value'));
    			}
    		});
    		
    		if (selected == ''){
    			$('#size_class > li').each(function(i){
        			sizeSelected.push($(this).attr('value'));
        		});
    		} else{
    			sizeSelected = selected;
    		}
    		
    		if (selected2 == ''){
    			$('#color_class > li').each(function(i){
        			colorSelected.push($(this).attr('value'));
        		});
    		} else{
    			colorSelected = selected2;
    		}
    		if (price == null){
    			var price1 = 0;
    			var price2 = 500000;
    		} else{
    			var price1 = price[0];
    			var price2 = price[1];
    		}
    		
    		var url = "/product/Ajax/list/section/" + sno + "/size/" + sizeSelected + "/color/" + colorSelected + "/price1/" + price1 + "/price2/" + price2 + "?sort=" + variable[0] + "," + variable[1];
    		
    		$("#ajax_product_list").hide();
    		$(".loading-overlay").remove();
    		$("#ajax_product_list").load(url + " div#ajax_product_list", function(){
				reload_main();
			}).show();
    		$('html, body').animate({scrollTop : $(".breadcrumb-nav").offset().top}, 500);

        })
    }
    
    /* category번호로 접근시 filter Ajax */
	function filter_category_ajax(no) {
    	$(function(){
    		var selected = [];
    		var selected2 = [];
    		if($('.price_class > a.active').attr('value') != null){
    			var selected3 = $('.price_class > a.active').attr('value');
    			var price = selected3.split('~');
    		} else {
    			var selected3;
    			var price;
    		}
    		var sizeSelected = [];
    		var colorSelected = [];
    		var priceSelected = [];
    		var sort = $('#orderby').val();
    		var variable = sort.split(',');
    	
    		$('#size_class > li').each(function(i){
    			if($(this).hasClass('active')){
    				selected.push($(this).attr('value'));
    			}
    		});
    		
    		$('#color_class > li').each(function(i){
    			if($(this).hasClass('active')){
    				selected2.push($(this).attr('value'));
    			}
    		});
    		
    		if (selected == ''){
    			$('#size_class > li').each(function(i){
        			sizeSelected.push($(this).attr('value'));
        		});
    		} else{
    			sizeSelected = selected;
    		}
    		
    		if (selected2 == ''){
    			$('#color_class > li').each(function(i){
        			colorSelected.push($(this).attr('value'));
        		});
    		} else{
    			colorSelected = selected2;
    		}
    		
    		if (price == null){
    			var price1 = 0;
    			var price2 = 500000;
    		} else{
    			var price1 = price[0];
    			var price2 = price[1];
    		}
    		var url = "/product/Ajax/list/category/" + no + "/size/" + sizeSelected + "/color/" + colorSelected + "/price1/" + price1 + "/price2/" + price2 + "?sort=" + variable[0] + "," + variable[1];
    		$.ajax({
    			url : "/product/categoryFilterCheck",
    			type : "POST",
    			traditional : true,
    			data : {
    				"no" : no,
    				"size" : sizeSelected,
    				"color" : colorSelected,
    				"price1" : price1,
    				"price2" : price2
    			},
    			dataType : "html",
    			complete : function(xhr, textStatus) {
    				var num = xhr.responseText;
    				if (num > 0) {
    					$("#ajax_product_list").hide();
    					$(".loading-overlay").remove();
    					$("#ajax_product_list").load(url + " div#ajax_product_list", function(){
    						reload_main();
    					}).show();
    				} else {
    					alert("결과값이 없습니다!");
    				} 				 
				},

    		});
   		});
	}
    
	/* division번호로 접근시 filter Ajax */
	function filter_division_ajax(no) {
    	$(function(){
    		var selected = [];
    		var selected2 = [];
    		if($('.price_class > a.active').attr('value') != null){
    			var selected3 = $('.price_class > a.active').attr('value');
    			var price = selected3.split('~');
    		} else {
    			var selected3;
    			var price;
    		}
    		var sizeSelected = [];
    		var colorSelected = [];
    		var priceSelected = [];
    		var sort = $('#orderby').val();
    		var variable = sort.split(',');
    	
    		$('#size_class > li').each(function(i){
    			if($(this).hasClass('active')){
    				selected.push($(this).attr('value'));
    			}
    		});
    		
    		$('#color_class > li').each(function(i){
    			if($(this).hasClass('active')){
    				selected2.push($(this).attr('value'));
    			}
    		});
    		
    		if (selected == ''){
    			$('#size_class > li').each(function(i){
        			sizeSelected.push($(this).attr('value'));
        		});
    		} else{
    			sizeSelected = selected;
    		}
    		
    		if (selected2 == ''){
    			$('#color_class > li').each(function(i){
        			colorSelected.push($(this).attr('value'));
        		});
    		} else{
    			colorSelected = selected2;
    		}
    		
    		if (price == null){
    			var price1 = 0;
    			var price2 = 500000;
    		} else{
    			var price1 = price[0];
    			var price2 = price[1];
    		}
    		var url = "/product/Ajax/list/division/" + no + "/size/" + sizeSelected + "/color/" + colorSelected + "/price1/" + price1 + "/price2/" + price2 + "?sort=" + variable[0] + "," + variable[1];
    		$.ajax({
    			url : "/product/divisionFilterCheck",
    			type : "POST",
    			traditional : true,
    			data : {
    				"no" : no,
    				"size" : sizeSelected,
    				"color" : colorSelected,
    				"price1" : price1,
    				"price2" : price2
    			},
    			dataType : "html",
    			complete : function(xhr, textStatus) {
    				var num = xhr.responseText;
    				if (num > 0) {
    					$("#ajax_product_list").hide();
    					$(".loading-overlay").remove();
    					$("#ajax_product_list").load(url + " div#ajax_product_list", function(){
    						reload_main();
    					}).show();
    				} else {
    					alert("결과값이 없습니다!");
    				} 				 
				},

    		});
   		});
	}
	
	/* section번호로 접근시 filter Ajax */
	function filter_section_ajax(no) {
    	$(function(){
    		var selected = [];
    		var selected2 = [];
    		if($('.price_class > a.active').attr('value') != null){
    			var selected3 = $('.price_class > a.active').attr('value');
    			var price = selected3.split('~');
    		} else {
    			var selected3;
    			var price;
    		}
    		var sizeSelected = [];
    		var colorSelected = [];
    		var priceSelected = [];
    		var sort = $('#orderby').val();
    		var variable = sort.split(',');
    	
    		$('#size_class > li').each(function(i){
    			if($(this).hasClass('active')){
    				selected.push($(this).attr('value'));
    			}
    		});
    		
    		$('#color_class > li').each(function(i){
    			if($(this).hasClass('active')){
    				selected2.push($(this).attr('value'));
    			}
    		});
    		
    		if (selected == ''){
    			$('#size_class > li').each(function(i){
        			sizeSelected.push($(this).attr('value'));
        		});
    		} else{
    			sizeSelected = selected;
    		}
    		
    		if (selected2 == ''){
    			$('#color_class > li').each(function(i){
        			colorSelected.push($(this).attr('value'));
        		});
    		} else{
    			colorSelected = selected2;
    		}
    		
    		if (price == null){
    			var price1 = 0;
    			var price2 = 500000;
    		} else{
    			var price1 = price[0];
    			var price2 = price[1];
    		}
    		var url = "/product/Ajax/list/section/" + no + "/size/" + sizeSelected + "/color/" + colorSelected + "/price1/" + price1 + "/price2/" + price2 + "?sort=" + variable[0] + "," + variable[1];
    		$.ajax({
    			url : "/product/sectionFilterCheck",
    			type : "POST",
    			traditional : true,
    			data : {
    				"no" : no,
    				"size" : sizeSelected,
    				"color" : colorSelected,
    				"price1" : price1,
    				"price2" : price2
    			},
    			dataType : "html",
    			complete : function(xhr, textStatus) {
    				var num = xhr.responseText;
    				if (num > 0) {
    					$("#ajax_product_list").hide();
    					$(".loading-overlay").remove();
    					$("#ajax_product_list").load(url + " div#ajax_product_list", function(){
    						reload_main();
    					}).show();
    				} else {
    					alert("결과값이 없습니다!");
    				} 				 
				},

    		});
   		});
	}
	
/* 여기서부턴 장바구니, 위시리스트, quickview등 list에서의 유틸관련 소스 */
 	// a href=# 이동 방지
	$(document).on('click', 'a[href="#"]', function(e){
		e.preventDefault();
	});
	
	// pop-up 닫기
	$(document).ready(function(){
	    $.fn.ajaxOptionClose = function() {
	    	$( "#cart_ajax_option" ).css("display", "none");
	    	return this;
	    };
	    
	    $.fn.ajaxQuickOptionClose = function() {
	    	$( "#quick_cart_ajax_option" ).css("display", "none");
	    	return this;
	    };
	    
	    $.fn.popupClose = function() {
			$( "#cart_ajax_complete" ).css("display", "none");
			return this;
		};
		
		$.fn.popupQuickClose = function() {
			$( "#quick_cart_ajax_complete" ).css("display", "none");
			return this;
		};
		    
		$.fn.sizeTableClose = function() {
			$( "#cart_ajax_sizeTable" ).css("display", "none");
			return this;
		};
		
		$.fn.OptionUpdateClose = function() {
			$( "#cart_ajax_updateOption" ).css("display", "none");
			return this;
		};
		
		$.fn.loginPopupClose = function() {
			$( "#login_popup" ).css("display", "none");
			return this;
		};
		
		$.fn.loginPopupQuickClose = function() {
			$( "#quick_login_popup" ).css("display", "none");
			return this;
		};
		
		$.fn.wishlistPopupClose = function() {
			$( "#wishlist_ajax_complete" ).css("display", "none");
			return this;
		};
		
		$.fn.wishlistQuickPopupClose = function() {
			$( "#quick_wishlist_ajax_complete" ).css("display", "none");
			return this;
		};
		
		$.fn.qnaPopupClose = function() {
			$( "#qna_popup" ).css("display", "none");
			return this;
		};
		
		$.fn.ajaxWishlistOptionClose = function() {
			$( "#wishlist_ajax_option" ).css("display", "none");
			return this;
		};
		
	});
	
	//장바구니 ajax 수량체크
	function cartOptionStockCheck(no) {
		var size=Number($("#cart_ajax_size option:selected").val());
		var color=Number($("#cart_ajax_color option:selected").val());
		if(color != 0) {
			$('#cart_option_button').hide();
		}
		if(size != 0){
			$('#cart_ajax_color').attr('disabled', false);
			$('#cart_option_button').hide();
		} else {
			$('#cart_ajax_color').attr('disabled', true);
		}
		if (size != 0 && color != 0){
			$.ajax({
				url : "/cart/option/stockCheck",
				type : "POST",
				data : {
					"no" : no,
					"size" : size,
					"color" : color
				},
				dataType : "html",
				success : function(result) {
					if (result > 0) {
						$('#cart_option_button').show();
					} else {
						alert("수량이 부족합니다. 다른 옵션을 선택해주세요");
						$('#cart_option_button').hide();
					} 				 
				}
			});
		}
	}
	
	 // 장바구니 ajax
    function cartOptionAjax(no) {
		var url = "/cart/option/" + no;
		$.ajax({
			url : "/cart/option/",
			type : "POST",
			data : {
				"no" : no
			},
			dataType : "html",
			complete : function(xhr, textStatus) {
				var num = xhr.responseText;
				if (num > 0) {
					$("#cart_ajax_option").css({
						"top": (($(window).height()-$("#cart_ajax_option").outerHeight())/2+$(window).scrollTop())+"px",
						"left": (($(window).width()-$("#cart_ajax_option").outerWidth())/2+$(window).scrollLeft())+"px"
					 });
					$("#cart_ajax_option").load(url + " .cart_option").fadeIn(300);
				} else {
					alert("결과값이 없습니다!");
				} 				 
			},
		});

    }
	 
    // 장바구니 옵션 수정
    function cartUpdateOptionAjax(no, cartno) {
		var url = "/cart/option/update/" + no;
		$.ajax({
			url : "/cart/option/",
			type : "POST",
			data : {
				"no" : no
			},
			dataType : "html",
			complete : function(xhr, textStatus) {
				var num = xhr.responseText;
				if (num > 0) {
					
					$("#cart_ajax_updateOption").css({
						"top": (($(window).height()-$("#cart_ajax_updateOption").outerHeight())/2+$(window).scrollTop())+"px",
						"left": (($(window).width()-$("#cart_ajax_updateOption").outerWidth())/2+$(window).scrollLeft())+"px"
					 });
					$("#cart_ajax_updateOption").load(url + " .cart_option").fadeIn(300);
					document.getElementById("hidden_cart_no").value = cartno;
				} else {
					alert("결과값이 없습니다!");
				} 				 
			},
		});

    }
	
	// list에서 장바구니 추가 접근
	function cart_add_list(no) {
		var size=$("#cart_ajax_size option:selected").text();
		var color=$("#cart_ajax_color option:selected").text();
		$.ajax({
			url : "/cart/add",
			type : "POST",
			data : {
				"no" : no,
				"size" : size,
				"color" : color,
				"qty" : 1
			},
			dataType : "html",
			success : function() {
				$( "#cart_ajax_option" ).css("display", "none");
				$("#cart_ajax_complete").css({
					"top": (($(window).height()-$("#cart_ajax_complete").outerHeight())/2+$(window).scrollTop())+"px",
					"left": (($(window).width()-$("#cart_ajax_complete").outerWidth())/2+$(window).scrollLeft())+"px"
				 });
				$("#cart_ajax_complete").load("/cart/move/" + " .overlay_popup").fadeIn(300);		 
			}
		});
		
	}
	
	// 장바구니 전체 삭제
	function cart_deleteall() {
		var check = confirm("정말 장바구니를 비우시겠습니까?");
		if(check == true){
		  location.href="/cart/deleteall";
		}
		else if(check == false){
		  return false;
		}
	}
	
	// 장바구니 사이즈표
	function cart_size_table(no) {
		var url = "/cart/sizetable/" + no;
		$("#cart_ajax_sizeTable").css({
			"top": (($(window).height()-$("#cart_ajax_sizeTable").outerHeight())/2+$(window).scrollTop())+"px",
			"left": (($(window).width()-$("#cart_ajax_sizeTable").outerWidth())/2+$(window).scrollLeft())+"px",
		 });
		$("#cart_ajax_sizeTable").load(url + " .size_table").fadeIn(300);
		
	}
	
	// 장바구니 수량 업데이트
	function cart_update_qty(no){
		var qty = $('#cart_qty' + no).val();
		$.ajax({
			url : "/cart/updateqty",
			type : "POST",
			data : {
				"no" : no,
				"qty" : qty
			},
			dataType : "html",
			success : function() {
				location.href='/cart';
			}
		});
		
	}
	
	// 장바구니 번호별 삭제
	function cart_del(no){
		var check = confirm("정말 삭제하시겠습니까?");
		if(check == true){
		  location.href="/cart/del/" + no;
		}
		else if(check == false){
		  return false;
		}
	}
	
	// 장바구니 업데이트
	function cart_update_option(no){
		var size=$("#cart_ajax_size option:selected").text();
		var color=$("#cart_ajax_color option:selected").text();
		var cartno=$("#hidden_cart_no").val();
		$.ajax({
			url : "/cart/updateOption",
			type : "POST",
			data : {
				"no" : cartno,
				"productno" : no,
				"size" : size,
				"color" : color
			},
			dataType : "html",
			success : function(result) {
				if(result == 0)
					location.href="/cart";
				else
					alert("현재 선택된 옵션입니다. 변경할 옵션을 선택해주세요.");
			}
		});
		
	}
	
	// 로그인 팝업 띄우기
	function login_popup(){
		var url="/user/loginPopup";
		$("#login_popup").css({
			"top": (($(window).height()-$("#login_popup").outerHeight())/2+$(window).scrollTop())+"px",
			"left": (($(window).width()-$("#login_popup").outerWidth())/2+$(window).scrollLeft())+"px",
		 });
		$("#login_popup").load(url + " #modal-wrapper").fadeIn(300);
	}
	
	// 로그인 팝업 로그인 처리
	function ajax_popup_login(){
		var id = $("#login-email").val();
		var pw = $("#login-password").val();
		if(id == ''){
			alert("아이디를 입력하세요");
		} else if(pw == '') {
			alert("비밀번호를 입력하세요");
		}
		if (id != '' && pw != ''){
			$.ajax({
				url : "/user/loginPopup",
				type : "POST",
				data : {
					"id" : id,
					"pw" : pw
				},
				dataType : "html",
				success : function(result) {
					if(result == 0){
						window.location.reload();
					} else if(result == 1 || result == 2){
						alert("잘못된 정보를 입력하셨습니다.");
						document.getElementById('login-email').value = '';
						document.getElementById('login-password').value = '';
					}
				}
			});
		}
	}
	
	// 위시리스트 추가
	function wishlist_ajax_add(no, productno){
		var url = "/wishlist/complete";
		var failurl = "/wishlist/already";
		$.ajax({
			url : "/wishlist/add",
			type : "POST",
			data : {
				"userno" : no,
				"productno" : productno
			},
			dataType : "html",
			success : function(result) {
				if (result == 0){
					$("#wishlist_ajax_complete").css({
						"top": (($(window).height()-$("#wishlist_ajax_complete").outerHeight())/2+$(window).scrollTop())+"px",
						"left": (($(window).width()-$("#wishlist_ajax_complete").outerWidth())/2+$(window).scrollLeft())+"px",
					 });
					$("#wishlist_ajax_complete").load(url + " .overlay_popup").fadeIn(300);
				} else{
					$("#wishlist_ajax_complete").css({
						"top": (($(window).height()-$("#wishlist_ajax_complete").outerHeight())/2+$(window).scrollTop())+"px",
						"left": (($(window).width()-$("#wishlist_ajax_complete").outerWidth())/2+$(window).scrollLeft())+"px",
					 });
					$("#wishlist_ajax_complete").load(failurl + " .overlay_popup").fadeIn(300);
				}
				
			}
		});
	}
	
	// 위시리스트 번호별 삭제
	function wishlist_del(no, userno){
		var check = confirm("정말 삭제하시겠습니까?");
		if(check == true){
		  location.href="/wishlist/del/" + userno + "/" + no;
		}
		else if(check == false){
		  return false;
		}
	}
	
	// 위시리스트 전체삭제
	function wishlist_delAll(userno){
		var check = confirm("정말 위시리스트를 비우시겠습니까?");
		if(check == true){
		  location.href="/wishlist/delall/" + userno;
		}
		else if(check == false){
		  return false;
		}
	}
	
	 // 퀵뷰 장바구니 ajax
    function cartQuickOptionAjax(no) {
		var size=$("#quick_cart_ajax_size option:selected").text();
		var color=$('.config-swatch-list > li.active > a').text();
		var sizeno=$("#quick_cart_ajax_size").val();
		var colorno=$('.config-swatch-list > li.active').val();
		var qty=$("#quick-qty").val();
		if(size == "사이즈를 선택하세요"){
			alert("사이즈를 선택하세요");
		} else if(color == ""){
			alert("색상을 선택하세요");
		} else {
			$.ajax({
				url : "/cart/option/stockCheck",
				type : "POST",
				data : {
					"no" : no,
					"size" : sizeno,
					"color" : colorno
				},
				dataType : "html",
				success : function(result) {
					if (result > 0) {
						$.ajax({
							url : "/cart/add",
							type : "POST",
							data : {
								"no" : no,
								"size" : size,
								"color" : color,
								"qty" : qty
							},
							dataType : "html",
							success : function() {
								$( "#quick_cart_ajax_option" ).css("display", "none");
								$("#quick_cart_ajax_complete").css({
									"top": ($("#quick_cart_ajax_complete").outerHeight())/2+"px",
									"left": ($("#quick_cart_ajax_complete").outerWidth())/2+"px"
								 });
								$("#quick_cart_ajax_complete").load("/cart/move/" + " .quick_overlay_popup").fadeIn(300);		 
							}
						});
					} else {
						alert("수량이 부족합니다. 다른 옵션을 선택해주세요");
						$('#cart_option_button').hide();
					} 				 
				}
			});
		}
		
		

    }
	
	// 퀵뷰에서 로그인 팝업 띄우기
	function quick_login_popup(){
		var url="/user/loginPopup";
		$("#quick_login_popup").css({
			"top": ($("#quick_login_popup").outerHeight())/2+"px",
			"left": ($("#quick_login_popup").outerWidth())/2+"px"
		 });
		$("#quick_login_popup").load(url + " #quick_modal-wrapper").fadeIn(300);
	}
	
	// 퀵뷰에서 위시리스트 추가
	function wishlist_quick_ajax_add(no, productno){
		var url = "/wishlist/complete";
		var failurl = "/wishlist/already";
		$.ajax({
			url : "/wishlist/add",
			type : "POST",
			data : {
				"userno" : no,
				"productno" : productno
			},
			dataType : "html",
			success : function(result) {
				if (result == 0){
					$("#quick_wishlist_ajax_complete").css({
						"top": ($("#quick_wishlist_ajax_complete").outerHeight())/2+"px",
						"left": ($("#quick_wishlist_ajax_complete").outerWidth())/2+"px"
					 });
					$("#quick_wishlist_ajax_complete").load(url + " .quick_overlay_popup").fadeIn(300);
				} else{
					$("#quick_wishlist_ajax_complete").css({
						"top": ($("#quick_wishlist_ajax_complete").outerHeight())/2+"px",
						"left": ($("#quick_wishlist_ajax_complete").outerWidth())/2+"px"
					 });
					$("#quick_wishlist_ajax_complete").load(failurl + " .quick_overlay_popup").fadeIn(300);
				}
				
			}
		});
	}
	
	// 상품설명 전체보기, 닫기
    $(function(){
    	 var offset = $( '.expic' ).offset();
    	  $("#expic_button > button").click ( function(){
    		  if($(this).hasClass("checkcrop")){
    			  $(this).removeClass("checkcrop");﻿
    			  $("#expic").removeClass('expic');
    			  $("#expic").addClass('expic_all');
    			  $('#spancrop span').html('상품설명 닫기');
    		  }
    		  else{
    			  $(this).addClass("checkcrop");
    			  $("#expic").removeClass('expic_all');
    			  $("#expic").addClass('expic');
    			  $('#spancrop span').html('상품설명 전체보기');
    			  $('html, body').animate({scrollTop : (offset.top)}, 100);
    		  }
    	 });
    });
	
    // Q&A 팝업 레이어
    $(document).ready(function () {
    	$('#qnasubmit').click(function () {
    		$("#qna_popup").css({
    			"top": (($(window).height()-$("#qna_popup").outerHeight())/2+$(window).scrollTop())+"px",
    			"left": (($(window).width()-$("#qna_popup").outerWidth())/2+$(window).scrollLeft())+"px"
    			 });
    		$( "#qna_popup" ).load( ".qna_popup" ).fadeIn(100);
    	});
    });
    // 상품문의 등록
    function product_question_add(){
   	 var no = $('#product_no').val();
   	 var type = $('input[name="question_type"]:checked').val();
   	 var name = $('#question_name').val();
   	 var contents = $('#question_contents').val();
   	 contents = contents.replace(/(?:\r\n|\r|\n)/g, '<br>');
   	 if (name == ''){
   		 alert("제목을 입력해주세요!");
   	 } else if (name.length > 30){
   		 alert("제목은 영문자 포함 30자 이내로만 등록 가능합니다.");
   	 } else if (contents == ''){
   		 alert("문의 내용을 입력해주세요!");
   	 } else {
   		 $.ajax({
   				url : "/product/communication/question/add",
   				type : "POST",
   				data : {
   					"product_no" : no,
   					"type" : type,
   					"name" : name,
   					"contents" : contents
   				},
   				dataType : "html",
   				success : function() {
   					alert("상품 문의가 등록되었습니다!");
   					window.location.reload(); 				 
   				},
   				error : function() {
   					alert("상품 문의 등록이 실패하였습니다.");
   				}
   			});
   	 } 
   }
    
   // 상품별 문의
    $(document).ready(function () {
    	$(document).on('click', '.question_name', function() {
    		if($(this).parents('tr').next('tr').is(":visible")){
    			$(this).parents('tr').next('tr').slideUp(1);
    		} else {
    			$(".question_name").parents('tr').next('tr').slideUp(1);
    			$(this).parents('tr').next('tr').slideDown(1);
    		}
    	})
    })
    
   // 상품별 문의 pagination load() 처리
   $(function(){
   	$(document).on("click","#paginationajax_question a",function(e) {
   		$("#qna-pagination-load").hide(); 
   		$("#qna-pagination-load").load($(this).attr("href") + " div#qna-pagination-load").show();
   		e.preventDefault();
   	});
   });

   // 상품별 문의 sorting ajax 처리
   function product_question_sort(productno, sortno) {
   	$("#qna-pagination-load").hide();
   	$("#qna-pagination-load").load("/product/communication/question/list/" + productno + "/" + sortno + " div#qna-pagination-load").show();
   	e.preventDefault();
   }

   /* 퀵뷰(색상) active class 추가, 삭제 */
   $(function(){
   	var Btn = $(".config-swatch-list > li");
   		Btn.click(function(){ 
   			if($(this).hasClass("active")){
   				$(this).removeClass("active");
   			} else {
   				Btn.removeClass("active");
   				$(this).addClass("active");
   			}
   		});
   });

   // 재고확인후 상품 selector에 추가
   function product_view_stock(no, color, colorNo) {
   	var size=$("#view_ajax_size option:selected").text();
   	var sizeNo=Number($("#view_ajax_size option:selected").val());
   	var price=$("#product_price").text();

   	if (size != "사이즈를 선택하세요"){
   		$(".choose_first").hide();
   		$(".choose_color").show();
   	} else {
   		$(".choose_color").hide();
   		$(".choose_first").show();
   	}
   	
   	if (color == null) {
   		color = $(".view_ajax_color > li.active > a").text();
   		var colorNo = Number($(".view_ajax_color > li.active").val());
   	}
   	
   	for(var i=0; i<$("input[name='hidden_option']").length; i++){
   		if($("input[name='hidden_option']").eq(i).val() == sizeNo + "/" + colorNo){
   			$('#view_ajax_size').prop('selectedIndex',0);
   			$(".choose_color").hide();
   			$(".choose_first").show();
   			alert("이미 선택된 옵션입니다. 다른 옵션을 선택해주세요.");
   			return false;
   		}
   	}
   	
   	if (color != '' && size != "사이즈를 선택하세요"){
   		$.ajax({
   			url : "/cart/option/stockCheck",
   			type : "POST",
   			data : {
   				"no" : no,
   				"size" : sizeNo,
   				"color" : colorNo
   			},
   			dataType : "html",
   			success : function(result) {	
   				if (result > 0) {
   					if( $('input[name=hidden_option]').val() == colorNo + "/" + sizeNo){

   					} else {
   						$('#view_ajax_size').prop('selectedIndex',0);
   						$(".config-swatch-list > li").removeClass("active");
   						$("#goods_selected:last").append("<div class='product-single-filter goods'>"
   														+ "<div class='container'>"
   														+ "<div class='row'>"
   														+ "<div class='col'>"
   														+ "<input type='hidden' name='hidden_cart' value='" + size + "/" + color + "'>"
   														+ "<input type='hidden' name='hidden_option' value='" + sizeNo + "/" + colorNo + "'>"
   														+ "<span class='goods_option'>" + color + "</span>"
   														+ "<span>,&nbsp</span>"
   														+ "<span class='goods_option'>" + size + "</span>"
   														+ "</div>"
   														+ "<div class='col-5'>"
   														+ "<div class='goods_qty'>"
   														+ "<div class='product-single-qty'>"
   														+ "<input name='product_qty' class='horizontal-quantity form-control' type='text'>"
   														+ "</div>" + "</div>" + "</div>"
   														+ "<div class='col' style='white-space: pre;'>"
   														+ "<strong class='goods_price' >" + price + "</strong>"
   														+ "<a style='cursor: pointer;' class='icon-cancel goods_option goods_cancel'></a>"
   														+ "</div>"
   														+ "</div>");
   						reload_main();
   						$(".choose_color").hide();
   						$(".choose_first").show();
   					}
   				} else {
   					alert("수량이 부족합니다. 다른 옵션을 선택해주세요");
   				} 				 
   			}
   		});
   	}
   		
   }

   // 상품 셀렉터에서 x 클릭시 index를 찾아 삭제
   $(document).on('click', '.goods_cancel', function() {
   	var idx = $(this).index('.goods_cancel');
   	$('.goods').eq(idx).remove();
   });


   // view에서 장바구니 추가 접근
   function cart_add_view(no) {
   	if($("input[name='hidden_cart']").val() == null){
   		alert("최소 한가지의 옵션 세트를 선택해주세요.");
   	} else {
   		$("input[name='hidden_cart']").each(function (i) {
   			var hidden_cart = $(this).val();
   			var str = hidden_cart.split('/');
   			var size = str[0];
   			var color = str[1];
   			var qty = $('input[name=product_qty]').eq(i).val();
   			$.ajax({
   				url : "/cart/add",
   				type : "POST",
   				data : {
   					"no" : no,
   					"size" : size,
   					"color" : color,
   					"qty" : qty
   				},
   				dataType : "html",
   				success : function() {
   				}
   			});
   		});
   		$( "#cart_ajax_option" ).css("display", "none");
   		$("#cart_ajax_complete").css({
   			"top": (($(window).height()-$("#cart_ajax_complete").outerHeight())/2+$(window).scrollTop())+"px",
   			"left": (($(window).width()-$("#cart_ajax_complete").outerWidth())/2+$(window).scrollLeft())+"px"
   		 });
   		$("#cart_ajax_complete").load("/cart/move/" + " .overlay_popup").fadeIn(300);		 
   	}
   	
   }
   	
   // 총합 계산
   $( "#goods_selected" ).change(function() {
   	var original_price = $('#original_price').val();
   	$("input[name='product_qty']").each(function (i) {
   		var qty = $('input[name=product_qty]').eq(i).val();

   	  	$(".goods_price").eq(i).remove();
   	  	$(".goods_cancel").eq(i).before("<strong class='goods_price'>" + (original_price * qty).toLocaleString('en') + "원</strong>");
   	})
   });

   // 장바구니 총합 계산
$(document).ready(function() {
	var total = 0;
	$("input[name='each_price']").each(function() {
		total += parseInt($(this).val());
	});

	$(".cart_total").text(total.toLocaleString('en') + "원");
});


   
   // 상품에서 직접 구매 접근
   function product_order_page(userno, no){
		if($("input[name='hidden_cart']").val() == null){
		   	alert("최소 한가지의 옵션 세트를 선택해주세요.");
		} else {
			 if (userno == 0) {
				 var check = confirm("비회원으로 구매를 진행하시면 추후 교환/환불 등에 어려움이 있을 수 있고, 포인트를 적립하실 수 없습니다.\n그래도 비회원으로 구매하시겠습니까?");
				 var url="/user/loginPopup";
				 if(check == true){
					 var productList = new Array();
					 var sizeNo=Number($("#view_ajax_size option:selected").val());
					 for(var i=0; i<$("input[name='product_qty']").length; i++){
						 var productData = new Object();
						 var option = $("input[name='hidden_option']").eq(i).val();
						 var str = option.split('/');
						 var size = str[0];
						 var color = str[1];
						 var qty = $('input[name=product_qty]').eq(i).val();
						 
						 productData.no = no;
						 productData.size = size;
						 productData.color = color;
						 productData.qty = qty;
						 
						 productList.push(productData);
						 
					 }
					 var jsonProductData = JSON.stringify(productList);
						 $.ajax({
			                 type : "POST",
			                 contentType: 'application/json',
			                 dataType : "json",
			                 url : "/checkout/jsonData",
			                 data : jsonProductData,
			                 success : function(data) {
			                       location.href="/checkout/getForm";
			                 },
			                 error : function(e) {
			                        alert('서버 연결 도중 에러가 났습니다. 다시 시도해 주십시오.');
			                 }
			         });
				 } else {
					 $("#login_popup").css({
							"top": (($(window).height()-$("#login_popup").outerHeight())/2+$(window).scrollTop())+"px",
							"left": (($(window).width()-$("#login_popup").outerWidth())/2+$(window).scrollLeft())+"px",
						 });
						$("#login_popup").load(url + " #modal-wrapper").fadeIn(300);
				 }
			 } else {
				 var productList = new Array();
				 var sizeNo=Number($("#view_ajax_size option:selected").val());
				 for(var i=0; i<$("input[name='product_qty']").length; i++){
					 var productData = new Object();
					 var option = $("input[name='hidden_option']").eq(i).val();
					 var str = option.split('/');
					 var size = str[0];
					 var color = str[1];
					 var qty = $('input[name=product_qty]').eq(i).val();
					 
					 productData.no = no;
					 productData.size = size;
					 productData.color = color;
					 productData.qty = qty;
					 
					 productList.push(productData);
					 
				 }
				 var jsonProductData = JSON.stringify(productList);
					 $.ajax({
		                 type : "POST",
		                 contentType: 'application/json',
		                 dataType : "json",
		                 url : "/checkout/jsonData",
		                 data : jsonProductData,
		                 success : function(data) {
		                       location.href="/checkout/getForm";
		                 },
		                 error : function(e) {
		                        alert('서버 연결 도중 에러가 났습니다. 다시 시도해 주십시오.');
		                 }
		         });		 
			 }
		}
	 }
   
   function cart_order_page(no){
		if(no == -1) {
			alert("장바구니에 상품이 없습니다.");
		} else if(no == 0) {
			var productList = new Array();
			for(var i = 0; i < $(".cart_count").length; i ++){
				 var productData = new Object();
				 var productno = $("input[name='hidden_cart_no']").eq(i).val();
				 var option = $("input[name='hidden_option']").eq(i).val();
				 var str = option.split('/');
				 var size = str[0];
				 var color = str[1];
				 var qty = $('input[name=product_qty]').eq(i).val();
				 
				 productData.no = productno;
				 productData.size = size;
				 productData.color = color;
				 productData.qty = qty;
				 
				 productList.push(productData);
			}
			var jsonProductData = JSON.stringify(productList);
				 $.ajax({
			        type : "POST",
			        contentType: 'application/json',
			        dataType : "json",
			        url : "/checkout/jsonData",
			        data : jsonProductData,
			        success : function(data) {
			              location.href="/checkout/getForm";
			        },
			        error : function(e) {
			               alert('서버 연결 도중 에러가 났습니다. 다시 시도해 주십시오.');
			        }
			});
		} else if (no == 1){
			 var check = confirm("비회원으로 구매를 진행하시면 추후 교환/환불 등에 어려움이 있을 수 있고, 포인트를 적립하실 수 없습니다.\n그래도 비회원으로 구매하시겠습니까?");
			 var url="/user/loginPopup";
			 if (check == true){
				 var productList = new Array();
					for(var i = 0; i < $(".cart_count").length; i ++){
						 var productData = new Object();
						 var productno = $("input[name='hidden_cart_no']").eq(i).val();
						 var option = $("input[name='hidden_option']").eq(i).val();
						 var str = option.split('/');
						 var size = str[0];
						 var color = str[1];
						 var qty = $('input[name=product_qty]').eq(i).val();
						 
						 productData.no = productno;
						 productData.size = size;
						 productData.color = color;
						 productData.qty = qty;
						 
						 productList.push(productData);
					}
					var jsonProductData = JSON.stringify(productList);
						 $.ajax({
					        type : "POST",
					        contentType: 'application/json',
					        dataType : "json",
					        url : "/checkout/jsonData",
					        data : jsonProductData,
					        success : function(data) {
					              location.href="/checkout/getForm";
					        },
					        error : function(e) {
					               alert('서버 연결 도중 에러가 났습니다. 다시 시도해 주십시오.');
					        }
					});
			 } else {
				 $("#login_popup").css({
						"top": (($(window).height()-$("#login_popup").outerHeight())/2+$(window).scrollTop())+"px",
						"left": (($(window).width()-$("#login_popup").outerWidth())/2+$(window).scrollLeft())+"px",
					 });
					$("#login_popup").load(url + " #modal-wrapper").fadeIn(300);
			 }
		}
	}

	// 주소지 선택시 active 추가 / 삭제
	$(document).on("click", ".address_box_change", function() {
		if ($(this).parent().parent().hasClass('active') == false) {
			$('.shipping-address-box').removeClass('active');
			$(this).parent().parent().addClass('active');
			$(".active-data").removeClass('active_jquery');
			$(this).parent().children(".active-data").addClass('active_jquery');
		}
	});

	// 주소지 수정시 modal에 데이터 전송
	$(document).on("click", ".modaldata", function() {
		$('.updateModal_name').val($(this).data('name'));
		$('.updateModal_phone').val($(this).data('phone'));
		$('.updateModal_zipcode').val($(this).data('zipcode'));
		$('.updateModal_address').val($(this).data('address'));
		$('.updateModal_detailaddress').val($(this).data('detailaddress'));
		$('.updateModal_reference').val($(this).data('reference'));
		$('.updateModal_no').val($(this).data('addressno'));
		$('.updateModal_basic').val($(this).data('basic'));
	});

	// 주문 페이지 주소지 선택 -> 주소지 수정
	function modal_address_update_ajax() {
		var no = $("input[name=modal_no]").val();
		var name = $("input[name=modal_name]").val();
		var phone = $("input[name=modal_phone]").val();
		var zipcode = $("input[name=modal_zipcode]").val();
		var address = $("input[name=modal_address]").val();
		var detailaddress = $("input[name=modal_detailaddress]").val();
		var reference = $("input[name=modal_reference]").val();
		var basic = $("input[name=modal_basic]").val();
		if(name == ''){
			alert("이름을 입력하세요");
		} else if(phone == ''){
			alert("휴대폰 번호를 입력하세요");
		} else if(zipcode == ''){
			alert("우편번호를 입력하세요");
		} else if(address == ''){
			alert("주소를 입력하세요");
		} else if(detailaddress == ''){
			alert("상세주소를 입력하세요");
		} else if(reference == ''){
			alert("참고항목을 입력하세요");
		} else {
			$.ajax({
					url : "/user/updateAddress",
					type : "POST",
					data : {
						"no" : no,
						"name" : name,
						"phone" : phone,
						"zipcode" : zipcode,
						"address" : address,
						"detailaddress" : detailaddress,
						"reference" : reference
					},
					dataType : "html",
					success : function() {
						$('#updateAddressModal').modal("hide");
						$('#address_id' + no).remove();
						$("#address_parent_id" + no).append(
							"<div id='address_id" + no + "'>"
						  + "<div class='address_box_change'>"
						  + "<address>"
						  + "(<span>" + zipcode + "</span>)<br>"
						  + "<span>" + address + "</span><br>"
						  + "<span>" + detailaddress + "</span><br>"
						  + "<span>" + reference + "</span><br>"
						  + "<span>" + name + "</span><br>"
						  + "<span>" + phone + "</span><br>"
						  + "</address></div>"
						  + "<a href='#' data-toggle='modal' data-target='#updateAddressModal' data-name='" + name + "' data-phone='" + phone + "' data-zipcode='" + zipcode + "' "
						  + "data-address='" + address +"' data-detailaddress='" + detailaddress + "' data-reference='" + reference + "' data-addressno='" + no + "' class='btn btn-sm btn-outline-secondary modaldata"
						  + (basic == 0 ? ' active-data active_jquery' : ' active-data')
						  + "'>"
						  + "수정</a>"
						  + (basic == 0 ? "<span class='float-right'><strong>기본 배송지</strong></span>" : '')
						  + "</div>"
						  + "</div>");			 
					},
					error : function() {
						alert("수정에 실패하였습니다. 다시 시도해주세요");
					}
				});
		}
	}


	// 배송지 추가전 값 검증
	function modalAddAddress(no) {
		$.ajax({
			url : "/user/checkAddress",
			type : "POST",
			data : {
				"user_no" : no
			},
			dataType : "html",
			success : function(result) {
				if (result >= 5){
					alert("최대로 저장할 수 있는 주소록의 갯수는 5개입니다.");
				} else {
					$('#addressModal').modal("show");
				}
			},
			error : function() {
				alert("다시 시도해주세요");
			}
		});
		
	}

	//주문 페이지 주소지 선택 -> 주소지 추가
	function modal_address_new_ajax(no) {
		var name = $("input[name=modal_new_name]").val();
		var phone = $("input[name=modal_new_phone]").val();
		var zipcode = $("input[name=modal_new_zipcode]").val();
		var address = $("input[name=modal_new_address]").val();
		var detailaddress = $("input[name=modal_new_detailaddress]").val();
		var reference = $("input[name=modal_new_reference]").val();
		if(name == ''){
			alert("이름을 입력하세요");
		} else if(phone == ''){
			alert("휴대폰 번호를 입력하세요");
		} else if(zipcode == ''){
			alert("우편번호를 입력하세요");
		} else if(address == ''){
			alert("주소를 입력하세요");
		} else if(detailaddress == ''){
			alert("상세주소를 입력하세요");
		} else if(reference == ''){
			alert("참고항목을 입력하세요");
		} else {
			$.ajax({
				url : "/user/addAddress",
				type : "POST",
				data : {
					"user_no" : no,
					"name" : name,
					"phone" : phone,
					"zipcode" : zipcode,
					"address" : address,
					"detailaddress" : detailaddress,
					"reference" : reference
				},
				dataType : "html",
				success : function(result) {
					$('#addressModal').modal("hide");
					$('.shipping-address-box').removeClass('active');
					$(".active-data").removeClass('active_jquery');
					$(".add-address-apppend").append(
						"<div id='address_parent_id" + result + "' class='shipping-address-box active'>"
					  + "<div id='address_id" + result + "'>"
					  + "<div class='address_box_change'>"
					  + "<address>"
					  + "(<span>" + zipcode + "</span>)<br>"
					  + "<span>" + address + "</span><br>"
					  + "<span>" + detailaddress + "</span><br>"
					  + "<span>" + reference + "</span><br>"
					  + "<span>" + name + "</span><br>"
					  + "<span>" + phone + "</span><br>"
					  + "</address></div>"
					  + "<a href='#' data-toggle='modal' data-target='#updateAddressModal' data-name='" + name + "' data-phone='" + phone + "' data-zipcode='" + zipcode + "' "
					  + "data-address='" + address +"' data-detailaddress='" + detailaddress + "' data-reference='" + reference + "' data-addressno='" + result + "'data-basic='1' class='btn btn-sm btn-outline-secondary modaldata active-data active_jquery'>"
					  + "수정</a>"
					  + "</div>"
					  + "</div>");
				},
				error : function() {
					alert("저장에 실패하였습니다. 다시 시도해주세요.");
				}
			});
		}
	}

	//주소지 방식 변경
	$(document).on("change", "input[name='addressType']", function() {
		if($(this).val() == "exist"){
			$('.new-address-append').hide();
			$('.add-address-apppend').show();
			$('.modalAddAddress').show();
		} else if ($(this).val() == "new"){
			$('.add-address-apppend').hide();
			$('.modalAddAddress').hide();
			$('.new-address-append').show();
		}
	});

	// 주소지 방식 기본(비회원)
	$( document ).ready(function() {
		if ($("input[name='addressType']").val() == "new"){
			$('.new-address-append').show();
		}
	});

	// 장바구니 총합 계산
	$(document).ready(function () {
		var total = 0;
		 $("input[name='checkout_each_price']").each(function() {
			total += parseInt($(this).val());
		});
		 $(".checkout_user_point").text(Math.round(total/1000));
		 $(".checkout_total_price").text(total.toLocaleString('en')+"원");
		 $("input[name='checkout_hidden_point']").val(Math.round(total/1000));
		 $("input[name='checkout_hidden_total']").val(total);
	});

	// 포인트 체크박스 감지하여 disabled 속성 추가 / 해제
	$(document).on("change", "#address-save" ,function(){
		if($(this).is(":checked")){
			$("input[name='user_point']").attr("disabled", false);
			$("#point_access").show();
		} else {
			$("input[name='user_point']").attr("disabled", true);
			$("#point_access").hide();
		}
		
	});

	// 포인트 검증후 결제 금액에 반영
	function point_verify(no){
		var total = $("input[name='checkout_hidden_total']").val();
		var point = $("input[name='user_point']").val();
		var current_point = $("#user_current_point").val();
		var use_point = $("#user_point").val();
		if(current_point - point < 0) {
			alert("입력하신 포인트가 적용 가능한 남은 포인트보다 많습니다.");
		}
		else if (isNaN(point) == true || point == '' || point == 0 || point < 0){
			alert("올바른 값을 입력해주세요");
		} else {
			$.ajax({
				url : "/user/checkPoint",
				type : "POST",
				data : {
					"no" : no,
					"point" : point
				},
				dataType : "html",
				success : function(result) {
					if(result == 0){
						$("#point_success").hide();
						$("#point_success").show();
						$("input[name='checkout_hidden_total']").val(total-point);			
						$(".checkout_total_price").text((total-point).toLocaleString('en')+"원");
						$("#user_current_point").val(current_point-point);
						$("#span_current_point").text((current_point-point));
						$("#user_point").val(Number(use_point) + Number(point));
					} else if(result == 1){
						alert("사용하실 수 없는 포인트입니다. 확인후 다시 시도해주세요.");
					}
				}
			});
		}
	}

	// 상품 구매
	function payment(no) {
		var addressType = $('input[name=addressType]:checked').val();
		var paytype = $('input[name=shipping-method]:checked').val();

		if(paytype == 'deposit'){	// 계좌이체의 경우
			payment_deposit(no, addressType);
		} else if(paytype == 'card'){	// 카드 결제의 경우
			payment_card(no, addressType);
		}
	}

	// 상품 구매 - 카드
	function payment_card(no, addressType){
		var point = 0;
		var message = '';
		var account_bank = $("select[name=account]").val();
		var account_name = $("input[name=account_name]").val();
		var total_price = $("input[name=checkout_hidden_total]").val();
		var order_password = '';
		var product_name = '';
		for(var i=0; i<$(".product_name_ajax").length; i++){
			product_name += $(".product_name_ajax").eq(i).text() + "/"; 
		}
		product_name = product_name.substr(0, product_name.length -1);
		if (addressType == 'exist'){	// 기존의 배송지 선택
			var name = $('.active_jquery').data('name');
			var phone = $('.active_jquery').data('phone');
			var address1 = $('.active_jquery').data('zipcode');
			var address2 = $('.active_jquery').data('address');
			var address3 = $('.active_jquery').data('detailaddress');
			var address4 = $('.active_jquery').data('reference');
			var address = "(" + $('.active_jquery').data('zipcode') + ")" + $('.active_jquery').data('address') + " " + $('.active_jquery').data('detailaddress') + " " + $('.active_jquery').data('reference');
		} else if (addressType == 'new'){	// 새로운 배송지 선택
			var name = $("input[name='new_name']").val();
			var phone = $("input[name='new_phone']").val();
			var address1 = $("input[name='new_zipcode']").val();
			var address2 = $("input[name='new_address']").val();
			var address3 = $("input[name='new_detailaddress']").val();
			var address4 = $("input[name='new_reference']").val();
			var address = "(" + $("input[name='new_zipcode']").val() + ")" + $("input[name='new_address']").val() + " " + $("input[name='new_detailaddress']").val() + " " + $("input[name='new_reference']").val();
			var order_password = $("input[name='new_password']").val();
			if(order_password == null){
				order_password = '';
			}		
		}
		if($("input[name='point_check']").is(":checked") == true){
			point = $("#user_point").val();
			if (point == ''){
				point = 0;
			}
		} 
		if($("input[name='message']").is(":checked") == true){
			message = $("input[name='orderer_message']").val();
		}
		if (name == ''){
			alert("이름을 입력하세요");
		} else if(no == 0 && order_password == ''){
			alert("비회원 비밀번호를 입력해주세요.");
		}else if(phone == ''){
			alert("휴대폰 번호를 입력하세요");
		} else if(address1 =='' || address2 =='' || address3 =='' || address4 ==''){
			alert("모든 주소를 입력하세요");
		} else {
			var IMP = window.IMP;
			IMP.init("imp93876938");
			
			IMP.request_pay({
				pg : 'html5_inicis',
				pay_method : 'card',
				merchant_uid : 'merchant_' + new Date().getTime(),
				name : product_name,
				amount : 100, // 가상결제 이므로 카드결제 시스템에서의 상품 가격은 100원으로 통일
				buyer_name : name,
				buyer_tel : phone,
				buyer_addr : address
			}, function(rsp) {
				if ( rsp.success ) {
					var msg = '결제가 완료되었습니다.';
					msg += '고유ID : ' + rsp.imp_uid;				// 고유 거래 번호
					msg += '상점 거래ID : ' + rsp.merchant_uid;	// 가맹점에서 생성하는 고유 주문번호
					msg += '결제 금액 : ' + rsp.paid_amount;		// 결제 금액 (1000원)
					msg += '카드 승인번호 : ' + rsp.apply_num;		// 승인 번호
					$.ajax({
						url : "/order/card",
						type : "POST",
						data : {
							"order_name" : name,
							"order_phone" : phone,
							"order_address" : address,
							"user_no" : no,
							"order_message" : message,
							"card_id" : rsp.imp_uid,
							"card_shopid" : rsp.merchant_uid,
							"card_applyno" : rsp.apply_num,
							"point" : point,
							"total_price" : total_price,
							"order_password" : order_password
						},
						dataType : "html",
						success : function() {
							$.ajax({
								url : "/order/get/lastOrder",
								type : "POST",
								dataType : "html",
								success : function(order_no){
									for(var i=0; i<$(".product_info").length; i++){
										var product_no = $("input[name='checkout_product_no']").eq(i).val();
										var product_size = $("input[name='checkout_product_size']").eq(i).val();
										var product_color = $("input[name='checkout_product_color']").eq(i).val();
										var product_qty = $("input[name='checkout_product_qty']").eq(i).val();
										var product_price = $("input[name='checkout_product_price']").eq(i).val();
										$.ajax({
											url : "/order/info/add",
											type : "POST",
											dataType : "html",
											data : {
												"order_no" : order_no,
												"product_no" : product_no,
												"size_no" : product_size,
												"color_no" : product_color,
												"qty" : product_qty,
												"price" : product_price,
											},
											success : function(){
											},
											error : function() {
												alert('결제 실패');
											}
										});
									}
									location.replace("/order/complete/" + order_no);
								}
							});
						}
					});
				} else {
					var msg = '결제에 실패하였습니다.';
					msg += '에러내용 : ' + rsp.error_msg;
				}
				alert(msg);
			});
		}
	}

	// 상품 구매 - 계좌이체
	function payment_deposit(no, addressType){
		var point = 0;
		var message = '';
		var account_bank = $("select[name=account]").val();
		var account_name = $("input[name=account_name]").val();
		var total_price = $("input[name=checkout_hidden_total]").val();
		var order_password = '';
		if (addressType == 'exist'){	// 기존의 배송지 선택
			var name = $('.active_jquery').data('name');
			var phone = $('.active_jquery').data('phone');
			var address1 = $('.active_jquery').data('zipcode');
			var address2 = $('.active_jquery').data('address');
			var address3 = $('.active_jquery').data('detailaddress');
			var address4 = $('.active_jquery').data('reference');
			var address = "(" + $('.active_jquery').data('zipcode') + ")" + $('.active_jquery').data('address') + " " + $('.active_jquery').data('detailaddress') + " " + $('.active_jquery').data('reference');
		} else if (addressType == 'new'){	// 새로운 배송지 선택
			var name = $("input[name='new_name']").val();
			var phone = $("input[name='new_phone']").val();
			var address1 = $("input[name='new_zipcode']").val();
			var address2 = $("input[name='new_address']").val();
			var address3 = $("input[name='new_detailaddress']").val();
			var address4 = $("input[name='new_reference']").val();
			var address = "(" + $("input[name='new_zipcode']").val() + ")" + $("input[name='new_address']").val() + " " + $("input[name='new_detailaddress']").val() + " " + $("input[name='new_reference']").val();
			var order_password = $("input[name='new_password']").val();
			if(order_password == null){
				order_password = '';
			}		
		}
		if($("input[name='point_check']").is(":checked") == true){
			point = $("#user_point").val();
			if (point == ''){
				point = 0;
			}
		} 
		if($("input[name='message']").is(":checked") == true){
			message = $("input[name='orderer_message']").val();
		}
		if (name == ''){
			alert("이름을 입력하세요");
		} else if(no == 0 && order_password == ''){
			alert("비회원 비밀번호를 입력해주세요.");
		}else if(phone == ''){
			alert("휴대폰 번호를 입력하세요");
		} else if(address1 =='' || address2 =='' || address3 =='' || address4 ==''){
			alert("모든 주소를 입력하세요");
		}
		else if (account_bank == 0){
			alert("계좌를 선택해주세요!");
		} else if(account_name == ''){
			alert("올바른 입금자명을 입력해주세요!");
		} else {
			$.ajax({
				url : "/order/deposit",
				type : "POST",
				data : {
					"order_name" : name,
					"order_phone" : phone,
					"order_address" : address,
					"user_no" : no,
					"order_message" : message,
					"account_bank" : account_bank,
					"account_name" : account_name,
					"point" : point,
					"total_price" : total_price,
					"order_password" : order_password
				},
				dataType : "html",
				success : function() {
					$.ajax({
						url : "/order/get/lastOrder",
						type : "POST",
						dataType : "html",
						success : function(order_no){
							for(var i=0; i<$(".product_info").length; i++){
								var product_no = $("input[name='checkout_product_no']").eq(i).val();
								var product_size = $("input[name='checkout_product_size']").eq(i).val();
								var product_color = $("input[name='checkout_product_color']").eq(i).val();
								var product_qty = $("input[name='checkout_product_qty']").eq(i).val();
								var product_price = $("input[name='checkout_product_price']").eq(i).val();
								$.ajax({
									url : "/order/info/add",
									type : "POST",
									dataType : "html",
									data : {
										"order_no" : order_no,
										"product_no" : product_no,
										"size_no" : product_size,
										"color_no" : product_color,
										"qty" : product_qty,
										"price" : product_price,
									},
									success : function(){
									},
									error : function() {
										alert('결제 실패');
									}
								});
							}
							location.replace("/order/complete/" + order_no);
						}
					});
				},
				error : function(result){
					alert(result);
				}
			});
		}
	}
	
	// 장바구니 전체 삭제
	function cart_deleteall_header() {
		var check = confirm("정말 장바구니를 비우시겠습니까?");
		if(check == true){
			$.ajax({
    			url : "/cart/deleteallAjax",
    			type : "POST",
    			dataType : "html",
    			success : function(){
    				alert("삭제가 완료되었습니다.");
    				location.reload();
    			},
    			error : function(){
    				alert("장바구니 비우기 실패");
    			}
    		});
		}
		else if(check == false){
		  return false;
		}
	}
 	
	 // 장바구니 ajax
    function wishlistOrderOptionAjax(no) {
		var url = "/wishlist/option/" + no;
		$.ajax({
			url : "/cart/option/",
			type : "POST",
			data : {
				"no" : no
			},
			dataType : "html",
			complete : function(xhr, textStatus) {
				var num = xhr.responseText;
				if (num > 0) {
					$("#wishlist_ajax_option").css({
						"top": (($(window).height()-$("#cart_ajax_option").outerHeight())/2+$(window).scrollTop())+"px",
						"left": (($(window).width()-$("#cart_ajax_option").outerWidth())/2+$(window).scrollLeft())+"px"
					 });
					$("#wishlist_ajax_option").load(url + " .cart_option").fadeIn(300);
				} else {
					alert("결과값이 없습니다!");
				} 				 
			},
		});

    }
	 
	// 위시리스트에서 바로구매 접근
	function wishlist_purchase(no){	
	    var productList = new Array();
		var productData = new Object();
		var size = $("#cart_ajax_size option:selected").val();
		var color = $("#cart_ajax_color option:selected").val();
		var qty = $("#cart_ajax_qty option:selected").val();
		
		productData.no = no;
		productData.size = size;
		productData.color = color;
		productData.qty = qty;
		
		productList.push(productData);
		 
		var jsonProductData = JSON.stringify(productList);
		 $.ajax({
	        type : "POST",
	        contentType: 'application/json',
	        dataType : "json",
	        url : "/checkout/jsonData",
	        data : jsonProductData,
	        success : function(data) {
	              location.href="/checkout/getForm";
	        },
	        error : function(e) {
	               alert('서버 연결 도중 에러가 났습니다. 다시 시도해 주십시오.');
	        }
		});
	}
	
	 // 퀵뷰 바로구매 ajax
    function purchaseQuickOptionAjax(no) {
		var size=$("#quick_cart_ajax_size option:selected").text();
		var color=$('.config-swatch-list > li.active > a').text();
		var sizeno=$("#quick_cart_ajax_size").val();
		var colorno=$('.config-swatch-list > li.active').val();
		var qty=$("#quick-qty").val();
		colorno = String(colorno);
		if(size == "사이즈를 선택하세요"){
			alert("사이즈를 선택하세요");
		} else if(color == ""){
			alert("색상을 선택하세요");
		} else {
			$.ajax({
				url : "/cart/option/stockCheck",
				type : "POST",
				data : {
					"no" : no,
					"size" : sizeno,
					"color" : colorno
				},
				dataType : "html",
				success : function(result) {
					if (result <= 0) {
						alert("수량이 부족합니다. 다른 옵션을 선택해주세요");
						$('#cart_option_button').hide();
					} else {
						var productList = new Array();
						var productData = new Object();
						
						productData.no = no;
						productData.size = sizeno;
						productData.color = colorno;
						productData.qty = qty;
						
						productList.push(productData);
						 
						var jsonProductData = JSON.stringify(productList);
						 $.ajax({
					        type : "POST",
					        contentType: 'application/json',
					        dataType : "json",
					        url : "/checkout/jsonData",
					        data : jsonProductData,
					        success : function(data) {
					              location.href="/checkout/getForm";
					        },
					        error : function(e) {
					               alert('서버 연결 도중 에러가 났습니다. 다시 시도해 주십시오.');
					        }
						});
					} 				 
				}
			});
		}
	}
	 
	// jQuery Datepicker
    $(function () {
        $('#datetimepicker1').datepicker({
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
    $(function () {
        $('#datetimepicker2').datepicker({
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
    
    // 주문 / 배송 날짜 조회
    function order_lookup_date() {
    	var d = new Date();
    	var year = (d.getFullYear() - 1);
    	var month = '' + (d.getMonth() + 1);
    	var date = '' + d.getDate();
    	if (month.length < 2) {
    		month = '0' + month;
    	}
    	if (date.length < 2) {
    		date = '0' + date;
    	}
    	var yearAgo = year + "-" + month + "-" + date;
    	var date1 = $("#datetimepicker1").val();
    	var date2 = $("#datetimepicker2").val();
    	if(date1 == '' || date2 == '') {
    		alert("시작일 또는 종료일을 선택하세요.");
    	} else if (date1 < yearAgo){
    		alert("1년 이전의 결과는 조회하실 수 없습니다.");
    	} else if (date1 > date2){
    		alert("시작일은 종료일보다 클 수 없습니다.");
    	} else {
    		location.href="/mypage?start=" + date1 + "&end=" + date2;
    	}
    }
    
 	// 구매확정 confirm
    function order_confirm(no, point){
    	var check = confirm("구매확정 이후에는 상품에 취소 / 환불이 불가능합니다.\n정말 구매확정 처리하시겠습니까?");
		if(check == true){
			location.href="/mypage/order/update/" + no + "?point=" + point;
			alert("포인트가 적립되었습니다.");
		} else {
			return false;
		}
    }
    
 	// 교환 / 반품 날짜 조회
    function order_change_lookup_date() {
    	var d = new Date();
    	var year = (d.getFullYear() - 1);
    	var month = '' + (d.getMonth() + 1);
    	var date = '' + d.getDate();
    	if (month.length < 2) {
    		month = '0' + month;
    	}
    	if (date.length < 2) {
    		date = '0' + date;
    	}
    	var yearAgo = year + "-" + month + "-" + date;
    	var date1 = $("#datetimepicker1").val();
    	var date2 = $("#datetimepicker2").val();
    	if(date1 == '' || date2 == '') {
    		alert("시작일 또는 종료일을 선택하세요.");
    	} else if (date1 < yearAgo){
    		alert("1년 이전의 결과는 조회하실 수 없습니다.");
    	} else if (date1 > date2){
    		alert("시작일은 종료일보다 클 수 없습니다.");
    	} else {
    		location.href="/mypage/change/?start=" + date1 + "&end=" + date2;
    	}
    }
    
 	// 배송완료 조회 
    function order_change_apply_lookup_date() {
    	var d = new Date();
    	var year = (d.getFullYear() - 1);
    	var month = '' + (d.getMonth() + 1);
    	var date = '' + d.getDate();
    	if (month.length < 2) {
    		month = '0' + month;
    	}
    	if (date.length < 2) {
    		date = '0' + date;
    	}
    	var yearAgo = year + "-" + month + "-" + date;
    	var date1 = $("#datetimepicker1").val();
    	var date2 = $("#datetimepicker2").val();
    	if(date1 == '' || date2 == '') {
    		alert("시작일 또는 종료일을 선택하세요.");
    	} else if (date1 < yearAgo){
    		alert("1년 이전의 결과는 조회하실 수 없습니다.");
    	} else if (date1 > date2){
    		alert("시작일은 종료일보다 클 수 없습니다.");
    	} else {
    		location.href="/mypage/change/apply?start=" + date1 + "&end=" + date2;
    	}
    }
 	
 	// 교환 / 환불 신청
 	function change_apply(no, type){
 		if (type == 0){
 			var check = confirm("교환 신청 하시겠습니까?");
 			if(check == true){
 				location.href="/mypage/exchange/apply?no=" + no + "&type=5";
 				alert("배송시 전달된 교환/환불 신청서를 작성후 택배사를 통해 직접 반품해주셔야 원할한 진행이 가능합니다.");
 			} else {
 				return false;
 			}
 		} else if(type == 1){
 			var check = confirm("환불 신청 하시겠습니까?");
 			if(check == true){
 				location.href="/mypage/exchange/apply?no=" + no + "&type=7";
 				alert("배송시 전달된 교환/환불 신청서를 작성후 택배사를 통해 직접 반품해주셔야 원할한 진행이 가능합니다.");
 			} else {
 				return false;
 			}
 		}
 	}
 	
 	// 개인정보 수정
 	function account_update(no){
 		var name =  $("input[name=name]").val();
 		var email = $("input[name=email]").val();
 		var phone = $("input[name=phone]").val();
 		var password = $("input[name=password]").val();
 		if (password =='') password = null;
 		if($("#change-pass-checkbox").is(":checked")){
			if(password == '' || $("input[name=password2]").val() == ''){
				alert("비밀번호를 입력하세요");
				return false;
			}
 			if ($("input[name=password]").val() != $("input[name=password2]").val()) {
 				alert("비밀번호가 일치하지 않습니다.");
 				return false;
 			}
 		}
 		$.ajax({
			url : "/mypage/myaccount/update",
			type : "POST",
			data : {
				"no" : no,
				"name" : name,
				"email" : email,
				"phone" : phone,
				"password" : password
			},
			dataType : "html",
			success : function(result) {
				if($("#change-pass-checkbox").is(":checked")){
					alert("비밀번호 변경으로 인해 로그아웃 처리 됩니다.\n다시 로그인해주세요.");
					location.href = "/user/logout";
					
				}
				else {
					alert("개인정보가 수정되었습니다.");
					location.reload();
				}
			}
		});
 	}
 	
 	// 회원탈퇴
 	function user_withdraw(){
 		var id =  $("input[name=id]").val();
 		var password = $("input[name=password]").val();
 		var check = confirm("회원 탈퇴하시면 모든 정보가 삭제됩니다.\n정말 회원탈퇴 하시겠습니까?");
			if(check == true){
				$.ajax({
					url : "/mypage/withdraw",
					type : "POST",
					data : {
						"id" : id,
						"password" : password
					},
					dataType : "html",
					success : function(result) {
						if (result == 0){
							location.href="/mypage/withdraw/complete"
						} else {
							alert("입력하신 정보와 현재 로그인된 사용자의 정보가 일치하지 않습니다.");
						}
					},
					error : function() {
						alert("실패");
					}
				});
			} else {
				return false;
			}
			
 	}
 	
 	// 배송지 추가
 	function mypage_address_add(){
 		
 		$.ajax({
			url : "/mypage/checkAddress",
			type : "POST",
			dataType : "html",
			success : function(result) {
				if (result == "many"){
					alert("저장가능한 배송지의 갯수는 최대 5개입니다.");
				} else {
					 $("#addressModal").modal("show"); 
				}
			},
			error : function() {
				alert("실패");
			}
		});
 	}
 	
 	// 기본 배송지 변경
 	function mypage_address_basic(no){
 		var check = confirm("기본 배송지를 변경하시겠습니까?");
		if(check == true){
	 		$.ajax({
				url : "/mypage/address/basic/update",
				type : "POST",
				dataType : "html",
				data : {
					"no" : no
				},
				success : function() {
					location.href="/mypage/address";
				},
				error : function() {
					alert("실패");
				}
			});
		} else {
			return false;
		}
 	}
 	
 	// 배송지 삭제
 	function mypage_address_del(no){
 		var check = confirm("배송지를 삭제하시겠습니까?");
		if(check == true){
	 		$.ajax({
				url : "/mypage/address/del",
				type : "POST",
				dataType : "html",
				data : {
					"no" : no
				},
				success : function() {
					location.href="/mypage/address";
				},
				error : function() {
					alert("실패");
				}
			});
		} else {
			return false;
		}
 	}
 	
 	// 마이페이지 주소 추가
	function mypage_address_new() {
		var name = $("input[name=modal_new_name]").val();
		var phone = $("input[name=modal_new_phone]").val();
		var zipcode = $("input[name=modal_new_zipcode]").val();
		var address = $("input[name=modal_new_address]").val();
		var detailaddress = $("input[name=modal_new_detailaddress]").val();
		var reference = $("input[name=modal_new_reference]").val();
		if(name == ''){
			alert("이름을 입력하세요");
		} else if(phone == ''){
			alert("휴대폰 번호를 입력하세요");
		} else if(zipcode == ''){
			alert("우편번호를 입력하세요");
		} else if(address == ''){
			alert("주소를 입력하세요");
		} else if(detailaddress == ''){
			alert("상세주소를 입력하세요");
		} else if(reference == ''){
			alert("참고항목을 입력하세요");
		} else {
			$.ajax({
				url : "/mypage/address/add",
				type : "POST",
				data : {
					"name" : name,
					"phone" : phone,
					"zipcode" : zipcode,
					"address" : address,
					"detailaddress" : detailaddress,
					"reference" : reference
				},
				dataType : "html",
				success : function(result) {
					location.reload();
				},
				error : function() {
					alert("저장에 실패하였습니다. 다시 시도해주세요.");
				}
			});
		}
	}
 	
 	// 마이페이지 주소 수정 modal
 	$(document).on("click", "#mypageUpdateAddress", function() {
		$('.updateModal_name').val($(this).data('name'));
		$('.updateModal_phone').val($(this).data('phone'));
		$('.updateModal_zipcode').val($(this).data('zipcode'));
		$('.updateModal_address').val($(this).data('address'));
		$('.updateModal_detailaddress').val($(this).data('detailaddress'));
		$('.updateModal_reference').val($(this).data('reference'));
		$('.updateModal_no').val($(this).data('addressno'));
	});
 	
 	// 마이페이지 주소 수정
 	function mypage_address_update() {
 		var no = $('.updateModal_no').val();
 		var name = $('.updateModal_name').val();
 		var phone = $('.updateModal_phone').val();
 		var zipcode = $('.updateModal_zipcode').val();
 		var address = $('.updateModal_address').val();
 		var detailaddress = $('.updateModal_detailaddress').val();
 		var reference = $('.updateModal_reference').val();
 		$.ajax({
			url : "/mypage/address/update",
			type : "POST",
			data : {
				"no" : no,
				"name" : name,
				"phone" : phone,
				"zipcode" : zipcode,
				"address" : address,
				"detailaddress" : detailaddress,
				"reference" : reference
			},
			dataType : "html",
			success : function(result) {
				location.reload();
			},
			error : function() {
				alert("수정에 실패하였습니다. 다시 시도해주세요.");
			}
		});
 	}
 	
    // 장바구니 총합 계산
 	$(document).ready(function() {
 		var contents = $("#mypage_review_contents").val();
 		var result = contents.replace(/(<br>|<br\/>|<br \/>)/g, '\r\n');
 		$("#mypage_review_contents").val(result);
 	});
    
    // 마이페이지 리뷰 삭제
    function mypage_review_del(no){
    	var check = confirm("상품평 삭제시 적립된 포인트는 회수됩니다.\n그래도 삭제하시겠습니까?");
		if(check == true){
			location.href="/mypage/review/del/" + no;
		} else {
			return false;
		}
    }
    
    // 상품별 리뷰 pagination load() 처리
    $(function(){
    	$(document).on("click","#paginationajax_review a",function(e) {
    		$("#review-pagination-load").hide(); 
    		$("#review-pagination-load").load($(this).attr("href") + " div#review-pagination-load").show();
    		e.preventDefault();
    	});
    });
    
    // 리뷰 펼치기 관련
    $(document).ready(function () {
    	$(document).on('click', '.review_before', function() {
    		$(this).parents('tr').hide();
    		$(this).parents('tr').next('tr').slideDown(1);
    	});
    	$(document).on('click', '.review_after', function() {
    		$(this).parents('tr').hide();
    		$(this).parents('tr').prev('tr').slideDown(1);
    	});
    });
    
    // 공지사항 소팅
    function notice_sorting() {
    	var sort = $('#orderby').val();
    	var url_original = "/custcenter/notice/?sort=" + sort;
		var url_string = window.location.href;
    	var url = new URL(url_string);
    	var category = url.searchParams.get("category");
    	var search = url.searchParams.get("search");

		if(category == null && search == null){
			location.href = url_original;
		} else if (category != null && search != null) {
			location.href = url_original + "&search=" + search + "&category=" + category;
		}
    }
    
    // 공지사항 검색
    function notice_search() {
    	var sort = $('#orderby').val();
    	var url_original = "/custcenter/notice/?sort=" + sort;
		var url_string = window.location.href;
    	var url = new URL(url_string);
    	var category = $("select[name=category]").val();
    	var search = $("#search").val();
    	
    	location.href = url_original + "&search=" + search + "&category=" + category;
    }
    
    // post방식의 form 새 윈도우 띄우기
    $(document).ready(function(){
   	  $("#btn_tracking").on("click", function(){
   		if (document.getElementById("orderno").value == ''){
   			alert("주문번호를 입력하세요");
   		} else if (document.getElementById("password").value == ''){
   			alert("비밀번호를 입력하세요");
   		} else {
   			window.open("", "popup_window", "width=1200, height=950, scrollbars=no");
   	   	    $("#myform").submit();
   		}
   	    
   	  });
   	});
    
    // 자바스크립트로 파일 확장자 구분하여 이미지 파일만 받기
    function counsel_imgcheck(){
    	var attach = document.getElementById('attach').value;
    	var filter = attach.slice(attach.indexOf(".") + 1).toLowerCase();
    	if(filter !='jpg' && filter != 'png' && filter != 'gif' && filter !='bmp'){
    		alert("이미지 관련 파일(jpg, png, gif, bmp) 파일만 등록 가능합니다.");
    		document.getElementById('attach').value = '';
    	}
    }
    
    // 검색 소팅
    function search_sorting(search){
    	var sort = $('#orderby').val();
    	location.href="/product/list?search=" + search + "&page=0&sort=" + sort;
    }
    
	// 자동완성
	$("#autocomplete").on('input', function() {
		var search = this.value;
		  $("#autocomplete").autocomplete({
	  	    source: '/product/search?search=' + search
	  	  });
		
	}); 
	
	// 최근 본상품 이동
	$(function() {
		var offset = $(".sidenav").offset();
		var topPadding = 100;
		$(window).scroll(function() {
			if ($(window).scrollTop() > offset.top) {
				$(".sidenav").stop().animate({
					marginTop: $(window).scrollTop() - offset.top + topPadding
				});
			} else {
				$(".sidenav").stop().animate({
					marginTop: 0
				});
			};
		});
	});
	
	// tooltip
	$(function() {
		$("[data-toggle='tooltip']").tooltip({
			position : {
				my : "left+15 center",
				at : "right center"
			},
			content : function() {
				return $(this).prop('title');
			},
			hide: { duration: 1 }
		});

	});
    
