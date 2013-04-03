// -- intelligentPopover.js
// requires jQuery, boostrap-popover
(function($) {
	
	var Popover = {
		init : function(options, elem) {
			var self = this;
			self.$elem = $(elem);
			self.options = $.extend({}, $.fn.intelligentPopover.options , options);
			self.$elem.popover({
				html : true,
				trigger : 'hover',
				title : self.options['title'],
				content : self.options['content'],
				placement: 'top',
				delay : {show: 1000, hide: 10}
			})
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