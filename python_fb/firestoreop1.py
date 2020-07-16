import firebase_admin
from firebase_admin import credentials
from firebase_admin import auth
from firebase_admin import firestore


#service acc creds
cred = credentials.Certificate('fb-sdk.json')

#init app
firebase_admin.initialize_app(cred)

db = firestore.client()
doc_ref = db.collection('weather').document('d1')
#doc_ref1 = db.collection('weather')

val_ref = doc_ref.get(field_paths = {'temp'}).to_dict()
intval = val_ref.get('temp')
doc = doc_ref.get()
if doc.exists:
    print(intval)
else:
    print(u'No such document!')


