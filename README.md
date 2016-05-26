## Rerverse scaffold demo from Redmine database schema

This is a Skinny demo application which shows you the result of `./skinny g reverse-scaffold-all` with [Redemine database](https://github.com/seratch/redmine-reverse-scaffold/blob/master/redmine.sql).

https://github.com/redmine/redmine

```
git clone git@github.com:redmine/redmine.git
cd redmine
bin/bundle install
cp -p config/database.yml.example config/database.yml

mysql -uroot -e "create database redmine_development"
bin/rake db:migrate

brew install skinny
skinny new redmine-reverse-scaffold
cd redmine-reverse-scaffold

# add MySQL JDBC driver to project/Build.scala
# modify database settings in src/main/resources/application.conf

./skinny g reverse-scaffold-all
```
