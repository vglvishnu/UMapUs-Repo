var Navigator = function() {
	var self = this;

	self.navigate = function(event) {
		self.attachClickEvent('#familynewsnav');
		self.attachClickEvent('#profilenav');
		self.attachClickEvent('#familytreenav');
		self.attachClickEvent('#lookupnav');
		self.attachClickEvent('#invitenav');
		self.attachClickEvent('#helpnav');

	};

	self.attachClickEvent = function(id) {

		$(id).click(function(event) {
			$('#leftpane a').each(function(index) {
				if ($(this).hasClass("nav-selectedbg")) {
					$(this).removeClass("nav-selectedbg");
				}
			});
			$(id).addClass("nav-selectedbg");
			$('#workpane').children().each(function(index) {
				$(this).css('display', 'none');
			});
			var divid = id.substring(0, id.indexOf("nav"));
			$(divid).css('display', 'block');
		/*	if (id == "#familytreenav") {
				try
				{
				  nodeConnector.connectNodes("treenode1","treenode2","Wife");
				  nodeConnector.connectNodes("treenode1","treenode3","Son");
				  nodeConnector.connectNodes("treenode1","treenode4","Daughter");
				  nodeConnector.connectNodes("treenode3","treenode4","Sister");
				  nodeConnector.connectNodes("treenode3","treenode2","Mom");
			    }catch(e)
			  {
			  	alert(e);
			  }
			} */
		});
	};
};

var nav = new Navigator();
