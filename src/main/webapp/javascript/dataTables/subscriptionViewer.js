$(document).ready(function() {
	let table = $('#subscriptionTable');
	ajaxGet("subscriptions/", createGraph);
} );

function createGraph(obj) {
	table = $('#subscriptionTable').DataTable({
//		"bFilter": false,
//		"paging":   false,
//		"ordering": false,
//		"info":     false,
		dom : 'Bfrtip',
		data : obj,
		columns : [
			{ "data": "costumer", "width": "10%"},
			{ "data": "adress", "width": "20%"},
			{ "data": "description", "width": "10%", render: $.fn.dataTable.render.ellipsis(20)},
			{ "data": "priceString", "width": "10%"},
			{ "data": "startDate", "width": "10%"},
			{ "data": "intervalMonths", "width": "10%"},
			{ "data": "nextOrder", "width": "10%"},
			{ "data": "subscriptionID","width": "10%", render: $.fn.dataTable.render.ellipsis(20)},
			{ "data": "costumerID", "width": "10%", render: $.fn.dataTable.render.ellipsis(20)}
			],
			"columnDefs": [
				{"className": "dt-center", "targets": "_all"}
				],
				buttons: [
					{
						extend : 'collection',
						text : 'Export',
						buttons: ['excel', 'pdf']
					},
					{
						text: 'Se kunde',
						action: function ( e, dt, node, conf ) {
							if (isSelected() != null){
								window.location = "costumerInfo.html";
								deSelect();
							}
							else {
								alert("Tryk på en ordre for at se mere om kunden.")
							}
						}
					}
					]
	});


	$('#subscriptionTable').on('click', 'tbody tr', function () {
		selectRow(this);
	} );

}

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
