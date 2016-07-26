$(document).ready(function(){
							
	$("#select1 a").click(function () {
		$(this).addClass("selected").siblings().removeClass("selected");
		if ($(this).hasClass("select-all")) {
			$("#selectA").remove();
		} else {
			var copyThisA = $(this).clone();
			copyThisA.removeClass("selected");
			copyThisA.attr("style","margin-top:0px");
			if ($("#selectA").length > 0) {
				$("#selectA").html($(this).text());
			} else {
				$(".select-result dl").append(copyThisA.attr("id", "selectA"));
			}
		}
	});
	
	$("#select2 a").click(function () {
		$(this).addClass("selected").siblings().removeClass("selected");
		if ($(this).hasClass("select-all")) {
			$("#selectB").remove();
		} else {
			var copyThisB = $(this).clone();
			if ($("#selectB").length > 0) {
				$("#selectB").html($(this).text());
			} else {
				$(".select-result dl").append(copyThisB.attr("id", "selectB"));
			}
		}
	});
	
	$("#select3 a").click(function () {
		$(this).addClass("selected").siblings().removeClass("selected");
		if ($(this).hasClass("select-all")) {
			$("#selectC").remove();
		} else {
			var copyThisC = $(this).clone();
			if ($("#selectC").length > 0) {
				$("#selectC").html($(this).text());
			} else {
				$(".select-result dl").append(copyThisC.attr("id", "selectC"));
			}
		}
	});
	
	$("#selectA").live("click", function () {
		$(this).remove();
		$("#select1 .select-all").addClass("selected").siblings().removeClass("selected");
	});
	
	$("#selectB").live("click", function () {
		$(this).remove();
		$("#select2 .select-all").addClass("selected").siblings().removeClass("selected");
	});
	
	$("#selectC").live("click", function () {
		$(this).remove();
		$("#select3 .select-all").addClass("selected").siblings().removeClass("selected");
	});
	
	$(".select a").live("click", function () {
		if ($(".select-result dd").length > 1) {
			$(".select-no").hide();
		} else {
			$(".select-no").show();
		}
	});
	$(".nav-header").on("click",function(){
	    //通过判断按钮btn有没有active这个class名判断是否已经点击过
	    if($(this).hasClass("active")){
		 $(this).parents('dl').find('dd').hide()
		 $(this).css("background","url('../resources/img/new_version/jiantou2.png') no-repeat left center");
	    	 $(this).removeClass("active");

	    }else{
		  $(this).parents('dl').find('dd').show();
		  $(this).css("background","url('../resources/img/new_version/jiantou1.png')  no-repeat left center");
		  $(this).addClass("active");
	    }
	})
});

	
	
	
    $(document).ready(function() { 
    	 var url = $("#urlin").val();
			$.post(url,function(data){
				 var json = eval('('+data+')');
				 $("#ulcd").html(json);
				 
				 $("tr:odd").css("background-color","#f5f5f5"); 
					$("tr:even").css("background-color","#ffffff"); 
					$(".nav-list dd").click(function(){
				  		$(this).addClass('active')
				  		.siblings().removeClass('active');
					 })
					 $(".nav li").on("click",function(){
					    //通过判断按钮btn有没有active这个class名判断是否已经点击过
					    if(!$(this).find("ul").hasClass("dropdown-menu")){
					    	
							 var caidan = $(this).find('.nav_erji').html();
					 	     $('#ej_nav').html(caidan);
					 	   
					    }else{
							 $('#ej_nav').html(caidan);
							 
							 }
					      })	
						
			});
			
			 
			
			   
		      
			  $("#ej_nav").on("click","dd",function(){
					 $(this).addClass('nav_sj_active')
			  		.siblings().removeClass('nav_sj_active');
					 var url =  $("#urlses").val();
				 	    var datas = {"sess":$(this).parent().html()};
						 $.post(url,datas,function(){
							 
						 });
					})
			//下面为新增加js
			  $(".index_riqileft li").click(function(){
					 $(this).addClass('riqi_active')
			  		.siblings().removeClass('riqi_active');
					  })
				 
			 $("#nav_right li").click(function(){
					 $(this).addClass('nav_right_active')
			  		.siblings().removeClass('nav_right_active');
					  })
					  
					  
					  	 
			 $(".nav_right1").click(function(){
					$("#nav_gengxin").slideToggle();
					  })
			
			   

			 $("#nav_gengxin").mouseleave(function(){
					$(this).slideUp();
					  })
	        
			
        }) 
        
        
	