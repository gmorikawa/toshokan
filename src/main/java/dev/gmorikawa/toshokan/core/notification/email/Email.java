package dev.gmorikawa.toshokan.core.notification.email;

public class Email<Data> {
    private final EmailAddress recipient;
    private final String subject;
    private final Data data;

    public Email(
        EmailAddress recipient,
        String subject,
        Data data
    ) {
        this.recipient = recipient;
        this.subject = subject;
        this.data = data;
    }

    public EmailAddress getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public Data getData() {
        return data;
    }
}