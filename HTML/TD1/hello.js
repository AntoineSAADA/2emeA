let d2 = document.getElementById("d2");
d2.style.display = "none";


function valider() {
    const login = document.getElementById('login').value;
    const email = document.getElementById('mail').value;
    const password = document.getElementById('pw').value;
    const datenaiss = document.getElementById('datenaiss').value;
    const numero = document.getElementById('tel').value;
    const togg1 = document.getElementById('togg1')
    const dseize = new Date();
    dseize.setFullYear(dseize.getFullYear()-16);

    var erreur = '';



    console.log('debut des tests')
    
    if (login.length < 2) {
        erreur += 'Le login est trop court';
    }

    else if (email.indexOf('@') == -1 || email.indexOf('.') == -1) {
        erreur += 'L\'email n\'est pas valide';
    }

    else if (password.length < 6) {
        erreur += 'Le mot de passe est trop court';
    }
    // verifier si le mot de passe ne contient pas de chiffre
    else if (password == password.replace(/[0-9]/g, '')) {
        erreur += 'Le mot de passe doit contenir au moins un chiffre';
    }


    else if (numero.length < 10 || numero.length > 10) {
        erreur += 'Le numéro de téléphone n\'est pas valide, il n\'est pas composé de 10 chiffres';
    }

    // vérifier que le numéro ne comporte pas de lettres 
    else if (numero != numero.replace(/[a-z]/g, '')) {
        erreur += 'Le numéro de téléphone n\'est pas valide, il contient au moins une lettre';
    }

    // verifier que l'utilisateur est bien majeur 
    else if(new Date(datenaiss) > dseize) {
        erreur += 'Vous devez avoir au moins 16 ans';
    }

    togg1.addEventListener("click", () => {
        affichage.hidden = false
    })

    if (erreur == '') {
        d2.style.display = "block";
        return true;
    }
    else {
        alert(erreur);
        return false;
    }
}



// réalisation d'un questionnaire 

const Questions = [{
    id: 0,
    q: "What is capital of India?",
    a: [{ text: "gandhinagar", isCorrect: false },
        { text: "Surat", isCorrect: false },
        { text: "Delhi", isCorrect: true },
        { text: "mumbai", isCorrect: false }
    ]

},
{
    id: 1,
    q: "What is the capital of Thailand?",
    a: [{ text: "Lampang", isCorrect: false, isSelected: false },
        { text: "phuket", isCorrect: false },
        { text: "Ayutthaya", isCorrect: false },
        { text: "Bangkok", isCorrect: true }
    ]

},
{
    id: 2,
    q: "What is the capital of Gujarat",
    a: [{ text: "surat", isCorrect: false },
        { text: "vadodara", isCorrect: false },
        { text: "gandhinagar", isCorrect: true },
        { text: "rajkot", isCorrect: false }
    ]

}]


var start = true;

// Iterate
function iterate(id) {

// Getting the result display section
var result = document.getElementsByClassName("result");
result[0].innerText = "";

// Getting the question
const question = document.getElementById("question");


// Setting the question text
question.innerText = Questions[id].q;

// Getting the options
const op1 = document.getElementById('op1');
const op2 = document.getElementById('op2');
const op3 = document.getElementById('op3');
const op4 = document.getElementById('op4');


// Providing option text 
op1.innerText = Questions[id].a[0].text;
op2.innerText = Questions[id].a[1].text;
op3.innerText = Questions[id].a[2].text;
op4.innerText = Questions[id].a[3].text;

// Providing the true or false value to the options
op1.value = Questions[id].a[0].isCorrect;
op2.value = Questions[id].a[1].isCorrect;
op3.value = Questions[id].a[2].isCorrect;
op4.value = Questions[id].a[3].isCorrect;

var selected = "";

// Show selection for op1
op1.addEventListener("click", () => {
    op1.style.backgroundColor = "green";
    op2.style.backgroundColor = "white";
    op3.style.backgroundColor = "white";
    op4.style.backgroundColor = "white";
    selected = op1.value;
})

// Show selection for op2
op2.addEventListener("click", () => {
    op1.style.backgroundColor = "white";
    op2.style.backgroundColor = "green";
    op3.style.backgroundColor = "white";
    op4.style.backgroundColor = "white";
    selected = op2.value;
})

// Show selection for op3
op3.addEventListener("click", () => {
    op1.style.backgroundColor = "white";
    op2.style.backgroundColor = "white";
    op3.style.backgroundColor = "green";
    op4.style.backgroundColor = "white";
    selected = op3.value;
})

// Show selection for op4
op4.addEventListener("click", () => {
    op1.style.backgroundColor = "white";
    op2.style.backgroundColor = "white";
    op3.style.backgroundColor = "white";
    op4.style.backgroundColor = "green";
    selected = op4.value;
})

// Grabbing the evaluate button
const evaluate = document.getElementsByClassName("evaluate");


// Evaluate method
evaluate[0].addEventListener("click", () => {
    if (selected == "true") {
        result[0].innerHTML = "True";
        result[0].style.color = "green";
    } else {
        result[0].innerHTML = "False";
        result[0].style.color = "red";
    }
})
}

if (start) {
iterate("0");
}

// Next button and method
const next = document.getElementsByClassName('next')[0];
var id = 0;

next.addEventListener("click", () => {
start = false;
if (id < 2) {
    id++;
    iterate(id);
    console.log(id);
}
})
