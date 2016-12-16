/* global google */
var map;
var markers;
var searchBox;
function initAutocomplete() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 6.912708758438757, lng: 79.8621940612793},
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    // Create the search box and link it to the UI element.
    var input = document.getElementById('pac-input');
    searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

//    add click event listner
    map.addListener('click', function (e) {
        alert('clicked');
        document.getElementById('it_user_rg_coordinates').value = e.latLng.lat() + ', ' + e.latLng.lng();

    });

    // Bias the SearchBox results towards current map's viewport.
    map.addListener('bounds_changed', function () {
        searchBox.setBounds(map.getBounds());
    });

    markers = [];
    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener('places_changed', function () {
        var places = searchBox.getPlaces();

        if (places.length === 0) {
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
                map: map,
                icon: icon,
                title: place.name,
                draggable: true,
                animation: google.maps.Animation.DROP,
                position: place.geometry.location
            }));

            if (place.geometry.viewport) {
                // Only geocodes have viewport.
                bounds.union(place.geometry.viewport);
            } else {
                bounds.extend(place.geometry.location);
            }
        });
        map.fitBounds(bounds);
    });
}

function resetMap() {

    var center = {lat: 6.912708758438757, lng: 79.8621940612793};
    map.setZoom(13);
    map.setCenter(center);

    // Clear out the old markers.
    markers.forEach(function (marker) {
        marker.setMap(null);
    });
    markers = [];

}