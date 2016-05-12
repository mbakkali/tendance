import pytumblr
import json
from prettyprint import pp
import urllib
import hashlib
from pymongo import MongoClient

# Info pour la connexion Tumblr
client = pytumblr.TumblrRestClient(
    '8t8kFJhAt4lGP9HRFdYqbjed5kYqoQKrZ5aSWtwDONKSHLN1Yp',
    'rClU6yq9TTiGOF2sW0Bi3IqEGYHwAzsI112MpBf68ORMkdkJTX',
    '1mlFiNrnEMkIRwqaAB85SbcE4sZrnKwrNQXwPx1TQ3HJaIb3QE',
    'QtC6g8E2a2lWhi9PAHHE6KK8wwMfbcW8RkZc73O4TvTDpWqM01'
)


# Fonction pour envoyer dans la base de donnee nos photos
def inject_image(client,image):
    db=client.database
    images=db.images
    print images.insert(image).inserted_id


def fetch_tag(client,tag):
    posts = client.tagged(str(tag), limit=2)
    #mongo = 0
    mongo=MongoClient('mongodb://127.0.0.1:27107')
    for post in posts:
        print "processing photo..."
        tags=post["tags"]
        date=post["date"]
        photos=post["photos"][0]
        url=photos["original_size"]["url"]
        filename="photo"+str(abs(hash(url)))+".jpg"
        picture = {
                   "date": date,
                   "photo": urllib.urlopen(url).read(),
                  # "idphoto": picture["photo"],
                   "tags": tags
                   }
        #print picture
        print "insert in db..."
        inject_image(mongo, picture)
        print "writing file..."
        f = open(filename,'w')
        f.write(picture["photo"])
        f.close()

fetch_tag(client,"ootd")
