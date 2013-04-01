//Requires jQuery
(function($) {
	var Search = {
		init : function(options, elem) {
			var self = this;
			self.elem = elem;
			self.$elem = $(elem);
			self.loadingBarHtml = '<div class="progress progress-striped active text-center"><div class="bar" style="width: 100%;"></div></div>';
			self.options = $.extend({}, $.fn.asynchSearch.options , options);
			self.url = self.options['url'];
			self.$results = $(self.options.results);
			self.$form = $(self.options['form']);
			self.presentResultsOnSubmit();
			self.searchWithHashOnHashChange();
		}
		, searchWithHash : function() {
			var self = this;
			if(location.hash.indexOf('#'+self.url) !== -1) {
				self.$elem.val(location.hash.replace('#'+self.url,''));
				self.$form.submit();
			} else {
				location.hash = '';
				self.$elem.val('');
				self.$results.html('');
			}
		}
		
		, searchWithHashOnHashChange : function() {
			var self = this;
			self.searchWithHash();
			$(window).on('hashchange', function() {
				self.searchWithHash();
			});
		}
		, presentResultsOnSubmit : function() {
			var self = this;
			self.$form.submit(function() {
				self.presentResults();
				return false;
			});
		}
		, presentResults : function() {
			var self = this;
			self.display(self.loadingBarHtml);
			self.fetch()
				.done(function(results) {
					self.display(self.generateHtml(results));
				})
				.fail(function(reason) {
					self.display('<div class="text-center"><span class="alert alert-error">'+reason['responseText']+'</span></div>');
				});
		}
		, generateHtml: function(results) {
			var self = this;
			var html = '';
			self.$results.html(''); 
			$.each(results, function(i, document){
				self.$results.append('<blockquote><dl>'
						+'<dt><i class="icon-file"></i>'+document['title']+' <span class="">'+document['filename']+'</span></dt>'
						+'<dd>'+(document['highlights'].join(' [...] '))+'</dd>'
						+'<small class="muted pull-right">'+document['path']+'</small>'
						+'</dl></blockquote>'
						+'<hr>');
			});
		}
		, display : function(html) {
			var self = this;
			self.$results.hide().html(html).fadeIn('fast');
		}
		, fetch : function() {
			var self = this;
			location.hash = self.url+self.$elem.val();
			return $.ajax({
				url : self.url+self.$elem.val()
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
		url : '/search/',
		form : 'form'
	};
})(jQuery);