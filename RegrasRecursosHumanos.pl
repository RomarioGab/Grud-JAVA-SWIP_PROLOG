% Author:
% Date: 15/01/2020

:- dynamic data_nasc/3.
:- dynamic funcionario/10.
:- dynamic dependente/9.
:- dynamic departamento/3.
:- dynamic licenca/4.

carregar:- exists_file('BaseConhecimentoRecursosHumanos.pl'),consult('BaseConhecimentoRecursosHumanos.pl').

addfuncionario(A1,A,B,C,data_nasc(D,E,F),G,H,I,J,K):-assertz(funcionario(A1,A,B,C,data_nasc(D,E,F),G,H,I,J,K)).
removefuncionario(_,A,_,_,data_nasc(_,_,_),_,_,_,_,_):-retract(funcionario(_,A,_,_,data_nasc(_,_,_),_,_,_,_,_)).

adddependente(A,B,C,D,data_nasc(E,F,G),H,I,J,K):-assertz(dependente(A,B,C,D,data_nasc(E,F,G),H,I,J,K)).
removedependente(A,_,_,_,data_nasc(_,_,_),_,_,_,_):-retract(dependente(A,_,_,_,data_nasc(_,_,_),_,_,_,_)).

adddepartamento(A,B,C):-assertz(departamento(A,B,C)).
removedepartamento(A,_,_):-retract(departamento(A,_,_)).

addlicenca(A,B,data_inicio(D,E,F),data_fim(G,H,I)):-assertz(licenca(A,B,data_inicio(D,E,F),data_fim(G,H,I))).
removelicenca(A,_,data_inicio(_,_,_),data_fim(_,_,_)):-reract(licenca(A,_,data_inicio(_,_,_),data_fim(_,_,_))).

salvar :- tell('BaseConhecimentoRecursosHumanos.pl'),
          listing(funcionario),listing(dependente),listing(departamento),listing(licenca),
          told.

login(nome,[prolo,senh]).