#Social-radio

##Requirements

- Maven.
- Java 1.6 or Higher.
- Spring Framework.
- RESTFull Framework.
- NoSQL DB.
- IntelliJ or Eclipse IDE.
- Apache Solr or Elastic Search
- Streaming Server

##Goals

1. Build a application web for create your own radio integrated with 
multiple social networks like Facebook, Twitter among others. All the users regitered would be participating to chat, vote and promote music.

1. Apply Agile Methodologies to manage the development process.

1. Provide full documentation describing the API.

1. Assert 90% of Cobertura

##Architecture Design

Social-radio Core Application should expose RESTFull Resources for multiple kind of clients, like mobile or websites. 

As a client you could do the folloing feature.

 1. Search music that can be add it to the current radio playlist.
 2. Vote tracks of playlist to promote and be played as next song.
 3. Send Invitation to all sign in users at that moment for play a track as next song.
 4. All the invitacions and promotions of tracks should be post into Social networks. 
 5. You could use a Whishlist where you can search and add tracks to be eared only for that user.
 6. You could move tracks between Whishlist and Radiolist.
 7. Search music from different source like YouTube, GrooveShark and Local Files.
 8. Explore a Top 40 Ranking of the most voted tracks.

For every client it is mandatory to authenticate to access such resources.

Here you have the main design of the poject.

![image](https://raw.github.com/Abenchers/social-radio/master/docs/images/RadioSocial-Component-Diagram-v4.png)