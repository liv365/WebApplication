function handleFiles(files) {
    for (var i = 0; i < files.length; i++) {
        var file = files[i];

        if (!file.type.startsWith('image/')) {
            continue
        }

        var img = document.createElement("img");
        img.classList.add("obj");
        img.file = file;
        $("#hotelLogoParent").appendChild(img); // Assuming that "preview" is the div output where the content will be displayed.

        var reader = new FileReader();
        reader.onload = (function (aImg) {
            return function (e) {
                aImg.src = e.target.result;
            };
        })(img);
        reader.readAsDataURL(file);
    }
}
$(document).ready(function () {
    $("#menu li a").click(function () {
        $("#menu li").removeClass('');
        $(this).parent().addClass('active');
    });
});



$(document).ready(function () {

    $('#list-hotelFacility table').DataTable( {
        "searching": true
    } );
    $('#list-user table').DataTable( {
        "searching": true
    } );
    $('#list-userRole table').DataTable( {
        "searching": true
    } );

    // Setup - add a text input to each footer cell

    $('#propertySearch table thead tr').clone(true).appendTo( '#propertySearch thead' );
    $('#propertySearch table thead tr:eq(1) th').each( function (i) {
        var title = $(this).text();
        $(this).html( '<input type="text" placeholder=" '+title+'" style="width: 100%" />' );

        $( 'input', this ).on( 'keyup change', function () {
            if ( table.column(i).search() !== this.value ) {
                table
                    .column(i)
                    .search( this.value )
                    .draw();
            }
        } );
    } );

    var table = $('#propertySearch table').DataTable( {
        orderCellsTop: true,
        pageLength: 100
    } );


    $("#scroll_table1 .sorting_asc, #scroll_table1 .sorting, #scroll_table1").attr('style','');

    $("#select-change-hotel-at-work").select2();
    $("#select2-multiple").select2();
    $("#property").select2();
    $("#select2-role").select2();
    $('.submit').on('click', function (event) {
        $('body').append('<div style="" id="loadingDiv"><div class="loader"></div></div>');
        var $form = $("#createHotelTemplate");
        var $target = $("#hotelCreateLog");
        //USED in the New Hotel creation from template
        $.ajax({
            type: $form.attr('method'),
            url: $form.attr('action'),
            data: $form.serialize(),
            success: function (data, status, response) {
                var jsonObject = JSON.parse(response.responseText);
                $target.append("<div class=\"card p-4\">\n" +
                    "                    <div class=\"pb-4\">\n" +
                    "                        <div class=\"d-flex mb-3\">\n" +
                    "                            <span class=\"text-md\">Log</span>\n" +
                    "\n" +
                    "                        </div>\n" +
                    "\n" +
                    "                        <div>" +
                    jsonObject.message +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "\n" +
                    "                </div>");
                setTimeout(removeLoader, 200);
            },
            error: function (data, status) {
                alert("Error occurred " + jsonObject.message);
                console.log("status " + status);
                console.log(data);
                setTimeout(removeLoader, 200); //wait for page load PLUS
            },
            done: function (data, textStatus, jqXHR) {
                console.log("DONE");
                console.log(data);
                console.log(textStatus);
                console.log(jqXHR);
                setTimeout(removeLoader, 200); //wait for page load PLUS
            }

        });

        event.preventDefault();
    });
    // $('#summer-note').summernote();


});


$("#btnDeleteHotelLogo").click(function (e) {
    e.preventDefault();
    var propertyId = $(this).data('pid');
    $.ajax({
        type: "POST",
        url: "/upload/deleteImage/",
        data: {
            id: $(this).val(),
            propertyId: propertyId
        },
        success: function (result) {

            location.reload();
            $("#hotelLogoParent").empty();
        },
        error: function (result) {
            alert('error ' + result);
        }
    });
});
Dropzone.autoDiscover = false;
var acceptedFileTypes = "image/*"; //dropzone requires this param be a comma separated list
var fileList = [];
var i = 0;
$("#hotelLogoUploadImage").dropzone({
    addRemoveLinks: true,
    maxFiles: 1, //change limit as per your requirements
    dictMaxFilesExceeded: "Maximum upload limit reached",
    acceptedFiles: acceptedFileTypes,
    dictInvalidFileType: "upload only JPG/PNG",
    init: function () {
        $(this.element).addClass("dropzone");

        this.on("success", function (file, serverFileName) {

            $("#hotelLogo").val(file.name);
            fileList[i] = {
                "serverFileName": serverFileName,
                "fileName": file.name,
                "fileId": i
            };
            $('.dz-message').show();
            i += 1;
        });
        this.on("removedfile", function (file) {
            // console.log(file);
            $("#hotelLogo").val("");

            var rmvFile = "";
            for (var f = 0; f < fileList.length; f++) {
                if (fileList[f].fileName == file.name) {
                    rmvFile = fileList[f].serverFileName;
                }
            }

            if (rmvFile) {
                $.ajax({
                    url: "/upload", //Read it from data- variable
                    type: "POST",
                    data: {
                        fileNameDelete: file.name,
                        type: 'delete',
                    },
                });
            }
        });

    }
});

