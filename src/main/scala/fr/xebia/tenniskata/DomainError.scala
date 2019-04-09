package fr.xebia.tenniskata

sealed trait DomainError
case object ScoreNotValid extends DomainError
