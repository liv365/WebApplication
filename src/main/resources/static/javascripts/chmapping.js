$(document).ready(function () {
    $('#list-propertyChannelMapping table').DataTable({
        "columns": [
            { "width": "5%" },
            null,
            null,
            { "width": "5%" },
            null,
            null,
            { "width": "5%" }

        ]
    } );

    $('#select-change-hotel-at-work').on('change', function() {
        document.forms["form-change-hotel-at-work"].submit();
    });

    $(document).ready(function () {
        $('#list-mappedProperties table').DataTable({
            columns: [
                {"width": "5%"},
                null,
                null,
                {"width": "5%"},
                null,
                {"width": "5%"}
            ]
        });
        $('#list-mappedRooms table').DataTable({
            columns: [
                {"width": "5%"},
                null,
                null,
                {"width": "5%"},
                null,null,null,
                {"width": "5%"}
            ]
        });
    });
});

