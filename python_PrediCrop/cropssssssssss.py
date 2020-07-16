#Importing the dataset
import numpy as np
import pandas as pd
import joblib

#Importing the dataset
dataset = pd.read_csv("test.csv")
X = dataset.iloc[:, [2, 3, 4]].values
y = dataset.iloc[:, 0].values

# Splitting the dataset into the Training set and Test set
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.25, 
                                                    random_state = 0)

#Linear Regression
from sklearn.ensemble import RandomForestClassifier
classifier = RandomForestClassifier(n_estimators = 50, criterion = 'gini', random_state = 0 )
classifier.fit(X_train,y_train)
accuracy = classifier.score(X_test,y_test)
print (accuracy)
#Predicting the result
y_pred = classifier.predict(X_test)

#Saving the model
joblib.dump(classifier, 'model_Crop2.lite')
