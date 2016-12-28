<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="<c:url value="/resources/js/myScript/my.tour.add.avia.js"/>"></script>

<div class="modal fade" id="ModalOpenAvia" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header"></div>
					<div class="js-tickets-container">${parse}</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-font" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span></button>
			</div>
		</div>
	</div>
</div>