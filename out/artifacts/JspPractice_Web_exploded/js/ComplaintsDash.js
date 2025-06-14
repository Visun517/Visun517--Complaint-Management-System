$('#complain-tbody').on('click', 'tr', function () {
    let clickedItemIndex = $(this);
    let id = clickedItemIndex.find('td').eq(0).text();
    let description = clickedItemIndex.find('td').eq(1).text();
    let status = clickedItemIndex.find('td').eq(2).text();
    let remarks = clickedItemIndex.find('td').eq(3).text();
    let createdDate = clickedItemIndex.find('td').eq(4).text();
    console.log(description)

    $('#complaintId').val(id);
    $('#status').val(status);
    $('#description').val(description);
    $('#remark').val(remarks);
    $('#creatAt').val(createdDate);
    $('#updateId').val(id);
    $('#deleteId').val(id);

})