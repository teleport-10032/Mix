/*----------------------------------------------------*/
/*  Google map js
/*----------------------------------------------------*/

if ($("#mapBox").length) {
  var $lat = $("#mapBox").data("lat");
  var $lon = $("#mapBox").data("lon");
  var $zoom = $("#mapBox").data("zoom");
  var $marker = $("#mapBox").data("marker");
  var $info = $("#mapBox").data("info");
  var $markerLat = $("#mapBox").data("mlat");
  var $markerLon = $("#mapBox").data("mlon");
  var map = new GMaps({
    el: "#mapBox",
    lat: $lat,
    lng: $lon,
    scrollwheel: false,
    scaleControl: true,
    streetViewControl: false,
    panControl: true,
    disableDoubleClickZoom: true,
    mapTypeControl: false,
    zoom: $zoom,
    styles: [
      {
        featureType: "water",
        elementType: "geometry.fill",
        stylers: [
          {
            color: "#d3d3d3",
          },
        ],
      },
      {
        featureType: "transit",
        stylers: [
          {
            color: "#808080",
          },
          {
            visibility: "off",
          },
        ],
      },
      {
        featureType: "road.highway",
        elementType: "geometry.stroke",
        stylers: [
          {
            visibility: "on",
          },
          {
            color: "#b3b3b3",
          },
        ],
      },
      {
        featureType: "road.highway",
        elementType: "geometry.fill",
        stylers: [
          {
            color: "#ffffff",
          },
        ],
      },
      {
        featureType: "road.local",
        elementType: "geometry.fill",
        stylers: [
          {
            visibility: "on",
          },
          {
            color: "#ffffff",
          },
          {
            weight: 1.8,
          },
        ],
      },
      {
        featureType: "road.local",
        elementType: "geometry.stroke",
        stylers: [
          {
            color: "#d7d7d7",
          },
        ],
      },
      {
        featureType: "poi",
        elementType: "geometry.fill",
        stylers: [
          {
            visibility: "on",
          },
          {
            color: "#ebebeb",
          },
        ],
      },
      {
        featureType: "administrative",
        elementType: "geometry",
        stylers: [
          {
            color: "#a7a7a7",
          },
        ],
      },
      {
        featureType: "road.arterial",
        elementType: "geometry.fill",
        stylers: [
          {
            color: "#ffffff",
          },
        ],
      },
      {
        featureType: "road.arterial",
        elementType: "geometry.fill",
        stylers: [
          {
            color: "#ffffff",
          },
        ],
      },
      {
        featureType: "landscape",
        elementType: "geometry.fill",
        stylers: [
          {
            visibility: "on",
          },
          {
            color: "#efefef",
          },
        ],
      },
      {
        featureType: "road",
        elementType: "labels.text.fill",
        stylers: [
          {
            color: "#696969",
          },
        ],
      },
      {
        featureType: "administrative",
        elementType: "labels.text.fill",
        stylers: [
          {
            visibility: "on",
          },
          {
            color: "#737373",
          },
        ],
      },
      {
        featureType: "poi",
        elementType: "labels.icon",
        stylers: [
          {
            visibility: "off",
          },
        ],
      },
      {
        featureType: "poi",
        elementType: "labels",
        stylers: [
          {
            visibility: "off",
          },
        ],
      },
      {
        featureType: "road.arterial",
        elementType: "geometry.stroke",
        stylers: [
          {
            color: "#d6d6d6",
          },
        ],
      },
      {
        featureType: "road",
        elementType: "labels.icon",
        stylers: [
          {
            visibility: "off",
          },
        ],
      },
      {},
      {
        featureType: "poi",
        elementType: "geometry.fill",
        stylers: [
          {
            color: "#dadada",
          },
        ],
      },
    ],
  });

  map.addMarker({
    lat: $markerLat,
    lng: $markerLon,
    icon: $marker,
    infoWindow: {
      content: $info,
    },
  });
}

/*----------------------------------------------------*/
/*  Google map js
/*----------------------------------------------------*/

