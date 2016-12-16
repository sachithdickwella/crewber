var map2;
function initAutocomplete3() {
     map2 = new google.maps.Map(document.getElementById('map3'), {
        center: {lat: 6.912708758438757, lng: 79.8621940612793},
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    
//    map.updateMap();


    // Create the search box and link it to the UI element.
    var input = document.getElementById('pac-input');
    var searchBox = new google.maps.places.SearchBox(input);
    map2.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

//    add click event listner
    map2.addListener('click', function (e) {

//        document.getElementById('it_user_rg_coordinates').value = e.latLng.lat() + ', ' + e.latLng.lng();

    });

    // Bias the SearchBox results towards current map's viewport.
    map2.addListener('bounds_changed', function () {
        searchBox.setBounds(map2.getBounds());
    });

    var markers = [];
    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener('places_changed', function () {
        var places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }

        // Clear out the old markers.
        markers.forEach(function (marker) {
            marker.setMap(null);
        });
        markers = [];

        // For each place, get the icon, name and location.
        var bounds = new google.maps.LatLngBounds();
        places.forEach(function (place) {
            var icon = {
                url: place.icon,
                size: new google.maps.Size(71, 71),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(17, 34),
                scaledSize: new google.maps.Size(25, 25)
            };
        
            // Create a marker for each place.
            markers.push(new google.maps.Marker({
                map: map2,
                icon: icon,
                title: place.name,
                position: place.geometry.location
            }));

            if (place.geometry.viewport) {
                // Only geocodes have viewport.
                bounds.union(place.geometry.viewport);
            } else {
                bounds.extend(place.geometry.location);
            }
        });
        map2.fitBounds(bounds);
        

    });


}
