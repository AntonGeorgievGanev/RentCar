INSERT INTO `images` (`name`, `location`)
VALUES("2020-toyota-yaris-104-1587498075.jpg", "http://res.cloudinary.com/ddw62fr4j/image/upload/v1720556236/zpaehdhae2s6detdkhft.jpg"),
      ("audi.jpg", "http://res.cloudinary.com/ddw62fr4j/image/upload/v1720556687/lgmbwvug04yokcj9aoku.jpg");

INSERT INTO `cars` (`brand`, `model`, `year`, `engine_type`, `transmission`, `category`, `seats`, `fuel_consumption`, `trunk_volume`, `price_per_day`,`is_available`, `image_id`)
VALUES("Reanult", "Clio", 2022, "PETROL", "MANUAL", "COMPACT", 4, 5, 280, 80, 1, 1),
      ("Vw", "Passat", 2019, "DIESEL", "AUTOMATIC", "ESTATE", 4, 8, 480, 170, 1, 1),
      ("Toyota", "CH-R", 2023, "HYBRID", "AUTOMATIC", "SUV", 4, 4, 350, 190, 1, 1),
      ("Toyota", "Yaris", 2020, "HYBRID", "AUTOMATIC", "COMPACT", 4, 4, 200, 90, 1, 2),
      ("Audi", "A4", 2021, "DIESEL", "AUTOMATIC", "ESTATE", 4, 8, 450, 120, 1, 3);

INSERT INTO `extras` (`name`, `price`)
VALUES ("GPS", 15),
       ("Baby seat", 10),
       ("Suitcase", 7),
       ("Car freshener", 2);

