--INSERT INTO user(id,username,password,first_name,last_name,photo,country_id,town,district,address,phone,email,whatsapp,facebook,instagram,twitter)
--VALUES
--(1,'elysee',crypt('passer'),'elysee','Kouassi','mypicture.png',1,'Dakar','medina','rue 34 Angle 24','+22177937282','kouassielysee@gmail.com','+221778399392','myfacebook','myinstagram','mytwitter') RETURNING *;
--
--
    INSERT INTO town(id,name) VALUES
    (1,'Dakar'), (2,'Pikine'),(3,'Touba'),(4,'Guédiawaye'),(5,'Thies'),(6,'Kaolack'),(7,'Mbour'),
    (8,'Saint-Louis'),(9,'Rufisque'),(10,'Ziguinchor'),(11,'Diourbel'),
    (12,'Louga'),(13,'Tambacounda'),(14,'Mbacké'),(15,'Kolda');  


INSERT INTO category(id,description,name,user_id,title) VALUES
       (1,'Categorie Shop','Boutique',2,'shop'),
       (2,'Categorie Service','Service',2,'service'),
       (3,'Categorie ventes groupées','groupSale',2,'groupSale'),
       (4,'Categorie Magasin','Magasin',2,'store');


INSERT INTO sub_category(id,description,name,category_id,title) VALUES
  (1,'Sous-categorie de Shop','Supermarché',1,'supermarket'),
  (2,'Sous-categorie de Shop','Santé et Beauté',1,'healthBeauty'),
  (3,'Sous-categorie de Shop','Téléphone et Tablete',1,'phone'),
  (4,'Sous-categorie de Shop','Electronique',1,'electronic'),
  (5,'Sous-categorie de Shop','Informatique',1,'informatic'),
  (6,'Sous-categorie de Shop','Electroménager',1,'homeAppliance'),
  (7,'Sous-categorie de Shop','Habillement',1,'cloth'),
  (8,'Sous-categorie de Shop','Loisir',1,'hobby'),
  (9,'Sous-categorie de Service','Couture',2,'sewing'),
  (10,'Sous-categorie de Service','Coiffure',2,'hairdressing'),
  (11,'Sous-categorie de Service','Ménuiserie',2,'carpentry'),
  (12,'Sous-categorie de Service','Epiceries',2,'grocery'),
  (13,'Sous-categorie de Service','Mécanique',2,'mechanical'),
  (14,'Sous-categorie de Service','Réparation',2,'repair'),
  (15,'Sous-categorie de Shop','Autre',1,'other'),
  (16,'Sous-categorie de Service','Autre',2,'other')
 ;


	    
INSERT INTO day(id,name) VALUES (1,'1 jour'),(2,'2 jour'),(3,'3 jours'),(4,'4 jours'),(5,'5 jours'),(6,'6 jours'),(7,'7 jours'),(8,'8 jours'),(9,'9 jours'),(10,'10 jours'),(11,'11 jours'),(12,'12 jours'),(13,'13 jours'), (14,'14 jours'),(15,'15 jours'),(16,'16 jours'),(17,'17 jours'),(18,'18 jours'),(19,'19 jours'),  (20,'20 jours'),	(21,'21 jours'),(22,'22 jours'),(23,'23 jours'),(24,'24 jours'),(25,'25 jours'),(26,'26 jours'), (27,'27 jours'),(28,'28 jours'), (29,'29 jours'),(30,'30 jours'),(31,'31 jours'); 
	    

INSERT INTO hour(id,name) VALUES (1,'08 H'),(2,'09 H'),(3,'10 H'),(4,'11 H'),(5,'12 H'),(6,'13 H'),(7,'14 H'),(8,'15 H'),(9,'16 H'),(10,'17 H'),(11,'18 H'),(12,'19 H'),(13,'20 H'), (14,'21 H'),(15,'22 H'); 


