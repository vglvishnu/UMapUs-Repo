function guid() {
	function _p8(s) {
		var p = (Math.random().toString(16) + "000000000").substr(2, 8);
		return s ? "-" + p.substr(0, 4) + "-" + p.substr(4, 4) : p;
	}

	return _p8() + _p8(true) + _p8(true) + _p8();
}

var FamilyTree = function() {

	var self = this;

	self.nodeTemplate = '<div  id="UUID" class="node" style="left:LEFTpx;top:TOPpx">' + 
	                    '<div  class="treenode"><p>NODENAME</p></div>' +
	                    '<div  class="RIP RIPTop RIPhide"></div><div  class="RIP RIPRight RIPhide"></div><div class="RIP RIPLeft RIPhide"></div>'+ 
	                    '<div  class="addtools toolmother" data-toggle="tooltip" data-placement="right" title="" data-original-title="Add Mom"><p>Mom</p></div>' + 
	                    '<div  class="addtools toolfather" data-toggle="tooltip" data-placement="right" title="" data-original-title="Add Dad"><p>Dad</p></div>' + 
	                    '<div  class="addtools toolchild" data-toggle="tooltip" data-placement="right" title="" data-original-title="Add Child"><p>Child</p></div>' + 
	                    '<div  class="addtools toolspouse" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="Add Spouse"><p>Spouse</p></div>' + 
	                    '<div  class="addtools toolsibling" data-toggle="tooltip" data-placement="left" title="" data-original-title="Add Sibling"><p>Sibling</p></div>' + 
	                    '<div  class="addtools toolother" data-toggle="tooltip" data-placement="left" title="" data-original-title="Add Others"><p>Others</p></div>' + 
	                    '<div  class="addtools tooldelete" data-toggle="tooltip" data-placement="top" title="" data-original-title="Delete Node"><label><i class="fa fa-times"></i></label><p>Delete</p></div>' + 
	                    '<div  class="addtools tooledit" data-toggle="tooltip" data-placement="top" title="" data-original-title="Edit Node"><label><i class="fa fa-edit"></i></label><p>Edit</p></div>'+
	                    '<div  class="addtools toolminimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize" ><label><i class="fa fa-minus"></i></label><p>Minimize</p></div>' + 
	                    '</div>';

	self.inithover = function(node) {

		$('#createFamily').on("click", function(e) {
			$('#treetip').css("display", "block");
			$('#theTree').css("display", "block");
			$('#startTree').css("display", "none");

			jsPlumb.draggable("treenode1");
		});

		//modaltesting.initModal();
        if(node == undefined || node == null)
		     divtreenode = $('.treenode');
		else
		     divtreenode = $(node);
		     
		divtreenode.hover(function(eventObject) {
			self.hideMenu();
			var id = '#' + $(this).parent().attr("id");
			$(id + ' .addtools').show();
			//$(id + '.RIP').hide();
			$(this).addClass('treenode-selected');
		}, function(eventObject) {
			/* var id = '#'+$(this).parent().attr("id");
			 if($(id).attr("data-nodeselected") == null || $(id).attr("data-nodeselected") == "false")
			 $(id +' .addtools').hide(); */
		});

		divtreenode.click(function(event) {
			self.hideMenu();
			$(this).addClass('treenode-selected');
			var id = '#' + $(this).parent().attr("id");
			$(id + ' .addtools').show();
			$(id).attr("data-nodeselected", "true");
			//$(id + '.RIP').hide();
			return false;
		});

		$('html').click(function(eventObject) {
			self.hideMenu();
		});

		self.hideMenu = function() {
			var curselectednode = $('.treenode').filter('.treenode-selected');
			curselectednode.removeClass('treenode-selected');
			curselectednode.siblings().filter('.addtools').hide();
			//curselectednode.siblings().filter('.RIP').show();
			curselectednode.parent().attr("data-nodeselected", "false");
		};
	};

	self.initTools = function() {
		$(".addtools").on("click", function(event) {
			var sourceid = $(this).parent().attr("id");
			var relation = $(this).children().filter('p').text().trim();
			self.addRelation(sourceid, relation, "SampleNode");
		});
	};

	self.initToolsNewNode = function(nodeid) {
		$(nodeid + " .addtools").on("click", function(event) {
			var sourceid = $(this).parent().attr("id");
			var relation = $(this).children().filter('p').text().trim();
			self.addRelation(sourceid, relation, "SampleNode");
		});
		$('.addtools').tooltip();
	};

	self.renderTree = function(treeObj) {
		$.each(treeObj.nodes, function(index, nodeObj) {
			var htmlcontent = self.nodeTemplate.replace("UUID", guid()).replace("NODENAME", nodeObj.nodename).replace("LEFT", nodeObj.position.left).replace("TOP", nodeObj.position.top);
			$("#theTree").append(htmlcontent);
		});
		self.inithover();
		self.initTools();
		$.each(treeObj.relations, function(index, relationObj) {
			nodeConnector.connectNodes(relationObj.sourceid, relationObj.targetid, relationObj.relationship);
		});
	};

	self.minimizeNode = function(nodeid) {
		var connectionlist = jsPlumb.getConnections({
			source : nodeid,
		});
		if(connectionlist.length == 0)
		  return;
		self.minimizeNodeinternal(nodeid);
		$('#' + nodeid).show();
		var tool = $('#' + nodeid +" .toolminimize i");
		if(tool.hasClass('fa-minus'))
		{
			tool.removeClass('fa-minus');
			tool.addClass('fa-plus');
		}
		$('#' + nodeid +' .treenode').css("background-image","url('./images/UserGroup.png')");
		$('#' + nodeid +" .toolminimize p").html("Expand");
		//background-image: url('../images/ProfileDefault.png');
		
	};

	self.minimizeNodeinternal = function(nodeid) {
		var connectionlist = jsPlumb.getConnections({
			source : nodeid,
		});

		for (var i = 0; i < connectionlist.length; i++) {
			
			
			self.minimizeNodeinternal(connectionlist[i].target.id);
			jsPlumb.hide(connectionlist[i].target.id);
			//jsPlumb.detach(connectionlist[i]);
		}
		
		$('#' + nodeid).hide();
	};
	
	
	self.expandNode = function(nodeid) {
		var connectionlist = jsPlumb.getConnections({
			source : nodeid,
		});
		if(connectionlist.length == 0)
		  return;
		  
		self.expandNodeinternal(nodeid);
		$('#' + nodeid).show();
		var tool = $('#' + nodeid +" .toolminimize i");
		if(tool.hasClass('fa-plus'))
		{
			tool.removeClass('fa-plus');
			tool.addClass('fa-minus');
		}
		$('#' + nodeid +' .treenode').css("background-image","url('./images/ProfileDefault.png')");
		$('#' + nodeid +" .toolminimize p").html("Minimize");
		//background-image: url('../images/ProfileDefault.png');
		
	};

	self.expandNodeinternal = function(nodeid) {
		var connectionlist = jsPlumb.getConnections({
			source : nodeid,
		});

		for (var i = 0; i < connectionlist.length; i++) {
			jsPlumb.show(connectionlist[i].target.id);
			$('#' + connectionlist[i].target.id).show();
			if($('#'+connectionlist[i].target.id+' .toolminimize i').hasClass("fa-plus"))
			{
			       var 	grpconnlist = jsPlumb.getConnections({
			                             source : connectionlist[i].target.id,
		                                 });
		           for(var j=0;j<grpconnlist.length;j++)
		           {
		           	 jsPlumb.hide(grpconnlist[j].target.id);
		           }                     
			  continue;
			}
			else
			    self.expandNodeinternal(connectionlist[i].target.id);
			}
			
		};


	

	self.deleteConnection = function(nodeid) {
		var connectionlist = jsPlumb.getConnections({
			source : nodeid,
		});

		for (var i = 0; i < connectionlist.length; i++) {
			self.deleteConnection(connectionlist[i].target.id);
		}
		var connectionlist = jsPlumb.getConnections({
			target : nodeid,
		});

		for (var i = 0; i < connectionlist.length; i++) {
			jsPlumb.detach(connectionlist[i]);
		}
		$('#' + nodeid).remove();
	};


    self.moveNodes = function(movetype)
    {
    	if(movetype == "Down")
    	{
    		$('.treenode').each(function(i,node)
    		{
    			var curtop = $(this).parent().css("top");
    			var newtop = parseInt(curtop.substr(0, curtop.indexOf("px")))+200;
    			$(this).parent().css("top",newtop+"px");
    		});
    		$('.connectornode').each(function(i,node)
    		{
    			var curtop = $(this).css("top");
    			var newtop = parseInt(curtop.substr(0, curtop.indexOf("px")))+200;
    			$(this).css("top",newtop+"px");
    		});
       	}
       	if(movetype == "Right")
    	{
    		$('.treenode').each(function(i,node)
    		{
    			var curleft = $(this).parent().css("left");
    			var newleft = parseInt(curleft.substr(0, curleft.indexOf("px")))+300;
    			$(this).parent().css("left",newleft+"px");
    		});
    		$('.connectornode').each(function(i,node)
    		{
    			var curleft = $(this).css("left");
    			var newleft = parseInt(curleft.substr(0, curleft.indexOf("px")))+300;
    			$(this).css("left",newleft+"px");
    		});
       	}
    	jsPlumb.repaintEverything();
    };

    self.getDesiredPostion = function(sourceid,srctop,srcleft,relation)
    {
    	var left = parseInt(srcleft.substr(0, srcleft.indexOf("px"))) + 100;
    	var top = parseInt(srctop.substr(0, srctop.indexOf("px"))) + 300;
    	
    	
    	
    	
    	if(relation == "Spouse")
		{
			 top = parseInt(srctop.substr(0, srctop.indexOf("px")));
			 left = parseInt(srcleft.substr(0, srcleft.indexOf("px"))) - 170;
		}
		if(relation == "Mom")
		{
			 top = parseInt(srctop.substr(0, srctop.indexOf("px"))) - 276;
			 left = parseInt(srcleft.substr(0, srcleft.indexOf("px"))) - 130;
		}
		if(relation == "Dad")
		{
			 top = parseInt(srctop.substr(0, srctop.indexOf("px"))) - 276;
			 left = parseInt(srcleft.substr(0, srcleft.indexOf("px"))) + 130;
		}
		if(relation == "Sibling")
		{
			top = parseInt(srctop.substr(0, srctop.indexOf("px")));
		    var count = self.getNumberofSiblings(sourceid);
		    if(count == 0)
		        leftdx = 400;
		    else
		        leftdx = 400 * (count+1);
			left = parseInt(srcleft.substr(0, srcleft.indexOf("px"))) + leftdx;
		}
		return {top:top,left:left};
    };
    
    self.getNumberofSiblings = function(sourcediv)
    {
    	
    	
    	var count = 0;
    	var connectionlist = jsPlumb.getConnections(
    		{source : sourcediv}
    	);
    	
    	for(var i=0;i<connectionlist.length;i++)
		{
		       var curconn = connectionlist[i];
		       var currel = curconn._jsPlumb.overlays[0].label;
		       if(currel == "parents")
		       {
		             count = self.getNumberofSiblings(curconn.targetId);
		       }
		       if(currel == "Brother" || currel == "Sister")
		             count++;
		}
    	return count;
    };
    
    
    self.getMinimumHeight = function()
    {
    	
    	$('.node').each(function(i,node){
    	        	
    	});
    };
    
    
	self.addRelation = function(sourceid, relation, nodename) {
		
		/* adjust nodes */
		{
        var srcleft = $('#' + sourceid).css("left");
		var srctop = $('#' + sourceid).css("top");
		var left = parseInt(srcleft.substr(0, srcleft.indexOf("px"))) + 100;
    	var top = parseInt(srctop.substr(0, srctop.indexOf("px"))) + 300;
        if(left - 300 < 100)
    	   self.moveNodes("Right");
    	if(top - 350 < 100)
    	   self.moveNodes("Down");
       
         }
         
         
		var targetid = guid();
		var srcleft = $('#' + sourceid).css("left");
		var srctop = $('#' + sourceid).css("top");
		
		
		
		
		
		var position = self.getDesiredPostion(sourceid,srctop,srcleft,relation);
		
		var left = position.left;
		var top = position.top;
		
		
		var modelcontent = {
			UUID : targetid,
			NODENAME : nodename,
			LEFT : left,
			TOP : top,
			RELATION : relation,
			SOURCEID : sourceid
		};

		if (relation == "Delete") {
			try {
				var modelcontent = {
					sourceid : sourceid
				};
				$('#DeleteConnectionModal').data('datacontent', modelcontent);
				$("#DeleteConnectionModal").modal('show');
				//self.deleteConnection(sourceid);
			} catch(e) {
				alert(e);
			}
			return;
		}

        if (relation == "Minimize") {
			try {
				var modelcontent = {
					sourceid : sourceid
				};
			    self.minimizeNode(sourceid);
			} catch(e) {
				alert(e);
			}
			return;
		}

        if (relation == "Expand") {
			try {
				var modelcontent = {
					sourceid : sourceid
				};
			    self.expandNode(sourceid);
			} catch(e) {
				alert(e);
			}
			return;
		}
		
		if(relation == "Edit")
		{
			//alert("SourceID :" + sourceid);
			var modelcontent =  $('#' + sourceid).data("datacontent");
			
			$('#EditRelation').data('datacontent', modelcontent);
			$("#EditRelation").modal('show');
			return;
		}
	
	    var usesource = true;
	    if(modelcontent.RELATION == "Sibling")
	        usesource = false;
	    var relObj = self.checkRelationExists(modelcontent.SOURCEID,modelcontent.RELATION,usesource);
	    if(relObj.result == true)
	    {
	    	var existingDiv = relObj.existingdiv;
	    	var animationtype = "bounce";
	    	$('#'+existingDiv).removeClass([animationtype,"animation"]).addClass(animationtype + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
                   $(this).removeClass(animationtype);
                   $(this).removeClass("animation");
            });
	    	
	    	return;
	    }
		$('#AddRelation').data('datacontent', modelcontent);
		$("#AddRelation").modal('show');
	

	};

    self.checkRelationExists = function(sourcediv,relation,usesource)
    {
    	if(usesource == true)
    	{
    	var connectionlist = jsPlumb.getConnections({
			source : sourcediv,
		});
		}
		else
		{
			var connectionlist = jsPlumb.getConnections({
			target : sourcediv,
		});
		}
		for(var i=0;i<connectionlist.length;i++)
		{
		       var curconn = connectionlist[i];
		       var currel = curconn._jsPlumb.overlays[0].label;
		       if(relation == "Mom" || relation == "Dad")
		       {
		       			if(currel == "parents" )
		  	       	   			return self.checkRelationExists(curconn.targetId,relation,true);
		  	   }
		       if(currel == relation)
		          return {result:true,existingdiv:curconn.targetId};       
		}
		return {result:false};
		
    };
	
	
	
};

