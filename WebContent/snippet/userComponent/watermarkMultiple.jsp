
<script src="register/vendor/jquery/jquery.min.js"></script>
<script type='text/javascript' src='js/inputFile.js'></script>

<!-- Area Chart Example-->
<div class="card mb-3">
	<div class="card-header">
		<i class="fa fa-image"></i> Add watermark with multiple files
	</div>
	<div class="card-body col-lg-10 mx-auto text-center" style="padding: 1rem;">

		<form method="post" action="CreateMultipleWatermark"
			enctype="multipart/form-data">
			<div class="form-group">
				<!-- COMPONENT START -->
				<div class="form-group">
					<div class="input-group input-image-multiple" name="imageFiles">
						<input type="text" name="localFile" class="form-control"
							placeholder='Choose a file...' /> <span class="input-group-btn">
							<button class="btn btn-default btn-choose" type="button">Choose</button>
						</span>
					</div>
				</div>
				<div class="btn-group btn-group-toggle" data-toggle="buttons">
					<label class="btn btn-secondary active"> 
						<input type="radio" name="position" value="centered" autocomplete="off" checked> Center
					</label>
					<label class="btn btn-secondary">
						<input type="radio" name="position" value="upper_left" autocomplete="off"> Upper Left
					</label>
					<label class="btn btn-secondary"> 
						<input type="radio" name="position" value="upper_right" autocomplete="off"> Upper Right 
					</label>
					<label class="btn btn-secondary"> 
						<input type="radio" name="position" value="delow_left" autocomplete="off"> Delow Left 
					</label>
					<label class="btn btn-secondary"> 
						<input type="radio" name="position" value="delow_right" autocomplete="off"> Delow Right 
					</label>
				</div>
				<br>
				<div class="text-left" style="margin: 10px;">
					<input type="radio" name="tab" value="hidden"
						onclick="hiddenText();" disabled /> Logo <input type="radio"
						name="tab" value="show" onclick="showText();" checked /> Text
				</div>
				<div id="hide" class="hide" style="display: block;">
					<input type="text" name="textWatermark" class="form-control"
						placeholder='Name of the watermark...' />
				</div>
			</div>
			<!-- COMPONENT END -->
			<div class="form-group" style="margin-bottom: 0;">
				<button type="submit" class="btn btn-primary">Create</button>
				<button type="reset" class="btn btn-danger">Reset</button>
			</div>
		</form>
	</div>
	<%
		if (request.getAttribute("error") != null) {
	%>
	<div class="alert alert-danger col-lg-6 col-lg-offset-3 text-center">
		<p><%=request.getAttribute("error")%></p>
	</div>
	<%
		}
	%>
	<div class="card-footer small text-muted">You must add a logo to
		be able to put it to the watermark</div>
</div>