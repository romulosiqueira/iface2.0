## Code Smells encontrados no codigo

------- Bloaters ---------

Existiam classes no projeto onde elas faziam o trabalho que não era delas, fazendo com que se tornasse muito grande e dificil de ser feito manutenção, pois era dificil saber onde estava sendo feito criado aquele codigo.

Os metodos foram deslocados para as classes na qual suas funções pertecem, tornando o codigo mais facil de ler e de dar manuntenção

----------- Object-Orientation Abusers -----------

Existiam classes a mais do que necessario, onde algumas não tinham funções nenhuma, essas classes foram deletadas do projeto.

------------- Dispensables ----------------
Algumas classes tinham metodos getters and setters que não estavam sendo utilizados na aplicação, esses métodos foram deletados.
