# Email Client

## Overview

This application sends email address for user confirmation, password recovery and other notifications. It sends messages over SMTP.

## Environment Variables

### EMAIL_HOST

* __Type__: _string_.
* __Description__: SMTP server from which the message will be sent.

### EMAIL_PORT

* __Type__: _int_.
* __Description__: Host's port. Usually 465 (SSL) or 587 (TLS).

### EMAIL_USERNAME

* __Type__: _string_.
* __Description__: Valid email address from the __EMAIL_HOST__.

### EMAIL_PASSWORD

* __Type__: _string_.
* __Description__: Password to authenticate __EMAIL_USERNAME__ on __EMAIL_HOST__.

## Configuration

### Gmail

To use your own gmail account to send emails you can use the host `smtp.gmail.com` with port `587`. The password does not have to be your email sign-in password. You can generate another password for application authentication only.

Google requires an app password when you have 2-Step Verification enabled. Use the steps below to create one and set it as `EMAIL_PASSWORD`.

1. Sign in to your Google Account at [myaccount.google.com](https://myaccount.google.com/).
2. Go to Security and make sure 2-Step Verification is enabled.
3. In the Security section, open App passwords.
4. For “Select app”, choose Mail. For “Select device”, choose Other (Custom name) and enter a name like “Toshokan”.
5. Click Generate and copy the 16-character app password.
6. Use that app password as the value for `EMAIL_PASSWORD`.

If App passwords is not visible, confirm that 2-Step Verification is enabled and that the account is not managed by an organization that blocks app passwords.
