var jogadorVez, ws, usuario;

function coordinates(cell) {
    var dc = cell.cellIndex;
    var dr = cell.parentNode.rowIndex;
    return [dr, dc];
}

function drop(ev) {
    //ev.preventDefault();
    ws.send(ev);
}

function inserirElemento(){
    var espacos = document.getElementsByTagName("td");

    for (var i = 0; i < espacos.length; i++) {
        espacos[i].addEventListener("click", function (e) {
            console.log(e);
            if (this.getElementsByTagName("img").length === 0) {
                if(jogadorVez === usuario){
                   this.innerHTML = "<img src='img/x.png'  height='50' alt=''>";
                    drop(e.target.id);
                    
                }
                else {
                    this.innerHTML = "<img src='img/o.png' height='50' alt=''>";
                    drop(e.target.id);
                
                }
            }
            }
    )};
 }

function setMessage(mensagem){
    var resultado = document.getElementById("resultado");
    resultado.innerHTML = mensagem;
}

function onMessage(evt){
    var msg = JSON.parse(evt.data);
    switch (msg.type) {
        case 0:
            /* Informando o jogador atual */
            usuario = msg.jogador;
            break;
        case 1:
            /* Recebendo o tabuleiro modificado */
            jogadorVez = msg.turn;
            setMessage((jogadorVez === usuario) ? "É a sua vez de jogar." : "É a vez do adversário de jogar.");
            montarTabela(msg.tabuleiro);
            break;
        case 2:
            /* Fim do jogo */
            setMessage(msg.message);
            ws.close();
            break;
    }
}

function montarTabela(tabuleiro) {
        console.log(tabuleiro);
    var table = document.getElementsByTagName("table")[0];
    tabuleiro.forEach(function (row, rowIndex) {
        row.forEach(function (col, colIndex) {
            var cell = table.rows[rowIndex].cells[colIndex];
            cell.id = `${rowIndex}_${colIndex}`;
            
            cell.innerHTML = (col === 0 ? "" :  (col === 1 ? '<img src="img/x.png" height="50" alt="">' : '<img src="img/o.png" height="50" alt="">' ));
            
           
            cell.ondrop = drop;
        });
        
    });
    inserirElemento();
}

window.onload = function(){
   ws = new WebSocket("ws://" + document.location.host + document.location.pathname + "jogoVelha");
   ws.onmessage = onMessage;
};


