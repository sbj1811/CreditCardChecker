# CreditCardChecker

Checks the validity of a credit card entered.

Supports: American Express, Visa, Mastercard, and Discover

## Features

* Checks for provider based CCN digit lengths and formats with Luhn validation.
* Checks for provider based CVV length
* If the data is incorrect, it will notify user.
* Supported platfome: Jelly bean MR1 (SDK 17)

## Design decisions made :
* MVVM architehture 
* Spinners to select Expiry date This will also avoid format errors.