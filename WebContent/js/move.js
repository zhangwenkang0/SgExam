//淡入，淡出效果
var addFadeEvent = function(obj, param) {
	var ul = null,
		isAllow = true;
	var liList = [];
	var curindex = 0,
		lilength = 0;

	var option = {
		dur: 1000,
		period: 10000
	};

	var init = function() {
		resizeImg();

		ul = $(obj).children("ul");
		liList = $(ul).children("li").css({
			position: 'absolute',
			left: 0,
			top: 0,
			opacity: 0
		});
		lilength = liList.length;
		param = param || {};
		$.extend(option, param);

		$(liList[curindex]).css({
			opacity: 1
		});
		initScrollListener();
	}

	var scrollImg = function(dir) {
		if (!isAllow) return;
		isAllow = false;
		setTimeout(function() {
			isAllow = true;
		}, option.dur / 2);
		var curobj, tarobj;
		curobj = liList[curindex];
		if (dir == "up") {
			curindex = (curindex + 1) % lilength;
		} else {
			curindex = (curindex - 1 + lilength) % lilength;
		}
		tarobj = liList[curindex];

		//$(tarobj).fadeIn(option.dur);
		$(tarobj).css({
			zIndex: -1,
			opacity: 1,
			left: -20
		}).css("filter", 'alpha(opacity=100)').stop().animate({
			left: 0
		}, option.dur);
		$(curobj).css({
			zIndex: 0
		}).stop().animate({
			left: 0,
			opacity: 0
		}, option.dur).css("filter", "alpha(opacity=100)");
		//$(curobj).fadeOut(option.dur,function(){
		//  $(tarobj).fadeIn(option.dur);
		//});
		clearTimeout(auto);
		auto = setTimeout(scrollImg, option.period);
	}
	var auto;
	auto = setTimeout(scrollImg, option.period);

	var fadeLeft = function() {
		scrollImg("up");
	}
	var fadeRight = function() {
		scrollImg("down");
	}

	var resizeImg = function() {
		var w = $(obj).width();
		var h = $(obj).height();

		$(obj).find("ul li").each(function() {
			$(this).css("width", w);
			$(this).find("img").each(function() {
				this.onload = function() {
					$(this).css("width", w);
					var img_h = $(this).height();
					if (img_h < h) {
						$(this).css("width", w * h / img_h);
					}

				};
				$(this).css("width", w);
				var img_h = $(this).height();
				if (img_h < h) {
					$(this).css("width", w * h / img_h);
				}
			});
		});
	}

	var initScrollListener = function() {
		var handler = function(e) {
			e = e || window.event;
			if (e.type == "mousewheel") {
				e.wheel = e.wheelDelta > 0 ? "up" : "down";
			} else {
				e.wheel = e.detail == 3 ? "down" : "up";
			}
			scrollImg(e.wheel);
		}

		//滚轮
		var type = "mousewheel";
		if (navigator.userAgent.indexOf("Firefox") != -1) type = "DOMMouseScroll";
		if (obj.addEventListener) {
			obj.addEventListener(type, handler, false);
		} else {
			obj.attachEvent("on" + type, handler);
		}

		//resize
		$(window).resize(function() {
			resizeImg();
		});
	}

	init();

	return {
		isleft: function() {
			return true;
		},
		isright: function() {
			return true;
		},
		left: fadeLeft,
		right: fadeRight
	}
}