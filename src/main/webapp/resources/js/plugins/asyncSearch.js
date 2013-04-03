// -- asyncSearch.js
// requires jQuery, intelligentPopover
(function($) {
	var Search = {
		init : function(options, elem) {
			var self = this;
			self.elem = elem;
			self.$elem = $(elem);
			self.options = $.extend({}, $.fn.asyncSearch.options , options);
			self.i18n = self.options['i18n'];
			self.$results = $(self.options['results']);
			self.url = self.options['url'];
			self.$form = $(self.options['form']);
			self.loadingBarHtml = '<div id="asynchSearch-loading" class="progress progress-striped active text-center"><div class="bar" style="width: 100%;"></div></div>';
			self.loadingBarId = '#asynchSearch-loading';
			self.moreButtonHtml = '<ul id="asynchSearch-more" class="pager"><li><a style="width:100%">'+self.i18n['more']+'</a></li></ul>';
			self.moreButtonId = '#asynchSearch-more';
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
			
			// check if there's anything to search
			if(self.$elem.val().length <= 0) 
				return;
			
			self.display(self.loadingBarHtml);
			self.fetch(self.skip)
				.done(function(results) {
					self.display(self.generateHtml(results));
				})
				.fail(function(error) {
					self.handleError(error);
				})
				.always(function() {
					$(self.loadingBarId).remove();
				});
		}
		
		// handles errors
		, handleError : function(error) {
			var self = this;
			switch(error.status) {
			case 404 :
				self.displayErrorMessage(self.i18n['noResults']);
				break;
			default :
				self.displayErrorMessage(self.i18n['unknownError ']);
			}
		}
		
		// displays error message
		, displayErrorMessage : function(message) {
			var self = this;
			self.display('<div class="alert alert-error text-center">'+message
					+'<a class="close" data-dismiss="alert" href="#">&times;</a>'
					+'</div>');
		}
		
		// generates html for each document
		, generateHtml: function(results) {
			var self = this;
			var html = '';
			$.each(results, function(i, document){
				var documentId = 'asynchSearch'+self.skip+'v'+i;
				self.$results
					.append('<blockquote id="'+documentId+'"><dl>'
						+'<dt>'+document['filename']+' <span class="document-title"> - '+document['title']+'</span></dt>'
						+'<dd>'+(document['highlights'].join(' [...] '))+'</dd>'
						+'<small class="muted pull-right hidden-phone">'+document['path']+'</small>'
						+'</dl></blockquote>'
						+'<hr>');
				$('#'+documentId).intelligentPopover({
					content : '' 
						+self.printIfNotNull(self.i18n['author'], document['author']) 
						+self.printIfNotNull(self.i18n['size'], (Math.round(document['size']/1024)+' kB'))
						+self.printIfNotNull(self.i18n['modified'], document['modified'])
				});
			});
			self.appendMoreButtonIfNecessary(results);
		}
		
		, printIfNotNull: function(label, nullable) {
			if((nullable).length > 0)
				return '<b>'+label+':</b> '+nullable+'<br>';
			return '';
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

	$.fn.asyncSearch = function(options) {

		return this.each(function() {
			var search = Object.create(Search);
			search.init(options, this);
		})
	}
	
	$.fn.asyncSearch.options = {
		results : '.results',
		url : '/search/',
		form : 'form',
		i18n : {
			more : 'more',
			// messages used in popover
			author : 'Author',
			modified : 'Modified',
			size : 'Size',
			// errors
			noResults: 'Sorry, there are no results',
			unknownError: 'Unknown error occurred.',
		}
	};
})(jQuery);