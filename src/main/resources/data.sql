INSERT INTO `images` (`name`, `location`)
VALUES("2020-toyota-yaris-104-1587498075.jpg", "http://res.cloudinary.com/ddw62fr4j/image/upload/v1720556236/zpaehdhae2s6detdkhft.jpg"),
      ("audi.jpg", "http://res.cloudinary.com/ddw62fr4j/image/upload/v1720556687/lgmbwvug04yokcj9aoku.jpg"),
      ("santa_fe.jpg", "http://res.cloudinary.com/ddw62fr4j/image/upload/v1721453820/kzezuvd51qgoqlm7wrod.jpg"),
      ("superb.jpg", "http://res.cloudinary.com/ddw62fr4j/image/upload/v1721790412/ooeqypwdwmysbu8mjemi.jpg"),
      ("Dacia_Spring_Electric-01.jpg", "http://res.cloudinary.com/ddw62fr4j/image/upload/v1721790653/kpzp40j2kjrxstogydpu.jpg"),
      ("Hyundai_Kona_Electric_2024-01@2x.jpg", "http://res.cloudinary.com/ddw62fr4j/image/upload/v1721790753/heytnezysusupkllye14.jpg"),
      ("1024-768-citroen-ami.jpg", "http://res.cloudinary.com/ddw62fr4j/image/upload/v1721880583/rx7yd8hjgw4zmpgn0qel.jpg"),
      ("renault-twizy.jpg", "http://res.cloudinary.com/ddw62fr4j/image/upload/v1721880659/nyb16jq9ordt0ntwkfkc.jpg");

INSERT INTO `cars` (`brand`, `model`, `year`, `engine_type`, `transmission`, `category`, `seats`, `fuel_consumption`, `trunk_volume`, `price_per_day`,`is_available`, `image_id`)
VALUES("Reanult", "Clio", 2022, "PETROL", "MANUAL", "COMPACT", 4, 5, 280, 80, 1, 1),
      ("Vw", "Passat", 2019, "DIESEL", "AUTOMATIC", "ESTATE", 4, 8, 480, 170, 1, 1),
      ("Toyota", "CH-R", 2023, "HYBRID", "AUTOMATIC", "SUV", 4, 4, 350, 190, 1, 1),
      ("Toyota", "Yaris", 2020, "HYBRID", "AUTOMATIC", "COMPACT", 4, 4, 200, 90, 1, 2),
      ("Audi", "A4", 2021, "DIESEL", "AUTOMATIC", "ESTATE", 4, 8, 450, 120, 1, 3),
      ("Hyundai", "Santa Fe", 2022, "HYBRID", "AUTOMATIC", "SUV", 4, 7, 430,300, 1, 4),
      ("Skoda", "Superb", 2023, "DIESEL", "AUTOMATIC", "ESTATE", 4, 6, 500, 320, 1, 5),
      ("Dacia", "Spring", 2023, "ELECTRIC", "AUTOMATIC", "COMPACT", 4, 3, 200, 150, 1, 6),
      ("Hyundai", "Kona", 2024, "ELECTRIC", "AUTOMATIC", "SUV", 4, 5, 390, 200, 1, 7),
      ("Citroen", "Ami", 2020, "ELECTRIC", "AUTOMATIC", "COMPACT", 2, 4, 200, 70, 1, 8),
      ("Renault", "Twizy", 2021, "ELECTRIC", "AUTOMATIC", "COMPACT", 2, 5, 200, 85, 1, 9);

INSERT INTO `extras` (`name`, `price`)
VALUES ("GPS", 15),
       ("Baby seat", 10),
       ("Suitcase", 7),
       ("Car freshener", 2);

