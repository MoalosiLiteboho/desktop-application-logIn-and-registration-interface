create database if not exists HospitalSystemDesktopApplication;

use HospitalSystemDesktopApplication;

create table if not exists Authorities(
    Id int not null auto_increment,
    Role varchar(255) not null,
    primary key(Id)
);

create table if not exists User(
    Id int not null,
    Name varchar(255) not null,
    Surname varchar(255) not null,
    Gender varchar(255) not null,
    DateOfBirth date not null,
    primary key(Id)
);

create table if not exists UserAccount(
    UserId int not null,
    AuthorityId int not null,
    Email varchar(255) not null,
    Password varchar(255) not null,
    Enabled bit not null,
    ExpiryDate date not null,
    primary key(UserId),
    foreign key(UserId) references User(Id),
    foreign key(AuthorityId) references Authorities(Id)
);
