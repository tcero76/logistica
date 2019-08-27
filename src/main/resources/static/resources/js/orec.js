/**
 * 
 */

function enviar() {
	let csrf = $('input[name="_csrf"]').val();
	$.ajax({
		url : "/orec/form?_csrf=" + csrf,
		success:function(data,status,jqXHR) {
			$('#frmOrec').replaceWith(data);
		},
	});
}