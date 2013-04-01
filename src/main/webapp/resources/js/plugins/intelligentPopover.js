// -- intelligentPopover.js
// requires jQuery, boostrap-popover
(function($) {
	
	var Popover = {
		init : function(options, elem) {
			var self = this;
			self.$elem = $(elem);
			self.options = $.extend({}, $.fn.asynchSearch.options , options);
			self.$elem.popover({
				html : true,
				trigger : 'manual',
				title : self.options['title'],
				content : self.options['content'],
				placement: 'top',
				template: '<div class="popover" onmouseover="clearTimeout(timeoutObj);$(this).mouseleave(function() {$(this).hide();});"><div class="arrow"></div><div class="popover-inner"><h3 class="popover-title"></h3><div class="popover-content"><p></p></div></div></div>'
			}).mouseenter(function(e) {
				var $self = $(this);
				$self.popover('show');
			}).mouseleave(function(e) {
			    var $self = $(this);
			    timeoutObj = setTimeout(function(){
			        $self.popover('hide');
			    }, 100);
			});
		}
	}
	
	$.fn.intelligentPopover = function(options) {

		return this.each(function() {
			var popover = Object.create(Popover);
			popover.init(options, this);
		})
	}
	
	$.fn.intelligentPopover.options = {
			title : '',
			content : ' '
		};
})(jQuery);