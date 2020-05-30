package fr.isen.audranmeyrignac.androidtoolbox



data class outputAPI (
    var displayName: String? = null,
    var displayGender: String? = null,
    var displayMail: String? = null,
    var displayPictureMedium : String? = null,
    var displayPictureLarge : String? = null,
    var displayAge : String? = null,
    var displayDob : String? = null,

    var displayStreet : String? = null,
    var displayCity : String? = null,
    var displayState : String? = null,
    var displayCountry : String? = null,
    var displayPostCode : String? = null,
    var displayCoordinates : String? = null,
    var displayTimezone : String? = null,

    var displayCell : String? = null,
    var displayPhone : String? = null,

    var displayNat : String? = null,

    var displayDateRegistration : String? = null
)