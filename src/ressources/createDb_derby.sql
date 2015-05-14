--
-- Base de données: 'andjety'
--

-- --------------------------------------------------------

--
-- Structure de la table 'databaselist'
--

CREATE TABLE databaselist (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  dblist_libelle varchar(500) NOT NULL,
  dblist_ip varchar(500) DEFAULT NULL,
  dblist_instance varchar(500) DEFAULT NULL,
  dblist_user varchar(500) DEFAULT NULL,
  dblist_password varchar(500) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- Structure de la table 'scheduletasks'
--

CREATE TABLE scheduletasks (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  idtask INTEGER NOT NULL,
  ifalldays varchar(10) NOT NULL,
  iflundi INTEGER NOT NULL,
  ifmardi INTEGER NOT NULL,
  ifmercredi INTEGER NOT NULL,
  ifjeudi INTEGER NOT NULL,
  ifvendredi INTEGER NOT NULL,
  ifsamedi INTEGER NOT NULL,
  ifdimanche INTEGER NOT NULL,
  minutes INTEGER NOT NULL,
  heures INTEGER NOT NULL,
  PRIMARY KEY (id)
)

-- --------------------------------------------------------

--
-- Structure de la table 'tasks'
--

CREATE TABLE tasks (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  task_libelle varchar(500) NOT NULL,
  task_requete varchar(500) NOT NULL,
  task_resultat varchar(2000) DEFAULT NULL,
  PRIMARY KEY (id)
) 

-- --------------------------------------------------------

--
-- Structure de la table 'tasksdb'
--

CREATE TABLE tasksdb (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  id_task INTEGER NOT NULL,
  id_db INTEGER NOT NULL,
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- Structure de la table 'tasksexec'
--

CREATE TABLE tasksexec (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  idtask INTEGER NOT NULL,
  idScheduleTask INTEGER NOT NULL,
  dateheure varchar(1000) NOT NULL,
  resultat varchar(1000) NOT NULL,
  PRIMARY KEY (id)
) 

