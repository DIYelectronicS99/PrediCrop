import firebase_admin
from firebase_admin import credentials
from firebase_admin import auth
from firebase_admin import firestore

#service acc creds
cred = credentials.Certificate('fb-sdk.json')

#init app
firebase_admin.initialize_app(cred)

db = firestore.client()

doc_ref = db.collection('weather').document('op1')

doc_ref.set(
    {
        'temp': 20,
        'rain': 132,
        'pressure': 8
    }
)