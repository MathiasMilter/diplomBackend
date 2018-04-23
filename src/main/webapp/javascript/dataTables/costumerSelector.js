$(document).ready(function() {
  let table = $('#costumerTable');
  ajaxGet("costumers", createGraph);
});

function createGraph(obj) {
  table = $('#costumerTable').DataTable({
    //				"bFilter": false,
    //				"paging":   false,
    //				"ordering": false,
    //				"info":     false,
    dom: 'Bfrtip',
    data: obj,
    columns: [{
        "data": "name",
        "width": "20%"
      },
      {
        "data": "email",
        "width": "20%"
      },
      {
        "data": "phoneNumber",
        "width": "20%"
      },
      {
        "data": "adress",
        "width": "20%"
      },
      {
        "data": "hasSubscription",
        "width": "10%"
      },
      {
        "data": "costumerID",
        "width": "10%",
        render: $.fn.dataTable.render.ellipsis(20)
      },
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
          text: 'Ny Kunde',
          action: function(e, dt, node, conf) {
            window.location = "costumerRegister.html";
          }
        }, {
          text: 'Se Kunde',
          action: function(e, dt, node, conf) {
            if (isSelected() != null) {
              window.location = "costumerInfo.html";
            } else {
              alert("Vælg venligst en kunde først.")
            }


          }
        }, {
          text: 'Redigér Kunde',
                    className: 'yellowButton',
          action: function(e, dt, node, conf) {
            if (isSelected() != null) {
              changeOrder();
              window.location = "costumerRegister.html";
            } else {
              alert("Vælg venligst en kunde først.")
            }


          }
        }, {
          text: 'Slet Kunde',
          className: 'redButton',
          action: function(e, dt, node, conf) {
            if (isSelected() != null) {
              if (confirm("!!!DU ER VED AT SLETTE EN KUNDE!!!")) {
                ajaxDelete('costumers/' + getCostumerID());
                location.reload();
                deSelect();
              }
            } else {
              alert("Vælg venligst en kunde først.")
            }


          }
        }]
      }

    ]
  });

  $('#costumerTable').on('click', 'tbody tr', function() {
    selectRow(this);
    select();
  });

};

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