//ROOM PHOTOS ARE HANDLED HERE
if (window.location.href.indexOf("room") >= 0) {

}

$("#roomUploadImage").dropzone({
    addRemoveLinks: true,
    maxFiles: 10, //change limit as per your requirements
    dictMaxFilesExceeded: "Maximum upload limit reached",
    acceptedFiles: acceptedFileTypes,
    dictInvalidFileType: "upload only JPG/PNG",

    init: function () {
        $(this.element).addClass("dropzone");
        var myDropzone = this;
        $.each($("#dropzone-roomphotos").data("roomphotos"), function (key, value) {
            console.log("each here +" + value.name);
            var mockFile = {name: value.name, size: value.size};
            myDropzone.emit("addedfile", mockFile);

          //  myDropzone.emit("thumbnail", mockFile, "/upload/getImage?filename="+ value.name+"&room="+$("#dropzone-room-id").data("room"));

            myDropzone.emit("thumbnail", mockFile, "/upload/getImage?filename=" + value.name + "&room=" + $("#dropzone-room-id").data("room"));
            fileList[i] = {
                "serverFileName": value.name,
                "fileName": value.name,
                "fileId": i
            };
            $('.dz-message').show();
            i += 1;

        });
        this.on("sending", function (file, xhr, data) {
            data.append("roomid", $("#dropzone-room-id").data("room"));
        });
        this.on("success", function (file, serverFileName) {
            fileList[i] = {
                "serverFileName": serverFileName,
                "fileName": file.name,
                "fileId": i
            };
            $('.dz-message').show();
            i += 1;
        });
        this.on("removedfile", function (file) {
            var rmvFile = "";
            for (var f = 0; f < fileList.length; f++) {
                if (fileList[f].fileName == file.name) {
                    rmvFile = fileList[f].serverFileName;
                }
            }
            console.log(rmvFile);
            if (rmvFile) {
                console.log("REMOVE FILE");
                $.ajax({
                    url: "/upload/roomphoto", //Read it from data- variable
                    type: "POST",
                    data: {
                        fileNameDelete: file.name,
                        type: 'delete',
                        roomid: $("#dropzone-room-id").data("room")
                    },
                });
            }
        });

    }
});

$("#hotelPhoto").dropzone({
    addRemoveLinks: true,
    maxFiles: 60, //change limit as per your requirements
    dictMaxFilesExceeded: "Maximum upload limit reached",
    acceptedFiles: acceptedFileTypes,
    dictInvalidFileType: "upload only JPG/PNG",

    init: function () {
        $(this.element).addClass("dropzone");
        this.on("success", function (file, serverFileName) {
            fileList[i] = {
                "serverFileName": serverFileName,
                "fileName": file.name,
                "fileId": i
            };
            $('.dz-message').show();
            i += 1;
        });
        this.on("removedfile", function (file) {
            // console.log(file);
            $("#hotelLogo").val("");

            var rmvFile = "";
            for (var f = 0; f < fileList.length; f++) {
                if (fileList[f].fileName == file.name) {
                    rmvFile = fileList[f].serverFileName;
                }
            }
            if (rmvFile) {
                $.ajax({
                    url: "/upload", //Read it from data- variable
                    type: "POST",
                    data: {
                        fileNameDelete: file.name,
                        type: 'delete',
                        fileType: "hotelPhotos"
                    },
                });
            }
        });

    }
});
$('button[name=deleteHotelPhoto]').click(function () {
    var id = $(this).data("id");
    var photoId = $(this).data("photoid");
    var photoType = $(this).data("phototype");
    var propertyId = $(this).data("propertyid");

    $.ajax({
        url: "/upload/deleteImage", //Read it from data- variable
        type: "POST",
        data: {
            id: id,
            type: 'delete',
            photoType: photoType,
            photoId: photoId,
            propertyId: propertyId
        },
        success: function () {
            $("#hotelPhoto-" + photoId).remove();
        },
    });
});






