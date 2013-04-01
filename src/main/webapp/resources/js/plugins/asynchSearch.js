// -- asynchSearch.js
// Requires jQuery
(function($) {
	var Search = {
		init : function(options, elem) {
			var self = this;
			self.elem = elem;
			self.$elem = $(elem);
			self.options = $.extend({}, $.fn.asynchSearch.options , options);
			self.loadingBarHtml = '<div id="asynchSearch-loading" class="progress progress-striped active text-center"><div class="bar" style="width: 100%;"></div></div>';
			self.loadingBarId = '#asynchSearch-loading';
			self.moreButtonHtml = '<div id="asynchSearch-more" class="btn btn-large btn-primary text-center">'+self.options['more']+'</div>';
			self.moreButtonId = '#asynchSearch-more';
			self.url = self.options['url'];
			self.$results = $(self.options.results);
			self.$form = $(self.options['form']);
			self.updateHashOnSubmit();
			self.searchOnHashChange();
		}
		
		// updates hash when form is submitted
		, updateHashOnSubmit : function() {
			var self = this;
			self.$form.submit(function() {
				location.hash = self.url+self.$elem.val();
				return false;
			});
		}
		
		// triggers searching when hash is changed
		, searchOnHashChange : function() {
			var self = this;
			self.searchWithHash();
			$(window).on('hashchange', function() {
				self.searchWithHash();
			});
		}
		
		// triggers function to search for documents
		, searchWithHash : function() {
			var self = this;
			self.$results.html(''); // clear the results
			self.skip = 0;
			if(location.hash.indexOf('#'+self.url) !== -1) {
				self.updateSearchBar();
				self.fetchAndPresentResults();
			} else { // if the location bar is not readable go to the home page
				self.$elem.val('');
				self.$results.html('');
			}
		}
		
		// updates search bar to show the same phrase as location bar
		, updateSearchBar : function() {
			var self = this;
			self.$elem.val(location.hash.replace('#'+self.url,''));
		}
		
		// fetches and presents results
		, fetchAndPresentResults : function() {
			var self = this;
			self.display(self.loadingBarHtml);
			
			self.fetch(self.skip)
				.done(function(results) {
					self.display(self.generateHtml(results));
				})
				.fail(function(reason) {
					self.displayErrorMessage(reason['responseText']);
				}).always(function() {
					$(self.loadingBarId).remove();
				});
		}
		
		// displays error message
		, displayErrorMessage : function(message) {
			var self = this;
			self.display('<div class="text-center"><span class="alert alert-error">'+message+'</span></div>');
		}
		
		// generates html for each document
		, generateHtml: function(results) {
			var self = this;
			var html = '';
			$.each(results, function(i, document){
				self.$results.append('<blockquote><dl>'
						+'<dt><i class="icon-file"></i>'+document['title']+' <span>'+document['filename']+'</span></dt>'
						+'<dd>'+(document['highlights'].join(' [...] '))+'</dd>'
						+'<small class="muted pull-right">'+document['path']+'</small>'
						+'</dl></blockquote>'
						+'<hr>');
			});
			self.appendMoreButtonIfNecessary(results);
		}
		
		// adds 'more' button when there might be more results
		, appendMoreButtonIfNecessary : function(results) {
			var self = this;
			self.skip += results.length;
			if(results.length >= 10) {
				self.$results.append(self.moreButtonHtml);
				$(self.moreButtonId).click(function() {
					$(self.moreButtonId).remove();
					self.fetchAndPresentResults();
				});
			}
		}
		
		// displays results
		, display : function(html) {
			var self = this;
			self.$results.append(html);
		}
		
		// fetches for documents
		, fetch : function(skip) {
			var self = this;
			return $.ajax({
				url : self.url+self.$elem.val()+"/"+skip
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
		form : 'form',
		more : 'more'
	};
})(jQuery);