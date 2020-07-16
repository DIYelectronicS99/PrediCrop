import joblib
ar=[]
for i in range (3):
  x=int(input())
  ar.append(x)
model = joblib.load('model_Crop.lite')
new_observation=[ar]
print(model.predict(new_observation))