var ModalSettings = function() {
	self = this;
	
	var id = '#AddRelation';
	var editid = '#EditRelation';
	
	self.cleanupModal = function()
	{
		    var value = null;
		    $('#editmodalname').val(value);
			$('#editemailinmodal').val(value);
			$('#editgenderselect').val(value);
			$('#editmodalage').val(value);
			$('#editisPersonExpired').val(value);
			$('#addmodalname').val(value);
			$('#addemailinmodal').val(value);
			$('#addgenderselect').val(value);
			$('#addmodalage').val(value);
			$("#addisPersonExpired").attr('checked', false); 
		
	};
	
	
	self.initEditModal = function()
	{
		 $('#EditRelation').modal({
			backdrop : 'static',
			keyboard : false,
			show : false
			
		});
		
		$('#EditRelation').on('show.bs.modal', function(e) {
			self.cleanupModal();
			var modalcontent = $('#EditRelation').data('datacontent');
			$('#editmodalname').val(modalcontent.NODENAME);
			$('#editemailinmodal').val(modalcontent.EMAIL);
			$('#editgenderselect').val(modalcontent.GENDER);		
			$('#editmodalage').val(modalcontent.AGE);
			$("#editisPersonExpired").attr('checked', modalcontent.isEXPIRED); 
			
		});
		self.onEditRelationConfirm();
		
		
	};


    self.onEditRelationConfirm = function()
    {
    	$('#EditRelationConfirm').on("click", function(e) {
			var modalcontent = $('#EditRelation').data('datacontent');
			modalcontent.NODENAME=$('#editmodalname').val();
            modalcontent.EMAIL=$('#editemailinmodal').val();
            modalcontent.GENDER=$('#editgenderselect').val();
            modalcontent.AGE=$('#editmodalage').val();
            modalcontent.isEXPIRED=$('#editisPersonExpired').is(":checked");
            self.updateExpiredStatus(modalcontent);
            $('#'+modalcontent.UUID).data('datacontent', modalcontent);
            $('#'+modalcontent.UUID+' .treenode p').html(modalcontent.NODENAME); 
            $(editid).modal('hide');
		});
    	
    };
    
    self.onAddRelationConfirm  =  function()
    {
    	$('#AddRelationConfirm').on("click", function(e) {
			var modalcontent = $(id).data('datacontent');
			if (self.checkName()) {
				$(id).modal('hide');
				modalcontent.NODENAME = $('#addmodalname').val();
				var htmlcontent = familyTree.nodeTemplate.replace("UUID", modalcontent.UUID).replace("NODENAME", modalcontent.NODENAME).replace("LEFT", modalcontent.LEFT).replace("TOP", modalcontent.TOP);
				if (modalcontent.RELATION == "Child") {
					if ($("#addgenderselect").val() == "Male")
						modalcontent.RELATION = "Son";
					else
						modalcontent.RELATION = "Daughter";
				}
				if (modalcontent.RELATION == "Sibling") {
					if ($("#addgenderselect").val() == "Male")
						modalcontent.RELATION = "Brother";
					else
						modalcontent.RELATION = "Sister";
				}
				$("#theTree").append(htmlcontent);
				modalcontent.NODENAME = $('#addmodalname').val();
				modalcontent.EMAIL = $('#addemailinmodal').val();
				modalcontent.AGE = $('#addmodalage').val();
				modalcontent.GENDER = $('#addgenderselect').val();
				modalcontent.isEXPIRED = $("#addisPersonExpired").is(":checked");
				self.updateExpiredStatus(modalcontent);
				$('#'+modalcontent.UUID).data('datacontent', modalcontent);
				familyTree.inithover();
				familyTree.initToolsNewNode("#" + modalcontent.UUID);
				nodeConnector.connectNodes(modalcontent.SOURCEID, modalcontent.UUID, modalcontent.RELATION);
			} else {

			}

		});
    };

	self.initModal = function() {

		
		
		$(id).modal({
			backdrop : 'static',
			keyboard : false,
			show : false
			
		});

		$('#DeleteConnectionModal').modal({
			backdrop : 'static',
			keyboard : false,
			show : false
		});

      self.initEditModal();
      
	  self.onAddRelationConfirm();	
		
		
	 

		$(id).on('show.bs.modal', function(e) {
			self.cleanupModal();
			var modalcontent = $(id).data('datacontent');
			$('#modalname').val(null);
			$(id + ' h4.modal-title').html("Add Relation: [[ " + modalcontent.RELATION + " ]]");
			if (modalcontent.RELATION == "Mom" || modalcontent.RELATION == "Spouse" || modalcontent.RELATION == "child") {
				$(id + ' #addgenderselect').val("Female");
			} else
				$(id + ' #addgenderselect').val("Male");
			//		
		});

		$(id).on('hide.bs.modal', function(e) {
			$('#spanmodalname').remove();
		});

		

		$('#DeleteConnectionYes').on("click", function(e) {
			var modelcontent = $('#DeleteConnectionModal').data('datacontent');
			var sourceid = modelcontent.sourceid;
			familyTree.deleteConnection(sourceid);
			$('#DeleteConnectionModal').modal('hide');
		});
		
		
	};

	self.checkName = function() {
		var domelement = $('#addmodalname');
		var name = domelement.val();
		if (name == null || name.trim().length == 0) {
			var strmessage = "Must provide Name";
			if ($('#span' + domelement.attr("id")).length == 0)
				domelement.parent().append('<span id="span' + domelement.attr("id") + '" class="error_msg">' + strmessage + '</span>');
			return false;
		} else {
			$('#span' + domelement.attr("id")).remove();
			return true;
		}
	};


    self.updateExpiredStatus = function(modalcontent)
    {
    	if(modalcontent.isEXPIRED == true)
				{
					$('#'+modalcontent.UUID+' .RIP').removeClass("RIPhide");
					$('#'+modalcontent.UUID+' .RIP').addClass("RIPshow");
				}
				else
				{
					if($('#'+modalcontent.UUID+' .RIP').hasClass("RIPshow"))
					{
						$('#'+modalcontent.UUID+' .RIP').removeClass("RIPshow");
						$('#'+modalcontent.UUID+' .RIP').addClass("RIPhide");
					}
					     
				}
				
    };

   
    
};

