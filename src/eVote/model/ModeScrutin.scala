package eVote.model

import java.util.Observable
import java.util.Observer

trait ModeScrutin extends Observer{
	var modeScrutinNumero:Int=_
	var modeScrutinName:String=_
	var ep:ElectionPresidentielle=_
	
	def notification():Unit={
	  
	}
	
}