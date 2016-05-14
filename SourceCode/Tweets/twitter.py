#Import the necessary methods from tweepy library
from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream

#Variables that contains the user credentials to access Twitter API 
access_token = "2955025639-C3qRpAMnTbGyKDhqmrvyQ7LSoMrakV5HNdFyFBd"
access_token_secret = "zXeoL0vmwlwvtAFfZ7av8rj4QwNugoUIF1yrgtRg4NCiB"
consumer_key = "g4PDNF7TanTB7AExcqQANhrn8"
consumer_secret = "9o59MIHDvLs8di40Zhg9XfoDzYT4zREFX02rGrUkOwZqTV05Te"


#This is a basic listener that just prints received tweets to stdout.
class StdOutListener(StreamListener):

    def on_data(self, data):
        print (data)
        return True

    def on_error(self, status):
        print (status)


if __name__ == '__main__':

    #This handles Twitter authetification and the connection to Twitter Streaming API
    l = StdOutListener()
    auth = OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_token, access_token_secret)
    stream = Stream(auth, l)
    stream.filter(track=['beach','explore','mountain','dessert','landscape','hillstation','waterfall','weekendtravel','spring','fall','roadtrip','weekend','weekendmoments','beautifuldestinations','driving','traintravel','airlines','flight','cruise','bus','tourism'])