$('#create-room-details').click(function (e) {
    e.preventDefault();

    if ($('#create-room-details').text() == 'Create') {
        $('body').append('<div style="" id="loadingDiv"><div class="loader"></div></div>');
        var roomview = $('#room-view').val();
        var description = $('#description').summernote('code').toString();
        var floor = $('#floor').val();
        var lang = $('#language').val();
        var room = $('#roomId').val();
        // alert(description);
        var language = lang.toString();

        $.ajax({
            method: "POST",
            url: "/roomDetailsLanguage/ajaxCreateRoomDetailsView",
            data: {
                room: room,
                roomView: roomview,
                description: description,
                floor: floor,
                language: language
            },
            success: function () {
                setTimeout(removeLoader, 200); //wait for page load PLUS
                $('#room-view').val('');
                $("#description").summernote("reset");
                $('#floor').val('');
                $('#language').val('');
                $('#roomId').val('');

                $('#message').load(document.URL + ' #message>*', '');

                $('#langRoom').load(location.href + ' #langRoom>*', '');


            },
            error: function (resp, status) {
                setTimeout(removeLoader, 200); //wait for page load PLUS
                // console.log(resp.status);
                $('#message').load(document.URL + ' #message>*', '');
                //alert("Failed to save the data.");

            }
        });
    }
});


$('#langRoom').on('click', '#lang a',function (e) {

    e.preventDefault();
    idRoomDetailsLang = $(this).attr('data-id');
    var floorUpd = $(this).attr('data-floor');
    var lngUpd = $(this).attr('data-language');
    var namelngUpd = $(this).attr('data-lang');
    var roomUpd = $(this).attr('data-room');
    var descriptionUpd = $(this).attr('data-description');

    $('#room-view').val(roomUpd);
    $('#floor').val(floorUpd);
    $('#language').val(lngUpd);
    $("#description").summernote('code', descriptionUpd);
    // $('#roomId').val('');
    $('#create-room-details').text('Update');
});


$('#clear-room-details').click(function () {
    $('#room-view').val('');
    $("#description").summernote("reset");
    $('#floor').val('');
    $('#language').val('');
    //$('#roomId').val('');
    $('#message').load(document.URL  + ' #message>*','');
    $('#create-room-details').text( 'Create');
});

$('#create-room-details').click(function (e) {

    e.preventDefault();
    $('body').append('<div style="" id="loadingDiv"><div class="loader"></div></div>');
    if ($('#create-room-details').text() == 'Update') {
        var roomviewUpd = $('#room-view').val();
        var descriptionUpd = $('#description').summernote('code')
        var floorUpd = $('#floor').val();
        var langUpd = $('#language').val();
        var roomUpd = $('#roomId').val();

        var languageUpd = langUpd.toString();

        $.ajax({
            method: "POST",
            url: "/roomDetailsLanguage/ajaxUpdateRoomDetailsView",
            data: {
                room: roomUpd,
                roomView: roomviewUpd,
                description: descriptionUpd,
                floor: floorUpd,
                language: languageUpd,
                id : idRoomDetailsLang
            },
            success: function () {
                $('#room-view').val('');
                $("#description").summernote("reset");
                $('#floor').val('');
                $('#language').val('');
                $('#roomId').val('');
                //location.reload();
                $('#message').load(document.URL  + ' #message>*','');
                $('#langRoom').load(location.href  + ' #langRoom>*','');
                setTimeout(removeLoader, 200); //wait for page load PLUS
            },
            error: function (resp, status) {
                // console.log(resp.status);
                //alert("Failed to update the data.");
                $('#langRoom').load(location.href  + ' #langRoom>*','');
                setTimeout(removeLoader, 200); //wait for page load PLUS
            }
        });
        $('#create-room-details').text('Create');
    }
});