if ($("#mapBox2").length) {
  var $lat = $("#mapBox2").data("lat");
  var $lon = $("#mapBox2").data("lon");
  var $zoom = $("#mapBox2").data("zoom");
  var $marker = $("#mapBox2").data("marker");
  var $info = $("#mapBox2").data("info");
  var $markerLat = $("#mapBox2").data("mlat");
  var $markerLon = $("#mapBox2").data("mlon");
  var map = new GMaps({
    el: "#mapBox2",
    lat: $lat,
    lng: $lon,
    scrollwheel: false,
    scaleControl: true,
    streetViewControl: false,
    panControl: true,
    disableDoubleClickZoom: true,
    mapTypeControl: false,
    zoom: $zoom,
    styles: [
      {
        featureType: "administrative",
        elementType: "labels.text.fill",
        stylers: [
          {
            color: "#444444",
          },
        ],
      },
      {
        featureType: "landscape",
        elementType: "all",
        stylers: [
          {
            color: "#f2f2f2",
          },
        ],
      },
      {
        featureType: "poi",
        elementType: "all",
        stylers: [
          {
            visibility: "off",
          },
        ],
      },
      {
        featureType: "road",
        elementType: "all",
        stylers: [
          {
            saturation: -100,
          },
          {
            lightness: 45,
          },
        ],
      },
      {
        featureType: "road.highway",
        elementType: "all",
        stylers: [
          {
            visibility: "simplified",
          },
        ],
      },
      {
        featureType: "road.arterial",
        elementType: "labels.icon",
        stylers: [
          {
            visibility: "off",
          },
        ],
      },
      {
        featureType: "transit",
        elementType: "all",
        stylers: [
          {
            visibility: "off",
          },
        ],
      },
      {
        featureType: "water",
        elementType: "all",
        stylers: [
          {
            color: "#d3ed34",
          },
          {
            visibility: "on",
          },
        ],
      },
    ],
  });

  map.addMarker({
    lat: $markerLat,
    lng: $markerLon,
    icon: $marker,
    infoWindow: {
      content: $info,
    },
  });
}

if ($("#mapBox3").length) {
  var $lat = $("#mapBox3").data("lat");
  var $lon = $("#mapBox3").data("lon");
  var $zoom = $("#mapBox3").data("zoom");
  var $marker = $("#mapBox3").data("marker");
  var $info = $("#mapBox3").data("info");
  var $markerLat = $("#mapBox3").data("mlat");
  var $markerLon = $("#mapBox3").data("mlon");
  var map = new GMaps({
    el: "#mapBox3",
    lat: $lat,
    lng: $lon,
    scrollwheel: false,
    scaleControl: true,
    streetViewControl: false,
    panControl: true,
    disableDoubleClickZoom: true,
    mapTypeControl: false,
    zoom: $zoom,
    styles: [
      {
        featureType: "all",
        elementType: "labels.text.fill",
        stylers: [
          {
            saturation: 36,
          },
          {
            color: "#465166",
          },
          {
            lightness: 40,
          },
        ],
      },
      {
        featureType: "all",
        elementType: "labels.text.stroke",
        stylers: [
          {
            visibility: "on",
          },
          {
            color: "#3a4459",
          },
          {
            lightness: 16,
          },
        ],
      },
      {
        featureType: "all",
        elementType: "labels.icon",
        stylers: [
          {
            visibility: "off",
          },
        ],
      },
      {
        featureType: "administrative",
        elementType: "geometry.fill",
        stylers: [
          {
            color: "#3a4459",
          },
          {
            lightness: 5,
          },
        ],
      },
      {
        featureType: "administrative",
        elementType: "geometry.stroke",
        stylers: [
          {
            color: "#3a4459",
          },
          {
            lightness: 5,
          },
          {
            weight: 1.2,
          },
        ],
      },
      {
        featureType: "landscape",
        elementType: "geometry",
        stylers: [
          {
            color: "#3a4459",
          },
          {
            lightness: 19,
          },
        ],
      },
      {
        featureType: "poi",
        elementType: "geometry",
        stylers: [
          {
            color: "#3a4459",
          },
          {
            lightness: 15,
          },
        ],
      },
      {
        featureType: "road.highway",
        elementType: "geometry.fill",
        stylers: [
          {
            color: "#394358",
          },
          {
            lightness: 10,
          },
        ],
      },
      {
        featureType: "road.highway",
        elementType: "geometry.stroke",
        stylers: [
          {
            color: "#394358",
          },
          {
            lightness: 29,
          },
          {
            weight: 0.2,
          },
        ],
      },
      {
        featureType: "road.arterial",
        elementType: "geometry",
        stylers: [
          {
            color: "#394358",
          },
          {
            lightness: 18,
          },
        ],
      },
      {
        featureType: "road.local",
        elementType: "geometry",
        stylers: [
          {
            color: "#3a4459",
          },
          {
            lightness: 16,
          },
        ],
      },
      {
        featureType: "transit",
        elementType: "geometry",
        stylers: [
          {
            color: "#3a4459",
          },
          {
            lightness: 19,
          },
        ],
      },
      {
        featureType: "water",
        elementType: "geometry",
        stylers: [
          {
            color: "#394358",
          },
          {
            lightness: 10,
          },
        ],
      },
    ],
  });

  map.addMarker({
    lat: $markerLat,
    lng: $markerLon,
    icon: $marker,
    infoWindow: {
      content: $info,
    },
  });
}