var modaltesting = new ModalSettings();
var familyTree = new FamilyTree();
modaltesting.initModal();

var testtree = {
	sessionid : "JSESSIONID2324234A23423423",
	isCompletedRefresh : false,
	nodes : [{
		divid : "abcd-1234",
		nodename : "Arunkumar Hariharan",
		position : {
			left : 200,
			top : 200
		}
	}, {
		divid : "abcd-4321",
		nodename : "Deepika Arunkumar",
		position : {
			left : 400,
			top : 200
		}
	}],

	relations : [{
		sourceid : "abcd-1234",
		targetid : "abcd-4321",
		relationship : "Wife"
	}]
};

// jsPlumb related stuff

var NodeConnector = function() {
	var self = this;

	self.common = {
		connector : ["Bezier", {
			curviness : 70
		}],
		cssClass : "c1",
		endpoint : "Blank",
		endpointClass : "c1Endpoint",
		anchors : ["BottomCenter", "TopCenter",[0.75, 0, 0, -1]],
		paintStyle : {
			lineWidth : 1,
			strokeStyle : "#8FDE56",
			outlineWidth : 1,
			outlineColor : "#666"
		},
		endpointStyle : {
			fillStyle : "#8FDE56"
		},
		/*	hoverPaintStyle : hoverPaintStyle, */
		overlays : [["Label", {
			cssClass : "l1 component label",
			label : "????",
			location : 0.4,
			id : "label",
			events : {
				"click" : function(label, evt) {
					//alert("clicked on label for connection " + label.component.id);
				}
			}
		}], ["Arrow", {
			cssClass : "l1arrow",
			location : 0.5,
			width : 20,
			length : 20,
			events : {
				"click" : function(arrow, evt) {
					//alert("clicked on arrow for connection " + arrow.component.id);
				}
			}
		}]]
	};

	self.connectNodes = function(sourcediv, targetdiv, labelname) {
		var htmlcontent = '<div id="'+sourcediv + "-GRPRELATION" +'"class="connectornode" style="left:LEFTpx;top:TOPpx">'+
		                  '<div  class="addtools tooldelete" data-toggle="tooltip" data-placement="top" title="" data-original-title="Delete Node"><label><i class="fa fa-times"></i></label><p>Delete</p></div>' + 
	                      '<div  class="addtools tooledit" data-toggle="tooltip" data-placement="top" title="" data-original-title="Edit Node"><label><i class="fa fa-edit"></i></label><p>Edit</p></div>'+
	                      '<div  class="addtools toolminimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize" ><label><i class="fa fa-minus"></i></label><p>Minimize</p></div>' +
	                      '</div>' ;
		
		self.common.anchors = ["BottomCenter", "TopCenter",[0.75, 0, 0, -1]];
		if(labelname == "Spouse")
		{
			self.common.anchors = ["LeftMiddle", "RightMiddle",[0.75, 0, 0, -1]];
			self.connectDivNodes(sourcediv,targetdiv,labelname,self.common);
		}
		
		else if(labelname == "Mom" || labelname == "Dad" || labelname == "Brother" || labelname == "Sister")
		{	
			
			self.common.anchors = ["TopCenter", "BottomCenter",[0.75, 0, 0, -1]];
			htmlcontent = htmlcontent.replace("GRPRELATION","PARENTS");
			var srcleft = $('#' + sourcediv).css("left");
		    var srctop = $('#' + sourcediv).css("top");
		    var left = parseInt(srcleft.substr(0, srcleft.indexOf("px")))+ 44;
    	    var top = parseInt(srctop.substr(0, srctop.indexOf("px"))) - 87;
		    if(top-60 <60)
		    {
		    	familyTree.moveNodes("Down");
		    	srcleft = $('#' + sourcediv).css("left");
		        srctop = $('#' + sourcediv).css("top");
		        left = parseInt(srcleft.substr(0, srcleft.indexOf("px")))+ 44;
    	        top = parseInt(srctop.substr(0, srctop.indexOf("px"))) - 87;
		    }
		    if(left-60 < 100)
		    {
		    	familyTree.moveNodes("Right");
		    	srcleft = $('#' + sourcediv).css("left");
		        srctop = $('#' + sourcediv).css("top");
		        left = parseInt(srcleft.substr(0, srcleft.indexOf("px")))+ 44;
    	        top = parseInt(srctop.substr(0, srctop.indexOf("px"))) - 87;
		    }
			if($('#'+sourcediv + "-PARENTS").length == 0)
			{		
				   htmlcontent=htmlcontent.replace("LEFT",left);
				   htmlcontent=htmlcontent.replace("TOP",top);
			       $("#theTree").append(htmlcontent);	
			       
			       self.common.connector[0] = "Straight";
			       self.connectDivNodes(sourcediv,sourcediv + "-PARENTS","parents",self.common);
			      // familyTree.inithover('.connectornode');
				  // familyTree.initToolsNewNode("#" +sourcediv + "-PARENTS");
			       
			}
			self.common.connector[0] = "Bezier";  
	        
	        if (labelname == "Brother" || labelname == "Sister")
	           self.common.anchors = ["BottomCenter", "TopCenter",[0.75, 0, 0, -1]];
			self.connectDivNodes(sourcediv + "-PARENTS",targetdiv,labelname,self.common);
		}
		else
		{
			self.connectDivNodes(sourcediv,targetdiv,labelname,self.common);
		}

	};
	
	
	self.connectDivNodes = function(sourcediv, targetdiv, labelname,common) {
		jsPlumb.draggable(sourcediv);
		jsPlumb.draggable(targetdiv);
		common.overlays[0][1].label = labelname;
		jsPlumb.connect({
			source : sourcediv,
			target : targetdiv
		},common);
  };
};

var nodeConnector = null;

jsPlumb.ready(function() {
	nodeConnector = new NodeConnector();
});

