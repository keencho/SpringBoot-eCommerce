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
		
	});
	
	// 장바구니 ajax 수량체크
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
				"size" : size,
				"color" : color
			},
			dataType : "html",
			success : function() {
				location.href="/cart";	 
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
	