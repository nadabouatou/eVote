package eVote.controler

import java.util.Observable
import eVote.model.ModeScrutin
import eVote.model.ElectionPresidentielle
import java.util.Observer

class ElectionPresidentielleObservable extends Observable{
  
	var addedOberver:Observer =_
	var electionP:ElectionPresidentielle=_
	
	def init(isObserver:ScrutinObserver):Unit={
		isObserver.addObserver(addedOberver)
	}
	
	def traitement(sMode:Int):Unit={
	  	electionP.modeDeScrutin =sMode
	}
}