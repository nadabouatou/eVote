package eVote.controler

import java.util.Observable
import eVote.model.ModeScrutin
import java.util.Observer
import java.util.ArrayList

trait SignalScrutin{
	var observers = new ArrayList[Observer]()
	
	def update(sMode:Int):Int;
	def addObserver(o:Observer):Unit={
		if(o!=Nil)observers.add(o)
	}
	
	def removeObserver(s:Observer):Unit={
		if(s!=Nil)observers.remove(s)
	}
}