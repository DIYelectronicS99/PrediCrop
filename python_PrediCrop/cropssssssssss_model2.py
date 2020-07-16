import joblib
import firebase_admin
from firebase_admin import credentials
from firebase_admin import auth
from firebase_admin import firestore
import time

  #service acc creds
cred = credentials.Certificate('fb-sdk.json')

  #init app
firebase_admin.initialize_app(cred)

db = firestore.client()
doc_ref = db.collection('weather').document('d1')

  #model call
def obs(t,r,h):
  ar=[]
  ar.append(t)
  ar.append(r)
  ar.append(h)
  model = joblib.load('model_Crop2.lite')
  new_observation=[ar]
  s = (model.predict(new_observation))
  finalstr = ' '.join(map(str, s))
  return (finalstr)

while True:



  if doc_ref.get().exists:
    temp_ref = doc_ref.get(field_paths = {'temp'}).to_dict()
    rain_ref = doc_ref.get(field_paths = {'rain'}).to_dict()
    humi_ref = doc_ref.get(field_paths = {'humidity'}).to_dict()
    int_temp = temp_ref.get('temp')
    int_rain = rain_ref.get('rain')
    int_humi = humi_ref.get('humidity')
    if int_rain is not None:
      cropPredict = obs(int_temp,int_rain,int_humi)
    opdoc_ref = db.collection('weather').document('op1')
    opdoc_ref.set(
      {
        'crop': cropPredict
      }
    )
  else:
      print ('fields null')
  time.sleep(3)






