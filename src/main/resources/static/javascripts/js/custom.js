/*************************************
**************************************
* 01. Bootstrap wysihtml5 editor
* 02. Background image
* 03. SlideShow Styles
* 04. Testimonial 1 Script
* 05. Single Side Slide
* 06. List Slide
* 08. Fast Click Select
* 09. Price Range Slider
* 10. Slim Scroll message & notification
* 11. METIS MENU
* 12. Add Listing Process
**************************************/
(function($){
	"use strict";
	
	
		/*---Bootstrap wysihtml5 editor --*/	
		$('.textarea').wysihtml5();
	
		//Background image ------------------
		var a = $(".bg");
		a.each(function (a) {
			if ($(this).attr("data-bg")) $(this).css("background-image", "url(" + $(this).data("bg") + ")");
		});



	/*----SlideShow Styles --------------*/
    function csselem() {
        $(".slideshow-container .slideshow-item").css({
            height: $(".slideshow-container").outerHeight(true)
        });
        $(".slider-container .slider-item").css({
            height: $(".slider-container").outerHeight(true)
        });
    }
    csselem();
	
	/*------ Testimonial 1 Script ----*/
	$('.testimonial-carousel').slick({
	  slidesToShow:2,
	  arrows: false,
	  autoplay:true,
	  responsive: [
		{
		  breakpoint: 768,
		  settings: {
			arrows: false,
			centerPadding: '0px',
			slidesToShow:2
		  }
		},
		{
		  breakpoint: 480,
		  settings: {
			arrows: false,
			centerPadding: '0px',
			slidesToShow: 1
		  }
		}
	  ]
	});
	
	/*--- Single Side Slide ---*/
	$('.single-side-slide').slick({
	  centerMode: true,
	  centerPadding: '0px',
	  slidesToShow:1,
	  responsive: [
		{
		  breakpoint: 768,
		  settings: {
			arrows:true,
			centerMode: true,
			centerPadding: '0px',
			slidesToShow:1
		  }
		},
		{
		  breakpoint: 480,
		  settings: {
			arrows:true,
			centerMode: true,
			centerPadding: '0px',
			slidesToShow: 1
		  }
		}
	  ]
	});
	
	/*---- List Slide ---*/
	$('.list-slider').slick({
	  centerMode: true,
	  autoplay: true,
	  arrows: false,
	  centerPadding: '0px',
	  slidesToShow: 3,
	  responsive: [
		{
		  breakpoint: 768,
		  settings: {
			arrows: false,
			centerMode: true,
			centerPadding: '0px',
			slidesToShow: 2
		  }
		},
		{
		  breakpoint: 480,
		  settings: {
			arrows: false,
			centerMode: true,
			centerPadding: '0px',
			slidesToShow: 1
		  }
		}
	  ]
	});
	
	
	/*----- Fast Click Select ------*/
	  $('select').niceSelect();  
	  
	 /*-------- Price Range Slider ------*/
	var s3 = $("#price-range").freshslider({
		range: true,
		step:1,
		value:[10, 500],
		onchange:function(low, high){
			console.log(low, high);
		}
	});
	
	  /*-------- Slim Scroll message & notification--------------*/
	  $('#main-menu').slimScroll({
		color: '#f4f5f7',
		size: '5px',
		height: '350px',
		alwaysVisible: true
	  });
	  
	  
	  /*--------- METIS MENU ----------*/
	$('#main-menu').metisMenu();
	
	/*---------- Add Listing Process ------*/
	$(function() {

		//jQuery time
		var current_fs, next_fs, previous_fs; //fieldsets
		var left, opacity, scale; //fieldset properties which we will animate
		var animating; //flag to prevent quick multi-click glitches

		$(".next").click(function(){
			if(animating) return false;
			animating = true;
			
			current_fs = $(this).parent();
			next_fs = $(this).parent().next();
			
			//activate next step on progressbar using the index of next_fs
			$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
			
			//show the next fieldset
			next_fs.show(); 
			//hide the current fieldset with style
			current_fs.animate({opacity: 0}, {
				step: function(now, mx) {
					//as the opacity of current_fs reduces to 0 - stored in "now"
					//1. scale current_fs down to 80%
					scale = 1 - (1 - now) * 0.2;
					//2. bring next_fs from the right(50%)
					left = (now * 50)+"%";
					//3. increase opacity of next_fs to 1 as it moves in
					opacity = 1 - now;
					current_fs.css({'transform': 'scale('+scale+')'});
					next_fs.css({'left': left, 'opacity': opacity});
				}, 
				duration: 800, 
				complete: function(){
					current_fs.hide();
					animating = false;
				}, 
				//this comes from the custom easing plugin
				easing: 'easeInOutBack'
			});
		});

		$(".previous").click(function(){
			if(animating) return false;
			animating = true;
			
			current_fs = $(this).parent();
			previous_fs = $(this).parent().prev();
			
			//de-activate current step on progressbar
			$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");
			
			//show the previous fieldset
			previous_fs.show(); 
			//hide the current fieldset with style
			current_fs.animate({opacity: 0}, {
				step: function(now, mx) {
					//as the opacity of current_fs reduces to 0 - stored in "now"
					//1. scale previous_fs from 80% to 100%
					scale = 0.8 + (1 - now) * 0.2;
					//2. take current_fs to the right(50%) - from 0%
					left = ((1-now) * 50)+"%";
					//3. increase opacity of previous_fs to 1 as it moves in
					opacity = 1 - now;
					current_fs.css({'left': left});
					previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
				}, 
				duration: 800, 
				complete: function(){
					current_fs.hide();
					animating = false;
				}, 
				//this comes from the custom easing plugin
				easing: 'easeInOutBack'
			});
		});

		$(".submit").click(function(){
			return false;
		})

		});
	

})(jQuery);

