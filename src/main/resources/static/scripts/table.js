function registerClickableTableRow() {
    const rows = document.querySelectorAll('.clickable-row');

    rows.forEach(row => {
        row.addEventListener('click', () => {
            const href = row.dataset.href;
            if (href) {
                window.location.href = href;
            }
        });
    });
}