$(document).ready(function () {
    $('.repeater').repeater({});

    $(document).ready(function() {
        var max_fields      = 10; //maximum input boxes allowed
        var wrapper   		= $(".input_fields_wrap"); //Fields wrapper
        var add_button      = $(".add_field_button"); //Add button ID

        var x = 1; //initlal text box count
        $(add_button).click(function(e){ //on add input button click
            e.preventDefault();
            if(x < max_fields){ //max input box allowed
                x++; //text box increment
                var newel = $('.input_fields_wrap:last').clone(true);
                if ($('.remove_field').length == 0){
                    $(newel).append('<a href="#" class="mt-4 remove_field"><span class="i-con-h-a"><i class="mr-2 i-con i-con-close"></i>Remove</span></a>');
                }
                newel.find("input").val("");
                $(newel).insertAfter(".input_fields_wrap:last");
            }
        });

        $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
            e.preventDefault(); $(this).parent('div').remove();
        })
    });
});

function isEmpty(str) {
    switch (str) {
        case '':
        case ' ':
        case null:
        case false:
        case typeof this == 'undefined':
            return true;
        default:
            return false;
    }
}


$('#mEdit').on('show.bs.modal', function(e) {
    var def = $(e.relatedTarget).data('default');
    $('input[id="default"]').prop('checked', def);
    var guests = $(e.relatedTarget).data('name');
    $(e.currentTarget).find('input[id="guest"]').val(guests);
});

$('#occupancyScenarios-tab').click(function (e) {

if ($('#pricing').val() != 1)//Per Guest
{
    $('#addOccupancy').prop('disabled', true);
}
});

$('#addR').click(function (e) {
    e.preventDefault();
    var adult = $('#combValueAdult').val();
    var child = $('#combValueChild').val();
    var rooms = $('#roomId').val();
    var comb = (child == 0 ? adult + ' Adult(13+)' :adult + ' Adult(13+), '+ child + ' Child(3-12)');

    $.ajax( {
        method: "POST",
        url : "/roomOccupancyScenarios/ajaxAddRoomOccupancyScenarios",
        data :  {
            room: rooms,
            adult: adult,
            child : child,
            combinations: comb
        } ,
        success : function() {

            $('#messageOccupScenario').load(window.location  + ' #messageOccupScenario>*','');
            $('#tbl').load(window.location  + ' #tbl>*','');
            $('#combValueAdult').val('0');
            $('#combValueChild').val('0');


        },
        error: function(resp, status) {
           // alert("Failed to save the data.");
            $('#messageOccupScenario').load(window.location  + ' #messageOccupScenario>*','');
        }
    });

});

$('#updateOccupancyScenario').click(function (e) {
    e.preventDefault();
    var def = $('#default').prop('checked');
    var rooms = $('#roomId').val();
    $.ajax({
        method: "POST",
        url: "/roomOccupancyScenarios/ajaxUpdateRoomOccupancyScenarios",
        data: {
            room: rooms,
            default: def,
            id: idRoomOccupancyScenario
        },
        success: function () {
            $('#tbl').load(window.location + ' #tbl>*', '');
            $('#messageOccupScenario').load(window.location + ' #messageOccupScenario>*', '');
            $('input[id="default"]').val('');
            },
        error: function (resp, status) {
                // console.log(resp.status);
                //alert("Failed to save the data.");
            $('#messageOccupScenario').load(window.location + ' #messageOccupScenario>*', '');
        }
    });
});


$('#deleteOccupancyScenario').click(function (e) {
    //$('#tbl').on('click', '#actions  a[href="#mTrash"]',function (e) {
    e.preventDefault();
    $('body').append('<div style="" id="loadingDiv"><div class="loader"></div></div>');
    var rooms = $('#roomId').val();
    //idRoomOccupancyScenario = $(this).attr('data-id');

    $.ajax( {
        method: "POST",
        url : "/roomOccupancyScenarios/ajaxDeleteRoomOccupancyScenarios",
        data :  {
            room: rooms,
            id:idRoomOccupancyScenario
        } ,
        success : function() {

            $('#tbl').load(window.location  + ' #tbl>*','');
            $('#messageOccupScenario').load(window.location  + ' #messageOccupScenario>*','');
            setTimeout(removeLoader, 200); //wait for page load PLUS
        },
        error: function(resp, status) {
            // console.log(resp.status);
            //alert("Failed to save the data.");
            $('#messageOccupScenario').load(window.location  + ' #messageOccupScenario>*','');
            setTimeout(removeLoader, 200); //wait for page load PLUS
        }
    });
});

