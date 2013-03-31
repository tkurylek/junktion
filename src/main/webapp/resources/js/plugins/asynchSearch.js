(function($) {
	var Search = {
		init : function(options, elem) {
			var self = this;
			self.elem = elem;
			self.$elem = $(elem);
			self.options = $.extend({}, $.fn.asynchSearch.options , options);
			if(location.hash.indexOf('#'+self.options.servicePath) !== -1) {
				self.$elem.val(location.hash.replace('#'+self.options.servicePath,''));
			}
			self.cycle();
		}
		, cycle : function() {
			var self = this;
			$(self.options['form']).submit(function() {
				self.display('<div class="progress progress-striped active text-center"><div class="bar" style="width: 100%;"></div></div>');
				self.fetch()
					.done(function(results) {
						self.display(self.generateHtml(results));
					})
					.fail(function() {
						self.display('<p class="text-center">Brak wyników :(</p>');
					});
				return false;
			});
		}
		, generateHtml: function(results) {
			var self = this;
			var html = '';
			$(self.options.results).html(''); 
			$.each(results, function(i, document){
				$(self.options.results).append('<blockquote><dl>'
						+'<dt><i class="icon-file"></i>'+document['title']+' <span class="">'+document['filename']+'</span></dt>'
						+'<dd>'+(document['highlights'].join(' [...] '))+'</dd>'
						+'<small class="muted pull-right">'+document['path']+'</small>'
						+'</dl></blockquote>'
						+'<hr>');
			});
		}
		, display : function(html) {
			$(this.options.results).hide().html(html).fadeIn('fast');
		}
		, fetch : function() {
			var self = this;
			location.hash = self.options.servicePath+self.$elem.val();
			return $.ajax({
				url : self.options.servicePath+self.$elem.val()
				, dataType : 'json'
				, data : {}
			});
		}
	};

	$.fn.asynchSearch = function(options) {

		return this.each(function() {
			var search = Object.create(Search);
			search.init(options, this);
		})
	}
	
	$.fn.asynchSearch.options = {
		results : '.results',
		servicePath : '/search/',
		form : 'form'
	};
})(jQuery);