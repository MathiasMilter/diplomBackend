$(document).ready(function() {
  let table = $('#orderTable');
  ajaxGet("orders/", createGraph);
});

function createGraph(obj) {
  table = $('#orderTable').DataTable({
    //		"bFilter": false,
    //		"paging":   false,
    //		"ordering": false,
    //		"info":     false,
    dom: 'Bfrtip',
    data: obj,
    columns: [{
        "data": "costumer",
        "width": "10%"
      },
      {
        "data": "adress",
        "width": "20%"
      },
      {
        "data": "description",
        "width": "10%",
        render: $.fn.dataTable.render.ellipsis(20)
      },
      {
        "data": "prizeString",
        "width": "10%"
      },
      {
        "data": "time",
        "width": "10%"
      },
      {
        "data": "done",
        "width": "10%"
      },
      {
        "data": "payed",
        "width": "10%"
      },
      {
        "data": "orderID",
        "width": "10%",
        render: $.fn.dataTable.render.ellipsis(20)
      },
      {
        "data": "costumerID",
        "width": "10%",
        render: $.fn.dataTable.render.ellipsis(20)
      }
    ],
    "columnDefs": [{
      "className": "dt-center",
      "targets": "_all"
    }],
    buttons: [{
      extend: 'collection',
      text: 'Export',
      buttons: ['excel', 'pdf']
    }, {
      extend: 'collection',
      text: 'Handling',
      buttons: [{
          text: 'Se kunde',
          action: function(e, dt, node, conf) {
            if (isSelected() != null) {
              window.location = "costumerInfo.html";
              deSelect();
            } else {
              alert("Tryk på en ordre for at se mere om kunden.")
            }
          }
        },
        {
          text: 'Se order',
          action: function(e, dt, node, conf) {
            if (isSelected() != null) {
              window.location = "orderInfo.html";
              deSelect();
            } else {
              alert("Tryk på en ordre for at se mere om kunden.")
            }
          }
        },
        {
          text: 'Gennemført/Ikke Gennemført',
          action: function(e, dt, node, conf) {
            if (isSelected() != null) {
              ajaxPut('orders/done/' + getOrderID());
              location.reload();
              deSelect();
            } else {
              alert("Vælg venligst en ordre først.")
            }


          }
        },
        {
          text: 'Betalt/Ikke Betalt',
          action: function(e, dt, node, conf) {
            if (isSelected() != null) {
              ajaxPut('orders/pay/' + getOrderID());
              location.reload();
              deSelect();
            } else {
              alert("Vælg venligst en ordre først.")
            }


          }
        }, {
          text: 'Redigér ordre',
          className: 'yellowButton',
          action: function(e, dt, node, conf) {
            if (isSelected() != null) {
              if (confirm("!!!DU ER VED AT SLETTE EN ORDRE!!!")) {
                changeOrder();
                deSelect();
                window.location = "orderRegister.html";
              }
            } else {
              alert("Vælg venligst en ordre først.")
            }


          }
        },
        {
          text: 'Slet Ordre',
          className: 'redButton',
          action: function(e, dt, node, conf) {
            if (isSelected() != null) {
              if (confirm("!!!DU ER VED AT SLETTE EN ORDRE!!!")) {
                ajaxDelete('orders/' + getOrderID());
                location.reload();
                deSelect();
              }
            } else {
              alert("Vælg venligst en ordre først.")
            }


          }
        }
      ]
    }]
  });

  $('#orderTable').on('click', 'tbody tr', function() {
    selectRow(this);
    setOrderID(table.row(this).data().orderID);
  });

}

$.fn.dataTable.render.ellipsis = function(cutoff, wordbreak, escapeHtml) {
  var esc = function(t) {
    return t
      .replace(/&/g, '&amp;')
      .replace(/</g, '&lt;')
      .replace(/>/g, '&gt;')
      .replace(/"/g, '&quot;');
  };

  return function(d, type, row) {
    // Order, search and type get the original data
    if (type !== 'display') {
      return d;
    }

    if (typeof d !== 'number' && typeof d !== 'string') {
      return d;
    }

    d = d.toString(); // cast numbers

    if (d.length < cutoff) {
      return d;
    }

    var shortened = d.substr(0, cutoff - 1);

    // Find the last white space character in the string
    if (wordbreak) {
      shortened = shortened.replace(/\s([^\s]*)$/, '');
    }

    // Protect against uncontrolled HTML input
    if (escapeHtml) {
      shortened = esc(shortened);
    }

    return '<span class="ellipsis" title="' + esc(d) + '">' + shortened + '&#8230;</span>';
  };
};
