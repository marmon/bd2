/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2011-12-12 16:30:37                          */
/*==============================================================*/


alter table PLATNOSCI
   drop constraint FK_PLATNOSC_REFERENCE_REZERWAC;

alter table PLATNOSCI
   drop constraint FK_PLATNOSC_REFERENCE_TYPY_PLA;

alter table POKOJE
   drop constraint FK_POKOJE_REFERENCE_TYPY_POK;

alter table POZYCJE_REZERWACJI
   drop constraint FK_POZYCJE__REFERENCE_REZERWAC;

alter table POZYCJE_REZERWACJI
   drop constraint FK_POZYCJE__REFERENCE_POKOJE;

alter table REZERWACJE
   drop constraint FK_REZERWAC_REFERENCE_KLIENCI;

drop table KLIENCI cascade constraints;

drop table PLATNOSCI cascade constraints;

drop table POKOJE cascade constraints;

drop table POZYCJE_REZERWACJI cascade constraints;

drop table REZERWACJE cascade constraints;

drop table TYPY_PLATNOSCI cascade constraints;

drop table TYPY_POKOI cascade constraints;

/*==============================================================*/
/* Table: KLIENCI                                               */
/*==============================================================*/
create table KLIENCI 
(
   ID_KLIENTA           NUMBER(32,0)         not null,
   NUMER_DOKUMENTU      VARCHAR2(32)         not null,
   IMIE                 VARCHAR2(32)         not null,
   NAZWISKO             VARCHAR2(64)         not null,
   NUMER_TELEFONU       NUMBER(16,0)         not null,
   KRAJ                 VARCHAR2(32),
   MIEJSCOWOSC          VARCHAR2(32),
   KOD_POCZTOWY         VARCHAR2(16),
   ULICA                VARCHAR2(32),
   NUMER_ULICY          VARCHAR2(8),
   NUMER_MIESZKANIA     NUMBER(8,0),
   constraint PK_KLIENCI primary key (ID_KLIENTA)
);

/*==============================================================*/
/* Table: PLATNOSCI                                             */
/*==============================================================*/
create table PLATNOSCI 
(
   ID_REZERWACJI        NUMBER(32,0)         not null,
   ID_TYPU_PLATNOSCI    NUMBER(32,0)         not null,
   KWOTA                NUMBER(32,2)         not null,
   constraint PK_PLATNOSCI primary key (ID_REZERWACJI, ID_TYPU_PLATNOSCI)
);

/*==============================================================*/
/* Table: POKOJE                                                */
/*==============================================================*/
create table POKOJE 
(
   NUMER_POKOJU         NUMBER(32,0)         not null,
   ID_TYPU_POKOJU       NUMBER(32,0)         not null,
   NUMER_PIETRA         NUMBER(8,0)          not null,
   NUMER_TELEFONU       NUMBER(8,0)          not null,
   constraint PK_POKOJE primary key (NUMER_POKOJU)
);

/*==============================================================*/
/* Table: POZYCJE_REZERWACJI                                    */
/*==============================================================*/
create table POZYCJE_REZERWACJI 
(
   NUMER_POKOJU         NUMBER(32,0)         not null,
   ID_REZERWACJI        NUMBER(32,0)         not null,
   OD                   DATE                 not null,
   DO                   DATE                 not null,
   constraint PK_POZYCJE_REZERWACJI primary key (NUMER_POKOJU, ID_REZERWACJI)
);

/*==============================================================*/
/* Table: REZERWACJE                                            */
/*==============================================================*/
create table REZERWACJE 
(
   ID_REZERWACJI        NUMBER(32,0)         not null,
   ID_KLIENTA           NUMBER(32,0)         not null,
   DATA_ZALOZENIA       DATE                 not null,
   WARTOSC              NUMBER(32,2)         not null,
   constraint PK_REZERWACJE primary key (ID_REZERWACJI)
);

/*==============================================================*/
/* Table: TYPY_PLATNOSCI                                        */
/*==============================================================*/
create table TYPY_PLATNOSCI 
(
   ID_TYPU_PLATNOSCI    NUMBER(32,0)         not null,
   NAZWA                VARCHAR2(64)         not null,
   constraint PK_TYPY_PLATNOSCI primary key (ID_TYPU_PLATNOSCI)
);

/*==============================================================*/
/* Table: TYPY_POKOI                                            */
/*==============================================================*/
create table TYPY_POKOI 
(
   ID_TYPU_POKOJU       NUMBER(32,0)         not null,
   CENA                 NUMBER(16,0)         not null,
   LICZBA_MIEJSC        NUMBER(2,0)          not null,
   LICZBA_LOZEK         NUMBER(2,0)          not null,
   OPIS                 VARCHAR2(256),
   constraint PK_TYPY_POKOI primary key (ID_TYPU_POKOJU)
);

alter table PLATNOSCI
   add constraint FK_PLATNOSC_REFERENCE_REZERWAC foreign key (ID_REZERWACJI)
      references REZERWACJE (ID_REZERWACJI);

alter table PLATNOSCI
   add constraint FK_PLATNOSC_REFERENCE_TYPY_PLA foreign key (ID_TYPU_PLATNOSCI)
      references TYPY_PLATNOSCI (ID_TYPU_PLATNOSCI);

alter table POKOJE
   add constraint FK_POKOJE_REFERENCE_TYPY_POK foreign key (ID_TYPU_POKOJU)
      references TYPY_POKOI (ID_TYPU_POKOJU);

alter table POZYCJE_REZERWACJI
   add constraint FK_POZYCJE__REFERENCE_REZERWAC foreign key (ID_REZERWACJI)
      references REZERWACJE (ID_REZERWACJI);

alter table POZYCJE_REZERWACJI
   add constraint FK_POZYCJE__REFERENCE_POKOJE foreign key (NUMER_POKOJU)
      references POKOJE (NUMER_POKOJU);

alter table REZERWACJE
   add constraint FK_REZERWAC_REFERENCE_KLIENCI foreign key (ID_KLIENTA)
      references KLIENCI (ID_KLIENTA);

