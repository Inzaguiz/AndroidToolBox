package fr.isen.audranmeyrignac.androidtoolbox

data class Result (
    var results : ArrayList<PersonClass> ?= null
)

data class PersonClass (
    var gender : String ?= null,
    var name : NameClass ?= null,
    var location : LocationClass ?= null,
    var email : String ?= null,
    var dob : BirthClass ?= null,
    var registered : RegisteredClass ?= null,
    var phone : String ?= null,
    var cell : String ?= null,
    var picture : PictureClass ?= null,
    var nat : String ?= null
)

data class NameClass (
    var first : String ?= null,
    var last : String ?= null
)

data class LocationClass (
    var street : StreetClass ?= null,
    var city : String ?= null,
    var state : String ?= null,
    var country : String ?= null,
    var postcode : String ?= null,
    var coordinates : CoordinatesClass ?= null,
    var timezone : TimezoneClass ?= null
)

data class CoordinatesClass (
    var latitude : String ?= null,
    var longitude : String ?= null
)

data class TimezoneClass (
    var offset : String ?= null,
    var description : String ?= null
)

data class StreetClass (
    var number : String ?= null,
    var nameStreet : String ?= null
)

data class BirthClass (
    var date : String ?= null,
    var age : String ?= null
)

data class RegisteredClass (
    var date : String ?= null,
    var age : String ?= null
)

data class PictureClass (
    var large : String ?= null,
    var medium : String ?= null
)