if ($("#mapBox4").length) {
  var $lat = $("#mapBox4").data("lat");
  var $lon = $("#mapBox4").data("lon");
  var $zoom = $("#mapBox4").data("zoom");
  var $marker = $("#mapBox4").data("marker");
  var $info = $("#mapBox4").data("info");
  var $markerLat = $("#mapBox4").data("mlat");
  var $markerLon = $("#mapBox4").data("mlon");
  var map = new GMaps({
    el: "#mapBox4",
    lat: $lat,
    lng: $lon,
    scrollwheel: false,
    scaleControl: true,
    streetViewControl: false,
    panControl: true,
    disableDoubleClickZoom: true,
    mapTypeControl: false,
    zoom: $zoom,
    styles: [
      {
        featureType: "administrative",
        elementType: "labels.text.fill",
        stylers: [
          {
            color: "#444444",
          },
        ],
      },
      {
        featureType: "landscape",
        elementType: "all",
        stylers: [
          {
            color: "#f2f2f2",
          },
        ],
      },
      {
        featureType: "poi",
        elementType: "all",
        stylers: [
          {
            visibility: "off",
          },
        ],
      },
      {
        featureType: "road",
        elementType: "all",
        stylers: [
          {
            saturation: -100,
          },
          {
            lightness: 45,
          },
        ],
      },
      {
        featureType: "road.highway",
        elementType: "all",
        stylers: [
          {
            visibility: "simplified",
          },
        ],
      },
      {
        featureType: "road.arterial",
        elementType: "labels.icon",
        stylers: [
          {
            visibility: "off",
          },
        ],
      },
      {
        featureType: "transit",
        elementType: "all",
        stylers: [
          {
            visibility: "off",
          },
        ],
      },
      {
        featureType: "water",
        elementType: "all",
        stylers: [
          {
            color: "#46bcec",
          },
          {
            visibility: "on",
          },
        ],
      },
    ],
  });

  map.addMarker({
    lat: $markerLat,
    lng: $markerLon,
    icon: $marker,
    infoWindow: {
      content: $info,
    },
  });
}

if ($("#mapBox5").length) {
  var $lat = $("#mapBox5").data("lat");
  var $lon = $("#mapBox5").data("lon");
  var $zoom = $("#mapBox5").data("zoom");
  var $marker = $("#mapBox5").data("marker");
  var $info = $("#mapBox5").data("info");
  var $markerLat = $("#mapBox5").data("mlat");
  var $markerLon = $("#mapBox5").data("mlon");
  var map = new GMaps({
    el: "#mapBox5",
    lat: $lat,
    lng: $lon,
    scrollwheel: false,
    scaleControl: true,
    streetViewControl: false,
    panControl: true,
    disableDoubleClickZoom: true,
    mapTypeControl: false,
    zoom: $zoom,
    styles: [
      {
        featureType: "administrative",
        elementType: "labels.text.fill",
        stylers: [
          {
            color: "#444444",
          },
        ],
      },
      {
        featureType: "landscape",
        elementType: "all",
        stylers: [
          {
            color: "#f2f2f2",
          },
        ],
      },
      {
        featureType: "poi",
        elementType: "all",
        stylers: [
          {
            visibility: "off",
          },
        ],
      },
      {
        featureType: "road",
        elementType: "all",
        stylers: [
          {
            saturation: -100,
          },
          {
            lightness: 45,
          },
        ],
      },
      {
        featureType: "road.highway",
        elementType: "all",
        stylers: [
          {
            visibility: "simplified",
          },
        ],
      },
      {
        featureType: "road.arterial",
        elementType: "labels.icon",
        stylers: [
          {
            visibility: "off",
          },
        ],
      },
      {
        featureType: "transit",
        elementType: "all",
        stylers: [
          {
            visibility: "off",
          },
        ],
      },
      {
        featureType: "water",
        elementType: "all",
        stylers: [
          {
            color: "#46bcec",
          },
          {
            visibility: "on",
          },
        ],
      },
    ],
  });

  map.addMarker({
    lat: $markerLat,
    lng: $markerLon,
    icon: $marker,
    infoWindow: {
      content: $info,
    },
  });
}
