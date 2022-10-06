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
let separateur = ","; //Séparateurs des choix de réponse
let separateur2 = "/";

function init() //Initialisation
{
    nbFaites = 0;
    aff_courant = 0;
    affichage(aff_courant);
    document.getElementById("btnPrec").disabled = true;
    document.getElementById("btnSuiv").disabled = false;
    reponseJuste = 0;
}

//Données Question, Choix de réponses
questions = new Array()
questions[0] = new Array() //Questions é choix multiples
    questions[0][0] = "Quel est la capital de la France ?";
    questions[0][1] = "Paris, Marseille, Lyon";
    questions[0][2] = "0";
questions[1] = new Array() //Question é saisie de texte
    questions[1][0] = "Quel est la capitale de l'Allemagne ?";
    questions[1][1] = "Berlin";
questions[2] = new Array() //Questions é choix combinés
    questions[2][0] = "Quel est la capitale de l'Espagne ?";
    questions[2][1] = "Madrid,Non pas cette réponse,Madrid";
    questions[2][2] = "0,2";
questions[3] = new Array() //Vrai-Faux
    questions[3][0] = "Ce questionnaire est en javascript.";
    questions[3][1] = "Vrai,Faux";
    questions[3][2] = "0";
questions[4] = new Array() //Slider
    questions[4][0] = "Comment évalueriez-vous ce questionnaire?";
    questions[4][1] = "0/100";

reponses = new Array()

function affichage(index) //Changement de l'affichage d'une question
{
    //Affichage du numero de question
    titre = "<H4>Question " + (index + 1) + " de " + (questions.length) + "</H4>";
    document.getElementById("question").innerHTML = titre;
    
    var main;
    main = "<LI> " + questions[index][0] + "</LI><BR>";
    
    //Affichage des choix ou texbox


    choix = questions[index][1].split(separateur);
    if (choix.length > 1) //Si il y a plusieurs choix
    {
        if (questions[index][2].split(separateur).length>1) //S'il y a plus d'un élément é choisir
        {
            for (x=0;x<choix.length;x++)
            {
                main += "<INPUT type='checkbox' id='rep" + x + "' value='"+x+"'> " + choix[x] + "<BR>";
            }
        }
        else  //S'il y a un seul élément é choisir
        {
            for (x=0;x<choix.length;x++)
            {
                main += "<INPUT type='radio' name='rep' id='rep"+x+"' value='"+x+"'";
                if (x == 0) //Le choix selectionné par default est le 1er
                {
                    main += " checked";
                }
                main += "> " + choix[x] + "<BR>";
            }
        }
    }
    else if (questions[index][1].split(separateur2).length>1) //Si il y a plusieurs choix
    {
        main += "<INPUT type='range' id='ratio' min='"+questions[index][1].split(separateur2)[0]+"' max='"+questions[index][1].split(separateur2)[1]+"''>";
        reponseJuste += 1;
    }
    else //Dans le cas d'une réponse courte
    {

            main += "<INPUT type='text' id='reponse'>";
    }
    document.getElementById("main").innerHTML = main;
    
    rafraichir_details("courrant");
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
        main += "<TR><TD colspan='2'><I>"+(x+1)+". "+(questions[x][0])+"</I></TD></TR>";
        main += "<TR>";
        main += "<TD><B>";
        
        //Affichage des réponses entrées
        if (questions[x].length > 2)
        {
            numeros1 = reponses[x].split(separateur)
            textes = questions[x][1].split(separateur)
            for (y=0;y<numeros1.length;y++)
            {
                main += textes[numeros1[y]] + ", "
            }
            main = main.substring(0,main.length-2);
            main += "</B></TD>  <TD><B>"
            
            numeros2 = questions[x][questions[x].length-1].split(separateur)
            for (y=0;y<numeros2.length;y++)
            {
                main += textes[numeros2[y]] + ", "
            }
            main = main.substring(0,main.length-2);
        }
        else //Affichage é coté de la bonne réponse
        {
            if (reponses[x] == "") reponses[x]='""';
            main += reponses[x] + "</B></TD>  <TD><B>" + questions[x][questions[x].length-1]
        }
        main += "</B></TD>";
        main += "</TR>";
    }
    main += "</TABLE>";
    main += "<BR><INPUT type='button' value='Recommencer' onclick='init()'>"
    document.getElementById("main").innerHTML = main; //Copie du code dans le div Main
    
    rafraichir_details("final");
}
function rafraichir_details(moment) // Rafrachis les informations dans la barre du bas
{
    if (aff_courant == 0){
        reponseJuste = 0;
    }
    if (aff_courant > nbFaites)
    {
        nbFaites = aff_courant
    }
    nbBonneRep = 0;
    if (correction_auto == 1 || moment == "final")
    {
        for (x=0;x<nbFaites;x++)
        {
            if (reponses[x] == questions[x][(questions[x].length-1)]) //Addition des bonne réponses
            {
                nbBonneRep += 1
            }
            
        }
        var details;
        details = "Score " + moment + " : "  + (nbBonneRep+reponseJuste) + " / " + nbFaites;
        if (aff_courant != 0)
        {
            details += " (" + Math.round(((nbBonneRep+reponseJuste)/nbFaites*100)*10)/10 + "%)";
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
        
        if (correction_auto == 1)
        {
            document.getElementById("btnPrec").disabled = true;
        }
        else //Dans le cas oé la correction nest pas automatisé, l'utilisateur peut tjrs revenir é la question précédente
        {
            document.getElementById("btnPrec").disabled = false;
        }
    }
    else if (aff_courant == questions.length-1)//S'il s'agit de la derniére question les 2 boutons sont bloqués
    {
        aff_courant += 1;
        aff_resultat();
        document.getElementById("btnPrec").disabled = true;
        document.getElementById("btnSuiv").disabled = true;
    }
}
function prec() //Bouton Précédant
{
    enregistre_rep()
    if (aff_courant > -1)
    {
        aff_courant -= 1; //Changement du compteur de la question courrante
        affichage(aff_courant); //Changement de l'affichage de la question
        
        document.getElementById("btnSuiv").disabled = false;
        
        if (aff_courant == 0)
        {
            document.getElementById("btnPrec").disabled = true;
        }
    }
}
function enregistre_rep() //Enregistrement des réponses saises ...
{
    rep = "";
    if (aff_courant != questions.length)
    {
            choix = questions[aff_courant][1].split(separateur);
            if (choix.length > 1)
            {

                for (x=0;x<choix.length;x++) //Ajout de ou des l'index des réponses
                {
                    if (document.getElementById("rep"+x).checked)
                    {
                        rep += x + separateur;
                    }
                }
                rep = rep.substring(0,rep.length-1); //Enleve la vergule de trop
            }
            else if (questions[aff_courant][1].split(separateur2).length>1) {
                rep = document.getElementById("ratio").value;
            }
            else{
                    rep += document.getElementById("reponse").value
            }
            reponses[aff_courant] = rep //Stockage de la réponse
        }
}