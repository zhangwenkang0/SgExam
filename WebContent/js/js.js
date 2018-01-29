///*子菜单*/
var SPAN = 103;
var width = $(window).width();
var height = $(window).height();

$(function() {
	if (width >= 1680) {
		SPAN = 124;
	} else {
		SPAN = 103;
	}
	if (height < 550) height = 550;
	var h_num = (Math.floor(height / SPAN) - 3) * SPAN;
	var mar_top = h_num + "px";
	if (width >= 1279) {
		$("#top_nav").css("width", SPAN * 5);
		$('.wrap_main').css("top", mar_top).css("left", SPAN * 3 + "px");
	} else if (width < 1279 && width >= 950) {
		mar_top = h_num + 35;
		$('.wrap_main').css("top", mar_top).css("left", SPAN + "px");
		//$('#top_nav').append('<div class="top_nav_box"></div>');
	} else {
		mar_top = (Math.floor(height / SPAN) - 5) * SPAN + 35;
		$('.wrap_main').css("top", mar_top).css("left", SPAN * 2 + "px");
	}
	$('.list_top').css("height", SPAN * 3 + "px");
	$('.logo').find('img').prop("height", SPAN - 1);
	var crumbswid = (document.body.clientWidth - 960) / 2 + 209;
	$('.crumbs_left').css("width", crumbswid + "px");
	$('.menuname').css("top", SPAN * 3 - 50 + "px").css("left", (document.body.clientWidth - 960) / 2 + "px");

	$('.xq_box').css("top", mar_top);
	$('.xq_box').css("height", SPAN * 2 + "px");
	$('.xq_bg').css("height", SPAN * 2 + "px");
	$('.xq_main').css("height", SPAN * 2 + "px");

	//edit by zjcf
	if (width >= 1279) {
		$("#top_nav").css({
			width: SPAN * 4 + "px",
			height: SPAN * 2 + "px"
		}).children("ul").css("width", SPAN * 6 + "px");
		$("li.nav-li.unuse>div").css({
			width: SPAN + "px",
			height: SPAN + "px"
		});
		$("div.hover_right").css("margin-left", SPAN * 7 + "px");
	} else {
		if (width < 768) width = 768;
		$("#top_nav").css({
			width: width - SPAN * 3 + "px",
			height: SPAN * 2 + "px"
		}).children("ul").css("width", width - SPAN * 2);
		$("li.nav-li.unuse>div").css({
			width: width - SPAN * 7,
			height: SPAN
		}).find(".line").hide();
		$("div.hover_right").css("margin-left", 0);
	}
	//end
});

$(window).resize(function() {
	width = document.documentElement.clientWidth;
	height = document.documentElement.clientHeight;
	if (width >= 1680) {
		SPAN = 124;
	} else {
		SPAN = 103;
	}
	if (height < 550) height = 550;
	var h_num = (Math.floor(height / SPAN) - 3) * SPAN;
	var mar_top = h_num + "px";
	if (width >= 1279) {
		$('.wrap_main').css("top", mar_top).css("left", SPAN * 3 + "px");
	} else if (width < 1279 && width >= 950) {
		mar_top = h_num + 35;
		$('.wrap_main').css("top", mar_top).css("left", SPAN + "px");
		//$('#top_nav').append('<div class="top_nav_box"></div>');
	} else {
		mar_top = (Math.floor(height / SPAN) - 5) * SPAN + 35;
		$('.wrap_main').css("top", mar_top).css("left", SPAN * 2 + "px");
	}
	$('.list_top').css("height", SPAN * 3 + "px");
	$('.logo').find('img').prop("height", SPAN - 1);
	var crumbswid = (document.body.clientWidth - 960) / 2 + 209;
	$('.crumbs_left').css("width", crumbswid + "px");
	$('.menuname').css("top", SPAN * 3 - 50 + "px").css("left", (document.body.clientWidth - 960) / 2 + "px");

	$('.xq_box').css("top", mar_top);
	$('.xq_bg').css("height", SPAN * 2 + "px");
	$('.xq_main').css("height", SPAN * 2 + "px");

	//edit by zjcf
	width = document.documentElement.clientWidth
	if (width >= 1279) {
		$("#top_nav").css({
			width: SPAN * 4 + "px",
			height: SPAN * 2 + "px"
		}).children("ul").css("width", SPAN * 6 + "px").find("li.nav-li .top_nav").css({
			width: SPAN + "px"
		});
		$("li.nav-li.unuse>div").css({
			width: SPAN + "px",
			height: SPAN + "px"
		}).find(".line").show();
		$(".hover_right").css({
			marginLeft: SPAN * 7 + "px"
		})
	} else {
		if (width < 768) width = 768;
		$("#top_nav").css({
			width: width - SPAN * 3,
			height: SPAN * 2 + "px"
		}).children("ul").css({
			width: width - SPAN * 2
		}).find("li.nav-li .top_nav").css({
			width: SPAN + "px"
		});
		$("li.nav-li.unuse>div").css({
			width: width - SPAN * 7,
			height: SPAN
		}).find(".line").hide();
		$(".hover_right").css({
			marginLeft: 0
		})
	}
	//end

});

