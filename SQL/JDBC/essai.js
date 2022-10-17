var ClasAffect=SpreadsheetApp.getActiveSpreadsheet().getSheetByName('AffectCandide');

function affectCandides(){
  ClasAffect.getRange('L1').copyTo(ClasAffect.getRange('L6:L300'));
  // Compter le nombre de lignes renvoyées par la requête en B6
  comptageLigne('AffectCandide','B',6,'M1');//renvoie nbLigne
  // Pour chaque ligne
  for(i=6;i<parseInt(nbLigne)+6;i++){ // Parcourir les lignes
    //Remettre à blanc les requêtes sur les profs
    ClasAffect.getRange('O1:P35').setValue('');
    // Rechercher les profs qui sont dispos le jour et l'heure de la ligne (requête en O1)    
    ClasAffect.getRange('O1').setFormula('=query(BDs!I3:M2000;"select I where M<>\'I\' and I<>\''+ClasAffect.getRange(i,8).getValue()+'\' and J='+numSerie(ClasAffect.getRange(i,10).getValue())+' and K=\''+ClasAffect.getRange(i,11).getValue()+'\'")');

    ClasAffect.getRange('P1:P30').setFormula('=if(not(isblank(O1)); vlookup(O1;BDs!C$3:D$26;2;false);"")');//vlookup sur les types de profs
    ClasAffect.getRange('Q1:Q30').setFormula('=if(not(isblank(O1)); VLOOKUP(O1;BDs!C$3:H$24;6;false);"")');//En colonne Q, mettre un vlookup qui donne les types de profs
    ClasAffect.getRange('R1').setFormula('=query(BDs!I3:M2000;"select count(I) where M<>\'I\' and I<>\''+ClasAffect.getRange(i,8).getValue()+'\' and J='+numSerie(ClasAffect.getRange(i,10).getValue())+' and K=\''+ClasAffect.getRange(i,11).getValue()+'\'")'); //compter le nombre de profs dispos
    
    nbDeLign=ClasAffect.getRange("R2").getValue(); // Récupérer le nombre de lignes renvoyées par la requête
    ClasAffect.getRange(1,15,nbDeLign,2).copyTo(ClasAffect.getRange(1,15,nbDeLign,2),SpreadsheetApp.CopyPasteType.PASTE_VALUES,false); //Copie les valeurs de la requête dans la colonne P
    ClasAffect.getRange('T1').setFormula('=vlookup("'+ClasAffect.getRange(i,8).getValue()+'";BDs!C3:D52;2;false)') //Type de prof
    ClasAffect.getRange('U1').setFormula('=if(T1="I";query(O1:Q16;"select O where Q>0 order by P DESC"); query(O1:Q16;"select O where Q>0 order by P ASC"))');//Si le prof est I, on trie par ordre décroissant de type de prof, sinon on trie par ordre croissant
    


    disposProfs=ClasAffect.getRange(1,21,nbDeLign).getValues(); // Récupérer les profs dispos
    rule = SpreadsheetApp.newDataValidation().requireValueInList(disposProfs).build(); // Créer la règle
    ClasAffect.getRange(i,12).setDataValidation(rule);// Appliquer la règle
   //ClasAffect.getRange('O1:U300').setValue('');



  };
 
};

function valideAffectCandide(){
  // compter le nbre de lignes
  //Pour chaque ligne (en partant du bas et en remontant)
    // si pas vide, récupérer l'étudiant
      // récupérer le nom du prof qui va être candide en A2
      // aller chercher la ligne de l'étudiant dans Data
      // En colonne Q (17), mettre le nom du prof
      // aller mettre à jour les dispos du prof
  // relancer la mise à jour
};


