$('#complain-tbody-admin').on('click', 'tr', function () {
    let clickedItemIndex = $(this);
    let id = clickedItemIndex.find('td').eq(0).text();
    let description = clickedItemIndex.find('td').eq(1).text();
    let status = clickedItemIndex.find('td').eq(2).text();
    let remarks = clickedItemIndex.find('td').eq(3).text();
    let createdDate = clickedItemIndex.find('td').eq(4).text();
    console.log(description)

    $('#complain-id').val(id);
    $('#statusDropDown').val(status);
    $('#description').val(description);
    $('#createAt').val(createdDate);
    $('#status').val(status);
    $('#remark').val(remarks);



})