$(function() {
	$("#top_nav").hover(
		function() {
			var w = document.documentElement.clientWidth;
			if (w > 1279) {
				$(this).css({
					width: SPAN * 5 + "px"
				});
				$("div.hover_right").css("margin-left", SPAN * 8 + "px")
			}
		},
		function() {
			var w = document.documentElement.clientWidth;
			if (w > 1279) {
				$(this).css({
					width: SPAN * 4 + "px"
				});
				$("div.hover_right").css("margin-left", SPAN * 7 + "px")
			}
		}
	);
	$('#top_nav li.nav-li').not(".unuse").hover(
		function() {
			var w = document.documentElement.clientWidth;
			var _this_ = $(this).addClass("hover");
			if (w > 1024) {
				_this_.children(".top_nav").stop().animate({
					width: SPAN * 2 + "px"
				}, "normal", function() {
					_this_.children(".nav-child").show().addClass('rotate');
				})
			} else {
				_this_.children(".nav-child").slideDown("fast");
			}
		},
		function() {
			var w = document.documentElement.clientWidth;
			var _this_ = $(this).removeClass("hover");
			if (w > 1024) {
				_this_.children(".nav-child").hide().removeClass("rotate");
				_this_.children(".top_nav").stop().animate({
					width: SPAN + "px"
				}, "normal");
			} else {
				//  _this_.children(".nav-child").slideUp("fast");    
				_this_.children(".nav-child").hide();
			}

		}
	);
});

$(function() {
	$('.animate').hover(function() {
			var _this_ = $(this);
			_this_.find('span').stop().animate({
				"width": "60px"
			}, "slow", function() {
				_this_.find('dl').slideDown('slow');
			});
		},
		function() {
			var _this_ = $(this);
			_this_.find('span').stop().animate({
				"width": "0px"
			}, "slow");
			_this_.find('dl').slideUp('fast');
		});
});

$(function() {
	$('.animate2').hover(function() {
			$(this).find('dl').slideDown('slow');
		},
		function() {
			$(this).find('dl').slideUp('fast');
		});
});

function AddRand(url) {
	if (url.indexOf('?') == -1) {
		url += '?';
	} else {
		url += '&';
	}
	return url + 'random=' + Math.random();
}

function refreshCaptcha() {
	var $img = $('#captchaimg');
	var src = $img.attr('src');
	var index = src.indexOf('?');
	if (index != -1) {
		src = src.substring(0, index);
	}
	src += '?rnd=' + Math.random();
	$img.attr('src', src);
}

