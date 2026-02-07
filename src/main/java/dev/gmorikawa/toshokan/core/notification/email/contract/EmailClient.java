package dev.gmorikawa.toshokan.core.notification.email.contract;

import dev.gmorikawa.toshokan.core.notification.email.Email;

public interface EmailClient {
    <Data> boolean send(Email<Data> email);
}
