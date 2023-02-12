# finalWebShop
1. maven: clean and install 
sudo docker run -p 3307:3306 --name mysql -d --net product-net -e MYSQL_DATABASE=demo -e MYSQL_USER=user -e MYSQL_PASSWORD=user -e MYSQL_ROOT_PASSWORD=root_password mysql
3.docker-compose build
4.docker-compose up