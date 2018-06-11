

<%@page import="domain.User"%>
<%@page import="java.util.List"%>
<%@page import="domain.Image"%>
<%@page import="java.util.ArrayList"%>
<%@page import="services.ServiceImage"%>

<%	User user2=(User) session.getAttribute("user"); %>
<div class="card mb-3">
	<div class="card-header">
		<i class="fa fa-table"></i> Data ViewImage
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable" width="100%"
				cellspacing="0">
				<thead>
					<tr>
						<th>Url Image</th>
						<th>Date Creation</th>
						<th>Expiration Date</th>
						<th>Visits</th>
					</tr>
				</thead>
				<tbody>
					<%
						ServiceImage simage = new ServiceImage();
						List<Image> images = new ArrayList<Image>();
						images = simage.recoverImageForUser(user2);
						for (Image image : images) {
					%>
					<tr>
						<td><%=getServletContext().getInitParameter("urlImage")%>/image.html?img=<%=image.getUrl_redirect()%></td>
						<td><%=image.getDate_creation()%></td>
						<td><%=image.getExpiration_date()%></td>
						<td><%=image.getVisits()%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<div class="card-footer small text-muted">Updated now</div>
</div>