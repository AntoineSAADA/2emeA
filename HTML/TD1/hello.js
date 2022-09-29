function valider() {
    const login = document.getElementById('login').value;
    const email = document.getElementById('mail').value;
    const password = document.getElementById('pw').value;
    const datenaiss = document.getElementById('datenaiss').value;
    const numero = document.getElementById('tel').value;

    const dMajor = new Date();
    dMajor.setFullYear(dMajor.getFullYear()-18);

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
    else if(new Date(datenaiss) > dMajor) {
        erreur += 'Vous devez avoir au moins 18 ans';
    }
    
    
    if (erreur == '') {
        return true;
    }
    else {
        alert(erreur);
        return false;
    }
}


// réalisation d'un questionnaire 

const questionnaire = [{
    question: 'Quel est le nom de la capitale de la France ?',
    reponses: ['Paris', 'Lyon', 'Marseille', 'Bordeaux'],
    bonneReponse: 0
}, {
    question: 'Quel est le nom de la capitale de l\'Espagne ?',
    reponses: ['Madrid', 'Barcelone', 'Valence', 'Séville'],
    bonneReponse: 0
}, {
    question: 'Quel est le nom de la capitale de l\'Italie ?',
    reponses: ['Rome', 'Milan', 'Turin', 'Venise'],
    bonneReponse: 0
}, {
    question: 'Quel est le nom de la capitale de l\'Allemagne ?',
    reponses: ['Berlin', 'Hambourg', 'Munich', 'Cologne'],
    bonneReponse: 0
}, {
    question: 'Quel est le nom de la capitale de la Belgique ?',
    reponses: ['Bruxelles', 'Anvers', 'Liège', 'Gand'],
    bonneReponse: 0
}];
// on initialise le score à 0
let score = 0;
// on initialise le nombre de questions à 0
let nbQuestions = 0;
// on initialise le nombre de bonnes réponses à 0
let nbBonnesReponses = 0;
// on initialise le tableau des réponses
let reponses = [];

function verif1() {
    const reponse = document.querySelector('input[name="question1"]:checked').value;
    if (reponse == questionnaire[0].bonneReponse) {
        nbBonnesReponses++;
    }
    reponses.push(reponse);
    nbQuestions++;
    document.getElementById('question1').style.display = 'none';
    document.getElementById('question2').style.display = 'block';
}