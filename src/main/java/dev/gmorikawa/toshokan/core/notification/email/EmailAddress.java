package dev.gmorikawa.toshokan.core.notification.email;

import dev.gmorikawa.toshokan.core.notification.email.exception.InvalidEmailAddressException;

public class EmailAddress {
    private final String address;

    public EmailAddress(String address) {
        if (!EmailAddress.validate(address)) {
            throw new InvalidEmailAddressException();
        }

        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return address;
    }

    private static boolean validate(String address) {
        return address != null && address.contains("@");
    }
}
