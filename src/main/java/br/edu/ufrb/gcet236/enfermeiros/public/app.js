function start() {
  requestPOST();
  requestPOST();
  requestListarAlunos();
}

var itensSelecionadosListGroup = [];
var ultimoItemClicado;
//configurarSelecaoDosItensListGroupClick();
configurarSelecaoInicialDosItensListGroup();
configurarBtnDesselecionar();
configurarBtnSelecionarTudo();

function configurarSelecaoInicialDosItensListGroup() {
  $('.list-group').on('contextmenu', '.list-group-item', function (event) {
    event.preventDefault();
    ultimoItemClicado = $(this);
    //console.log(mes)

    if (this.classList.contains('active')) {
      $(this).removeClass('active');
      itensSelecionadosListGroup.filter(removerItemSelecionado);
    } else {
      $(this).addClass('active'); //.siblings().removeClass('active');
      itensSelecionadosListGroup.push($(this));
    }
    mudarEstadosDaInterfaceNaSelecao(itensSelecionadosListGroup.length);
  });
}

function configurarBtnDesselecionar() {
   $('#desselecionarTudoBtn').on('click', function (event) {
     event.preventDefault();
     ultimoItemClicado.siblings().removeClass('active');
     ultimoItemClicado.removeClass('active');
     mudarEstadosDaInterfaceNaSelecao(0);
     itensSelecionadosListGroup = [];
     escolherFunc();
   });
 }

 function configurarBtnSelecionarTudo() {
   $('#selecionarTudoBtn').on('click', function (e) {
     var id = itensSelecionadosListGroup[0].attr('id');
     console.log(id);
     itensSelecionadosListGroup = [];
     itensSelecionadosListGroup.push($(`#${id}`));
     itensSelecionadosListGroup[0].siblings().addClass('active');
     itensSelecionadosListGroup[0].siblings().each(function (index, element) {
      itensSelecionadosListGroup.push($(`#${element.id}`));
     });
     mudarEstadosDaInterfaceNaSelecao(itensSelecionadosListGroup.length);
   });
 }

function getCpfISLG(i) {
  return i.find('label')[1].innerHTML;
}

function removerItemSelecionado(value, index, arr) {
  // If the value at the current array index matches the specified value (2)
  if (getCpfISLG(value) === getCpfISLG(ultimoItemClicado)) {
    // Removes the value from the original array
    
    arr.splice(index, 1);
    return true;
  }
  return false;
}
function mudarEstadosDaInterfaceNaSelecao(n) {
  var buttonsExtra = document.querySelectorAll('.buttonsExtra');
  var buttonsDiminuir = document.querySelectorAll('.bd');
  var buttonsAumentar = document.querySelectorAll('.ba');
  var buttonsFrequencia = document.querySelectorAll('.bf');

  if (n == 0) {
    //Caso barra de pesquisa esteja preechida
    ativacao = true;
    if (document.getElementById('searchbar').value != '') {
      escolherFunc();
      configurarBtnToShearch();
      buttonsExtra[2].hidden = !ativacao;
    } else {
      navBarTitulo.innerHTML = 'Enfermeiros';
    }
  } else if (n == 1) {
    navBarTitulo.innerHTML = `${n} enfermeiro`;
    ativacao = false;
  }
  
  for (b = 0; b < buttonsExtra.length; b++) {
    buttonsExtra[b].hidden = ativacao;
  }
  
  addAluno.hidden = !ativacao;

  if (n > 1) {
    buttonsExtra[1].hidden = !ativacao;
    navBarTitulo.innerHTML = `${n} enfermeiros`;
  }
}

function requestListarAlunos() {
  console.log('Função Get foi ativada');

  const response = fetch('http://localhost:8080/api/listar_enfermeiros')
    .then(function (responseData) {
      return responseData.json();
    })
    .then(function (jsonData) {
      const enfermeirosLista = document.getElementById('enfermeirosLista');

      const items = jsonData.map(doc => {
        //querySnapshot.docs.map(doc => {

        return `<a href="#" id=${doc.cpf}  
         class="list-group-item list-group-item-action flex-column align-items-start">
         <!--Dados aluno-->
         <div style="float: left; margin-left: 10px;">
             <!--NOME-->
             <span class="material-symbols-outlined">person</span>
             <label " class= "user name NOME" style="vertical-align: top; max-width: 350px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${doc.nome}</label>
             <br>

             <!--CPF-->
             <span class="material-symbols-outlined">money</span>
             <label class= "user matricula FREQ" style="vertical-align: top;">CPF:</label>
             <label class= "user matricula FREQ" style="vertical-align: top;">${doc.cpf}</label>
             <br>
             
             <!--RG-->
             <span class="material-symbols-outlined">pin</span>
             <label class= "user matricula FREQ" style="vertical-align: top;">RG:</label>
             <label class= "user matricula FREQ" style="vertical-align: top;">${doc.rg}</label>
         </div>
     
         <div style="float: right; margin-right: 10px;">
             <br>

             <!--LOTAÇÃO-->
             <span class="material-symbols-outlined">location_on</span>
             <label class= "user matricula FREQ" style="vertical-align: top;">${doc.lotação}</label>
             <br>

             <!--TELEFONE-->
             <span class="material-symbols-outlined">call</span>
             <label class= "user matricula FREQ" style="vertical-align: top;">${doc.telefone}</label>
         </div>
     </a>`;
      });

      enfermeirosLista.innerHTML = items.join('');
    })
    .catch(function (e) {
      console.log('Erro: ' + e);
    });
}

function requestCadastrarAluno() {
  var nome = document.getElementById('nomeAdicionarEnfermeiroInput').value;
  var cpf = document.getElementById('cpfAdicionarEnfermeiroInput').value;
  var rg = document.getElementById('rgAdicionarEnfermeiroInput').value;
  var lotacao = document.getElementById(
    'lotacaoAdicionarEnfermeiroInput'
  ).value;
  var telefone = document.getElementById(
    'telefoneAdicionarEnfermeiroInput'
  ).value;

  const headers = {
    'Content-Type': 'application/json',
  };

  const init = {
    method: 'POST',
    headers: headers,
    body: JSON.stringify({
      nome: nome,
      telefone: telefone,
      rg: rg,
      cpf: cpf,
      lotação: lotacao,
    }),
  };

  const response = fetch('http://localhost:8080/api/cadastrar', init)
    .then(function (responseData) {
      return responseData.json();
    })
    .then(function (jsonData) {
      console.log(jsonData);
      requestListarAlunos();
      return jsonData;
    })
    .catch(function (e) {
      console.log('Erro: ' + e);
    });
}

function requestPOST() {
  console.log('Função POST foi ativadaaaa');
  const headers = {
    'Content-Type': 'application/json',
  };
  const init = {
    method: 'POST',
    headers: headers,
    body: JSON.stringify({
      nome: 'Fulano',
      telefone: '75999999999',
      rg: '1234567',
      cpf: '12345678910',
      lotação: 'Apartamentos',
    }),
  };
  const response = fetch('http://localhost:8080/api/cadastrar', init)
    .then(function (responseData) {
      return responseData.json();
    })
    .then(function (jsonData) {
      console.log(jsonData);
      return jsonData;
    })
    .catch(function (e) {
      console.log('Erro: ' + e);
    });
}