$(function() {
	$('#submitbutton').click(function() {
		var $form = $('#submitform');
		var data = $form.serialize();
		var url = $form.attr('action');
		$.post(AddRand(url), data, function(result) {
			eval(result);
		});
	});
	$('#submitform').submit(function() {
		return false;
	});
});
//js.js
(function($) {
	$.fn.sGallery = function(o) {
		return new $sG(this, o);
		//alert('do');
	};

	var settings = {
		thumbObj: null, //预览对象
		titleObj: null, //标题
		botLast: null, //按钮上一个
		botNext: null, //按钮下一个
		thumbNowClass: 'now', //预览对象当前的class,默认为now
		slideTime: 800, //平滑过渡时间
		autoChange: true, //是否自动切换
		changeTime: 5000, //自动切换时间
		delayTime: 100 //鼠标经过时反应的延迟时间
	};

	$.sGalleryLong = function(e, o) {
		this.options = $.extend({}, settings, o || {});
		var _self = $(e);
		var set = this.options;
		var thumb;
		var size = _self.size();
		var nowIndex = 0; //定义全局指针
		var index; //定义全局指针
		var startRun; //预定义自动运行参数
		var delayRun; //预定义延迟运行参数

		//初始化
		_self.eq(0).show();

		//主切换函数
		function fadeAB() {
			if (nowIndex != index) {
				if (set.thumbObj != null) {
					$(set.thumbObj).removeClass().eq(index).addClass(set.thumbNowClass);
				}
				_self.eq(nowIndex).stop(false, true).fadeOut(set.slideTime);
				_self.eq(index).stop(true, true).fadeIn(set.slideTime);
				$(set.titleObj).eq(nowIndex).hide(); //新增加title
				$(set.titleObj).eq(index).show(); //新增加title
				nowIndex = index;
				if (set.autoChange == true) {
					clearInterval(startRun); //重置自动切换函数
					startRun = setInterval(runNext, set.changeTime);
				}
			}
		}

		//切换到下一个
		function runNext() {
			index = (nowIndex + 1) % size;
			fadeAB();
		}

		//点击任一图片
		if (set.thumbObj != null) {
			thumb = $(set.thumbObj);
			//初始化
			thumb.eq(0).addClass(set.thumbNowClass);
			thumb.bind("mousemove", function(event) {
				index = thumb.index($(this));
				fadeAB();
				delayRun = setTimeout(fadeAB, set.delayTime);
				clearTimeout(delayRun);
				event.stopPropagation();
			})
		}

		//点击上一个
		if (set.botNext != null) {
			var botNext = $(set.botNext);
			botNext.mousemove(function() {
				runNext();
				return false;
			});
		}

		//点击下一个
		if (set.botLast != null) {
			var botLast = $(set.botLast);
			botLast.mousemove(function() {
				index = (nowIndex + size - 1) % size;
				fadeAB();
				return false;
			});
		}

		//自动运行
		if (set.autoChange == true) {
			startRun = setInterval(runNext, set.changeTime);
		}

	}

	var $sG = $.sGalleryLong;

})(jQuery);

function slide(Name, Class, Width, Height, fun) {
	$(Name).width(Width);
	$(Name).height(Height);

	if (fun == true) {
		$(Name).append('<div class="title-bg"></div><div class="title-pic"></div><div class="change"></div>')
		var atr = $(Name + ' div.changeDiv a');
		var sum = atr.length;
		for (i = 1; i <= sum; i++) {
			var title = atr.eq(i - 1).attr("title");
			var href = atr.eq(i - 1).attr("href");
			$(Name + ' .change').append('<i>' + i + '</i>');
			$(Name + ' .title-pic').append('<a href="' + href + '">' + title + '</a>');
		}
		$(Name + ' .change i').eq(0).addClass('cur');
	}
	$(Name + ' div.changeDiv a').sGallery({ //对象指向层，层内包含图片及标题
		titleObj: Name + ' div.title-pic a',
		thumbObj: Name + ' .change i',
		thumbNowClass: Class
	});
	$(Name + " .title-bg").width(Width);
}

//缩略图
jQuery.fn.LoadImage = function(scaling, width, height, loadpic) {
	if (loadpic == null) loadpic = "../images/msg_img/loading.gif";
	return this.each(function() {
		var t = $(this);
		var src = $(this).attr("src")
		var img = new Image();
		img.src = src;
		//自动缩放图片
		var autoScaling = function() {
				if (scaling) {
					if (img.width > 0 && img.height > 0) {
						if (img.width / img.height >= width / height) {
							if (img.width > width) {
								t.width(width);
								t.height((img.height * width) / img.width);
							} else {
								t.width(img.width);
								t.height(img.height);
							}
						} else {
							if (img.height > height) {
								t.height(height);
								t.width((img.width * height) / img.height);
							} else {
								t.width(img.width);
								t.height(img.height);
							}
						}
					}
				}
			}
			//处理ff下会自动读取缓存图片
		if (img.complete) {
			autoScaling();
			return;
		}
		$(this).attr("src", "");
		var loading = $("<img alt=\"加载中...\" title=\"图片加载中...\" src=\"" + loadpic + "\" />");

		t.hide();
		t.after(loading);
		$(img).load(function() {
			autoScaling();
			loading.remove();
			t.attr("src", this.src);
			t.show();
			//$('.photo_prev a,.photo_next a').height($('#big-pic img').height());
		});
	});
}

