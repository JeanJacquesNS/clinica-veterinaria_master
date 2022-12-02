CREATE DATABASE CLINICA_VETERINARIA;

------------------------------------------------------------

    use clinica_veterinaria;
create table cliente
(
    idCliente      int auto_increment
        primary key,
    nome           varchar(50) not null,
    sexo           varchar(10) not null,
    tipo_documento varchar(20) not null,
    nr_documento   varchar(20) not null,
    telefone       varchar(20) not null,
    telefone_2     varchar(20) null,
    bairro         varchar(50) null,
    avenida        varchar(50) null,
    rua            varchar(50) null,
    constraint cliente_nr_documento_uindex
        unique (nr_documento)
);

create table animal
(
    idAnimal        int auto_increment
        primary key,
    nome            varchar(50) not null,
    nrDocumento     varchar(20) not null,
    especie         varchar(50) null,
    sexo            char        not null,
    data_nascimento varchar(10) not null,
    idCliente       int         not null,
    constraint animal_cliente_idCliente_fk
        foreign key (idCliente) references cliente (idCliente),
    constraint animal_cliente_nr_documento_fk
        foreign key (nrDocumento) references cliente (nr_documento)
);

create table funcionario
(
    idFuncionario  int auto_increment
        primary key,
    nome           varchar(50) not null,
    sexo           varchar(10) null,

    tipo_documento varchar(50) not null,
    nr_documento   varchar(50) not null,
    telefone       varchar(20) not null,
    telefone_2     varchar(20) ,
    email          varchar(30) null,
    bairro         varchar(50) null,
    avenida        varchar(50) null,
    rua            varchar(50) null
);
create table autenticacao
(
    idAutenticacao int auto_increment primary key ,
    login varchar(50) not null,
    password varchar(50) not null,
    nr_documento varchar(50) not null unique,
    primeiro_acesso varchar(1) null,
    constraint autenticacao_funcionario_nr_documento_fk
        foreign key (nr_documento) references funcionario (nr_documento)
);
create table consulta
(
    idConsulta       int auto_increment
        primary key,
    data_consulta    varchar(10)  not null,
    descricao        varchar(200) not null,
    nome_animal      varchar(50)  not null,
    nome_veterinario varchar(50)  not null
);

create table exame
(
    idExame          int auto_increment
        primary key,
    nome_animal      varchar(50)  not null,
    descricao        varchar(200) not null,
    nome_veterinario varchar(50)  not null,
    data             varchar(10)  not null
);



create table tratamento
(
    idTratamento int auto_increment
        primary key,
    data_inicial varchar(10)  not null,
    data_final   varchar(10)  not null,
    descricao    varchar(200) not null,
    diagnostico  varchar(200) not null,
    nome_animal  varchar(20)  not null
);

