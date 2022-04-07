const cep = document.querySelector("#cep")

const showData = (resultado)=>{
    for(const campo in resultado) {
        if(document.querySelector("#"+campo)){
            document.querySelector("#"+campo).value = resultado[campo]
        }
    }
}

cep.addEventListener("blur", (e)=>{  // o blur serve para que, assim que a pessoa tirar o foco, eu quero saber o que foi digitado
    let pesquisa = cep.value.replace("-", "") // procure o traço, e se encontrar, troque por nada (nesse caso vai retirar o traço)
    const opcoes = {
        method: 'GET',
        mode: 'cors',
        cache: 'default'
    }
    fetch(`https://viacep.com.br/ws/${pesquisa}/json/`, opcoes) // pega o CEP e joga na URL
    .then(response=>{ response.json() // se der certo
        .then(data => showData(data))
    })
    .catch(e => console.log('Deu Erro: ' + e,message)) // se der errado

   
})