//向上滚动代码
function startmarquee(elementID, h, n, speed, delay) {
	var t = null;
	var box = '#' + elementID;
	$(box).hover(function() {
		clearInterval(t);
	}, function() {
		t = setInterval(start, delay);
	}).trigger('mouseout');

	function start() {
		$(box).children('ul:first').animate({
			marginTop: '-=' + h
		}, speed, function() {
			$(this).css({
				marginTop: '0'
			}).find('li').slice(0, n).appendTo(this);
		})
	}
}

//TAB切换
function SwapTab(name, title, content, Sub, cur) {
	$(name + ' ' + title).mouseover(function() {
		$(this).addClass(cur).siblings().removeClass(cur);
		$(content + " > " + Sub).eq($(name + ' ' + title).index(this)).show().siblings().hide();
	});
}
//返回顶部
function goTopEx() {
	var obj = document.getElementById("goTopBtn");

	function getScrollTop() {
		return document.documentElement.scrollTop || document.body.scrollTop;
	}

	function setScrollTop(value) {
		document.documentElement.scrollTop = value;
		document.body.scrollTop = value;
	}
	window.onscroll = function() {
		getScrollTop() > 0 ? obj.style.display = "block" : obj.style.display = "none";
	}
	obj.onclick = function() {
		var goTop = setInterval(scrollMove, 10);

		function scrollMove() {
			setScrollTop(getScrollTop() / 1.1);
			if (getScrollTop() < 1) clearInterval(goTop);
		}
	}
}
//滑动
$(function() {
	new slide("#main-slide", "cur", 990, 250, 1); //焦点图
	new SwapTab(".SwapTab", "span", ".tab-content", "ul", "fb"); //排行TAB
})

