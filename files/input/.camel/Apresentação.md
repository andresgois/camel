Bom dia pessoal, meu nome é André
complementando a parte do backend
vou fala agora sobre a prática devops 
que utilizamos.

a cada vez que implementamos uma nova feature 
em nosso repositorio principal
era ativado um gatilho para o github actions
que por sua vez seguia uma serie de passos
que incluiam:
- atualização das dependências
- acesso a aplicação da AWS via SSH
- copia do repositorio para a instância EC2 
que é a máquina virtual da AWS
- instalação das dependências
- o restart do gerenciador de processos do Node
que no caso é o PM2.

usamos também o servidor NGINX como proxy reverso
que no caso pega todas as requisições da porta 80 
é transfere para a porta 3000 que é onde está alocada 
nossa aplicação backend, devido a porta 3000 não está sendo 
exposta para garantir apenas os acessos necessários no servidor.
Nós também compramos um domínio supermercadosq e 
fizemos o certificado no EC2 com o intuito de deixar o nosso sistema mais profissional.

utilizamos também o S3 que fornece 
armazenamento de objetos por meio de uma interface de serviço da web
onde guardamos nossas imagens.
