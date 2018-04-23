function selectRow(row){
 	$('tr').not(row).removeClass('tableSelected');
	$(row).addClass('tableSelected');
	setCostumerID(table.row(row).data().costumerID);
	select();
}
