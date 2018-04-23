$(document).ready(function() {
	let table = $('#orderTable');
	ajaxGet("orders/costumer/" + getCostumerID(), createGraph);
} );

function createGraph(obj) {
	table = $('#orderTable').DataTable({
//				"bFilter": false,
//				"paging":   false,
//				"ordering": false,
//				"info":     false,
		dom : 'Bfrtip',
		data : obj,
		columns : [
			{ "data": "description", "width": "20%",render: $.fn.dataTable.render.ellipsis(20)},
			{ "data": "prizeString", "width": "15%"},
			{ "data": "time", "width": "15%"},
			{ "data": "done", "width": "15%"},
			{ "data": "payed", "width": "15%"},
			{ "data": "orderID", "width": "20%", render: $.fn.dataTable.render.ellipsis(20)}
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
					text: 'Ny Ordre',
					action: function ( e, dt, node, conf ) {
						window.location = "orderRegister.html";
					}
				},
				{
					text: "Opret/Redigér abonnoment",
					action: function ( e, dt, node, conf ) {					
						if ($("#startDate").text().length > 5){
							changeOrder();
						}
						window.location = "subscriptionRegister.html";
					}
				}
		]
	});
	
    $('#orderTable').on('click', 'tbody tr', function () {
    	selectRow(this);
    	setOrderID(table.row(this).data().orderID);
    	setCostumerID(table.row(this).data().costumerID);
    } );
};

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

