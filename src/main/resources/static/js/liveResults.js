
async function loadResults() {
    const response = await fetch("/api/results");
    const results = await response.json();
    const table = document.getElementById("resultsTable");
    table.innerHTML = "<tr><th>Candidate</th><th>Votes</th></tr>";
    results.forEach(r => {
        const row = `<tr><td>${r.name}</td><td>${r.voteCount}</td></tr>`;
        table.innerHTML += row;
    });
}
setInterval(loadResults, 2000); // Refresh every 2 seconds
loadResults();
