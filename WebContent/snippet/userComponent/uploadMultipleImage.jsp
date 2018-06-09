<%@page import="domain.TimeImage"%>
<%@page import="services.ServiceTimeImage"%>
<%@page import="java.util.List"%>

<div class="card mb-3">
	<div class="card-header">
		<i class="fa fa-cloud-upload"></i> Upload multiple images
	</div>
	<div class="card-body col-lg-10 mx-auto">
		<div class="table-responsive">
			<form method="post" action="ViewImage" enctype="multipart/form-data">
				<div class="form-group">
					<div class="input-group input-image-multiple" name="imageFile">
						<input type="text" class="form-control"
							placeholder='Choose a file...' /> <span class="input-group-btn">
							<button class="btn btn-default btn-choose" type="button">Choose</button>
						</span>
					</div>
					<br></br> 
					<select name="viewImage" class="form-control">
						<%
							ServiceTimeImage stimeimage = new ServiceTimeImage();
							List<TimeImage> timeimages = stimeimage.recoverAllTimeImage();
							for (TimeImage timeimage : timeimages) {
						%>
						<option value="<%=timeimage.getMinutes()%>"><%=timeimage.getDescription()%>
							to expire
						</option>
						<%
							}
						%>
					</select>
				</div>
				<!-- COMPONENT END -->
				<div class="form-group text-center">
					<button type="submit" class="btn btn-primary">Upload</button>
					<button type="reset" class="btn btn-danger">Reset</button>
				</div>
			</form>
		</div>
	</div>
	<div class="card-footer small text-muted">The images are added automatically on your list of images uploaded
	</div>
</div>