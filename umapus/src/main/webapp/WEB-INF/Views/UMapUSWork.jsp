<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html class="no-js">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>UMapUS</title>
		<meta name="description" content="">
		<!--	<meta name="viewport" content="width=device-width, initial-scale=1"> -->

		<!-- Place favicon.ico and apple-touch-icon(s) in the root directory -->

		<link rel="stylesheet" href="css/normalize.css">
		<link rel="stylesheet" href="css/main.css">
		<link rel="stylesheet" href="css/UMapUS.css">
		<link rel="stylesheet" href="css/bootstrap-theme.css">
		<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" href="css/font-awesome.min.css">
		<link rel="stylesheet" href="css/datepicker.css">
		<link rel="stylesheet" href="css/animate.css">
		<script src="js/vendor/modernizr-2.7.1.min.js"></script>
		<script src="js/Validators.js" type="text/javascript"></script>
		<script src="js/Messages.js" type="text/javascript"></script>
	</head>
	<body style="padding-top:55px">
		<div id="maincontainer" class="container-fluid" >
			<div id="header" class="navbar navbar-inverted navbar-fixed-top header mainpagesection container-fluid">

				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a id="brand" href="#" class="navbar-brand" style="margin-top:-13px"> <img id="brandimg" alt="Logo" src="./img/Logo.jpg" width="120px" height="48px" class="img-rounded"/> </a>
					<div class="collapse navbar-collapse" style="float:left;" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="active">
								<a href="#">Home</a>
							</li>
							<li>
								<a href="#">My Family Tree</a>
							</li>
							<li>
								<a href="#">About</a>
							</li>
							<li>
								<a href="#">Contact Us</a>
							</li>
						</ul>

					</div>

					<!-- navbar-collapse -->

				</div>
				<div id="login" class="navbar-right">
					<div class="btn-group" style="padding-top:8px">
						<button type="button" class="btn btn-success">
                              ${message}
						</button>

						<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
							<span class="caret"></span>
							<span class="sr-only">Toggle Dropdown</span>
						</button>

						<ul class="dropdown-menu" role="menu">

							<li>
								<a href="#"><i class="fa fa-camera-retro"></i> Invitations</a>
							</li>
							<li>
								<a href="#">Messages</a>
							</li>

							<li class="divider"></li>
							<li>
								<a href="#">Logout</a>
							</li>
						</ul>

					</div>

					<div id="loginerrpanel" class="error_msg">

					</div>
				</div>

			</div>

			<div id="mainnav" class="row">

				<div id="leftpane" class="col-md-1 leftpane">
					<div class="row">
						<div  class="icongroup col-sm-12" style="margin-top:5px;">
							<a href="#" id="familynewsnav" class="thumbnail"> <img src="./images/FamilyUpdates.png" width="48px" height="48px" alt="...">
							<p style="text-align:center;padding-top: 3px;">

								Feed
							</p> </a>
						</div>
					</div>

					<div class="row">
						<div  class="icongroup col-sm-12">
							<a href="#" id="profilenav" class="thumbnail"> <img src="./images/Profile.png" width="48px" height="48px" alt="...">
							<p style="text-align:center;padding-top: 3px;">
								My Profile
							</p> </a>
						</div>
					</div>
					<div class="row">
						<div  class="icongroup  col-sm-12">
							<a href="#" id="familytreenav"  class="thumbnail"> <img src="./images/FamilyTree.png" width="48px" height="48px" alt="...">
							<p style="text-align:center;padding-top: 3px;">
								Family tree
							</p> </a>
						</div>
					</div>
					<div class="row">
						<div  class="icongroup  col-sm-12">
							<a href="#" id="lookupnav" class="thumbnail"> <img src="./images/Discover.png" width="48px" height="48px" alt="...">
							<p style="text-align:center;padding-top: 3px;">
								Discover
							</p> </a>
						</div>
					</div>
					<div class="row">
						<div  class="icongroup  col-sm-12">
							<a href="#" id="invitenav" class="thumbnail"> <img src="./images/Invite.png" width="48px" height="48px" alt="...">
							<p style="text-align:center;padding-top: 3px;">
								Invite
							</p> </a>
						</div>
					</div>
					<div class="row">
						<div  class="icongroup col-sm-12">
							<a href="#" id="helpnav" class="thumbnail"> <img src="./images/Collaborate.png" width="48px" height="48px" alt="...">
							<p style="text-align:center;padding-top: 3px;">
								Collaborate
							</p> </a>
						</div>
					</div>
				</div>
				<!-- end of leftpane -->

				<div id="workpane" class="col-md-11 workpane-after" style="margin-left:-20px;margin-top:5px">
					<div id="familynews" style="display:none;" class="panel panel-primary">

						<div id="familynewshead" class="panel-heading">
							<h5 class="panel-title">Family feeds</h5>
						</div>
						<div class="panel-body">
							<div id="myfamilyupdate" class="row">
								<div class="col-md-2">

									<a href="#" id="helpnav"> <img src="./images/ProfileDefault.png" style="margin-left:40px;" width="96px" height="96px" alt="...">
									<p style="text-align:center;padding-top:3px;">
										Arunkumar Nattamai Hariharan
									</p> </a>

								</div>
								<div id="sharecontent" class="col-md-10 row">
									<div class="popover right">
										<div class="arrow"></div>

										<div class="popover-content" style="padding-bottom: 20px">
											<div class="col-md-7" style="padding-bottom:7px;">
												<textarea  class="form-control"  style="resize:none;" rows="4" placeholder="Share your family news / Updates"></textarea>
											</div>
											<div class="col-md-5 row">
												<div class="col-md-12 row">
													<button class="col-md-offset-4 col-md-6 btn btn-primary">
														Share message
													</button>
												</div>
												<div class="col-md-offset-1 col-md-11" style="margin-top: 15px;padding-left: 10px">
													<span>
														<input type="checkbox" class="chkboxinput">
														My family </span>
													<span>
														<input type="checkbox" class="chkboxinput">
														Extended family</span>
													<span>
														<input type="checkbox" class="chkboxinput">
														Everyone</span>

												</div>
											</div>
										</div>
									</div>
								</div>

								<hr/>
								<div id="sharedmessages" class="row">
									<div id="sharedmessage1" class="col-md-12" style="border:2px;margin-top:10px">
										<div class="col-md-2">
											<a href="#" id="helpnav"> <img src="./images/ProfileDefault.png" style="margin-left:65px;" width="32px" height="32px" alt="...">
											<p style="text-align:center;padding-top:3px;">
												Nikitha Nattamai Arunkumar
											</p> </a>
										</div>
										<div class="popover right">
											<div class="arrow"></div>
											<div class="popover-content" style="padding-bottom: 20px">
												<div class="col-md-10">
													<p style="word-wrap: break-word;">
														Shared messages 1 .....
														Shared messages 1 .....
														Shared messages 1 .....
														Shared messages 1 .....
														Shared messages 1 .....

													</p>
												</div>
												<div class="col-md-offset-4 col-md-8 row">
													<a href="#" class="col-md-2"> <span> <i class="fa fa-thumbs-o-up"></i> Thumbs up</span> </a>
													<a href="#" class="col-md-2"> <span> <i class="fa fa-heart-o"></i> Love</span> </a>
													<a href="#" class="col-md-3"> <span> <i class="fa fa-comment-o"></i> Comment</span> </a>
													<a href="#" class="col-md-2"> <span> <i class="fa fa-group"></i> Share</span> </a>
												</div>
											</div>
										</div>
									</div>

									<hr/>
									<div id="sharedmessage2" class="col-md-12" style="border:2px;margin-top:10px">
										<div class="col-md-2">
											<a href="#" id="helpnav"> <img src="./images/ProfileDefault.png" style="margin-left:65px;" width="32px" height="32px" alt="...">
											<p style="text-align:center;padding-top:3px;">
												Anirudh Nattamai Arunkumar
											</p> </a>
										</div>
										<div class="popover right">
											<div class="arrow"></div>
											<div class="popover-content" style="padding-bottom: 20px">
												<div class="col-md-10">
													<p style="word-wrap: break-word;">
														Shared messages 1 .....
														Shared messages 1 .....
														Shared messages 1 .....
														Shared messages 1 .....
														Shared messages 1 .....

													</p>
												</div>
												<div class="col-md-offset-4 col-md-8 row">
													<a href="#" class="col-md-2"> <span> <i class="fa fa-thumbs-o-up"></i> Like</span> </a>
													<a href="#" class="col-md-2"> <span> <i class="fa fa-heart-o"></i> Love</span> </a>
													<a href="#" class="col-md-3"> <span> <i class="fa fa-comment-o"></i> Comment</span> </a>
													<a href="#" class="col-md-2"> <span> <i class="fa fa-group"></i> Share</span> </a>
												</div>
											</div>
										</div>
									</div>
								</div><!-- SharedMessages End -->
							</div>

						</div>
					</div><!-- end of family news -->
					<div id="profile" style="display:none;" class="panel panel-primary">
						<div id="profilehead" class="panel-heading">
							<h5 class="panel-title"> Profile data</h5>
						</div>
						<div class="panel-body container">
							<div id="profilecontent" class="row">
								<div id="profilepic" class="col-md-2">
									<img src="./images/ProfileDefault.png" width="200px" height="200px" alt="profile default"/>
									<div id="uploadpic" style="margin-left:42px">
										<div id="uploader" class="btn btn-primary fileUpload">
											<span> Update Pic</span>
											<input type="file" class="upload"/>
										</div>
									</div>
								</div>
								<!-- profile pic -->
								<div id="profiledata" class="form-horizontal col-md-9">
									<div class="row">
										<div class="form-group">
											<label for="firstname" class="col-sm-2 control-label">Family Name</label>
											<div class="col-offset-1 col-sm-9">
												<input type="text" class="form-control" disabled="disabled" id="familyname" placeholder="Family Name">

											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-sm-6">
											<label for="firstname" class="col-sm-4 control-label">First Name</label>
											<div class="col-offset-1 col-sm-8">
												<input type="text" class="form-control" id="firstname" placeholder="First Name">

											</div>
										</div>
										<div class="form-group col-sm-6">
											<label for="lastname" class="col-sm-3 control-label">Last Name</label>
											<div class="col-offset-1 col-sm-9">
												<input type="text" class="form-control" id="lastname" placeholder="Last Name">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-sm-6">
											<label for="emailsignup" class="col-sm-4 control-label"> Email </label>
											<div class="col-offset-1 col-sm-8">
												<input type="text" class="form-control" disabled="disabled" id="emailsignup" placeholder="Email">
											</div>
										</div>
										<div class="form-group col-sm-6">
											<label for="passwdsignup" class="col-sm-3 control-label">Password</label>
											<div class="col-offset-1 col-sm-9">
												<input type="password" class="form-control" id="passwdsignup" placeholder="Password">
											</div>
										</div>
									</div>

									<div class="row">
										<div class="form-group col-sm-6">
											<label for="Gender" class="col-sm-4 control-label">Gender</label>
											<div data-toggle="buttons" class="col-offset-1 col-sm-8" style="padding-top:6px;">
												<label >
													<input class="radioinput" type="radio"  name="gender" id="sexm" value="Male" checked>
													Male </label>
												<label >
													<input class="radioinput" type="radio"  name="gender" id="sexf" value="Female">
													Female </label>

											</div>
										</div>
										<div class="form-group col-sm-6">
											<label for="passwdsignup" class="col-sm-3 control-label">Birth Date </label>
											<div class="col-offset-1 col-sm-5">
												<div class="input-group">
													<input id="birthdate" type="text" class="form-control" readonly="">
													<span class="input-group-btn">
														<button id="birthdatepicker" class="btn btn-warning" data-date="10/10/2010" data-date-format="mm/dd/yyyy" data-date-viewMode="years" type="button">
															<span class="glyphicon glyphicon-calendar"></span>
														</button>
												</div>
											</div>
											<div class="col-offset-1 col-sm-1">
												<button class="btn btn-link" style="margin-left:-26px">
													why we need this ?
												</button>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="form-group col-sm-12">
											<label for="geolocation" class="col-sm-2 control-label">Geo Location</label>
											<div class="col-offset-1 col-sm-10">
												<div class="row">
													<div class="col-sm-4">
														<input type="text" class="form-control"  id="City" placeholder="City">

													</div>

													<div class="col-sm-3">
														<input type="text" class="form-control"  id="State" placeholder="State">
													</div>

													<div class="col-sm-5">
														<input type="text" class="form-control"  id="Country" placeholder="Country" style="width: 233px;">
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-offset-7 col-sm-5">
											<div class="row">
												<button class="btn btn-primary col-sm-offset-3 col-sm-3">
													Save
												</button>
												<button class="btn btn-danger  col-sm-3" style="margin-left:12px">
													Cancel
												</button>
											</div>
										</div>
									</div>
								</div>
								<!-- profile data -->
							</div>
							<!-- end of profile content -->
						</div>
					</div>
					<!-- end of profile -->

					<div id="familytree" style="display:none;">
						<div id="familytreediv" style="margin:auto;text-align: center;">
							<div id="startTree">
								<h2> Feeling exicted to create your family tree Lets get started.</h2>
								<span><h4> Family Name :  Nattamai </h4></span>
								<div class="row">
									<div class="col-md-offset-4 col-md-4">
										<button id="createFamily" type="button" class="btn btn-info btn-lg btn-block">
											Create my Family tree
										</button>
									</div>
								</div>
							</div>
							<div id="treetip" class="alert alert-info fade in" style="display:none;text-align: left">
								<button class="close" aria-hidden="true" data-dismiss="alert" type="button">
									×
								</button>
								<h4> Tips : -</h4>

								<ul >
									<li>
										<p>
											Hover over the circle to add connections
										</p>
									</li>
									<li>
										<p>
											Drag the circle to move the postion
										</p>
									</li>
								</ul>
								<button class="btn btn-primary" aria-hidden="true" data-dismiss="alert" type="button">
									Ok Got it !!!
								</button>

							</div>
							<div id="theTree" style="display: none">
								<div id="treenode1" class="node" style="left:600px;top:150px">
									<div id="mainnode" class="treenode">
										<!-- <img src="./images/ProfileDefault.png" width="50px" height="50px" class="image-rounded"> -->
										<p id="primarynodename">
											Arunkumar Hariharan
										</p>
									</div>

									<div id="relation1" class="addtools toolmother" data-toggle="tooltip" data-placement="right" title="" data-original-title="Add Mom">
										<p>
											Mom
										</p>
									</div>
									<div id="relation2" class="addtools toolfather" data-toggle="tooltip" data-placement="right" title="" data-original-title="Add Dad">
										<p>
											Dad
										</p>

									</div>
									<div id="relation3" class="addtools toolchild" data-toggle="tooltip" data-placement="right" title="" data-original-title="Add Child">
										<p>
											Child
										</p>

									</div>
									<div id="relation4" class="addtools toolspouse" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="Add Spouse">
										<p>
											Spouse
										</p>
									</div>
									<div id="relation5" class="addtools toolsibling" data-toggle="tooltip" data-placement="left" title="" data-original-title="Add Sibling">
										<p>
											Sibling
										</p>
									</div>
									<div id="relation6" class="addtools toolother" data-toggle="tooltip" data-placement="left" title="" data-original-title="Add Others">
										<p>
											Others
										</p>
									</div>
									<!--	<div id="relation7" class="addtools tooldelete" >
									<label><i class="fa fa-times"></i></label>
									<p>
									Delete
									</p>
									</div>
									-->

								</div>

							</div>
						</div>
					</div>
					<!-- end of family tree -->
					<div id="lookup" style="display:none;">
						<span> test this lookup</span>
					</div>
					<!-- end of lookup -->
					<div id="invite" style="display:none;">
						<span> test this invite</span>
					</div>
					<!-- end of invite -->
					<div id="help" style="display:none;">
						<span> test this Help</span>
					</div>
					<!-- end of collaborate -->
				</div>
				<!-- end of workpane -->
			</div>
			<!-- main navigation -->
		</div>

		<!-- Modals for family tree -->
		<!-- Add Relation Model start -->
		<div class="modal fade" id="AddRelation" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">Add Relation</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
							<div class="form-group">
								<label for="modalname" class="col-sm-2 control-label"><span style="color:red">* </span>Name</label>
								<div class="col-offset-1 col-sm-9">
									<input type="text" class="form-control" id="addmodalname" placeholder="Name">

								</div>
							</div>
							<div class="form-group">
								<label for="emailinmodal" class="col-sm-2 control-label"> Email </label>
								<div class="col-offset-1 col-sm-9">
									<input type="text" class="form-control" id="addemailinmodal" placeholder="Email">
								</div>
							</div>
							<div class="form-group">
								<label for="genderinmodal" class="col-sm-2 control-label"><span style="color:red">* </span> Gender </label>
								<div class="col-offset-1 col-sm-9">
									<select id="addgenderselect" class="form-control">
										<option>Male</option>
										<option>Female</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="modalage" class="col-sm-2 control-label"><span style="color:red">* </span>Age</label>
								<div class="col-offset-1 col-sm-9">
									<input type="number" class="form-control" id="addmodalage" placeholder="Age in Years">
								</div>
							</div>
							<div class="checkbox" >
								<label>
									<input type="checkbox" id="addisPersonExpired">
									is Person Expired ? </label>
							</div>
							<div class="panel-group" id="accordion">
								<div >
									<div class="panel-heading">
										<h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#addmoreinfo" class=""> Add more information &gt; </a></h4>
									</div>
									<div id="addmoreinfo" class="panel-collapse collapse out" style="height: auto;">
										<div id="addmoreinformationcontent" class="panel-body">

										</div>

									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							Close
						</button>
						<button id="AddRelationConfirm" type="button" class="btn btn-primary">
							Add
						</button>
					</div>

				</div>
			</div>
		</div>

		<!-- Add Relation Modal end -->

		<!-- Edit Node Modal -->
		<div class="modal fade" id="EditRelation" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">Edit Relation</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
							<div class="form-group">
								<label for="modalname" class="col-sm-2 control-label"><span style="color:red">* </span>Name</label>
								<div class="col-offset-1 col-sm-9">
									<input type="text" class="form-control" id="editmodalname" placeholder="Name">

								</div>
							</div>
							<div class="form-group">
								<label for="emailinmodal" class="col-sm-2 control-label"> Email </label>
								<div class="col-offset-1 col-sm-9">
									<input type="text" class="form-control" id="editemailinmodal" placeholder="Email">
								</div>
							</div>
							<div class="form-group">
								<label for="genderinmodal" class="col-sm-2 control-label"><span style="color:red">* </span> Gender </label>
								<div class="col-offset-1 col-sm-9">
									<select id="editgenderselect" class="form-control">
										<option>Male</option>
										<option>Female</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label for="modalage" class="col-sm-2 control-label"><span style="color:red">* </span>Age</label>
								<div class="col-offset-1 col-sm-9">
									<input type="number" class="form-control" id="editmodalage" placeholder="Age in Years">

								</div>
							</div>

							<div class="checkbox" >
								<label>
									<input type="checkbox" id="editisPersonExpired">
									is Person Expired ? </label>
							</div>

							<div class="moreinfo">
								<a href="#"> More Information >> </a>

							</div>

						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							Cancel
						</button>
						<button id="EditRelationConfirm" type="button" class="btn btn-primary">
							Save Changes
						</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Modals for delete connection -->
		<div class="modal fade" id="DeleteConnectionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">Delete Confirmation</h4>
					</div>
					<div class="modal-body">
						<h4> This will delete the Family member and all connection associated. Are you sure you wan't to do this ? </h4>
					</div>
					<div class="modal-footer">
						<button id="DeleteConnectionYes" type="button" class="btn btn-danger">
							Yes
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">
							No
						</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Main div container -->
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script>
			window.jQuery || document.write('<script src="js/vendor/jquery-1.11.0.min.js"><\/script>');
			// Enable Navigator

		</script>
		<script src="js/vendor/jquery-ui-1.10.4.custom.js" type="text/javascript"></script>
		<script src="js/vendor/jquery.jsPlumb-1.5.5.js" type="text/javascript"></script>
		<script src="./js/Navigator.js" type="text/javascript"></script>
		<script src="js/vendor/bootstrap.min.js" type="text/javascript"></script>
		<script>
			nav.navigate();
		</script>
		<script src="./js/bootstrap-datepicker.js" type="text/javascript"></script>
		<script src="./js/familytree.js" type="text/javascript"></script>
		<script>
			$('#birthdatepicker').datepicker({
				format : 'mm/dd/yyyy',
				onRender : function(date) {
					return date.valueOf() > new Date().valueOf() ? 'disabled' : '';
				}
			}).on('changeDate', function(event) {
				$('#birthdate').val($('#birthdatepicker').data('date'));
			});
		</script>
		<script>
			familyTree.inithover();
			familyTree.initTools();
			$('.addtools').tooltip();
		</script>

	</body>
