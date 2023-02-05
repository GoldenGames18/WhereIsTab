# WhereIsTwab

A simple discord bot for destiny 2
* Give all wishes
* Search the last article on https://www.bungie.net/7

![alt all vandor](https://www.bungie.net/pubassets/pkgs/140/140245/FrontpageBanner_01.jpg?cv=3983621215&av=4088142355)
------------

### Installation

1 Create bot 
* Go to https://discord.com/developers/applications and create your bot and set your permission 
* Invite him to your server

2 Destiny api
* Go to https://www.bungie.net/developer and login with steam or other platforms.
* Register your app

3 Edit config file
* Go to the folder `resources` and in the file `config.toml` place your discord token and api key

```toml
  [bot]
  token = "token discord"
  ApiKey = "your api kay"
  json = "subscribe.json"
    
```

### Run discord bot

* Install all dependancie and build application `gradle build`
* Run application `gradle run`