$('#tbl').on('click', 'a',function (e) {
    e.preventDefault();
    idRoomOccupancyScenario = $(this).attr('data-id');
});

$(function () {
    //popover
    $('[data-toggle="popover"]').popover();
});


function removeLoader() {
    $("#loadingDiv").fadeOut(200, function () {
        // fadeOut complete. Remove the loading div
        $("#loadingDiv").remove(); //makes page more lightweight
    });
}


$('#allroom').click(function(){
    $('input[type=checkbox] ').prop('checked',$(this).prop('checked'));
});

$('#selectall').click(function(){
    $('input[type=checkbox] ').not(this).prop('checked', this.checked);
});

$('#default').click(function() {
    $('#minThrough').val('1');
    $('#minArrival').val('1');
    $('#maxThrough').val('0');
    $('#maxArrival').val('0');
    $('#exactThrough').val('0');
    $('#exactArrival').val('0');
    $('#canoch').addClass('active');
    $('#cdnoch').addClass('active');
    $('#cnoch').addClass('active');
});

$(window).on('load', function () {
    $('#list-properties table').DataTable({
        "columns": [
            { "width": "5%" },
            null,
            null,
            { "width": "5%" },
            null

        ]
    } );

});
$(document).ready(function () {
       $("#nav_nav > li > a").on("click", function(e) {

        if ($(this).next("ul").length > 0) {

            e.preventDefault();
            var navInner = $(this).next("li");

            if (navInner.is(":visible")) {

            }

        }

    });

    $('#facilities .form-check.form-check-inline').attr('id', 'form__check').addClass( "col-md-3 mb-3" );
    $('#attribute .form-check.form-check-inline').attr('id', 'form__check').addClass( "col-md-3 mb-3" );
    if (navigator.appVersion.indexOf("Chrome/") != -1) {
        $(".th_width").addClass("width_autoselect");
    }


    // select2 width of dropdown - select hotels aside menu
    $("#aside .card.hotel-logo_sidenav").attr('style', 'width: 220px!important');
    $("#aside .select2.select2-container.select2-container--default").attr('style', 'width: 220px!important');



});



$("#language-sq").empty();
$("#language-en").empty();
$("#language-es").empty();
$("#language-it").empty();
$("#language-de").empty();

$('#language-sq').prepend('<img src="/assets/languages/albania-flag.png"/>');
$('#language-en').prepend('<img src="/assets/languages/english-flag.png"/>');
$('#language-es').prepend('<img src="/assets/languages/spain-flag.png"/>');
$('#language-it').prepend('<img src="/assets/languages/italy-flag.png"/>');
$('#language-de').prepend('<img src="/assets/languages/germany-flag.png"/>');


if (window.location.href.indexOf("?lang=sq") > -1) {
    $(".lang-msg").html('<img src="/assets/languages/albania-flag.png"/>');
}



if (window.location.href.indexOf("?lang=sq") > -1) {
    $(".lang-msg").html('<img src="/assets/languages/albania-flag.png"/>');
}
if (window.location.href.indexOf("?lang=en") > -1) {
    $(".lang-msg").html('<img src="/assets/languages/english-flag.png"/>');
}
if (window.location.href.indexOf("?lang=es") > -1) {
    $(".lang-msg").html('<img src="/assets/languages/spain-flag.png"/>');
}
if (window.location.href.indexOf("?lang=it") > -1) {
    $(".lang-msg").html('<img src="/assets/languages/italy-flag.png"/>');
}
if (window.location.href.indexOf("?lang=de") > -1) {
    $(".lang-msg").html('<img src="/assets/languages/germany-flag.png"/>');
}
$(document).ready(function () {

    $('#checkIn').on('change', function () {
        var checkIn = $('#checkIn').datepicker('getDate');
        var checkOut = moment(checkIn).add(1, 'day').toDate();
        $('#checkOut').datepicker('update', checkOut);
    });

});

