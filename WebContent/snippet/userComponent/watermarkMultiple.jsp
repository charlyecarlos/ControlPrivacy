
<script src="register/vendor/jquery/jquery.min.js"></script>
<script type='text/javascript' src='js/inputFile.js'></script>
      
<!-- Area Chart Example-->
<div class="card mb-3 text-center">
	<div class="card-header">
		<span class="glyphicon glyphicon-level-up"></span>Add watermark with multiple files
	</div>
	<div class="card-body col-lg-10 mx-auto" style="padding: 1rem;">
		<form method="post" action="CreateSimpleWatermark" enctype="multipart/form-data">
			<div class="form-group">
				<!-- COMPONENT START -->
				<div class="form-group">
					<div class="input-group input-image-multiple">
						<input type="text" name="localFile" class="form-control"placeholder='Choose a file...' /> 
						<span class="input-group-btn">
							<button class="btn btn-default btn-choose" type="button">Choose</button>
						</span>
					</div>
				</div>
				<div class="text-left" style="margin: 10px;">
					<input type="radio" name="tab" value="hidden"
						onclick="hiddenText();" checked /> Logo <input type="radio"
						name="tab" value="show" onclick="showText();" /> Text
				</div>
				<div id="hide" class="hide" style="display: none;">
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
	<div class="card-footer small text-muted">You must add a logo to
		be able to put it to the watermark</div>
</div>