function setTab(m, n) {
	var tli = document.getElementById("mm" + m).getElementsByTagName("li");
	var mli = document.getElementById("nn" + m).getElementsByTagName("ul");
	for (i = 0; i < tli.length; i++) {
		tli[i].className = i == n ? "hover" : "";
		mli[i].style.display = i == n ? "block" : "none";
	}
}
//图片切换
function DY_scroll(wraper, prev, next, img, speed, or) {
	var pic_num = 0;
	var wraper = $(wraper);
	var prev = $(prev);
	var next = $(next);
	var img = $(img);
	var pic_count = img.find('li').length;
	var w = img.find('li').outerWidth(true);
	var s = speed;
	next.click(function() {
		pic_num = pic_num < pic_count ? ((pic_num + 1) % pic_count) : 0;
		img.animate({
			'margin-left': -w
		}, function() {
			img.find('li').eq(0).appendTo(img);
			img.css({
				'margin-left': 0
			});
			$('#banner_ico li.on').removeClass('on');
			$("#banner_ico li").eq(pic_num).addClass('on');
		});
	});
	prev.click(function() {
		pic_num = pic_num == 0 ? pic_count - 1 : pic_num - 1;
		img.find('li:last').prependTo(img);
		img.css({
			'margin-left': -w
		});
		img.animate({
			'margin-left': 0
		});
		$('#banner_ico li.on').removeClass('on');
		$("#banner_ico li").eq(pic_num).addClass('on');
	});
	$('#banner_ico li').click(function() {
		var tmp_num = $(this).index('#banner_ico li');
		var count = pic_num;
		if (tmp_num < count) {
			for (var i = count; i > tmp_num; i--) {
				prev.click();
			}
		} else {
			for (var i = tmp_num; i > count; i--) {
				next.click();
			}
		}

	});

	if (or == true) {
		ad = setInterval(function() {
			next.click();
		}, s * 1000);
		wraper.hover(function() {
			clearInterval(ad);
		}, function() {
			ad = setInterval(function() {
				next.click();
			}, s * 1000);
		});
	}
}
// 首页9大块
var lazyLayout = function(elem, _option_) {
	var elem = elem;
	var option = {
		grid_v: 8,
		item_w: 0,
		item_h: 0
	};
	$.extend(option, _option_);
	option.item_w = option.item_h = $(elem).width() / option.grid_v;

	var centerobj = null;

	var zoomconfig = [{
		w: 495,
		h: 247,
		l: 248,
		t: 0
	}, {
		w: 619,
		h: 247,
		l: 248,
		t: 0
	}, {
		w: 411,
		h: 205,
		l: 206,
		t: 0
	}, {
		w: 514,
		h: 205,
		l: 206,
		t: 0
	}, {
		w: 412,
		h: 205,
		l: 0,
		t: 0
	}];
	var normalconfig = [
		[{
			w: 123,
			h: 123,
			l: 0,
			t: 0
		}, {
			w: 123,
			h: 123,
			l: 124,
			t: 0
		},
//		 {
//			w: 123,
//			h: 123,
//			l: 744,
//			t: 0
//		}, {
//			w: 123,
//			h: 123,
//			l: 868,
//			t: 0
//		},
		{
			w: 123,
			h: 123,
			l: 0,
			t: 124
		}, {
			w: 123,
			h: 123,
			l: 124,
			t: 124
		}
//		, {
//			w: 123,
//			h: 123,
//			l: 744,
//			t: 124
//		}, {
//			w: 123,
//			h: 123,
//			l: 868,
//			t: 124
//		}
		],
		[{
			w: 123,
			h: 123,
			l: 0,
			t: 0
		}, {
			w: 123,
			h: 123,
			l: 124,
			t: 0
		}, 
//		{
//			w: 123,
//			h: 123,
//			l: 868,
//			t: 0
//		}, {
//			w: 123,
//			h: 123,
//			l: 992,
//			t: 0
//		}, 
		{
			w: 123,
			h: 123,
			l: 0,
			t: 124
		}, {
			w: 123,
			h: 123,
			l: 124,
			t: 124
		}
//		, {
//			w: 123,
//			h: 123,
//			l: 868,
//			t: 124
//		}, {
//			w: 123,
//			h: 123,
//			l: 992,
//			t: 124
//		}
		],
		[{
			w: 102,
			h: 102,
			l: 0,
			t: 0
		}, {
			w: 102,
			h: 102,
			l: 103,
			t: 0
		},
//		{
//			w: 102,
//			h: 102,
//			l: 618,
//			t: 0
//		}, {
//			w: 102,
//			h: 102,
//			l: 721,
//			t: 0
//		}, 
		{
			w: 102,
			h: 102,
			l: 0,
			t: 103
		}, {
			w: 102,
			h: 102,
			l: 103,
			t: 103
		}
//		, {
//			w: 102,
//			h: 102,
//			l: 618,
//			t: 103
//		}, {
//			w: 102,
//			h: 102,
//			l: 721,
//			t: 103
//		}
		],
		[{
			w: 102,
			h: 102,
			l: 0,
			t: 0
		}, {
			w: 102,
			h: 102,
			l: 103,
			t: 0
		},
//		{
//			w: 102,
//			h: 102,
//			l: 721,
//			t: 0
//		}, {
//			w: 102,
//			h: 102,
//			l: 824,
//			t: 0
//		}, 
		{
			w: 102,
			h: 102,
			l: 0,
			t: 103
		}, {
			w: 102,
			h: 102,
			l: 103,
			t: 103
		}
//		, {
//			w: 102,
//			h: 102,
//			l: 721,
//			t: 103
//		}, {
//			w: 102,
//			h: 102,
//			l: 824,
//			t: 103
//		}
		],
		[{
			w: 102,
			h: 102,
			l: 0,
			t: 206
		}, {
			w: 102,
			h: 102,
			l: 103,
			t: 206
		}, {
			w: 102,
			h: 102,
			l: 206,
			t: 206
		}, {
			w: 102,
			h: 102,
			l: 309,
			t: 206
		}, {
			w: 102,
			h: 102,
			l: 0,
			t: 309
		}, {
			w: 102,
			h: 102,
			l: 103,
			t: 309
		}, {
			w: 102,
			h: 102,
			l: 206,
			t: 309
		}, {
			w: 102,
			h: 102,
			l: 309,
			t: 309
		}]
	];
	var ww = $(window).width();
	var curzoom;
	var curnormal;
	//var curzoom = ww>1680 ? zoomconfig[0] : zoomconfig[3];
	//var curnormal = ww> 1680 ? normalconfig[0] : normalconfig[3];
	if (ww >= 1800) {
		curzoom = zoomconfig[1];
		curnormal = normalconfig[1];
	} else if (ww > 1679 && ww < 1800) {
		curzoom = zoomconfig[0];
		curnormal = normalconfig[0];
	} else if (ww <= 1679 && ww > 1360) {
		curzoom = zoomconfig[3];
		curnormal = normalconfig[3];
	} else if (ww <= 1360 && ww >= 950) {
		curzoom = zoomconfig[2];
		curnormal = normalconfig[2];
	} else {
		curzoom = zoomconfig[4];
		curnormal = normalconfig[4];
	}

	var init = function() {
		var index = 0;
		$(".wrap_main_ul .griditem").each(function() {
			if ($(this).hasClass("zoom")) {
				centerobj = this;
				$(this).css({
					left: curzoom.l,
					top: curzoom.t,
					width: curzoom.w,
					height: curzoom.h
				});
			} else {
				$(this).find(".main_ul").hide();
				$(this).css({
					left: curnormal[index].l,
					top: curnormal[index].t,
					width: curnormal[index].w,
					height: curnormal[index].h
				});
				index++;
			}
		});
	}

	var zoom = function() {
		var pos = $(this).position();
		$(this).stop().animate({
			left: curzoom.l,
			top: curzoom.t,
			width: curzoom.w,
			height: curzoom.h
		}, function() {
			$(this).find(".main_ul").fadeIn();
		}).addClass("zoom").removeClass("box1");
	}
	var narrow = function(pos) {
		$(this).stop().animate({
			left: pos.left,
			top: pos.top,
			width: curnormal[0].w,
			height: curnormal[1].h
		}, function() {
			$(this).find(".main_ul").hide();
			$(this).removeClass("zoom").addClass("box1");
		});
	}

	var change = function(obj) {
		if ($(obj).hasClass("zoom")) return;
		var pos = $(obj).position();
		//var oldObj = $(elem).find(".zoom");
		zoom.call(obj);
		narrow.call(centerobj, pos);
		centerobj = obj;
	}

	$(window).resize(function() {

		var ww = $(window).width();
		if (ww >= 1800) {
			curzoom = zoomconfig[1];
			curnormal = normalconfig[1];
		} else if (ww >= 1679 && ww < 1800) {
			curzoom = zoomconfig[0];
			curnormal = normalconfig[0];
		} else if (ww < 1679 && ww > 1360) {
			curzoom = zoomconfig[3];
			curnormal = normalconfig[3];
		} else if (ww <= 1360 && ww >= 950) {
			curzoom = zoomconfig[2];
			curnormal = normalconfig[2];
		} else {
			curzoom = zoomconfig[4];
			curnormal = normalconfig[4];
		}

		init();
	});

	$(".wrap_main_ul .griditem").click(function() {
		change(this);
	});

	init();
}

$(function() {
	lazy = new lazyLayout($('.wrap_main_ul'), {});
})

//菜单
$(window).scroll(function() {
	var wrapsub = document.getElementById("wrap_sub");
	if (wrapsub != undefined) {
		var t = document.getElementById("wrap_sub").getBoundingClientRect().top;
		if (t < 0) $(".wrap_sub").addClass("wrap_fix");
		else $(".wrap_sub").removeClass("wrap_fix");
	}
});

//二级页面背景等比缩放
$(function() {
	var w = document.documentElement.clientWidth;
	if (w < 1000) w = 1000;
	var lt = $(".list_top");
	var lh = lt.height();
	lt.find("ul img").css("width", w).load(function() {
		var h = $(this).height();
		if (h < lh) {
			$(this).css("width", w * lh / h);
		}
	});

	$(window).resize(function() {
		var w = document.documentElement.clientWidth;
		if (w < 1000) w = 1000;
		var lt = $(".list_top");
		var lh = lt.height();
		lt.find("ul img").css("width", w).each(function() {
			var h = $(this).height();
			if (h < lh) {
				$(this).css("width", w * lh / h);
			}
		});

	});
})