if (window.location.href.indexOf("property/edit") >= 0) {
 //$(document).ready(function () {
    var itemResult;
    function getLocation(dataLoc) {
        var i = 0;
        $.ajax({
            url: "https://dev.virtualearth.net/REST/v1/Locations",
            dataType: "jsonp",
            data: {
                key: "Aq3VH9W38vBJyU8wwZDwtshVNWWE8bcyoKOaTkvqH4cTCJU2ep7EB-z5AKrxAMn2",
                q: dataLoc //+"&locality="+ $('#city').val() +"&adminDistrict=" +$('#region').val()
            },
            jsonp: "jsonp",
            success: function (data) {
                var result = data.resourceSets[0];
                if (result) {
                    if (result.estimatedTotal > 0) {
                        $.map(result.resources, function (item) {
                            if (i === 0) {
                                //console.log(item);
                                itemResult = item;
                                i = i + 1;
                            }
                        });
                    }
                }
            }
        });
    }

    function initMap() {
        var map = new Microsoft.Maps.Map(document.getElementById('myMap'), {
            credentials: 'Aq3VH9W38vBJyU8wwZDwtshVNWWE8bcyoKOaTkvqH4cTCJU2ep7EB-z5AKrxAMn2',
            center: (itemResult !== undefined ? new Microsoft.Maps.Location(itemResult.point.coordinates[0], itemResult.point.coordinates[1]) : new Microsoft.Maps.Location(41.327953, 19.819025))
        });
        var pushpin = new Microsoft.Maps.Pushpin(map.getCenter(), {
            color: '#f00',
            draggable: false
        });
        map.entities.push(pushpin);
        if (itemResult !== undefined) {
            //console.log(itemResult.name);
            $('#location').val(itemResult.name.toString());
            $('#printoutPanel').val(itemResult.point.coordinates[0] + ", " + itemResult.point.coordinates[1]);
        }
    }

    document.write("<script type='text/javascript' src='https://www.bing.com/api/maps/mapcontrol?callback=initMap' async defer></script>")
    getLocation($('#street').val() + ", " + $('#city').val() + ", " + $('#region').val());

    $('#mapSearch').click(function () {
        $.ajax({
            url: "https://dev.virtualearth.net/REST/v1/Locations",
            dataType: "jsonp",
            data: {
                key: "Aq3VH9W38vBJyU8wwZDwtshVNWWE8bcyoKOaTkvqH4cTCJU2ep7EB-z5AKrxAMn2",
                q: $('#location').val()//+"&locality="+ $('#city').val() +"&adminDistrict=" +$('#region').val()
            },
            jsonp: "jsonp",
            success: function (data) {
                var result = data.resourceSets[0];
                if (result) {
                    if (result.estimatedTotal > 0) {
                        var i =0;
                        $.map(result.resources, function (item) {
                            if (i == 0) {
                                initMapOnClick(item);
                                itemResult1 = item;
                            }
                            i = i +1 ;
                        });
                    }
                }
            }
        });

        function initMapOnClick(item) {
            var map = new Microsoft.Maps.Map(document.getElementById('myMap'), {
                credentials: 'Aq3VH9W38vBJyU8wwZDwtshVNWWE8bcyoKOaTkvqH4cTCJU2ep7EB-z5AKrxAMn2',
                center: (item !== undefined ? new Microsoft.Maps.Location(item.point.coordinates[0], item.point.coordinates[1]) : new Microsoft.Maps.Location(41.327953, 19.819025))
            });
            var pushpin = new Microsoft.Maps.Pushpin(map.getCenter(), {
                color: '#f00',
                draggable: false
            });
            map.entities.push(pushpin);
            if (item !== undefined) {
                //console.log(itemResult.name);
                $('#location').val(item.name.toString());
                $('#printoutPanel').val(item.point.coordinates[0] + ", " + item.point.coordinates[1]);
            }
        }
    });
}


$('#mapProperty, #createProd').click(function () {
    $('body').append('<div style="" id="loadingDiv"><div class="loader"></div></div>');
});
$('#mapRoom0').on('click',function () {
    $('body').append('<div style="" id="loadingDiv"><div class="loader"></div></div>');
});

$('#updateRoom').click(function (){
    var qty = $('#qty').val();
    if(qty == "") {
      $('#msg').show();
    }
});

$('#qty').on( 'keyup change', function () {
    $('#msg').hide();
});













