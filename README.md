# CreditCardChecker

Checks the validity of a credit card.


## Features

* Supports: American Express, Visa, Master Card, and Discover
* Checks for ISSUED provider based CCN digit lengths and formats with Luhn validation.
* Checks for provider based CVV length
* If the data is incorrect, it will notify user.
* Displays card provider logo once card type is detected.
* Supported platform: Jelly bean MR1 (SDK 17)
* Supports phone and tablet screen sizes.

## Design decisions:
* MVVM architecture with Android Data binding - Bindings make UI updates easier to handle and components can be reused.
* User notification delivered via Toast.
* Spinners to select Expiry date - This will also avoid format errors.
* CVV info displayed via Dialog so that it can be dismissed to go back to filling the details.
* No local storage of data for user data security. 
* Tested on Android Oreo and Marshmallow physical devices, and Lollipop emulator.

## Examples:
 
    "3379513561108795" -> Valid but not issued
    "371449635398431"  -> Valid and issued by AMEX
    "6011111111111117" -> Valid and issued by Discover
    "5555555555554444" -> Valid and issued by Master Card
    "4111111111111111" -> Valid and issued by Visa

![CCC App][CCC-app]

[CCC-app]: ./media/app_banner.jpg