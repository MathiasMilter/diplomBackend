$(document).ready(function() {

	var table = $('#selectOrder')

	$.ajax({
		type : "GET",
		url : 'rest/products',
		dataType : 'json',
		success : function(obj) {
			table = $('#productTable').DataTable({
				"bFilter": false,
				"paging":   false,
				"ordering": false,
				"info":     false,
				"bDeferRender" : true,
				"scrollY" : '50vh',
				select: true,
				data : obj,
				columns : [
					{ "data": "id", "visible" : false},
					{ "data": "name", render: $.fn.dataTable.render.ellipsis(20)},
					{ "data": "count", render: $.fn.dataTable.render.ellipsis(20)}
					],
					"columnDefs": [
						{"className": "dt-center", "targets": "_all"}
						]
			});
		}
	});
} );

$.fn.dataTable.render.ellipsis = function ( cutoff, wordbreak, escapeHtml ) {
	var esc = function ( t ) {
		return t
		.replace( /&/g, '&amp;' )
		.replace( /</g, '&lt;' )
		.replace( />/g, '&gt;' )
		.replace( /"/g, '&quot;' );
	};

	return function ( d, type, row ) {
		// Order, search and type get the original data
		if ( type !== 'display' ) {
			return d;
		}

		if ( typeof d !== 'number' && typeof d !== 'string' ) {
			return d;
		}

		d = d.toString(); // cast numbers

		if ( d.length < cutoff ) {
			return d;
		}

		var shortened = d.substr(0, cutoff-1);

		// Find the last white space character in the string
		if ( wordbreak ) {
			shortened = shortened.replace(/\s([^\s]*)$/, '');
		}

		// Protect against uncontrolled HTML input
		if ( escapeHtml ) {
			shortened = esc( shortened );
		}

		return '<span class="ellipsis" title="'+esc(d)+'">'+shortened+'&#8230;</span>';
	};
};
