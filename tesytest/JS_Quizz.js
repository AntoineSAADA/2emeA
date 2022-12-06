let d2 = document.getElementById("d2");
let d1 = document.getElementById("d1");
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

    let erreur = '';



    
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
        d1.style.display = "none";
        return true;
    }
    else {
        alert(erreur);
        return false;
    }
}


var aff_courant;
var nbFaites = 0;
var reponseJuste = 0;


let correction_auto = 1; //Possibilité de revenir en arriére (0)
let separateur = "/";

function init() //Initialisation
{
    nbFaites = 0;
    aff_courant = 0;
    affichage(aff_courant);
    document.getElementById("btnSuiv").disabled = false;
    reponseJuste = 0;
}


//Création d'une classe question
class Question {
    constructor(type,enonce, reponse, choix) {
        this.type = type;
        this.enonce = enonce;
        this.reponse = reponse;
        this.choix = choix;
    }
    // Réalisation des getters et setters
    get type() {
        return this._type;
    }
    set type(type) {
        this._type = type;
    }
    get enonce() {
        return this._enonce;
    }
    set enonce(enonce) {
        this._enonce = enonce;
    }
    get reponse() {
        return this._reponse;
    }
    set reponse(reponse) {
        this._reponse = reponse;
    }
    get choix() {
        return this._choix;
    }
    set choix(choix) {
        this._choix = choix;
    }

}

//Données Question, Choix de réponses

questions = new Array()
questions[0] = new Question("QCM","Quelles sont les plus grandes ville de France ?","Paris/Marseille","Paris/Lyon/Marseille/Lille");
questions[1] = new Question("QCU","Ce questionnaire est-il bien fait ?","Oui","Oui/Non");
questions[2] = new Question("Texte","Quelle est la capitale de l'Allemagne ?","Berlin","");
questions[3] = new Question("Slider","Quelle âge avez vous","","0/100");


reponses = new Array()


function affichage(index) //Changement de l'affichage d'une question
{
    //Affichage du numero de question
    titre = "<H4>Question " + (index + 1) + " sur " + (questions.length) + "</H4>";
    document.getElementById("question").innerHTML = titre;

    var main;
    main = "<LI> " + questions[index].enonce + "</LI><BR>";
    
    if (questions[index].type == "QCM")
    {
        choix = questions[index].choix.split(separateur);
            for (x=0;x<choix.length;x++){
                main += "<INPUT type='checkbox' id='rep"+x+"' value='"+choix[x]+"'> " + choix[x] + "<BR>";
            }
    }
    else if (questions[index].type == "QCU"){
        choix = questions[index].choix.split(separateur);
        console.log(choix);
            for (x=0;x<choix.length;x++){
                console.log(choix[x]);
                main += "<INPUT type='radio' name='rep' id='rep' value='"+choix[x]+"'> " + choix[x] + "<BR>";
            }
    }
    else if (questions[index].type == "Texte"){
        main += "<INPUT type='text' id='repTexte' value=''>";
    }
    else if (questions[index].type == "Slider"){
        var min = questions[index].choix.split(separateur)[0];
        var max = questions[index].choix.split(separateur)[1];
        main += "<INPUT type='range' id='repSlider' value='0' min='"+min+"' max='"+max+"'>";
    }
    rafraichir_details("courant");
    document.getElementById("main").innerHTML = main;
}
function aff_resultat()//Afichage des résultats
{
    //Affichage du Titre
    titre = "<H4>Résultats</H4>";
    document.getElementById("question").innerHTML = titre;
    var main;
    main = "<TABLE border='1' cellpadding='5' cellspacing='0'>";
    main += "<TR>";
    main += "<TD><B>Vos Réponses</B></TD>  <TD><B>Les Réponses</B></TD>";
    main += "</TR>";


    for (x=0;x<reponses.length;x++) //Affichage Question-Réponse
    {
        //Affichage de la question
        main += "<TR><TD colspan='2'><I>"+(x+1)+". "+(questions[x].enonce)+"</I></TD></TR>";
        main += "<TR>";
        main += "<TD><B>";
        //Affichage de la réponse
        if (questions[x].type == "QCM")
        {
            main += reponses[x];
        }
        else if (questions[x].type == "QCU"){
            main += reponses[x];
        }
        else if (questions[x].type == "Texte")
        {
            main += reponses[x];
        }
        else if (questions[x].type == "Slider")
        {
            main += reponses[x];
        }
        // Ajout de la bonne réponse dans la case de droite
        main += "</B></TD><TD><B>";
        main += questions[x].reponse;
        
        main += "</B></TD>";
        main += "<TD><B>";
    }
    main += "</TABLE>";
    main += "<BR><INPUT type='button' value='Recommencer' onclick='init()'>"
    document.getElementById("main").innerHTML = main; //Copie du code dans le div Main
    
    rafraichir_details("final");
}
function rafraichir_details(moment) // Rafrachis les informations dans la barre du bas
{
    if (aff_courant > nbFaites)
    {
        nbFaites = aff_courant
    }
    nbBonneRep = 0;

    if (correction_auto == 1 || moment == "final")

    {
        for (x=0;x<nbFaites;x++)
        {
            console.log(reponses[x]);
            console.log(questions[x].reponse);
            if(reponses[x] == questions[x].reponse)
            {
                
                nbBonneRep++;
                console.log(nbBonneRep)
            }
            
        }
        var details;
        details = "Score " + moment + " : "  + (nbBonneRep) + " / " + nbFaites;
        if (aff_courant != 0)
        {
            details += " (" + Math.round(((nbBonneRep)/nbFaites*100)*10)/10 + "%)";
        }
    }
    else //dans le cas d'une correction non-automatic le résultat n'est pas affiché
    {
        details = " ";
    }

    document.getElementById("details").innerHTML = details;
}
function suiv() //Bouton Suivant
{
    enregistre_rep()
    
    if (aff_courant < questions.length - 1)
    {
        aff_courant += 1; //Changement du compteur de la question courrante
        affichage(aff_courant); //Changement de l'affichage de la question
    }
    else if (aff_courant == questions.length-1)//S'il s'agit de la derniére question les 2 boutons sont bloqués
    {
        aff_courant += 1;
        aff_resultat();
        document.getElementById("btnSuiv").disabled = true;
    }
}

function enregistre_rep() //Enregistrement des réponses saises ...
{
    rep = "";
    if (aff_courant != questions.length)
    {
        if(questions[aff_courant].type == "QCM"){
            choix = questions[aff_courant].choix.split(separateur);
            for(x;x<choix.length;x++)
            {
                if (document.getElementById("rep"+x).checked == true)
                {
                    rep += document.getElementById("rep"+x).value + separateur;
                    document.getElementById("rep"+x).checked = false;
                }
            }
            rep = rep.substring(0, rep.length - 1);
        }
        else if (questions[aff_courant].type == "QCU"){
            choix = questions[aff_courant].choix.split(separateur);
            for(radio of document.getElementsByName("rep"))
            {
                if (radio.checked == true)
                {
                    rep = radio.value;
                    break;
                }
            }
        }
        else if(questions[aff_courant].type == "Texte")
        {
            rep = document.getElementById("repTexte").value;
        }
        else if(questions[aff_courant].type == "Slider"){
            rep = document.getElementById("repSlider").value;
        }
        reponses[aff_courant] = rep;
    }
 
}