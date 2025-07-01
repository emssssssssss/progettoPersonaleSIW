
// Imposta dinamicamente il massimo valore dei posti
document.addEventListener('DOMContentLoaded', () => {
    const selectFascia = document.getElementById('fasciaId');
    const inputPosti = document.getElementById('numeroPersone');

    function aggiornaMax() {
        const opzione = selectFascia.selectedOptions[0];
        const maxDisponibili = opzione.getAttribute('data-posti-disponibili');
        inputPosti.max = maxDisponibili;
    }

    selectFascia.addEventListener('change', aggiornaMax);
    aggiornaMax(); // inizializzazione
});

document.addEventListener('DOMContentLoaded', () => {
    const selectFascia = document.getElementById('fasciaId');
    const spanPosti = document.getElementById('postiValue');

    function aggiornaPostiDisponibili() {
        const selectedOption = selectFascia.options[selectFascia.selectedIndex];
        const posti = selectedOption.getAttribute('data-posti-disponibili');
        spanPosti.textContent = posti ? posti : 'Seleziona una fascia';
    }

    aggiornaPostiDisponibili();
    selectFascia.addEventListener('change', aggiornaPostiDisponibili);
});
